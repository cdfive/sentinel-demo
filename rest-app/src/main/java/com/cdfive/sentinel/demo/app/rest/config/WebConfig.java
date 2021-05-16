package com.cdfive.sentinel.demo.app.rest.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author cdfive
 */
@Slf4j
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<Filter> sentinelFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "true");
        log.info("Sentinel servlet CommonFilter registered");
        return registration;
    }
}
