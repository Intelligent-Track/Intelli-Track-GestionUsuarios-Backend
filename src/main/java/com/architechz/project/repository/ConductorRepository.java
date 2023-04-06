package com.architechz.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.architechz.project.models.*;

@Repository
public interface ConductorRepository extends JpaRepository<Driver, Long> {

    Boolean existsByUsername(String username);
    String deleteByUsername(String username);
    Driver findByUsername(String username);
}
