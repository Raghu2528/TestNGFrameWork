package DateSourcesTests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dao.PropertiesFileReader;
import ApplicationLibrary.GenericMethods;
import dao.PropertiesFileReader;

public class DataSourceCreationTestCases extends GenericMethods{
	PropertiesFileReader propertyFileReader;
	//Constructor
	public DataSourceCreationTestCases() {
		propertyFileReader= new PropertiesFileReader();

	}
	 WebDriver driver;
	@Parameters ({"browser"})
	@BeforeTest
	public void openBrowserAndLogin(String browser) throws InterruptedException, IOException {
		GenericMethods.openBrowser(propertyFileReader.getApplicationUrl(),browser);
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		GenericMethods.enterText(ObjectProperties.LoginPageElements.username, propertyFileReader.getUserName());
		GenericMethods.enterText(ObjectProperties.LoginPageElements.password, propertyFileReader.getPassword());
		GenericMethods.buttonClick(ObjectProperties.LoginPageElements.loginBtn);
		Thread.sleep(propertyFileReader.getImplicitlyWait());
	}

@Test(priority=0,enabled=true)
public  void createSqlServerDatasource() throws InterruptedException {
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.connectLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.createDsLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.verifyText(ObjectProperties.CreateDatasouce.createDsPageTitle, "Create Data Connection");
	GenericMethods.createDs("SQLServer");
	GenericMethods.dataSourceDetails("sqlserver_0000", "ggk-wrl-exp-002.ggktech.local", "1433", "saa", "Welcome@456");
	
	GenericMethods.datasourceSavePopups();
}

@Test(priority=1,enabled=true)
public  void createSqlServerDatasource1() throws InterruptedException {
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.connectLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.createDsLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.verifyText(ObjectProperties.CreateDatasouce.createDsPageTitle, "Create Data Connection");
	GenericMethods.createDs("SQLServer");
	GenericMethods.dataSourceDetails("sqlserver_0000", "ggk-wrl-exp-002.ggktech.local11111111", "0000", "saa", "Welcome@456");
	
	GenericMethods.datasourceSavePopups();
}




	@AfterTest
	public void quitBrowser() {
		driver.close();
		
	}
}