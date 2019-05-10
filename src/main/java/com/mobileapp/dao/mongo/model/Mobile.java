package com.mobileapp.dao.mongo.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


public class Mobile {
	
	@Id
	//@Generated(value = { "" })
	public ObjectId id;
	
	public String model;
	public String year;
	public String imei;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Mobile(ObjectId id, String model, String year, String imei) {
		super();
		this.id = id;
		this.model = model;
		this.year = year;
		this.imei = imei;
	}
	

}
