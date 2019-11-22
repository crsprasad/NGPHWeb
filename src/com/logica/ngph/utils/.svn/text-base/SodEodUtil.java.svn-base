package com.logica.ngph.utils;

import java.sql.Timestamp;
import java.util.Calendar;

import com.logica.ngph.Entity.SODEODTASKT;
import com.logica.ngph.dtos.SodEodTaskTDto;

public class SodEodUtil {
	
	
public static SODEODTASKT converSOdEodTDtoToEntity(SodEodTaskTDto sodEodTaskTDto){
	SODEODTASKT sodeodtaskt = new SODEODTASKT();
	sodeodtaskt.setBranch(sodEodTaskTDto.getBranch());
	sodeodtaskt.setBusinessDate(sodEodTaskTDto.getBusinessDate());
	sodeodtaskt.setSodOrEod(sodEodTaskTDto.getSodOrEod());
	sodeodtaskt.setTaskId(sodEodTaskTDto.getTaskId());
	sodeodtaskt.setUserId(sodEodTaskTDto.getUserId());
	sodeodtaskt.setStartTime(getCurrentTime());
	sodeodtaskt.setStatus(1);
	return sodeodtaskt;
}
/**
* This method is used to get the current timestamp
* @return Timestamp timeStampDate
*/
public static Timestamp getCurrentTime(){
	java.util.Date 	str_date = Calendar.getInstance().getTime();
	java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
	
	return timeStampDate;
}

}
