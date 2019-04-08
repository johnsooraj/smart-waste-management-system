package com.cleancity.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cleancity.user.document.UserAddress;
import com.cleancity.user.document.UserDetails;
import com.cleancity.user.repository.UserDetailRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDetailRepository userDetailRepo;

	@Override
	public List<UserDetails> findAll() {
		this.addDummyData();
		return userDetailRepo.findAll();
	}

	@Override
	public void addDummyData() {
		
		UserAddress address  = new  UserAddress();
		address.setAddressLine1("njallor");
		address.setPincode(686671l);
		//
		
		UserDetails u1 = new UserDetails(null, "john", (byte) 23, "473684gbdfg89");
		UserDetails u2 = new UserDetails(null, "sooraj", (byte) 25, "47368bdfbdf489");
		UserDetails u3 = new UserDetails(null, "xavier", (byte) 20, "47368bdxbf489");
		u1.setUserAddress(address);
		
		userDetailRepo.save(u1);
		userDetailRepo.save(u2);
		userDetailRepo.save(u3);
	}

}
