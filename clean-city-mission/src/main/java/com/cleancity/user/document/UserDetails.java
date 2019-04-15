package com.cleancity.user.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserDetails {

	@Id
	private String id;
	private String name;
	private Byte age;

	@Indexed(unique = true)
	private String phone;

	private Double binCredit = 00.00;

	private Date timestamp;
	private Date createDate;
	private byte flag;

	private UserAddress userAddress;

	public UserDetails() {

	}

	public UserDetails(String id, String name, Byte age, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Double getBinCredit() {
		return binCredit;
	}

	public void setBinCredit(Double binCredit) {
		this.binCredit = binCredit;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + ", age=" + age + ", phone=" + phone + "]";
	}

}
