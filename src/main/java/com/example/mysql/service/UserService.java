package com.example.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysql.Entity.User;
import com.example.mysql.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepo;

	public User createusers(User user) {
		return userrepo.save(user);
	}

	public List<User> getalluser() {
		return userrepo.findAll();
	}

	public User getById(Long id) {
		return userrepo.findById(id).orElseThrow(() -> new RuntimeException("User not found" + id));
	}

	public User updateUser( Long id ,User user) {
		return userrepo.save(user);
	}

	public void deleteUserId(Long id) {
		userrepo.deleteById(id);
	}

}
