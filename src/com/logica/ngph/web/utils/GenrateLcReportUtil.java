
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


import com.logica.ngph.dtos.LcMastDto;
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

public class GenrateLcReportUtil extends PdfPageEventHelper{
	private HttpServletRequest servletRequest;
	private  String reportName;
	public void generatePaymentSentReport(List<LcMastDto> dtoList,Map<String,String> columnMap,String reportType)throws Exception{
		if(reportType.equals("EXCEL")){
			createPaymentSentExcelFile(dtoList, columnMap);
		}else if(reportType.equals("PDF")){
			 createPaymentSentPdfFile(dtoList, columnMap);
		}else if(reportType.equals("CSV")){
			 createPaymentSentCvsFile(dtoList, columnMap);
		}
	}
	
	private void createPaymentSentPdfFile(List<LcMastDto> dtoList,Map<String,String> columnMap)throws Exception{
		
		Document document = new Document(PageSize.A4,2,2,70,70);
		String name = reportName.replaceAll(" ", "");
		String fileName = name+getDateTime()+".pdf";
		File file = new File(fileName);
		FileOutputStream outStream = new FileOutputStream(file);
		document.setMarginMirroring(true);
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document,	outStream);
			writer.setPdfVersion(PdfWriter.VERSION_1_3);
			// to set header & footer on each page
			writer.setPageEvent(this);
			document.open();
			// to create Search criteria
			document.add(getSearchCriteriaTable());
			// to  create report data
			document.add(getReportTable(dtoList,columnMap));
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
	
	
	private  final static String getDateTime()  
    {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hhmmss");  
        df.setTimeZone(TimeZone.getTimeZone("GMT"));  
        return df.format(new Date());  
    }  
	
