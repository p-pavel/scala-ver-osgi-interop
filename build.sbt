import com.typesafe.sbt.osgi.Osgi

ThisBuild / organization := "com.perikov"
ThisBuild / publishMavenStyle := true
Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / version := "1.0.0-SNAPSHOT"

lazy val scala212 =
  project
    .in(file("modules/scala212"))
    .enablePlugins(OsgiProject)
    .settings(
      name := "osgi.examples.interop.scala212",
      scalaVersion := "2.12.10",
      libraryDependencies += "com.perikov" %% "osgi-examples-interop-api" % "1.0.0"

    )

lazy val scala3 =
  project
    .in(file("modules/scala3"))
    .enablePlugins(OsgiProject)
    .settings(
      name := "osgi.examples.interop.scala3",
      scalaVersion := "3.4.1",
      libraryDependencies += "com.perikov" %% "osgi-examples-interop-api" % "1.0.0",
    )

lazy val api =
  project
    .in(file("modules/api"))
    .enablePlugins(OsgiProject)
    .settings(
      name := "osgi.examples.interop.api",
      version := "1.0.0",
      scalaVersion := "3.4.1",
      crossScalaVersions := Seq("2.12.10", "3.4.1"),
      OsgiKeys.exportPackage :=
        Seq("com.perikov.osgi.examples.interop.api;version=\"1.0.0\"")
    )

lazy val interop =
  project
    .in(file("."))
    .aggregate(scala212, scala3, api)
