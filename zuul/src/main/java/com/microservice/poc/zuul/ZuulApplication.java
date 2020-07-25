package com.microservice.poc.zuul;

import com.microservice.poc.zuul.filters.zuul.PostFilter;
import com.microservice.poc.zuul.filters.zuul.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    @Bean
    public PreFilter preFilter() {

        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {

        return new PostFilter();
    }


    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
