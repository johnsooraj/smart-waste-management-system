package com.cleancity.wastebin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cleancity.wastebin.document.BinTracker;

public interface BinTrackerRepository extends MongoRepository<BinTracker, String> {

}
