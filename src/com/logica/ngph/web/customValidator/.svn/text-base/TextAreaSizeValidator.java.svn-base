package com.logica.ngph.web.customValidator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.logica.ngph.web.action.AuditServiceUtil;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class TextAreaSizeValidator extends FieldValidatorSupport{
	 private static Logger logger  = Logger.getLogger(TextAreaSizeValidator.class);
	 public void validate(Object object) throws ValidationException{
		 try {
			String fieldName = getFieldName();
		//	System.out.println("fiels name " + fieldName);
			String fieldValue = getFieldValue(fieldName, object).toString();
			if (StringUtils.isNotEmpty(fieldValue) && fieldValue != null
					&& fieldValue.length() != 0) {
				String[] noOfLines = fieldValue.split("\n");
				int minNumLine = Integer.parseInt(getMaxNumLines());
				int maxCharsInTextAr = Integer.parseInt(getMaxCharsField());
				int maxCharInLine = Integer.parseInt(getMaxCharsField());
				if (fieldValue.length() > maxCharsInTextAr) {
					//System.out.println(maxCharsInTextAr);
					addFieldError(fieldName, object);
				} else if (noOfLines.length > minNumLine) {
					//System.out.println(minNumLine);
					addFieldError(fieldName, object);
				} else {
					for (int i = 0; i < noOfLines.length; i++) {
						String strLine = noOfLines[i];
						if (strLine.length() > maxCharInLine) {
							//System.out.println(strLine.length());
							addFieldError(fieldName, object);
						}
					}
				}
			}
		} catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
	 }
	 private String fieldName;
	 private String maxNumLines;
	 private String maxCharInLine;
	 private String maxCharsField;
	 public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
	 public String getMaxNumLines() {
		return maxNumLines;
	}
	public void setMaxNumLines(String maxNumLines) {
		this.maxNumLines = maxNumLines;
	}
	
	 public String getMaxCharsField() {
		return maxCharsField;
	}
	public void setMaxCharsField(String maxCharsField) {
		this.maxCharsField = maxCharsField;
	}
	
	public String getMaxCharInLine() {
		return maxCharInLine;
	}
	public void setMaxCharInLine(String maxCharInLine) {
		this.maxCharInLine = maxCharInLine;
	}

}
