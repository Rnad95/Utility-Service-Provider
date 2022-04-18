package com.example.utilityserviceprovider.services;

import com.example.utilityserviceprovider.domain.MyUser;
import com.example.utilityserviceprovider.infrastructure.MyUserRepo;
import com.example.utilityserviceprovider.infrastructure.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private MyUserRepo myUserRepo;
    @Autowired
    private RoleRepo roleRepo;

    public List<MyUser> listAll(String key) {
        if (key != null) {
            return myUserRepo.search(key);
        } else {
            return myUserRepo.findAllByRoleName("SERVICEPROVIDER");
        }

    }
    public  void save (MyUser myUser){
        myUserRepo.save(myUser);
    }

    public MyUser get(long id) {
        return myUserRepo.findById(id).get();
    }

    public void delete(long id) {
        myUserRepo.deleteById(id);
    }

}
