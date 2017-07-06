/**
 * 
 */
/**
 * @author Garima
 *
 */
package aml_regression;


import org.testng.annotations.Test;
import org.testng.annotations.Test;
import Utility.Utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_AML;
import Functions.Function_Classes;



public class _5_All_TestScenarios {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver, driver1;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1 = "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=127";
	String Username, Password, LoggedName;
	int len_Eprincipal,len_AvailCdt;
	String loanlink,Status,xlVoid,xlCheckDate;
	String xlCdtlimit , xlDraw,I_xlDraw,I_xlCdtlimit;
	String xpath,EarnedPrincoutput,AvailCdt,AvailCdt1,AvailCdtoutput1,AvailCdtoutput,EPrincipal;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustName, xlTransaction,xlVerification, xlAggregate,xlValue,xlCheck,Final_Aggregate;
	String[][] xlData;
	String[][] xlResults;
	public static Properties OR=null;


	//@BeforeTest	
	@Test (priority = 1)
	public void _1_navigate_to_eCashURL (){		
		System.out.println("Ecash URL  --  Started");
		
	
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver, URL);	
		      //add fail entry to the excel sheet

	}
	
	
	@Test (priority = 2)
	public void  _2_store_login () throws Exception{

		System.out.println("Ecash Teller Login   --  Started");
			
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);
		

		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath,"gsrivastavteller");
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath,"changeme123");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(1000);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		Function_Classes.field_sendKeys(driver, xpath, "9998 Some Place, TN");

		Thread.sleep(1000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
			
		System.out.println("Ecash Store Login   --  Passed");
		
		
	}
	

	
	@Test (priority = 3)
	public void  _3_admin_login () throws Exception{
		
		System.out.println("Ecash Admin Login   --  Started");
		
		xlData = Utils.readXL(OR.getProperty("amlpath"),"AML");	

		System.out.println(xlData[1][11]);
			
		System.setProperty("webdriver.chrome.driver", "C:\\Automation QA\\chromedriver.exe");
		
		driver1 = new ChromeDriver(); //	new chrome driver

		driver1.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver1, URL1);	


		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver1, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver1, xpath,"dateadm");
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver1, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver1, xpath,"changeme");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver1, xpath);
		
		Thread.sleep(1000);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		Thread.sleep(1000);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		
		Thread.sleep(1000);
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // selecting the state 
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			
		System.out.println("Ecash Store Login   --  Passed");
		
		
	}
	

	
	
	// New MO Creation  - working fine
	@Test (priority = 4)
	public void _4_AML() throws Exception {
	
		System.out.println("Ecash New Loan Creation   --  Started");
		
	
		for ( int i = 1 ; i<=194 ; i++){	
		
	
			xlCustName = xlData[i][1];
			xlTransaction = xlData[i][2];
			xlVerification =xlData[i][14];
			xlVoid = xlData[i][10];
			xlAggregate = xlData[i][8];
			xlValue = xlData[i][3];
			xlCheck = xlData[i][13];
			xlCheckDate = xlData[i][11];
			xlVoid = xlData[i][10];
			
			if ( xlVerification .equals("AML")){
			
				
				Thread.sleep(1000);
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustName);
					
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				
				 if (xlTransaction .equals("Money Order Non-Integrated no Cart")  ){
					 
					 if(xlCustName .equals("Justin Barber") || xlCustName .equals("Mikeysha Tibbs")){
						 
						 Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
						
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						 
					 }
					 
					 if (xlVoid .equals("Void Transaction")){
						 
							Thread.sleep(1000); 
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
							Function_AML.MoneyOrder_NoCart(driver,xpath,xlValue,xlCustName,xlVoid);
							
							Thread.sleep(1000); 	
							xpath =".//*[@id='ctl00_ctl00_AppHeader_liTools']/a";
							Function_AML.Void_transaction(driver,xpath,xlValue,xlCheckDate,xlTransaction);
							
							Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);
					
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
				 	} 
				
					 else{
				
										 
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
						 	Function_AML.MoneyOrder_NoCart(driver,xpath,xlValue,xlCustName,xlVoid);
						 }
				
			 } 
				 
				 else if (xlTransaction .equals("Money Order Non-Integrated with Cart") ){
					 
					 if(xlCustName .equals("Justin West")|| xlCustName .equals("Laura Bohl") || xlCustName .equals("Warren Mines") ){
						 
						 Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
						
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						 
					 }
					 
					 if (xlVoid .equals("Void Transaction")){
						 
							Thread.sleep(1000); 
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
							Function_AML.MoneyOrder_WithCart(driver,xpath,xlValue,xlAggregate,xlCustName,xlVoid);
								
							Thread.sleep(1000); 	
							xpath =".//*[@id='ctl00_ctl00_AppHeader_liTools']/a";
							Function_AML.Void_transaction(driver,xpath,xlValue,xlCheckDate,xlTransaction);
							
							Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);
					
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					 	} 
					
					 else{
					 
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
							Function_AML.MoneyOrder_WithCart(driver,xpath,xlValue,xlAggregate,xlCustName,xlVoid);
								 
						 }	 
				 } 
		
					
				 else  if (xlTransaction .equals("Bill pay no Cart") ){
					 
					 if(xlCustName .equals("Julio Alvarado") || xlCustName .equals("Sherrie Lowe") || xlCustName .equals("Mikeysha Tibbs") ) {
						 
						 Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
						
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						 
					 }
					 
					 if (xlVoid .equals("Void Transaction")){
					 
						Thread.sleep(1000); 
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
						Function_AML.BillPay_NoCart(driver,xpath,xlValue);
						
						Thread.sleep(1000); 	
						xpath =".//*[@id='ctl00_ctl00_AppHeader_liTools']/a";
						Function_AML.Void_transaction(driver,xpath,xlValue,xlCheckDate,xlTransaction);
						
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);
				
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				 	} 
				
					 else{
				
					 	
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
						Function_AML.BillPay_NoCart(driver,xpath,xlValue);
				 }
					 
			} 
				 else  if (xlTransaction .equals("Bill pay with Cart") ){
					 
					 if(xlCustName .equals("Aleta Fleming") || xlCustName .equals("Laura Bohl") || xlCustName .equals("Warren Mines") ) {
						 
						 Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
						
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						 
					 }
					 
					 
					 if (xlVoid .equals("Void Transaction")){
						 
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
						Function_AML.BillPay_WithCart(driver,xpath,xlValue,xlAggregate);
						
						Thread.sleep(1000); 	
						xpath =".//*[@id='ctl00_ctl00_AppHeader_liTools']/a";
						Function_AML.Void_transaction(driver,xpath,xlValue,xlCheckDate,xlTransaction);
						
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);
				
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				 	} 
				
					 else{
				
					 	
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
						Function_AML.BillPay_WithCart(driver,xpath,xlValue,xlAggregate);
					 } 
				 } 
				else if (xlTransaction .equals("Insight card load Non-Integrated No Cart")){
					
					if(xlCustName .equals("Mikeysha Tibbs")){
						 
			 			Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
					
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					
					 
				 }
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
					Function_AML.InsightCardLoad_NoCart(driver,xpath,xlValue);
				 }
				 
				else if (xlTransaction .equals("Insight card load Non-Integrated with Cart")){
					
						if(xlCustName .equals("Mark Proctor") || xlCustName .equals("Warren Mines")){
						 
				 			Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
						
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						 
					 }
					 
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
							Function_AML.InsightCardLoad_WithCart(driver,xpath,xlValue,xlAggregate,xlCustName);
				 } 
	
	
		
				else if (xlTransaction .equals("Check no Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					Function_AML.Check_NoCart(driver,xpath,xlValue,xlCheck,xlCheckDate,xlAggregate,xlCustName);
					
				}
				 
				else if (xlTransaction .equals("Check with Cart") ){
					
					if(xlCustName .equals("Mark Proctor")){
						 
			 			Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);
					
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					
					 
				 }
					
					if (xlVoid .equals("Void Transaction")){
						
		
						Thread.sleep(1000); 
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
						Function_AML.Check_WithCart(driver,xpath,xlValue,xlCheck,xlCheckDate,xlAggregate,xlCustName);
					
						
						Thread.sleep(1000); 	
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans']";
						Function_AML.Void_transaction_Check(driver,xpath,xlValue,xlCheckDate);
						
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);
					
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					}
					
					else{	
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
						Function_AML.Check_WithCart(driver,xpath,xlValue,xlCheck,xlCheckDate,xlAggregate,xlCustName);
						
					}	
				}
				
				else if (xlTransaction .equals("Wire transfer Receive No Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
					Function_AML.WireTransferReceive_NoCart(driver,xpath,xlValue);
					
				}
				 
				else if (xlTransaction .equals("Wire transfer Receive with Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
					Function_AML.WireTransferReceive_WithCart(driver,xpath,xlValue,xlAggregate);
					
				}
				else if (xlTransaction .equals("Wire transfer Send No Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
					Function_AML.WireTransferSend_NoCart(driver,xpath,xlValue);
					
				}
				 
				else if (xlTransaction .equals("Wire transfer Send with Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
					Function_AML.WireTransferSend_WithCart(driver,xpath,xlValue,xlAggregate);
					
				}
			
				 
				else if (xlTransaction .equals("Title Loan No Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the MS button
					Function_AML.TitleLoan_NoCart(driver,driver1,xpath,xlValue,xlCheckDate,xlVoid);
					
			
				}
				 
				else if (xlTransaction .equals("Title Loan With Cart") ){
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the MS button
					Function_AML.TitleLoan_WithCart(driver,driver1,xpath,xlValue,xlAggregate,xlCheckDate,xlVoid);

					
				}
				 
				else if (xlTransaction .equals("Loan Payment No Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the MS button
					Function_AML.LoanPayment_NoCart(driver,driver1,xpath,xlValue,xlCheckDate,xlVoid);
					
		
				}
				 
				else if (xlTransaction .equals("Loan Payment With Cart") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the MS button
					Function_AML.LoanPayment_WithCart(driver,driver1,xpath,xlValue,xlAggregate,xlCheckDate,xlVoid);
					
		
				}
				else if (xlTransaction .equals("ATM withdrawl") ){
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
					Function_AML.ATM_Withdrawals(driver,xpath,xlValue);
					
		
				}

				else if (xlTransaction .equals("First Business Transaction") ){
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);

					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_gvCustomerList_0_lnkUrl']";
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					for (int k = i + 1 ; k <= i+2 ; k++){
					
						String Transaction = xlData[k][2];
						String B_Value =xlData[k][3];
						String B_Aggregate =xlData[k][8];
						String B_Check = xlData[k][13];
						String B_Cust_Name = xlData[k][1];
						String B_Void = xlData[k][10];
						
						if (B_Cust_Name .equals("Emmet Brickowoski") || B_Cust_Name .equals("President Business") || B_Cust_Name .equals("Rick Grimes")){	
							
							if (Transaction .equals("Money Order Non-Integrated no Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_NoCart(driver,xpath,B_Value,B_Cust_Name,B_Void);
								 
								} 
	
							
							if (Transaction .equals("Money Order Non-Integrated with Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_WithCart(driver,xpath,B_Value,B_Aggregate,B_Cust_Name,B_Void);
								 
								} 
							
							if (Transaction .equals("Check no Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_NoCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}
							 
							if (Transaction .equals("Check with Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_WithCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}	
							Thread.sleep(1000);
							xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustName);
								
							xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_gvCustomerList_0_lnkUrl']";
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						}
 
					}
					
					 int j = i + 2 ;
					String Name = xlData[j][1];
					 if (Name .equals("Construction Business") || Name .equals("My least favorite business")|| Name .equals("My Favorite Business") || Name .equals("Another Business")){
						 
						 i = i + 1;
						 System.out.println(i);
					 }
					 else{
						 
						 i= i + 2;
						 System.out.println(i);
					 }

						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);

						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						Thread.sleep(1000);
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // selecting the state 
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				}
				 
				else if (xlTransaction .equals("Second Business Transaction") ){
					
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);

					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					
					Thread.sleep(1000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // selecting the state 
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					
					for (int k = i + 1 ; k <= i + 2 ; k++){
					
						String Transaction = xlData[k][2];
						String B_Value =xlData[k][3];
						String B_Aggregate =xlData[k][8];
						String B_Check = xlData[k][13];
						String B_Cust_Name = xlData[k][1];
						String B_Void = xlData[k][10];

						if (B_Cust_Name .equals("Mecha Godzilla") || B_Cust_Name .equals("Kia Armstrong") || B_Cust_Name .equals("Lord Business") || B_Cust_Name .equals("Emmet Brickowoski")){
							
							if (Transaction .equals("Money Order Non-Integrated no Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_NoCart(driver,xpath,B_Value,B_Cust_Name,B_Void);
								 
								} 
	
							
							if (Transaction .equals("Money Order Non-Integrated with Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_WithCart(driver,xpath,B_Value,B_Aggregate,B_Cust_Name,B_Void);
								 
								} 
							
							if (Transaction .equals("Check no Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_NoCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}
							 
							if (Transaction .equals("Check with Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_WithCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}	
							Thread.sleep(1000);
							xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustName);
								
							xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						}
					}	
						
					 int j = i + 2 ;
					String Name = xlData[j][1];
					 if (Name .equals("Construction Business") || Name .equals("My least favorite business")|| Name .equals("My Favorite Business") || Name .equals("Another Business")){
						 
						 i = i + 1;
						 System.out.println(i);
					 }
					 else{
						 
						 i= i + 2;
						 System.out.println(i);
					 }
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);

						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						Thread.sleep(1000);
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // selecting the state 
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				}
							
					 
						 
				else if (xlTransaction .equals("Third Business Transaction") ){
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);

					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					
				
					for (int k = i + 1 ; k <= i + 2 ; k++){
					
						String Transaction = xlData[k][2];
						String B_Value =xlData[k][3];
						String B_Aggregate =xlData[k][8];
						String B_Check = xlData[k][13];
						String B_Cust_Name = xlData[k][1];
						String B_Void = xlData[k][10];

						if (B_Cust_Name .equals("Ladawnya Adair") || B_Cust_Name .equals("Michael Bryant") || B_Cust_Name .equals("Mecha Godzilla") || B_Cust_Name .equals("Lord Business")){
							
							if (Transaction .equals("Money Order Non-Integrated no Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_NoCart(driver,xpath,B_Value,B_Cust_Name,B_Void);
								 
							} 
	
							
							if (Transaction .equals("Money Order Non-Integrated with Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_WithCart(driver,xpath,B_Value,B_Aggregate,B_Cust_Name,B_Void);
								 
							} 
							
							if (Transaction .equals("Check no Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_NoCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}
							 
							if (Transaction .equals("Check with Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_WithCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}	
							Thread.sleep(1000);
							xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustName);
								
							xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						}
						
						
					}
					 int j = i + 2 ;
					String Name = xlData[j][1];
					 if (Name .equals("Construction Business") || Name .equals("My least favorite business")|| Name .equals("My Favorite Business") || Name .equals("Another Business")){
						 
						 i = i + 1;
						 System.out.println(i);
					 }
					 else{
						 
						 i= i + 2;
						 System.out.println(i);
					 }

						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);

						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						Thread.sleep(1000);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

						
						Thread.sleep(1000);
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // selecting the state 
						new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				} 
							 
		
				else if (xlTransaction .equals("Fourth Business Transaction") ){
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCheckDate);

					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

					
			
					for (int k = i + 1 ; k <= i + 2 ; k++){
					
						String Transaction = xlData[k][2];
						String B_Value =xlData[k][3];
						String B_Aggregate =xlData[k][8];
						String B_Check = xlData[k][13];
						String B_Cust_Name = xlData[k][1];
						String B_Void = xlData[k][10];

						if (B_Cust_Name .equals("President Business") || B_Cust_Name .equals("Kia Armstrong")||  B_Cust_Name .equals("Emmet Brickowoski")){
							
							if (Transaction .equals("Money Order Non-Integrated no Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_NoCart(driver,xpath,B_Value,B_Cust_Name,B_Void);
								 
							} 
	
							
							if (Transaction .equals("Money Order Non-Integrated with Cart") ){
								 
								 xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the MS button
								 Function_AML.MoneyOrder_WithCart(driver,xpath,B_Value,B_Aggregate,B_Cust_Name,B_Void);
								 
							} 
							
							if (Transaction .equals("Check no Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_NoCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}
							 
							if (Transaction .equals("Check with Cart") ){
								
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
								Function_AML.Check_WithCart(driver,xpath,B_Value,B_Check,xlCheckDate,B_Aggregate,B_Cust_Name);
								
							}	
							Thread.sleep(1000);
							xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustName);
								
							xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
							new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						}
						
						
					}
					 int j = i + 2 ;
					String Name = xlData[j][1];
					 if (Name .equals("Construction Business") || Name .equals("My least favorite business")|| Name .equals("My Favorite Business") || Name .equals("Another Business")){
						 
						 i = i + 1;
						 System.out.println(i);
					 }
					 else{
						 
						 i= i + 2;
						 System.out.println(i);
					 }

					 
				
				
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[1][11]);

					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				}
			}
		}
		
	}	
}	
	