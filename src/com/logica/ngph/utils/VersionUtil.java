package com.logica.ngph.utils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @version $Revision:   1.0  $ $Date:   Dec 24 2013 15:00:00  $
 * @author Subbu
 */
public class VersionUtil
{
    static Log logger = LogFactory.getLog(VersionUtil.class);
    private static String VERSION_FILE = "NGPH_version.properties";
    private static String BUILD_VERSION = "major_version";
    private static String MINOR_VERSION = "minor_versions";
    private static String PRODUCT_NAME = "product_name";
    private static String PRODUCT_CODE = "product_code";
    
    private static Properties versionProperties = null;
    
    // It returns minor version which includes major version no in it
    public static String getVersion()
    {
        StringBuffer output = new StringBuffer();
        Properties versionProps = getPropertiesFile();
        if ( StringUtils.isNotBlank( versionProps.getProperty(MINOR_VERSION,"") ))
        {
        	output.append(versionProps.getProperty(MINOR_VERSION));
        }
        else if ( versionProps.containsKey(BUILD_VERSION))
        {
            output.append(versionProps.getProperty(BUILD_VERSION));
        } else
        {
            output.append("-unlabeled-");
        }
        return output.toString();
    }
    
    // It returns product name
    public static String getProductName()
    {
        StringBuffer output = new StringBuffer();
        if (null==versionProperties)
        	getPropertiesFile();
        	        
        if ( versionProperties.containsKey(PRODUCT_NAME))
        	output.append(versionProperties.getProperty(PRODUCT_NAME));
        {
            output.append("-unlabeled-");
        }
        return output.toString();
    }
    
    // It returns product code
    public static String getProductCode()
    {
    	StringBuffer output = new StringBuffer();
    	
    	 if (null==versionProperties)
         	getPropertiesFile();
        
        if ( versionProperties.containsKey(PRODUCT_CODE))
        	output.append(versionProperties.getProperty(PRODUCT_CODE));
        {
            output.append("-unlabeled-");
        }
        return output.toString();
    }       
   

    public static String getVersion(String q5VersionOutput)
    {
            StringBuffer output = new StringBuffer(q5VersionOutput);
            output.append("\nBuild          ").append(getVersion());
            return output.toString();
    }

    // It loads property details from NGPH_version.properties file
    private static Properties getPropertiesFile()
    {
    	if (versionProperties == null) {
    		InputStream is = null;
    		try
	        {
	            Properties versionProps = new Properties();
	            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(VERSION_FILE);
	            if(is != null)
	            {
	                versionProps.load(is);
	                versionProperties = versionProps;
	            } else {
	            	return new Properties();
	            }
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	            logger.error("",e);
	            return new Properties();
	        } finally
	        {
	        	if (is != null) try { is.close(); } catch (Exception e) {}
	        }
    	}
        return versionProperties;
    }

    public static void main(String[] args)
    {
        System.out.println("Build information found in current classpath:");
        System.out.println(getVersion());
		System.out.println(getProductName());
		System.out.println(getProductCode());
    }
}
