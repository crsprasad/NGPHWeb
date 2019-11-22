package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.Entity.Branches;
import com.logica.ngph.dtos.MaintainBranchesDTO;

public interface MaintainBranchesService {

	public String saveData(MaintainBranchesDTO maintainBranchesDTO);
	
	public Boolean isBranchCodePresent(String code);
	public List<MaintainBranchesDTO> getALLBranch(String code);

}
