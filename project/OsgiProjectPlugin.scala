package com.perkov.osgi.projectPlugin

import sbt.*
import sbt.Keys.*
import com.typesafe.sbt.osgi
import osgi.OsgiKeys

object OsgiProject extends AutoPlugin {
  override def trigger = allRequirements
  override def requires: Plugins = osgi.SbtOsgi

  def packageName(orgName: String, projName: String, name: String) =
    s"$orgName.$projName.$name"

  object autoImport {
    val commandsPackage =
      settingKey[String]("The package name for the Karaf commands")
  }
  import autoImport.commandsPackage

  val dependencies =
    Seq(
      "org.apache.karaf.shell" % "org.apache.karaf.shell.commands" % "4.4.6",
      "org.osgi" % "org.osgi.framework" % "1.9.0",
    ) ++
      Seq(
        "log" -> "1.4.0",
        "component" -> "1.4.0",
        "component.annotations" -> "1.4.0"
      ).map { case (name, ver) => "org.osgi" % s"org.osgi.service.$name" % ver }
  override def projectSettings: Seq[Setting[_]] =
    osgi.SbtOsgi.autoImport.osgiSettings ++
      Seq(
        commandsPackage := packageName(
          organization.value,
          name.value,
          "commands"
        ),
        OsgiKeys.importPackage := Seq("*"),
        OsgiKeys.privatePackage += commandsPackage.value,
        OsgiKeys.additionalHeaders += (
          "Karaf-Commands" -> commandsPackage.value
        ),
        libraryDependencies ++= dependencies
      )
}
