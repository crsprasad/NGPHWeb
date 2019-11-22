package com.logica.ngph.web.utils;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.logica.ngph.Entity.Functions;


public class UserRolesAccess implements SessionAware {
	private Map<String, Object> session;
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	public boolean checkForRole(List<Functions> functionsList,String functionID)
	{
		 
		int count =0;
		for(int i = 0 ;i<functionsList.size();i++)
		{
			Functions functions  = functionsList.get(i);
			if(functions.getFieldId().equals(functionID.trim()))
			{
				count =1;
				break;
			}
		}
		if(count == 1)
		{
			return true;
		}else
		{
			return false;
		}
		
	}
}
