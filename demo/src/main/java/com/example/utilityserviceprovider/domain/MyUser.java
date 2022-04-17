package com.example.utilityserviceprovider.domain;

import com.example.utilityserviceprovider.infrastructure.ReviewRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
public class MyUser<set> implements UserDetails{


    @Setter(value= AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)

    private Long id ;

    @Column(unique = true)
    private  String username;

    private  String firstName;
    private  String lastName;
    private  String imageURL;
    private  String password;
    private  String phoneNumber;
    private  String email;

    private  double NumOfStar;
    double avgStar =0.0;
    double sumReviewStars = 0.0;
    int repeatedUser;
//    List<Review> ListOFReview;



    // look for two ways relations

    //relationship with category
    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    private Category category;


    //relationship with role
    @ManyToOne
    @JoinColumn(name = "role_id" , referencedColumnName = "id")
    private Role role;

    @OneToMany
    List<ServiceRequest> requestsList;

    @OneToMany
    List<ServiceRequest> responsesList;

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

    public void addRequest(ServiceRequest request){
        requestsList.add(request);
    }

//    public void addReview(Review review){
//        Boolean flag = false;
//        for (Review rev : this.ListOFReview) {
//            if (rev.getUser() == review.getUser()) {
//                flag = true;
//                repeatedUser++;
//                break;
//            }
//        }
//        if (flag == true) {
//            ListOFReview.add(review);
//        } else {
//            ListOFReview.add(review);
//            updateStars(review);
//        }
//    }
//    protected void  updateStars(Review review){
//        sumReviewStars = sumReviewStars +  review.getStars();
//        avgStar = sumReviewStars/ (ListOFReview.size()- repeatedUser);
//        this.NumOfStar = (double) Math.round(avgStar * 5) / 5;
//    }

    public void addResponse(ServiceRequest response){
        responsesList.add(response);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(role.getName()));
        return authority;
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


    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }


}
