package com.example.utilityserviceprovider.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class Admin extends MyUser{

}
