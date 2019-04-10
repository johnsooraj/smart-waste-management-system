package com.cleancity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.service.WasteBinService;

@RestController
@RequestMapping("/api/bin")
public class BinController {

	@Autowired
	WasteBinService binService;

	// fetch bin details for user app
	@GetMapping
	public List<WasteBin> fetchBinLocations() {
		return binService.fetchAllWasteBins();
	}

	// call from android app of authority
	@PostMapping
	public Object installNewBin(@RequestBody WasteBin bin) {
		return null;
	}

	// update usage for IOT device
	@GetMapping("/usage/{userId}/{binId}/{usage}")
	public Object updateUserUsage(@PathVariable("") String userId, @PathVariable("binId") String binId,
			@PathVariable int usage) throws Exception {
		Object result = binService.updateUserUsage(userId, binId, usage);
		return result;
	}

}
