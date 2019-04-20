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
		binLocations.add(new WasteBin(0.0, null, null, "Vyttila", 682019l, 9.968919, 76.317752));
		binLocations.add(new WasteBin(0.0, null, null, "Kadavanthra", 682020l, 9.967274, 76.300080));
		binLocations.add(new WasteBin(0.0, null, null, "Pallimukku", 682016l, 9.964653, 76.288511));
		binLocations.add(new WasteBin(0.0, null, null, "Kacheripady", 682018l, 9.985595, 76.281471));
		binLocations.add(new WasteBin(0.0, null, null, "Kaloor", 682017l, 9.994821, 76.292121));
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
