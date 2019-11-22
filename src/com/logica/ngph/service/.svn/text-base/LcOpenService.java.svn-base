package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.LcMastDto;

public interface LcOpenService {
	public List<LCCanonicalDto> getDate(String lcNumber);
	public LCCanonicalDto getLCScreenData(String lcNumber);
	public List<LcCommodity> getCommodityDetails(String lcNumber);
	public String saveLC(LCCanonicalDto canonicalDto,List <LcCommodity> commodityList);
	public String getLcStatus(String lcNumber);
	public String saveChangeStatus(String lcNumber,String status);
	public List<LCCanonicalDto> getLCNumber(String lcNumber);
	public List<LcMastDto> displayLcNumber();
	public List<LCCanonicalDto> getTranferDocumentaryData(String lcNumber,String status,String direction);
}
