package com.reports.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reports.entity.Eligibility;

public interface EligibleRepo extends JpaRepository<Eligibility, Integer> {

	@Query("select distinct(planName) from Eligibility")
	public List<String> findPlanNames();

	@Query("select distinct(planStatus) from Eligibility")
	public List<String> findPlanStatus();

}
