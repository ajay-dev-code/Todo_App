package com.example.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mysql.Entity.User;

@Repository
public interface UserRepository  extends  JpaRepository<User,Long>{

}
