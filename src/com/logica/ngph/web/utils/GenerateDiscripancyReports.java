package com.logica.ngph.web.utils;


import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.logica.ngph.Entity.BgMast;
import com.logica.ngph.Entity.DiscrepanciesReport;
import com.logica.ngph.Entity.RRNDiscrepancy;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class GenerateDiscripancyReports extends PdfPageEventHelper {
	private HttpServletRequest servletRequest;
	private String reportName;

	public void generatePaymentSentReport(DiscrepanciesReport dtoList,List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports, String reportType) throws Exception {
		if (reportType.equals("EXCEL")) {
			createPaymentSentExcelFile(dtoList,outwardDiscrepanciesReports,inwardDiscrepanciesReports);
		} else if (reportType.equals("PDF")) {
			createPaymentSentPdfFile(dtoList,outwardDiscrepanciesReports,inwardDiscrepanciesReports);
		} else if (reportType.equals("CSV")) {
			createPaymentSentCvsFile(dtoList,outwardDiscrepanciesReports,inwardDiscrepanciesReports);
		}
	}

	private void createPaymentSentPdfFile(DiscrepanciesReport dtoList,List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports) throws Exception {

		Document document = new Document(PageSize.A4, 30, 10, 100, 100);
		String name = reportName.replaceAll(" ", "");
		String fileName = name + getDateTime() + ".pdf";
		File file = new File(fileName);
		FileOutputStream outStream = new FileOutputStream(file);
		document.setMarginMirroring(true);
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, outStream);
			writer.setPdfVersion(PdfWriter.VERSION_1_3);
			// to set header & footer on each page
			writer.setPageEvent(this);
			document.open();
			// to create Search criteria
			document.add(getSearchCriteriaTable(""));
			// to create report data
			PdfPTable table = new PdfPTable(2);
			 PdfPCell cell;
		        cell = new PdfPCell();
		        cell.setBorderWidth(0.0f);
		        cell.setColspan(2);
		        cell.addElement(getReportTable(dtoList,outwardDiscrepanciesReports,inwardDiscrepanciesReports));
		        table.addCell(cell);
		        
		        table.addCell(getReportTable2(outwardDiscrepanciesReports,inwardDiscrepanciesReports));
		        table.addCell(getReportTable3(outwardDiscrepanciesReports,inwardDiscrepanciesReports));
		        document.add(table);   
		//	document.add(getReportTable(dtoList,outwardDiscrepanciesReports,inwardDiscrepanciesReports));
			//document.add(getSearchCriteriaTable("Outwards"));
		//	document.add(getReportTable2(outwardDiscrepanciesReports,inwardDiscrepanciesReports));
			//document.add(getSearchCriteriaTable("Inwards"));
		//	document.add(getReportTable3(outwardDiscrepanciesReports,inwardDiscrepanciesReports));
		} catch (Exception de) {
			de.printStackTrace();
			throw new Exception("Unable to generate file");
		} finally {
			document.close();
			outStream.flush();
			outStream.close();
			writer.close();
		}

		pushFileToDownload(fileName);
	}
	
