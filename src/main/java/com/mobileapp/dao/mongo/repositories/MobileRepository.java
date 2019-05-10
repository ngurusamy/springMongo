package com.mobileapp.dao.mongo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mobileapp.dao.mongo.model.Mobile;

public interface MobileRepository extends MongoRepository<Mobile, ObjectId> {
	public Mobile findByid(ObjectId id);
	public Mobile findBymodel(String model);
	public Mobile findByimei(String imei);

}
