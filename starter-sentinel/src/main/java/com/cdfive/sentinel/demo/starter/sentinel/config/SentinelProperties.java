package com.cdfive.sentinel.demo.starter.sentinel.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author cdfive
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sentinel")
@PropertySource(value = "classpath:sentinel.properties", ignoreResourceNotFound = true)
public class SentinelProperties {

    @Value("${sentinel.enable:true}")
    private Boolean enable;

    @Value("${sentinel.appName:${spring.application.name:#{null}}}")
    private String appName;

    @Value("${sentinel.clientPort:#{null}}")
    private Integer clientPort;

    @Value("${sentinel.dashboardUrl:#{null}}")
    private String dashboardUrl;

    @Value("${sentinel.eager:true}")
    private Boolean eager;
}
