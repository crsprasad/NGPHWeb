package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.Addresses;
import com.logica.ngph.common.dtos.Parties;
import com.logica.ngph.dtos.IMPS_ReconsiletDto;

public interface FileUploadService {
	public void saveAdresses(List<Addresses> addresses) throws NGPHException;
	public void saveParties(List<Parties> parties) throws NGPHException;
	public void saveIMPSConsilet(List<IMPS_ReconsiletDto> impsReconsiletList, String direction) throws NGPHException;
	
	public String doProcess(String data,String tableName, String fileName, String csvFileName);
	public String performexecute(String data, String fileName);
	public void logFileStatus(String fileName, String fileStatus);

}
