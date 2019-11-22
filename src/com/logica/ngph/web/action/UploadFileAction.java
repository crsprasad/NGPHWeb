package com.logica.ngph.web.action;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.logica.ngph.common.dtos.Addresses;
import com.logica.ngph.common.dtos.Parties;
import com.logica.ngph.dtos.FileUploadFieldDetail;
import com.logica.ngph.dtos.IMPS_ReconsiletDto;

import com.logica.ngph.service.FileUploadService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;


public class UploadFileAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6691675849110168301L;
	static Logger logger = Logger.getLogger(UploadFileAction.class);
	private File ifscFileDataUpload;
	private String ifscFileDataUploadContentType;
	private String ifscFileDataUploadFileName;
	private Map<String, Object> session;
	private String fileType;
	private HttpServletRequest servletRequest;
	private File srcifscFile;
	String filePath = null;
	String srcFileName = null;
	String srcFile = null;
	String destFileName = null;
	String srcFilePath = null;
	String destFilePath = null;
	
	public File getIfscFileDataUpload() {
		return ifscFileDataUpload;
	}
	public void setIfscFileDataUpload(File ifscFileDataUpload) {
		this.ifscFileDataUpload = ifscFileDataUpload;
	}
	public String getIfscFileDataUploadContentType() {
		return ifscFileDataUploadContentType;
	}
	public void setIfscFileDataUploadContentType(
			String ifscFileDataUploadContentType) {
		this.ifscFileDataUploadContentType = ifscFileDataUploadContentType;
	}
	public String getIfscFileDataUploadFileName() {
		return ifscFileDataUploadFileName;
	}
	public void setIfscFileDataUploadFileName(String ifscFileDataUploadFileName) {
		this.ifscFileDataUploadFileName = ifscFileDataUploadFileName;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		
		this.session = session;
	}
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	Map<String, FileUploadFieldDetail> filedMap = new LinkedHashMap<String,FileUploadFieldDetail>();
	Map<String,Integer> localIndexMap = new HashMap<String, Integer>();
	List<Addresses> addressList = new ArrayList<Addresses>();
	List<IMPS_ReconsiletDto> impsReconsiletList = new ArrayList<IMPS_ReconsiletDto>();
	
	List<Parties> partiesList = new ArrayList<Parties>();
	


	
	@SkipValidation
	public String loadUpload(){
				
		return "fileUpload";
	}
	/**
	* This method is used to read the uploaded file and covert into .csv file
	* @return String forward
	 * @throws IOException 
	 * @throws Exception 
	*/
	
	public String performUpload() throws IOException 
	{
					//Convert .xlsx file to .csv file and check if file is successfully converted or not
				String tableName = "TA_PARTIES";
				FileUploadService  fileUploadService = (FileUploadService) ApplicationContextProvider.getBean("fileUploadService");
				if (convertXLSXToCsvFile().equalsIgnoreCase("success")) 
				{
					System.out.println("File Convertion successful");	
					System.out.println("destFilePath is "+destFilePath);
					String status = fileUploadService.doProcess(destFilePath, tableName, srcFileName, destFileName );
					if(status.equalsIgnoreCase("success"))
					{
						return "fileSubmit";
					}
					else
					{
						addActionError("File Upload Failed");
						return "input";
					}
		
				} else 
				{
					return "input";
				}
	}
	
	
	 public void setServletRequest(HttpServletRequest servletRequest) {  
	        this.servletRequest=servletRequest;  
	          
	    }  

	 public String convertXLSXToCsvFile() throws IOException
	 {
			filePath =  servletRequest.getSession().getServletContext().getRealPath("/"); 
			srcifscFile = new File(filePath, this.ifscFileDataUploadFileName); 
			FileUtils.copyFile(this.ifscFileDataUpload, srcifscFile);
			srcFileName= this.ifscFileDataUploadFileName.toString();
			srcFile = srcFileName.substring(0, srcFileName.indexOf("."));
			destFileName = srcFile.concat(".csv");
			System.out.println("srcFileName is "+srcFileName);
			System.out.println("destFileName is "+destFileName);
			srcFilePath = filePath.concat("\\"+srcFileName);
			destFilePath = filePath.concat("\\"+destFileName);
			if(srcifscFile!=null)
			{		
				if(FilenameUtils.getExtension(srcFilePath).equalsIgnoreCase("XLSX"))
					{
					// used to read the .xlsx file and convert into .csv file
					StringBuffer data = new StringBuffer();
					try {
						FileOutputStream fos = new FileOutputStream(destFilePath);
		
						// Get the workbook object for XLSX file
						XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(srcFilePath));
		
						// Get first sheet from the workbook
						XSSFSheet sheet = wBook.getSheetAt(0);
						Row row;
						Cell cell;
		
						// Iterate through each rows from first sheet
						Iterator<Row> rowIterator = sheet.iterator();
						while (rowIterator.hasNext()) {
							row = rowIterator.next();
		
							// For each row, iterate through each columns
							Iterator<Cell> cellIterator = row.cellIterator();
							while (cellIterator.hasNext()) {
		
								cell = cellIterator.next();
								if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
								{
									data.append(Integer.parseInt("0") + " ,");
									
								}
								else
								{
									switch (cell.getCellType()) {
									
									
									case Cell.CELL_TYPE_BOOLEAN:
										data.append(cell.getBooleanCellValue() + ",");
										break;
									case Cell.CELL_TYPE_NUMERIC:
										data.append(cell.getNumericCellValue() + ",");
										break;
									case Cell.CELL_TYPE_STRING:
										if(cell.getStringCellValue().contains(","))
										{
											data.append(cell.getStringCellValue().replace(",", "-")+ ",");
										}
										else
										{
											data.append(cell.getStringCellValue() + ",");
										}
										break;
									
									}
								
								}
							}
							data.deleteCharAt(data.length()-1);
							data.append("\r\n");
							
						}
		
						fos.write(data.toString().getBytes());
						fos.close();
						return "success";
					} catch (Exception ioe) {
						ioe.printStackTrace();
					}
				}
				else
				{
					addFieldError("ifscFileDataUpload", "Invalid File Type, Please upload .xlsx file only");
					return "input";
				}
			}
			
			else
			{
				addFieldError("fileData", "Nothing Found To Upload");				
			}
		 return "success";
	 }
	 
	
}
