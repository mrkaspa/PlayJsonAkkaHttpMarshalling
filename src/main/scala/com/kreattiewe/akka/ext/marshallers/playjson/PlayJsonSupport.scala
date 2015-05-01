package com.kreattiewe.akka.ext.marshallers.playjson

import play.api.libs.json._

/**
 * Created by michelperez on 3/30/15.
 */


import akka.http.marshalling.{PredefinedToEntityMarshallers, ToEntityMarshaller}
import akka.http.model.MediaTypes
import akka.http.unmarshalling.{FromEntityUnmarshaller, PredefinedFromEntityUnmarshallers}
import akka.stream.FlowMaterializer
import play.api.libs.json.{Json, Reads, Writes}

import scala.concurrent.ExecutionContext

object PlayJsonSupport extends PlayJsonSupport

/** Play JSON integration for Akka HTTP (un)marshalling. */
trait PlayJsonSupport {

  /** `FromEntityUnmarshaller` for `application/json` depending on a Play JSON `Reads`. */
  implicit def unmarshaller[A](implicit reads: Reads[A], ec: ExecutionContext, mat: FlowMaterializer): FromEntityUnmarshaller[A] =
    PredefinedFromEntityUnmarshallers
      .stringUnmarshaller
      .forContentTypes(MediaTypes.`application/json`)
      .map(s => {
      val readed = reads.reads(Json.parse(s))
      if (readed.isError)
        readed.recoverTotal {
          e => throw new JsonParseException("JSON Validation error", JsonParseThrowable(Json.obj("messages" -> JsError.toFlatJson(e))))
        }
      readed.get
    })

  /** `ToEntityMarshaller` to `application/json` depending on a Play JSON `Writes`. */
  implicit def marshaller[A](implicit writes: Writes[A]): ToEntityMarshaller[A] =
    PredefinedToEntityMarshallers
      .stringMarshaller(MediaTypes.`application/json`)
      .compose(a => writes.writes(a).toString)

  /** Exception class to catch the parse errors **/
  case class JsonParseException(msg: String, jsonParseThrowable: JsonParseThrowable) extends Exception(msg, jsonParseThrowable)

  /** Throwable that wraps the errors **/
  case class JsonParseThrowable(errors: JsValue) extends Throwable

}

