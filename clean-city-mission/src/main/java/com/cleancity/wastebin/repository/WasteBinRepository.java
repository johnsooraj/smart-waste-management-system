package com.cleancity.wastebin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cleancity.wastebin.document.WasteBin;

@Repository
public interface WasteBinRepository extends MongoRepository<WasteBin, String> {

	public List<WasteBin> findAllByOrderByCreateDateDesc();

	public WasteBin findByPincode(Long pin);

	public List<WasteBin> findByAddressLine1(String add1);

	public List<WasteBin> findByAddressLine2(String add2);

	public List<WasteBin> findByAddressLine3(String add3);

	@Query("UPDATE WasteBin wb SET wb.binCurrentCapacity = 0.0 WHERE wb.id = ?1")
	public boolean updateBinCurrentCapacity(String id);

}
