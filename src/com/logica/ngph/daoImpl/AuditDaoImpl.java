package com.logica.ngph.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.EventAudit;
import com.logica.ngph.Entity.EventMaster;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.AuditDao;
import com.logica.ngph.dtos.LCCanonicalDto;

public class AuditDaoImpl extends HibernateDaoSupport implements AuditDao {
	static Logger logger = Logger.getLogger(AuditDaoImpl.class);
	

	public void saveEventMaster(EventMaster eventMaster) throws SQLException {
		getHibernateTemplate().saveOrUpdate(eventMaster);
		
	}


	public void saveEventAudit(EventAudit eventAudit) throws SQLException {
		getHibernateTemplate().saveOrUpdate(eventAudit);
		
	}
	
	public void saveEventAuditData(EventAudit eventAudit) throws SQLException {
		getHibernateTemplate().save(eventAudit);
		
	}

}
