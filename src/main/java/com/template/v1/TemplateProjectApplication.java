package com.template.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(scanBasePackages = {"com.template.common", "com.template.v1"})
@EnableRedisRepositories
@EnableJpaRepositories
public class TemplateProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateProjectApplication.class, args);
    }
}
