package com.example.utilityserviceprovider.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

    @OneToOne(mappedBy = "role")
    private MyUser myUser;


    public Role(String name) {
        this.name = name;
    }

}
