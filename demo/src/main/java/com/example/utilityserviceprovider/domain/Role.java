package com.example.utilityserviceprovider.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    private String name ;

<<<<<<< HEAD
=======

>>>>>>> d76d375893f02e81927910822046a9f0f480707c
    @OneToMany(mappedBy = "role")
    private List<MyUser> myUser;


    public Role(String name) {
        this.name = name;
    }

}
