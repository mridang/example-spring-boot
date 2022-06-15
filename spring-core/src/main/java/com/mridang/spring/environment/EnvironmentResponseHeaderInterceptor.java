package com.mridang.spring.correlation;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import net.saliman.spring.request.correlation.support.RequestCorrelationUtils;

@Component
public class CorrelationResponseHeaderInterceptor extends OncePerRequestFilter {

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("X-Request-Id", RequestCorrelationUtils.getCurrentRequestId());
        filterChain.doFilter(request, response);
    }
}
