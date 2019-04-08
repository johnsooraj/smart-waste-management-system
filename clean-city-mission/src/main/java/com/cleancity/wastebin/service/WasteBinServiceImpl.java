package com.cleancity.wastebin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.repository.WasteBinRepository;

@Service
public class WasteBinServiceImpl implements WasteBinService {

	@Autowired
	WasteBinRepository binRepo;

	@Override
	public WasteBin findByPincode(Long code) {
		return binRepo.findByPincode(code);
	}

	@Override
	public List<WasteBin> fetchAllWasteBins() {
		return binRepo.findAll();
	}

	@Override
	public WasteBin findByAddressLine(String str) {
		return null;
	}

	@Override
	public WasteBin saveWasteBin(WasteBin bin) {
		return binRepo.save(bin);
	}

}
