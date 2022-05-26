package com.mridang.spring.correlation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import net.saliman.spring.request.correlation.api.CorrelationIdGenerator;

@Configuration
public class CorrelationConfiguration {

    @Bean
    @Primary
    public CorrelationIdGenerator requestIdGenerator() {
        return new CorrelationIdGenerator() {

            final IdGenerator idGenerator = new AlternativeJdkIdGenerator();

            @Override
            public String generateSessionId(HttpServletRequest request) {
                HttpSession session = request.getSession();
                return session.getId();
            }

            @Override
            public String generateRequestId(HttpServletRequest request) {
                return idGenerator.generateId().toString();
            }
        };
    }
}
