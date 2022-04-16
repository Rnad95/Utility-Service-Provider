package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.Category;
import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GeneralController {
    @Autowired
    CategoryRepo categoryRepo;


    @GetMapping("/")
    public String getHomePage(Model model){
        List<Category> categories = categoryRepo.findAll();
        categories =  categories.stream().filter(index -> index.getParent() == null).collect(Collectors.toList());
        model.addAttribute("categories",categories);
        return "index.html";
    }

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }

}
