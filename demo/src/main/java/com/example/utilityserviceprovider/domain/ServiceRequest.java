package com.example.utilityserviceprovider.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ServiceRequest {
    /**
     * Creating datafield id, date, doneRequest
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date date;
    private boolean doneRequest;
    /**
     * @Create two Constructors: default Constructor and Constructor passing content and stars
     *
     */
    public ServiceRequest() {
    }
    public ServiceRequest(Date date, boolean doneRequest) {
        this.date = date;
        this.doneRequest = doneRequest;
    }

    /**
     *
     * @Getter and
     */
    public Date getDate() {
        return date;
    }
    public boolean isDoneRequest() {
        return doneRequest;
    }

}
