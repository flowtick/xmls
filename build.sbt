val scalaV = "2.12.4"

scalacOptions += "-P:scalajs:sjsDefinedByDefault"

lazy val root = project.in(file(".")).
  aggregate(xmlsJS, xmlsJVM).
  settings(
    publish := {},
    publishLocal := {},
    name := "xmls"
  )

lazy val xmls = crossProject.in(file(".")).
  settings(
    name := "xmls",
    version := "0.1-SNAPSHOT",
    scalaVersion := scalaV,
    libraryDependencies += "org.parboiled" %%% "parboiled" % "2.1.4",
    libraryDependencies += "com.chuusai" %%% "shapeless" % "2.3.2",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.3" % Test,
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided"
  )

lazy val xmlsJVM = xmls.jvm
lazy val xmlsJS = xmls.js.settings(
  artifactPath in (Compile, fastOptJS) := baseDirectory.value / ".." / "dist" / "xmls.js",
  artifactPath in (Compile, fullOptJS) := (artifactPath in (Compile, fastOptJS)).value
)