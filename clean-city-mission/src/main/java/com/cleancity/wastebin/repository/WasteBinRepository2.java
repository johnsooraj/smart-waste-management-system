package com.cleancity.wastebin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cleancity.wastebin.document.BinTracker;
import com.cleancity.wastebin.document.WasteBin;

@Repository
public class WasteBinRepository2 implements WasteBinRepositoryDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<WasteBin> findBinByLargestQuantity() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC, "binCurrentCapacity"));
		query.limit(10);
		return mongoTemplate.find(query, WasteBin.class);
	}

	@Override
	public List<BinTracker> fetchBinTackerByBinId(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("binId").is(id));
		query.with(Sort.by("dateTime").descending());
		return mongoTemplate.find(query, BinTracker.class);
	}
}
