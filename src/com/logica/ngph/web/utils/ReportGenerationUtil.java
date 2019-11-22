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

import com.logica.ngph.dtos.ReportDto;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class ReportGenerationUtil extends PdfPageEventHelper{
	private HttpServletRequest servletRequest;
	private  String reportName;
	public void generatePaymentSentReport(List<ReportDto> dtoList,Map<String,String> columnMap,String reportType)throws Exception{
		if(reportType.equals("EXCEL")){
			createPaymentSentExcelFile(dtoList, columnMap);
		}else if(reportType.equals("PDF")){
			 createPaymentSentPdfFile(dtoList, columnMap);
		}else if(reportType.equals("CSV")){
			 createPaymentSentCvsFile(dtoList, columnMap);
		}
	}
	
	private void createPaymentSentPdfFile(List<ReportDto> dtoList,Map<String,String> columnMap)throws Exception{
		
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");  
        df.setTimeZone(TimeZone.getTimeZone("IST"));  
        return df.format(new Date());  
    }  
	
	private void createPaymentSentExcelFile(List<ReportDto> dtoList,Map<String,String> columnMap)throws Exception{
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
        if(columnMap.get("msgRef")!=null){
        	Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Message Senders Reference  [system generated]");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
        if(columnMap.get("msgType")!=null){
        	Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Msg Type");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
        if(columnMap.get("branch")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Branch");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
        if(columnMap.get("senderBank")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Sender IFSC");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
        if(columnMap.get("receiverBank")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Receiver IFSC");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		} 
		if(columnMap.get("status")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Status");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("txnReference")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Senders Reference");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("dateofIssue")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Date of Issue");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("applicent")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Applicant");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("beneficiary")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Beneficiary");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("applicentBank")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Applicant Bank");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("currency")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Currency");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("amount")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Amount");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		
		if(columnMap.get("dateofExpiry")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Date of Expiry");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("dateofShipment")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Date of Shipment");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("dateofAmendment")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Date of Amendment");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("newDateofExpiry")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("New Date of Expiry");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("newDateofShipment")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("New Date of Shipment");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		
		if(columnMap.get("presentBankRef")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
			headerCell.setCellValue("Presenting Banks Ref");
			headerCell.setCellStyle(styles.get("header"));
			headerCellNumber++;
		}
		if(columnMap.get("noofAmendment")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("No of Amendment");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		if(columnMap.get("dateofReduction")!=null){
			Cell headerCell = headerRow.createCell(headerCellNumber);
        	headerCell.setCellValue("Date of Reduction");
        	headerCell.setCellStyle(styles.get("header"));
        	 headerCellNumber++;
		}
		 if(columnMap.get("amountReduced")!=null){
	        	Cell headerCell = headerRow.createCell(headerCellNumber);
	        	headerCell.setCellValue("Amount Reduced");
	        	headerCell.setCellStyle(styles.get("header"));
	        	 headerCellNumber++;
		}
		 if(columnMap.get("amountEnhanced")!=null){
	        	Cell headerCell = headerRow.createCell(headerCellNumber);
	        	headerCell.setCellValue("Amount Enhanced");
	        	headerCell.setCellStyle(styles.get("header"));
	        	 headerCellNumber++;
		}
		 if(columnMap.get("amountofOutstanding")!=null){
	        	Cell headerCell = headerRow.createCell(headerCellNumber);
	        	headerCell.setCellValue("Amount Outstanding");
	        	headerCell.setCellStyle(styles.get("header"));
	        	 headerCellNumber++;
		}

        int rownum = 2;
        for (ReportDto dto : dtoList) {
            Row row = sheet.createRow(rownum);
            int cellNumber = 0;
            if(columnMap.get("msgRef")!=null){
            	if(dto.getMsgRef()!=null){
	            	Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getMsgRef());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
            if(columnMap.get("msgType")!=null){
            	if(dto.getMsgType()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getMsgType());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}        
            if(columnMap.get("branch")!=null){
            	if(dto.getBranch()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getBranch());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
           
            if(columnMap.get("senderBank")!=null){
            	if(dto.getSenderBank()!=null){
					Cell datatCell = row.createCell(cellNumber);
					datatCell.setCellValue(dto.getSenderBank());
					datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("receiverBank")!=null){
				if(dto.getReceiverBank()!=null){
					Cell datatCell = row.createCell(cellNumber);
					datatCell.setCellValue(dto.getReceiverBank());
					datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("status")!=null){
				if(dto.getPaymentStatus()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getPaymentStatus());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("txnReference")!=null){
				if(dto.getTxnReference()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getTxnReference());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("dateofIssue")!=null){
				if(dto.getIssueDate()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getIssueDate());
	            	datatCell.setCellStyle(styles.get("dateCell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("applicent")!=null){
				if(dto.getOrderingCustomer()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getOrderingCustomer());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("beneficiary")!=null){
				if(dto.getBeneficiaryCustomer()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getBeneficiaryCustomer());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("applicentBank")!=null){
				if(dto.getApplicentBank()!=null){
					Cell datatCell = row.createCell(cellNumber);
	            	datatCell.setCellValue(dto.getApplicentBank());
	            	datatCell.setCellStyle(styles.get("cell"));
	            	cellNumber++;
            	}
			}
			if(columnMap.get("currency")!=null){
				if(dto.getMsgCurrency()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getMsgCurrency());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
				}
			}
			if(columnMap.get("amount")!=null){
				Cell datatCell = row.createCell(cellNumber);
				if(dto.getMsgAmount()!=null){
            	datatCell.setCellValue(Double.parseDouble(dto.getMsgAmount().toString()));
				}else{
					datatCell.setCellValue(Double.parseDouble("0.00"));
				}
            	datatCell.setCellStyle(styles.get("numberCell"));
            	cellNumber++;
			}
			if(columnMap.get("dateofExpiry")!=null){
				if(dto.getExpDate()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getExpDate());
            	datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
				}
			}
			if(columnMap.get("dateofShipment")!=null){
				if(dto.getLastDateofShipment()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLastDateofShipment());
            	datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
				}
			}
			
			if(columnMap.get("dateofAmendment")!=null){
				if(dto.getAmendmentDate()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getAmendmentDate());
            	datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
				}
			}
			if(columnMap.get("newDateofExpiry")!=null){
				if(dto.getExpDate()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getExpDate());
            	datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
				}
			}
			
			if(columnMap.get("newDateofShipment")!=null){
				if(dto.getLastDateofShipment()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getLastDateofShipment());
            	datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
				}
			}
			if(columnMap.get("presentBankRef")!=null){
				if(dto.getRelatedRefrence()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(dto.getRelatedRefrence());
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
				}
			}
			if(columnMap.get("noofAmendment")!=null){
				if(dto.getNoofAmendments()!=null){
				Cell datatCell = row.createCell(cellNumber);
            	datatCell.setCellValue(Integer.parseInt(dto.getNoofAmendments().toString()));
            	datatCell.setCellStyle(styles.get("cell"));
            	cellNumber++;
				}
			}
			
			if(columnMap.get("dateofReduction")!=null){
				if(dto.getAmendmentDate()!=null){
				Cell datatCell = row.createCell(cellNumber);
				datatCell.setCellValue(dto.getAmendmentDate());
				datatCell.setCellStyle(styles.get("dateCell"));
            	cellNumber++;
				}
			}
			
			if(columnMap.get("amountReduced")!=null){
				Cell datatCell = row.createCell(cellNumber);
				if(dto.getReducedAmt()!=null){
            	datatCell.setCellValue(Double.parseDouble(dto.getReducedAmt().toString()));
				}else{
					datatCell.setCellValue(Double.parseDouble("0.00"));
				}
            	datatCell.setCellStyle(styles.get("numberCell"));
            	cellNumber++;
			}
			if(columnMap.get("amountofOutstanding")!=null){
				Cell datatCell = row.createCell(cellNumber);
				if(dto.getOutstandAmt()!=null){
            	datatCell.setCellValue(Double.parseDouble(dto.getOutstandAmt().toString()));
				}else{
					datatCell.setCellValue(Double.parseDouble("0.00"));
				}
            	datatCell.setCellStyle(styles.get("numberCell"));
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
	
	private void createPaymentSentCvsFile(List<ReportDto> dtoList,Map<String,String> columnMap)throws Exception{
		String name = reportName.replaceAll(" ", "");
		String fileName = name+getDateTime()+".csv";
		File file = new File(fileName);
		FileWriter writer = new FileWriter(file);
		SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
		if(columnMap.get("msgRef")!=null){
			writer.append("Message Senders Reference  [system generated]");
		    writer.append(',');
		}
		if(columnMap.get("msgType")!=null){
        	writer.append("Msg Type");
        	 writer.append(',');
		}
		if(columnMap.get("branch")!=null){
        	writer.append("Branch");
        	 writer.append(',');
		}		
		
		if(columnMap.get("senderBank")!=null){
			writer.append("Sender IFSC");
       	 	writer.append(',');
		}
		if(columnMap.get("receiverBank")!=null){
			writer.append("Receiver IFSC");
       		writer.append(',');
		}   
		
		if(columnMap.get("status")!=null){
        	writer.append("Status");
        	 writer.append(',');
		}
		if(columnMap.get("txnReference")!=null){
        	writer.append("Senders Reference");
        	 writer.append(',');
		}
		if(columnMap.get("dateofIssue")!=null){
        	writer.append("Date of Issue");
        	 writer.append(',');
		}
		if(columnMap.get("applicent")!=null){
        	writer.append("Applicant");
        	 writer.append(',');
		}
		
		if(columnMap.get("beneficiary")!=null){
        	writer.append("Beneficiary");
        	 writer.append(',');
		}
		if(columnMap.get("applicentBank")!=null){
        	writer.append("Applicant Bank");
        	 writer.append(',');
		}
		
		if(columnMap.get("currency")!=null){
        	writer.append("Currency");
        	 writer.append(',');
		}
		if(columnMap.get("amount")!=null){
        	writer.append("Amount");
        	 writer.append(',');
		}
		if(columnMap.get("dateofExpiry")!=null){
        	writer.append("Date of Expiry");
        	 writer.append(',');
		}
		if(columnMap.get("dateofShipment")!=null){
        	writer.append("Date of Shipment");
        	 writer.append(',');
		}
		if(columnMap.get("dateofAmendment")!=null){
        	writer.append("Date of Amendment");
        	 writer.append(',');
		}
		if(columnMap.get("newDateofExpiry")!=null){
			writer.append("New Date of Expiry");
       		writer.append(',');
		}
		if(columnMap.get("newDateofShipment")!=null){
        	writer.append("New Date of Shipment");
        	 writer.append(',');
		}
		if(columnMap.get("presentBankRef")!=null){
        	writer.append("Presenting Banks Ref");
        	 writer.append(',');
		}
		if(columnMap.get("noofAmendment")!=null){
        	writer.append("No of Amendment");
        	 writer.append(',');
		}
		if(columnMap.get("dateofReduction")!=null){
        	writer.append("Date of Reduction");
        	 writer.append(',');
		}
		if(columnMap.get("amountReduced")!=null){
			writer.append("Amount Reduced");
			writer.append(',');
		}
		
		
		if(columnMap.get("amountEnhanced")!=null){
        	writer.append("Amount Enhanced");
        	writer.append(',');
		}
		if(columnMap.get("amountofOutstanding")!=null){
        	writer.append("Amount Outstanding");
        	writer.append(',');
		}
		writer.append('\n');
		
		 for (ReportDto dto : dtoList) {
			 if(columnMap.get("msgRef")!=null){
				 if(dto.getMsgRef()!=null){
	            	writer.append(dto.getMsgRef());
	            	writer.append(',');
				 }
				}
			 if(columnMap.get("msgType")!=null){
				 if(dto.getMsgType()!=null){
	            	writer.append(dto.getMsgType());
	            	writer.append(',');
				 }
				}
			 if(columnMap.get("branch")!=null){
				 if(dto.getBranch()!=null){
	            	writer.append(dto.getBranch());
	            	writer.append(',');
				 }
				}
			
            if(columnMap.get("senderBank")!=null){
            	if(dto.getSenderBank()!=null){
				writer.append(dto.getSenderBank());
	       	 	writer.append(',');
            	}
			}
			if(columnMap.get("receiverBank")!=null){
				if(dto.getReceiverBank()!=null){
				writer.append(dto.getReceiverBank());
	       		writer.append(',');
				}
			}
			if(columnMap.get("status")!=null){
				if(dto.getPaymentStatus()!=null){
            	writer.append(dto.getPaymentStatus());
            	writer.append(',');
				}
			}
			if(columnMap.get("txnReference")!=null){
				if(dto.getTxnReference()!=null){
            	writer.append(dto.getTxnReference());
            	writer.append(',');
				}
			}
			if(columnMap.get("dateofIssue")!=null){
				if(dto.getIssueDate()!=null){
            	writer.append(sm.format(dto.getIssueDate().getTime()));
            	writer.append(',');
				}
			}
			if(columnMap.get("applicent")!=null){
				if(dto.getOrderingCustomer()!=null){
					String updateBenCust = dto.getOrderingCustomer().replaceAll("\r\n", " ").replaceAll(",", "");
					writer.append(updateBenCust);
				}else{
					writer.append(dto.getOrderingCustomer());
				}
            	writer.append(',');
			}
			if(columnMap.get("beneficiary")!=null){
				if(dto.getBeneficiaryCustomer()!=null){
					String updateBenCust = dto.getBeneficiaryCustomer().replaceAll("\r\n", " ").replaceAll(",", "");
					writer.append(updateBenCust);
				}else{
					writer.append(dto.getBeneficiaryCustomer());
				}
            	writer.append(',');
			}
			if(columnMap.get("applicentBank")!=null){
				if(dto.getApplicentBank()!=null){
            	writer.append(dto.getApplicentBank());
            	writer.append(',');
				}
			}
			if(columnMap.get("currency")!=null){
				if(dto.getMsgCurrency()!=null){
            	writer.append(dto.getMsgCurrency());
            	writer.append(',');
				}
			}
			if(columnMap.get("amount")!=null){
				if(dto.getMsgAmount()!=null){
            	writer.append(dto.getMsgAmount().toString());
				}else{
					writer.append("0.00");
				}
            	writer.append(',');
			}
			if(columnMap.get("dateofExpiry")!=null){
				if(dto.getExpDate()!=null){
            	writer.append(sm.format(dto.getExpDate().getTime()));
            	writer.append(',');
				}
			}
	
			if(columnMap.get("dateofShipment")!=null){
				if(dto.getLastDateofShipment()!=null){
            	writer.append(sm.format(dto.getLastDateofShipment().getTime()));
            	writer.append(',');
				}
			}
			if(columnMap.get("dateofAmendment")!=null){
				if(dto.getAmendmentDate()!=null){
            	writer.append(sm.format(dto.getAmendmentDate().getTime()));
            	writer.append(',');
				}
			}
			if(columnMap.get("newDateofExpiry")!=null){
				if(dto.getExpDate()!=null){
				writer.append(sm.format(dto.getExpDate().getTime()));
	       		writer.append(',');
				}
			}
			if(columnMap.get("newDateofShipment")!=null){
				if(dto.getLastDateofShipment()!=null){
            	writer.append(sm.format(dto.getLastDateofShipment().getTime()));
            	writer.append(',');
				}
			}
			if(columnMap.get("presentBankRef")!=null){
				if(dto.getRelatedRefrence().toString()!=null){
            	writer.append(dto.getRelatedRefrence());
            	writer.append(',');
				}
			}
			if(columnMap.get("noofAmendment")!=null){
				if(dto.getNoofAmendments().toString()!=null){
            	writer.append(dto.getNoofAmendments().toString());
            	writer.append(',');
				}
			}
			if(columnMap.get("dateofReduction")!=null){
				if(dto.getAmendmentDate()!=null){
				writer.append(sm.format(dto.getAmendmentDate().getTime()));
				writer.append(',');
				}
			}
			if(columnMap.get("amountReduced")!=null){
				if(dto.getReducedAmt().toString()!=null){
            	writer.append(dto.getReducedAmt().toString());
            	writer.append(',');
				}
			}
			if(columnMap.get("amountofOutstanding")!=null){
				if(dto.getOutstandAmt().toString()!=null){
            	writer.append(dto.getOutstandAmt().toString());
            	writer.append(',');
				}
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
			PdfPTable footTable = getFooterSignatures(writer);
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
		headTable.addCell("\n");
		try{
			headTable.getDefaultCell().setFixedHeight(20);
			headTable.getDefaultCell().setBackgroundColor(Color.getHSBColor(199, 15, 100));
			headTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			String destinationPath = servletRequest.getSession().getServletContext().getRealPath("/");
			Image png = Image.getInstance(destinationPath+"images/logo.png"); // to do
			headTable.addCell(png); 
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
	private PdfPTable getFooterSignatures(PdfWriter writer) throws DocumentException {
		Font fontStyleFooters = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		PdfPTable footTable = new PdfPTable(2);
		footTable.setWidths(new int[]{80, 20});
		footTable.setWidthPercentage(100);
		footTable.getDefaultCell().setPadding(2);
		footTable.getDefaultCell().setBorderWidth(0);
		footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		footTable.addCell(new Phrase("@Copyright 2012 QNG. All rights reserved.", fontStyleFooters)); 
		
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);
		footTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
		footTable.addCell(new Phrase("Page No :"+Integer.toString(writer.getPageNumber()),fontStyleCells)); 
		try{
			footTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footTable.getDefaultCell().setFixedHeight(20);
			String destinationPath = servletRequest.getSession().getServletContext().getRealPath("/");
			Image png = Image.getInstance(destinationPath+"images/CGILogo.jpg"); //to do
			footTable.addCell(png); 
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
		table.getDefaultCell().getExtraParagraphSpace();
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
	private PdfPTable getReportTable(List<ReportDto> dtoList, Map<String,String> columnMap) throws DocumentException {
		PdfPTable table = new PdfPTable(columnMap.size());
		/*table.setWidthPercentage(100); 
		table.getDefaultCell().setPadding(2);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setColspan(columnMap.size());
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk header = new Chunk("Results:", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
		table.addCell(new Phrase(header));
		table.addCell("\n");*/
		float [] widths ={5f,3.5f,3.5f,3.5f,3.5f,8f,4f,4f,4f,7.5f,7.5f,4f,3f,5f,4f,4f,4f,4f,4f,3f,4f,5f,5f,5f};
		if(columnMap.size()== widths.length){
			table.setWidths(widths); 
		}
		SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");		
		Font fontStyleHeaders = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL);
		//table.setWidths(widths); 
		table.setWidthPercentage(90); 
		
		if(columnMap.get("msgRef")!=null){
			table.addCell(new Phrase("Message Senders Reference", fontStyleHeaders));
		}
		if(columnMap.get("msgType")!=null){
			table.addCell(new Phrase("Msg Type", fontStyleHeaders));
		}
		if(columnMap.get("branch")!=null){
			table.addCell(new Phrase("Branch", fontStyleHeaders));
		}
		if(columnMap.get("senderBank")!=null){
			table.addCell(new Phrase("Sender IFSC", fontStyleHeaders));
		}
		if(columnMap.get("receiverBank")!=null){
			table.addCell(new Phrase("Receiver IFSC", fontStyleHeaders));
		}
		
		if(columnMap.get("status")!=null){
			table.addCell(new Phrase("Status", fontStyleHeaders));
		}
		if(columnMap.get("txnReference")!=null){
			table.addCell(new Phrase("Senders Reference", fontStyleHeaders));
		}
		if(columnMap.get("dateofIssue")!=null){
			table.addCell(new Phrase("Date of Issue", fontStyleHeaders));
		}
		if(columnMap.get("applicent")!=null){
			table.addCell(new Phrase("Applicant", fontStyleHeaders));
		}
		
		if(columnMap.get("beneficiary")!=null){
			table.addCell(new Phrase("Beneficiary", fontStyleHeaders));
		}
		if(columnMap.get("applicentBank")!=null){
			table.addCell(new Phrase("Applicant Bank", fontStyleHeaders));
		}
		if(columnMap.get("currency")!=null){
			table.addCell(new Phrase("Currency", fontStyleHeaders));
		}
		
		if(columnMap.get("amount")!=null){
			table.addCell(new Phrase("Amount", fontStyleHeaders));
		}
		if(columnMap.get("dateofExpiry")!=null){
			table.addCell(new Phrase("Date of Expiry", fontStyleHeaders));
		}
		if(columnMap.get("dateofShipment")!=null){
			table.addCell(new Phrase("Date of Shipment", fontStyleHeaders));
		}
		
		if(columnMap.get("dateofAmendment")!=null){
			table.addCell(new Phrase("Date of Amendment", fontStyleHeaders));
		}
		if(columnMap.get("newDateofExpiry")!=null){
			table.addCell(new Phrase("New Date of Expiry", fontStyleHeaders));
		}
		if(columnMap.get("newDateofShipment")!=null){
			table.addCell(new Phrase("New Date of Shipment", fontStyleHeaders));
		}
		
		if(columnMap.get("presentBankRef")!=null){
			table.addCell(new Phrase("Presenting Banks Ref", fontStyleHeaders));
		}
		if(columnMap.get("noofAmendment")!=null){
			table.addCell(new Phrase("No of Amendment", fontStyleHeaders));
		}
		if(columnMap.get("dateofReduction")!=null){
			table.addCell(new Phrase("Date of Reduction", fontStyleHeaders));
		}
		if(columnMap.get("amountReduced")!=null){
			table.addCell(new Phrase("Amount Reduced", fontStyleHeaders));
		}
		if(columnMap.get("amountEnhanced")!=null){
			table.addCell(new Phrase("Amount Enhanced", fontStyleHeaders));
		}
		if(columnMap.get("amountofOutstanding")!=null){
			table.addCell(new Phrase("Amount Outstanding", fontStyleHeaders));
		}

		for (ReportDto dto : dtoList ) { 
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT); 
			if(columnMap.get("msgRef")!=null){
				if(dto.getMsgRef()!=null){
				table.addCell(new Phrase(dto.getMsgRef(), fontStyleCells));
				}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("msgType")!=null){
				if(dto.getMsgType()!=null){
				table.addCell(new Phrase(dto.getMsgType(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("branch")!=null){
				if(dto.getBranch()!=null){
				table.addCell(new Phrase(dto.getBranch(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("senderBank")!=null){
				if(dto.getSenderBank()!=null){
				table.addCell(new Phrase(dto.getSenderBank(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("receiverBank")!=null){
				if(dto.getReceiverBank()!=null){
				table.addCell(new Phrase(dto.getReceiverBank(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("status")!=null){
				if(dto.getPaymentStatus()!=null){
				table.addCell(new Phrase(dto.getPaymentStatus(), fontStyleCells));
			}	
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("txnReference")!=null){
				if(dto.getTxnReference()!=null){
				table.addCell(new Phrase(dto.getTxnReference(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("dateofIssue")!=null){
				if(dto.getIssueDate()!=null){
				table.addCell(new Phrase(sm.format(dto.getIssueDate().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("applicent")!=null){
				if(dto.getOrderingCustomer()!=null){
				table.addCell(new Phrase(dto.getOrderingCustomer(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("beneficiary")!=null){
				if(dto.getBeneficiaryCustomer()!=null){
				table.addCell(new Phrase(dto.getBeneficiaryCustomer(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("applicentBank")!=null){
				if(dto.getApplicentBank()!=null){
				table.addCell(new Phrase(dto.getApplicentBank(), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("currency")!=null){
				if(dto.getApplicentBank()!=null){
				table.addCell(new Phrase(dto.getMsgCurrency(), fontStyleCells));
			}		
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("amount")!=null){
				if(dto.getMsgAmount()!=null){
				table.addCell(new Phrase(dto.getMsgAmount().toString(), fontStyleCells));
				}else{
					table.addCell(new Phrase("0.00", fontStyleCells));
				}
			}
			if(columnMap.get("dateofExpiry")!=null){
				if(dto.getExpDate()!=null){
				table.addCell(new Phrase(sm.format(dto.getExpDate().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("dateofShipment")!=null){
				if(dto.getLastDateofShipment()!=null){
				table.addCell(new Phrase(sm.format(dto.getLastDateofShipment().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("dateofAmendment")!=null){
				if(dto.getAmendmentDate()!=null){
				table.addCell(new Phrase(sm.format(dto.getAmendmentDate().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("newDateofExpiry")!=null){
				if(dto.getExpDate()!=null){
				table.addCell(new Phrase(sm.format(dto.getExpDate().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("newDateofShipment")!=null){
				if(dto.getLastDateofShipment()!=null){
				table.addCell(new Phrase(sm.format(dto.getLastDateofShipment().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("presentBankRef")!=null){
				if(dto.getRelatedRefrence()!=null){
				table.addCell(new Phrase(dto.getRelatedRefrence(), fontStyleCells));
			}	
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("noofAmendment")!=null){
				if(dto.getNoofAmendments()!=null){
				table.addCell(new Phrase(dto.getNoofAmendments().toString(), fontStyleCells));
			}	
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("dateofReduction")!=null){
				if(dto.getAmendmentDate()!=null){
				table.addCell(new Phrase(sm.format(dto.getAmendmentDate().getTime()), fontStyleCells));
			}
				else{
					table.addCell("");
				}
			}
			if(columnMap.get("amountReduced")!=null){
				if(dto.getReducedAmt()!=null){
				table.addCell(new Phrase(dto.getReducedAmt().toString(), fontStyleCells));
				}else{
					table.addCell(new Phrase("0.00", fontStyleCells));
				}
			}
		
			if(columnMap.get("amountofOutstanding")!=null){
				if(dto.getOutstandAmt()!=null){
				table.addCell(new Phrase(dto.getOutstandAmt().toString(), fontStyleCells));
				}else{
					table.addCell(new Phrase("0.00", fontStyleCells));
				}
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
        style.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyyy"));
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
    	    response.setHeader("Cache-Control",null);
			response.setHeader("Pragma",null);
    	    /*response.setContentLength(4096);
       	    response.setHeader("Cache-Control","max-age=0");
       	    response.setHeader("Cache-Control","no-cache");
       	    response.setHeader("Pragma","public");
       	    response.setHeader("Pragma","no-cache");
       	    response.setDateHeader("Expires", 0);
    	    */
 	        FileInputStream fileIn = new FileInputStream(fileName);
 	        ServletOutputStream outStream = response.getOutputStream();
 	        byte[] outputByte = new byte[4096];
 	        while(fileIn.read(outputByte, 0, 4096) != -1){
 	        	outStream.write(outputByte, 0, 4096);
 	        }
 	        
 	    
 	        fileIn.close();
 	        outStream.flush();
 	        outStream.close();
 	        response.getOutputStream().flush();
 	        response.getOutputStream().close();
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
