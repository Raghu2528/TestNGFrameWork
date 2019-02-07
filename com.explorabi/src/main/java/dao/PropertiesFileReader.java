package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
	private Properties properties;
	private final String propertyFilePath= "src//main//java//Config.properties";
	public PropertiesFileReader(){
		 BufferedReader reader;
		 try {
		 reader = new BufferedReader(new FileReader(propertyFilePath));
		 properties = new Properties();
		 try {
		 properties.load(reader);
		 reader.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 throw new RuntimeException("Config.properties not found at " + propertyFilePath);
		 } 
		 }
		 
		 public String getchromeDriver(){
		 String chromeDriver = properties.getProperty("chromeDriver");
		 if(chromeDriver!= null) return chromeDriver;
		 else throw new RuntimeException("chromeDriver not specified in the Configuration.properties file."); 
		 }
		 
		 public long getImplicitlyWait() { 
		 String implicitlyWait = properties.getProperty("implicitlyWait");
		 if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		 else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
		 }
		 
		 public String getApplicationUrl() {
		 String url = properties.getProperty("url");
		 if(url != null) return url;
		 else throw new RuntimeException("url not specified in the Configuration.properties file.");
		 }
		 
		 public String getUserName() {
			 String userName = properties.getProperty("userName");
					 if(userName!=null) return userName;
					 else throw new RuntimeException("userName not specified in the Configuration.properties file."); 
		 }
		 public String getPassword() {
			 String password = properties.getProperty("password");
					 if(password!=null) return password;
					 else throw new RuntimeException("userName not specified in the Configuration.properties file."); 
		 }
}
