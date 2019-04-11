package com.cleancity.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleancity.user.document.UserDetails;
import com.cleancity.user.repository.UserDetailRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDetailRepository userDetailRepo;

	@Override
	public List<UserDetails> findAll() {
		return userDetailRepo.findAll();
	}

	@Override
	public UserDetails saveNewUserUser(UserDetails details) {
		UserDetails user = userDetailRepo.save(details);
		return user;
	}

	@Override
	public UserDetails fetchUserDetails(String id) {
		Optional<UserDetails> user = userDetailRepo.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public boolean updateUserCredit(String userId, Double rate) {
		Optional<UserDetails> data = userDetailRepo.findById(userId);
		if (data.isPresent()) {
			Double currentRate = data.get().getBinCredit() + rate;
			userDetailRepo.updateBinCredit(userId, currentRate);
			return true;
		} else {
			return false;
		}
	}

}
