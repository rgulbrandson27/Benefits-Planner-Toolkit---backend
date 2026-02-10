package com.raina.benefits.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class BenefitsPlannerToolkitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenefitsPlannerToolkitApplication.class, args);
    }

}
