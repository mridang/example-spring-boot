package com.mridang;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
class JacksonConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModules(new ProblemModule(), new ConstraintViolationProblemModule(), new JavaTimeModule());
    }
}
