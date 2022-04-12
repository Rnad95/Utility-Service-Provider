package com.example.utilityserviceprovider.infrastructure;

import com.example.utilityserviceprovider.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository <MyUser, Long> {
    UserDetails findByUsername(String username);

}
