package com.example.utilityserviceprovider.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Category {

    @Setter(value= AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id ;

    @Column(unique = true)
    String title;

    //one category can have many users
    @OneToMany (mappedBy = "category")
    List<MyUser> usersList;

}
