package com.example.utilityserviceprovider.web;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.domain.Review;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    MyUserRepo myUserRepo;

    @Autowired
    ReviewRepository reviewRepository;

    @PostMapping("/addReview/{id}") //id = sp id
    public RedirectView addReview (@PathVariable Long id , @ModelAttribute  Review review , Model model){
        Review newReview = new Review(review.getContent(),review.getStars());
        MyUser provider =myUserRepo.findById(id).orElseThrow();
        newReview.setProvider(provider);
        reviewRepository.save(newReview);

        return new RedirectView("/");
    }

    @GetMapping("/addReview/{id}") //id = sp id
    public String addReviewGet (@PathVariable Long id ,Model model){
        MyUser provider=myUserRepo.findById(id).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProviderId(id);
        model.addAttribute("provider",provider);
        model.addAttribute("reviews",reviews);
        return "review";
    }

}