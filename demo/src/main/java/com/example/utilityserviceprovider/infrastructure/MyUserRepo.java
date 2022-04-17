package com.example.utilityserviceprovider.infrastructure;

import com.example.utilityserviceprovider.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserRepo extends JpaRepository <MyUser, Long> {

    UserDetails findByUsername(String username);
    List<MyUser> findAllByRoleName(String role);

    //    Adding them to the search operation
    @Query("select serviceUser from MyUser serviceUser where serviceUser.firstName like %?1%"
            +"or serviceUser.lastName like %?1%"
            +"or serviceUser.username like %?1%")
    List<MyUser> search(String key);

}
