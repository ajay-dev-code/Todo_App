package com.example.mysql.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysql.Entity.UserLogin;
import com.example.mysql.Util.JwtUtil;
import com.example.mysql.repository.UserLoginRepository;
import com.example.mysql.service.UserLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserLoginService service;
	private final UserLoginRepository userrepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		String password = passwordEncoder.encode(body.get("password"));
		
		
		if (userrepo.findByEmail(email).isPresent()) {
			return new ResponseEntity<>("Email alredy exists", HttpStatus.CONFLICT);
		}

		service.createUser(UserLogin.builder().email(email).password(password).build());
		return new ResponseEntity<>("Succesfully register", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		String password = body.get("password");

		var UserLoginOptional = userrepo.findByEmail(email);
		if (UserLoginOptional.isEmpty()) {
			return new ResponseEntity<>("User not Registered ", HttpStatus.UNAUTHORIZED);
		}
		UserLogin userLogin = UserLoginOptional.get();
		if (!passwordEncoder.matches(password, userLogin.getPassword())) {
			return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
		}
		String token = jwtUtil.genrateToken(email);

		return ResponseEntity.ok(Map.of("token", token)); 
	}

	

}
