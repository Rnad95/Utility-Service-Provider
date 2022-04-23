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


    @OneToMany(mappedBy = "role")
    private List<MyUser> myUser;


    public Role(String name) {
        this.name = name;
    }

}
