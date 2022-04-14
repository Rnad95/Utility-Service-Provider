package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.Category;
import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GeneralController {
    @Autowired
    CategoryRepo categoryRepo;


    @GetMapping("/")
    public String getHomePage(){
        return "index.html";
    }


}
