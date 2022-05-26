package com.mridang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
class DefaultRestMvcConfiguration implements RepositoryRestConfigurer {

    private final LocalValidatorFactoryBean localValidatorFactoryBean;

    @Autowired
    public DefaultRestMvcConfiguration(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
            LocalValidatorFactoryBean localValidatorFactoryBean) {
        this.localValidatorFactoryBean = localValidatorFactoryBean;
    }

    @Override
    public void configureValidatingRepositoryEventListener(
            ValidatingRepositoryEventListener validatingRepositoryEventListener) {
        validatingRepositoryEventListener.addValidator("beforeCreate", localValidatorFactoryBean);
        validatingRepositoryEventListener.addValidator("beforeSave", localValidatorFactoryBean);
        validatingRepositoryEventListener.addValidator("beforeSave", localValidatorFactoryBean);
    }

    @Bean
    RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
                config.setRepositoryDetectionStrategy(RepositoryDetectionStrategies.ANNOTATED);
                config.setBasePath("/api/admin");
            }
        };
    }
}
