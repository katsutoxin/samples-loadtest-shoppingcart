enablePlugins(GatlingPlugin)
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

scalaVersion := "2.12.10"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

val gatlingVersion = "3.3.1"

// Docker settings

dockerExposedPorts in Docker := Seq.empty

import com.typesafe.sbt.packager.docker._

dockerUsername in Docker := Some("cloudstateio")

dockerCommands ++= Seq(
  Cmd("User", "root"),
  ExecCmd("RUN", "mkdir", "-p", "/opt/docker/results"),
  ExecCmd("RUN",
    "chmod", "777",
     "/opt/docker/results"),
  Cmd("USER", "1001:0")
)

// Libraries

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion,
  "io.gatling"            % "gatling-test-framework"    % gatlingVersion,
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "com.github.phisgr" %% "gatling-grpc" % "0.8.2")

// Protobuf

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)