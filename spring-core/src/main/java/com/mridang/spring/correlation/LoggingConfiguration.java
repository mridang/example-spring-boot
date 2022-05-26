package com.mridang.spring.correlation;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.saliman.spring.request.correlation.api.RequestCorrelationInterceptor;

@Configuration
public class LoggingConfiguration {

    @Bean
    public RequestCorrelationInterceptor correlationLoggingInterceptor() {
        return new RequestCorrelationInterceptor() {
            @Override
            public void afterCorrelationIdSet(String sessionId, String requestId) {
                ThreadContext.put("session-id", sessionId);
                ThreadContext.put("request-id", requestId);
            }

            @Override
            public void cleanUp(String sessionId, String requestId) {
                ThreadContext.remove("session-id");
                ThreadContext.remove("request-id");
            }
        };
    }
}
