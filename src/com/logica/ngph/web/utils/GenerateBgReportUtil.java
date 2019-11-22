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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.logica.ngph.dtos.BgMastDto;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class GenerateBgReportUtil extends PdfPageEventHelper {
	private HttpServletRequest servletRequest;
	private String reportName;

	public void generatePaymentSentReport(List<BgMastDto> dtoList,	Map<String, String> columnMap, String reportType) throws Exception {
		if (reportType.equals("EXCEL")) {
			createPaymentSentExcelFile(dtoList, columnMap);
		} else if (reportType.equals("PDF")) {
			createPaymentSentPdfFile(dtoList, columnMap);
		} else if (reportType.equals("CSV")) {
			createPaymentSentCvsFile(dtoList, columnMap);
		}
	}

	private void createPaymentSentPdfFile(List<BgMastDto> dtoList,
			Map<String, String> columnMap) throws Exception {

		Document document = new Document(PageSize.A4,2,2,70,70);
		
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
			document.add(getSearchCriteriaTable());
			// to create report data
			document.add(getReportTable(dtoList, columnMap));
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

	private final static String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(new Date());
	}

	private void createPaymentSentExcelFile(List<BgMastDto> dtoList,
			Map<String, String> columnMap) throws Exception {
		Workbook wb = new HSSFWorkbook();
		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(reportName);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);

		// title row
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(30);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(reportName);
		titleCell.setCellStyle(styles.get("title"));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnMap.size()));

		// header row
		Row headerRow = sheet.createRow(1);
		columnMap.entrySet();
		int headerCellNumber = 0;
		if (columnMap.get("bgNumber") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Bg Number");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgCreateType") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Bg Type");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		BgMastDto bgMastDto = dtoList.get(0);
		String direction = bgMastDto.getBgDirection();
		if (columnMap.get("advisingBank") != null) {
			if(direction.equals("O")){
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Advising Bank");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
			}else if(direction.equals("I")){
				Cell headerCell = headerRow.createCell(headerCellNumber);
				headerCell.setCellValue("Issuing Bank");
				headerCell.setCellStyle(styles.get("header"));
				headerCellNumber++;	
			}
		}
		if (columnMap.get("bgIssueDate") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Issue Date");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgAmount") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Amount");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("details") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Details");
			headerCell.setCellStyle(styles.get("header"));
			sheet.autoSizeColumn(headerCellNumber);
			headerCellNumber++;
		}
		if (columnMap.get("bgStatus") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Status");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgLastModifiedTime") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Last Modified Time");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("msgStatus") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Msg Status");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		/*if (columnMap.get("noOfMsg") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("No Of Message");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgRuleCode") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Bg Rule Code");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgRuleDesc") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Bg Rule Desc");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}

		if (columnMap.get("bgNarration") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Narration");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgNoOfAmntmnt") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Bg No Of Amntmnt");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if (columnMap.get("bgLastModifiedTime") != null) {
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Bg Last Modified Time");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		*/
		

		int rownum = 2;
		for (BgMastDto dto : dtoList) {
			Row row = sheet.createRow(rownum);
			int cellNumber = 0;
			if (columnMap.get("bgNumber") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgNumber());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			if (columnMap.get("bgCreateType") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgCreateType());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			if (columnMap.get("advisingBank") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getAdvisingBank());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			if (columnMap.get("bgIssueDate") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgIssueDate());
				datatCell.setCellStyle(styles.get("dateCell"));
				cellNumber++;
			}
			if (columnMap.get("bgAmount") != null) {
				Cell datatCell = row.createCell(cellNumber);
				if (dto.getBgAmount() != null)
					datatCell.setCellValue(dto.getBgAmount() + "");
				datatCell.setCellStyle(styles.get("numberCell"));
				cellNumber++;
			}
			if (columnMap.get("details") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getDetails());
				datatCell.setCellStyle(styles.get("cell"));
				sheet.autoSizeColumn(cellNumber);
				cellNumber++;
			}
			if (columnMap.get("bgStatus") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgStatus());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			if (columnMap.get("bgLastModifiedTime") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgLastModifiedTime());
				datatCell.setCellStyle(styles.get("dateCell"));
				cellNumber++;
			}
			if (columnMap.get("msgStatus") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getMsgStatus());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			
