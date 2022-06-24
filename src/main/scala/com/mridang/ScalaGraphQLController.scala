package com.mridang

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ScalaGraphQLController {

  @QueryMapping def queryScala: ScalaResponse = {
    ScalaResponse("Foo", Some(69))
  }
}
