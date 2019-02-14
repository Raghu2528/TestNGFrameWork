package DateSourcesTests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	private static final int ArrayList = 0;
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

/*//@Test(priority=0,enabled=true)
public  void verifycCeateSqlServerDatasourceWithValidDetails() throws InterruptedException {
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

//@Test(priority=1,enabled=true)
public  void verifyCreateSqlServerDatasourceWithInvalidDetails() throws InterruptedException {
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.connectLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.createDsLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.verifyText(ObjectProperties.CreateDatasouce.createDsPageTitle, "Create Data Connection");
	GenericMethods.createDs("SQLServer");
	GenericMethods.dataSourceDetails("sqlserver_0000", "ggk-wrl-exp-004.ggktech.local11111111", "0000", "saa", "Welcome@456");
	
	GenericMethods.datasourceSavePopups();
}*/

@Test(priority=0,enabled=true)
public  void sampleTest() throws InterruptedException {
	List<String> values = new ArrayList<String>();
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.viewDsLink);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	GenericMethods.buttonClick(ObjectProperties.HomePageElements.navigateToGrid);
	Thread.sleep(propertyFileReader.getImplicitlyWait());
	List<WebElement> xpaths = driver.findElements(By.xpath("//table[@st-table='displayedCollection']//tbody//tr"));
	int count = xpaths.size();
	for(int i=0;i<count;i++) {
		String text = driver.findElement(By.xpath("//table[@st-table='displayedCollection']//tbody//tr//td[2]")).getText().trim();
		values.add(text);
	}
		System.out.println(values);
}

/*
	@AfterTest
	public void quitBrowser() {
		driver.close();
		
	}*/
}
