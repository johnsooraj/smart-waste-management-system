package com.cleancity.wastebin.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cleancity.wastebin.document.CustomerBinData;

public interface CustomerBinDataRepository extends MongoRepository<CustomerBinData, String> {
	
	public Optional<CustomerBinData> findByCustomerIdAndBinId(String custId, String binId);

}
