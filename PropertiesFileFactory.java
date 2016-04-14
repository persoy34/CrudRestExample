package com.enviance.test;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFileFactory {
	public static Properties getProperties(){
		Properties properties=null;
		try {
			ClassLoader classLoader=PropertiesFileFactory.class.getClassLoader();
			properties=new Properties();
			properties.load(classLoader.getResourceAsStream("enviance.properties"));
			
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		return properties;
	}

}
