package com.cleancity.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cleancity.user.document.UserDetails;

@Repository
public interface UserDetailRepository extends MongoRepository<UserDetails, String> {

}
