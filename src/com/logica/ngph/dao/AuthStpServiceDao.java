package com.logica.ngph.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.logica.ngph.dtos.AuthMsgPolled;

public interface AuthStpServiceDao {
	
	public List<AuthMsgPolled> getMsgsPolled(String key);
	
	public Map<String, Object> getGroupInfo(List<AuthMsgPolled> vals, String key);
	
	public ArrayList<Object> getSupInfo(Map<String,Object> hm);
	
	public void insertData(List<Object> al,Map<String, Object> GroupInfo, String key, List<AuthMsgPolled> vals);
}
