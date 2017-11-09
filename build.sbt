import sbt.url
import sbtrelease.ReleaseStateTransformations._

val scalaV = "2.12.4"

scalacOptions += "-P:scalajs:sjsDefinedByDefault"

lazy val common = Seq(
  name := "xmls",
  organization := "com.flowtick",
  scalaVersion := scalaV,
  crossScalaVersions := Seq(scalaV, "2.11.11"),
  releaseCrossBuild := true,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommand("publishSigned"),
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  ),
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  ),
  publishMavenStyle := true,
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
)

lazy val root = project.in(file(".")).
  settings(common).
  aggregate(xmlsJS, xmlsJVM).
  settings(
    publish := {},
    publishLocal := {},
    PgpKeys.publishSigned := {}
  )

lazy val xmls = crossProject.in(file(".")).
  settings(common).
  settings(
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