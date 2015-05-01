name := """PlayJsonAkkaHttpMarshalling"""

version := "1.0"

scalaVersion := "2.11.6"

// Change this to another test framework if you prefer
libraryDependencies ++= {
  val akkaStreamV = "1.0-M5"
  Seq(
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamV,
    "com.typesafe.play" % "play-json_2.11" % "2.4.0-M3",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )
}

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

