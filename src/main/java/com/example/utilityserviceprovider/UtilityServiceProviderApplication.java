package com.example.utilityserviceprovider;


import com.example.utilityserviceprovider.domain.Category;
import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


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

            MyUser myUser1 = new MyUser<>("SP", "utility" , "admin" , "https://image.shutterstock.com/image-vector/businessman-silhouette-avatar-profile-picture-260nw-199246385.jpg" , "123" , "example@example.com" , "07888888");
            Role role1 = repository.findRoleByName("SERVICEPROVIDER");
            myUser1.setPassword(encoder.encode(myUser1.getPassword()));
            myUser1.setRole(role1);
            myUserRepo.save(myUser1);

            MyUser myUser2 = new MyUser<>("CT", "utility" , "admin" , "https://icons.veryicon.com/png/o/miscellaneous/two-color-icon-library/user-286.png" , "123" , "example@example.com" , "07888888");
            Role role2 = repository.findRoleByName("CUSTOMER");
            myUser2.setPassword(encoder.encode(myUser2.getPassword()));
            myUser2.setRole(role2);
            myUserRepo.save(myUser2);
//            List<MyUser> accounts = init(new Faker(), 10 ,role1 , myUserRepo);
//            System.out.println("accounts" + accounts);
            List<MyUser> userAccounts = new ArrayList<>();
            List<Category> categories = new ArrayList<>();
            Faker faker = new Faker();
            for (int counter = 0; counter < 10; counter++) {
                categories.add(
                        Category.builder()
                                .title(faker.name().title())
                                .build()
                );
                if(counter%2 == 1 ){
                    categories.get(counter).setParent(categories.get(counter-1));
                    List<Category> list = new ArrayList<>();
                    list.add(categories.get(counter));
                    categories.get(counter-1).setChildren(list);
                }
                categoryRepo.save(categories.get(counter));
            }

            for (int counter = 0; counter < 10; counter++) {
                userAccounts.add(
                        MyUser.builder()
                                .username(faker.name().username())
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .email(faker.internet().emailAddress())
                                .password(faker.internet().password())
                                .phoneNumber(faker.phoneNumber().cellPhone())
                                .imageURL(faker.internet().avatar())
                                .build()
                );
                if (counter < 3){
                    Category category1 = categoryRepo.findCategoryById((long) counter);
                    userAccounts.get(counter).setRole(role);
                    userAccounts.get(counter).setCategory(category1);
                    myUserRepo.save(userAccounts.get(counter));
                }else if (counter < 6){
                    Category category1 = categoryRepo.findCategoryById((long) counter);
                    userAccounts.get(counter).setRole(role1);
                    userAccounts.get(counter).setCategory(category1);
                    myUserRepo.save(userAccounts.get(counter));
                } else{
                    Category category1 = categoryRepo.findCategoryById((long) counter);
                    userAccounts.get(counter).setRole(role2);
                    userAccounts.get(counter).setCategory(category1);
                    myUserRepo.save(userAccounts.get(counter));
                }

            }


        };
    }

//    private static List<MyUser> init(Faker faker, int minimumAccounts , Role role , MyUserRepo myUserRepo) {
//
//
//        return userAccounts;
//    }
}
