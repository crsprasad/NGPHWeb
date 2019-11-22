package com.logica.ngph.web.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;

import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.LCCanonicalDto;


/**
 * @author chakkar
 *
 */
public class ExportToExcelUtil {
	
	
	 private InputStream inputStream;
	 
	/*
	 * This method is for exporting Authorized payment screen data to Excel document
	 */
	public HSSFWorkbook generateExportToExcel(LCCanonicalDto lcCanonicalDto, CreateBankGuaranteeDto createBankGuaranteeDto, BankGuaranteeCoverDto bankGuaranteeCoverDto, String reportFile, int msgType, String msgSubType)throws Exception 
	{
		HSSFWorkbook myWorkBook = new HSSFWorkbook();
		HSSFSheet mySheet = myWorkBook.createSheet(reportFile);
		
			try
			{
				mySheet.setColumnWidth(0, 11000);
				mySheet.setColumnWidth(1, 12000);
				mySheet.setColumnWidth(2, 11000);
				mySheet.setColumnWidth(3, 12000);
				
				
				//Title Font Style 
				HSSFFont font = myWorkBook.createFont();
				font.setFontHeightInPoints((short)11);
				font.setFontName("Arial");
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.BROWN.index);
				HSSFCellStyle style = myWorkBook.createCellStyle();
			    style.setFont(font);
			    
			  //Header details
				Row titleRow = mySheet.createRow(1);
				 Cell title = titleRow.createCell(1);
				 title.setCellValue(reportFile);
				 title.setCellStyle(style);
				 style.setWrapText(true); 
				 style.setWrapText(true);
				 
				//BodyDetails Font Style 
					HSSFFont bodyFont = myWorkBook.createFont();
					bodyFont.setFontHeightInPoints((short)10);
					bodyFont.setFontName("Arial");
					bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					bodyFont.setColor(HSSFColor.BROWN.index);
					HSSFCellStyle bodyStyle = myWorkBook.createCellStyle();
					bodyStyle.setFont(bodyFont);
					
					 Row bodyDetails = mySheet.createRow(9);
					 Cell bodyTitle = bodyDetails.createCell(0);
					 bodyTitle.setCellValue("Message Details");
					 bodyTitle.setCellStyle(bodyStyle);
					 bodyStyle.setWrapText(true);
				 
				 switch (msgType) {
		            
				    case 700: 
         			           myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,700,"XXX");
         			           myWorkBook = callExportToExcelLCOpen(lcCanonicalDto, myWorkBook, mySheet);
                  	           break;
		            case 707: 
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,707,"XXX");
		            			myWorkBook = callExportToExcelAmendLC(lcCanonicalDto, myWorkBook, mySheet);
		                     	break;
		            case 730:  
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,730,"XXX");
		            			myWorkBook = callExportToExcelLcAck(lcCanonicalDto, myWorkBook, mySheet);
		            			break;
		            case 734:  
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,734,"XXX");
		            			myWorkBook = callExportToExcelAdviceofRefusal(lcCanonicalDto, myWorkBook, mySheet);
		            			break;
		            case 5:  
		                     	break;
		            case 6:  
		                     	break;
		            case 7:  
		                     	break;
		            case 740:  
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,740,"XXX");
		            			myWorkBook = callExportToExcelAuthoriseLCPaymentAdvice(lcCanonicalDto, myWorkBook, mySheet);
		                     	break;
		            case 750: 
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,750,"XXX");
		            			myWorkBook = callExportToExcelAdviceofDiscrepancy(lcCanonicalDto, myWorkBook, mySheet);
		                     	break;
		            case 754:
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,754,"XXX");
		            			myWorkBook = callExportToExcelAdviceofPayment(lcCanonicalDto, myWorkBook, mySheet);
		                     	break;
		            case 756: 
		            			myWorkBook = callLCHearderMsgDetails(lcCanonicalDto, myWorkBook, mySheet,756,"XXX");
		            			myWorkBook = callExportToExcelAdviceLCPayment(lcCanonicalDto, myWorkBook, mySheet);
		                     	break;
		            case 760: if(msgSubType.equalsIgnoreCase("XXX"))
		            			{
		            				myWorkBook = callBGHeaderMsgDetails(createBankGuaranteeDto, myWorkBook, mySheet,760,"XXX");
		            				myWorkBook = callExportToExcelCreateBankGuarantee(createBankGuaranteeDto, myWorkBook, mySheet);
		            			}
		            			else
		            			{
		            				myWorkBook = callBGCovHeaderMsgDetails(bankGuaranteeCoverDto, myWorkBook, mySheet,760,"COV");
		            				myWorkBook = callExportToExcelCreateBankGuaranteeCover(bankGuaranteeCoverDto, myWorkBook, mySheet);
		            			}
		            			break;
		            case 767: if(msgSubType.equalsIgnoreCase("XXX"))
        						{
		            				myWorkBook = callBGHeaderMsgDetails(createBankGuaranteeDto, myWorkBook, mySheet,767,"XXX");
		            				myWorkBook = callExportToExcelAmendBG(createBankGuaranteeDto, myWorkBook, mySheet);
        						}
        						else
        						{
    		            			myWorkBook = callBGCovHeaderMsgDetails(bankGuaranteeCoverDto, myWorkBook, mySheet,767,"COV");
    		            			myWorkBook = callExportToExcelCreateAmendBGCover(bankGuaranteeCoverDto, myWorkBook, mySheet);
        						}
	                     		break;
		            case 768: 
		            			myWorkBook = callBGHeaderMsgDetails(createBankGuaranteeDto, myWorkBook, mySheet,768,"XXX");
		            			myWorkBook = callExportToExcelAckBG(createBankGuaranteeDto, myWorkBook, mySheet);
		            			break;
		            case 769: 
		            			myWorkBook = callBGHeaderMsgDetails(createBankGuaranteeDto, myWorkBook, mySheet,769,"XXX");
		            			myWorkBook = callExportToExcelAdviceofReduction(createBankGuaranteeDto, myWorkBook, mySheet);
	                     		break;
		            case 799: 
		            			myWorkBook = callBGHeaderMsgDetails(createBankGuaranteeDto, myWorkBook, mySheet,799,"XXX");
		            			myWorkBook = callExportToExcelFreeFormat(createBankGuaranteeDto, myWorkBook, mySheet);
	                     		break;
		            default: 
		                     	break;
				 }
				
				 try {
					    ByteArrayOutputStream boas = new ByteArrayOutputStream();
					    myWorkBook.write(boas);
					    setInputStream(new ByteArrayInputStream(boas.toByteArray()));
					   } catch (IOException e) {
					    e.printStackTrace();
					   }
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return myWorkBook;
		
	
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public HSSFWorkbook callLCHearderMsgDetails(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet, int msgType, String msgSubType)
	{
		//HeaderDetails Font Style 
		HSSFFont headerFont = myWorkBook.createFont();
		headerFont.setFontHeightInPoints((short)10);
		headerFont.setFontName("Arial");
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.BROWN.index);
		HSSFCellStyle headerStyle = myWorkBook.createCellStyle();
		headerStyle.setFont(headerFont);
		 
		 Row headerDetails = mySheet.createRow(3);
		 Cell header = headerDetails.createCell(0);
		 header.setCellValue("Basic Details");
		 header.setCellStyle(headerStyle);
		 headerStyle.setWrapText(true);
		 
		//Message Type details
		 CellStyle cellStyle =  myWorkBook.createCellStyle();
		 cellStyle.setWrapText(true);
		 Row messageTypeRow = mySheet.createRow(5);
		 Cell msgTypeLabel = messageTypeRow.createCell(0);
		 msgTypeLabel.setCellValue("Message Type");
		 Cell msgTypeData = messageTypeRow.createCell(1);
		 msgTypeData.setCellStyle(cellStyle);
		 msgTypeData.setCellValue(msgType);
		 cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		 
		 Cell subMsgTypeLabel = messageTypeRow.createCell(2);
		 subMsgTypeLabel.setCellValue("Sub Message Type");
		 Cell subMsgTypeData = messageTypeRow.createCell(3);
		 subMsgTypeData.setCellStyle(cellStyle);
		 subMsgTypeData.setCellValue(msgSubType);
		 
		
		Row headerRow = mySheet.createRow(6);
		 Cell senderBankLabel = headerRow.createCell(0);
		 senderBankLabel.setCellValue("Sender Bank IFSC");
		 Cell receiverBankLabel = headerRow.createCell(2);
		 receiverBankLabel.setCellValue("Receiver Bank IFSC");
		 
		if(lcCanonicalDto.getIssuingBankCode()!=null && !lcCanonicalDto.getIssuingBankCode().isEmpty())
		 {	 
			 Cell senderBankData = headerRow.createCell(1);
			 senderBankData.setCellStyle(cellStyle);
			 senderBankData.setCellValue(lcCanonicalDto.getIssuingBankCode());
		 }
		 
		 if(lcCanonicalDto.getAdvisingBank()!=null && !lcCanonicalDto.getAdvisingBank().isEmpty())
		 {
			 Cell receiverBankData = headerRow.createCell(3);
			 receiverBankData.setCellStyle(cellStyle);
			 receiverBankData.setCellValue(lcCanonicalDto.getAdvisingBank());
		 }
		 

		 return myWorkBook;
	}

	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelLCOpen for 700 message 
	public HSSFWorkbook callExportToExcelLCOpen(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
						Row row11 = mySheet.createRow(11);
						Row row12 = mySheet.createRow(12);
						Row row13 = mySheet.createRow(13);
						Row row14 = mySheet.createRow(14);
						Row row15 = mySheet.createRow(15);
						Row row16 = mySheet.createRow(16);
						Row row17 = mySheet.createRow(17);
						Row row18 = mySheet.createRow(18);
						Row row19 = mySheet.createRow(19);
						Row row20 = mySheet.createRow(20);
						Row row21 = mySheet.createRow(21);
						Row row22 = mySheet.createRow(22);
						Row row23 = mySheet.createRow(23);
						Row row24 = mySheet.createRow(24);
						Row row25 = mySheet.createRow(25);
						Row row26 = mySheet.createRow(26);
						Row row27 = mySheet.createRow(27);
						Row row28 = mySheet.createRow(28);
						Row row29 = mySheet.createRow(29);
						Row row30 = mySheet.createRow(30);
						Row row31 = mySheet.createRow(31);
						Row row32 = mySheet.createRow(32);
						Row row33 = mySheet.createRow(33);
						Row row34 = mySheet.createRow(34);
						Row row35 = mySheet.createRow(35);
						Row row36 = mySheet.createRow(36);
						Row row37 = mySheet.createRow(37);
						Row row38 = mySheet.createRow(38);
						Row row39 = mySheet.createRow(39);
						Row row40 = mySheet.createRow(40);
						Row row41 = mySheet.createRow(41);
						Row row42 = mySheet.createRow(42);
						Row row43 = mySheet.createRow(43);
						Row row44 = mySheet.createRow(44);
						Row row45 = mySheet.createRow(45);
						Row row46 = mySheet.createRow(46);
						Row row47 = mySheet.createRow(47);
						Row row48 = mySheet.createRow(48);
						Row row49 = mySheet.createRow(49);
						Row row50 = mySheet.createRow(50);
						Row row51 = mySheet.createRow(51);
						Row row52 = mySheet.createRow(52);
						Row row53 = mySheet.createRow(53);
						Row row54 = mySheet.createRow(54);
						Row row55 = mySheet.createRow(55);
						Row row56 = mySheet.createRow(56);
						Row row57 = mySheet.createRow(57);
						Row row58 = mySheet.createRow(58);
						Row row59 = mySheet.createRow(59);
						Row row60 = mySheet.createRow(60);
						Row row61 = mySheet.createRow(61);
						Row row62 = mySheet.createRow(62);
						
						
						Cell datarow11Label1 = row11.createCell(0);
						datarow11Label1.setCellValue("Sequence of Total(27)");
						Cell datarow12Label1 = row12.createCell(0);
						datarow12Label1.setCellValue("Form of Documentary Credit(40A)");
						Cell datarow13Label1 = row13.createCell(0);
						datarow13Label1.setCellValue("Documentary Credit Number(20)");
						Cell datarow14Label1 = row14.createCell(0);
						datarow14Label1.setCellValue("Reference to Pre-Advice(23)");
						Cell datarow15Label1 = row15.createCell(0);
						datarow15Label1.setCellValue("Date of Issue(31C)");
						Cell datarow16Label1 = row16.createCell(0);
						datarow16Label1.setCellValue("Applicable Rules(40E)");
						Cell datarow17Label1 = row17.createCell(0);
						datarow17Label1.setCellValue("Date of Expiry(31D)");
						Cell datarow18Label1 = row18.createCell(0);
						datarow18Label1.setCellValue("Place of Expiry(31D)");
						Cell datarow19Label1 = row19.createCell(0);
						datarow19Label1.setCellValue("Applicent Bank(51a)");
						Cell datarow20Label1 = row20.createCell(0);
						datarow20Label1.setCellValue("Applicent Bank Code(51A)");
						Cell datarow21Label1 = row21.createCell(0);
						datarow21Label1.setCellValue("Applicent Bank Name and Address(51D)");
						Cell datarow22Label1 = row22.createCell(0);
						datarow22Label1.setCellValue("Applicent(50)");
						Cell datarow23Label1 = row23.createCell(0);
						datarow23Label1.setCellValue("Beneficiary Name and Address(59)");
						Cell datarow24Label1 = row24.createCell(0);
						datarow24Label1.setCellValue("Currency Code(32B)");
						Cell datarow25Label1 = row25.createCell(0);
						datarow25Label1.setCellValue("Amount(32B)");
						Cell datarow26Label1 = row26.createCell(0);
						datarow26Label1.setCellValue("Positive Tolerance(39A)");
						Cell datarow27Label1 = row27.createCell(0);
						datarow27Label1.setCellValue("Negative Tolerance(39A)");
						Cell datarow28Label1 = row28.createCell(0);
						datarow28Label1.setCellValue("Maximum Credit Amount(39B)");
						Cell datarow29Label1 = row29.createCell(0);
						datarow29Label1.setCellValue("Additional Amounts Covered(39C)");
						Cell datarow30Label1 = row30.createCell(0);
						datarow30Label1.setCellValue("Available With(41a)");
						Cell datarow31Label1 = row31.createCell(0);
						datarow31Label1.setCellValue("Available Bank Code(41A)");
						Cell datarow32Label1 = row32.createCell(0);
						datarow32Label1.setCellValue("Available Code(41a)");
						Cell datarow33Label1 = row33.createCell(0);
						datarow33Label1.setCellValue("Available Bank Name and Address(41D)");
						Cell datarow34Label1 = row34.createCell(0);
						datarow34Label1.setCellValue("Drafts At(42C)");
						Cell datarow35Label1 = row35.createCell(0);
						datarow35Label1.setCellValue("Drawee(42a)");
						Cell datarow36Label1 = row36.createCell(0);
						datarow36Label1.setCellValue("Drawee Bank Code(42A)");
						Cell datarow37Label1 = row37.createCell(0);
						datarow37Label1.setCellValue("Drawee Name and Address(42D)");
						Cell datarow38Label1 = row38.createCell(0);
						datarow38Label1.setCellValue("Mixed Payment Details(42M)");
						Cell datarow39Label1 = row39.createCell(0);
						datarow39Label1.setCellValue("Deferred Payment Details(42P)");
						Cell datarow40Label1 = row40.createCell(0);
						datarow40Label1.setCellValue("Partial Shipments(43P)");
						Cell datarow41Label1 = row41.createCell(0);
						datarow41Label1.setCellValue("Transshipment(43T)");
						Cell datarow42Label1 = row42.createCell(0);
						datarow42Label1.setCellValue("Place of Taking in Charge/Dispatch from .../Place of Receipt(44A)");
						Cell datarow43Label1 = row43.createCell(0);
						datarow43Label1.setCellValue("Port of Loading/Airport of Departure(44E)");
						Cell datarow44Label1 = row44.createCell(0);
						datarow44Label1.setCellValue("Port of Discharge/Airport of Destination(44F)");
						Cell datarow45Label1 = row45.createCell(0);
						datarow45Label1.setCellValue("Place of Final Destination/For Transportation to.../Place of Delivery(44B)");
						Cell datarow46Label1 = row46.createCell(0);
						datarow46Label1.setCellValue("Latest Date of Shipment(44C)");
						Cell datarow47Label1 = row47.createCell(0);
						datarow47Label1.setCellValue("Shipment Period(44D)");
						Cell datarow48Label1 = row48.createCell(0);
						datarow48Label1.setCellValue("Description of Goods and/or Services(45A)");
						Cell datarow49Label1 = row49.createCell(0);
						datarow49Label1.setCellValue("Documents Required(46A)");
						Cell datarow50Label1 = row50.createCell(0);
						datarow50Label1.setCellValue("Additional Conditions(47A)");
						Cell datarow51Label1 = row51.createCell(0);
						datarow51Label1.setCellValue("Charges(71B)");
						Cell datarow52Label1 = row52.createCell(0);
						datarow52Label1.setCellValue("Period for Presentation(48)");
						Cell datarow53Label1 = row53.createCell(0);
						datarow53Label1.setCellValue("Confirmation Instructions(49)");
						Cell datarow54Label1 = row54.createCell(0);
						datarow54Label1.setCellValue("Reimbursing Bank(53a)");
						Cell datarow55Label1 = row55.createCell(0);
						datarow55Label1.setCellValue("Reimbursing Bank Code(53A)");
						Cell datarow56Label1 = row56.createCell(0);
						datarow56Label1.setCellValue("Reimbursing Name and Address(53D)");
						Cell datarow57Label1 = row57.createCell(0);
						datarow57Label1.setCellValue("Instructions to the Paying/Accepting/Negotiating Bank(78)");
						Cell datarow58Label1 = row58.createCell(0);
						datarow58Label1.setCellValue("Advise Through Bank(57a)");
						Cell datarow59Label1 = row59.createCell(0);
						datarow59Label1.setCellValue("Advising Bank Code(57A)");
						Cell datarow60Label1 = row60.createCell(0);
						datarow60Label1.setCellValue("Advising Bank Location(57B)");
						Cell datarow61Label1 = row61.createCell(0);
						datarow61Label1.setCellValue("Advising Bank Name and Address(57D)");
						Cell datarow62Label1 = row62.createCell(0);
						datarow62Label1.setCellValue("Sender To Receiver Information(72)");
						
						CellStyle cellStyle =  myWorkBook.createCellStyle();
						cellStyle.setWrapText(true);
						CellStyle datecellStyle =  myWorkBook.createCellStyle();
						datecellStyle.setWrapText(true);
						CellStyle amountcellStyle =  myWorkBook.createCellStyle();
						
						if(lcCanonicalDto.getSequence()!=null && lcCanonicalDto.getSequenceofTotal()!=null)
						 {
							 Cell data1 = row11.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getSequence()+ " / " +lcCanonicalDto.getSequenceofTotal());				 
						 }
						 if(lcCanonicalDto.getLcType()!=null)
						 {
							 Cell data1 = row12.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getLcType());
						 }
						 if(lcCanonicalDto.getLcNumber()!=null)
						 {
							 Cell data1 = row13.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getLcNumber());
						 }
						 if(lcCanonicalDto.getLcPresdvice()!=null)
						 {
							 Cell data1 = row14.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getLcPresdvice());
						 }
						 if(lcCanonicalDto.getIssueDate()!=null)
						 {
							 Cell data1 = row15.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 Date amountPaidDate = lcCanonicalDto.getIssueDate();
							 CreationHelper createHelper = myWorkBook.getCreationHelper();
							 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
							 datecellStyle.setDataFormat(dateFormat);
							 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
							 data1.setCellValue(amountPaidDate);
						 }
						 if(lcCanonicalDto.getApplicableRule()!=null)
						 {
							 Cell data1 = row16.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getApplicableRule());
						 }
						 if(lcCanonicalDto.getLcExpiryDate()!=null)
						 {
							 Cell data1 = row17.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 Date amountPaidDate = lcCanonicalDto.getLcExpiryDate();
							 CreationHelper createHelper = myWorkBook.getCreationHelper();
							 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
							 datecellStyle.setDataFormat(dateFormat);
							 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
							 data1.setCellValue(amountPaidDate);
						 }
						 if(lcCanonicalDto.getLcExpirePlace()!=null)
						 {
							 Cell data1 = row18.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getLcExpirePlace());
						 }	
						 if(lcCanonicalDto.getApplicentIdentifier()!=null)
						 {
							 Cell data1 = row19.createCell(1);
							 data1.setCellValue(lcCanonicalDto.getApplicentIdentifier());
						 }
						 if(lcCanonicalDto.getApplicantBankCode()!=null)
						 {
							 Cell data1 = row20.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getApplicantBankCode());
						 }
						 if(lcCanonicalDto.getApplicentBankNameandAddr()!=null)
						 {
							 Cell data1 = row21.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getApplicentBankNameandAddr());
						 } 
						 if(lcCanonicalDto.getApplicantNameAddress()!=null)
						 {
							 Cell data1 = row22.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getApplicantNameAddress());
						 } 
						 if(lcCanonicalDto.getBeneficiaryNameAddress()!=null)
						 {
							 Cell data1 = row23.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getBeneficiaryNameAddress());
						 }
						 if(lcCanonicalDto.getLcCurrency()!=null)
						 {
							 Cell data1 = row24.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getLcCurrency());
						 }
						 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
						 Cell data2 = row25.createCell(1); 
						 if (!String.valueOf(lcCanonicalDto.getLcAmount().doubleValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getLcAmount())==null)) 
						 {
							 data2.setCellValue(lcCanonicalDto.getLcAmount().intValue());
						 }
						 else
						 {
							 data2.setCellValue(BigDecimal.ZERO.intValue());
						 }
						 if(lcCanonicalDto.getPositiveTolerance()!=null)
						 {
							 Cell data1 = row26.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getPositiveTolerance());
						 }
						 if(lcCanonicalDto.getNegativeTolerance()!=null)
						 {
							 Cell data1 = row27.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getNegativeTolerance());
						 }
						 if(lcCanonicalDto.getMaximumCreditAmount()!=null)
						 {
							 Cell data1 = row28.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getMaximumCreditAmount());
						 }
						 if(lcCanonicalDto.getAdditionalAmounts()!=null)
						 {
							 Cell data1 = row29.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAdditionalAmounts());
						 }
						 if(lcCanonicalDto.getAvailableIdentifier()!=null)
						 {
							 Cell data1 = row30.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAvailableIdentifier());
						 }
						 if(lcCanonicalDto.getAuthorisedBankCode()!=null)
						 {
							 Cell data1 = row31.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAuthorisedBankCode());
						 }
						 if(lcCanonicalDto.getAuthorisationMode()!=null)
						 {
							 Cell data1 = row32.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAuthorisationMode());
						 }
						 if(lcCanonicalDto.getAuthorisedAndAddress()!=null)
						 {
							 Cell data1 = row33.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAuthorisedAndAddress());
						 }
						 if(lcCanonicalDto.getDraftsAt()!=null)
						 {
							 Cell data1 = row34.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDraftsAt());
						 }
						 if(lcCanonicalDto.getDraweeIdentifier()!=null)
						 {
							 Cell data1 = row35.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDraweeIdentifier());
						 }
						 if(lcCanonicalDto.getDraweeBankCode()!=null)
						 {
							 Cell data1 = row36.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDraweeBankCode());
						 }
						 if(lcCanonicalDto.getDraweeBankNameAddress()!=null)
						 {
							 Cell data1 = row37.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDraweeBankNameAddress());
					     }
						 if(lcCanonicalDto.getMixedPaymentDetails()!=null)
						 {
							 Cell data1 = row38.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getMixedPaymentDetails());
						 }
						 if(lcCanonicalDto.getDeferredPaymentDetails()!=null)
						 {
							 Cell data1 = row39.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDeferredPaymentDetails());
						 }
						 if(lcCanonicalDto.getPartialShipments()!=null)
						 {
							 Cell data1 = row40.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getPartialShipments());
						 }
						 if(lcCanonicalDto.getTranshipment()!=null)
						 {
							 Cell data1 = row41.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getTranshipment());
						 }
						 if(lcCanonicalDto.getInitialDispatchPlace()!=null)
						 {
							 Cell data1 = row42.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getInitialDispatchPlace());
						 }
						 if(lcCanonicalDto.getGoodsLoadingDispatchPlace()!=null)
						 {
							 Cell data1 = row43.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getGoodsLoadingDispatchPlace());
						 }
						 if(lcCanonicalDto.getGoodsDestination()!=null)
						 {
							 Cell data1 = row44.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getGoodsDestination());
						 }
						 if(lcCanonicalDto.getFinalDeliveryPlace()!=null)
						 {
							 Cell data1 = row45.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getFinalDeliveryPlace());
						 }
						 if(lcCanonicalDto.getLatestDateofShipment()!=null)
						 {
							 Cell data1 = row46.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 Date amountPaidDate = lcCanonicalDto.getLatestDateofShipment();
							 CreationHelper createHelper = myWorkBook.getCreationHelper();
							 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
							 datecellStyle.setDataFormat(dateFormat);
							 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
							 data1.setCellValue(amountPaidDate);
						 }
						 if(lcCanonicalDto.getShipmentPeriod()!=null)
						 {
							 Cell data1 = row47.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getShipmentPeriod());
						 }
						 if(lcCanonicalDto.getDescriptionofGoods()!=null)
						 {
							 Cell data1 = row48.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDescriptionofGoods());
						 }
						 if(lcCanonicalDto.getDocumentsRequired()!=null)
						 {
							 Cell data1 = row49.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getDocumentsRequired());
						 }
						 if(lcCanonicalDto.getAdditionalConditions()!=null)
						 {
							 Cell data1 = row50.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAdditionalConditions());
					     }
						 if(lcCanonicalDto.getChargeDetails()!=null)
						 {
							 Cell data1 = row51.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getChargeDetails());
						 }
						 if(lcCanonicalDto.getPeriodforPresentation()!=null)
						 {
							 Cell data1 = row52.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getPeriodforPresentation());
						}
						 if(lcCanonicalDto.getConfirmationCode()!=null)
						 {
							 Cell data1 = row53.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getConfirmationCode());
						 }
						 if(lcCanonicalDto.getReimbursingIdentifier()!=null)
						 {
							 Cell data1 = row54.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getReimbursingIdentifier());
						}
						 if(lcCanonicalDto.getReimbursingBankCode()!=null)
						 {
							 Cell data1 = row55.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getReimbursingBankCode());
						 }
						 if(lcCanonicalDto.getReimbursingBankNameAddress()!=null)
						 {
							 Cell data1 = row56.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getReimbursingBankNameAddress());
						 }
						 if(lcCanonicalDto.getInstructionstoPayingBank()!=null)
						 {
							 Cell data1 = row57.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getInstructionstoPayingBank());
						 }
						 if(lcCanonicalDto.getAdvisingIdentifier()!=null)
						 {
							 Cell data1 = row58.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAdvisingIdentifier());
						 }
						 if(lcCanonicalDto.getAdviseThroughBankCode()!=null)
						 {
							 Cell data1 = row59.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankCode());
						 }
						 if(lcCanonicalDto.getAdviseThroughBankLocation()!=null)
						 {
							 Cell data1 = row60.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankLocation());
						 }
						 if(lcCanonicalDto.getAdviseThroughBankName()!=null)
						 {
							 Cell data1 = row61.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankName());
						 }
						 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
						 {
							 Cell data1 = row62.createCell(1);
							 data1.setCellStyle(datecellStyle);
							 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
						 }

			return myWorkBook;
}

	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
