package com.tinqin.academy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tinqin.academy")
public class RestApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestApplication.class, args);
    }

}
