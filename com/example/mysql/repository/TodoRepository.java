package com.example.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysql.Entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
