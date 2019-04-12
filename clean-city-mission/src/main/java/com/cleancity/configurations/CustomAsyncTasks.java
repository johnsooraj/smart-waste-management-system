package com.cleancity.configurations;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.repository.WasteBinRepository;
import com.cleancity.wastebin.repository.WasteBinRepositoryDao;

@EnableAsync
@Configuration
@EnableScheduling
public class CustomAsyncTasks {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	HttpPostNotification tapNotification;

	@Autowired
	WasteBinRepository binRepo;

	@Autowired
	WasteBinRepositoryDao binRepo2;

	/**
	 * <p>
	 * The Fuzzy logic is working here, It will trigger an event to check the status
	 * of each bin that installed in a organization. By Given cut off value, it will
	 * predict that the bin is going to fill, also it will check the next filled
	 * bin, then after it will create a path between these places.
	 * </p>
	 *
	 * <p>
	 * The above cases can list here,
	 * </p>
	 * <ul>
	 * <li>Case 1: Fetch All Bins And Loop it</li>
	 * <li>Case 2: Sort the given list based on capacity/current quantity of
	 * bin</li>
	 * <li>Case 3: Check the bin capacity with our cut off value</li>
	 * </ul>
	 *
	 */

	@Async
	@Scheduled(cron = "*/20 * * * * *")
	public void checkBinStatusForTenSecondsIntervel() {
		logger.info("scheduled task running");
		List<WasteBin> dd = binRepo2.findBinByLargestQuantity();
		tapNotification.buildNotificationBody(
				"f7e7oZfxl9s:APA91bFT_W9GrwEpHGyFPSyh3Ny8g8VhdIAGIOYaslzFKhtyWNLwXBkAuxhri6ao2s5iAauUScUttmY5FAX1FrMoSMUH2yCvTOE_hAU7FwCHhe69TlmqVsfdBmnLMEQriWAKGgWDsbRJ",
				dd);
		if (tapNotification.sendNotification()) {
		}

	}
}
