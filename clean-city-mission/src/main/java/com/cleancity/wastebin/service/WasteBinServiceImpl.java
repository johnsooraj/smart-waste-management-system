package com.cleancity.wastebin.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleancity.configurations.FirebaseAdminConfig;
import com.cleancity.user.document.UserDetails;
import com.cleancity.user.repository.UserDetailRepository;
import com.cleancity.wastebin.document.BinTracker;
import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.repository.BinTrackerRepository;
import com.cleancity.wastebin.repository.WasteBinRepository;

@Service
public class WasteBinServiceImpl implements WasteBinService {

	static Logger logger = Logger.getLogger(FirebaseAdminConfig.class.getName());

	@Autowired
	WasteBinRepository binRepo;

	@Autowired
	UserDetailRepository userRepo;

	@Autowired
	BinTrackerRepository binTrackRepo;

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
	public Object updateUserUsage(String userId, String binId, Double usage) throws Exception {

		// fetch bin and update new status/usage data
		// fetch user and debt the balance
		Optional<UserDetails> user = userRepo.findById(userId);
		Optional<WasteBin> wasteBin = binRepo.findById(binId);

		if (user.isPresent() && wasteBin.isPresent()) {

			WasteBin binData = wasteBin.get();
			UserDetails userData = user.get();

			// current usage of user * RS:10
			Double userUsage = usage - binData.getBinCurrentCapacity();
			userData.setBinCredit(userData.getBinCredit() - (10 * userUsage));

			// bin capacity will always increase as it use
			binData.setBinCurrentCapacity(usage);
			binData.setTimestamp(new Date());

			// update bin and use data
			binRepo.save(binData);
			userRepo.save(userData);

			this.addBinTracker(binId, userId, userUsage);

		} else {
			throw new Exception("invalid bin or user");
		}
		return null;
	}

	private void addBinTracker(String binId, String userId, Double usage) {
		binTrackRepo.save(new BinTracker(binId, userId, usage));
		logger.info("bin-tracker updated");
	};

	@Override
	public boolean clearBin(String binId) {
		return binRepo.updateBinCurrentCapacity(binId);
	}

}
