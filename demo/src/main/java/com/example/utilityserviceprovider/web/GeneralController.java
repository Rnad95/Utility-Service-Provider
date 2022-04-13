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
        Category categorya = new Category("a");
        Category categoryb = new Category("b");
        Category categorym = categoryRepo.findCategoriesByTitle("Maintenance");
        categorya.setParent(categorym);
        categoryb.setParent(categorya);
        List<Category> list1 = new ArrayList<>();
        list1.add(categoryb);
        categorya.setChildren(list1);
        List<Category> list = new ArrayList<>();
        list.add(categorya);
        categorym.setChildren(list);
        categoryRepo.save(categorym);
        categoryRepo.save(categorya);
        categoryRepo.save(categoryb);
        return "index.html";
    }

}