	private void createPaymentSentExcelFile(List<LcMastDto> dtoList,Map<String,String> columnMap)throws Exception{
		Workbook wb  = new HSSFWorkbook(); 
		Map<String, CellStyle> styles = createStyles(wb);
		
        Sheet sheet = wb.createSheet(reportName);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(30);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(reportName);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnMap.size()));

        //header row
        Row headerRow = sheet.createRow(1);
        columnMap.entrySet();
        int headerCellNumber =0;
        if(columnMap.get("lcNo")!=null){
        	Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Number");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
        if(columnMap.get("lcType")!=null){
        	Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Type");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
        LcMastDto lcMastDto = dtoList.get(0);
		String direction = lcMastDto.getLcDirection();
		if(columnMap.get("lcAdvisingBank")!=null){
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
		if(columnMap.get("lcAppicant")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Appicant");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lcBenificiary")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Benificiary");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lcIssueDate")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Issue Date");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lcCurrency")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Ccy");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lcAmount")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Amount");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lcExpireDate")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Expiry Date");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lcNarrative")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Narrative");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("status")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Status");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("lstModifiedTime")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Last Modified Time");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("msgStatus")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Msg Status");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		
        int rownum = 2;
        for (LcMastDto dto : dtoList) {
            Row row = sheet.createRow(rownum);
            int cellNumber = 0;
            if(columnMap.get("lcNo")!=null){
            	Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcNo());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcType")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcType());
            	datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
			}
			if(columnMap.get("lcAdvisingBank")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcAdvisingBank());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcAppicant")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcAppicant());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcBenificiary")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcBenificiary());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcIssueDate")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcIssueDate()+"");
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcCurrency")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcCurrency());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcAmount")!=null){
				Cell datatCell = row.createCell(cellNumber);
				if(dto.getLcAmount()!=null){
	            	datatCell.setCellValue(Double.parseDouble(dto.getLcAmount().toString()));
					}else{
						datatCell.setCellValue(Double.parseDouble("0.00"));
					}
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcExpireDate")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcExpireDate()+"");
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lcNarrative")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLcNarrative());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("status")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getStatus());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("lstModifiedTime")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLstModifiedTime());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
			if(columnMap.get("msgStatus")!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getMsgStatus());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
			}
            rownum++;
        }

      /*  rownum = rownum+3;
        Row row =null;
        row = sheet.createRow(rownum);
        Cell searchCriteriaHeaderCell =  row.createCell(0);
        row.setHeightInPoints(20);
        searchCriteriaHeaderCell.setCellValue("Search Criteria");
        searchCriteriaHeaderCell.setCellStyle(styles.get("subtitle"));
        sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 20));
        rownum++;
        
        for(int i=0; i<7; i++){
	        row = sheet.createRow(rownum);
	        Cell searchCriteriaCell =  row.createCell(0);
	        searchCriteriaCell.setCellValue("Name : value");
	        searchCriteriaCell.setCellStyle(styles.get("cell"));
	        rownum++;
        }*/
        
        for (int i = 0; i < columnMap.size(); i++) {
            sheet.autoSizeColumn(i); 
        }

        // Write the output to a file
        String name = reportName.replaceAll(" ", "");
        String fileName = name+getDateTime()+".xls";
        File file = new File(fileName);
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        pushFileToDownload(fileName);
	}
	
	private void createPaymentSentCvsFile(List<LcMastDto> dtoList,Map<String,String> columnMap)throws Exception{
		String name = reportName.replaceAll(" ", "");
		String fileName = name+getDateTime()+".csv";
		File file = new File(fileName);
		FileWriter writer = new FileWriter(file);
		if(columnMap.get("lcNo")!=null){
			writer.append("Number");
		    writer.append(',');
		}
        if(columnMap.get("lcType")!=null){
        	writer.append("Type");
        	writer.append(',');
		}
    	LcMastDto lcMastDto = dtoList.get(0);
		String direction = lcMastDto.getLcDirection();
		if(columnMap.get("lcAdvisingBank")!=null){
			if(direction.equals("O")){
				writer.append("Advising Bank");
	        	 writer.append(',');
			}else if(direction.equals("I")){
				writer.append("Issuing Bank");
	        	 writer.append(',');
			}
		}
		if(columnMap.get("lcAppicant")!=null){
        	writer.append("Appicant");
        	 writer.append(',');
		}
		if(columnMap.get("lcBenificiary")!=null){
        	writer.append("Benificiary");
        	 writer.append(',');
		}
		if(columnMap.get("lcIssueDate")!=null){
        	writer.append("Issue Date");
        	 writer.append(',');
		}
		if(columnMap.get("lcCurrency")!=null){
        	writer.append("Ccy");
        	 writer.append(',');
		}
		if(columnMap.get("lcAmount")!=null){
			writer.append("Amount");
			writer.append(',');
		}
		if(columnMap.get("lcExpireDate")!=null){
        	writer.append("Expiry Date");
        	 writer.append(',');
		}
		if(columnMap.get("lcNarrative")!=null){
        	writer.append("Narrative");
        	 writer.append(',');
		}
		if(columnMap.get("status")!=null){
        	writer.append("Status");
        	 writer.append(',');
		}
		
		if(columnMap.get("lstModifiedTime")!=null){
        	writer.append("Last Modified Time");
        	 writer.append(',');
		}
		if(columnMap.get("msgStatus")!=null){
        	writer.append("Msg Status");
        	 writer.append(',');
		}
		writer.append('\n');
		 for (LcMastDto dto : dtoList) {
            if(columnMap.get("lcNo")!=null){
            	writer.append(dto.getLcNo());
            	writer.append(',');
			}
			if(columnMap.get("lcType")!=null){
            	writer.append(dto.getLcType());
            	writer.append(',');
			}
			if(columnMap.get("lcAdvisingBank")!=null){
            	writer.append(dto.getLcAdvisingBank());
            	writer.append(',');
			}
			if(columnMap.get("lcAppicant")!=null){
				if(dto.getLcAppicant()!=null){
					String updateApppicant = dto.getLcAppicant().replaceAll("\r\n", " ").replaceAll(",", "");
					writer.append(updateApppicant);
				}else{
					writer.append(dto.getLcAppicant());
				}
            	writer.append(',');
			}
			if(columnMap.get("lcBenificiary")!=null){
				if(dto.getLcBenificiary()!=null){
					String updateBenify = dto.getLcBenificiary().replaceAll("\r\n", " ").replaceAll(",", "");
					writer.append(updateBenify);
			}else{
				writer.append(dto.getLcBenificiary());
			}
				
            	writer.append(',');
			}
			if(columnMap.get("lcIssueDate")!=null){
            	writer.append(dto.getLcIssueDate()+"");
            	writer.append(',');
			}
			if(columnMap.get("lcCurrency")!=null){
            	writer.append(dto.getLcCurrency());
            	writer.append(',');
			}
			if(columnMap.get("lcAmount")!=null){
				if(dto.getLcAmount()!=null){
            	writer.append(dto.getLcAmount().toString());
				}else{
					writer.append("0.00");
				}
            	writer.append(',');
			}
			if(columnMap.get("lcExpireDate")!=null){
            	writer.append(dto.getLcExpireDate()+"");
            	writer.append(',');
			}
			if(columnMap.get("lcNarrative")!=null){
				if(dto.getLcNarrative()!=null){
					String updatedNarrative = dto.getLcNarrative().replaceAll("\r\n", " ").replaceAll(",", "");
					writer.append(updatedNarrative);
				}else{
					writer.append(dto.getLcNarrative());
				}
            	writer.append(',');
			}
			if(columnMap.get("status")!=null){
            	writer.append(dto.getStatus()+"");
            	writer.append(',');
			}
			if(columnMap.get("lstModifiedTime")!=null){
            	writer.append(dto.getLstModifiedTime()+"");
            	writer.append(',');
			}
			if(columnMap.get("msgStatus")!=null){
            	writer.append(dto.getMsgStatus()+"");
            	writer.append(',');
			}
			writer.append('\n');
        }
		writer.flush();
	    writer.close();
	    pushFileToDownload(fileName); 
}
	
	
	
	/**
	 *  This method will be called on each page creation to set header and footer
	 * @param writer
	 * @param document
	 */
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Rectangle page = document.getPageSize();
			//will print the header using the code below
			PdfPTable headTable = getHeader(writer);
			//total width of the table is set
			float width = page.width();
			float leftMargin = document.leftMargin();
			float rightMargin = document.rightMargin();
			float total = width-leftMargin-rightMargin;
			headTable.setTotalWidth(total);
			//the table is printed at the specified location
			headTable.writeSelectedRows(0, -1, document.leftMargin(), page.height(), writer.getDirectContent());
		
			//will print the footer using the code below
			PdfPTable footTable = getFooterSignatures();
			float width1 = page.width();
			float leftMargin1 = document.leftMargin();
			float rightMargin1 = document.rightMargin();
			float total1 = width1-leftMargin1-rightMargin1;
			footTable.setTotalWidth(total1);
			footTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin()-50,     writer.getDirectContent());
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * To generate Header in the PDF
	 * @param writer
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable getHeader(PdfWriter writer) throws DocumentException {
		PdfPTable headTable = new PdfPTable(1);
		headTable.getDefaultCell().setPadding(2); 
		headTable.getDefaultCell().setBorderWidth(0);
		headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
		headTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		headTable.addCell(new Phrase("Page No :"+Integer.toString(writer.getPageNumber()),fontStyleCells)); 
		headTable.addCell("\n");
		try{
			headTable.getDefaultCell().setFixedHeight(20);
			headTable.getDefaultCell().setBackgroundColor(Color.getHSBColor(199, 15, 100));
			headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			/*String destinationPath = servletRequest.getSession().getServletContext().getRealPath("/");
			Image png = Image.getInstance(destinationPath+"images/logo.png"); // to do
			headTable.addCell(png); */
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return headTable;
	}
	
	/**
	 * To generate Footer in the PDF
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable getFooterSignatures() throws DocumentException {
		Font fontStyleFooters = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		PdfPTable footTable = new PdfPTable(2);
		footTable.setWidths(new int[]{80, 20});
		footTable.setWidthPercentage(100);
		footTable.getDefaultCell().setPadding(2);
		footTable.getDefaultCell().setBorderWidth(0);
		footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		footTable.addCell(new Phrase("@Copyright 2012 QNG. All rights reserved.", fontStyleFooters)); 
		try{
			footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footTable.getDefaultCell().setFixedHeight(20);
			/*String destinationPath = servletRequest.getSession().getServletContext().getRealPath("/");
			Image png = Image.getInstance(destinationPath+"images/poweredbyLogica.jpg"); //to do
			footTable.addCell(png); */
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return footTable;
		}
	
	/*
	 * To generate Search Criteria in the PDF
	 */
	private PdfPTable getSearchCriteriaTable() throws DocumentException {
	//	Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[]{48,4,48});
		table.setWidthPercentage(100); 
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setColspan(3);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		Chunk header = new Chunk(reportName, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
		header.setUnderline(0.2f, -5f);
		table.addCell(new Phrase(header));
		/*table.addCell("\n");
		table.getDefaultCell().setColspan(1);
		table.getDefaultCell().setPadding(2);
		for(int i=0; i<10; i++){
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(new Phrase("Name",fontStyleHeaders)); //to do
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(new Phrase("=",fontStyleHeaders));
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Value",fontStyleHeaders)); // to do
		}*/
		table.getDefaultCell().setColspan(3);
		table.addCell("\n");
		table.addCell("\n");
		return table;
	}
	
	/**
	 * To generate Search Criteria in the PDF
	 * @param dtoList
	 * @param columnMap
	 * @return
	 * @throws DocumentException
	 */
	private PdfPTable getReportTable(List<LcMastDto> dtoList, Map<String,String> columnMap) throws DocumentException {
		PdfPTable table = new PdfPTable(columnMap.size());
		/*table.setWidthPercentage(100); 
		table.getDefaultCell().setPadding(2);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setColspan(columnMap.size());
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk header = new Chunk("Results:", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
		table.addCell(new Phrase(header));
		table.addCell("\n");*/
		Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL);
		float [] widths ={8f,8.5f,8f,9f,9f,6f,3f,7f,6f,11.5f,10f,6f,10f};
		if(columnMap.size()== widths.length){
			table.setWidths(widths); 
		}
		table.setWidthPercentage(90); 
		if(columnMap.get("lcNo")!=null){
			table.addCell(new Phrase("Number", fontStyleHeaders));
		}
		if(columnMap.get("lcType")!=null){
			table.addCell(new Phrase("Type", fontStyleHeaders));
		}
		LcMastDto lcMastDto = dtoList.get(0);
		String direction = lcMastDto.getLcDirection();
		if(columnMap.get("lcAdvisingBank")!=null){
			if(direction.equals("O")){
				table.addCell(new Phrase("Advising Bank", fontStyleHeaders));
			}else if(direction.equals("I")){
				table.addCell(new Phrase("Issuing Bank", fontStyleHeaders));
			}
		}
		if(columnMap.get("lcAppicant")!=null){
			table.addCell(new Phrase("Appicant", fontStyleHeaders));
		}
		if(columnMap.get("lcBenificiary")!=null){
			table.addCell(new Phrase("Benificiary", fontStyleHeaders));
		}
		if(columnMap.get("lcIssueDate")!=null){
			table.addCell(new Phrase("Issue Date", fontStyleHeaders));
		}
		if(columnMap.get("lcCurrency")!=null){
			table.addCell(new Phrase("Ccy", fontStyleHeaders));
		}
		if(columnMap.get("lcAmount")!=null){
			table.addCell(new Phrase("Amount", fontStyleHeaders));
		}
		
		if(columnMap.get("lcExpireDate")!=null){
			table.addCell(new Phrase("Expiry Date", fontStyleHeaders));
		}
		if(columnMap.get("lcNarrative")!=null){
			table.addCell(new Phrase("Narrative", fontStyleHeaders));
		}
		if(columnMap.get("status")!=null){
			table.addCell(new Phrase("Status", fontStyleHeaders));
		}
		if(columnMap.get("lstModifiedTime")!=null){
			table.addCell(new Phrase("Last Modified Time", fontStyleHeaders));
		}
		if(columnMap.get("msgStatus")!=null){
			table.addCell(new Phrase("Msg Status", fontStyleHeaders));
		}
		for (LcMastDto dto : dtoList ) { 
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT); 
			if(columnMap.get("lcNo")!=null){
				table.addCell(new Phrase(dto.getLcNo(), fontStyleCells));
			}
			if(columnMap.get("lcType")!=null){
				table.addCell(new Phrase(dto.getLcType(), fontStyleCells));
			}
			if(columnMap.get("lcAdvisingBank")!=null){
				table.addCell(new Phrase(dto.getLcAdvisingBank(), fontStyleCells));
			}
			if(columnMap.get("lcAppicant")!=null){
				table.addCell(new Phrase(dto.getLcAppicant(), fontStyleCells));
			}
			if(columnMap.get("lcBenificiary")!=null){
				table.addCell(new Phrase(dto.getLcBenificiary(), fontStyleCells));
			}
			if(columnMap.get("lcIssueDate")!=null){
				table.addCell(new Phrase(dto.getLcIssueDate()+"", fontStyleCells));
			}
			if(columnMap.get("lcCurrency")!=null){
				table.addCell(new Phrase(dto.getLcCurrency(), fontStyleCells));
			}
			if(columnMap.get("lcAmount")!=null){
				
				if(dto.getLcAmount()!=null){
					table.addCell(new Phrase(dto.getLcAmount().toString(), fontStyleCells));
					}else{
						table.addCell(new Phrase("0.00", fontStyleCells));
					}
			}
			if(columnMap.get("lcExpireDate")!=null){
				table.addCell(new Phrase(dto.getLcExpireDate()+"", fontStyleCells));
			}
			if(columnMap.get("lcNarrative")!=null){
				table.addCell(new Phrase(dto.getLcNarrative(), fontStyleCells));
			}
			if(columnMap.get("status")!=null){
				table.addCell(new Phrase(dto.getStatus()+"", fontStyleCells));
			}
			if(columnMap.get("lstModifiedTime")!=null){
				table.addCell(new Phrase(dto.getLstModifiedTime()+"", fontStyleCells));
			}
			if(columnMap.get("msgStatus")!=null){
				table.addCell(new Phrase(dto.getMsgStatus()+"", fontStyleCells));
			}
		}
		return table;
		}
	
	/**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        CreationHelper createHelper = wb.getCreationHelper();
        DataFormat format = wb.createDataFormat();
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        org.apache.poi.ss.usermodel.Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);
        
        org.apache.poi.ss.usermodel.Font subtitleFont = wb.createFont();
        subtitleFont.setFontHeightInPoints((short)14);
        subtitleFont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(subtitleFont);
        styles.put("subtitle", style);

        org.apache.poi.ss.usermodel.Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
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
        style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
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
    
    private void pushFileToDownload(String fileName) throws Exception{
    	HttpServletResponse response = ServletActionContext.getResponse();
    	 try {
    		 File file = new File(fileName);
    		response.setContentType("application/octet-stream");
    	    response.setHeader("Content-Disposition","attachment;filename="+fileName);
    	   /* response.setContentLength(16384);
    	    response.setHeader("Cache-Control","max-age=0");
    	    response.setHeader("Cache-Control","no-cache");
    	    response.setHeader("Pragma","public");
    	    response.setHeader("Pragma","no-cache");
    	    response.setDateHeader("Expires", 0);    */	   
    	    response.setHeader("Cache-Control",null);
			response.setHeader("Pragma",null);
    	    FileInputStream fileIn = new FileInputStream(fileName);
 	        ServletOutputStream outStream = response.getOutputStream();
 	        byte[] outputByte = new byte[16384];
 	        while(fileIn.read(outputByte, 0, 16384) != -1){
 	        	outStream.write(outputByte, 0, 16384);
 	        }
 	        fileIn.close();
 	        outStream.flush();
 	        outStream.close();
 	        if(file.delete()){
 	        	System.out.println("File Deleted... ");
 	        }
 		}catch(Exception e){
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
