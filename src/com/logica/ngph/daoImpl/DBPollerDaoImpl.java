package com.logica.ngph.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.MsgsPolledDto;
import com.logica.ngph.dao.DBPollerDao;
import com.logica.ngph.dtos.SodEodTaskTDto;



import com.logica.ngph.utils.NgphCanonicalUtil;
import com.logica.ngph.utils.SodEodUtil;

public class DBPollerDaoImpl extends HibernateDaoSupport implements DBPollerDao{

	
	public List<MsgsPolledDto> getPolledMessages() {
		
		/*String polledMessageQry ="select polled.msgsRef ,polled.lastOrchServiceId from MsgsPolled as polled "+
		"inner join NgphCanonical as canonical on polled.msgsRef = canonical.msgRef ";*/
		String polledMessageQry ="select polled.msgsRef ,polled.lastOrchServiceId,canonical.msgValueDate,canonical.msgStatus,canonical.msgPrevStatus,canonical.msgBranch from MsgsPolled as polled,NgphCanonical as canonical where polled.msgsRef = canonical.msgRef "+
		"and polled.pollStatus is null and polled.markedOutTime is null and canonical.msgStatus='3'";
		
		//"where polled.pollStatus is null and polled.markedOutTime is null";
		/*String query = "select  P.MSGS_MSGREF, P.LASTORCHSERVICEIDCALLED , T.MSGS_INTRBKSTTLMDT , T.MSGS_MSGSTS , T.MSGS_PREVMSGSTS,T.MSGS_BRANCH" +
		" from TA_MSGSPOLLED P   inner join TA_MESSAGES_TX T on  P.MSGS_MSGREF = T.MSGS_MSGREF and P.MARKED_OUT_TIME is null and P.POLL_STATUS is null "+ 
		"and T.MSGS_MSGSTS = 3";*/
		
		//List msgPolledList = getHibernateTemplate().find(polledMessageQry);
		@SuppressWarnings("rawtypes")
		List msgPolledList = getHibernateTemplate().find(polledMessageQry);
		List<MsgsPolledDto> msgPolledDtoList = new ArrayList<MsgsPolledDto>();
		for (int i = 0; i < msgPolledList.size(); i++) {
			MsgsPolledDto msgsPolledDto = new MsgsPolledDto();
            Object[] obj = (Object[]) msgPolledList.get(i);
            msgsPolledDto.setMsgsRef((String)obj[0]);
            msgsPolledDto.setLastOrchServiceIdCalled((String)obj[1]);
            msgsPolledDto.setSettlementDate((Timestamp)obj[2]);
            msgsPolledDto.setMsgsStatus((String)obj[3]);
            msgsPolledDto.setMsgsPrevStatus((String)obj[4]);
            msgsPolledDto.setBranchName((String)obj[5]);
           
            msgPolledDtoList.add(msgsPolledDto);
        }
	
		return msgPolledDtoList;
		
	}
	
	/*public String getBusinessDay(String branchCode){
		
		String businessDayQuery = "select businessDay from BusinessDayM where branch=?";
		
		// have to load the hibernate template in constructor
		String businessDay = getHibernateTemplate().find(businessDayQuery, branchCode).toString();
		
		return businessDay;
	}*/
	private void saveSodEodT(SodEodTaskTDto sodEodTaskTDto) {
		
		getHibernateTemplate().saveOrUpdate(SodEodUtil.converSOdEodTDtoToEntity(sodEodTaskTDto));	
	
	}
	@SuppressWarnings("rawtypes")
	public void perFormDbPoll(final Timestamp businesDate ,final String prevMessageStatus,final String messageRef,SodEodTaskTDto sodEodTaskTDto) throws NGPHException {
		saveSodEodT(sodEodTaskTDto);
		
		
			getHibernateTemplate().executeFind(new HibernateCallback() {
				
				 public Object doInHibernate(Session session) throws HibernateException, SQLException {
					 Query updateNgphCanonicalQuery ;
					 updateNgphCanonicalQuery = session.createQuery("update NgphCanonical set msgValueDate =:businesDate, lastModTime = sysdate ,msgPrevStatus = '3',msgStatus = '2'  where msgRef = :msgRef");
					// updateNgphCanonicalQuery.setTimestamp("valueDate",convertStringToTimeStamp(businesDate));
					 updateNgphCanonicalQuery.setTimestamp("businesDate", businesDate);
					 updateNgphCanonicalQuery.setString("msgRef",messageRef.trim());
			              int i=  updateNgphCanonicalQuery.executeUpdate();
			              
			              logger.debug("number of rows affected"+i);
			              Query updateTaMessagePolledQuery ;
			  			
							
			              updateTaMessagePolledQuery = session.createQuery("update MsgsPolled set markedOutTime = sysdate ,pollStatus = 'C' where msgsRef = :msgRef");
			              updateTaMessagePolledQuery.setString("msgRef",messageRef.trim());
					            
					              int j=  updateTaMessagePolledQuery.executeUpdate();
					              
					              logger.debug("number of rows affected"+j);
		          
		                return null;
		            }
		        });
		

}
/*private Timestamp convertStringToTimeStamp(String dateString){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	java.util.Date date=null;
	java.sql.Timestamp timest = null;
	try {
		date = sdf.parse(dateString);
		 timest = new java.sql.Timestamp(date.getTime()); 
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return timest;
}*/

public com.logica.ngph.common.dtos.NgphCanonical getMessage(String messageRef){
	@SuppressWarnings("unchecked")
	List<NgphCanonical> canonicalEntityList = 	getHibernateTemplate().find("from NgphCanonical where msgRef=?", messageRef);
	NgphCanonical ngphCanonical = null;
	
	if(canonicalEntityList.size()>0){
		ngphCanonical = canonicalEntityList.get(0);
	}
	return NgphCanonicalUtil.ngphCanonicalEntityToDto(ngphCanonical);
}


public Timestamp getBusinessDate(String branchCode) {
	String businessDayQuery = "select businessDay from BusinessDayM where branch=?";
	Timestamp businessDay = null;
	// have to load the hibernate template in constructor
	@SuppressWarnings("rawtypes")
	List businessDayList = getHibernateTemplate().find(businessDayQuery, branchCode);
	if(businessDayList != null && businessDayList.size() > 0){
		businessDay = (Timestamp)businessDayList.get(0);
	}
	
	return  businessDay;

}


}
