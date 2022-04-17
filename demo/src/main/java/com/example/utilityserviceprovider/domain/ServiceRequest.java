package com.example.utilityserviceprovider.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@Entity
public class ServiceRequest {
    /**
     * Creating datafield id, date, doneRequest
     */

    //id   date  ..etc   customer_id  provider_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @CreationTimestamp
//    private Date date; //must be checked to be unique (manually)

    private boolean doneRequest;
    private boolean accepted;

    private String details;
    private String location;
    //relationships
    @ManyToOne
    MyUser provider;

    @ManyToOne
    MyUser customer;


    /**
     *
     * @Create two Constructors: default Constructor and Constructor passing content and stars
     *
     */

    public ServiceRequest() {
    }


    public ServiceRequest(String details,String location) {
//        this.date = date;
        this.doneRequest = false; //not done when created
        this.accepted=false;    // not accepted when created
        this.details=details;
        this.location=location;
    }

    public boolean isDoneRequest() {
        return doneRequest;
    }



    @Override
    public String toString() {
        return "ServiceRequest{" +
                "doneRequest=" + doneRequest +
                ", accepted=" + accepted +
                ", details='" + details + '\'' +
                ", location='" + location + '\'' +
                ", provider=" + provider +
                ", customer=" + customer +
                '}';
    }
}

