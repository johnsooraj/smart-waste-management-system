package com.cleancity.configurations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cleancity.wastebin.document.BinTracker;
import com.cleancity.wastebin.document.WasteBin;
import com.cleancity.wastebin.repository.WasteBinRepository;
import com.cleancity.wastebin.repository.WasteBinRepositoryDao;
import com.cleancity.wastebin.service.WasteBinService;

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

	@Autowired
	WasteBinService binService;

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

		logger.info("---------------Fuzzy Logic Start---------------");

		// fetch largest 10 bins
		List<WasteBin> largeTenBins = binService.largestTenBinsByQuantity();
		List<WasteBin> finalOrderOfBins = new ArrayList<WasteBin>();
		Map<String, Double> fuzzyResultSet = new HashMap<String, Double>();
		for (WasteBin wasteBin : largeTenBins) {
			// only check for fuzzy logic if any of the bin is grater than 80%
			if (wasteBin.getBinCurrentCapacity() > 80) {
				logger.info("fuzzy input data count = " + largeTenBins.size());

				logger.info("-> collecting information for fuzzy input id = " + wasteBin.getId());
				// fetch record of bin usage
				List<BinTracker> trackers = binRepo2.fetchTrackForLastTwoDays(wasteBin.getId());
				logger.info("--> fuzzy information count = " + trackers.size());
				if (!trackers.isEmpty()) {
					// calculate average of total deposit by count
					double sum = trackers.stream().mapToDouble(BinTracker::getFilledQuantity).sum();
					double avarge = sum / trackers.size();
					fuzzyResultSet.put(wasteBin.getId(), avarge);
				}

				// sort data
				Map<String, Double> newMap = sortByComparator(fuzzyResultSet, false);
				newMap.forEach((key, value) -> {
					finalOrderOfBins.add(binRepo.findById(key).get());
				});

				tapNotification.buildNotificationBody(
						"cq5JSpx_bvE:APA91bEalqAgx71TVSjYogPb7UvVNx6AeyHL9q4wBsreO5laDsMmd9WjrAanCeSG-g-RHcavscL4QCii_o3N42Pue9d1OX-WUfUI1NmIBvFSgLZWGIK-p4Q1L_3BJFj-TRy3GiNqQ-oQ",
						finalOrderOfBins);
				if (tapNotification.sendNotification()) {
				}
			} else {

			}
		}
		logger.info("---------------Fuzzy Logic End---------------");
	}

	private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order) {

		List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Double>>() {
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Entry<String, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}
