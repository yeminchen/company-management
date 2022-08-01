package com.ym.projectmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ym"})
@EnableJpaRepositories(basePackages = {"com.ym"})
@EntityScan(basePackages = {"com.ym"})
@EnableJpaAuditing
public class ProjectManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementServiceApplication.class, args);
    }

}
