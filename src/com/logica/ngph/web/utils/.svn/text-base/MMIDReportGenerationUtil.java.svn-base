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

import com.logica.ngph.dtos.AccountDto;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class MMIDReportGenerationUtil extends PdfPageEventHelper{
	private HttpServletRequest servletRequest;
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	private  String reportName;
	public void generateMMIDReport(Map<String, List<AccountDto>> accNumVsAccDto,Map<String,String> columnMap,String reportType)throws Exception{
	//	List dtoList1 = rearrage(dtoList);
		if(reportType.equals("EXCEL")){
			createMMIDReportExcelFile(accNumVsAccDto, columnMap);
		}else if(reportType.equals("PDF")){
			createMMIDReportPdfFile(accNumVsAccDto, columnMap);
		}else if(reportType.equals("CSV")){
			createMMIDReportCvsFile(accNumVsAccDto, columnMap);
		}
	}
	
	/*List<AccountDto> rearrage (List<AccountDto> dtoList){
		
	}
	*/
	
private void createMMIDReportPdfFile(Map<String, List<AccountDto>> accNumVsAccDto,Map<String,String> columnMap)throws Exception{
		
		Document document = new Document(PageSize.A4, 30, 10, 80, 80);
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
			document.add(getReportTable(accNumVsAccDto,columnMap));
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
	private void createMMIDReportExcelFile(Map<String, List<AccountDto>> accNumVsAccDto,Map<String,String> columnMap)throws Exception{
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
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
	    
	    int rowcount = 1;
	    
	    for (String accountNumber : accNumVsAccDto.keySet()) {
	    
	    	//header row
	    Row headerRow = sheet.createRow(rowcount);
	   // columnMap.entrySet();
	    int headerCellNumber =0;	    
	    	Cell headerCell = headerRow.createCell(headerCellNumber);	    	
	    	headerCell.setCellValue("Account Number");
	    	headerCell.setCellStyle(styles.get("header"));	
	    	headerCellNumber++;
		
	  //  if(columnMap.get("accountName")!=null){	
	    	Cell headerCell1 = headerRow.createCell(headerCellNumber);	
	    	headerCell1.setCellValue(accountNumber);
	    	headerCell1.setCellStyle(styles.get("cell"));
	    	 headerCellNumber++;
		//} 	
	    	List<AccountDto> accDtoSubList1 = accNumVsAccDto.get(accountNumber);
			String  accountName = "";
			for (AccountDto accountDto : accDtoSubList1) { 
			
				accountName = accountDto.getAccountName();
			}
			Cell headerCell2 = headerRow.createCell(headerCellNumber);	
			headerCell2.setCellValue(accountName);
			headerCell2.setCellStyle(styles.get("cell"));
			int headerCellNumber1=0;
			 rowcount++;
			 Row headerRow1 = sheet.createRow(rowcount);
	    	  Cell headerCell3 = headerRow1.createCell(headerCellNumber1);	
	    	  headerCell3.setCellValue("MMID :");
	    	  headerCell3.setCellStyle(styles.get("header"));
	    	  headerCellNumber1++;
	    	  Cell headerCell4 = headerRow1.createCell(headerCellNumber1);	
	    	  headerCell4.setCellValue("Mobile Number :");
	    	  headerCell4.setCellStyle(styles.get("header"));
	    	  headerCellNumber1++;
	    	  Cell headerCell5 = headerRow1.createCell(headerCellNumber1);	
	    	  headerCell5.setCellValue("Is Default :");
	    	  headerCell5.setCellStyle(styles.get("header"));  	  
	    	  	    	  
	    	  rowcount++;
	    	  int innerRow = rowcount;
	    	
	    	List<AccountDto> accDtoSubList = accNumVsAccDto.get(accountNumber);
			for (AccountDto accountDto : accDtoSubList) { 
				Row row = sheet.createRow(innerRow);
		        int cellNumber = 0;		      
		        	/*Cell datatCell = row.createCell(cellNumber);
		        	datatCell.setCellValue("MMID :");
		        	datatCell.setCellStyle(styles.get("header"));
		        	cellNumber++;*/
		        	Cell datatCell1 = row.createCell(cellNumber);
		        	datatCell1.setCellValue(accountDto.getMMID());
		        	datatCell1.setCellStyle(styles.get("cell"));
			    	cellNumber++;
			    	/*Cell datatCell2 = row.createCell(cellNumber);
			    	datatCell2.setCellValue("Mobile Number :");
			    	datatCell2.setCellStyle(styles.get("header"));
		        	cellNumber++;*/
		        	Cell datatCell2 = row.createCell(cellNumber);
		        	datatCell2.setCellValue(accountDto.getMobileNo());
		        	datatCell2.setCellStyle(styles.get("cell"));
			    	cellNumber++;
			    	
			    	Cell datatCell3 = row.createCell(cellNumber);
		        	datatCell3.setCellValue(accountDto.getIsDefault());
		        	datatCell3.setCellStyle(styles.get("cell"));
			    	cellNumber++;
			    	
			    	innerRow++;
			
			}
			rowcount = innerRow;
			rowcount++;
			
	    }
	    	
		/*}
		if(columnMap.get("mobileNo")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
	    	headerCell.setCellValue("Mobile Numeber");
	    	headerCell.setCellStyle(styles.get("header"));
	    	 headerCellNumber++;
		}
		
	    int rownum = 2;
	    for (AccountDto dto : dtoList) {
	        Row row = sheet.createRow(rownum);
	        int cellNumber = 0;
	        if(columnMap.get("accoutNo")!=null){
	        	Cell datatCell = row.createCell(cellNumber);
	        	datatCell.setCellValue(dto.getAccoutNo());
	        	datatCell.setCellStyle(styles.get("cell"));
	        	cellNumber++;
			}
			if(columnMap.get("accountName")!=null){
				Cell datatCell = row.createCell(cellNumber);
	        	datatCell.setCellValue(dto.getAccountName());
	        	datatCell.setCellStyle(styles.get("dateCell"));
	        	cellNumber++;
			}
			if(columnMap.get("MMID")!=null){
				Cell datatCell = row.createCell(cellNumber);
	        	datatCell.setCellValue(dto.getMMID());
	        	datatCell.setCellStyle(styles.get("cell"));
	        	cellNumber++;
			}
			if(columnMap.get("mobileNo")!=null){
				Cell datatCell = row.createCell(cellNumber);
	        	datatCell.setCellValue(dto.getMobileNo());
	        	datatCell.setCellStyle(styles.get("cell"));
	        	cellNumber++;
			}
			
	        rownum++;
	    }
	
	*/
	    
	    for (int i = 0; i < 3; i++) {
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

	private void createMMIDReportCvsFile(Map<String, List<AccountDto>> accNumVsAccDto,Map<String,String> columnMap)throws Exception{
		String name = reportName.replaceAll(" ", "");
		String fileName = name+getDateTime()+".csv";
		File file = new File(fileName);
		FileWriter writer = new FileWriter(file);
		
		
		
		 for (String accountNumber : accNumVsAccDto.keySet()) {
		//if(columnMap.get("accoutNo")!=null){
			writer.append("Account Number :");
		    writer.append(',');
		//}
	    //if(columnMap.get("accountName")!=null){
	    	writer.append(accountNumber);
	    	writer.append(',');
		//}
	    	List<AccountDto> accDtoSubList1 = accNumVsAccDto.get(accountNumber);
			String  accountName = "";
			for (AccountDto accountDto : accDtoSubList1) { 
			
				accountName = accountDto.getAccountName();
			}
			
		//if(columnMap.get("MMID")!=null){
	    	writer.append(accountName);
	    	 writer.append(',');
	    	// writer.append(" ");
	    	// writer.append(',');
	//	}
		/*if(columnMap.get("mobileNo")!=null){
	    	writer.append("Mobile Numeber");
	    	 writer.append(',');
		}*/
		
		
		writer.append('\n');
		writer.append("MMID :");
		writer.append(',');
		writer.append("Mobile Number :");
    	writer.append(',');
    	writer.append("Is Default :");
    	writer.append(',');
    	writer.append('\n');
		
		List<AccountDto> accDtoSubList = accNumVsAccDto.get(accountNumber);
		for (AccountDto accountDto : accDtoSubList) {
			
				//writer.append("MMID :");
	        	//writer.append(',');
			
	        	writer.append(accountDto.getMMID());
	        	writer.append(',');
			
	        //	writer.append("Mobile Number :");
	        //	writer.append(',');
			
	        	writer.append(accountDto.getMobileNo());
	        	writer.append(',');
	        	
	        	writer.append(accountDto.getIsDefault());
	        	writer.append(',');
	        	writer.append('\n');
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
		Chunk header = new Chunk(reportName, FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD));
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
	private PdfPTable getReportTable(Map<String, List<AccountDto>> accNumVsAccDto, Map<String,String> columnMap) throws DocumentException {
		
		PdfPTable parentTable = new PdfPTable(1);	
		
		for (String accountNumber : accNumVsAccDto.keySet()) {
			
		
		
		PdfPTable table = new PdfPTable(3);	
		/*table.setWidthPercentage(100); 
		table.getDefaultCell().setPadding(2);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setColspan(columnMap.size());
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk header = new Chunk("Results:", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
		table.addCell(new Phrase(header));
		table.addCell("\n");*/
		Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.5f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT); 
		
		
		
		
		table.addCell(new Phrase("Account Number: ", fontStyleHeaders));
		table.addCell(new Phrase(accountNumber+" ", fontStyleCells));
		List<AccountDto> accDtoSubList1 = accNumVsAccDto.get(accountNumber);
		String  accountName = "";
		for (AccountDto accountDto : accDtoSubList1) { 
		
			accountName = accountDto.getAccountName();
		}
		
		// PdfPCell cell;
	     //   cell = new PdfPCell(new Phrase(accountName,fontStyleCells));
	      //  cell.setBorderWidth(0.0f);
	       // cell.setColspan(2);
	        table.addCell(new Phrase(accountName,fontStyleCells));
	        table.addCell(new Phrase("MMID : ", fontStyleHeaders));
	        table.addCell(new Phrase("Mobile Number : ", fontStyleHeaders));
	        table.addCell(new Phrase("Is Default : ", fontStyleHeaders));
	        
		
		
			List<AccountDto> accDtoSubList = accNumVsAccDto.get(accountNumber);
			for (AccountDto accountDto : accDtoSubList) { 
				//table.addCell(new Phrase("MMID : ", fontStyleHeaders));
					table.addCell(new Phrase(accountDto.getMMID()+"", fontStyleCells));		
					//table.addCell(new Phrase("Mobile Number : ", fontStyleHeaders));
					table.addCell(new Phrase(accountDto.getMobileNo()+"", fontStyleCells));
					table.addCell(new Phrase(accountDto.getIsDefault()+"", fontStyleCells));
			
			}
			parentTable.addCell(table);
		}
		return parentTable;
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
			/*response.setHeader("Cache-Control","max-age=0");
			response.setHeader("Cache-Control","no-cache");
			response.setContentLength(4096);
			response.setHeader("Pragma","public");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader("Expires", 0);*/
		    response.setHeader("Content-Disposition","attachment;filename="+fileName);
		    response.setHeader("Cache-Control",null);
			response.setHeader("Pragma",null);
		        FileInputStream fileIn = new FileInputStream(fileName);
		        ServletOutputStream outStream = response.getOutputStream();
		        byte[] outputByte = new byte[4096];
		        while(fileIn.read(outputByte, 0, 4096) != -1){
		        	outStream.write(outputByte, 0, 4096);
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

	

}
