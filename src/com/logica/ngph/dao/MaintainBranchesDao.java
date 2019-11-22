package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.Addresses;
import com.logica.ngph.Entity.Branches;
import com.logica.ngph.dtos.MaintainBranchesDTO;


public interface MaintainBranchesDao {
	
	public String saveData(Branches branches,Addresses addresses,String action,String oldRef);
	public String getSequence();
	
	public Boolean isBranchCodePresent(String code);
	
	public List<MaintainBranchesDTO> getALLBranch(String code);

}
