package org.wtt.docker.listener;

import java.io.FileInputStream;
import java.util.Properties;

@SuppressWarnings("all")	
public class PropertiesUtility {
	
	    public static Properties properties;
     	public static Properties loadProperties(String path) {
		Properties properties=new Properties();
		try (FileInputStream st=new FileInputStream(path)){
			properties.load(st);
			setProperies(properties);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

		public static Properties getProperies() {
			return properties;
		}

		public static void setProperies(Properties properies) {
			PropertiesUtility.properties = properies;
		}

		
     public static Properties loadApplicationProperties() {
 		Properties properties=loadProperties("src/test/resources/application.properties");
  		return properties;
    	 
     }
     
 

}
