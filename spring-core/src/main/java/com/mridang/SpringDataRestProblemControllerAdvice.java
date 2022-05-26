package com.mridang;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.AdviceTrait;
import org.zalando.problem.violations.Violation;

@ControllerAdvice
class SpringDataRestProblemControllerAdvice implements AdviceTrait {

    @ExceptionHandler
    public ResponseEntity<Problem> handleRepositoryConstraintViolationException(RepositoryConstraintViolationException exception, NativeWebRequest request) {
        List<Violation> violationList = exception.getErrors().getAllErrors()
                .stream()
                .map(objectError -> {
                    if (objectError instanceof FieldError) {
                        return new Violation(((FieldError) objectError).getField(), objectError.getDefaultMessage());
                    }
                    return new Violation(null, objectError.getDefaultMessage());
                })
                .collect(toList());

        ThrowableProblem problem = Problem.builder()
                .withTitle("Constraint Violation")
                .withStatus(Status.BAD_REQUEST)
                .with("violations", violationList)
                .build();
        return create(problem, request);
    }

}