/*			if (columnMap.get("noOfMsg") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getNoOfMsg());
				datatCell.setCellStyle(styles.get("numberCell"));
				cellNumber++;
			}
			if (columnMap.get("bgRuleCode") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgRuleCode());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			if (columnMap.get("bgRuleDesc") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgRuleDesc());
				datatCell.setCellStyle(styles.get("cell"));
				cellNumber++;
			}
			if (columnMap.get("bgNarration") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgNarration());
				datatCell.setCellStyle(styles.get("numberCell"));
				cellNumber++;
			}
			if (columnMap.get("bgNoOfAmntmnt") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgNoOfAmntmnt() + "");
				datatCell.setCellStyle(styles.get("numberCell"));
				cellNumber++;
			}
			if (columnMap.get("bgLastModifiedTime") != null) {
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getBgLastModifiedTime());
				datatCell.setCellStyle(styles.get("dateCell"));
				cellNumber++;
			}*/
			rownum++;
		}

		/*
		 * rownum = rownum+3; Row row =null; row = sheet.createRow(rownum); Cell
		 * searchCriteriaHeaderCell = row.createCell(0);
		 * row.setHeightInPoints(20);
		 * searchCriteriaHeaderCell.setCellValue("Search Criteria");
		 * searchCriteriaHeaderCell.setCellStyle(styles.get("subtitle"));
		 * sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 20));
		 * rownum++;
		 * 
		 * for(int i=0; i<7; i++){ row = sheet.createRow(rownum); Cell
		 * searchCriteriaCell = row.createCell(0);
		 * searchCriteriaCell.setCellValue("Name : value");
		 * searchCriteriaCell.setCellStyle(styles.get("cell")); rownum++; }
		 */

		for (int i = 0; i < columnMap.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		String name = reportName.replaceAll(" ", "");
		String fileName = name + getDateTime() + ".xls";
		File file = new File(fileName);
		FileOutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
		pushFileToDownload(fileName);
	}

	private void createPaymentSentCvsFile(List<BgMastDto> dtoList,
			Map<String, String> columnMap) throws Exception {
		String name = reportName.replaceAll(" ", "");
		String fileName = name + getDateTime() + ".csv";
		File file = new File(fileName);
		FileWriter writer = new FileWriter(file);
		if (columnMap.get("bgNumber") != null) {
			writer.append("Bg Number");
			writer.append(',');
		}
		if (columnMap.get("bgCreateType") != null) {
			writer.append("Bg Type");
			writer.append(',');
		}
		BgMastDto bgMastDto = dtoList.get(0);
		String direction = bgMastDto.getBgDirection();
		if (columnMap.get("advisingBank") != null) {
			if(direction.equals("O")){
				writer.append("Advising Bank");
				writer.append(',');
			}else if(direction.equals("I")){
				writer.append("Issuing Bank");
				writer.append(',');	
			}
		}
		if (columnMap.get("bgIssueDate") != null) {
			writer.append("Issue Date");
			writer.append(',');
		}
		if (columnMap.get("bgAmount") != null) {
			writer.append("Amount");
			writer.append(',');
		}
		if (columnMap.get("details") != null) {
			writer.append("Details");
			writer.append(',');
		}
		if (columnMap.get("bgStatus") != null) {
			writer.append("Status");
			writer.append(',');
		}
		if (columnMap.get("bgLastModifiedTime") != null) {
			writer.append("Last Modified Time");
			writer.append(',');
		}
		if (columnMap.get("msgStatus") != null) {
			writer.append("Msg Status");
			writer.append(',');
		}
		/*if (columnMap.get("noOfMsg") != null) {
			writer.append("No Of Message");
			writer.append(',');
		}
		if (columnMap.get("bgRuleCode") != null) {
			writer.append("Bg Rule Code");
			writer.append(',');
		}
		if (columnMap.get("bgRuleDesc") != null) {
			writer.append("Bg Rule Desc");
			writer.append(',');
		}
		if (columnMap.get("bgNarration") != null) {
			writer.append("Narration");
			writer.append(',');
		}
		if (columnMap.get("bgNoOfAmntmnt") != null) {
			writer.append("Bg No Of Amntmnt");
			writer.append(',');
		}
		if (columnMap.get("bgLastModifiedTime") != null) {
			writer.append("Bg Last Modified Time");
			writer.append(',');
		}*/
		writer.append('\n');

		for (BgMastDto dto : dtoList) {
			if (columnMap.get("bgNumber") != null) {
				writer.append(dto.getBgNumber());
				writer.append(',');
			}
			if (columnMap.get("bgCreateType") != null) {
				writer.append(dto.getBgCreateType() + "");
				writer.append(',');
			}
			if (columnMap.get("advisingBank") != null) {
				writer.append(dto.getAdvisingBank());
				writer.append(',');
			}
			if (columnMap.get("bgIssueDate") != null) {
				writer.append(dto.getBgIssueDate() + "");
				writer.append(',');
			}
			if (columnMap.get("bgAmount") != null) {
				if(dto.getBgAmount()!=null){
				writer.append(dto.getBgAmount().toString());
				}else{
					writer.append("0.00");	
				}
				writer.append(',');
			}
			if (columnMap.get("details") != null) {
				if(dto.getDetails()!=null){
					String updateDetail = dto.getDetails().replaceAll("\r\n", " ").replaceAll(",", "");
					writer.append(updateDetail);
				}else{
					writer.append(dto.getDetails());
				}
				writer.append(',');
			}
			if (columnMap.get("bgStatus") != null) {
				writer.append(dto.getBgStatus() + "");
				writer.append(',');
			}
			if (columnMap.get("bgLastModifiedTime") != null) {
				writer.append(dto.getBgLastModifiedTime() + "");
				writer.append(',');
			}
			if (columnMap.get("msgStatus") != null) {
				writer.append(dto.getMsgStatus() + "");
				writer.append(',');
			}
			
			/*if (columnMap.get("noOfMsg") != null) {
				writer.append(dto.getNoOfMsg() + "");
				writer.append(',');
			}
			if (columnMap.get("bgRuleCode") != null) {
				writer.append(dto.getBgRuleCode());
				writer.append(',');
			}
			if (columnMap.get("bgRuleDesc") != null) {
				writer.append(dto.getBgRuleDesc());
				writer.append(',');
			}
			if (columnMap.get("bgNarration") != null) {
				writer.append(dto.getBgNarration());
				writer.append(',');
			}
			if (columnMap.get("bgNoOfAmntmnt") != null) {
				writer.append(dto.getBgNoOfAmntmnt() + "");
				writer.append(',');
			}
			if (columnMap.get("bgLastModifiedTime") != null) {
				writer.append(dto.getBgLastModifiedTime() + "");
				writer.append(',');
			}*/
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
	private PdfPTable getSearchCriteriaTable() throws DocumentException {
		// Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9,
		// Font.BOLD);
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[] { 48, 4, 48 });
		table.setWidthPercentage(100);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setColspan(3);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
		Chunk header = new Chunk(reportName, FontFactory.getFont(
				FontFactory.HELVETICA, 10, Font.BOLD));
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
	private PdfPTable getReportTable(List<BgMastDto> dtoList,
			Map<String, String> columnMap) throws DocumentException {
		PdfPTable table = new PdfPTable(columnMap.size());
		/*
		 * table.setWidthPercentage(100); table.getDefaultCell().setPadding(2);
		 * table.getDefaultCell().setBorderWidth(0);
		 * table.getDefaultCell().setColspan(columnMap.size());
		 * table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		 * Chunk header = new Chunk("Results:",
		 * FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
		 * table.addCell(new Phrase(header)); table.addCell("\n");
		 */
		Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 6,Font.BOLD);
		table.getDefaultCell().setColspan(1);
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	//	table.getAbsoluteWidths();
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 5,Font.NORMAL);
		float [] widths ={12f,12f,10f,9f,10f,30f,11f,9f,14f};
		if(columnMap.size()== widths.length){
			table.setWidths(widths); 
		}
		table.setWidthPercentage(90); 
		
		if (columnMap.get("bgNumber") != null) {
			table.addCell(new Phrase("Bg Number", fontStyleHeaders));
		}
		if (columnMap.get("bgCreateType") != null) {
			table.addCell(new Phrase("Bg Type", fontStyleHeaders));
		}
		BgMastDto bgMastDto = dtoList.get(0);
		String direction = bgMastDto.getBgDirection();
		if (columnMap.get("advisingBank") != null) {
			if(direction.equals("O")){
				table.addCell(new Phrase("Advising Bank", fontStyleHeaders));
			}else if(direction.equals("I")){
				table.addCell(new Phrase("Issuing Bank", fontStyleHeaders));
			}
		}
		if (columnMap.get("bgIssueDate") != null) {
			table.addCell(new Phrase("Issue Date", fontStyleHeaders));
		}
		if (columnMap.get("bgAmount") != null) {
			table.addCell(new Phrase("Amount", fontStyleHeaders));
		}
		if (columnMap.get("details") != null) {
			table.addCell(new Phrase("Details", fontStyleHeaders));
		}
		if (columnMap.get("bgStatus") != null) {
			table.addCell(new Phrase("Status", fontStyleHeaders));
		}
		if (columnMap.get("bgLastModifiedTime") != null) {
			table.addCell(new Phrase("Last Modified Time", fontStyleHeaders));
		}
		if (columnMap.get("msgStatus") != null) {
			table.addCell(new Phrase("Msg Status", fontStyleHeaders));
		}
	/*	if (columnMap.get("noOfMsg") != null) {
			table.addCell(new Phrase("No Of Message", fontStyleHeaders));
		}
		if (columnMap.get("bgRuleCode") != null) {
			table.addCell(new Phrase("Bg Rule Code", fontStyleHeaders));
		}

		if (columnMap.get("bgRuleDesc") != null) {
			table.addCell(new Phrase("Bg Rule Desc", fontStyleHeaders));
		}
		if (columnMap.get("bgNarration") != null) {
			table.addCell(new Phrase("Narration", fontStyleHeaders));
		}
	
		if (columnMap.get("bgNoOfAmntmnt") != null) {
			table.addCell(new Phrase("Bg No Of Amntmnt", fontStyleHeaders));
		}
		if (columnMap.get("bgLastModifiedTime") != null) {
			table.addCell(new Phrase("bg Last Modified Time", fontStyleHeaders));
		}*/
		
		

		for (BgMastDto dto : dtoList) {
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			if (columnMap.get("bgNumber") != null) {
				table.addCell(new Phrase(dto.getBgNumber(), fontStyleCells));
			}
			if (columnMap.get("bgCreateType") != null) {
				table.addCell(new Phrase(dto.getBgCreateType() + "", fontStyleCells));
			}
			if (columnMap.get("advisingBank") != null) {
				table.addCell(new Phrase(dto.getAdvisingBank(), fontStyleCells));
			}
			if (columnMap.get("bgIssueDate") != null) {
				table.addCell(new Phrase(dto.getBgIssueDate() + "",
						fontStyleCells));
			}
			if (columnMap.get("bgAmount") != null) {
				if(dto.getBgAmount()!=null){
					table.addCell(new Phrase(dto.getBgAmount().toString(), fontStyleCells));
					}else{
						table.addCell(new Phrase("0.00", fontStyleCells));
					}
			}
			if (columnMap.get("details") != null) {
				table.addCell(new Phrase(dto.getDetails(), fontStyleCells));
			}
			if (columnMap.get("bgStatus") != null) {
				table.addCell(new Phrase(dto.getBgStatus() + "", fontStyleCells));
			}
			if (columnMap.get("bgLastModifiedTime") != null) {
				table.addCell(new Phrase(dto.getBgLastModifiedTime() + "",
						fontStyleCells));
			}
			if (columnMap.get("msgStatus") != null) {
				table.addCell(new Phrase(dto.getMsgStatus() + "", fontStyleCells));
			}
			/*if (columnMap.get("noOfMsg") != null) {
				table.addCell(new Phrase(dto.getNoOfMsg() + "", fontStyleCells));
			}
			if (columnMap.get("bgRuleCode") != null) {
				table.addCell(new Phrase(dto.getBgRuleCode(), fontStyleCells));
			}
			if (columnMap.get("bgRuleDesc") != null) {
				table.addCell(new Phrase(dto.getBgRuleDesc(), fontStyleCells));
			}
			if (columnMap.get("bgNarration") != null) {
				table.addCell(new Phrase(dto.getBgNarration(), fontStyleCells));
			}
			
			if (columnMap.get("bgNoOfAmntmnt") != null) {
				table.addCell(new Phrase(dto.getBgNoOfAmntmnt() + "",
						fontStyleCells));
			}
			if (columnMap.get("bgLastModifiedTime") != null) {
				table.addCell(new Phrase(dto.getBgLastModifiedTime() + "",
						fontStyleCells));
			}*/
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
			response.setHeader("Cache-Control",null);
			response.setHeader("Pragma",null);
			/*response.setHeader("Cache-Control","max-age=0");
			response.setHeader("Cache-Control","no-cache");
			 response.setContentLength(16384);
			response.setHeader("Pragma","public");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader("Expires", 0);*/
			FileInputStream fileIn = new FileInputStream(fileName);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] outputByte = new byte[16384];
	 	        while(fileIn.read(outputByte, 0, 16384) != -1){
	 	        	outStream.write(outputByte, 0, 16384);
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
