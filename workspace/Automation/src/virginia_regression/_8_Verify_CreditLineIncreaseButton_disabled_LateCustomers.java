/**
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression;

import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;


import Functions.Function_Classes;


public class _8_Verify_CreditLineIncreaseButton_disabled_LateCustomers {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	WebDriver driver1;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1= "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=167";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,ContractualDueDate,OriDate,BusDate, BDate,InitialCdtLimit;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	String[][] xlResults1;
	public static Properties OR=null;
	
	//@BeforeTest

	
@Test (priority = 1)
	public void  _1_admin_login () throws Exception{
	
	System.setProperty("webdriver.chrome.driver", "C:\\Automation QA\\chromedriver.exe");
	
	driver1 = new ChromeDriver(); //	new chrome driver

	driver1.manage().window().maximize();	
	Function_Classes.navigate_to(driver1, URL1);
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtUsername']";  // Clearing User Name field
	Function_Classes.field_clear(driver1, xpath );
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtUsername']";  // Passing User name 
	Function_Classes.field_sendKeys(driver1, xpath, "dateadm");
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtPasswd']";  // Clearing Password field
	Function_Classes.field_clear(driver1, xpath );
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtPasswd']";  // Passing Password
	Function_Classes.field_sendKeys(driver1, xpath, "changeme");
	
	Thread.sleep(1000);
	xpath = ".//*[@id='btnSubmit']"; // Click on go button
	Function_Classes.field_click(driver1, xpath);
	

    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/01/2020");
			
    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
    
    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
    String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    System.out.println(newBusDate);
    
	System.out.println("_Business_Date is changed successfully  --  Passed"); 

	
}

@Test (priority = 2)
public void _2_navigate_to_eCashURL (){		
	System.out.println("Ecash URL  --  Started");
	driver = new FirefoxDriver(); //	firefox driver
	driver.manage().window().maximize();	
	 
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);		

}


@Test (priority = 3)
public void  _3_teller_login () throws Exception{
	OR = new Properties();
	FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
	OR.load(fn);

	xlData = Utils.readXL(OR.getProperty("vpath"),"VA_LOC_credit");
	
	xlUsername = xlData [1][0];
	xlPassword = xlData [1][1];
	xlStore = xlData [1][2]; 
		
	System.out.println("Ecash Store Login   --  Started");
	Thread.sleep(1000);
	xpath=".//*[@id='txtUsername']";  // Clearing User Name field
	Function_Classes.field_clear(driver, xpath );
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtUsername']";  // Passing User name 
	Function_Classes.field_sendKeys(driver, xpath, xlUsername);
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtPasswd']";  // Clearing Password field
	Function_Classes.field_clear(driver, xpath );
	
	Thread.sleep(1000);
	xpath=".//*[@id='txtPasswd']";  // Passing Password
	Function_Classes.field_sendKeys(driver, xpath, xlPassword);
	
	Thread.sleep(1000);
	xpath = ".//*[@id='btnSubmit']"; // Click on go button
	Function_Classes.field_click(driver, xpath);
	
	Thread.sleep(1000);
	xpath=".//*[@id='ddlStore']";  // selecting the state 
	Function_Classes.field_sendKeys(driver, xpath, xlStore);

	Thread.sleep(1000);
	xpath = ".//*[@id='btnSelStore']"; // clicking ok 
	Function_Classes.field_click (driver, xpath);
	
	
	System.out.println("eCash Store Login   --  Passed");
}
	// New Customer Creation  - working fine
	@Test (priority = 4)
	public void _4_new_LOC_Creation() throws Exception {
	
		
		System.out.println("Ecash New Customer Creation   --  Started");
		xlResults1 = Utils.readXL(OR.getProperty("vLateCustIncCdtLmtpath"),"VA_LOC");	

		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("VALOCLATESTATUS TESTLOCINCREASE");

		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		System.out.println("Ecash New LOC Creation   --  Started");
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the advance button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1000");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
		Function_Classes.field_clear(driver, xpath);

		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount
		Function_Classes.field_sendKeys(driver, xpath,"100");
			
	//	Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));


}
	
	
@Test (priority = 5)
public void  _5_business_date_rolledforwrdto26days () throws Exception{
	

	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/27/2020");
				
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	    String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    System.out.println(newBusDate);
	    
		System.out.println("_Business_Date is changed successfully  --  Passed"); 

	
}


@Test (priority = 6)
public void  _6_IncreaseCreditLimit_26days () throws Exception{
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults1[1][1]=loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
		Status =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		System.out.println(Status);
			
		if(Status .equals("1 Day Late")) {
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocIncreaseLine']/span";  // Finish Button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";  // New Amount DropDown
			  
				if ( driver.findElements(By.xpath(xpath)).size() !=0){
				
					xlResults1[1][7] = "Increase Credit Line button is ENABLED - FAILED";
					
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";  // ori date
					InitialCdtLimit =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(InitialCdtLimit);
					xlResults1[1][2] = InitialCdtLimit;
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
					OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(OriDate);
					xlResults1[1][3] = OriDate;
					
							
					xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
					BusDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(BusDate);
					BDate = BusDate.substring(15);
					xlResults1[1][4] = BDate;
	
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
					ContractualDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(ContractualDueDate);
					xlResults1[1][5] = ContractualDueDate;
	
					xlResults1[1][6] = Status;
					
					Utils.writeXL(OR.getProperty("vLateCustIncCdtLmtpath"),"VA_LOC",xlResults1);	

					
			//		xlResults1[1][3] = "-";
					
				
				}
				else {
					

					xlResults1[1][7] = "Increase Credit Line button is DISABLED - PASSED";
					
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";  // ori date
					InitialCdtLimit =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(InitialCdtLimit);
					xlResults1[1][2] = InitialCdtLimit;
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
					OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(OriDate);
					xlResults1[1][3] = OriDate;
					
							
					xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
					BusDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(BusDate);
					BDate = BusDate.substring(15);
					xlResults1[1][4] = BDate;
	
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
					ContractualDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(ContractualDueDate);
					xlResults1[1][5] = ContractualDueDate;
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
					Status =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Status);
					xlResults1[1][6] = Status;
					
						

		//		xlResults1[1][3] = "-";
					
			}
				Utils.writeXL(OR.getProperty("vLateCustIncCdtLmtpath"),"VA_LOC",xlResults1);
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";  // New Amount DropDown
				Assert.assertEquals(0, driver.findElements(By.xpath(xpath)).size());	
		}
		else {
			
			xlResults1[1][7] = "Customer is not 1 day late-FAILED";
			Utils.writeXL(OR.getProperty("vLateCustIncCdtLmtpath"),"VA_LOC",xlResults1);	
		}
	

}

@Test (priority = 7)
public void  _7_business_date_rolledforwrdto25days () throws Exception{
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/26/2020");
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		System.out.println(newBusDate);
		
		System.out.println("_Business_Date is changed successfully  --  Passed"); 
}

@Test (priority = 8)
public void  _8_increaseCreditLimit_25days () throws Exception{
	

	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
	loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	xlResults1[2][1]=loanlink;
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
	Status =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	System.out.println(Status);
		if(Status .equals("Not Late")) {
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocIncreaseLine']/span";  // Finish Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			Thread.sleep(1500);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";  // New Amount DropDown
	//		WebElement IncLimitButton1 = driver.findElement(By.xpath(xpath));

			
			if ( driver.findElements(By.xpath(xpath)).size() !=0){
										
				
					xlResults1[2][7] = "Increase Credit Line Button is ENABLED - PASSED";

					xlResults1[2][2] = InitialCdtLimit;
					
			//		xlResults1[2][3] = NewCdtlimitPostIncrease;
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
					OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(OriDate);
					xlResults1[2][3] = OriDate;
					
							
					xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
					BusDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(BusDate);
					BDate = BusDate.substring(15);
					xlResults1[2][4] = BDate;

						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
					ContractualDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(ContractualDueDate);
					xlResults1[2][5] = ContractualDueDate;
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
					Status =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Status);
					xlResults1[2][6] = Status;
					

					
					
				}
				else {
				
					xlResults1[2][7] = "Increase Credit Line Button is DISABLED - FAILED";
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";  // ori date
					InitialCdtLimit =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(InitialCdtLimit);
					xlResults1[2][2] = InitialCdtLimit;
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
					OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(OriDate);
					xlResults1[2][3] = OriDate;
					
							
					xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
					BusDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(BusDate);
					BDate = BusDate.substring(15);
					xlResults1[2][4] = BDate;

						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
					ContractualDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(ContractualDueDate);
					xlResults1[2][5] = ContractualDueDate;
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
					Status =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Status);
					xlResults1[2][6] = Status;
					
			//		xlResults1[2][3] = NewCdtlimitPostIncrease;
		
				}
				
				Utils.writeXL(OR.getProperty("vLateCustIncCdtLmtpath"),"VA_LOC",xlResults1);
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";  // New Amount DropDown
				Assert.assertEquals(1, driver.findElements(By.xpath(xpath)).size());
//				Assert.assertEquals(true, IncLimitButton1.isDisplayed());
		   }
		
		else {
			
				xlResults1[2][7] = "Customer is LATE - FAILED";
				Utils.writeXL(OR.getProperty("vLateCustIncCdtLmtpath"),"VA_LOC",xlResults1);
			}
			

			Assert.assertEquals(Status,"Not Late");

	}

@Test (priority = 9)
public void _9_void_loan_() throws Exception {
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/01/2020");
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		System.out.println(newBusDate);

	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
	
			
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
}	

@Test (priority = 10)
public void  _10_business_date_backtoorginal () throws Exception{


		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/29/2017");
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		System.out.println(newBusDate);
		
		System.out.println("_Business_Date is changed successfully  --  Passed"); 

	}





@Test (priority = 11)
public void _11_log_out_admin() throws Exception {

	
		xpath=".//*[@id='menuv2']/li[9]/a";
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		Thread.sleep(1500);
		Function_Classes.logout(driver1, ".//*[@id='menuv2']/li[7]/a"); // for log out 
		
		Thread.sleep(1000);
		driver1.close();

}



@Test (priority = 12)
public void _12_log_out_teller() throws Exception {
	
		xpath=".//*[@id='menuv2']/li[7]/a";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		Thread.sleep(2000);
		Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
		
		Thread.sleep(1000);
		driver.close();

	}


}