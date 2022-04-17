package com.example.utilityserviceprovider.web;


import com.example.utilityserviceprovider.domain.Category;
import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MyUserRepo myUserRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    CategoryRepo categoryRepo;

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




    @GetMapping("/customer-signup")
    public String getSignupPage(){
        Category categorya = new Category("Body");
        Category categoryb = new Category("Engine");
        Category categoryc = new Category("ABS System");
        Category categorye = new Category("Airbag");
        Category categoryf = new Category("Electrical system");
        Category categoryj = new Category("Hybrid");
        Category categoryh = new Category("Classic");
        Category categorym = categoryRepo.findCategoriesByTitle("Car");
        categorya.setParent(categorym);
        categoryb.setParent(categorym);
        categoryc.setParent(categorym);
        categorye.setParent(categorym);
        categoryf.setParent(categorym);
        categoryj.setParent(categorym);
        categoryh.setParent(categorym);
        List<Category> list1 = new ArrayList<>();
        list1.add(categorya);
        list1.add(categoryb);
        list1.add(categoryc);
        list1.add(categorye);
        list1.add(categoryf);
        list1.add(categoryj);
        list1.add(categoryh);
        categorym.setChildren(list1);
        categoryRepo.save(categorym);
        categoryRepo.save(categorya);
        categoryRepo.save(categoryb);
        categoryRepo.save(categoryc);
        categoryRepo.save(categorye);
        categoryRepo.save(categoryf);
        categoryRepo.save(categoryj);
        categoryRepo.save(categoryh);

        return "customer-signup";
    }



    @PostMapping("/customer-signup")
    public String postSignupUser(@ModelAttribute MyUser myUser){
        if(myUserRepo.findByUsername(myUser.getUsername())!=null)
        {
            return "customer-signup.html";
            //we should raise an error
        }
        Role role = roleRepo.findRoleByName("CUSTOMER");
        myUser.setPassword(encoder.encode(myUser.getPassword()));
        myUser.setRole(role);
        myUserRepo.save(myUser);
        return "login.html";
    }


//*****************************
    @GetMapping("/service-profile")
    public String getService(){
    return "service-profile";
}

    @GetMapping("/service-signup")
    public String getProviderSignupPage(Model model){
        List<Category> categories = categoryRepo.findAll();
        categories =  categories.stream().filter(index -> index.getParent() == null).collect(Collectors.toList());
        model.addAttribute("categories",categories);
        return "service-signup";
    }
    @PostMapping("/service-signup")

    public String postSignupProvider(@ModelAttribute MyUser myUser ){
        if(myUserRepo.findByUsername(myUser.getUsername())!=null)
        {
            return "service-signup.html";
            //we should raise an error
        }

        Role role = roleRepo.findRoleByName("SERVICEPROVIDER");
        myUser.setPassword(encoder.encode(myUser.getPassword()));
        myUser.setRole(role);
        myUserRepo.save(myUser);
        return "login.html";
    }


//************** Category Controller ****************

@GetMapping("/category/{name}")
public String getCategoryList(@PathVariable(name= "name") String name , Model model){
        List<MyUser> categoryProviders = categoryRepo.findCategoriesByTitle(name).getUsersList();
        model.addAttribute("Providers" , categoryProviders);
        return "providerList";
}


    //--------------------------------------------------customer or service provider ?
    @GetMapping("/signup")
    public String getWhichSignupPage() {
        return "signup";
    }


    //--------------------------------------------------customer profile
    @GetMapping("/customer-profile/{id}")   // need the root name from hamzeh
    public String getCustomerProfile(Model model , @PathVariable long id ) {
        MyUser user = new MyUser();
        user = myUserRepo.findById(id).orElseThrow();
        model.addAttribute("user", user);

        return "profile";
    }
//******************************



    @GetMapping("/logout")
    public RedirectView logout()
    {
        return new RedirectView("/");
    }

}
