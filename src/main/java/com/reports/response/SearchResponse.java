package com.reports.response;

import javax.persistence.Column;

import lombok.Data;

@Data
public class SearchResponse {

	@Column(name = "ELIGIBLE_NAME")
	private String name;

	@Column(name = "MOBILE_NO")
	private Long mobileNo;

	@Column(name = "Eligible_EMAIL")
	private String eamil;

	@Column(name = "ELI_GENDER")
	private Character gender;

	@Column(name = "EL_SSN")
	private Long ssn;

	

}
