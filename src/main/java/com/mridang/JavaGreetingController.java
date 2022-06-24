package com.mridang;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class JavaGreetingController {

    @QueryMapping
    public String queryJava() {
        return "Hello, Java!";
    }

}
