package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Role;
import com.example.utilityserviceprovider.domain.ServiceRequest;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import com.example.utilityserviceprovider.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
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

//    @GetMapping("/allProvider")
//    public String getAllProvider (Model model){
//        List<MyUser> listProvider = myUserRepo.findAll();
//        model.addAttribute("SerProvList", listProvider);
//        return "search";
//    }


    @RequestMapping("/allProviders")
    String viewRenderPage(Model model, @Param("keyword") String keyword){
        List<MyUser> listProvider = providerService.listAll(keyword);
        model.addAttribute("SerProvList",listProvider);
        return "render-sp";
    }

    //    public String getRequestForm (Model model ,@PathVariable Long id){
//        MyUser provider = myUserRepo.findById(id).orElseThrow();
//        model.addAttribute("provider", provider);
//        return "request.html";
//    }
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

    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable(name = "id") Long id,Model m) {
        MyUser myUser = providerService.get(id);
        m.addAttribute("myUser", myUser);
        return "edit_profile";

    }

    @PostMapping("/edit/{id}")
    public RedirectView UpdateUser (@ModelAttribute MyUser UserUpdate ){
        MyUser currentUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        currentUser.setFirstName(UserUpdate.getFirstName());
        currentUser.setLastName(UserUpdate.getLastName());
        currentUser.setImageURL(UserUpdate.getImageURL());
        currentUser.setPhoneNumber(UserUpdate.getPhoneNumber());
        currentUser.setEmail(UserUpdate.getEmail());

        System.out.println("******************************************************************"+currentUser);
        System.out.println("**********************************UserUpdate********************************"+UserUpdate);
        myUserRepo.save(currentUser);
        return new RedirectView("/profile/{id}");
    }

    //      DELETE THE ACCOUNT
    @RequestMapping("/delete/{id}")
    public RedirectView deleteProduct(@PathVariable(name = "id") Long id) {
        providerService.delete(id);
        return new RedirectView("/logout-process");
    }

    @PostMapping("/save/{id}")
    public RedirectView createRequest(@PathVariable Long id , @ModelAttribute MyUser myUser ){
        MyUser saveInfo = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        myUserRepo.save(saveInfo);
        return new RedirectView("/myProfile");
    }

}