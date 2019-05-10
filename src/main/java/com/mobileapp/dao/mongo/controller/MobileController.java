package com.mobileapp.dao.mongo.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.dao.mongo.model.Mobile;
import com.mobileapp.dao.mongo.repositories.MobileRepository;

@RestController
@RequestMapping("/mobiles")
public class MobileController {
	@Autowired
	private MobileRepository repository;
	
	private final MongoTemplate mongoTemplate;
	
	
	public MobileController(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}

	public Mobile findByModel(String model) {
		return repository.findBymodel(model);
	}
	
	public Mobile findByIMEI(String imei) {
		return repository.findByimei(imei);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Mobile> getAllMobile() {
		System.out.println("GET METHOD 1 HIT");
		return repository.findAll();
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public Mobile getMobileById(@PathVariable("id") ObjectId id) {
		System.out.println("GET METHOD 2 HIT");
		return repository.findByid(id);
	}
	
	@RequestMapping(path = "/imei/{imei}", method = RequestMethod.GET)
	public Mobile getMobileByIMEI(@PathVariable("imei") String imei) {
		System.out.println("IMEI NO : " + imei);
		return repository.findByimei(imei);
	}
	
	/*
	 * @RequestMapping(value = "/(imei}", method = RequestMethod.GET) public Mobile
	 * getMobileByimei(String imei) { return repository.findByimei(imei); }
	 */
	
	/*
	 * @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT) public
	 * void modifyMobileById(@PathVariable("id") ObjectId id, @Valid @RequestBody
	 * Mobile mobile) { mobile.setId(id); repository.save(mobile); }
	 */
	@RequestMapping(value = "/update/{imei}", method = RequestMethod.PUT)
	public void modifyMobileByIMEI(@PathVariable("imei") String imei, @Valid @RequestBody Mobile mobile) {
		mobile.setId(repository.findByimei(imei).getId());
		repository.save(mobile);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Mobile createMobile(@Valid @RequestBody Mobile mobile) {
		System.out.println("POST CALLED");
		mobile.setId(ObjectId.get());
		repository.save(mobile);
		return mobile;
	}
	
	@RequestMapping(value = "/search/{val}", method = RequestMethod.GET)
	public Mobile  findMobile(@PathVariable("val") String val) {
		Mobile mobileBean = null;
		
		mobileBean = findByModel(val);
		
		if(mobileBean != null) {
			System.out.println("SEARCHED BY MODEL NAME");
			return mobileBean;
		}
		
		mobileBean = findByIMEI(val);
		
		if(mobileBean != null) {
			System.out.println("SEARCHED BY IMEI NUMBER");
			return mobileBean;
		}
		
		return mobileBean;
	}
	
	@RequestMapping(value = "/match/{pattern}", method = RequestMethod.GET)
	public List<Mobile> getByLikeMatch(@PathVariable("pattern") String pattern) {
		System.out.println("PATTERN MACTH CALL");
		Query query = new Query();
		Criteria criteria = new Criteria();
		
		//query.addCriteria(Criteria.where("model").regex(pattern));
		//criteria.andOperator(Criteria.where("model").regex(pattern), Criteria.where("imei").regex(pattern));
		//query.addCriteria(criteria.orOperator(Criteria.where("model").regex(pattern), Criteria.where("imei").regex(pattern)));
		query.addCriteria(criteria.orOperator(Criteria.where("model").regex(pattern), Criteria.where("imei").regex(pattern)));

		List<Mobile> mobiles = mongoTemplate.find(query, Mobile.class);
		return mobiles;
	}
	
}
