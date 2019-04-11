package com.cleancity.wastebin.service;

import java.util.List;

import com.cleancity.wastebin.document.WasteBin;

public interface WasteBinService {

	public List<WasteBin> fetchAllWasteBins();

	public WasteBin findByAddressLine(String str);

	public WasteBin findByPincode(Long code);

	public WasteBin saveWasteBin(WasteBin bin);

	public WasteBin getWasteBinById(String id);

	public Object updateUserUsage(String userId, String binId, Double usage) throws Exception;
	
	public boolean clearBin(String binId);
}