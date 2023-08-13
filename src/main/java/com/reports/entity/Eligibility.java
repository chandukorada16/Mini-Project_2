package com.reports.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Eligibility {

	@Id
	private Integer eligibleId;

	private String name;

	private Long mobileNo;

	private String eamil;

	private Character gender;

	private Long ssn;

	private String planName;

	private String planStatus;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	private String createdBy;

	private String updatedBy;

}
