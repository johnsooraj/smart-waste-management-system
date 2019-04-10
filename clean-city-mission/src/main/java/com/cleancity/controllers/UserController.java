package com.cleancity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancity.user.document.UserDetails;
import com.cleancity.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<UserDetails> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/{userId}")
	public UserDetails fetchUserDetailsByUserId(@PathVariable String userId) {
		return null;
	}

	@PostMapping
	public UserDetails saveUserDetails(@RequestBody UserDetails userData) {
		return userService.saveNewUserUser(userData);
	}

	@PutMapping
	public UserDetails updateUserDetails(@RequestBody UserDetails userData) {
		return null;
	}

	@DeleteMapping("/{userId}")
	public boolean deleteUserDetails(@PathVariable String userId) {
		return true;
	}
}
