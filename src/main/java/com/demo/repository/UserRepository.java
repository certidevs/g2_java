package com.demo.repository;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //Comprobar si un username o email está ocupado para el registro

    //REGISTRO: verificar si email o username están ocupados
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    //LOGIN: recuperar el user

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByPassword(String password);
}

