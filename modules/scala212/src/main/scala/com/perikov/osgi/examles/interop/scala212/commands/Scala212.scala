package com.perikov.osgi.examples.interop.scala212.commands

import org.apache.karaf.shell.api.action.{Command, Argument,lifecycle, Action}
import lifecycle.{Reference, Service}
import org.osgi.service.log.Logger
import org.osgi.service.log.LoggerFactory

import com.perikov.osgi.examples.interop.api.Scala3Api

@Command(scope = "scala212", name = "call3", description = "Calls scala3 code")
@Service
class CallScals3 extends Action {

  @Reference
  var scala3: Scala3Api = _
  @Reference var factory: LoggerFactory = _
  

  override def execute(): Object = {
    val log = factory.getLogger(classOf[CallScals3])
    log.info("Calling Scala 3")
    scala3.greet("Scala 2.12.10")
  }
}