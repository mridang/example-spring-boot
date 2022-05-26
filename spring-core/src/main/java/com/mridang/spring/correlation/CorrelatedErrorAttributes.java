package com.mridang.spring.correlation;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import net.saliman.spring.request.correlation.support.RequestCorrelationUtils;

@Component
class CorrelatedErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("requestId", RequestCorrelationUtils.getCurrentRequestId());
        return errorAttributes;
    }
}
