package com.mridang;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers()
                .xssProtection().disable()
                .frameOptions().disable()
                .and()
                .authorizeRequests(a -> a
                        .antMatchers("/public/**").permitAll()
                        .antMatchers("/actuator/**").permitAll()
                        .anyRequest().permitAll());
    }
}
