package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.HelpData;

public interface HelpService {

	public List<HelpData> fetchHelpData() throws NGPHException;

}
