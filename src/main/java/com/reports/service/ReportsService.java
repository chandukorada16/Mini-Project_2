package com.reports.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.reports.request.SearchRequest;
import com.reports.response.SearchResponse;

public interface ReportsService {

	public List<String> getUniquePlanName();

	public List<String> getUniquePlanStatus();

	public List<SearchResponse> search(SearchRequest request);

	public void generateExcel(HttpServletResponse response) throws Exception;

	public void generatePdf(HttpServletResponse response) throws Exception;

}
