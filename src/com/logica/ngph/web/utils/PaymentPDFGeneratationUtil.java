/**
 * 
 */
package com.logica.ngph.web.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


/**
 * @author chakkar
 *
 */
public class PaymentPDFGeneratationUtil extends PdfPageEventHelper {
	
	private HttpServletRequest servletRequest;
	private  String reportName;
	SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
	
	
	public void generatePaymentPDFReport(LCCanonicalDto lcCanonicalDto, CreateBankGuaranteeDto createBankGuaranteeDto,BankGuaranteeCoverDto bankGuaranteeCoverDto,Map<String,String> columnMap, int msgType, String subMsgType)throws Exception{
		
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
			
			
			switch (msgType) {
            
		    case 700: 
 			            document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportLcOpenDataTable(lcCanonicalDto,columnMap));
            			break;
            case 707: 
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportAmendLCDataTable(lcCanonicalDto,columnMap));
            			break;
            case 730:  
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportLcAckDataTable(lcCanonicalDto,columnMap));
            			break;
            case 734:  
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportAdviceofRefusalDataTable(lcCanonicalDto,columnMap));
            			break;
            case 740:  
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportAuthoriseLCPaymentAdviceDataTable(lcCanonicalDto,columnMap));
            			break;
            case 750: 
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportAdviceofDiscrepancyDataTable(lcCanonicalDto,columnMap));
            			break;
            case 754:
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportAdviceofPaymentDataTable(lcCanonicalDto,columnMap));
            			break;
            case 756: 
            			document.add(getReportLCHeaderTable(lcCanonicalDto,columnMap));
            			document.add(getReportAdviceLCPaymentDataTable(lcCanonicalDto,columnMap));
            			break;
            case 760: if(subMsgType.equalsIgnoreCase("XXX"))
            			{
            				document.add(getReportBGHeaderTable(createBankGuaranteeDto,columnMap));
            				document.add(getReportCreateBankGuaranteeDataTable(createBankGuaranteeDto,columnMap));
            			}
            			else
            			{
            				document.add(getReportBGHeaderTable(bankGuaranteeCoverDto,columnMap));
            				document.add(getReportCreateBankGuaranteeCoverDataTable(bankGuaranteeCoverDto,columnMap));
            			}
            			break;
            case 767: if(subMsgType.equalsIgnoreCase("XXX"))
						{
            				document.add(getReportBGHeaderTable(createBankGuaranteeDto,columnMap));
            				document.add(getReportAmendBGDataTable(createBankGuaranteeDto,columnMap));
						}
						else
						{
							document.add(getReportBGHeaderTable(bankGuaranteeCoverDto,columnMap));
            				document.add(getReportCreateAmendBGCoverDataTable(bankGuaranteeCoverDto,columnMap));
						}
                 		break;
            case 768: 
            			document.add(getReportBGHeaderTable(createBankGuaranteeDto,columnMap));
				        document.add(getReportAckBGDataTable(createBankGuaranteeDto,columnMap));
				        break;
          case 769: 
        	  			document.add(getReportBGHeaderTable(createBankGuaranteeDto,columnMap));
        	  			document.add(getReportAdviceofReductionDataTable(createBankGuaranteeDto,columnMap));
        	  			break;
            case 799: 
            			document.add(getReportBGHeaderTable(createBankGuaranteeDto,columnMap));
            			document.add(getReportFreeFormatDataTable(createBankGuaranteeDto,columnMap));
            			break;
            default: 
                     	break;
		 }
	
						
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
	
	
	/*
	 * To generate Search Criteria in the PDF
	 */
	private PdfPTable getSearchCriteriaTable() throws DocumentException {
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
		table.getDefaultCell().setColspan(3);
		table.addCell("\n");
		table.addCell("\n");
		return table;
	}
	
	
	private void pushFileToDownload(String fileName) throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletOutputStream outStream = response.getOutputStream();
		try {
			File file = new File(fileName);
			response.reset();
			// response.setContentType("application/pdf");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			FileInputStream fileIn = new FileInputStream(fileName);
			byte[] outputByte = new byte[4096];
			while (fileIn.read(outputByte, 0, 4096) != -1) {
				outStream.write(outputByte, 0, 4096);
			}

			fileIn.close();

			if (file.delete()) {
				System.out.println("File Deleted... ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Download File");
		} finally {
			outStream.flush();
			outStream.close();
		}

    }


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
	
	
	/**
	 * To generate Search Criteria in the PDF
	 * @param dtoList
	 * @param columnMap
	 * @return
	 * @throws DocumentException
	 */
	
	public PdfPTable getReportBGHeaderTable(CreateBankGuaranteeDto createBankGuaranteeDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(4);
		
		Font fontHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		table.setWidthPercentage(90); 
		
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell(new Phrase("Basic Details", fontHeaders));
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
	
		if(columnMap.get("label.MB.IssuingBranchIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.MB.IssuingBranchIFSC"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getIssuingBankCode()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getIssuingBankCode(), fontStyleCells));
		}
		
		if(columnMap.get("label.MB.Benefifsc")!=null){
			table.addCell(new Phrase(columnMap.get("label.MB.Benefifsc"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAdvisingBank()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAdvisingBank(), fontStyleCells));
		}
		
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell(new Phrase("Message Details", fontHeaders));
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");


		return table;
	}
	
	public PdfPTable getReportBGHeaderTable(BankGuaranteeCoverDto bankGuaranteeCoverDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(4);
		
		Font fontHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		table.setWidthPercentage(90); 
		
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell(new Phrase("Basic Details", fontHeaders));
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
	
		if(columnMap.get("label.MB.IssuingBranchIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.MB.IssuingBranchIFSC"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getIssuingBankCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getIssuingBankCode(), fontStyleCells));
		}
		
		if(columnMap.get("label.MB.Benefifsc")!=null){
			table.addCell(new Phrase(columnMap.get("label.MB.Benefifsc"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getAdvisingBank()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getAdvisingBank(), fontStyleCells));
		}
		
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell(new Phrase("Message Details", fontHeaders));
		table.addCell("\n");
		table.addCell("\n");
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
	
	public PdfPTable getReportLCHeaderTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(4);
		
		Font fontHeaders = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		table.setWidthPercentage(90); 
		
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell(new Phrase("Basic Details", fontHeaders));
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
	
		if(columnMap.get("label.MB.IssuingBranchIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.MB.IssuingBranchIFSC"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getIssuingBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getIssuingBankCode(), fontStyleCells));
		}
		
		if(columnMap.get("label.MB.Benefifsc")!=null){
			table.addCell(new Phrase(columnMap.get("label.MB.Benefifsc"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdvisingBank()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdvisingBank(), fontStyleCells));
		}
		
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell("\n");
		table.addCell(new Phrase("Message Details", fontHeaders));
		table.addCell("\n");
		table.addCell("\n");
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
	
	public PdfPTable getReportFreeFormatDataTable(CreateBankGuaranteeDto createBankGuaranteeDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90); 
		
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.799.pptransactionReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.799.pptransactionReferenceNumber"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgNumber()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.799.pprelatedReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.799.pprelatedReferenceNumber"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getRelatedReference()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("79", fontFieldHeaders));
		if(columnMap.get("label.799.ppNarrative")!=null){
			table.addCell(new Phrase(columnMap.get("label.799.ppNarrative"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getNarrative()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getNarrative(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
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
	
	public PdfPTable getReportCreateBankGuaranteeDataTable(CreateBankGuaranteeDto createBankGuaranteeDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths);
		table.setWidthPercentage(90); 
		
		table.addCell(new Phrase("27", fontFieldHeaders));
		if(columnMap.get("label.760.ppSequenceofTotal")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppSequenceofTotal"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getSequence()!=null || createBankGuaranteeDto.getSequenceofTotal()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getSequence()+ " / " +createBankGuaranteeDto.getSequenceofTotal(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.760.pptransactionReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.pptransactionReferenceNumber"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgNumber()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("23", fontFieldHeaders));
		if(columnMap.get("label.760.ppFurtherIdentification")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppFurtherIdentification"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgCreateType()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgCreateType(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("30", fontFieldHeaders));
		if(columnMap.get("label.760.ppbgDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppbgDate"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgDate()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getBgDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("40C", fontFieldHeaders));
		if(columnMap.get("label.760.ppbgRuleCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppbgRuleCode"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgRuleCode()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgRuleCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("40C", fontFieldHeaders));
		if(columnMap.get("label.760.ppbgRuleNarration")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppbgRuleNarration"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgRuleNarration()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgRuleNarration(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("77C", fontFieldHeaders));
		if(columnMap.get("label.760.ppbgDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppbgDetails"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgDetails()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.760.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.760.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getSenderToReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		
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
	
	public PdfPTable getReportAmendBGDataTable(CreateBankGuaranteeDto createBankGuaranteeDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths);
		table.setWidthPercentage(90); 
		
		table.addCell(new Phrase("27", fontFieldHeaders));
		if(columnMap.get("label.767.ppSequenceofTotal")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppSequenceofTotal"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getSequence()!=null || createBankGuaranteeDto.getSequenceofTotal()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getSequence()+ " / " +createBankGuaranteeDto.getSequenceofTotal(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.767.pptransactionReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.pptransactionReferenceNumber"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgNumber()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.767.ppbgrelatedReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppbgrelatedReference"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getRelatedReference()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("23", fontFieldHeaders));
		if(columnMap.get("label.767.ppFurtherIdentification")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppFurtherIdentification"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgFurtherIdentification()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgFurtherIdentification(), fontStyleCells));	
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("30", fontFieldHeaders));
		if(columnMap.get("label.767.ppbgDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppbgDate"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgDate()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getBgDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("26E", fontFieldHeaders));
		if(columnMap.get("label.767.ppNumberofAmendment")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppNumberofAmendment"), fontFieldHeaders));
		}
		String str1 = Integer.toString(createBankGuaranteeDto.getBgNoOfAmntmnt());
		if(str1!=null){
			table.addCell(new Phrase(Integer.toString(createBankGuaranteeDto.getBgNoOfAmntmnt()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31C", fontFieldHeaders));
		if(columnMap.get("label.767.ppDateofIssue")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppDateofIssue"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgIssueDate()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getBgIssueDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("77C", fontFieldHeaders));
		if(columnMap.get("label.767.ppamendDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppamendDetails"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgDetails()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.767.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.767.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getSenderToReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAckBGDataTable(CreateBankGuaranteeDto createBankGuaranteeDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90); 
		
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.768.pptransactionReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.pptransactionReferenceNumber"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgNumber()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.768.ppbgrelatedReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppbgrelatedReference"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getRelatedReference()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("25", fontFieldHeaders));
		if(columnMap.get("label.768.ppaccountIdentification")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppaccountIdentification"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgAccountIdentification()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgAccountIdentification(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("30", fontFieldHeaders));
		if(columnMap.get("label.768.ppdateofMessageBeingAcknowledged")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppdateofMessageBeingAcknowledged"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getDateofMessageBeingAcknowledged()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getDateofMessageBeingAcknowledged().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.768.ppchargeAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppchargeAmount"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getLcCurrency()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.768.ppDateofIssue")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppDateofIssue"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgDebitDate()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getBgDebitDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.768.ppCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppCurrency"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgCurrency()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.768.ppamount")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppamount"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgChargeAmount()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgChargeAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.768.ppaccountWithPartyIdentifier1")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppaccountWithPartyIdentifier1"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.768.ppaccountWithPartyIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppaccountWithPartyIFSC"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAdviseThroughBankCode()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAdviseThroughBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.768.ppaccountWithPartyLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppaccountWithPartyLocation"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAccountwithPartyLocation()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAccountwithPartyLocation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.768.ppaccountWithNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppaccountWithNameAndAddress"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAdviseThroughBankName()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAdviseThroughBankName(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.768.ppchargeDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppchargeDetails"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getChargesDetails()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getChargesDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.768.ppsenderToReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.768.ppsenderToReceiverInformation"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getSenderToReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAdviceofReductionDataTable(CreateBankGuaranteeDto createBankGuaranteeDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90); 
		
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.769.ppbgNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppbgNumber"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgNumber()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.769.ppbgrelatedReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppbgrelatedReference"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getRelatedReference()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("25", fontFieldHeaders));
		if(columnMap.get("label.769.ppaccountIdentification")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppaccountIdentification"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgAccountIdentification()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgAccountIdentification(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("30", fontFieldHeaders));
		if(columnMap.get("label.769.ppdateofReduction")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppdateofReduction"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getReductionDate()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getReductionDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.769.ppchargeAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppchargeAmount"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getChargeAmt()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getChargeAmt().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.769.ppchargeAmtCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppchargeAmtCurrency"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgCurrency()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.769.ppAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppAmount"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgChargeAmount()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgChargeAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.769.ppchargeDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppchargeDate"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getChargeDate()!=null){
			table.addCell(new Phrase(sm.format(createBankGuaranteeDto.getChargeDate().getTime()), fontStyleCells));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.769.ppamountReducedAmtCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppamountReducedAmtCurrency"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getReducedCurrency()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getReducedCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.769.ppamountReduced")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppamountReduced"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getReducedAmt()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getReducedAmt().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("34B", fontFieldHeaders));
		if(columnMap.get("label.769.ppoutstandingAmtCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppoutstandingAmtCurrency"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getOutstandingCurrency()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getOutstandingCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34B", fontFieldHeaders));
		if(columnMap.get("label.769.ppoutstandingAmt")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppoutstandingAmt"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getOutstandingAmt()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getOutstandingAmt().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("39C", fontFieldHeaders));
		if(columnMap.get("label.769.ppamountSpecification")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppamountSpecification"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAmountSpecification()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAmountSpecification(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.769.ppaccountWithBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppaccountWithBank"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAccountWithBank()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAccountWithBank(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.769.ppaccountWithPartyIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppaccountWithPartyIFSC"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAuthorisedBankCode()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAuthorisedBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.769.ppaccountWithPartyLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppaccountWithPartyLocation"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getAccountWithPartyLocation()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getAccountWithPartyLocation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.769.ppaccountWithNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppaccountWithNameAndAddress"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getBgAccountWithNameandAddress()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getBgAccountWithNameandAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.769.ppchargesDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppchargesDetails"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getChargesDetails()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getChargesDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.769.ppsenderToReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.769.ppsenderToReceiverInformation"), fontFieldHeaders));
		}
		if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null){
			table.addCell(new Phrase(createBankGuaranteeDto.getSenderToReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportLcAckDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.730.ppSenderBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppSenderBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.730.ppReceiverBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppReceiverBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getRelatedReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("25", fontFieldHeaders));
		if(columnMap.get("label.730.ppaccountIdentification")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppaccountIdentification"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAccId()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcAccId(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("30", fontFieldHeaders));
		if(columnMap.get("label.730.ppdateofMessageBeingAcknowledged")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppdateofMessageBeingAcknowledged"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAckDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getLcAckDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.730.ppChargeAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppChargeAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.730.ppCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.730.ppAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.730.ppamountPaidDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppamountPaidDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAmountPaidDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getAmountPaidDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.730.ppaccountWithPartyIdentifier1")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppaccountWithPartyIdentifier1"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.730.ppaccountWithBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppaccountWithBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.730.ppaccountWithNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppaccountWithNameAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankName()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankName(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.730.ppChargeDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppChargeDetails"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargeDetails()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargeDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.730.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.730.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAdviceofRefusalDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.734.ppSenderBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppSenderBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.734.ppReceiverBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppReceiverBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getRelatedReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32A", fontFieldHeaders));
		if(columnMap.get("label.734.ppUtilizationDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppUtilizationDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAckDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getLcAckDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32A", fontFieldHeaders));
		if(columnMap.get("label.734.ppCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32A", fontFieldHeaders));
		if(columnMap.get("label.734.ppUtilizationAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppUtilizationAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("73", fontFieldHeaders));
		if(columnMap.get("label.734.ppChargesClaimed")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppChargesClaimed"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargesClaimed()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargesClaimed(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33a", fontFieldHeaders));
		if(columnMap.get("label.734.ppchargeAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppchargeAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33a", fontFieldHeaders));
		if(columnMap.get("label.734.ppamountPaidDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppamountPaidDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAmountPaidDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getAmountPaidDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33a", fontFieldHeaders));
		if(columnMap.get("label.734.ppcurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppcurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getClaimCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getClaimCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33a", fontFieldHeaders));
		if(columnMap.get("label.734.ppTotalAmountClaimed")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppTotalAmountClaimed"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcClaimAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcClaimAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.734.ppaccountWithPartyIdentifier1")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppaccountWithPartyIdentifier1"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.734.ppaccountWithBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppaccountWithBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.734.ppaccountWithPartyLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppaccountWithPartyLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankLocation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankLocation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.734.ppaccountWithNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppaccountWithNameAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankName()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankName(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.734.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("77J", fontFieldHeaders));
		if(columnMap.get("label.734.ppDiscrepancies")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppDiscrepancies"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDiscrepancies()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDiscrepancies(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("77B", fontFieldHeaders));
		if(columnMap.get("label.734.ppDisposalDocs")!=null){
			table.addCell(new Phrase(columnMap.get("label.734.ppDisposalDocs"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcDispoDocs()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcDispoDocs(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAuthoriseLCPaymentAdviceDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.740.pplc_Number")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.pplc_Number"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("25", fontFieldHeaders));
		if(columnMap.get("label.740.ppAccountID")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppAccountID"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAcountID()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAcountID(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("40F", fontFieldHeaders));
		if(columnMap.get("label.740.ppapplicableRules")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppapplicableRules"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicableRule()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicableRule(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31D", fontFieldHeaders));
		if(columnMap.get("label.740.ppExpiryDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppExpiryDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcExpiryDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getLcExpiryDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31D", fontFieldHeaders));
		if(columnMap.get("label.740.ppexpiryPlace")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppexpiryPlace"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcExpirePlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcExpirePlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("58a", fontFieldHeaders));
		if(columnMap.get("label.740.ppNegotiatingBankPartyIdentifier")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppNegotiatingBankPartyIdentifier"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNegotiatingBankPartyIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNegotiatingBankPartyIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("58a", fontFieldHeaders));
		if(columnMap.get("label.740.ppNegotiatingBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppNegotiatingBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNegotiatingBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNegotiatingBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("58a", fontFieldHeaders));
		if(columnMap.get("label.740.ppNegotiatingBankNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppNegotiatingBankNameAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNegotiatingBankNameAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNegotiatingBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("59", fontFieldHeaders));
		if(columnMap.get("label.740.ppbeneficiaryNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppbeneficiaryNameAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getBeneficiaryNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getBeneficiaryNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.740.ppcreditCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppcreditCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.740.ppCreditAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppCreditAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCreditAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCreditAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("39A", fontFieldHeaders));
		if(columnMap.get("label.740.pppositiveTolerance")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.pppositiveTolerance"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPositiveTolerance()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPositiveTolerance(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39A", fontFieldHeaders));
		if(columnMap.get("label.740.ppnegativeTolerance")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppnegativeTolerance"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNegativeTolerance()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNegativeTolerance(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39B", fontFieldHeaders));
		if(columnMap.get("label.740.ppmaximumCreditAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppmaximumCreditAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMaximumCreditAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMaximumCreditAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("39C", fontFieldHeaders));
		if(columnMap.get("label.740.ppAdditionalAmountsCovered")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppAdditionalAmountsCovered"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdditionalAmountsCovered()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdditionalAmountsCovered(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41a", fontFieldHeaders));
		if(columnMap.get("label.740.ppAvailableWithIdentifier")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppAvailableWithIdentifier"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAvailableWithIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAvailableWithIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41A", fontFieldHeaders));
		if(columnMap.get("label.740.ppauthorisedBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppauthorisedBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAuthorisedBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAuthorisedBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41a", fontFieldHeaders));
		if(columnMap.get("label.740.ppauthorisationMode")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppauthorisationMode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAuthorisationMode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAuthorisationMode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41D", fontFieldHeaders));
		if(columnMap.get("label.740.ppauthorisedAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppauthorisedAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAuthorisedAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAuthorisedAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42C", fontFieldHeaders));
		if(columnMap.get("label.740.ppDraftsAt")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppDraftsAt"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraftsAt()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraftsAt(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42a", fontFieldHeaders));
		if(columnMap.get("label.740.ppDraweeBankID")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppDraweeBankID"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraweeBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraweeBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42a", fontFieldHeaders));
		if(columnMap.get("label.740.ppDraweeBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppDraweeBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraweeBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraweeBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42a", fontFieldHeaders));
		if(columnMap.get("label.740.ppDraweeBankNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppDraweeBankNameAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraweeBankNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraweeBankNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42M", fontFieldHeaders));
		if(columnMap.get("label.740.ppMixedPaymentDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppMixedPaymentDetails"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMixedPaymentDetails()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMixedPaymentDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42P", fontFieldHeaders));
		if(columnMap.get("label.740.ppDeferredPaymentDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppDeferredPaymentDetails"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDeferredPaymentDetails()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDeferredPaymentDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("71A", fontFieldHeaders));
		if(columnMap.get("label.740.ppReimbursingBanksCharges")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppReimbursingBanksCharges"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingBanksCharges()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingBanksCharges(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.740.ppOtherCharges")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppOtherCharges"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getOtherCharges()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getOtherCharges(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.740.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.740.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAdviceofDiscrepancyDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.750.ppSenderBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppSenderBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.750.pprelatedReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.pprelatedReferenceNumber"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getRelatedReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.750.ppCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMsgCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMsgCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.750.ppPrincipalAmountClaimed")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppPrincipalAmountClaimed"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPrincipalAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPrincipalAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.750.ppcurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppcurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.750.ppadditionalAmounts")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppadditionalAmounts"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdditionalAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdditionalAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.750.ppChargesDeducted")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppChargesDeducted"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargesDeducted()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargesDeducted(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("73", fontFieldHeaders));
		if(columnMap.get("label.750.ppChargesAdded")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppChargesAdded"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargesAdded()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargesAdded(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34B", fontFieldHeaders));
		if(columnMap.get("label.750.ppCUrrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppCUrrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34B", fontFieldHeaders));
		if(columnMap.get("label.750.ppTotalAmountPaid")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppTotalAmountPaid"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getTotalAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getTotalAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.750.ppaccountWithPartyIdentifier1")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppaccountWithPartyIdentifier1"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.750.ppaccountWithBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppaccountWithBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.750.ppaccountWithPartyLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppaccountWithPartyLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAccountWithPartyLoc()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAccountWithPartyLoc(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.750.ppaccountWithNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppaccountWithNameAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAccountWithPartyNameAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAccountWithPartyNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.750.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("77J", fontFieldHeaders));
		if(columnMap.get("label.750.ppDiscrepancies")!=null){
			table.addCell(new Phrase(columnMap.get("label.750.ppDiscrepancies"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDiscrepancies()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDiscrepancies(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAdviceofPaymentDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.754.ppSenderBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppSenderBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.754.pprelatedReferenceNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.pprelatedReferenceNumber"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getRelatedReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.754.ppPrincipalAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppPrincipalAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPricAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPricAmount(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.754.ppamountPaidDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppamountPaidDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAmountPaidDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getAmountPaidDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.754.ppCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMsgCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMsgCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32a", fontFieldHeaders));
		if(columnMap.get("label.754.ppPrincipalAmountClaimed")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppPrincipalAmountClaimed"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPrincipalAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPrincipalAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.754.ppcurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppcurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.754.ppadditionalAmounts")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppadditionalAmounts"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdditionalAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdditionalAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.754.ppChargesDeducted")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppChargesDeducted"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargesDeducted()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargesDeducted(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("73", fontFieldHeaders));
		if(columnMap.get("label.754.ppChargesAdded")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppChargesAdded"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargesAdded()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargesAdded(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34a", fontFieldHeaders));
		if(columnMap.get("label.754.ppTotalAmountClaimed")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppTotalAmountClaimed"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getTotalAmountClaim()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getTotalAmountClaim(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34a", fontFieldHeaders));
		if(columnMap.get("label.754.ppTotalAmountClaimedDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppTotalAmountClaimedDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getTotalPaidDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getTotalPaidDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34a", fontFieldHeaders));
		if(columnMap.get("label.754.ppCUrrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppCUrrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34a", fontFieldHeaders));
		if(columnMap.get("label.754.ppAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getTotalAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getTotalAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.754.ppReimbursingBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppReimbursingBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.754.ppReimbursingBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppReimbursingBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.754.ppReimbursingBankLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppReimbursingBankLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbersingBankLoc()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbersingBankLoc(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.754.ppReimbursingBankNameandAddess")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppReimbursingBankNameandAddess"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingBankNameandAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingBankNameandAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.754.ppaccountWithPartyIdentifier1")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppaccountWithPartyIdentifier1"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.754.ppaccountWithBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppaccountWithBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.754.ppaccountWithPartyLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppaccountWithPartyLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAccountWithPartyLoc()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAccountWithPartyLoc(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.754.ppaccountWithNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppaccountWithNameAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAccountWithPartyNameAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAccountWithPartyNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("58a", fontFieldHeaders));
		if(columnMap.get("label.754.ppbeneficiarybank")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppbeneficiarybank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getBeneficiaryBankpartyidentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getBeneficiaryBankpartyidentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("58a", fontFieldHeaders));
		if(columnMap.get("label.754.ppBeneficiarybankBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppBeneficiarybankBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getBeneficiaryBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getBeneficiaryBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("58a", fontFieldHeaders));
		if(columnMap.get("label.754.ppBeneficiaryBankNameandAddess")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppBeneficiaryBankNameandAddess"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getBeneficiaryBankNameAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getBeneficiaryBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.754.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("77A", fontFieldHeaders));
		if(columnMap.get("label.754.ppNarrative")!=null){
			table.addCell(new Phrase(columnMap.get("label.754.ppNarrative"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNarrative()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNarrative(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAdviceLCPaymentDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.756.pplc_Number")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.pplc_Number"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.756.ppPresentingBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppPresentingBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPresentingBanksReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPresentingBanksReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.756.pplcCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.pplcCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getClaimCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getClaimCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.756.ppTotalAmountClaimed")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppTotalAmountClaimed"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getTotalAmountClaimed()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getTotalAmountClaimed().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("33A", fontFieldHeaders));
		if(columnMap.get("label.756.ppamountPaidDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppamountPaidDate"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAmountPaidDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getAmountPaidDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33A", fontFieldHeaders));
		if(columnMap.get("label.756.ppcurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppcurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33A", fontFieldHeaders));
		if(columnMap.get("label.756.ppPaidAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppPaidAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPaidAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPaidAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.756.ppSendersCorrespondentPartyIdentifier")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppSendersCorrespondentPartyIdentifier"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendersCorrespondentPartyIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendersCorrespondentPartyIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.756.ppSendersCorrespondentcode")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppSendersCorrespondentcode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendersCorrespondentCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendersCorrespondentCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.756.ppSendersCorrespondentLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppSendersCorrespondentLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendersCorrespondentLocation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendersCorrespondentLocation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.756.ppSendersCorrespondentNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppSendersCorrespondentNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendersCorrespondentNameandAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendersCorrespondentNameandAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("54a", fontFieldHeaders));
		if(columnMap.get("label.756.ppReceiversCorrespondentPartyIdentifier")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppReceiversCorrespondentPartyIdentifier"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReceiversCorrespondentPartyIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReceiversCorrespondentPartyIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("54a", fontFieldHeaders));
		if(columnMap.get("label.756.ppReceiversCorrespondentCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppReceiversCorrespondentCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReceiversCorrespondentCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReceiversCorrespondentCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("54a", fontFieldHeaders));
		if(columnMap.get("label.756.ppReceiversCorrespondentLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppReceiversCorrespondentLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReceiversCorrespondentLocation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReceiversCorrespondentLocation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("54a", fontFieldHeaders));
		if(columnMap.get("label.756.ppReceiversCorrespondentNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppReceiversCorrespondentNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReceiversCorrespondentNameandAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReceiversCorrespondentNameandAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.756.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.756.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportAmendLCDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.707.ppSenderBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppSenderBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("21", fontFieldHeaders));
		if(columnMap.get("label.707.ppReceiverBanksReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppReceiverBanksReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReceiverBankReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReceiverBankReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("23", fontFieldHeaders));
		if(columnMap.get("label.707.ppIssuingBankReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppIssuingBankReference"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSenderBankReference()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSenderBankReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("52a", fontFieldHeaders));
		if(columnMap.get("label.707.ppIssuingBankPartyIdentifier")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppIssuingBankPartyIdentifier"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendersCorrespondentPartyIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendersCorrespondentPartyIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("52a", fontFieldHeaders));
		if(columnMap.get("label.707.ppIssuingBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppIssuingBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicantBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicantBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("52a", fontFieldHeaders));
		if(columnMap.get("label.707.ppIssuingBankNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppIssuingBankNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getIssunigBankNameAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getIssunigBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31C", fontFieldHeaders));
		if(columnMap.get("label.707.ppDateofIssue")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppDateofIssue"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getIssueDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getIssueDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("30", fontFieldHeaders));
		if(columnMap.get("label.707.ppDateofAmendment")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppDateofAmendment"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAmendmentDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getAmendmentDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("26E", fontFieldHeaders));
		if(columnMap.get("label.707.ppNumberofAmendment")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppNumberofAmendment"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAmndmntNo()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcAmndmntNo().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("59", fontFieldHeaders));
		if(columnMap.get("label.707.ppbeneficiaryNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppbeneficiaryNameAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getBeneficiaryNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getBeneficiaryNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31E", fontFieldHeaders));
		if(columnMap.get("label.707.ppNewDateofExpiry")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppNewDateofExpiry"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNewAmendExpiryDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getNewAmendExpiryDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.707.ppCurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppCurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMsgCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMsgCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.707.ppIncreaseofLCAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppIncreaseofLCAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getIncreaseAmendAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getIncreaseAmendAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.707.ppcurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppcurrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("33B", fontFieldHeaders));
		if(columnMap.get("label.707.ppDecreaseofLCAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppDecreaseofLCAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDecreaseAmendAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDecreaseAmendAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("34B", fontFieldHeaders));
		if(columnMap.get("label.707.ppCUrrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppCUrrency"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("34B", fontFieldHeaders));
		if(columnMap.get("label.707.ppNewLCAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppNewLCAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNewLcAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNewLcAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("39A", fontFieldHeaders));
		if(columnMap.get("label.707.pppositiveTolerance")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.pppositiveTolerance"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPositiveTolerance()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPositiveTolerance(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39A", fontFieldHeaders));
		if(columnMap.get("label.707.ppnegativeTolerance")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppnegativeTolerance"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNegativeTolerance()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNegativeTolerance(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39B", fontFieldHeaders));
		if(columnMap.get("label.707.ppmaximumCreditAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppmaximumCreditAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMaximumCreditAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMaximumCreditAmount(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39C", fontFieldHeaders));
		if(columnMap.get("label.707.ppAdditionalAmountsCovered")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppAdditionalAmountsCovered"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdditionalAmountsCovered()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdditionalAmountsCovered(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44A", fontFieldHeaders));
		if(columnMap.get("label.707.ppplaceofDispatch")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppplaceofDispatch"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getInitialDispatchPlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getInitialDispatchPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44E", fontFieldHeaders));
		if(columnMap.get("label.707.ppportofLoading")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppportofLoading"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getGoodsLoadingDispatchPlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getGoodsLoadingDispatchPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44F", fontFieldHeaders));
		if(columnMap.get("label.707.ppportofDischarge")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppportofDischarge"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getGoodsDestination()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getGoodsDestination(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44B", fontFieldHeaders));
		if(columnMap.get("label.707.ppfinalDeliveryPlace")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppfinalDeliveryPlace"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getFinalDeliveryPlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getFinalDeliveryPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44C", fontFieldHeaders));
		if(columnMap.get("label.707.pplatestDateofShipment")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.pplatestDateofShipment"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLatestDateofShipment()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getLatestDateofShipment().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44D", fontFieldHeaders));
		if(columnMap.get("label.707.ppShipmentPeriod")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppShipmentPeriod"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getShipmentPeriod()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getShipmentPeriod(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("79", fontFieldHeaders));
		if(columnMap.get("label.707.ppNarrative")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppNarrative"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNarrative()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNarrative(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.707.ppSendertoReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.707.ppSendertoReceiverInformation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportCreateBankGuaranteeCoverDataTable(BankGuaranteeCoverDto bankGuaranteeCoverDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("7020", fontFieldHeaders));
		if(columnMap.get("label.760COV.pptrxReferenceNo")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.pptrxReferenceNo"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7022", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbgFormNo")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbgFormNo"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgFormNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgFormNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7024", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbgType")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbgType"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgType()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgType(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7025", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppcurrency")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppcurrency"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getCurrency()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7025", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppamtofGuarantee")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppamtofGuarantee"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgAmount()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("7026", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbgFromDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbgFromDate"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgFromDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgFromDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7026", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbgToDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbgToDate"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgToDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgToDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7027", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbgGuaranteeEffDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbgGuaranteeEffDate"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgEffectiveDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgEffectiveDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7029", fontFieldHeaders));
		if(columnMap.get("label.760COV.pplodgementDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.pplodgementDate"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgLodgementEndDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgLodgementEndDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7030", fontFieldHeaders));
		if(columnMap.get("label.760COV.pplodgmentClaimPlace")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.pplodgmentClaimPlace"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgLodgementPalce()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgLodgementPalce(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7031", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppissuingBranchIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppissuingBranchIFSC"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgIssuingBankCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgIssuingBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7032", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppissuingBankNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppissuingBankNameandAddress"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getIssunigBankNameAndAddress()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getIssunigBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7033", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppapplicantNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppapplicantNameAddress"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgApplicentNameAndDetails()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgApplicentNameAndDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7034", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbenefibrname")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbenefibrname"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7035", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbenefifsc")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbenefifsc"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBeneficiaryBankCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBeneficiaryBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7036", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppbeneficiaryNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppbeneficiaryNameAddress"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7037", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppsenderToReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppsenderToReceiverInformation"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getSenderToReciverInformation()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getSenderToReciverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7038", fontFieldHeaders));
		if(columnMap.get("label.760COV.pppurposeofGuarantee")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.pppurposeofGuarantee"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgPurpose()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgPurpose(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7039", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppdescriptionoftheunderlinedcontract")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppdescriptionoftheunderlinedcontract"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7040", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppelectronicallyPaid")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppelectronicallyPaid"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getStampDutyPaid()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getStampDutyPaid(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7041", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppeStampCertificateNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppeStampCertificateNumber"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getStampCertificateNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getStampCertificateNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7042", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppeStamdateTime")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppeStamdateTime"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getStampDateAndTime()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getStampDateAndTime().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7043", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppamountpaid")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppamountpaid"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgAmountPaid()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgAmountPaid().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("7044", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppstatecode")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppstatecode"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgStateCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgStateCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7045", fontFieldHeaders));
		if(columnMap.get("label.760COV.pparticleno")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.pparticleno"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgArticleNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgArticleNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7046", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppdatetofpayment")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppdatetofpayment"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgPaymentDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgPaymentDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7047", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppplaceofpay")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppplaceofpay"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgPaymentPlace()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgPaymentPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7048", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppebginDematForm")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppebginDematForm"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgHeldDematForm()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgHeldDematForm(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7050", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppcustodianServiceProvider")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppcustodianServiceProvider"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getCustodianServiceProvider()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getCustodianServiceProvider(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7049", fontFieldHeaders));
		if(columnMap.get("label.760COV.ppdemataccno")!=null){
			table.addCell(new Phrase(columnMap.get("label.760COV.ppdemataccno"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getDematAccNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getDematAccNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportCreateAmendBGCoverDataTable(BankGuaranteeCoverDto bankGuaranteeCoverDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("7020", fontFieldHeaders));
		if(columnMap.get("label.767COV.pptrxReferenceNo")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.pptrxReferenceNo"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7021", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppbgrelatedReference")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppbgrelatedReference"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgRelatedReference()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgRelatedReference(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7055", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppFurtherIdentification")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppFurtherIdentification"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgFurtherIdentification()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgFurtherIdentification(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7056", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppAmendmentDate")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppAmendmentDate"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgAmendmentDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgAmendmentDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7057", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppNumberofAmendment")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppNumberofAmendment"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgNoofAmendments()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgNoofAmendments().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7058", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppDateofAmendment")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppDateofAmendment"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgIssueDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgIssueDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7059", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppbgDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppbgDetails"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgAmedmentDetails()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgAmedmentDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7037", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppsenderToReceiverInformation")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppsenderToReceiverInformation"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getSenderToReciverInformation()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getSenderToReciverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7031", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppissuingBranchIFSC")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppissuingBranchIFSC"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgIssuingBankCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgIssuingBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7032", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppissuingBankNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppissuingBankNameandAddress"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getIssunigBankNameAndAddress()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getIssunigBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7033", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppapplicantNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppapplicantNameAddress"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgApplicentNameAndDetails()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgApplicentNameAndDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7034", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppbenefibrname")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppbenefibrname"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7035", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppbenefifsc")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppbenefifsc"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBeneficiaryBankCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBeneficiaryBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7036", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppbeneficiaryBranchAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppbeneficiaryBranchAddress"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7040", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppelectronicallyPaid")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppelectronicallyPaid"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getStampDutyPaid()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getStampDutyPaid(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7041", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppeStampCertificateNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppeStampCertificateNumber"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getStampCertificateNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getStampCertificateNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7042", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppeStamdateTime")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppeStamdateTime"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgIssueDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgIssueDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7043", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppamountpaid")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppamountpaid"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgAmountPaid()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgAmountPaid().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("7044", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppstatecode")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppstatecode"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgStateCode()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgStateCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7045", fontFieldHeaders));
		if(columnMap.get("label.767COV.pparticleno")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.pparticleno"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgArticleNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgArticleNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7046", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppdatetofpayment")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppdatetofpayment"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgIssueDate()!=null){
			table.addCell(new Phrase(sm.format(bankGuaranteeCoverDto.getBgIssueDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7047", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppplaceofpay")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppplaceofpay"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgPaymentPlace()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgPaymentPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7048", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppebginDematForm")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppebginDematForm"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getBgHeldDematForm()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getBgHeldDematForm(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7050", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppCustodianServiceProvider")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppCustodianServiceProvider"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getCustodianServiceProvider()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getCustodianServiceProvider(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("7049", fontFieldHeaders));
		if(columnMap.get("label.767COV.ppdemataccno")!=null){
			table.addCell(new Phrase(columnMap.get("label.767COV.ppdemataccno"), fontFieldHeaders));
		}
		if(bankGuaranteeCoverDto.getDematAccNumber()!=null){
			table.addCell(new Phrase(bankGuaranteeCoverDto.getDematAccNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	}
	
	public PdfPTable getReportLcOpenDataTable(LCCanonicalDto lcCanonicalDto, Map<String,String> columnMap)throws DocumentException
	{
		PdfPTable table = new PdfPTable(3);
		
		Font fontFieldHeaders = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		table.getDefaultCell().setColspan(1); 
		table.getDefaultCell().setBorderWidth(0.2f);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Font fontStyleCells = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		float [] widths ={2f,9f,17f};
		table.setWidths(widths); 
		table.setWidthPercentage(90);
		
		
			
		table.addCell(new Phrase("27", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppseqofTotal")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppseqofTotal"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSequence()!=null || lcCanonicalDto.getSequenceofTotal()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSequence()+ " / " +lcCanonicalDto.getSequenceofTotal(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("40A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppformofDoc")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppformofDoc"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcType()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcType(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("20", fontFieldHeaders));
		if(columnMap.get("label.700XXX.pplcNumber")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.pplcNumber"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcNumber()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcNumber(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("23", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppreferenceToPreadvice")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppreferenceToPreadvice"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcPresdvice()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcPresdvice(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31C", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdateofIssue")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdateofIssue"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getIssueDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getIssueDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("40E", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppapplicableRules")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppapplicableRules"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicableRule()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicableRule(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdateofExp")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdateofExp"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcExpiryDate()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getLcExpiryDate().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("31D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppplaceofExp")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppplaceofExp"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcExpirePlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcExpirePlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("51a", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppapplicentBank")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppapplicentBank"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicentIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicentIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("51A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppapplicentBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppapplicentBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicantBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicantBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("51D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppapplicentBankNameAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppapplicentBankNameAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicentBankNameandAddr()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicentBankNameandAddr(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("50", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppapplicent")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppapplicent"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getApplicantNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getApplicantNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("59", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppbeneficiaryNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppbeneficiaryNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getBeneficiaryNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getBeneficiaryNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppcurreyCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppcurreyCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcCurrency()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcCurrency(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("32B", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppamount")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppamount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLcAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getLcAmount().toString(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase("0.00", fontStyleCells));
		}
		table.addCell(new Phrase("39A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.pppositiveTolerance")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.pppositiveTolerance"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPositiveTolerance()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPositiveTolerance(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppnegativeTolerance")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppnegativeTolerance"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getNegativeTolerance()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getNegativeTolerance(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39B", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppmaximumCreditAmount")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppmaximumCreditAmount"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMaximumCreditAmount()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMaximumCreditAmount(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("39C", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppadditinalAmtCoverd")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppadditinalAmtCoverd"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdditionalAmounts()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdditionalAmounts(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41a", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppavailable")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppavailable"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAvailableIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAvailableIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppavailableBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppavailableBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAuthorisedBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAuthorisedBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41a", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppavailableCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppavailableCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAuthorisationMode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAuthorisationMode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("41D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppavailableBankNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppavailableBankNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAuthorisedAndAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAuthorisedAndAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42C", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdraftsAt")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdraftsAt"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraftsAt()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraftsAt(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42a", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppDrawee")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppDrawee"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraweeIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraweeIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdraweeBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdraweeBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraweeBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraweeBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdreaweeNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdreaweeNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDraweeBankNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDraweeBankNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42M", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppmixedPaymnetDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppmixedPaymnetDetails"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getMixedPaymentDetails()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getMixedPaymentDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("42P", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdeferredPaymentDetails")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdeferredPaymentDetails"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDeferredPaymentDetails()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDeferredPaymentDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("43P", fontFieldHeaders));
		if(columnMap.get("label.700XXX.pppartialPayments")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.pppartialPayments"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPartialShipments()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPartialShipments(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("43T", fontFieldHeaders));
		if(columnMap.get("label.700XXX.pptransshipment")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.pptransshipment"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getTranshipment()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getTranshipment(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppplaceofDispatch")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppplaceofDispatch"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getInitialDispatchPlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getInitialDispatchPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44E", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppportofLoading")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppportofLoading"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getGoodsLoadingDispatchPlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getGoodsLoadingDispatchPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44F", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppportofDischarge")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppportofDischarge"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getGoodsDestination()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getGoodsDestination(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44B", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppplaceofFinalDestination")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppplaceofFinalDestination"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getFinalDeliveryPlace()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getFinalDeliveryPlace(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44C", fontFieldHeaders));
		if(columnMap.get("label.700XXX.pplatestDateofShipment")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.pplatestDateofShipment"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getLatestDateofShipment()!=null){
			table.addCell(new Phrase(sm.format(lcCanonicalDto.getLatestDateofShipment().getTime()), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("44D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppshipmentPeriod")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppshipmentPeriod"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getShipmentPeriod()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getShipmentPeriod(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("45A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdescriptionofGoods")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdescriptionofGoods"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDescriptionofGoods()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDescriptionofGoods(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("46A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppdocumentsRequired")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppdocumentsRequired"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getDocumentsRequired()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getDocumentsRequired(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("47A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppadditinalConditions")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppadditinalConditions"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdditionalConditions()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdditionalConditions(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("71B", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppcharges")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppcharges"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getChargeDetails()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getChargeDetails(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("48", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppperiodForPresentaion")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppperiodForPresentaion"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getPeriodforPresentation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getPeriodforPresentation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("49", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppconfirmation")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppconfirmation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getConfirmationCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getConfirmationCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53a", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppreimbursing")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppreimbursing"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppreimbursingBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppreimbursingBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("53D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppreimbursingNameandAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppreimbursingNameandAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getReimbursingBankNameAddress()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getReimbursingBankNameAddress(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("78", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppinstructionToPaying")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppinstructionToPaying"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getInstructionstoPayingBank()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getInstructionstoPayingBank(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57a", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppadvisingThrough")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppadvisingThrough"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdvisingIdentifier()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdvisingIdentifier(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57A", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppadvisingThroughBankCode")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppadvisingThroughBankCode"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankCode()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankCode(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57B", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppadvisingThroughBankLocation")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppadvisingThroughBankLocation"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankLocation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankLocation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("57D", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppadvisingThroughBankNameAndAddress")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppadvisingThroughBankNameAndAddress"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getAdviseThroughBankName()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getAdviseThroughBankName(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell(new Phrase("72", fontFieldHeaders));
		if(columnMap.get("label.700XXX.ppsenderToReceiverInfo")!=null){
			table.addCell(new Phrase(columnMap.get("label.700XXX.ppsenderToReceiverInfo"), fontFieldHeaders));
		}
		if(lcCanonicalDto.getSendertoReceiverInformation()!=null){
			table.addCell(new Phrase(lcCanonicalDto.getSendertoReceiverInformation(), fontStyleCells));
		}
		else
		{
			table.addCell(new Phrase(""));
		}
		table.addCell("\n");
		return table;
	
	
	}
	
	
}
