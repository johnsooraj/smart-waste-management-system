package com.cleancity.configurations;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cleancity.wastebin.repository.CustomerBinDataRepository;
import com.cleancity.wastebin.repository.WasteBinRepository;

@EnableAsync
@Configuration
@EnableScheduling
public class CustomAsyncTasks {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	CustomerBinDataRepository customerBinRepo;
	

	@Autowired
	WasteBinRepository binRepo;

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
	@Scheduled(cron = "*/10 * * * * *")
	public void checkBinStatusForTenSecondsIntervel() {
		logger.info("scheduled task running");
	}
}
