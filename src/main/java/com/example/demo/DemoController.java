package com.example.demo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/")
    public Map<String, String> index(HttpServletRequest req) {
        return getRequestInformation(req);
    }

    private Map<String, String> getRequestInformation(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put("header: " + key, value);
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            map.put("parameter: " + key, value);
        }
        Cookie[] cookies = Objects.requireNonNullElse(request.getCookies(), new Cookie[] {});
        for (Cookie cookie : cookies) {
            map.put("cookie: " + cookie.getName(), cookie.getValue());
        }
        map.put("getRemoteUser", request.getRemoteUser());
        map.put("getQueryString", request.getQueryString());
        map.put("getAuthType", request.getAuthType());
        map.put("getContextPath", request.getContextPath());
        map.put("getPathInfo", request.getPathInfo());
        map.put("getPathTranslated", request.getPathTranslated());
        map.put("getRequestedSessionId", request.getRequestedSessionId());
        map.put("getRequestURI", request.getRequestURI());
        map.put("getRequestURL", request.getRequestURL().toString());
        map.put("getMethod", request.getMethod());
        map.put("getServletPath", request.getServletPath());
        map.put("getContentType", request.getContentType());
        map.put("getLocalName", request.getLocalName());
        map.put("getProtocol", request.getProtocol());
        map.put("getRemoteAddr", request.getRemoteAddr());
        map.put("getServerName", request.getServerName());
        return map;
    }
}