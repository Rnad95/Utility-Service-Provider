package com.example.utilityserviceprovider.infrastructure;

import com.example.utilityserviceprovider.domain.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findAllByProviderId(Long id);

}
