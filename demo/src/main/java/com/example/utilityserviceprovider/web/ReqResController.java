package com.example.utilityserviceprovider.web;

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

@Controller
public class ReqResController {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    MyUserRepo myUserRepo;
    @Autowired
    ServiceRequestRepository requestRepository;


    @GetMapping("/addRequest/{id}") //id in the route is the provider's id
    public String getRequestForm (Model model ,@PathVariable Long id){
        MyUser provider = myUserRepo.findById(id).orElseThrow();
        model.addAttribute("provider", provider);
        return "request.html";
    }


    // Resolve conflict

    @PostMapping("/addRequest/{id}") //id in the route is the provider's id
    public RedirectView createRequest(@PathVariable Long id , @ModelAttribute ServiceRequest request ){
        ServiceRequest newRequest = new ServiceRequest(request.getDetails(),request.getLocation());
//        bring the provider
        MyUser provider = myUserRepo.findById(id).orElseThrow();
        newRequest.setProvider(provider);
        //bring the customer
        MyUser customer = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newRequest.setCustomer(customer);
        //save the request
        requestRepository.save(newRequest);
        System.out.println("************************going to the home*********************************"+request.toString());

        //add the request to lists
//        customer.addRequest(request);
//        provider.addRequest(request);


        return new RedirectView("/profile/{id}"); //must be changed to redirect us to the provider's page
    }
    //-----------------------------------------------------------------------------

    @GetMapping("/request/{id}") // id = request id
    public String getRequest(@PathVariable Long id,Model model){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        System.out.println("*********************************************************"+request.toString());
        model.addAttribute("request",request);

        return "response.html";
    }

    @PostMapping("/accept-request/{id}") //id is the id of the request
    public RedirectView acceptRequest (@PathVariable Long id){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        request.setAccepted(true);
        System.out.println("*************************accept********************************"+request.toString());
        requestRepository.save(request);
        return new RedirectView("/profile"); //returning to the provider's page /profile/{id}
    }
    @PostMapping("/reject-request/{id}") //id is the id of the request
    public RedirectView rejectRequest (@PathVariable Long id){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        request.setAccepted(false);
        request.setDoneRequest(true);
        requestRepository.save(request);

        System.out.println("*************************reject********************************"+request.toString());

        return new RedirectView("/"); //returning to the provider's page /profile/{id}
    }
    //--------------------------------------------------------------------------------



    //---------------------------------------------------------------------------------
    @ModelAttribute("user")
    public MyUser myUserEverywhere(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == MyUser.class)
        {
            MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
        }
        else
            return null;
    }

}
