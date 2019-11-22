package com.logica.ngph.daoImpl;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.dao.InformationDao;
import com.logica.ngph.dtos.InformationDto;

public class InformationDaoImpl extends HibernateDaoSupport implements InformationDao {
	static Logger logger = Logger.getLogger(InformationDaoImpl.class);
	
	public List<InformationDto> getReportDate(String direction) {
		String queryString="";
		List<InformationDto> result = new ArrayList();
		
		queryString ="select msgRef,direction,information,squenceNo,department,branch,msgType,subMsgType,txnReference," +
					"senderBank,receiverBank,relatedRefrence,lastModTime,msgStatus from Information where direction = '"+direction +"' order by lastModTime desc";
		
		List list = getHibernateTemplate().find(queryString);
		for(int i =0 ;i<list.size();i++){
			InformationDto informationDto = new InformationDto();
			Object[] obj = (Object[]) list.get(i);
			informationDto.setMsgRef((String) obj[0]);
			informationDto.setDirection((String) obj[1]);
			informationDto.setInformation((String) obj[2]);
			informationDto.setSquenceNo((String) obj[3]);
			informationDto.setDepartment((String) obj[4]);
			informationDto.setBranch((String) obj[5]);
			informationDto.setMsgType((String) obj[6]);
			informationDto.setSubMsgType((String) obj[7]);
			informationDto.setTxnReference((String) obj[8]);
			informationDto.setSenderBank((String) obj[9]);
			informationDto.setReceiverBank((String) obj[10]);
			informationDto.setRelatedRefrence((String) obj[11]);
			informationDto.setLastModTime(obj[12].toString().substring(0,10));
			informationDto.setMsgStatus(PaymentStatusEnum.findEnumByTag((String) obj[13]));
		result.add(informationDto);
		}
		logger.info("Number of records are getting from DB for ="+result.size());
		return result;
	}

	public String getRawInformationMsg(String msgref, String direction) {
		String query = "";
		String rawInformationMsg = "";
		try {
			if (direction.equalsIgnoreCase("I")) 
			{	
				query = "select rawMsg from RawMessage where msgRef = '"+ msgref + "' and rawHost = '9002'";
			} else {
				query = "select rawMsg from RawMessage where msgRef = '"+ msgref + "' and rawHost = 'QNGSYS'";
			}

			List list = getHibernateTemplate().find(query);
			if(!list.isEmpty())
			{
				for (int i = 0; i < list.size(); i++) {
					Clob cb = (Clob) list.get(i);
				 rawInformationMsg = cb.getSubString(1, (int)cb.length());
				}
			}
			return rawInformationMsg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
