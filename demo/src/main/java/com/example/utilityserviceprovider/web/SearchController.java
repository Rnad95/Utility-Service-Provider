package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import com.example.utilityserviceprovider.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    ProviderService providerService;
    @Autowired
    MyUserRepo myUserRepo;
    @Autowired
    RoleRepo roleRepo;

//    @RequestMapping("/search")
//    public String viewHomePage(Model model, @Param("keyword") String keyword) {
//        List<MyUser> listProvider = providerService.listAll(keyword);
//        model.addAttribute("SerProvList", listProvider);
//        return "search";
//    }

    @GetMapping("/allProvider")
    public String getAllProvider (Model model){
        List<MyUser> listProvider = myUserRepo.findAll();
        model.addAttribute("SerProvList", listProvider);
        return "search";
    }
//
//    @RequestMapping("/render")
//    String viewRenderPage(Model model, @Param("keyword") String keyword){
//        List<MyUser> listProvider = providerService.listAll(keyword);
//        model.addAttribute("SerProvList",listProvider);
//        return "render-sp";
//    }


    @RequestMapping("/render")
    String viewRenderPage(Model model, @Param("keyword") String keyword){
        List<MyUser> listProvider = providerService.listAll(keyword);
        model.addAttribute("SerProvList",listProvider);
        return "render-sp";
    }

    public String getRequestForm (Model model ,@PathVariable Long id){
        MyUser provider = myUserRepo.findById(id).orElseThrow();
        model.addAttribute("provider", provider);
        return "request.html";
    }
    @GetMapping("/profile/{id}")
    public String getHome(Principal p , Model m, @PathVariable Long id){
        if(p!=null){
//            UserDetails myUser= myUserRepo.findByUsername(p.getName());
            MyUser myUser = myUserRepo.findById(id).orElseThrow();

            m.addAttribute("userAcc",myUser);
            return "service-profile";
        }
        return "service-profile";
    }

    @GetMapping("/myProfile")
    public String myProfile(Principal p , Model m){
        if(p!=null){
            UserDetails myUser= myUserRepo.findByUsername(p.getName());
            m.addAttribute("userAcc",myUser);
            return "my-profile";
        }
        return "my-profile";
    }
//************************************ Try to Edit The information ****************************
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
        ModelAndView MAV = new ModelAndView("edit_profile");
        UserDetails myUser = providerService.get(id);
        MAV.addObject("myUser", myUser);
        return MAV;
    }

//    @PostMapping("/save")
//    public RedirectView saveProduct(@ModelAttribute("myUser") MyUser myUser) {
//        myUserRepo.save(myUser);
//        return new RedirectView("/myProfile");
//    }

//      DELETE THE ACCOUNT
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        providerService.delete(id);
        return "index";
    }
}
