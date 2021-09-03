package com.licenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.licenta.controllers")
@ComponentScan("com.licenta.services")
@ComponentScan("com.licenta.repositories")
@ComponentScan("com.licenta.dto.convertor")
@SpringBootApplication
public class LicentaApplication {


    public static void main(String[] args) {
        SpringApplication.run(LicentaApplication.class, args);
    }


}
