package com.example.utilityserviceprovider.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity

public class MyUser implements UserDetails {

    @Setter(value= AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id ;

    @Column(unique = true)
    private  String username;

    private  String firstName;
    private  String lastName;
    private  String imageURL;
    private  String password;
    private  String phoneNumber;
    private  String email;


    public MyUser(String username, String firstName, String lastName, String imageURL, String password, String email, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public MyUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
