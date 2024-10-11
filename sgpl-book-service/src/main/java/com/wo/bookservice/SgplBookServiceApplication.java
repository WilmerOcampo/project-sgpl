package com.wo.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SgplBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgplBookServiceApplication.class, args);
    }

}