//callExportToExcelAmendLC for 707 message 
	
	public HSSFWorkbook callExportToExcelAmendLC(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		Row row28 = mySheet.createRow(28);
		Row row29 = mySheet.createRow(29);
		Row row30 = mySheet.createRow(30);
		Row row31 = mySheet.createRow(31);
		Row row32 = mySheet.createRow(32);
		Row row33 = mySheet.createRow(33);
		Row row34 = mySheet.createRow(34);
		Row row35 = mySheet.createRow(35);
		Row row36 = mySheet.createRow(36);
		Row row37 = mySheet.createRow(37);
		Row row38 = mySheet.createRow(38);
		Row row39 = mySheet.createRow(39);
		
		
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		HSSFFont headerFont = myWorkBook.createFont();
		headerFont.setFontHeightInPoints((short)9);
		headerFont.setFontName("Arial");
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.BROWN.index);

			
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Sender's Reference(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Receiver's Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Issuing Bank's Reference(23)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Issuing Bank Party Identifier(52a)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Issuing Bank Code(52a)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Issuing Bank Name and Address(52a)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Date of Issue(31C)");
		
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Date of Amendment(30)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Number of Amendment(26E)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Beneficiary Name and Address(59)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("New Date of Expiry(31E)");
		
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Increase of Documentary Currency(32B)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Increase of Documentary Credit Amount(32B)");
		datarow23Label1.setCellStyle(cellStyle);
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Decrease of Documentary Currency(33B)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Decrease of Documentary Credit Amount(33B)");
		datarow25Label1.setCellStyle(cellStyle);
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("New Documentary Currency(34B)");
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("New Documentary Credit Amount After Amendment(34B)");
		datarow27Label1.setCellStyle(cellStyle);
		
		
		Cell datarow28Label1 = row28.createCell(0);
		datarow28Label1.setCellValue("Positive Tolerance(39A)");
		Cell datarow29Label1 = row29.createCell(0);
		datarow29Label1.setCellValue("Negative Tolerance(39A)");
		Cell datarow30Label1 = row30.createCell(0);
		datarow30Label1.setCellValue("Maximum Credit Amount(39B)");
		Cell datarow31Label1 = row31.createCell(0);
		datarow31Label1.setCellValue("Additional Amounts Covered(39C)");
		Cell datarow32Label1 = row32.createCell(0);
		datarow32Label1.setCellValue("Place of Taking in Charge/Dispatch from .../Place of Receipt(44A)");
		Cell datarow33Label1 = row33.createCell(0);
		datarow33Label1.setCellValue("Port of Loading/Airport of Departure(44E)");
		Cell datarow34Label1 = row34.createCell(0);
		datarow34Label1.setCellValue("Port of Discharge/Airport of Destination(44F)");
		Cell datarow35Label1 = row35.createCell(0);
		datarow35Label1.setCellValue("Place of Final Destination/For Transportation to.../Place of Delivery(44B)");
		Cell datarow36Label1 = row36.createCell(0);
		datarow36Label1.setCellValue("Latest Date of Shipment(44C)");
		Cell datarow37Label1 = row37.createCell(0);
		datarow37Label1.setCellValue("Shipment Period(44D)");
		Cell datarow38Label1 = row38.createCell(0);
		datarow38Label1.setCellValue("Narrative(79)");
		Cell datarow39Label1 = row39.createCell(0);
		datarow39Label1.setCellValue("Sender to Receiver Information(72)");
		
		
		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getReceiverBankReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReceiverBankReference());
		 }
		 if(lcCanonicalDto.getSenderBankReference()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getSenderBankReference());
		 }
		 if(lcCanonicalDto.getSendersCorrespondentPartyIdentifier()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getSendersCorrespondentPartyIdentifier());
		 }
		 if(lcCanonicalDto.getApplicantBankCode()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getApplicantBankCode());
		 }
		 if(lcCanonicalDto.getIssunigBankNameAndAddress()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getIssunigBankNameAndAddress());
			
		 }	 
		 if(lcCanonicalDto.getIssueDate()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getIssueDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 
		 if(lcCanonicalDto.getAmendmentDate()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getAmendmentDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0"));
		 Cell data2 = row19.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getLcAmndmntNo().intValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getLcAmndmntNo())==null)) 
		 {
			 data2.setCellValue(lcCanonicalDto.getLcAmndmntNo().intValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.intValue());
		 }
		 if(lcCanonicalDto.getBeneficiaryNameAddress()!=null)
		 {
			 Cell data1 = row20.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getBeneficiaryNameAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getNewAmendExpiryDate()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getNewAmendExpiryDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 if(lcCanonicalDto.getMsgCurrency()!=null)
		 {
			 Cell data1 = row22.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getMsgCurrency());
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data3 = row23.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getIncreaseAmendAmount().intValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getIncreaseAmendAmount())==null)) 
		 {
			 data3.setCellValue(lcCanonicalDto.getIncreaseAmendAmount().doubleValue());
		 }
		 else
		 {
			 data3.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 if(lcCanonicalDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row24.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getLcCurrency());
		 }	
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data4 = row25.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getDecreaseAmendAmount().doubleValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getDecreaseAmendAmount())==null)) 
		 {
			 data4.setCellValue(lcCanonicalDto.getDecreaseAmendAmount().doubleValue());
		 }
		 else
		 {
			 data4.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 if(lcCanonicalDto.getCurrency()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getCurrency());
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data5 = row27.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getNewLcAmount().doubleValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getNewLcAmount())==null)) 
		 {
			 data5.setCellValue(lcCanonicalDto.getNewLcAmount().doubleValue());
		 }
		 else
		 {
			 data5.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 if(lcCanonicalDto.getPositiveTolerance()!=null)
		 {
			 Cell data1 = row28.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getPositiveTolerance());
		 }
		 if(lcCanonicalDto.getNegativeTolerance()!=null)
		 {
			 Cell data1 = row29.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getNegativeTolerance());
		 }
		 if(lcCanonicalDto.getMaximumCreditAmount()!=null)
		 {
			 Cell data1 = row30.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getMaximumCreditAmount());
		 }
		 if(lcCanonicalDto.getAdditionalAmountsCovered()!=null)
		 {
			 Cell data1 = row31.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdditionalAmountsCovered());
		 }
		 if(lcCanonicalDto.getInitialDispatchPlace()!=null)
		 {
			 Cell data1 = row32.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getInitialDispatchPlace());
		 }
		 if(lcCanonicalDto.getGoodsLoadingDispatchPlace()!=null)
		 {
			 Cell data1 = row33.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getGoodsLoadingDispatchPlace());
		 }
		 if(lcCanonicalDto.getGoodsDestination()!=null)
		 {
			 Cell data1 = row34.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getGoodsDestination());
		 }
		 
		 if(lcCanonicalDto.getFinalDeliveryPlace()!=null)
		 {
			 Cell data1 = row35.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getFinalDeliveryPlace());
		 }
		 if(lcCanonicalDto.getLatestDateofShipment()!=null)
		 {
			 Cell data1 = row36.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getLatestDateofShipment();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 if(lcCanonicalDto.getShipmentPeriod()!=null)
		 {
			 Cell data1 = row37.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getShipmentPeriod());
		 }
		 if(lcCanonicalDto.getNarrative()!=null)
		 {
			 Cell data1 = row38.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getNarrative());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row39.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 return myWorkBook;
		
	}
	
	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelLcAck for 730 message 
	
	public HSSFWorkbook callExportToExcelLcAck(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);

		
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Sender's Reference(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Receiver's Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Account Identification(25)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Date of Message Being Acknowledged(30)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Amount of Charges(32a)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Currency(32a)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Amount(32a)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Amount Paid Or Reimbursed Date(32a)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Account With Party Identifier(57a)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Account with Bank(57a)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Account With Name and Address(57a)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Charges(71B)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Sender to Receiver Information(72)");
		
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getRelatedReference());
		 }
		 if(lcCanonicalDto.getLcAccId()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcAccId());
		 }
		 
		 if(lcCanonicalDto.getLcAckDate()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getLcAckDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 
		 if(lcCanonicalDto.getCurrency()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getCurrency());
		 }
		 
		 if(lcCanonicalDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcCurrency());
		 }
		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data2 = row17.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getLcAmount().intValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getLcAmount())==null)) 
		 {
			 data2.setCellValue(lcCanonicalDto.getLcAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 
		 if(lcCanonicalDto.getAmountPaidDate()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getAmountPaidDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
			
		 if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
		 }	
		 
		 if(lcCanonicalDto.getAdviseThroughBankCode()!=null)
		 {
			 Cell data1 = row20.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankCode());
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankName()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankName());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getChargeDetails()!=null)
		 {
			 Cell data1 = row22.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getChargeDetails());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		
		 return myWorkBook;
	}

	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAdviceofRefusal for 734 message 
	
	public HSSFWorkbook callExportToExcelAdviceofRefusal(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Sender's TRN(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Presenting Bank's Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Utilization Date(32A)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Currency(32A)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Utilization Amount(32A)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Charges Claimed(73)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Amount of Charges(33a)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Amount Paid Or Reimbursed Date(33a)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Currency(33a)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Total Amount Claimed(33a)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Account With Party Identifier(57a)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Account with Bank(57a)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Account With Party Location(57a)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Account With Name and Address(57a)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Sender to Receiver Information(72)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("Discrepancies(77J)");
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("Disposal of Documents(77B)");
		
		


		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getRelatedReference());
		 }
		 
		 if(lcCanonicalDto.getLcAckDate()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getLcAckDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 
		 if(lcCanonicalDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcCurrency());
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data2 = row15.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getLcAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getLcAmount())==null)) 
		 {
			 
			 data2.setCellValue(lcCanonicalDto.getLcAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
			
		 if(lcCanonicalDto.getChargesClaimed()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getChargesClaimed());
		 }	
		 
		 if(lcCanonicalDto.getCurrency()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getCurrency());
		 }
		 
		 if(lcCanonicalDto.getAmountPaidDate()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getAmountPaidDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 
		 if(lcCanonicalDto.getClaimCurrency()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getClaimCurrency());
		 }	
		 		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data3 = row20.createCell(1); 
		 System.out.println(lcCanonicalDto.getLcClaimAmount());
		 if (!String.valueOf(lcCanonicalDto.getLcClaimAmount().intValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getLcClaimAmount())==null)) 
		 {
			 data3.setCellValue(lcCanonicalDto.getLcClaimAmount().doubleValue());
		 }
		 else
		 {
			 data3.setCellValue(BigDecimal.ZERO.doubleValue());
		 }	
		 
		
		 		 
		 if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankCode()!=null)
		 {
			 Cell data1 = row22.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankCode());
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankLocation()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankLocation());
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankName()!=null)
		 {
			 Cell data1 = row24.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankName());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row25.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getDiscrepancies()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getDiscrepancies());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 if(lcCanonicalDto.getLcDispoDocs()!=null)
		 {
			 Cell data1 = row27.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getLcDispoDocs());
			 data1.setCellStyle(datecellStyle);
		 }
		
		 return myWorkBook;
	}

	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAuthoriseLCPaymentAdvice for 740 message 
	
	public HSSFWorkbook callExportToExcelAuthoriseLCPaymentAdvice(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		Row row28 = mySheet.createRow(28);
		Row row29 = mySheet.createRow(29);
		Row row30 = mySheet.createRow(30);
		Row row31 = mySheet.createRow(31);
		Row row32 = mySheet.createRow(32);
		Row row33 = mySheet.createRow(33);
		Row row34 = mySheet.createRow(34);
		Row row35 = mySheet.createRow(35);
		Row row36 = mySheet.createRow(36);
		Row row37 = mySheet.createRow(37);
		Row row38 = mySheet.createRow(38);
		

		 
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Documentary Credit Number(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Account Identification(25)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Applicable Rules(40F)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Expiry Date(31D)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Expiry Place(31D)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Negotiating Bank Party Identifier(58a)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Negotiating Bank Name & Address(58a)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Negotiating Bank Code(58a)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Beneficiary Name and Address(59)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Credit Currency(32B)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Credit Amount(32B)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Positive Tolerance(39A)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Negative Tolerance(39A)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Maximum Credit Amount(39B)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Additional Amounts Covered(39C)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("Available With(41a)");
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("Authorised Bank Code(41a) ");
		Cell datarow28Label1 = row28.createCell(0);
		datarow28Label1.setCellValue("Authorisation Mode(41a)");
		Cell datarow29Label1 = row29.createCell(0);
		datarow29Label1.setCellValue("Authorised Bank Name and Address(41a)");
		Cell datarow30Label1 = row30.createCell(0);
		datarow30Label1.setCellValue("Drafts At(42C)");
		Cell datarow31Label1 = row31.createCell(0);
		datarow31Label1.setCellValue("Drawee Bank ID(42a)");
		Cell datarow32Label1 = row32.createCell(0);
		datarow32Label1.setCellValue("Drawee Bank Code(42a)");
		Cell datarow33Label1 = row33.createCell(0);
		datarow33Label1.setCellValue("Drawee Bank Name & Address(42a)");
		Cell datarow34Label1 = row34.createCell(0);
		datarow34Label1.setCellValue("Mixed Payment Details(42M)");
		Cell datarow35Label1 = row35.createCell(0);
		datarow35Label1.setCellValue("Deferred Payment Details(42P)");
		Cell datarow36Label1 = row36.createCell(0);
		datarow36Label1.setCellValue("Reimbursing Bank's Charges(71A)");
		Cell datarow37Label1 = row37.createCell(0);
		datarow37Label1.setCellValue("Other Charges(71B)");
		Cell datarow38Label1 = row38.createCell(0);
		datarow38Label1.setCellValue("Sender to Receiver Information(72)");
		
		
		
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getAcountID()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAcountID());
		 }
		 
		 if(lcCanonicalDto.getApplicableRule()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getApplicableRule());
		 }
		 if(lcCanonicalDto.getLcExpiryDate()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getLcExpiryDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 if(lcCanonicalDto.getLcExpirePlace()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcExpirePlace());
		 }
		 if(lcCanonicalDto.getNegotiatingBankPartyIdentifier()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getNegotiatingBankPartyIdentifier());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getNegotiatingBankNameAndAddress()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getNegotiatingBankNameAndAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getNegotiatingBankCode()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getNegotiatingBankCode());
		 }
		 if(lcCanonicalDto.getBeneficiaryNameAddress()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getBeneficiaryNameAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row20.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcCurrency());
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data2 = row21.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getCreditAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getCreditAmount())==null)) 
		 {
			 
			 data2.setCellValue(lcCanonicalDto.getCreditAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 

		 if(lcCanonicalDto.getPositiveTolerance()!=null)
		 {
			 Cell data1 = row22.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getPositiveTolerance());
		 }
		 if(lcCanonicalDto.getNegativeTolerance()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getNegativeTolerance());
		 }
		 if(lcCanonicalDto.getMaximumCreditAmount()!=null)
		 {
			 Cell data1 = row24.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getMaximumCreditAmount());
		 }
		 if(lcCanonicalDto.getAdditionalAmountsCovered()!=null)
		 {
			 Cell data1 = row25.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdditionalAmountsCovered());
		 }
		 if(lcCanonicalDto.getAvailableWithIdentifier()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAvailableWithIdentifier());
		 }
		 if(lcCanonicalDto.getAuthorisedBankCode()!=null)
		 {
			 Cell data1 = row27.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAuthorisedBankCode());
		 }
		 if(lcCanonicalDto.getAuthorisationMode()!=null)
		 {
			 Cell data1 = row28.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAuthorisationMode());
		 }
		 if(lcCanonicalDto.getAuthorisedAndAddress()!=null)
		 {
			 Cell data1 = row29.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getAuthorisedAndAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getDraftsAt()!=null)
		 {
			 Cell data1 = row30.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getDraftsAt());
		 }
		 if(lcCanonicalDto.getDraweeBankpartyidentifier()!=null)
		 {
			 Cell data1 = row31.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getDraweeBankpartyidentifier());
		 }
		 if(lcCanonicalDto.getDraweeBankCode()!=null)
		 {
			 Cell data1 = row32.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getDraweeBankCode());
		 }
		 if(lcCanonicalDto.getDraweeBankNameAddress()!=null)
		 {
			 Cell data1 = row33.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getDraweeBankNameAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getMixedPaymentDetails()!=null)
		 {
			 Cell data1 = row34.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getMixedPaymentDetails());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getDeferredPaymentDetails()!=null)
		 {
			 Cell data1 = row35.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getDeferredPaymentDetails());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getReimbursingBanksCharges()!=null)
		 {
			 Cell data1 = row36.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReimbursingBanksCharges());
		 }
		 if(lcCanonicalDto.getOtherCharges()!=null)
		 {
			 Cell data1 = row37.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getOtherCharges());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row38.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		 return myWorkBook;
	}
	
	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAdviceofDiscrepancy for 750 message 
	
	public HSSFWorkbook callExportToExcelAdviceofDiscrepancy(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Sender's Reference(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Related Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Currency(32B)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Principal Amount(32B)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Currency(33B)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Additional Amount(33B)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Charges to be Deducted(71B)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Charges to be Added(73)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Currency(34B)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Total Amount to be Paid(34B)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Account With Party Identifier(57a)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Account with Bank(57a)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Account With Party Location(57a)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Account With Name and Address(57a)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Sender to Receiver Information(72)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("Discrepancies(77J)");
		


		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getRelatedReference());
		 }
		 
		 if(lcCanonicalDto.getMsgCurrency()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getMsgCurrency());
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data2 = row14.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getPrincipalAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getPrincipalAmount())==null)) 
		 {
			 
			 data2.setCellValue(lcCanonicalDto.getPrincipalAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
			
		 if(lcCanonicalDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcCurrency());
		 }	
		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data3 = row16.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getAdditionalAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getAdditionalAmount())==null)) 
		 {
			 data3.setCellValue(lcCanonicalDto.getAdditionalAmount().doubleValue());
		 }
		 else
		 {
			 data3.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 
		 if(lcCanonicalDto.getChargesDeducted()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getChargesDeducted());
		 }	
		 
		 if(lcCanonicalDto.getChargesAdded()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getChargesAdded());
		 }	
		 		 
		 if(lcCanonicalDto.getCurrency()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getCurrency());
		 }	
		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data4 = row20.createCell(1); 
		 
		 if (!String.valueOf(lcCanonicalDto.getTotalAmount().intValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getTotalAmount())==null)) 
		 {
			 data4.setCellValue(lcCanonicalDto.getTotalAmount().doubleValue());
		 }
		 else
		 {
			 data4.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 		 
		 if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankCode()!=null)
		 {
			 Cell data1 = row22.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankCode());
		 }
		 
		 if(lcCanonicalDto.getAccountWithPartyLoc()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAccountWithPartyLoc());
		 }
		 
		 if(lcCanonicalDto.getAccountWithPartyNameAndAddress()!=null)
		 {
			 Cell data1 = row24.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getAccountWithPartyNameAndAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row25.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getDiscrepancies()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getDiscrepancies());
			 data1.setCellStyle(datecellStyle);
		 }
		
		 return myWorkBook;
	}

	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAdviceofPayment for 754 message 
	
	public HSSFWorkbook callExportToExcelAdviceofPayment(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		Row row28 = mySheet.createRow(28);
		Row row29 = mySheet.createRow(29);
		Row row30 = mySheet.createRow(30);
		Row row31 = mySheet.createRow(31);
		Row row32 = mySheet.createRow(32);
		Row row33 = mySheet.createRow(33);
		Row row34 = mySheet.createRow(34);
		Row row35 = mySheet.createRow(35);
		Row row36 = mySheet.createRow(36);
		Row row37 = mySheet.createRow(37);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Sender's Reference(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Related Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Principal Amount(32a)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Amount Paid Or Reimbursed Date(32a)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Currency(32a)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Principal Amount Claimed(32a)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Currency(33B)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Additional Amounts(33B)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Charges to be Deducted(71B)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Charges to be Added(73)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Total Amount Claimed(34a)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Total Amount Claimed Date(34a)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Currency(34a)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Amount(34a)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Reimbursing Bank(53a)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("ReimbursingBank Code(53a)");
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("ReimbursingBank Location(53a)");
		Cell datarow28Label1 = row28.createCell(0);
		datarow28Label1.setCellValue("ReimbursingBank Name and Address(53a)");
		Cell datarow29Label1 = row29.createCell(0);
		datarow29Label1.setCellValue("Account With Party Identifier(57a)");
		Cell datarow30Label1 = row30.createCell(0);
		datarow30Label1.setCellValue("Account with Bank(57a)");
		Cell datarow31Label1 = row31.createCell(0);
		datarow31Label1.setCellValue("Account With Party Location(57a)");
		Cell datarow32Label1 = row32.createCell(0);
		datarow32Label1.setCellValue("Account With Name and Address(57a)");
		Cell datarow33Label1 = row33.createCell(0);
		datarow33Label1.setCellValue("Beneficiary Bank(58a)");
		Cell datarow34Label1 = row34.createCell(0);
		datarow34Label1.setCellValue("BeneficiaryBank Code(58a)");
		Cell datarow35Label1 = row35.createCell(0);
		datarow35Label1.setCellValue("BeneficiaryBank Name And Address(58a)");
		Cell datarow36Label1 = row36.createCell(0);
		datarow36Label1.setCellValue("Sender to Receiver Information(72)");
		Cell datarow37Label1 = row37.createCell(0);
		datarow37Label1.setCellValue("Narrative(77A)");


		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getRelatedReference());
		 }
		 if(lcCanonicalDto.getPricAmount()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getPricAmount());
		 }
		 if(lcCanonicalDto.getAmountPaidDate()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getAmountPaidDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 if(lcCanonicalDto.getMsgCurrency()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getMsgCurrency());
		 }
		
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data2 = row16.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getPrincipalAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getPrincipalAmount())==null)) 
		 {
			 
			 data2.setCellValue(lcCanonicalDto.getPrincipalAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
			
		 if(lcCanonicalDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcCurrency());
		 }	
		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data3 = row18.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getAdditionalAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getAdditionalAmount())==null)) 
		 {
			 data3.setCellValue(lcCanonicalDto.getAdditionalAmount().doubleValue());
		 }
		 else
		 {
			 data3.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 
		 if(lcCanonicalDto.getChargesDeducted()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getChargesDeducted());
		 }	
		 
		 if(lcCanonicalDto.getChargesAdded()!=null)
		 {
			 Cell data1 = row20.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getChargesAdded());
		 }	
		 
		 if(lcCanonicalDto.getTotalAmountClaim()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getTotalAmountClaim());
		 }	
		 
		 if(lcCanonicalDto.getTotalPaidDate()!=null)
		 {
			 Cell data1 = row22.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getTotalPaidDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		 		 
		 if(lcCanonicalDto.getCurrency()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getCurrency());
		 }	
		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data4 = row24.createCell(1); 
		 
		 if (!String.valueOf(lcCanonicalDto.getTotalAmount().intValue()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getTotalAmount())==null)) 
		 {
			 data4.setCellValue(lcCanonicalDto.getTotalAmount().doubleValue());
		 }
		 else
		 {
			 data4.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 		 
		 if(lcCanonicalDto.getReimbursingBankpartyidentifier()!=null)
		 {
			 Cell data1 = row25.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReimbursingBankpartyidentifier());
		 }
		 
		 if(lcCanonicalDto.getReimbursingBankCode()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReimbursingBankCode());
		 }
		 
		 if(lcCanonicalDto.getReimbersingBankLoc()!=null)
		 {
			 Cell data1 = row27.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getReimbersingBankLoc());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 if(lcCanonicalDto.getReimbursingBankNameandAddress()!=null)
		 {
			 Cell data1 = row28.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getReimbursingBankNameandAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null)
		 {
			 Cell data1 = row29.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
		 }
		 
		 if(lcCanonicalDto.getAdviseThroughBankCode()!=null)
		 {
			 Cell data1 = row30.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAdviseThroughBankCode());
		 }
		 
		 if(lcCanonicalDto.getAccountWithPartyLoc()!=null)
		 {
			 Cell data1 = row31.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getAccountWithPartyLoc());
		 }
		 
		 if(lcCanonicalDto.getAccountWithPartyNameAndAddress()!=null)
		 {
			 Cell data1 = row32.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getAccountWithPartyNameAndAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 if(lcCanonicalDto.getBeneficiaryBankpartyidentifier()!=null)
		 {
			 Cell data1 = row33.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getBeneficiaryBankpartyidentifier());
		 }
		 if(lcCanonicalDto.getBeneficiaryBankCode()!=null)
		 {
			 Cell data1 = row34.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getBeneficiaryBankCode());
		 }
		 if(lcCanonicalDto.getBeneficiaryBankNameAndAddress()!=null)
		 {
			 Cell data1 = row35.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getBeneficiaryBankNameAndAddress());
		 }
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row36.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getNarrative()!=null)
		 {
			 Cell data1 = row37.createCell(1);
			 datecellStyle.setWrapText(true);
			 data1.setCellValue(lcCanonicalDto.getNarrative());
			 data1.setCellStyle(datecellStyle);
		 }
		
		 return myWorkBook;
	}

	/**
	 * 
	 * @param lcCanonicalDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAdviceLCPayment for 756 message 
	
	public HSSFWorkbook callExportToExcelAdviceLCPayment(LCCanonicalDto lcCanonicalDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Sender's Reference(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Presenting Bank's Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("LC Currency(32B)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Total Amount Claimed(32B)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Amount Paid Or Reimbursed Date(33A)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Currency(33A)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Amount Paid Or Reimbursed(33A)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Sender's Correspondent  Party Identifier(53a)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Sender's Correspondent  code(53a)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Sender's Correspondent  Location(53a)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Sender's Correspondent  Name and Address(53a)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Receiver's Correspondent  Party Identifier(54a)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Receiver's Correspondent  Code(54a)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Receiver's Correspondent  Location(54a)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Receiver's Correspondent  Name and Address(54a)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("Sender to Receiver Information(72)");
		
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();

		 if(lcCanonicalDto.getLcNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(lcCanonicalDto.getLcNumber());				 
		 }
		 if(lcCanonicalDto.getPresentingBanksReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getPresentingBanksReference());
		 }
		
		 if(lcCanonicalDto.getClaimCurrency()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getClaimCurrency());
		 }
		
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data2 = row14.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getTotalAmountClaimed()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getTotalAmountClaimed())==null)) 
		 {
			 
			 data2.setCellValue(lcCanonicalDto.getTotalAmountClaimed().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 
		 
		 if(lcCanonicalDto.getAmountPaidDate()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date amountPaidDate = lcCanonicalDto.getAmountPaidDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(amountPaidDate);
		 }
		
		 if(lcCanonicalDto.getCurrency()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getCurrency());
			 data1.setCellStyle(datecellStyle);
		 }	
		  
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell data3 = row17.createCell(1); 
		 if (!String.valueOf(lcCanonicalDto.getPaidAmount()).equalsIgnoreCase(null) && !(String.valueOf(lcCanonicalDto.getPaidAmount())==null)) 
		 {
			 
			 data3.setCellValue(lcCanonicalDto.getPaidAmount().doubleValue());
		 }
		 else
		 {
			 data3.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 
		 if(lcCanonicalDto.getSendersCorrespondentPartyIdentifier()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getSendersCorrespondentPartyIdentifier());
		 }
		 if(lcCanonicalDto.getSendersCorrespondentCode()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getSendersCorrespondentCode());
		 }
		 if(lcCanonicalDto.getSendersCorrespondentLocation()!=null)
		 {
			 Cell data1 = row20.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getSendersCorrespondentLocation());
		 }
		 if(lcCanonicalDto.getSendersCorrespondentNameandAddress()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendersCorrespondentNameandAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getReceiversCorrespondentPartyIdentifier()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReceiversCorrespondentPartyIdentifier());
		 }
		 if(lcCanonicalDto.getReceiversCorrespondentCode()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReceiversCorrespondentCode());
		 }
		 if(lcCanonicalDto.getReceiversCorrespondentLocation()!=null)
		 {
			 Cell data1 = row24.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(lcCanonicalDto.getReceiversCorrespondentLocation());
		 }
		 if(lcCanonicalDto.getReceiversCorrespondentNameandAddress()!=null)
		 {
			 Cell data1 = row25.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getReceiversCorrespondentNameandAddress());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(lcCanonicalDto.getSendertoReceiverInformation()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 data1.setCellValue(lcCanonicalDto.getSendertoReceiverInformation());
			 data1.setCellStyle(datecellStyle);
		 }
		 
		 return myWorkBook;
	}
	
	public HSSFWorkbook callBGHeaderMsgDetails(CreateBankGuaranteeDto createBankGuaranteeDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet,int msgType, String msgSubType)
	{
		
		//HeaderDetails Font Style 
		HSSFFont headerFont = myWorkBook.createFont();
		headerFont.setFontHeightInPoints((short)10);
		headerFont.setFontName("Arial");
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.BROWN.index);
		HSSFCellStyle headerStyle = myWorkBook.createCellStyle();
		headerStyle.setFont(headerFont);
		 
		 Row headerDetails = mySheet.createRow(3);
		 Cell header = headerDetails.createCell(0);
		 header.setCellValue("Basic Details");
		 header.setCellStyle(headerStyle);
		 headerStyle.setWrapText(true);
		 
		//Message Type details
		 CellStyle cellStyle =  myWorkBook.createCellStyle();
		 cellStyle.setWrapText(true);
		 Row messageTypeRow = mySheet.createRow(5);
		 Cell msgTypeLabel = messageTypeRow.createCell(0);
		 msgTypeLabel.setCellValue("Message Type");
		 Cell msgTypeData = messageTypeRow.createCell(1);
		 msgTypeData.setCellStyle(cellStyle);
		 msgTypeData.setCellValue(msgType);
		 cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		 
		 Cell subMsgTypeLabel = messageTypeRow.createCell(2);
		 subMsgTypeLabel.setCellValue("Sub Message Type");
		 Cell subMsgTypeData = messageTypeRow.createCell(3);
		 subMsgTypeData.setCellStyle(cellStyle);
		 subMsgTypeData.setCellValue(msgSubType);
		 
		
		Row headerRow = mySheet.createRow(6);
		 Cell senderBankLabel = headerRow.createCell(0);
		 senderBankLabel.setCellValue("Sender Bank IFSC");
		 Cell receiverBankLabel = headerRow.createCell(2);
		 receiverBankLabel.setCellValue("Receiver Bank IFSC");
		 
		if(createBankGuaranteeDto.getIssuingBankCode()!=null && !createBankGuaranteeDto.getIssuingBankCode().isEmpty())
		 {	 
			 Cell senderBankData = headerRow.createCell(1);
			 senderBankData.setCellStyle(cellStyle);
			 senderBankData.setCellValue(createBankGuaranteeDto.getIssuingBankCode());
		 }
		 
		 if(createBankGuaranteeDto.getAdvisingBank()!=null && !createBankGuaranteeDto.getAdvisingBank().isEmpty())
		 {
			 Cell receiverBankData = headerRow.createCell(3);
			 receiverBankData.setCellStyle(cellStyle);
			 receiverBankData.setCellValue(createBankGuaranteeDto.getAdvisingBank());
		 }
		 

		 return myWorkBook;
	}
	
	/**
	 * 
	 * @param createBankGuaranteeDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelCreateBankGuarantee for 760 message
	
	public HSSFWorkbook callExportToExcelCreateBankGuarantee(CreateBankGuaranteeDto createBankGuaranteeDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		
		
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("SequenceofTotal(27)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Transaction Reference No(20)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Further Identification(23)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Date(30)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Applicable Rules(40C)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("BG Rule Narration(40C)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Details of Guarantee(77C)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Sender to Receiver Information(72)");
		
		
		 if(createBankGuaranteeDto.getSequence()!=null && createBankGuaranteeDto.getSequenceofTotal()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getSequence()+ " / " +createBankGuaranteeDto.getSequenceofTotal());				 
		 }
		 if(createBankGuaranteeDto.getBgNumber()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgNumber());				 
		 }
		 if(createBankGuaranteeDto.getBgCreateType()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgCreateType());
		 }
		 if(createBankGuaranteeDto.getBgDate()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = createBankGuaranteeDto.getBgDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(createBankGuaranteeDto.getBgRuleCode()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgRuleCode());
		 }
		 
		 if(createBankGuaranteeDto.getBgRuleNarration()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgRuleNarration());
		 }
		 if(createBankGuaranteeDto.getBgDetails()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgDetails());
		 }
		 if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null)
		 {			
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getSenderToReceiverInformation());
		 }
		 
		 return myWorkBook;
	}

	/**
	 * 
	 * @param createBankGuaranteeDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAmendBG for 767 message 
	
	public HSSFWorkbook callExportToExcelAmendBG(CreateBankGuaranteeDto createBankGuaranteeDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("SequenceofTotal(27)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Transaction Reference No(20)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Related Reference(21)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Further Identification(23)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Date(30)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Number of Amendment(26E)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Date of Issue or Request to Issue(31C)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Amendment Details(77C)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Sender to Receiver Information(72)");
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		
		if(createBankGuaranteeDto.getSequence()!=null && createBankGuaranteeDto.getSequenceofTotal()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getSequence() +" / "+ createBankGuaranteeDto.getSequenceofTotal());				 
		 }  
		if(createBankGuaranteeDto.getBgNumber()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgNumber());				 
		 }
		 if(createBankGuaranteeDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getRelatedReference());
		 }
		 if(createBankGuaranteeDto.getBgFurtherIdentification()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgFurtherIdentification());
		 }
		 if(createBankGuaranteeDto.getBgDate()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = createBankGuaranteeDto.getBgDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		
			
		 Cell data2 = row16.createCell(1);
		 data2.setCellValue(createBankGuaranteeDto.getBgNoOfAmntmnt());
		
		 
		if(createBankGuaranteeDto.getBgIssueDate()!=null)
		{
			Cell data1 = row17.createCell(1);
			data1.setCellStyle(datecellStyle);
			Date bgIssueDate = createBankGuaranteeDto.getBgIssueDate();
			CreationHelper createHelper = myWorkBook.getCreationHelper();
			short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			datecellStyle.setDataFormat(dateFormat);
			datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			data1.setCellValue(bgIssueDate);
		}
		 if(createBankGuaranteeDto.getBgDetails()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgDetails());
		 }
		 if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null)
		 {			
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getSenderToReceiverInformation());
		 }
		 
		 return myWorkBook;
}

	/**
	 * 
	 * @param createBankGuaranteeDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAckBG for 768 message 
	
	public HSSFWorkbook callExportToExcelAckBG(CreateBankGuaranteeDto createBankGuaranteeDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Transaction Reference No(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Related Reference(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Account Identification(25)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Date of Message Being Acknowledged(30)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Amount of Charges(32a)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Date of Issue(32a)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Currency(32a)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Amount(32a)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Account With Party Identifier(57a)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Account With Party IFSC(57a)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Account With Party Location(57a)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Account With Name and Address(57a)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Details of Charges(71B)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Sender To Receiver Information(72)");
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(createBankGuaranteeDto.getBgNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgNumber());				 
		 }
		 if(createBankGuaranteeDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getRelatedReference());
		 }
		
		 if(createBankGuaranteeDto.getBgAccountIdentification()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgAccountIdentification());
		 }
		 
		 if(createBankGuaranteeDto.getDateofMessageBeingAcknowledged()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date DateofAcknowledged = createBankGuaranteeDto.getDateofMessageBeingAcknowledged();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(DateofAcknowledged);
		 }
		 if(createBankGuaranteeDto.getLcCurrency()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getLcCurrency());
		 }
		 
		if(createBankGuaranteeDto.getBgDebitDate()!=null)
		{
			Cell data1 = row16.createCell(1);
			data1.setCellStyle(datecellStyle);
			Date bgDebitDate = createBankGuaranteeDto.getBgDebitDate();
			CreationHelper createHelper = myWorkBook.getCreationHelper();
			short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			datecellStyle.setDataFormat(dateFormat);
			datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			data1.setCellValue(bgDebitDate);
		}
		if(createBankGuaranteeDto.getBgCurrency()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgCurrency());
		 }
		
			amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
			Cell data2 = row18.createCell(1); 
		 if (!String.valueOf(createBankGuaranteeDto.getBgChargeAmount()).equalsIgnoreCase(null)) 
		 {
			
			data2.setCellValue(createBankGuaranteeDto.getBgChargeAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		
		 if(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier());
		 }
		 if(createBankGuaranteeDto.getAdviseThroughBankCode()!=null)
		 {			
			 Cell data1 = row20.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAdviseThroughBankCode());
		 }
		 
		 if(createBankGuaranteeDto.getAccountWithPartyLocation()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAccountWithPartyLocation());
		 }
		 if(createBankGuaranteeDto.getAdviseThroughBankName()!=null)
		 {			
			 Cell data1 = row22.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAdviseThroughBankName());
		 }
		 if(createBankGuaranteeDto.getChargesDetails()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getChargesDetails());
		 }
		 if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null)
		 {			
			 Cell data1 = row24.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getSenderToReceiverInformation());
		 }
		 return myWorkBook;
}

	/**
	 * 
	 * @param createBankGuaranteeDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelAdviceofReduction for 769 message 
	
	public HSSFWorkbook callExportToExcelAdviceofReduction(CreateBankGuaranteeDto createBankGuaranteeDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		Row row28 = mySheet.createRow(28);
		Row row29 = mySheet.createRow(29);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Transaction Reference No(20)");
		
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Related Reference(21)");
		
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Account Identification(25)");
		
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Date of Reduction or Release(30)");
		
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Amount of Charges(32a)");
		
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Currency(32a)");
		
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Amount(32a)");
		
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Charge Date(32a)");
		
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Reduced Amount Currency(33B)");
		
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Reduced Amount or Released(33B)");
				
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Outstanding Amount Currency(34B)");
		
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Outstanding Amount(34B)");
		
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Amount Specification(39C)");
		
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Account with Bank (57a)");
		
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Account With Party IFSC(57a)");
		
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("Account With Party Location(57a)");
		
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("Account With Name and Address(57a)");
		
		Cell datarow28Label1 = row28.createCell(0);
		datarow28Label1.setCellValue("Details of Charges(71B)");
		
		Cell datarow29Label1 = row29.createCell(0);
		datarow29Label1.setCellValue("Sender To Receiver Information(72)");
		
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		
		 if(createBankGuaranteeDto.getBgNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgNumber());				 
		 }
		 if(createBankGuaranteeDto.getRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getRelatedReference());
		 }
		
		 if(createBankGuaranteeDto.getBgAccountIdentification()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgAccountIdentification());
		 }
		 
		 if(createBankGuaranteeDto.getReductionDate()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date DateofAcknowledged = createBankGuaranteeDto.getReductionDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(DateofAcknowledged);
		 }
		 if(createBankGuaranteeDto.getChargeAmt()!=null)
		 {
			 Cell data1 = row15.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getChargeAmt());
		 }
		 
		 if(createBankGuaranteeDto.getBgCurrency()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgCurrency());
		 }
		 
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
			Cell data2 = row17.createCell(1); 
		 if (!String.valueOf(createBankGuaranteeDto.getBgChargeAmount()).equalsIgnoreCase(null)) 
		 {
			
			 data2.setCellValue(createBankGuaranteeDto.getBgChargeAmount().doubleValue());
		 }
		 else
		 {
			 data2.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 		 
		if(createBankGuaranteeDto.getChargeDate()!=null)
		{
			Cell data1 = row18.createCell(1);
			data1.setCellStyle(datecellStyle);
			Date bgDebitDate = createBankGuaranteeDto.getChargeDate();
			CreationHelper createHelper = myWorkBook.getCreationHelper();
			short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			datecellStyle.setDataFormat(dateFormat);
			datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			data1.setCellValue(bgDebitDate);
		}
		 if(createBankGuaranteeDto.getReducedCurrency()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getReducedCurrency());
		 }
		 	amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
			Cell data3 = row20.createCell(1); 
		 if (!String.valueOf(createBankGuaranteeDto.getReducedAmt()).equalsIgnoreCase(null)) 
		 {
			
			 data3.setCellValue(createBankGuaranteeDto.getReducedAmt().doubleValue());
		 }
		 else
		 {
			 data3.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 
		 if(createBankGuaranteeDto.getOutstandingCurrency()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getOutstandingCurrency());
		 }
		 	amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
			Cell data4 = row22.createCell(1); 
		 if (!String.valueOf(createBankGuaranteeDto.getOutstandingAmt()).equalsIgnoreCase(null)) 
		 {
			
			 data4.setCellValue(createBankGuaranteeDto.getOutstandingAmt().doubleValue());
		 }
		 else
		 {
			 data4.setCellValue(BigDecimal.ZERO.doubleValue());
		 }
		 
		if(createBankGuaranteeDto.getAmountSpecification()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAmountSpecification());
		 }
		
		 if(createBankGuaranteeDto.getAccountWithBank()!=null)
		 {
			 Cell data1 = row24.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAccountWithBank());
		 }
		 
		 if(createBankGuaranteeDto.getAuthorisedBankCode()!=null)
		 {			
			 Cell data1 = row25.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAuthorisedBankCode());
		 }
		 
		 if(createBankGuaranteeDto.getAccountWithPartyLocation()!=null)
		 {
			 Cell data1 = row26.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getAccountWithPartyLocation());
		 }
		 if(createBankGuaranteeDto.getBgAccountWithNameandAddress()!=null)
		 {			
			 Cell data1 = row27.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getBgAccountWithNameandAddress());
		 }
		 if(createBankGuaranteeDto.getChargesDetails()!=null)
		 {
			 Cell data1 = row28.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getChargesDetails());
		 }
		 if(createBankGuaranteeDto.getSenderToReceiverInformation()!=null)
		 {			
			 Cell data1 = row29.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(createBankGuaranteeDto.getSenderToReceiverInformation());
		 }
		 return myWorkBook;
}

	/**
	 * 
	 * @param createBankGuaranteeDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelFreeFormat for 799 message 
	
	public HSSFWorkbook callExportToExcelFreeFormat(CreateBankGuaranteeDto createBankGuaranteeDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Transaction Reference No(20)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Related Reference No(21)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Narrative(79)");
		 
		 if(createBankGuaranteeDto.getBgNumber()!=null)
		 {
			 Cell row11data1 = row11.createCell(1);
			 row11data1.setCellStyle(cellStyle);
			 row11data1.setCellValue(createBankGuaranteeDto.getBgNumber());
		 }
		 if(createBankGuaranteeDto.getRelatedReference()!=null)
		 {
			 Cell row12data1 = row12.createCell(1);
			 row12data1.setCellValue(createBankGuaranteeDto.getRelatedReference());
		 }
		 if(createBankGuaranteeDto.getNarrative()!=null)
		 {
			 Cell row13data1 = row13.createCell(1);
			 row13data1.setCellStyle(cellStyle);
			 row13data1.setCellValue(createBankGuaranteeDto.getNarrative());
		 }
		 
		 return myWorkBook;
	}
	
	public HSSFWorkbook callBGCovHeaderMsgDetails(BankGuaranteeCoverDto bankGuaranteeCoverDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet,int msgType, String msgSubType)
	{
		
		//HeaderDetails Font Style 
		HSSFFont headerFont = myWorkBook.createFont();
		headerFont.setFontHeightInPoints((short)10);
		headerFont.setFontName("Arial");
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.BROWN.index);
		HSSFCellStyle headerStyle = myWorkBook.createCellStyle();
		headerStyle.setFont(headerFont);
		 
		 Row headerDetails = mySheet.createRow(3);
		 Cell header = headerDetails.createCell(0);
		 header.setCellValue("Basic Details");
		 header.setCellStyle(headerStyle);
		 headerStyle.setWrapText(true);
		 
		//Message Type details
		 CellStyle cellStyle =  myWorkBook.createCellStyle();
		 cellStyle.setWrapText(true);
		 Row messageTypeRow = mySheet.createRow(5);
		 Cell msgTypeLabel = messageTypeRow.createCell(0);
		 msgTypeLabel.setCellValue("Message Type");
		 Cell msgTypeData = messageTypeRow.createCell(1);
		 msgTypeData.setCellStyle(cellStyle);
		 msgTypeData.setCellValue(msgType);
		 cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		 
		 Cell subMsgTypeLabel = messageTypeRow.createCell(2);
		 subMsgTypeLabel.setCellValue("Sub Message Type");
		 Cell subMsgTypeData = messageTypeRow.createCell(3);
		 subMsgTypeData.setCellStyle(cellStyle);
		 subMsgTypeData.setCellValue(msgSubType);
		 
		
		Row headerRow = mySheet.createRow(6);
		 Cell senderBankLabel = headerRow.createCell(0);
		 senderBankLabel.setCellValue("Sender Bank IFSC");
		 Cell receiverBankLabel = headerRow.createCell(2);
		 receiverBankLabel.setCellValue("Receiver Bank IFSC");
		 
		if(bankGuaranteeCoverDto.getIssuingBankCode()!=null && !bankGuaranteeCoverDto.getIssuingBankCode().isEmpty())
		 {	 
			 Cell senderBankData = headerRow.createCell(1);
			 senderBankData.setCellStyle(cellStyle);
			 senderBankData.setCellValue(bankGuaranteeCoverDto.getIssuingBankCode());
		 }
		 
		 if(bankGuaranteeCoverDto.getBeneficiaryBankCode()!=null && !bankGuaranteeCoverDto.getBeneficiaryBankCode().isEmpty())
		 {
			 Cell receiverBankData = headerRow.createCell(3);
			 receiverBankData.setCellStyle(cellStyle);
			 receiverBankData.setCellValue(bankGuaranteeCoverDto.getBeneficiaryBankCode());
		 }
		 

		 return myWorkBook;
	}

	/**
	 * 
	 * @param bankGuaranteeCoverDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelCreateBankGuaranteeCover for 760 Cover message
	
	public HSSFWorkbook callExportToExcelCreateBankGuaranteeCover(BankGuaranteeCoverDto bankGuaranteeCoverDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		CellStyle datecellStyle =  myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		Row row28 = mySheet.createRow(28);
		Row row29 = mySheet.createRow(29);
		Row row30 = mySheet.createRow(30);
		Row row31 = mySheet.createRow(31);
		Row row32 = mySheet.createRow(32);
		Row row33 = mySheet.createRow(33);
		Row row34 = mySheet.createRow(34);
		Row row35 = mySheet.createRow(35);
		Row row36 = mySheet.createRow(36);
		Row row37 = mySheet.createRow(37);
		Row row38 = mySheet.createRow(38);
		Row row39 = mySheet.createRow(39);
		Row row40 = mySheet.createRow(40);
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Transaction Reference Number(7020)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Guarantee Form Number(7022)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Type of Bank Guarantee(7024)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Currency Code(7025)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Amount of Guarantee(7025)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Guarantee From Date(7026)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Guarantee To Date(7026)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Guarantee Effective Date(7027)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("End Date for Lodgement of Claim(7029)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Place of Lodgement of Claim(7030)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Issuing Branch IFSC(7031)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Issuing Branch Name and address(7032)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Name of Applicant and his details(7033)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Name of Beneficiary and his details(7034)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Beneficiary IFSC(7035)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("Beneficiary branch name and address(7036)");
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("Sender to receiver information(7037)");
		Cell datarow28Label1 = row28.createCell(0);
		datarow28Label1.setCellValue("Purpose of Guarantee(7038)");
		Cell datarow29Label1 = row29.createCell(0);
		datarow29Label1.setCellValue("Reference/Description of the underlined contract(7039)");
		Cell datarow30Label1 = row30.createCell(0);
		datarow30Label1.setCellValue("Stamp Duty Electronically Paid(Y/N)(7040)");
		Cell datarow31Label1 = row31.createCell(0);
		datarow31Label1.setCellValue("E-Stamp Certificate Number(7041)");
		Cell datarow32Label1 = row32.createCell(0);
		datarow32Label1.setCellValue("E-Stamp Date and Time(7042)");
		Cell datarow33Label1 = row33.createCell(0);
		datarow33Label1.setCellValue("Amount Paid(7043)");
		Cell datarow34Label1 = row34.createCell(0);
		datarow34Label1.setCellValue("State Code(7044)");
		Cell datarow35Label1 = row35.createCell(0);
		datarow35Label1.setCellValue("Article Number(7045)");
		Cell datarow36Label1 = row36.createCell(0);
		datarow36Label1.setCellValue("Date of Payment(7046)");
		Cell datarow37Label1 = row37.createCell(0);
		datarow37Label1.setCellValue("Place of Payment(7047)");
		Cell datarow38Label1 = row38.createCell(0);
		datarow38Label1.setCellValue("E-Bank Guarantee to be held in demat form (Y/N)(7048)");
		Cell datarow39Label1 = row39.createCell(0);
		datarow39Label1.setCellValue("Custodian Service Provider(7050)");
		Cell datarow40Label1 = row40.createCell(0);
		datarow40Label1.setCellValue("Demat Account Number(7049)");
		
		 if(bankGuaranteeCoverDto.getBgNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgNumber());				 
		 }
		 if(bankGuaranteeCoverDto.getBgFormNumber()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgFormNumber());
		 }
		 if(bankGuaranteeCoverDto.getBgType()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgType());
		 }
		 if(bankGuaranteeCoverDto.getCurrency()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getCurrency());
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell row15data1 = row15.createCell(1); 
		 if (!String.valueOf(bankGuaranteeCoverDto.getBgAmount()).equalsIgnoreCase(null) && !(String.valueOf(bankGuaranteeCoverDto.getBgAmount())==null)) 
		 {
			 
			 row15data1.setCellValue(bankGuaranteeCoverDto.getBgAmount().doubleValue());
		 }
		 else
		 {
			 row15data1.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 
		 if(bankGuaranteeCoverDto.getBgFromDate()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgFromDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(bankGuaranteeCoverDto.getBgToDate()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgToDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(bankGuaranteeCoverDto.getBgEffectiveDate()!=null)
		 {
			 Cell data1 = row18.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgEffectiveDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(bankGuaranteeCoverDto.getBgLodgementEndDate()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgLodgementEndDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(bankGuaranteeCoverDto.getBgLodgementPalce()!=null)
		 {
			 Cell data1 = row20.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgLodgementPalce());
		 }
		 if(bankGuaranteeCoverDto.getBgIssuingBankCode()!=null)
		 {
			 Cell data1 = row21.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgIssuingBankCode());
		 }
		 if(bankGuaranteeCoverDto.getIssunigBankNameAndAddress()!=null)
		 {
			 Cell row22data1 = row22.createCell(1);
			 row22data1.setCellValue(bankGuaranteeCoverDto.getIssunigBankNameAndAddress());
			 row22data1.setCellStyle(datecellStyle);
		 }
		 
		 if(bankGuaranteeCoverDto.getBgApplicentNameAndDetails()!=null)
		 {
			 Cell row23data1 = row23.createCell(1);
			 row23data1.setCellValue(bankGuaranteeCoverDto.getBgApplicentNameAndDetails());
			 row23data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails()!=null)
		 {
			 Cell row24data1 = row24.createCell(1);
			 row24data1.setCellValue(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails());
			 row24data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getBeneficiaryBankCode()!=null)
		 {
			 Cell data1 = row25.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBeneficiaryBankCode());
		 }
		 if(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress()!=null)
		 {
			 Cell row26data1 = row26.createCell(1);
			 row26data1.setCellValue(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress());
			 row26data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getSenderToReciverInformation()!=null)
		 {
			 Cell row27data1 = row27.createCell(1);
			 row27data1.setCellValue(bankGuaranteeCoverDto.getSenderToReciverInformation());
			 row27data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getBgPurpose()!=null)
		 {
			 Cell row28data1 = row28.createCell(1);
			 row28data1.setCellValue(bankGuaranteeCoverDto.getBgPurpose());
			 row28data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract()!=null)
		 {
			 Cell row29data1 = row29.createCell(1);
			 row29data1.setCellValue(bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract());
			 row29data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getStampDutyPaid()!=null)
		 {
			 Cell data1 = row30.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getStampDutyPaid());
		 }
		 if(bankGuaranteeCoverDto.getStampCertificateNumber()!=null)
		 {
			 Cell data1 = row31.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getStampCertificateNumber());
		 }
		 if(bankGuaranteeCoverDto.getStampDateAndTime()!=null)
		 {
			 Cell data1 = row32.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getStampDateAndTime();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateTimeFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy 'at' hh:mm a");
			 datecellStyle.setDataFormat(dateTimeFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell row33data1 = row33.createCell(1); 
		 if (!String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid()).equalsIgnoreCase(null) && !(String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid())==null)) 
		 {
			 
			 row33data1.setCellValue(bankGuaranteeCoverDto.getBgAmountPaid().doubleValue());
		 }
		 else
		 {
			 row33data1.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 
		 
		 if(bankGuaranteeCoverDto.getBgStateCode()!=null)
		 {
			 Cell data1 = row34.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgStateCode());
		 }
		 if(bankGuaranteeCoverDto.getBgArticleNumber()!=null)
		 {
			 Cell data1 = row35.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgArticleNumber());
		 }
		 if(bankGuaranteeCoverDto.getBgPaymentDate()!=null)
		 {
			 Cell data1 = row36.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgPaymentDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(bankGuaranteeCoverDto.getBgPaymentPlace()!=null)
		 {
			 Cell data1 = row37.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgPaymentPlace());
		 }
		 if(bankGuaranteeCoverDto.getBgHeldDematForm()!=null)
		 {
			 Cell data1 = row38.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgHeldDematForm());
		 }
		 if(bankGuaranteeCoverDto.getCustodianServiceProvider()!=null)
		 {
			 Cell data1 = row39.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getCustodianServiceProvider());
		 }
		 if(bankGuaranteeCoverDto.getDematAccNumber()!=null)
		 {
			 Cell data1 = row40.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getDematAccNumber());
		 }
		 return myWorkBook;
	}

	/**
	 * 
	 * @param bankGuaranteeCoverDto
	 * @param myWorkBook
	 * @param mySheet
	 * @return
	 */
	//callExportToExcelCreateAmendBGCover for 767 Cover message
	
	public HSSFWorkbook callExportToExcelCreateAmendBGCover(BankGuaranteeCoverDto bankGuaranteeCoverDto, HSSFWorkbook myWorkBook, HSSFSheet mySheet)
	{
		
		CellStyle cellStyle =  myWorkBook.createCellStyle();
		cellStyle.setWrapText(true);
		CellStyle datecellStyle = myWorkBook.createCellStyle();
		datecellStyle.setWrapText(true);
		Row row11 = mySheet.createRow(11);
		Row row12 = mySheet.createRow(12);
		Row row13 = mySheet.createRow(13);
		Row row14 = mySheet.createRow(14);
		Row row15 = mySheet.createRow(15);
		Row row16 = mySheet.createRow(16);
		Row row17 = mySheet.createRow(17);
		Row row18 = mySheet.createRow(18);
		Row row19 = mySheet.createRow(19);
		Row row20 = mySheet.createRow(20);
		Row row21 = mySheet.createRow(21);
		Row row22 = mySheet.createRow(22);
		Row row23 = mySheet.createRow(23);
		Row row24 = mySheet.createRow(24);
		Row row25 = mySheet.createRow(25);
		Row row26 = mySheet.createRow(26);
		Row row27 = mySheet.createRow(27);
		Row row28 = mySheet.createRow(28);
		Row row29 = mySheet.createRow(29);
		Row row30 = mySheet.createRow(30);
		Row row31 = mySheet.createRow(31);
		Row row32 = mySheet.createRow(32);
		Row row33 = mySheet.createRow(33);
		Row row34 = mySheet.createRow(34);
		Row row35 = mySheet.createRow(35);

		
		CellStyle amountcellStyle =  myWorkBook.createCellStyle();
		
		Cell datarow11Label1 = row11.createCell(0);
		datarow11Label1.setCellValue("Transaction Reference Number(7020)");
		Cell datarow12Label1 = row12.createCell(0);
		datarow12Label1.setCellValue("Related Reference(7021)");
		Cell datarow13Label1 = row13.createCell(0);
		datarow13Label1.setCellValue("Further Identification(7055)");
		Cell datarow14Label1 = row14.createCell(0);
		datarow14Label1.setCellValue("Amendment Date(7056)");
		Cell datarow15Label1 = row15.createCell(0);
		datarow15Label1.setCellValue("Number of Amendment(7057)");
		Cell datarow16Label1 = row16.createCell(0);
		datarow16Label1.setCellValue("Date of issue or Request to Issue(7058)");
		Cell datarow17Label1 = row17.createCell(0);
		datarow17Label1.setCellValue("Amendment Details(7059)");
		Cell datarow18Label1 = row18.createCell(0);
		datarow18Label1.setCellValue("Sender to Receiver information (7037)");
		Cell datarow19Label1 = row19.createCell(0);
		datarow19Label1.setCellValue("Issuing Branch IFSC(7031)");
		Cell datarow20Label1 = row20.createCell(0);
		datarow20Label1.setCellValue("Issuing Branch Name and address(7032)");
		Cell datarow21Label1 = row21.createCell(0);
		datarow21Label1.setCellValue("Name of Applicant and his details(7033)");
		Cell datarow22Label1 = row22.createCell(0);
		datarow22Label1.setCellValue("Name of Beneficiary and his details(7034)");
		Cell datarow23Label1 = row23.createCell(0);
		datarow23Label1.setCellValue("Beneficiary IFSC(7035)");
		Cell datarow24Label1 = row24.createCell(0);
		datarow24Label1.setCellValue("Beneficiary branch name and address(7036)");
		Cell datarow25Label1 = row25.createCell(0);
		datarow25Label1.setCellValue("Stamp Duty Electronically Paid(Y/N)(7040)");
		Cell datarow26Label1 = row26.createCell(0);
		datarow26Label1.setCellValue("E-Stamp Certificate Number(7041)");
		Cell datarow27Label1 = row27.createCell(0);
		datarow27Label1.setCellValue("E-Stamp Date and Time(7042)");
		Cell datarow28Label1 = row28.createCell(0);
		datarow28Label1.setCellValue("Stamp Duty Amount paid(7043)");
		Cell datarow29Label1 = row29.createCell(0);
		datarow29Label1.setCellValue("State Code(7044)");
		Cell datarow30Label1 = row30.createCell(0);
		datarow30Label1.setCellValue("Article Number(7045)");
		Cell datarow31Label1 = row31.createCell(0);
		datarow31Label1.setCellValue("Date of Payment(7046)");
		Cell datarow32Label1 = row32.createCell(0);
		datarow32Label1.setCellValue("Place of Payment(7047)");
		Cell datarow33Label1 = row33.createCell(0);
		datarow33Label1.setCellValue("E-Bank Guarantee to be held in demat form (Y/N)(7048)");
		Cell datarow34Label1 = row34.createCell(0);
		datarow34Label1.setCellValue("Custodian Service Provider(7050)");
		Cell datarow35Label1 = row35.createCell(0);
		datarow35Label1.setCellValue("Demat Account Number(7049)");
		
		
		 if(bankGuaranteeCoverDto.getBgNumber()!=null)
		 {
			 Cell data1 = row11.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgNumber());				 
		 }
		 if(bankGuaranteeCoverDto.getBgRelatedReference()!=null)
		 {
			 Cell data1 = row12.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgRelatedReference());
		 }
		 if(bankGuaranteeCoverDto.getBgFurtherIdentification()!=null)
		 {
			 Cell data1 = row13.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgFurtherIdentification());
		 }
		 if(bankGuaranteeCoverDto.getBgAmendmentDate()!=null)
		 {
			 Cell data1 = row14.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgAmendmentDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0"));
		 Cell row15data1 = row15.createCell(1); 
		 if (!String.valueOf(bankGuaranteeCoverDto.getBgNoofAmendments()).equalsIgnoreCase(null) && !(String.valueOf(bankGuaranteeCoverDto.getBgNoofAmendments())==null)) 
		 {
			 
			 row15data1.setCellValue(bankGuaranteeCoverDto.getBgNoofAmendments().doubleValue());
		 }
		 else
		 {
			 row15data1.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 
		 if(bankGuaranteeCoverDto.getBgIssueDate()!=null)
		 {
			 Cell data1 = row16.createCell(1);
			 data1.setCellStyle(datecellStyle);
			 Date bgDate = bankGuaranteeCoverDto.getBgIssueDate();
			 CreationHelper createHelper = myWorkBook.getCreationHelper();
			 short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
			 datecellStyle.setDataFormat(dateFormat);
			 datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			 data1.setCellValue(bgDate);
		 }
		 if(bankGuaranteeCoverDto.getBgAmedmentDetails()!=null)
		 {
			 Cell data1 = row17.createCell(1);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgAmedmentDetails());
			 data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getSenderToReciverInformation()!=null)
		 {
			 Cell row18data1 = row18.createCell(1);
			 row18data1.setCellValue(bankGuaranteeCoverDto.getSenderToReciverInformation());
			 row18data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getBgIssuingBankCode()!=null)
		 {
			 Cell data1 = row19.createCell(1);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgIssuingBankCode());
		 }
		 if(bankGuaranteeCoverDto.getIssunigBankNameAndAddress()!=null)
		 {
			 Cell row20data1 = row20.createCell(1);
			 row20data1.setCellValue(bankGuaranteeCoverDto.getIssunigBankNameAndAddress());
			 row20data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getBgApplicentNameAndDetails()!=null)
		 {
			 Cell row21data1 = row21.createCell(1);
			 row21data1.setCellValue(bankGuaranteeCoverDto.getBgApplicentNameAndDetails());
			 row21data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails()!=null)
		 {
			 Cell row23data1 = row22.createCell(1);
			 row23data1.setCellValue(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails());
			 row23data1.setCellStyle(datecellStyle);
		 } 
		 if(bankGuaranteeCoverDto.getBeneficiaryBankCode()!=null)
		 {
			 Cell data1 = row23.createCell(1);
			 data1.setCellValue(bankGuaranteeCoverDto.getBeneficiaryBankCode());
		 }
		 if(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress()!=null)
		 {
			 Cell row24data1 = row24.createCell(1);
			 row24data1.setCellValue(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress());
			 row24data1.setCellStyle(datecellStyle);
		 }
		 if(bankGuaranteeCoverDto.getStampDutyPaid()!=null)
		 {
			 Cell row25data1 = row25.createCell(1);
			 row25data1.setCellValue(bankGuaranteeCoverDto.getStampDutyPaid());
			 row25data1.setCellStyle(datecellStyle);
		 } 
		 if(bankGuaranteeCoverDto.getStampCertificateNumber()!=null)
		 {
			 Cell row26data1 = row26.createCell(1);
			 row26data1.setCellValue(bankGuaranteeCoverDto.getStampCertificateNumber());
			 row26data1.setCellStyle(datecellStyle);
		 }
		if(bankGuaranteeCoverDto.getStampDateAndTime()!=null)
		{
			Cell data1 = row27.createCell(1);
			data1.setCellStyle(datecellStyle);
			Date bgDate = bankGuaranteeCoverDto.getStampDateAndTime();
			CreationHelper createHelper = myWorkBook.getCreationHelper();
			short dateTimeFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy 'at' hh:mm a");
			datecellStyle.setDataFormat(dateTimeFormat);
			datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			data1.setCellValue(bgDate);
		 }
		 amountcellStyle.setDataFormat(myWorkBook.createDataFormat().getFormat("0.00"));
		 Cell row28data1 = row28.createCell(1); 
		 if (!String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid()).equalsIgnoreCase(null) && !(String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid())==null)) 
		 {
			 
			 row28data1.setCellValue(bankGuaranteeCoverDto.getBgAmountPaid().doubleValue());
		 }
		 else
		 {
			 row28data1.setCellValue(BigDecimal.ZERO.doubleValue());
		 } 
		 if(bankGuaranteeCoverDto.getBgStateCode()!=null)
		 {
			 Cell data1 = row29.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgStateCode());
		 }
		 if(bankGuaranteeCoverDto.getBgArticleNumber()!=null)
		 {
			 Cell data1 = row30.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgArticleNumber());
		 }
		 if(bankGuaranteeCoverDto.getBgPaymentDate()!=null)
			{
				Cell data1 = row31.createCell(1);
				data1.setCellStyle(datecellStyle);
				Date bgDate = bankGuaranteeCoverDto.getBgPaymentDate();
				CreationHelper createHelper = myWorkBook.getCreationHelper();
				short dateFormat = createHelper.createDataFormat().getFormat("MM/dd/yyyy");
				datecellStyle.setDataFormat(dateFormat);
				datecellStyle.setAlignment(CellStyle.ALIGN_LEFT);
				data1.setCellValue(bgDate);
			 }
		 if(bankGuaranteeCoverDto.getBgPaymentPlace()!=null)
		 {
			 Cell data1 = row32.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgPaymentPlace());
		 }
		 if(bankGuaranteeCoverDto.getBgHeldDematForm()!=null)
		 {
			 Cell data1 = row33.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getBgHeldDematForm());
		 }
		 if(bankGuaranteeCoverDto.getCustodianServiceProvider()!=null)
		 {
			 Cell data1 = row34.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getCustodianServiceProvider());
		 }
		 if(bankGuaranteeCoverDto.getDematAccNumber()!=null)
		 {
			 Cell data1 = row35.createCell(1);
			 data1.setCellStyle(cellStyle);
			 data1.setCellValue(bankGuaranteeCoverDto.getDematAccNumber());
		 }
	
		 return myWorkBook;
	}


}
	

