ThisBuild / organization := "com.perikov"
ThisBuild / publishMavenStyle := true
Global / onChangedBuildSource := ReloadOnSourceChanges

// val commonSettings:  Seq[sbt.Def.Setting[_]] = Seq(
//   osgiSettings,
//   libraryDependencies += "org.apache.karaf.shell" % "org.apache.karaf.shell.commands" % "4.4.6",
// )

lazy val scala212 =
  project
    .in(file("modules/scala212"))
    .enablePlugins(SbtOsgi)
    .settings(
      name := "com.perikov.osgi.examples.interop.scala212",
      scalaVersion := "2.12.10",
      osgiSettings
    )

lazy val scala3 =
  project
    .in(file("modules/scala3"))
    .enablePlugins(SbtOsgi)
    .settings(
      name := "com.perikov.osgi.examples.interop.scala3",
      scalaVersion := "3.4.1",
      osgiSettings
    )

lazy val interop =
  project
    .in(file("."))
    .aggregate(scala212, scala3)
