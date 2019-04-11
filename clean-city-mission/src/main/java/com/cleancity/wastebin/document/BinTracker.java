package com.cleancity.wastebin.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Track the filling recode for Fuzzy Logic
 */

@Document
public class BinTracker {

	@Id
	private String id;
	private String binId;
	private String userId;
	private LocalDateTime dateTime;
	private Double filledQuantity;

	public BinTracker() {

	}

	public BinTracker(String binId, String userId, Double filledQuantity) {
		super();
		this.binId = binId;
		this.userId = userId;
		this.filledQuantity = filledQuantity;
		this.dateTime = LocalDateTime.now();	// create time of this object
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBinId() {
		return binId;
	}

	public void setBinId(String binId) {
		this.binId = binId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Double getFilledQuantity() {
		return filledQuantity;
	}

	public void setFilledQuantity(Double filledQuantity) {
		this.filledQuantity = filledQuantity;
	}

}
