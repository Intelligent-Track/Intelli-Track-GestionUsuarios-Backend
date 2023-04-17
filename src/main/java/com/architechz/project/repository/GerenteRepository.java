package com.architechz.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.architechz.project.models.*;
@Repository
public interface GerenteRepository extends JpaRepository<Manager, Long> {

    Boolean existsByUsername(String username);
    String deleteByUsername(String username);
    Manager findByUsername(String username);
}
