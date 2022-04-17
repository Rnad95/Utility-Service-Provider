package com.example.utilityserviceprovider.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
        this.stars = stars;
        CheckStar(stars);
    }

    /**
     *
     * @Getter and
     */

    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public double getStars() {
        return stars;
    }

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
