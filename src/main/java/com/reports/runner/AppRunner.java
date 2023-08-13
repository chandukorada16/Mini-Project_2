package com.reports.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.reports.entity.Eligibility;
import com.reports.repo.EligibleRepo;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private EligibleRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Eligibility entity1 = new Eligibility();
		entity1.setEligibleId(1);
		entity1.setName("Harsha");
		entity1.setMobileNo(9774563210l);
		entity1.setEamil("harsha@gmail.com");
		entity1.setGender('M');
		entity1.setSsn(908975768l);
		entity1.setPlanName("SAP");
		entity1.setPlanStatus("Approved");
		repo.save(entity1);

		Eligibility entity2 = new Eligibility();
		entity2.setEligibleId(2);
		entity2.setName("Chandu");
		entity2.setMobileNo(9603345169l);
		entity2.setEamil("chandukorada@gmail.com");
		entity2.setGender('M');
		entity2.setSsn(908934253l);
		entity2.setPlanName("CCAP");
		entity2.setPlanStatus("DENIED");
		repo.save(entity2);

		Eligibility entity3 = new Eligibility();
		entity3.setEligibleId(3);
		entity3.setName("Shiva");
		entity3.setMobileNo(9989658939l);
		entity3.setEamil("shiva@gmail.com");
		entity3.setGender('M');
		entity3.setSsn(90897576764l);
		entity3.setPlanName("SAP");
		entity3.setPlanStatus("PENDING");
		repo.save(entity3);

		Eligibility entity4 = new Eligibility();
		entity4.setEligibleId(4);
		entity4.setName("Ramu");
		entity4.setMobileNo(9989956309l);
		entity4.setEamil("ramu@gmail.com");
		entity4.setGender('M');
		entity4.setSsn(908975854l);
		entity4.setPlanName("MEDICAL");
		entity4.setPlanStatus("Approved");
		repo.save(entity4);

	}

}
