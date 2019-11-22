/**
 * 
 */
package com.logica.ngph.daoImpl;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.CreateBankGuaranteeHistDao;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;

/**
 * @author chakkar
 *
 */
public class CreateBankGuaranteeHistDaoImpl extends HibernateDaoSupport implements
		CreateBankGuaranteeHistDao {

	/* (non-Javadoc)
	 * @see com.logica.ngph.dao.CreateBankGuaranteeHistDao#getBankGuarantee(java.lang.String)
	 */
	@Override
	public CreateBankGuaranteeDto getBankGuarantee(String msgRef) {
		String query = "select canonical.txnReference,canonical.lcPrevAdvRef," +//2
		" canonical.lcIssueDt, canonical.lcAppRulesCode, canonical.lcAppRulesDesc," +//3
		" canonical.receiverBank, canonical.instructionsForCrdtrAgtText,canonical.msgRef, canonical.lcDocsReq1, canonical.seqNo, canonical.msgRef, canonical.senderBank, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=?"  ;
			
			List list = getHibernateTemplate().find(query,msgRef.trim());
			CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
			
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				createBankGuaranteeDto.setBgNumber((String)obj[0]);//20
				createBankGuaranteeDto.setBgCreateType((String) obj[1]);//23
				createBankGuaranteeDto.setBgDate((Date) obj[2]);//30
				createBankGuaranteeDto.setBgRuleCode((String)obj[3]);//40C
				createBankGuaranteeDto.setBgRuleNarration((String)obj[4]);//40C
				createBankGuaranteeDto.setAdvisingBank((String)obj[5]);//Receiver Bank IFSC
				createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[6]);//72
				createBankGuaranteeDto.setMsgRef((String)obj[7]);//Message Reference
				createBankGuaranteeDto.setBgDetails((String)obj[8]);//77C
				createBankGuaranteeDto.setSeqNo((String)obj[9]);//Sequence
				createBankGuaranteeDto.setMsgRef((String)obj[10]);//Message Reference
				createBankGuaranteeDto.setIssuingBankCode((String)obj[11]);//Sender Bank IFSC
				createBankGuaranteeDto.setMsgHost((String)obj[12]);//Host
			}
			return createBankGuaranteeDto;
	}

	/* (non-Javadoc)
	 * @see com.logica.ngph.dao.CreateBankGuaranteeHistDao#getAmendBg(java.lang.String)
	 */
	@Override
	public CreateBankGuaranteeDto getAmendBg(String msgRef) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.logica.ngph.dao.CreateBankGuaranteeHistDao#getAckBg(java.lang.String)
	 */
	@Override
	public CreateBankGuaranteeDto getAckBg(String msgRef) {
		return null;
	}

}
