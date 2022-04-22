package com.example.utilityserviceprovider.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Review {

    /**
     * Creating datafield id, content, string
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String content;
    private double stars;

    /**
     * @Create two Constructors: default Constructor and Constructor passing content and stars
     *
     */
    public Review() {

    }
    public Review(String content, double stars) {
        this.content = content;
        checkStar(stars);
    }

    private void calculate (double stars){
        provider.setSumOfStars(provider.getSumOfStars()+stars);
        provider.setNumberOfStars(provider.getNumberOfStars()+1);
        provider.setRate(provider.getSumOfStars()/provider.getNumberOfStars());
    }
    public void setProvider (MyUser provider){
        this.provider=provider;
        calculate(stars);
    }

    //relationships
    @ManyToOne
    MyUser provider;
    /**
     * Check the stars
     * @param stars
     */
    public void checkStar(double stars) {
        if (stars > 0 && stars <= 5) {
            this.stars = stars;
        }else{
            if(stars>5)
                this.stars=5;
            if(stars<0)
                this.stars=0;
        }
    }


}