package com.cleancity.user.service;

import java.util.List;

import com.cleancity.user.document.UserDetails;

public interface UserService {

	public List<UserDetails> findAll();
	
	public void addDummyData();
}
