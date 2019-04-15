package com.cleancity.wastebin.repository;

import java.util.List;

import com.cleancity.wastebin.document.BinTracker;
import com.cleancity.wastebin.document.WasteBin;

public interface WasteBinRepositoryDao {

	public List<WasteBin> findBinByLargestQuantity();

	public List<BinTracker> fetchBinTackerByBinId(String id);

	public List<BinTracker> fetchTrackForLastTwoDays(String id);
}
