import SonatypeKeys._

// Your project orgnization (package name)
organization := "com.kreattiewe.akka"

// Your profile name of the sonatype account. The default is the same with the organization
sonatypeProfileName := "com.kreattiewe"

name := """play-json-marshalling"""

version := "1.0"

scalaVersion := "2.11.6"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

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

pomExtra := (
  <url>https://github.com/mrkaspa/PlayJsonAkkaHttpMarshalling</url>
    <licenses>
      <license>
        <name>BSD-style</name>
        <url>http://www.opensource.org/licenses/bsd-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>github.com/mrkaspa/PlayJsonAkkaHttpMarshalling.git</url>
      <connection>scm:git:git@github.com:mrkaspa/PlayJsonAkkaHttpMarshalling.git</connection>
    </scm>
    <developers>
      <developer>
        <id>mrkaspa</id>
        <name>Michel Perez</name>
        <url>http://mrkaspa.com</url>
      </developer>
    </developers>)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

