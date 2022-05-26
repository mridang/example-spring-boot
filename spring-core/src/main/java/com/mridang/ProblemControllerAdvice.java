package com.mridang;

import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice(basePackageClasses = {RepositoryRestExceptionHandler.class})
class ProblemControllerAdvice implements ProblemHandling {

    //
}
