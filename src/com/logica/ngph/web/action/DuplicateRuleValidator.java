package com.logica.ngph.web.action;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class DuplicateRuleValidator extends FieldValidatorSupport{

	
	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		String fieldValue = (String) getFieldValue(fieldName, object );
		
		if (!(fieldValue instanceof String)) {
			return;
			}

			String str = ((String) fieldValue).trim();
			if (str.length() == 0) {
			return;
			}

			try {
			        Double.parseDouble(str);
			}catch(NumberFormatException nfe) {
			        addFieldError(fieldName, object);
			return;
			}
			try {
			        Integer.parseInt(str);
			}catch(NumberFormatException nfe) {
			        addFieldError(fieldName, object);
			return;
			}

			}

}
