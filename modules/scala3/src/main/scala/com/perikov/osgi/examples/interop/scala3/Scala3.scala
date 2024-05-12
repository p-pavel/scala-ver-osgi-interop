
package com.perikov.osgi.examples.interop.scala3

import org.osgi.service
import service.component.annotations.*
import service.log.*
import com.perikov.osgi.examples.interop.api.*

@Component
class Scala3 @Activate (@Reference factory: LoggerFactory) extends Scala3Api:
  val log = factory.getLogger(classOf[Scala3])
  log.info("Scala 3 is activated")
  override def greet(name: String): String = 
    log.info("Scala 3 performs the operation")
    "From Scala3: Hello, " + name

  @Deactivate
  def deactivate(): Unit =
    log.info("Scala 3 is deactivated")

