package br.pucminas.logistica.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public class BundleResource {
	
	private static BundleResource instance;
	
	private static InputStream inputStream;
	
	private static Properties properties;
		
	public static BundleResource getInstance(ServletContext context) throws IOException{
		if(instance == null){
			instance = new BundleResource(context);
		}	
		return instance;
	}
	
	private BundleResource(ServletContext context) throws IOException{
		if(inputStream == null){
			inputStream = context.getResourceAsStream("/WEB-INF/messages.properties");
			properties = new Properties();
			properties.load(inputStream);
		}
	}

	
	public static String getMessage(String key){
		return properties.getProperty(key);
	}
	
}
