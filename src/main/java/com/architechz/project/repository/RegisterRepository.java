package com.architechz.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.architechz.project.models.RegisterRequest;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterRequest, Long> {

    Boolean existsByUsername(String username);

    @Transactional
    String deleteByUsername(String username);

    RegisterRequest findByUsername(String username);
}
