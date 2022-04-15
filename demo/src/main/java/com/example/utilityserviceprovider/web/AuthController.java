package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MyUserRepo myUserRepo;

    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/costumer-signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/costumer-signup")
    public String postSignupUser(@ModelAttribute MyUser myUser) {

        Role role = roleRepo.findRoleByName("CUSTOMER");
        myUser.setPassword(encoder.encode(myUser.getPassword()));
        myUser.setRole(role);
        System.out.println(myUser);
        myUserRepo.save(myUser);
        return "login.html";
    }

    @GetMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("/");
    }

    //-------------------------------------------------------service provider sign up
    @GetMapping("/sp-signup")
    public String getSignupPageSP() {
        return "signupSP"; //needs to be created or changed to the html file name (fixed with hadeel)
    }

    @PostMapping("/sp-signup")
    public String postSignupUserSP(@ModelAttribute MyUser myUser) {

        Role role = roleRepo.findRoleByName("SERVICEPROVIDER");
        myUser.setPassword(encoder.encode(myUser.getPassword()));
        myUser.setRole(role);
        System.out.println(myUser);
        myUserRepo.save(myUser);
        return "login.html";
    }

    //--------------------------------------------------customer or service provider ?
    @GetMapping("/signup")
    public String getWhichSignupPage() {
        return "signupSpecify";
    }


    //--------------------------------------------------customer profile
    @GetMapping("/customer-profile/{id}")   // need the root name from hamzeh
    public String getCustomerProfile(Model model , @PathVariable long id ){
        MyUser user = new MyUser();
        user=myUserRepo.findById(id).orElseThrow();
        model.addAttribute("user",user);

        return"profile";
    }

}
