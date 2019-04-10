package com.cleancity.wastebin.document;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerBinData {

	@Id
	private String id;

	private String binId;

	private String customerId;

	private int binCurrentUsage;
	private Timestamp binLastUsed;

	private boolean status;
	private byte flag;
	private Timestamp createDate;

	public CustomerBinData() {

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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getBinCurrentUsage() {
		return binCurrentUsage;
	}

	public void setBinCurrentUsage(int binCurrentUsage) {
		this.binCurrentUsage = binCurrentUsage;
	}

	public Timestamp getBinLastUsed() {
		return binLastUsed;
	}

	public void setBinLastUsed(Timestamp binLastUsed) {
		this.binLastUsed = binLastUsed;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
