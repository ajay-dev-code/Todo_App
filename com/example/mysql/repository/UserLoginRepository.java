package com.example.mysql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mysql.Entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{
	Optional<UserLogin> findByEmail(String email);
}
