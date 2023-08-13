package com.reports.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reports.request.SearchRequest;
import com.reports.response.SearchResponse;
import com.reports.service.ReportsService;

@RestController
public class ReportsController {

	@Autowired
	private ReportsService service;

	@GetMapping("/planName")
	public ResponseEntity<List<String>> getPlans() {
		List<String> uniquePlanName = service.getUniquePlanName();
		return new ResponseEntity<List<String>>(uniquePlanName, HttpStatus.OK);
	}

	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getStatus() {
		List<String> uniquePlanStatus = service.getUniquePlanStatus();
		return new ResponseEntity<List<String>>(uniquePlanStatus, HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {
		List<SearchResponse> response = service.search(request);
		return new ResponseEntity<List<SearchResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headKey = "Content-Disposition";

		String headValue = "attachment;filename=excel.xls";

		response.setHeader(headKey, headValue);

		service.generateExcel(response);
	}

	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");

		String headKey = "Content-Disposition";

		String headValue = "attachment;filename=data.pdf";

		response.setHeader(headKey, headValue);

		service.generatePdf(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
