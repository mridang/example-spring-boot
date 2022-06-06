package com.mridang.spring.xray;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.strategy.LogErrorContextMissingStrategy;

@Configuration
public class XRayConfig {

    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard()
                .withDefaultPlugins()
                .withFastIdGenerator()
                .withContextMissingStrategy(new LogErrorContextMissingStrategy());
        AWSXRay.setGlobalRecorder(builder.build());
    }

    @Bean
    public Filter TracingFilter(@Value("${spring.application.name}") String applicationName) {
        return new AWSXRayServletFilter(applicationName);
    }
}
