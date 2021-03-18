package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Configuration
public class TestOfSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestOfSpringBootApplication.class, args);
    }

}
