package com.example.Keycloak.repository;

import com.example.Keycloak.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByComplaintId(Long complaintId);
}
