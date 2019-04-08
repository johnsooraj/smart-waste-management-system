package com.cleancity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	public List<WasteBin> fetchBinLocations() {
		return binService.fetchAllWasteBins();
	}

	@PostMapping
	public Object installNewBin(@RequestBody WasteBin bin) {
		return null;
	}

}
