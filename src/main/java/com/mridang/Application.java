package com.mridang;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.amazonaws.xray.spring.aop.XRayEnabled;

import net.saliman.spring.request.correlation.api.EnableRequestCorrelation;

@SpringBootApplication
@EnableRequestCorrelation
@XRayEnabled
public class Application implements EnvironmentAware {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.getProperties().forEach((o, o2) -> logger.info("Found property '{}' with value '{}'", o, o2));
        logger.info("Current active profiles are {}", Arrays.toString(environment.getActiveProfiles()));
    }
}