/*	private PdfPTable getReportParentTable(){
		PdfPTable table = new PdfPTable(2);
		
		 PdfPCell cell;
	        cell = new PdfPCell();
	        cell.setBorderWidth(0.0f);
	        cell.setColspan(2);
	        table.addCell(getReportTable(dtoList,outwardDiscrepanciesReports,inwardDiscrepanciesReports));
		
		
		
		
	}*/
	

	private final static String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(new Date());
	}

	private void createPaymentSentExcelFile(DiscrepanciesReport dtoList,List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports) throws Exception {
		Workbook wb = new HSSFWorkbook();
		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(reportName);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);

		// title row
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(reportName);
		titleCell.setCellStyle(styles.get("title"));
		
		

		// header row
		Row headerRow = sheet.createRow(1);
			Cell headerCelol = headerRow.createCell(0);
			headerCelol.setCellValue("No Of Out Bound Payment At NPCI");
			headerCelol.setCellStyle(styles.get("header"));
			Cell headerCello1h = headerRow.createCell(1);
			headerCello1h.setCellValue(dtoList.getOb_Npci_Count());
			headerCello1h.setCellStyle(styles.get("cell"));
			sheet.autoSizeColumn(0);
			Cell headerCelli1H = headerRow.createCell(4);
			headerCelli1H.setCellValue("No Of In Bound Payment AT NPCI");
			headerCelli1H.setCellStyle(styles.get("header"));
			Cell headerCelli1 = headerRow.createCell(5);
			headerCelli1.setCellValue(dtoList.getIb_Npci_Count());
			headerCelli1.setCellStyle(styles.get("cell"));
			sheet.autoSizeColumn(4);
			
		
		Row headerRow1 = sheet.createRow(2);
		
		Cell headerCelo2 = headerRow1.createCell(0);
		headerCelo2.setCellValue("No Of Out Bound Payment AT QNG");
		headerCelo2.setCellStyle(styles.get("header"));
		Cell headerCello2h = headerRow1.createCell(1);
		headerCello2h.setCellValue(dtoList.getOb_Qng_Count());
		headerCello2h.setCellStyle(styles.get("cell"));
		sheet.autoSizeColumn(1);
		Cell headerCelli2H = headerRow1.createCell(4);
		headerCelli2H.setCellValue("No Of In Bound Payment AT QNG");
		headerCelli2H.setCellStyle(styles.get("header"));
		Cell headerCelli2 = headerRow1.createCell(5);
		headerCelli2.setCellValue(dtoList.getIb_Qng_Count());
		headerCelli2.setCellStyle(styles.get("cell"));
		
		
		Row headerRow3 = sheet.createRow(3);
		Cell headerCelo3 = headerRow3.createCell(0);
		headerCelo3.setCellValue("Payment Sent By NPCI");
		headerCelo3.setCellStyle(styles.get("header"));
		Cell headerCello3h = headerRow3.createCell(1);
		headerCello3h.setCellValue(dtoList.getOb_Diff_Npci());
		headerCello3h.setCellStyle(styles.get("cell"));
	
		Cell headerCelli3H = headerRow3.createCell(4);
		headerCelli3H.setCellValue("Payment Received By NPCI");
		headerCelli3H.setCellStyle(styles.get("header"));
		Cell headerCelli3 = headerRow3.createCell(5);
		headerCelli3.setCellValue(dtoList.getIb_Diff_Npci());
		headerCelli3.setCellStyle(styles.get("cell"));
	
		
	
	Row headerRow4 = sheet.createRow(4);
	
	Cell headerCelo4 = headerRow4.createCell(0);
	headerCelo4.setCellValue("Payment Sent By QNG");
	headerCelo4.setCellStyle(styles.get("header"));
	Cell headerCello4h = headerRow4.createCell(1);
	headerCello4h.setCellValue(dtoList.getOb_Diff_Qng());
	headerCello4h.setCellStyle(styles.get("cell"));

	Cell headerCelli4H = headerRow4.createCell(4);
	headerCelli4H.setCellValue("Payment Recieived By QNG");
	headerCelli4H.setCellStyle(styles.get("header"));
	Cell headerCelli4 = headerRow4.createCell(5);
	headerCelli4.setCellValue(dtoList.getIb_Qng_Count());
	headerCelli4.setCellStyle(styles.get("cell"));
	
	Row headerRowOutward = sheet.createRow(7);	
	int headerCellNumber = 0;
	
	Cell headerCelloutward = headerRowOutward.createCell(headerCellNumber);
	headerCelloutward.setCellValue("RRN");
	headerCelloutward.setCellStyle(styles.get("header"));
	headerCellNumber++;
	
	Cell headerCelloutward1 = headerRowOutward.createCell(headerCellNumber);
	headerCelloutward1.setCellValue("Transaction Date");
	headerCelloutward1.setCellStyle(styles.get("header"));
	headerCellNumber++;
	
	Cell headerCelloutward2 = headerRowOutward.createCell(headerCellNumber);
	headerCelloutward2.setCellValue("Transaction Reference");
	headerCelloutward2.setCellStyle(styles.get("header"));
	headerCellNumber++;
	
	headerCellNumber = 6;
	Cell headerCellinward = headerRowOutward.createCell(headerCellNumber);
	headerCellinward.setCellValue("RRN");
	headerCellinward.setCellStyle(styles.get("header"));
	headerCellNumber++;
	
	Cell headerCellinward1 = headerRowOutward.createCell(headerCellNumber);
	headerCellinward1.setCellValue("Transaction Date");
	headerCellinward1.setCellStyle(styles.get("header"));
	headerCellNumber++;
	
	Cell headerCellinward2 = headerRowOutward.createCell(headerCellNumber);
	headerCellinward2.setCellValue("Transaction Reference");
	headerCellinward2.setCellStyle(styles.get("header"));
	headerCellNumber++;
	
	int rownum = 8;
	for (RRNDiscrepancy dto : outwardDiscrepanciesReports) {
		Row row = sheet.createRow(rownum);
		int cellNumber = 0;
		Cell datatCell1 = row.createCell(cellNumber);
		datatCell1.setCellValue(dto.getRRN());
		datatCell1.setCellStyle(styles.get("cell"));
		cellNumber++;
	
		Cell datatCell2 = row.createCell(cellNumber);
		datatCell2.setCellValue(dto.getTRN_DATE());
		datatCell2.setCellStyle(styles.get("numberCell"));
		cellNumber++;
	
		Cell datatCell3 = row.createCell(cellNumber);
		datatCell3.setCellValue(dto.getTRN_REF());
		datatCell3.setCellStyle(styles.get("dateCell"));
		cellNumber++;
		rownum++;
	}
	rownum = 8;
	for (RRNDiscrepancy dto : inwardDiscrepanciesReports) {
		Row row = sheet.createRow(rownum);
		int cellNumber = 6;
		
			Cell datatCell1 = row.createCell(cellNumber);
			datatCell1.setCellValue(dto.getRRN());
			datatCell1.setCellStyle(styles.get("cell"));
			cellNumber++;
		
			Cell datatCell2 = row.createCell(cellNumber);
			datatCell2.setCellValue(dto.getTRN_DATE());
			datatCell2.setCellStyle(styles.get("numberCell"));
			cellNumber++;
		
			Cell datatCell3 = row.createCell(cellNumber);
			datatCell3.setCellValue(dto.getTRN_REF());
			datatCell3.setCellStyle(styles.get("dateCell"));
			cellNumber++;
			
			rownum++;
		
	}
		String name = reportName.replaceAll(" ", "");
		String fileName = name + getDateTime() + ".xls";
		File file = new File(fileName);
		FileOutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
		pushFileToDownload(fileName);
		out.flush();
	}

	private void createPaymentSentCvsFile(DiscrepanciesReport dtoList,List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports) throws Exception {
		String name = reportName.replaceAll(" ", "");
		String fileName = name + getDateTime() + ".csv";
		File file = new File(fileName);
		FileWriter writer = new FileWriter(file);
			writer.append("No Of Out Bound Payment At NPCI");
			writer.append(',');
			writer.append(dtoList.getOb_Npci_Count()+"");
			writer.append(',');
			writer.append("No Of In Bound Payment AT NPCI");
			writer.append(',');
			writer.append(dtoList.getIb_Npci_Count()+"");
		
			writer.append('\n');
			writer.append("No Of Out Bound Payment AT QNG");
			writer.append(',');
			writer.append(dtoList.getOb_Qng_Count()+"");
			writer.append(',');
			writer.append("No Of In Bound Payment AT QNG");
			writer.append(',');
			writer.append(dtoList.getIb_Qng_Count()+"");
		
			writer.append('\n');
			
			writer.append("Payment Sent By NPCI");
			writer.append(',');
			writer.append(dtoList.getOb_Diff_Npci()+"");
			writer.append(',');
			writer.append("Payment Received By NPCI");
			writer.append(',');
			writer.append(dtoList.getIb_Diff_Npci()+"");
		
			writer.append('\n');
			writer.append("Payment Sent By QNG");
			writer.append(',');
			writer.append(dtoList.getOb_Diff_Qng()+"");
			writer.append(',');
			writer.append("Payment Recieived By QNG");
			writer.append(',');
			writer.append(dtoList.getIb_Qng_Count()+"");
			
			writer.append('\n');
			writer.append('\n');
			writer.append('\n');
			writer.append('\n');
			
			
			//RRN
			writer.append("Outwards");
			writer.append('\n');
			writer.append("RRN");
			writer.append(',');
			writer.append("Transaction Date");
			writer.append(',');
			writer.append("Transaction Reference");
			writer.append('\n');
			for (RRNDiscrepancy dto : outwardDiscrepanciesReports) {
				writer.append(dto.getRRN());
				writer.append(',');
				writer.append(dto.getTRN_DATE());
				writer.append(',');
				
				writer.append(dto.getTRN_REF());
				writer.append('\n');
				
				
			}
			writer.append('\n');
			writer.append('\n');
			writer.append('\n');
			writer.append("Inwards");
			writer.append('\n');
			writer.append("RRN");
			writer.append(',');
			writer.append("Transaction Date");
			writer.append(',');
			
			writer.append("Transaction Reference");
			writer.append('\n');
			for (RRNDiscrepancy dto : inwardDiscrepanciesReports) {
				writer.append(dto.getRRN());
				writer.append(',');
				writer.append(dto.getTRN_DATE());
				writer.append(',');
				
				writer.append(dto.getTRN_REF());
				writer.append('\n');
				
				
			}
			
			

	

		writer.flush();
		writer.close();
		pushFileToDownload(fileName);
	}

	/**
	 * This method will be called on each page creation to set header and footer
	 * 
	 * @param writer
	 * @param document
	 */
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Rectangle page = document.getPageSize();
			// will print the header using the code below
			PdfPTable headTable = getHeader(writer);
			// total width of the table is set
			float width = page.width();
			float leftMargin = document.leftMargin();
			float rightMargin = document.rightMargin();
			float total = width - leftMargin - rightMargin;
			headTable.setTotalWidth(total);
			// the table is printed at the specified location
			headTable.writeSelectedRows(0, -1, document.leftMargin(),
					page.height(), writer.getDirectContent());

			// will print the footer using the code below
			PdfPTable footTable = getFooterSignatures();
			float width1 = page.width();
			float leftMargin1 = document.leftMargin();
			float rightMargin1 = document.rightMargin();
			float total1 = width1 - leftMargin1 - rightMargin1;
			footTable.setTotalWidth(total1);
			footTable.writeSelectedRows(0, -1, document.leftMargin(),
					document.bottomMargin() - 50, writer.getDirectContent());
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * To generate Header in the PDF
	 * 
	 * @param writer
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable getHeader(PdfWriter writer) throws DocumentException {
		PdfPTable headTable = new PdfPTable(1);
		headTable.getDefaultCell().setPadding(2);
		headTable.getDefaultCell().setBorderWidth(0);
		headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 6,
				Font.NORMAL);
		headTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		headTable.addCell(new Phrase("Page No :"
				+ Integer.toString(writer.getPageNumber()), fontStyleCells));
		headTable.addCell("\n");
		try {
			headTable.getDefaultCell().setFixedHeight(20);
			headTable.getDefaultCell().setBackgroundColor(
					Color.getHSBColor(199, 15, 100));
			headTable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_LEFT);
			/*
			 * String destinationPath =
			 * servletRequest.getSession().getServletContext().getRealPath("/");
			 * Image png = Image.getInstance(destinationPath+"images/logo.png");
			 * // to do headTable.addCell(png);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return headTable;
	}

	/**
	 * To generate Footer in the PDF
	 * 
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable getFooterSignatures() throws DocumentException {
		Font fontStyleFooters = FontFactory.getFont(FontFactory.HELVETICA, 9,
				Font.BOLD);
		PdfPTable footTable = new PdfPTable(2);
		footTable.setWidths(new int[] { 80, 20 });
		footTable.setWidthPercentage(100);
		footTable.getDefaultCell().setPadding(2);
		footTable.getDefaultCell().setBorderWidth(0);
		footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		footTable.addCell(new Phrase(
				"@Copyright 2012 QNG. All rights reserved.", fontStyleFooters));
		try {
			footTable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_RIGHT);
			footTable.getDefaultCell().setFixedHeight(20);
			/*
			 * String destinationPath =
			 * servletRequest.getSession().getServletContext().getRealPath("/");
			 * Image png =
			 * Image.getInstance(destinationPath+"images/poweredbyLogica.jpg");
			 * //to do footTable.addCell(png);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return footTable;
	}

	/*
	 * To generate Search Criteria in the PDF
	 */
	private PdfPTable getSearchCriteriaTable(String name) throws DocumentException {
		// Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9,
		// Font.BOLD);
		if((name!=""))
		{
			reportName = name;
		}
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[] { 48, 4, 48 });
		table.setWidthPercentage(100);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setColspan(3);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		Chunk header = new Chunk(reportName, FontFactory.getFont(
				FontFactory.HELVETICA, 13, Font.BOLD));
		header.setUnderline(0.2f, -5f);
		table.addCell(new Phrase(header));

		/*
		 * table.addCell("\n"); table.getDefaultCell().setColspan(1);
		 * table.getDefaultCell().setPadding(2); for(int i=0; i<10; i++){
		 * table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		 * table.addCell(new Phrase("Name",fontStyleHeaders)); //to do
		 * table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 * table.addCell(new Phrase("=",fontStyleHeaders));
		 * table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		 * table.addCell(new Phrase("Value",fontStyleHeaders)); // to do }
		 */
		table.getDefaultCell().setColspan(3);
		table.addCell("\n");
		table.addCell("\n");
		return table;
	}

	/**
	 * To generate Search Criteria in the PDF
	 * 
	 * @param dtoList
	 * @param columnMap
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable getReportTable(DiscrepanciesReport dtoList,List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		/*
		 * table.setWidthPercentage(100); table.getDefaultCell().setPadding(2);
		 * table.getDefaultCell().setBorderWidth(0);
		 * table.getDefaultCell().setColspan(columnMap.size());
		 * table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		 * Chunk header = new Chunk("Results:",
		 * FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
		 * table.addCell(new Phrase(header)); table.addCell("\n");
		 */
		Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9,
				Font.BOLD);
		table.getDefaultCell().setColspan(1);
		table.getDefaultCell().setBorderWidth(0.5f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getAbsoluteWidths();

		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7,
				Font.NORMAL);
		
			table.addCell(new Phrase("No Of OutBound Payment At NPCI", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getOb_Npci_Count()+"", fontStyleCells));
			
			
			table.addCell(new Phrase("No Of InBound Payment At NPCI", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getIb_Npci_Count()+"", fontStyleCells));
			
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("No Of OutBound Payment At QNG", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getOb_Qng_Count()+"", fontStyleCells));
			
			
			table.addCell(new Phrase("No Of InBound Payment At QNG", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getIb_Qng_Count()+"", fontStyleCells));
			
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("No Of OutBound Payment Sent By NPCI, Not At QNG", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getOb_Diff_Npci()+"", fontStyleCells));
			
			table.addCell(new Phrase("No Of InBound Payment Sent By NPCI, Not At QNG", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getIb_Diff_Npci()+"", fontStyleCells));
			
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("No Of OutBound Payment Sent By QNG, Not received At NPCI", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getOb_Diff_Qng()+"", fontStyleCells));
			
			table.addCell(new Phrase("No Of InBound Payment Sent By QNG, Not received At NPCI", fontStyleHeaders));
			table.addCell(new Phrase(dtoList.getIi_Diff_Qng()+"", fontStyleCells));
			
			
			
			
			
		return table;
	}
	
	private PdfPTable getReportTable2(List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports) throws DocumentException {
		PdfPTable table = new PdfPTable(3);
			Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9,
				Font.BOLD);
		table.getDefaultCell().setColspan(1);
		table.getDefaultCell().setBorderWidth(0.5f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getAbsoluteWidths();

		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7,
				Font.NORMAL);
		
			
			
			table.getDefaultCell().setColspan(1);
			table.getDefaultCell().setBorderWidth(0.5f);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.getAbsoluteWidths();

						
				
				table.addCell(new Phrase("RRN", fontStyleHeaders));
				table.addCell(new Phrase("Transaction Ref", fontStyleHeaders));
				table.addCell(new Phrase("Transaction Date", fontStyleHeaders));
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				for (RRNDiscrepancy dto : outwardDiscrepanciesReports) {
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					
						table.addCell(new Phrase(dto.getRRN(), fontStyleCells));
						table.addCell(new Phrase(dto.getTRN_REF(), fontStyleCells));
						table.addCell(new Phrase(dto.getTRN_DATE(),fontStyleCells));
					}
			
				
			
		return table;
	}
	
	private PdfPTable getReportTable3(List<RRNDiscrepancy> outwardDiscrepanciesReports,List<RRNDiscrepancy> inwardDiscrepanciesReports) throws DocumentException {
		PdfPTable table = new PdfPTable(3);
			Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9,
				Font.BOLD);
		table.getDefaultCell().setColspan(1);
		table.getDefaultCell().setBorderWidth(0.5f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getAbsoluteWidths();

		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7,
				Font.NORMAL);
		
			
			
			table.getDefaultCell().setColspan(1);
			table.getDefaultCell().setBorderWidth(0.5f);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.getAbsoluteWidths();

						
				table.addCell(new Phrase("RRN", fontStyleHeaders));
				table.addCell(new Phrase("Transaction Ref", fontStyleHeaders));
				table.addCell(new Phrase("Transaction Date", fontStyleHeaders));
				
				
				
				for (RRNDiscrepancy dto : inwardDiscrepanciesReports) {
					table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					
						table.addCell(new Phrase(dto.getRRN(), fontStyleCells));
						table.addCell(new Phrase(dto.getTRN_REF() + "", fontStyleCells));
						table.addCell(new Phrase(dto.getTRN_DATE() + "",fontStyleCells));
					}
				
			
			
			
		return table;
	}

	/**
	 * Create a library of cell styles
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();
		DataFormat format = wb.createDataFormat();
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style;
		org.apache.poi.ss.usermodel.Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 18);
		titleFont
				.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(titleFont);
		styles.put("title", style);

		org.apache.poi.ss.usermodel.Font subtitleFont = wb.createFont();
		subtitleFont.setFontHeightInPoints((short) 14);
		subtitleFont
				.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(subtitleFont);
		styles.put("subtitle", style);

		org.apache.poi.ss.usermodel.Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 11);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(false);
		styles.put("header", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styles.put("cell", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setDataFormat(createHelper.createDataFormat().getFormat(
				"m/d/yy h:mm"));

		styles.put("dateCell", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setDataFormat(format.getFormat("#,##0.0000"));
		styles.put("numberCell", style);

		return styles;
	}

	private void pushFileToDownload(String fileName) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			File file = new File(fileName);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			/*response.setContentLength(4096);
			response.setHeader("Cache-Control","max-age=0");
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Pragma","public");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader("Expires", 0);*/
			response.setHeader("Cache-Control",null);
			response.setHeader("Pragma",null);
			FileInputStream fileIn = new FileInputStream(fileName);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] outputByte = new byte[4096];
			while (fileIn.read(outputByte, 0, 4096) != -1) {
				outStream.write(outputByte, 0, 4096);
			}
			fileIn.close();
			outStream.flush();
			outStream.close();
			if (file.delete()) {
				System.out.println("File Deleted... ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Download File");
		}
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}


