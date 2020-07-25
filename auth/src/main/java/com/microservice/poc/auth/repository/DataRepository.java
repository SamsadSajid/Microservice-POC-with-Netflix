package com.microservice.poc.auth.repository;

import com.microservice.poc.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<User, String> {

    Optional<User> findByUserName(String userName);

    List<User> findAll();
}
