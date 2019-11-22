/**
 * 
 */
package com.logica.ngph.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.PasswordSecurityPolicyDao;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.dtos.SecurityQuestionsDTO;

/**
 * @author chakkar
 *
 */
public class PasswordSecurityPolicyDaoImpl extends HibernateDaoSupport implements PasswordSecurityPolicyDao {
	
	
private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public PasswordSecurityPolicyDto getSecurityPolicy() throws SQLException {
		
		PasswordSecurityPolicyDto passwordSecurityPolicyDto = new PasswordSecurityPolicyDto();
		
		List policyList = getHibernateTemplate().find("select minChars, maxChars, noofDigits, noofSpecialChars, noofUpperChars, noofLowerChars, passwordExpDays, maxNoofPassChangesDay, lastPolicyModifiedDateTime from com.logica.ngph.Entity.SecurityPolicy ");
		for (int i = 0; i < policyList.size(); i++) {
			
			Object[] obj = (Object[]) policyList.get(i);
			passwordSecurityPolicyDto.setMinChars((Integer) obj[0]);
			passwordSecurityPolicyDto.setMaxChars((Integer) obj[1]);
			passwordSecurityPolicyDto.setNoofDigits((Integer) obj[2]);
			passwordSecurityPolicyDto.setNoofSpecialChars((Integer) obj[3]);
			passwordSecurityPolicyDto.setNoofUpperChars((Integer) obj[4]);
			passwordSecurityPolicyDto.setNoofLowerChars((Integer) obj[5]);
			passwordSecurityPolicyDto.setPasswordExpDays((Integer) obj[6]);
			passwordSecurityPolicyDto.setMaxNoofPassChangesDay((Integer) obj[7]);
			passwordSecurityPolicyDto.setLastPassPolicyChanges((Timestamp) obj[8]);
		}

		return passwordSecurityPolicyDto;
	}

	@Override
	public void updateSecutiyPolicy(PasswordSecurityPolicyDto passwordSecurityPolicyDto)throws SQLException {
		
		int minChars = passwordSecurityPolicyDto.getMinChars();
		int maxChars = passwordSecurityPolicyDto.getMaxChars();
		int noofUpperChars = passwordSecurityPolicyDto.getNoofUpperChars();
		int noofLowerChars = passwordSecurityPolicyDto.getNoofLowerChars();
		int noofDigits = passwordSecurityPolicyDto.getNoofDigits();
		int noofSpcChars = passwordSecurityPolicyDto.getNoofSpecialChars();
		int expiryDays = passwordSecurityPolicyDto.getPasswordExpDays();
		int maxNoofPassChgPerDay = passwordSecurityPolicyDto.getMaxNoofPassChangesDay();
		
		java.util.Date date= new java.util.Date();
		Timestamp policyUpdatedDate = new Timestamp(date.getTime());
		
		
		try {
			String query = "UPDATE PASSADMINACPOLICY SET MINCHARS =?, MAXCHARS=? , NOOFUPPERCASE = ?, NOOFLOWERCASE = ?, " +
					"NOOFDIGITS = ?, NOOFSPLCHARS = ?, EXPIRYDAYS = ?, MAX_PASSWORD_CHANGES = ?, LAST_PASS_POLICY_CHANGES = ? "; 

			try
			{
				jdbcTemplate.update(query,new Object[]{minChars,maxChars,noofUpperChars,noofLowerChars,noofDigits,noofSpcChars,expiryDays,maxNoofPassChgPerDay,policyUpdatedDate});
				System.out.println("Policy Updated successfully");
			}
			catch (Exception e) 
			{
				logger.error(e,e);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public SecurityQuestionsDTO getSecurityQuestionDetails(String user)
			throws SQLException {
		SecurityQuestionsDTO securityQuestionsDTO = new SecurityQuestionsDTO();
		
		List secQuestionsList = getHibernateTemplate().find("select securityQuesion, securityAnswer, user from com.logica.ngph.Entity.SecUsers where user = ?",user);
		for (int i = 0; i < secQuestionsList.size(); i++) {
			
			Object[] obj = (Object[]) secQuestionsList.get(i);
			securityQuestionsDTO.setSecurityQuesion((String) obj[0]);
			securityQuestionsDTO.setSecurityAnswer((String) obj[1]);
			securityQuestionsDTO.setUser((String) obj[2]);
		}
		return securityQuestionsDTO;
	}

}
