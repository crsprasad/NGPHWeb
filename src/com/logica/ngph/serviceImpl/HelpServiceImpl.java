package com.logica.ngph.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.HelpData;
import com.logica.ngph.service.HelpService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.io.InputStream;

public class HelpServiceImpl implements HelpService {

	static Log logger = LogFactory.getLog(HelpServiceImpl.class);
	/*private String buildMajorversion;
	private String buildMinorversion;
	private String productName;
	private String productCode;*/

	

	private static String VERSION_FILE = "NGPH_version.properties";
	private static String BUILD_VERSION = "major_version";
	private static String MINOR_VERSION = "minor_versions";
	private static String PRODUCT_NAME = "product_name";
	private static String PRODUCT_CODE = "product_code";

	private static Properties versionProperties = null;

	// It returns minor version which includes major version no in it
	public List<HelpData> fetchHelpData() throws NGPHException {

		List<HelpData> helpdataList = new ArrayList<HelpData>();
		HelpData helpData = new HelpData();
		Properties versionProps = getPropertiesFile();
		if (StringUtils.isNotBlank(versionProps.getProperty(MINOR_VERSION,"") )) {
			helpData.setMinorVersion(versionProps.getProperty(MINOR_VERSION));
		} else {
			logger.info("Version Details are Empty");
		}
		if (StringUtils.isNotBlank(versionProps.getProperty(BUILD_VERSION,""))) {
			helpData.setMajorVersion(versionProps.getProperty(BUILD_VERSION));
		} else {
			logger.info("Version Details are Empty");
		}
		// getting Product Name
		if (StringUtils.isNotBlank(versionProps.getProperty(PRODUCT_NAME,""))) {
			helpData.setProductName(versionProps.getProperty(PRODUCT_NAME));
		} else {
			logger.info("Product Name is Empty");
		}

		// getting Project Code
		if (StringUtils.isNotBlank(versionProps.getProperty(PRODUCT_CODE,""))) {
			helpData.setProductCode(versionProps.getProperty(PRODUCT_CODE));
		} else {
			logger.info("Product Code is Empty");
		}
		helpdataList.add(helpData);
		return helpdataList;
	}

	// It loads property details from NGPH_version.properties file
	private static Properties getPropertiesFile() {
		if (versionProperties == null) {
			InputStream is = null;
			try {
				Properties versionProps = new Properties();
				is = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(VERSION_FILE);
				if (is != null) {
					versionProps.load(is);
					versionProperties = versionProps;
				} else {
					return new Properties();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return new Properties();
			} finally {
				if (is != null)
					try {
						is.close();
					} catch (Exception e) {
					}
			}
		}
		return versionProperties;
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println("Build information found in current classpath:");
	 * System.out.println(getVersion()); System.out.println(getProductName());
	 * System.out.println(getProductCode()); }
	 */
}
