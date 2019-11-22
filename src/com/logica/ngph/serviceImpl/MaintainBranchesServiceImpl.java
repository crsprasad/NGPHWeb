package com.logica.ngph.serviceImpl;

import java.util.List;

import com.logica.ngph.Entity.Addresses;
import com.logica.ngph.Entity.Branches;
import com.logica.ngph.dao.MaintainBranchesDao;
import com.logica.ngph.dtos.MaintainBranchesDTO;
import com.logica.ngph.service.MaintainBranchesService;

public class MaintainBranchesServiceImpl implements MaintainBranchesService{
	MaintainBranchesDao maintainBranchesDao; 
	public MaintainBranchesDao getMaintainBranchesDao() {
		return maintainBranchesDao;
	}
	public void setMaintainBranchesDao(MaintainBranchesDao maintainBranchesDao) {
		this.maintainBranchesDao = maintainBranchesDao;
	}
	
	public String saveData(MaintainBranchesDTO maintainBranchesDTO) {
		String seq =maintainBranchesDao.getSequence();
		String branchName= maintainBranchesDTO.getBranchName();
		int branchlength = branchName.length();
		if(branchlength>4)
		{
			branchName = branchName.substring(branchlength-5, branchlength-1);
		}
		else if(branchlength<4)
		{
			if(branchlength==3)
					branchName = "0"+branchName;
			else if(branchlength==2)
				branchName = "00"+branchName;
			else if(branchlength==1)
				branchName = "000"+branchName;
		}
		String createdSequency="";
		if(seq.length()==1)
		createdSequency = "00000"+seq;
		else if(seq.length()==2)
			createdSequency = "0000"+seq;
		else if(seq.length()==3)
			createdSequency = "000"+seq;
		else if(seq.length()==4)
			createdSequency = "00"+seq;
		else if(seq.length()==5)
			createdSequency = "0"+seq;
		else if(seq.length()==6)
			createdSequency = "0000"+seq;
		
		String generatedAddr_ref = "BR"+branchName+createdSequency;
		
		maintainBranchesDao.saveData(changeDtoTOBranchEntity(maintainBranchesDTO,generatedAddr_ref),changeDtoTOAddressesEntity(maintainBranchesDTO,generatedAddr_ref)
				,maintainBranchesDTO.getActionPerform(),maintainBranchesDTO.getAddressRef());
		
		return "success";
	}
	
	public Branches changeDtoTOBranchEntity(MaintainBranchesDTO maintainBranchesDTO ,String addr_ref)
	{
		Branches branches = new Branches();
		if(maintainBranchesDTO.getActionPerform().equals("ADD"))
		branches.setBranchAddressRef(addr_ref);
		else
			branches.setBranchAddressRef(maintainBranchesDTO.getAddressRef());
		branches.setBranchBicCode(maintainBranchesDTO.getBranchBIC());
		branches.setBranchCategory(maintainBranchesDTO.getCountryCode());
		branches.setBranchCode(maintainBranchesDTO.getBranchCode());
		branches.setBranchIFSCCode(maintainBranchesDTO.getBranchIFSC());
		branches.setBranchName(maintainBranchesDTO.getBranchName());
		return branches;
	}
	public Addresses changeDtoTOAddressesEntity(MaintainBranchesDTO maintainBranchesDTO,String addr_ref )
	{
		Addresses addresses = new Addresses();
		addresses.setAddressFor("B");
		if(maintainBranchesDTO.getActionPerform().equals("ADD"))
		addresses.setAddressRef(addr_ref);
		else
			addresses.setAddressRef(maintainBranchesDTO.getAddressRef());
		addresses.setBranchReference(maintainBranchesDTO.getBranchCode());
		addresses.setBuildingDetail(maintainBranchesDTO.getBuildingNumber());
		addresses.setSubDepartment(maintainBranchesDTO.getSubDepartment());
		addresses.setStreetName(maintainBranchesDTO.getStreetName());
		
		addresses.setCityName(maintainBranchesDTO.getCountryCode());
		addresses.setCitySubDivision(maintainBranchesDTO.getCountrySubDivision());
		addresses.setDepartment(maintainBranchesDTO.getDepartment());
		addresses.setPostalAddressNature(maintainBranchesDTO.getAddressType());
		addresses.setPostalCode(maintainBranchesDTO.getPostCode());
		addresses.setPostalServiceAddress(maintainBranchesDTO.getAddressLine());
		addresses.setTownName(maintainBranchesDTO.getTownName());
		addresses.setSeq("1");
		
		
		return addresses;
	}
	
	public Boolean isBranchCodePresent(String code) {
		// TODO Auto-generated method stub
		return maintainBranchesDao.isBranchCodePresent(code);
	}
	
	public List<MaintainBranchesDTO> getALLBranch(String code) {
		// TODO Auto-generated method stub
		return maintainBranchesDao.getALLBranch(code);
	}

}
