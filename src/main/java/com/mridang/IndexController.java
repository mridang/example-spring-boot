package com.mridang;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.newrelic.api.agent.NewRelic;

@Validated
@Controller
public class IndexController {

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public void health() {
        NewRelic.ignoreTransaction();
    }
}
