package com.example.utilityserviceprovider.web;


import com.example.utilityserviceprovider.domain.Category;
import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.ServiceRequest;
import com.example.utilityserviceprovider.infrastructure.CategoryRepo;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


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

}
