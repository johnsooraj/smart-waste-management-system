package com.cleancity.wastebin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleancity.user.document.UserDetails;
import com.cleancity.user.repository.UserDetailRepository;
import com.cleancity.wastebin.document.CustomerBinData;
import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.repository.CustomerBinDataRepository;
import com.cleancity.wastebin.repository.WasteBinRepository;

@Service
public class WasteBinServiceImpl implements WasteBinService {

	@Autowired
	WasteBinRepository binRepo;

	@Autowired
	UserDetailRepository userRepo;

	@Autowired
	CustomerBinDataRepository userBinRepo;

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

	@Override
	public WasteBin getWasteBinById(String id) {
		Optional<WasteBin> bin = binRepo.findById(id);
		return bin.isPresent() ? bin.get() : null;
	}

	@Override
	public Object updateUserUsage(String userId, String binId, int usage) throws Exception {
		Optional<UserDetails> details = userRepo.findById(userId);
		Optional<WasteBin> wasteBin = binRepo.findById(binId);

		if (details.isPresent() && wasteBin.isPresent()) {
			CustomerBinData binData = new CustomerBinData();
			binData.setCustomerId(details.get().getId());
			binData.setBinId(wasteBin.get().getId());
			Optional<CustomerBinData> optional = userBinRepo.findByCustomerIdAndBinId(binData.getCustomerId(),
					binData.getBinId());
			if (optional.isPresent()) {
				optional.get().setBinCurrentUsage(optional.get().getBinCurrentUsage() + usage);
				userBinRepo.save(optional.get());
				return optional.get();
			} else {
				CustomerBinData data = new CustomerBinData();
				data.setCustomerId(userId);
				data.setBinId(binId);
				userBinRepo.save(data);
				return data;
			}
			// binData.setBinCurrentUsage(binCurrentUsage);
		} else {
			throw new Exception("invalid bin or user");
		}

	}

}
