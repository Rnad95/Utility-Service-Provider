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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date date;
    private boolean doneRequest;
    private boolean accepted;

    /**
     *
     * @Create two Constructors: default Constructor and Constructor passing content and stars
     *
     */

    public ServiceRequest() {
    }

    public ServiceRequest(Date date, boolean doneRequest,boolean accepted) {
        this.date = date;
        this.doneRequest = false; //not done when created
        this.accepted=false;    // not accepted when created
    }

    public boolean isDoneRequest() {
        return doneRequest;
    }


}