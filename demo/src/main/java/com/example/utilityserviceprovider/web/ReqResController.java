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

import java.util.List;

@Controller
public class ReqResController {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    MyUserRepo myUserRepo;
    @Autowired
    ServiceRequestRepository requestRepository;

    @GetMapping("/my-requests/{id}")
    public String getCustomerRequests(@PathVariable Long id ,Model model){
        List <ServiceRequest> requests = requestRepository.findAllByCustomerId(id);
        model.addAttribute("requests",requests);
        return "customer/customer-requests";
    }

    @GetMapping("/addRequest/{id}") //id in the route is the provider's id
    public String getRequestForm (Model model ,@PathVariable Long id){
        MyUser provider = myUserRepo.findById(id).orElseThrow();
        model.addAttribute("provider", provider);
        return "request.html";
    }


    @PostMapping("/addRequest/{id}") //id in the route is the provider's id
    public RedirectView createRequest(@PathVariable Long id , @ModelAttribute ServiceRequest request ){
        ServiceRequest newRequest = new ServiceRequest(request.getDetails(),request.getLocation(),request.getDate(),request.getTime());

        //        bring the provider
        MyUser provider = myUserRepo.findById(id).orElseThrow();
        newRequest.setProvider(provider);

        List <ServiceRequest> requests = requestRepository.findAllByProviderId(id);
        for(int i=0 ;i<requests.size();i++){

            if(requests.get(i).getDate()==request.getDate() && requests.get(i).getTime()==request.getTime()){
                System.out.println("************************NOT AVAILABLE TIME*************************");
            }
        }
        //bring the customer
        MyUser customer = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newRequest.setCustomer(customer);
        //save the request
        requestRepository.save(newRequest);

        return new RedirectView("/profile/{id}"); //must be changed to redirect us to the provider's page
    }
    //-----------------------------------------------------------------------------

    @GetMapping("/request/{id}") // id = request id
    public String getRequest(@PathVariable Long id,Model model){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        model.addAttribute("request",request);

        return "response.html";
    }

    @PostMapping("/accept-request/{id}") //id is the id of the request
    public RedirectView acceptRequest (@PathVariable Long id){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        request.setAccepted(true);
        requestRepository.save(request);
        return new RedirectView("/requests/{id}"); //returning to the provider's requests page
    }
    @PostMapping("/reject-request/{id}") //id is the id of the request
    public RedirectView rejectRequest (@PathVariable Long id){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        request.setAccepted(false);
        request.setDoneRequest(true);
        requestRepository.save(request);

        return new RedirectView("/requests/{id}"); //returning to the provider's requests page
    }

    @PostMapping("/done-request/{id}")//id is the id of the request
    public RedirectView doneRequest (@PathVariable Long id){
        ServiceRequest request=requestRepository.findById(id).orElseThrow();
        request.setDoneRequest(true);
        requestRepository.save(request);
        return new RedirectView("/requests/{id}");
    }
    //--------------------------------------------------------------------------------
        @GetMapping("/requests/{id}")
        public String checkingPSRequests (@PathVariable Long id ,Model model ){
        MyUser provider=myUserRepo.findById(id).orElseThrow();
        List<ServiceRequest> requests = requestRepository.findAllByProviderId(id);
        model.addAttribute("requests",requests);
        return "service-provider/provider-requests.html";
        }


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
