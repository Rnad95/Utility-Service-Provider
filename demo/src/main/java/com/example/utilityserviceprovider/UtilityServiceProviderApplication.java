package com.example.utilityserviceprovider;

import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UtilityServiceProviderApplication {
    private static final Logger log = LoggerFactory.getLogger(UtilityServiceProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UtilityServiceProviderApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(RoleRepo repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Role("ADMIN")));
            log.info("Preloading " + repository.save(new Role("CUSTOMER")));
            log.info("Preloading " + repository.save(new Role("SERVICEPROVIDER")));
        };
    }
}
