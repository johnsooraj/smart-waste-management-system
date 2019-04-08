package com.cleancity.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.repository.WasteBinRepository;

@Component
public class CustomEventListner {

	@Autowired
	WasteBinRepository binRepo;

	public static final List<WasteBin> binLocations = new ArrayList<WasteBin>();

	@EventListener
	public void handleContextRefreshEvent(ApplicationReadyEvent ctxStartEvt) {
		System.out.println("Context Start Event received.");
		binLocations.add(new WasteBin("Vyttila", 123456l, "9.968919, 76.317752"));
		binLocations.add(new WasteBin("Kadavanthra", 123412l, "9.967274, 76.300080"));
		binLocations.add(new WasteBin("Pallimukku", 123456l, "9.964653, 76.288511"));
		binLocations.add(new WasteBin("Kacheripady", 123456l, "9.985595, 76.281471"));
		binLocations.add(new WasteBin("Kaloor", 123456l, "9.994821, 76.292121"));
		this.addFixedBinLocations();
	}

	private void addFixedBinLocations() {
		binLocations.forEach(bins -> {
			binRepo.save(bins);
		});
	}
}
