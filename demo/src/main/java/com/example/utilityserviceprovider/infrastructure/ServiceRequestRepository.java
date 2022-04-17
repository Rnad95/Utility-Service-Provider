package com.example.utilityserviceprovider.infrastructure;

import com.example.utilityserviceprovider.domain.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}
