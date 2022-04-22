//package com.example.utilityserviceprovider.web;
//
//import com.example.utilityserviceprovider.domain.Category;
//import com.example.utilityserviceprovider.domain.MyUser;
//import com.example.utilityserviceprovider.domain.Role;
//import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
//import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
//import com.example.utilityserviceprovider.infrastructure.RoleRepo;
//import com.github.javafaker.Faker;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//public class SampleDatabase implements CommandLineRunner {
//
//    @Autowired
//    MyUserRepo myUserRepo;
//    @Autowired
//    RoleRepo roleRepo;
//        @Autowired
//        private CategoryRepo categoryRepo;
//
//        @Autowired
//        PasswordEncoder encoder;
//
//        private final Faker faker;
//
//        public SampleDatabase(MyUserRepo myUserRepo) {
//            this.myUserRepo = myUserRepo;
//            this.faker = new Faker();
//        }
//
//        @Override
//        public void run(String... args) throws Exception {
//            roleRepo.save(new Role("ADMIN"));
//            roleRepo.save(new Role("CUSTOMER"));
//            roleRepo.save(new Role("SERVICEPROVIDER"));
//
//            categoryRepo.save(new Category("Maintenance"));
//            categoryRepo.save(new Category("Car"));
//            categoryRepo.save(new Category("Electric"));
//
//            Role role = roleRepo.findRoleByName("ADMIN");
//            Category category1 = new Category("Maintenance");
//            List<MyUser> accounts = IntStream.rangeClosed(1, 2)
//                    .mapToObj(myUser -> new MyUser(
//                            faker.name().username(),
//                            faker.name().firstName(),
//                            faker.name().lastName(),
//                            faker.internet().image(),
//                            faker.internet().password(3,5),
//                            faker.phoneNumber().cellPhone(),
//                            faker.internet().emailAddress()
//                    )).collect(Collectors.toList());
//
//            for (int i = 0; i <accounts.size() ; i++) {
//                accounts.get(i).setPassword(encoder.encode(accounts.get(i).getPassword()));
//                accounts.get(i).setRole(role);
//                myUserRepo.save(accounts.get(i));
//            }
//
//
////   ********************* ADD CUSTOMER ************************************
//
//            Role role1 = roleRepo.findRoleByName("CUSTOMER");
//            List<MyUser> customerAccounts = IntStream.rangeClosed(1, 5)
//                    .mapToObj(myUser -> new MyUser(
//                            faker.name().username(),
//                            faker.name().firstName(),
//                            faker.name().lastName(),
//                            faker.internet().image(),
//                            faker.internet().password(3,5),
//                            faker.phoneNumber().cellPhone(),
//                            faker.internet().emailAddress()
//                    )).collect(Collectors.toList());
//            for (int i = 0; i <customerAccounts.size() ; i++) {
//                customerAccounts.get(i).setPassword(encoder.encode(customerAccounts.get(i).getPassword()));
//                customerAccounts.get(i).setRole(role1);
//                myUserRepo.save(customerAccounts.get(i));
//            }
//
//
//// *************************** SERVICE PROVIDER ***********************************
//
//            Role role2 = roleRepo.findRoleByName("SERVICEPROVIDER");
//            List<MyUser> serviceAccounts = IntStream.rangeClosed(1, 5)
//                    .mapToObj(myUser -> new MyUser(
//                            faker.name().username(),
//                            faker.name().firstName(),
//                            faker.name().lastName(),
//                            faker.internet().image(),
//                            faker.internet().password(3,5),
//                            faker.phoneNumber().cellPhone(),
//                            faker.internet().emailAddress()
//                    )).collect(Collectors.toList());
//            for (int i = 0; i <serviceAccounts.size() ; i++) {
//                serviceAccounts.get(i).setPassword(encoder.encode(serviceAccounts.get(i).getPassword()));
//                serviceAccounts.get(i).setRole(role2);
//                myUserRepo.save(serviceAccounts.get(i));
//            }
//
//        }
//
//    }
