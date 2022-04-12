package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MyUserRepo myUserRepo;

    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/costumer-signup")
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping("/costumer-signup")
    public String postSignupUser(@ModelAttribute MyUser myUser){
        Role role = roleRepo.findRoleByName("CUSTOMER");
        myUser.setPassword(encoder.encode(myUser.getPassword()));
        myUser.setRole(role);
        myUserRepo.save(myUser);
        return "login.html";
    }

    @GetMapping("/logout")
    public RedirectView logout()
    {
        return new RedirectView("/");
    }




}
