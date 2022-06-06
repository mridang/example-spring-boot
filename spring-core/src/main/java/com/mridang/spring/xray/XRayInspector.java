package com.mridang.spring.xray;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.amazonaws.xray.spring.aop.AbstractXRayInterceptor;

@Aspect
@Component
public class XRayInspector extends AbstractXRayInterceptor {

    @Override
    @Pointcut("@within(com.amazonaws.xray.spring.aop.XRayEnabled) && bean(*Controller)")
    public void xrayEnabledClasses() {
        //
    }
}
