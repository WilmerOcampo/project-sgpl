package com.wo.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class SgplAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgplAuthServiceApplication.class, args);
    }

}
