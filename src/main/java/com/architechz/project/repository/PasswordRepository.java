package com.architechz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.architechz.project.models.PasswordRequests;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordRequests, Long> {
    
    PasswordRequests findByToken (String token);

}
