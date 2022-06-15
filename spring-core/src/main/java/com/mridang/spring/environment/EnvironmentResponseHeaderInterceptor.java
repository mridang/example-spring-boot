package com.mridang.spring.environment;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class EnvironmentResponseHeaderInterceptor extends OncePerRequestFilter {

    private final String[] environment;

    @Autowired
    EnvironmentResponseHeaderInterceptor(Environment environment) {
        this.environment = environment.getActiveProfiles();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (environment.length > 0) {
            response.addHeader("X-Environment-Name", environment[0]);
        }
        filterChain.doFilter(request, response);
    }
}
