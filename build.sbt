import sbt.url

val scalaV = "2.12.12"

scalacOptions ++= Seq(
  "-P:scalajs:sjsDefinedByDefault"
)

inThisBuild(List(
  organization := "com.flowtick",
  licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  homepage := Some(url("https://github.com/flowtick/xmls")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/flowtick/xmls.git"),
      "scm:git@github.com:flowtick/xmls.git"
    )
  ),
  developers := List(
    Developer(id = "adrobisch", name = "Andreas Drobisch", email = "github@drobisch.com", url = url("http://drobisch.com/"))
  )
))

lazy val common = Seq(
  name := "xmls",
  scalaVersion := scalaV,
  crossScalaVersions := Seq(scalaV, "2.13.3")
)

lazy val root = project.in(file(".")).
  settings(common).
  aggregate(xmlsJS, xmlsJVM).
  settings(
    publish := {},
    publishLocal := {},
    PgpKeys.publishSigned := {}
  )

lazy val xmls = crossProject(JSPlatform, JVMPlatform).in(file(".")).
  settings(common).
  settings(
    libraryDependencies += "org.parboiled" %%% "parboiled" % "2.2.0",
    libraryDependencies += "com.chuusai" %%% "shapeless" % "2.3.3" % Provided,
    libraryDependencies += "org.scala-lang.modules" %%% "scala-xml" % "1.3.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.2" % Test,
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.0.0" % Provided
  )

lazy val xmlsJVM = xmls.jvm
lazy val xmlsJS = xmls.js.settings(
  artifactPath in (Compile, fastOptJS) := baseDirectory.value / ".." / "dist" / "xmls.js",
  artifactPath in (Compile, fullOptJS) := (artifactPath in (Compile, fastOptJS)).value
)
