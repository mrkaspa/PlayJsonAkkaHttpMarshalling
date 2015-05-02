# PlayJsonAkkaHttpMarshalling

PlayJson Marshalling for Akka Http and catching errors

Use Example:

# build.sbt

```scala
resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= {
  Seq(
    "com.kreattiewe.akka" %% "play-json-marshalling" % "1.0"  
    )
}

```

```scala
//Here you catch the errors
val myRejectionHandler = RejectionHandler.newBuilder()
    .handle {
    case MalformedRequestContentRejection(msg, cause) =>
      complete(StatusCodes.BadRequest, cause.get.asInstanceOf[JsonParseThrowable].errors)
  }.result()
  
val routes =
  (logRequestResult("") & handleRejections(myRejectionHandler)) {
    path("login") {
      (post & entity(as[LoginRequest])) {
        complete {}
      }
    }
  }
```
