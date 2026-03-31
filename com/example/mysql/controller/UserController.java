package com.example.mysql.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysql.Entity.User;
import com.example.mysql.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
	private UserService userservice;

	@PostMapping
	public ResponseEntity<User> createuser(@Valid @RequestBody User user) {

		User createUser = userservice.createusers(user);
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<User>> getalluser() {
		List<User> users = userservice.getalluser();
		return ResponseEntity.ok(users);
	}

	@GetMapping("{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {

		User users = userservice.getById(id);
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

		User updateUser = userservice.createusers(user);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserId(@PathVariable Long id) {
		userservice.deleteUserId(id);
		return ResponseEntity.ok("User Deleted Successfully !");
	}
	

}
