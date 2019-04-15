package com.cleancity.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cleancity.user.document.UserDetails;

@Repository
public interface UserDetailRepository extends MongoRepository<UserDetails, String> {

	@Query("UPDATE UserDetails usd SET usd.binCredit = ?2 WHERE usd.id = ?1")
	public boolean updateBinCredit(String id, Double value);

	public UserDetails findByPhone(String phone);

	public List<UserDetails> findAllByOrderByCreateDateDesc();
}
