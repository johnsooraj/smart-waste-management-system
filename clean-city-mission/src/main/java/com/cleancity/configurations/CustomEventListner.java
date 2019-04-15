package com.cleancity.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.cleancity.user.document.UserAddress;
import com.cleancity.user.document.UserDetails;
import com.cleancity.user.service.UserService;
import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.service.WasteBinService;

@Component
public class CustomEventListner {

	@Autowired
	WasteBinService binService;

	@Autowired
	UserService userService;

	public static final List<WasteBin> binLocations = new ArrayList<WasteBin>();

	@EventListener
	public void handleContextRefreshEvent(ApplicationReadyEvent ctxStartEvt) {
		System.out.println("Context Start Event received.");
		binLocations.add(new WasteBin("Vyttila", 123456l, "9.968919, 76.317752"));
		binLocations.add(new WasteBin("Kadavanthra", 123412l, "9.967274, 76.300080"));
		binLocations.add(new WasteBin("Pallimukku", 133456l, "9.964653, 76.288511"));
		binLocations.add(new WasteBin("Kacheripady", 144456l, "9.985595, 76.281471"));
		binLocations.add(new WasteBin("Kaloor", 67456l, "9.994821, 76.292121"));
		this.addFixedBinLocations();
		this.addDummyUser();
	}

	private void addFixedBinLocations() {
		binLocations.forEach(bins -> {
			binService.saveWasteBin(bins);
		});
	}

	private void addDummyUser() {
		UserDetails details = new UserDetails();
		UserAddress address = new UserAddress();
		details.setAge((byte) 23);
		details.setName("john");
		details.setPhone("9447599402");
		address.setPincode(686671l);
		address.setGeolocation("");
		address.setAddressLine1("njaloor house");
		details.setUserAddress(address);
		userService.saveNewUserUser(details);

	}
}
