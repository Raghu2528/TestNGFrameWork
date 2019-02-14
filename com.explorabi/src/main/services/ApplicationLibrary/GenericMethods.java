package ApplicationLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dao.PropertiesFileReader;

public class GenericMethods extends PropertiesFileReader {
	 static WebDriver driver;
	static PropertiesFileReader propertyFileReader;
	public GenericMethods() {
		propertyFileReader= new PropertiesFileReader();

	}
	
	// Open Browser
	public static void openBrowser(String URL,String key) {
		try {
			switch (key) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
					"E:\\Raghu\\OwnFrameWorkTestNG\\com.explorabi\\src\\main\\resources\\TestData\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			}
			
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
		}
	}


	// Element Exists or not
	public static WebElement verifyElementExist(By byVal) {
		WebElement element = null;
		try {
			int size = driver.findElements(byVal).size();
			// fluentWait(byVal, iSecs);
			if (size != 0) {
				if (size == 1) {
					element = driver.findElement(byVal);
				} else {
					System.out.println("Duplicate Elements");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return element;
	}

	// Wait for an element
	public static void waitForElement(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Wait For Element Failed");
		}
	}

	public static void verifyText(String xpath, String expectedPageText) {
		String actualPageText=driver.findElement(By.xpath(xpath)).getText();
		if(actualPageText.equalsIgnoreCase(expectedPageText)) {
			System.out.println("Text is verified");
		}else
			System.out.println("Test is not verified");
	}
	// Enter Text
	public static void enterText(String xpath, String textToEnter) {
		try {
			verifyElementExist(By.xpath(xpath));
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(textToEnter);
		} catch (Exception e) {
			System.out.println("Element not found sklfdjsfdsfkjsdfkjdsfj");
		}
	}

	// Button Click
	public static void buttonClick(String xpath) {
		verifyElementExist(By.xpath(xpath));
		driver.findElement(By.xpath(xpath)).click();
	}

	public static void createDs(String dataSourceType) {
		switch (dataSourceType) {
		case "SQLServer":
			driver.findElement(By.xpath("//a//span[contains(text(),'SQLServer')]")).click();
			String actualSqlText =driver.findElement(By.xpath("//div[@class='box box-default new-datasource']//span[contains(.,'SQLServer Connection')]")).getText();
			if(actualSqlText.contains("SQLServer Connection")) {
				System.out.println(actualSqlText+  " "+"text verified");
			}else
				System.out.println("Text Not Verified");
			break;
		case "MySQL":
			driver.findElement(By.xpath("//a//span[contains(text(),'MySQL')]")).click();
			String actualMySqlTex= driver.findElement(By.xpath("//div[@class='box box-default new-datasource']//span[contains(.,'MySQL Connection')]")).getText();
			if(actualMySqlTex.contains("MySQL Connection")) {
				System.out.println(actualMySqlTex+"text verified");
			}else
				System.out.println("Text Not Verified");
			break;
		case "ORACLE":
			driver.findElement(By.xpath("//a//span[contains(text(),'ORACLE')]")).click();
			String actualOracleText= driver.findElement(By.xpath("//div[@class='box box-default new-datasource']//span[contains(.,'ORACLE Connection')]")).getText();
			if(actualOracleText.contains("ORACLE Connection")) {
				System.out.println(actualOracleText+"text verified");
			}else
				System.out.println("Text Not Verified");
			break;
		case "EXCEL":
			driver.findElement(By.xpath("//a//span[contains(text(),'Excel')]")).click();
			String actualExcelText = driver.findElement(By.xpath("//div[@class='box box-default new-datasource']//span[contains(.,'Excel Connection')]")).getText();
			if(actualExcelText.contains("Excel Connection")) {
				System.out.println(actualExcelText+"text verified");
			}else
				System.out.println("Text Not Verified");
			break;
		case "CSV":
			driver.findElement(By.xpath("//a//span[contains(text(),'CSV')]")).click();
			String actualCsvText=	driver.findElement(By.xpath("//div[@class='box box-default new-datasource']//span[contains(.,'CSV Connection')]")).getText();
			if(actualCsvText.contains("CSV Connection")) {
				System.out.println(actualCsvText+"text verified");
			}else
				System.out.println("Text Not Verified");
			break;
		case "PostgresSQL":
			driver.findElement(By.xpath("//a//span[contains(text(),'PostgreSQL')]")).click();
			String actualPostgresText =driver.findElement(By.xpath("//div[@class='box box-default new-datasource']//span[contains(.,'PostgreSQL Connection')]")).getText();
			if(actualPostgresText.contains("PostgreSQL Connection")) {
				System.out.println(actualPostgresText+"text verified");
			}else
				System.out.println("Text Not Verified");
			break;
		default:
			System.out.println("Elementnotfound");
			break;
		}
		
	}
	//Passing all datasource details for sqlserver,mysql,oracle and postgres
	public static void dataSourceDetails(String dsName,String dsHost,String dsPort,String dsUsername,String dsPassword) throws InterruptedException {
		enterText(ObjectProperties.CreateDatasouce.dsName, dsName+ getCurrentTimeInstance());
		String text =driver.findElement(By.xpath(ObjectProperties.CreateDatasouce.dsName)).getAttribute("value");
		System.out.println(text);
		enterText(ObjectProperties.CreateDatasouce.dsHost, dsHost);
		enterText(ObjectProperties.CreateDatasouce.dsPort, dsPort);
		enterText(ObjectProperties.CreateDatasouce.dsUsername, dsUsername);
		enterText(ObjectProperties.CreateDatasouce.dsPassword, dsPassword);
		buttonClick(ObjectProperties.CreateDatasouce.dsSaveBtn);	
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		
	}
	//Datasource save popups
	public static void datasourceSavePopups() {
		driver.switchTo().activeElement();
		waitForElement(By.xpath(ObjectProperties.CreateDatasouce.popup));
		String actualPopupText = driver.findElement(By.xpath(ObjectProperties.CreateDatasouce.popup)).getText();
		String expectedPopupText = "Do you want to save the DataSource Connection?";
		String exceptedPopupText1 ="Connection failed. Do you still want to save the DataSource Connection?";
		if(actualPopupText.contains(expectedPopupText)) {
			buttonClick(ObjectProperties.CreateDatasouce.popupOkBtn);
			driver.switchTo().activeElement();
			waitForElement(By.xpath(ObjectProperties.CreateDatasouce.popup));
			String actualPopuText1= driver.findElement(By.xpath(ObjectProperties.CreateDatasouce.popup)).getText();		
			String expectedPopupText1 ="DataSource Connection saved successfully.";
			if(actualPopuText1.contains(expectedPopupText1)) {
				buttonClick(ObjectProperties.CreateDatasouce.popupOkBtn1);
				System.out.println(actualPopuText1);
			}else {
				System.out.println("Element not found in poup1");
			}
		}else if(actualPopupText.contains(exceptedPopupText1))  {
			String actualPopuText2= driver.findElement(By.xpath(ObjectProperties.CreateDatasouce.popup)).getText();	
			System.out.println(actualPopuText2);
		}
	}
	
	public static String getCurrentTimeInstance(){
		return new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());
	}
	
	public void getExcelValues() throws IOException, InvalidFormatException {
		List<String> excelValues =new ArrayList<String>();
		String fileName = "C:\\Users\\raghunath.borra\\Desktop\\TextData123.xls";
		File file = new File(fileName);
		//XSSFWorkbook wb = new XSSFWorkbook(file);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sh = wb.getSheet("sheet");
		int rows = sh.getLastRowNum()-sh.getFirstRowNum();
		for(int i=0;i<rows+1;i++) {
			Row row = sh.getRow(i);
			
			for(int j=0;j<row.getLastCellNum();j++) {
				String data = row.getCell(j).getStringCellValue();
				excelValues.add(data);
				System.out.println(data);
			}
		}
				
	}
	public List<String> getValuesFromApp() throws InterruptedException {
		GenericMethods.openBrowser(propertyFileReader.getApplicationUrl(),"chrome");
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		GenericMethods.enterText(ObjectProperties.LoginPageElements.username, propertyFileReader.getUserName());
		GenericMethods.enterText(ObjectProperties.LoginPageElements.password, propertyFileReader.getPassword());
		GenericMethods.buttonClick(ObjectProperties.LoginPageElements.loginBtn);
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		List<String> values = new ArrayList<String>();
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		GenericMethods.buttonClick(ObjectProperties.HomePageElements.connectLink);
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		GenericMethods.buttonClick(ObjectProperties.HomePageElements.viewDsLink);
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		GenericMethods.buttonClick(ObjectProperties.HomePageElements.navigateToGrid);
		Thread.sleep(propertyFileReader.getImplicitlyWait());
		List<WebElement> xpaths = driver.findElements(By.xpath("//table[@st-table='displayedCollection']//tbody//tr"));
		int count = xpaths.size();
		for(int i=1;i<count+1;i++) {
			String text = driver.findElement(By.xpath("//table[@st-table='displayedCollection']//tbody//tr["+i+"]//td[2]")).getText().trim();
			values.add(text);
		}
			System.out.println(values);
		return values;
	}
	
	public List<String> getValuesFromDB() throws SQLException, ClassNotFoundException {
		String jdbcpath = "jdbc:postgresql://10.118.60.185:5432/explorabi_mgmt_test_v1_8?";
		String username= "postgres";
		String password ="postgres";
		String query = "select name from datasource where createdbyid =117";
		List<String> valuesFromDb = new ArrayList<String>();
		Class.forName("org.postgresql.Driver");
		Connection con = null;
		con = DriverManager.getConnection(jdbcpath, username, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String text = rs.getString("name");
			valuesFromDb.add(text);
		}
		con.close();
		System.out.println(valuesFromDb);
		return valuesFromDb;
	}
	
	public void compareData() throws ClassNotFoundException, SQLException, InterruptedException {
		List<String> values1= getValuesFromDB();
		List<String> values2= getValuesFromApp();
		for(String values66:values1) {
			for(String values77:values2) {
		if(values66.contains(values77)) {
			System.out.println("test");
		}
		}
		}
 	}
	public static void main(String args[]) throws IOException, InvalidFormatException, InterruptedException, SQLException, ClassNotFoundException {
		GenericMethods gen = new GenericMethods();
	/*	gen.getValuesFromDB();
		gen.getValuesFromApp();*/
		gen.compareData();
		
	}
	
}
