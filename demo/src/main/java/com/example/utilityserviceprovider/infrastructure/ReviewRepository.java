package com.example.utilityserviceprovider.infrastructure;

import com.example.utilityserviceprovider.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReviewRepository extends JpaRepository<Review,Long> {


}
