package com.reports.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.reports.entity.Eligibility;
import com.reports.repo.EligibleRepo;
import com.reports.request.SearchRequest;
import com.reports.response.SearchResponse;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private EligibleRepo eligibleRepo;

	@Override
	public List<String> getUniquePlanName() {
		return eligibleRepo.findPlanNames();

	}

	@Override
	public List<String> getUniquePlanStatus() {

		return eligibleRepo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {

		List<SearchResponse> response = new ArrayList<>();

		Eligibility queryBuilder = new Eligibility();

		String planName = request.getPlanName();
		if (planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}

		String planStatus = request.getPlanStatus();

		if (planStatus != null && !planName.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}

		LocalDate planStartDate = request.getPlanStartDate();
		if (planStartDate != null) {
			queryBuilder.setPlanStartDate(planStartDate);
		}

		LocalDate planEndDate = request.getPlanEndDate();

		if (planEndDate != null) {
			queryBuilder.setPlanEndDate(planEndDate);
		}

		Example<Eligibility> example = Example.of(queryBuilder);

		List<Eligibility> entities = eligibleRepo.findAll(example);

		for (Eligibility entity : entities) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		}

		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Eligibility> entities = eligibleRepo.findAll();

		HSSFWorkbook workBook = new HSSFWorkbook();

		HSSFSheet sheet = workBook.createSheet();
		HSSFRow headRow = sheet.createRow(0);

		headRow.createCell(0).setCellValue("Name");
		headRow.createCell(1).setCellValue("Mobile");
		headRow.createCell(2).setCellValue("Gender");
		headRow.createCell(3).setCellValue("SSN");

		/*
		 * response.setContentType("application/vnd.ms-excel");
		 * response.setHeader("Content-Disposition",
		 * "attachment; filename=EligibilityDetails.xls")
		 */;

		int i = 1;
		for (Eligibility entity : entities) {
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getMobileNo());
			dataRow.createCell(2).setCellValue(String.valueOf(entity.getGender()));
			dataRow.createCell(3).setCellValue(entity.getSsn());
			i++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();

	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {

		List<Eligibility> entities = eligibleRepo.findAll();

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("SEARCH REPORT", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 3.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("phNo", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		for (Eligibility entity : entities) {
			table.addCell(entity.getName());
			table.addCell(entity.getEamil());
			table.addCell(String.valueOf(entity.getMobileNo()));
			table.addCell(String.valueOf(entity.getGender()));
			table.addCell(String.valueOf(entity.getSsn()));
		}
		document.add(table);
		document.close();
	}

}
