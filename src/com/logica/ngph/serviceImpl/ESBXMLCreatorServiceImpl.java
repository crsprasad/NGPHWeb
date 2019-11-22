package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.EI;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.ESBXMLCreatorDao;
import com.logica.ngph.dtos.searchEI_IDDto;
import com.logica.ngph.service.ESBXMLCreator;

public class ESBXMLCreatorServiceImpl implements ESBXMLCreator{
	
	ESBXMLCreatorDao ESBXMLCreatorDao;

	

	public ESBXMLCreatorDao getESBXMLCreatorDao() {
		return ESBXMLCreatorDao;
	}



	public void setESBXMLCreatorDao(ESBXMLCreatorDao eSBXMLCreatorDao) {
		ESBXMLCreatorDao = eSBXMLCreatorDao;
	}



	public String insertRecordInTA_EI(EI ei) throws SQLException {
		String returnvalue=ESBXMLCreatorDao.insertRecordInTA_EI(ei);
		// TODO Auto-generated method stub
		return returnvalue;
	}
	public String updateEI(EI ei) throws SQLException {
		String returnvalue=ESBXMLCreatorDao.updateEI(ei);
		// TODO Auto-generated method stub
		return returnvalue;
	}
	public String deleteEI(EI ei) throws SQLException {
		String returnvalue=ESBXMLCreatorDao.deleteEI(ei);
		// TODO Auto-generated method stub
		return returnvalue;
	}



	
	public String checkForPrimaryKey(String EI_ID) {
		String checkForPrimary= ESBXMLCreatorDao.checkForPrimaryKey(EI_ID);
		return checkForPrimary;
	}



	
	public List<searchEI_IDDto> dataRuleIDSearch(String code) {
		 List<searchEI_IDDto>	searchList = null;
			try{
					searchList = ESBXMLCreatorDao.dataEI_IDSearch(code);
				
			
				} catch (NGPHException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return searchList;
	}

}
