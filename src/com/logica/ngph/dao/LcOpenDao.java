package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.LcMastDto;

public interface LcOpenDao {
	public List<LCCanonicalDto> getLcDate(String lcNumber) ;
	public LCCanonicalDto getLcScreenData(String lcNumber); 
	public List<LcCommodity> getCommodityDetails(String lcNumber);
	public String getLcStatus(String lcNumber);
	public String saveChangeStatus(String lcNumber, String status);
	public List<LCCanonicalDto> getLCNumber(String lcNumber);
	public List<LcMastDto>  displayLcNumber();
	public List<LCCanonicalDto> getTranferDocumentaryData(String lcNumber,String status,String direction);

}
