package com.cdfive.sentinel.demo.stock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author cdfive
 */
@Slf4j
@ImportResource("classpath:/spring/spring-context.xml")
@SpringBootApplication(scanBasePackages = "com.cdfive.sentinel.demo")
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
        log.info("StockApplication started!");
    }
}
