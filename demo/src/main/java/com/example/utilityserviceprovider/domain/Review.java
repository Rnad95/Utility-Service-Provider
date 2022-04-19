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
    private double sumOfStars;
    private double numberOfStars;

    /**
     * @Create two Constructors: default Constructor and Constructor passing content and stars
     *
     */
    public Review() {

    }
    public Review(String content, double stars) {
        this.content = content;
        CheckStar(stars);
        this.stars = calculate(stars);
    }

    private double calculate (double stars){
        this.sumOfStars+=stars;
        this.numberOfStars++;
        this.stars=sumOfStars/numberOfStars;
        return this.stars;
    }

    //relationships

    @ManyToOne
    MyUser provider;





    /**
     * Check the stars
     * @param stars
     */
    public void CheckStar(double stars) {
        if (stars > 0 && stars <= 5) {
            this.stars = stars;
        }else{
            throw new IllegalArgumentException();
        }
    }


}
