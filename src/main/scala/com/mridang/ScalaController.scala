package com.mridang

import io.micrometer.core.annotation.Timed
import org.springframework.http.{MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{RequestBody, RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(path = Array("/probe"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
class ScalaController {

  @Timed
  @RequestMapping(path = Array("/live"), method = Array(RequestMethod.GET))
  def live(): ResponseEntity[Map[String, Any]] =
    ResponseEntity.ok().body(Map("message" -> "I'm alive!"))

  @Timed
  @RequestMapping(path = Array("/send"), method = Array(RequestMethod.POST))
  def send(@RequestBody xx: SearchQuery): ResponseEntity[Map[String, Any]] = {
    println(xx)
    ResponseEntity.ok().body(Map("message" -> "I'm alive!"))
  }


}
