package com.cleancity.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cleancity.user.document.UserDetails;

@Repository
public interface UserDetailRepository extends MongoRepository<UserDetails, String> {

	Page<UserDetails> findByFirstname(String firstname, Pageable pageable);
	
}
