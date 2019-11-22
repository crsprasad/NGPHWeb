package com.logica.ngph.service;

import java.math.BigDecimal;

public interface AuthorizationMaitenanceService {
	public String insertDataInTA_AUTHSCHEMAM(String Branch, String HostId,
			String channelType, String msgType,String SubMsgType,
			String Direction,String Currency,BigDecimal fromAmount,BigDecimal toAmount,
			String groupID,int groupSequence);

}
