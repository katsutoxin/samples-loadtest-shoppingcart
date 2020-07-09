addSbtPlugin("io.gatling" % "gatling-sbt" % "3.1.0")

addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.31")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.10.2"

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1")

libraryDependencies += "com.spotify" % "docker-client" % "8.9.0"
