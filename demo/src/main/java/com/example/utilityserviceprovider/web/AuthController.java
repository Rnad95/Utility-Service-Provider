package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MyUserRepo myUserRepo;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignupUser(@RequestParam String username,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String imageURL,@RequestParam String password,@RequestParam String email,@RequestParam String phoneNumber){
        MyUser myUser = new MyUser( username,  firstName,  lastName,  imageURL,  encoder.encode(password),  email,  phoneNumber);
        myUserRepo.save(myUser);
        return "login.html";
    }

    @GetMapping("/logout")
    public RedirectView logout()
    {
        return new RedirectView("/");
    }




}
