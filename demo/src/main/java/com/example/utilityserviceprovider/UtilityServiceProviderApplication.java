package com.example.utilityserviceprovider;


import com.example.utilityserviceprovider.domain.Category;
import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class UtilityServiceProviderApplication {
    private static final Logger log = LoggerFactory.getLogger(UtilityServiceProviderApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(UtilityServiceProviderApplication.class, args);
    }

    @Bean

    CommandLineRunner initDatabase(RoleRepo repository , CategoryRepo categoryRepo , PasswordEncoder encoder , MyUserRepo myUserRepo) {

        return args -> {
            log.info("Preloading " + repository.save(new Role("ADMIN")));
            log.info("Preloading " + repository.save(new Role("CUSTOMER")));
            log.info("Preloading " + repository.save(new Role("SERVICEPROVIDER")));

            log.info("Preloading " + categoryRepo.save(new Category("Maintenance")));
            log.info("Preloading " + categoryRepo.save(new Category("Car")));
            log.info("Preloading " + categoryRepo.save(new Category("Electric")));

            MyUser myUser = new MyUser<>("admin", "utility" , "admin" , "/png" , "123456" , "admin@admin.com" , "07888888");
            Role role = repository.findRoleByName("ADMIN");
            myUser.setPassword(encoder.encode(myUser.getPassword()));
            myUser.setRole(role);
            myUserRepo.save(myUser);
        };
    }
}
