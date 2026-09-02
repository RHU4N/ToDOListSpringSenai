package com.biolab.todolista.repositories;

import com.biolab.todolista.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Repository com inversão de dependancias
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //função que retorna um boleano para emular login
    boolean existsByUsernameAndPassword(String username, String password);
}
