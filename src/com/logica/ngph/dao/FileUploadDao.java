package com.logica.ngph.dao;

import java.util.ArrayList;
import java.util.List;

import com.logica.ngph.Entity.Addresses;
import com.logica.ngph.Entity.IMPS_Consilet;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.GenericFilePojo;


public interface FileUploadDao {
	public void saveAdresses(List<Addresses> addresses) throws NGPHException;
	public void saveParties(List<Parties> parties) throws NGPHException;
	public void saveIMPS(List<IMPS_Consilet> consilet) throws NGPHException;
	
    int updateFileData(ArrayList<GenericFilePojo> dataHolder, String lineData, String TableName) throws Exception;
	void populateFileData(ArrayList<GenericFilePojo> dataHolder, String lineData,String TableName) throws Exception;
	int checkFileData(ArrayList<GenericFilePojo> dataHolder, String lineData, String TableName) throws Exception;
	
	public void logFileData(String fileName, String tableName, String Status);
	public String populateIFSCFileData(String data, String tableName, String srcFileName, String csvFileName)throws Exception;
	public void populateLogFileData(String srcfileName, String tableName,String status);

}
