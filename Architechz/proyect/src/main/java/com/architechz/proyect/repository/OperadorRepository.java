package com.architechz.proyect.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.architechz.proyect.models.*;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {


}
