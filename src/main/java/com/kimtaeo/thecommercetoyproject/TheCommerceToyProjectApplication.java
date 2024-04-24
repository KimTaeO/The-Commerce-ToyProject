package com.kimtaeo.thecommercetoyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TheCommerceToyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheCommerceToyProjectApplication.class, args);
    }

}
