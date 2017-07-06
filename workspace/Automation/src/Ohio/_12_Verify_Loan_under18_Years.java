package Ohio;

import Utility.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _12_Verify_Loan_under18_Years {
	
	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	//String path = "C:/1/Seleniumproject/TestData_2007.xls";
	//String OR.getProperty("wpath") = "C:/1/Seleniumproject/TestResult_2007.xls";
	public static Properties OR=null;
	

	//@BeforeTest	
	@Test (priority = 1)
	public void Url_search (){		
		
		driver = new FirefoxDriver();			 
	driver.manage().window().maximize();		
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);			
}
	
	@Test (priority = 2)
	public void  StroreLogin () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("path"),"TestData");

	
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 2000;

			Thread.sleep(3500);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		Thread.sleep(3500);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		Thread.sleep(3500);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		Thread.sleep(3500);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		Thread.sleep(3500);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		Thread.sleep(3500);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		//String keys = "9998 Business date is always current date, oh";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);
		Thread.sleep(3500);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3000);			

	}
	
	
	
	// New Customer Creation  - working fine
	@Test (priority = 3)
	public void Customer_creation_Age_Verification() throws Exception {
		//xlData = Utils.readXL(path,"TestData");	
		//Utils.setExcelFile(xlPath,"Login");
		
		System.out.println("Customer_creation_Age_Verification    --  Started");
		
		xlCustFName = xlData [1][4];
		xlCustLName = xlData [1][5];
		xlssn = xlData [1][6];
		xldob = xlData [1][7];
		
		System.out.println(xlCustFName+ xlCustLName + xlssn + xldob );
		// Entering General Information			
		Thread.sleep(3500);
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		Function_Classes.field_click(driver, xpath);
		Thread.sleep(3500);
		xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
		Function_Classes.field_click(driver, xpath);
		Thread.sleep(3500);
		xpath=".//*[@id='FrmCustGeneral_txtFirstName']";  // First Name
		Function_Classes.field_sendKeys(driver, xpath, xlCustFName);
		Thread.sleep(3500);
		xpath=".//*[@id='FrmCustGeneral_txtLastName']";  // Last Name
		Function_Classes.field_sendKeys(driver, xpath, xlCustLName);
		Thread.sleep(3500);	   
		xpath=".//*[@id='FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
		Function_Classes.field_click(driver,xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xlssn);
		Thread.sleep(3500);		
		String xpath=".//*[@id='FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
		Function_Classes.field_click(driver,xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + "01/01/2015");
		
		// Entering Address
		
		Thread.sleep(3000);
		xpath = ".//*[@id='FrmCustAddress_cboType']";  // Select Address Type :  Home
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByIndex(2);
		Thread.sleep(3500);
		xpath =".//*[@id='FrmCustAddress_txtAddrLine1']"; // entering Address
		Function_Classes.field_sendKeys(driver, xpath, "124 Main St");
		Thread.sleep(3500);
		xpath =".//*[@id='FrmCustAddress_txtCity']"; // entering City
		Function_Classes.field_sendKeys(driver, xpath, "Columbus");
		Thread.sleep(3500);
		xpath = ".//*[@id='FrmCustAddress_cboState']";  // Select State :  Ohio
		Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect1.selectByIndex(43);
		Thread.sleep(3500);
		xpath =".//*[@id='FrmCustAddress_txtZip']"; // entering zip code
		Function_Classes.field_sendKeys(driver, xpath, "43201");
					
		// Entering Identification
		
		Thread.sleep(3000);
		xpath = ".//*[@id='FrmCustIdentification_cboType']";  // Select Address Type :  Driver License
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);
		Thread.sleep(3500);	
		xpath =".//*[@id='FrmCustIdentification_txtNumber']"; // entering License Number
		Function_Classes.field_sendKeys(driver, xpath, "UL012345678");
		Thread.sleep(3500);
		xpath =".//*[@id='FrmCustIdentification_txtIssuedOnDate']"; // entering Issue Date
		Function_Classes.field_sendKeys(driver, xpath, "12/12/2006");
		
		xpath =".//*[@id='FrmCustIdentification_txtZip']"; // entering zip code
		Function_Classes.field_sendKeys(driver, xpath, "43201");
		
			
		xpath = ".//*[@id='FrmCustIdentification_cboState']";  // Select State :  Ohio
		Select oSelect3 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect3.selectByIndex(43);
				
		xpath =".//*[@id='FrmCustIdentification_txtExpirationDate']"; // entering Expiration Date
		Function_Classes.field_sendKeys(driver, xpath, "12/12/2026");
		
		//Entering Bank Details
		
		Thread.sleep(3000);
		xpath = ".//*[@id='FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect4.selectByIndex(1);
		
		xlRoutingno = xlData [1][8];
		xpath =".//*[@id='FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
		Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
		
		
		xlaccno = xlData [1][9];
		xpath =".//*[@id='FrmCustBankAccount_txtAccountNum']"; // entering Account Number
		Function_Classes.field_sendKeys(driver, xpath, xlaccno);
		
		xlbankname = xlData [1][10];
		xpath =".//*[@id='FrmCustBankAccount_txtBankName']"; // entering Bank Name
		Function_Classes.field_sendKeys(driver, xpath, xlbankname);

		Thread.sleep(3000);
		xpath = ".//*[@id='btnSubmit']";  // Clicking on Save Button
		Function_Classes.field_click(driver,xpath);
		
		Thread.sleep(3500);
		xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
		String Address_Check = Function_Classes.field_gettext(driver, xpath);
		System.out.println(Address_Check);
		
		if(Address_Check.contains("The address you entered is similar to an address")){
			xpath = ".//*[@id='btnContinue']";
			Function_Classes.field_click(driver,xpath);					
		}
	
		
		Thread.sleep(3500);		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblFirstName']";	
		
		String xlrCustFName = Function_Classes.field_gettext(driver,xpath);
		System.out.println(xlrCustFName);
		//xlData[1][14] = Function_Classes.field_gettext(driver,xpath);
		System.out.println("yes");
		xlData[1][14] = xlrCustFName;
		//xlData[0][3] = "a";
		Thread.sleep(3000);
		System.out.println("yes1");
								
		 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
		
		// entering Phone number 
			Thread.sleep(2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_lnkListAddNew']";  // click on edit phone no
			Function_Classes.field_click(driver,xpath);
					
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Address Type :  Phone type  home no
			Select oSelect5 = new Select(driver.findElement(By.xpath(xpath)));
			oSelect5.selectByIndex(2);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
			Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phoe no 
			
			Thread.sleep(2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_btnSubmit']";  //  click on save button
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_pnlInterface']/div[1]/div[2]/ul/li";
			String Phone_Check = Function_Classes.field_gettext(driver, xpath);
			System.out.println(Phone_Check);
			
			Thread.sleep(3000);
			if(Phone_Check.contains("The phone number you entered is already in use by Thomas Barrett, at another store.")){
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_btnContinue']";
				Function_Classes.field_click(driver,xpath);	 
			}	
			 
		//Employer Details
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_lnkListAddNew']";  // click on New Employer link
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3500);
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
			Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
			IncomeType.selectByIndex(1);
			Thread.sleep(3500);
			
			
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']", "ABC Company"); // Employer Name
			
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']", "Software"); // Occupation  Name
		
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']", "4000"); // Pay Amount
			
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']", "9/01/2016"); // Pay Amount
			
			Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
			PayFreq.selectByIndex(12);
			
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_btnSubmit']"); //Click on Save button
			
			Thread.sleep(3500);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			/*xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // selecting loan Type  "Consumer Loan"
			Select Loan = new Select(driver.findElement(By.xpath(xpath)));
			Loan.selectByIndex(5);*/
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']","Consumer Loan" ); // Loan selection
			
	        Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
			Function_Classes.field_click(driver,xpath);
			
	        Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_upnlErr']/div/div[2]/ul/li";
			String Age = Function_Classes.field_gettext(driver, xpath);
			System.out.println(Age);
			
			if (Age.equals("Customer does not meet minimum age requirement of 18 years")) {
				xlData[1][14] = "Pass";
				
			    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			}
			else {
				xlData[1][14] = "Fail";
				
			    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			}
			
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustomerInfo_lnkName']";  // click on Customer link button 
			Function_Classes.field_click(driver,xpath);
			
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkEditCustomer']";  // click on edit customer link button 
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtBirthDate']";  // clear on Date field button 
			Function_Classes.field_clear(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtBirthDate']";  // Enter on Date field button 
			Function_Classes.field_sendKeys(driver, xpath, xldob);  // xldob
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_btnSubmit']";  // click on Save link button 
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3500);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // selecting loan Type  "Consumer Loan"
			Select Loan1 = new Select(driver.findElement(By.xpath(xpath)));
			Loan1.selectByIndex(3); 
			//Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']","Consumer Loan" ); // Loan selection
			
	        Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
			Function_Classes.field_click(driver,xpath);
			Thread.sleep(3000);
			xlcheckno = xlData [1][11];
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtCciCheckNumber']";  // entering the Check Number 
			Function_Classes.field_sendKeys(driver, xpath, xlcheckno);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  // entering the Loan Amount 
			Function_Classes.field_sendKeys(driver, xpath, "100");
			
			
	        Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // click on Next button 
			Function_Classes.field_click(driver,xpath);
			
			
			Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");  // Click on Next button		
			
           Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
			
			Thread.sleep(3500);
			
			
			//Function_Classes.isAlertPresent(driver);
			
			@SuppressWarnings("unused")
			boolean presentFlag = false;
			try {

				   // Check the presence of alert
				   Alert alert = driver.switchTo().alert();
				   // Alert present; set the flag
				   presentFlag = true;
				   // if present consume the alert
				   alert.accept();

				  } catch (NoAlertPresentException ex) {
				   // Alert not present
				   ex.printStackTrace();
				  }
			
			Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']");  // Click on Loan Id link
			
			
			
			Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span");  // Click on Void Entire Transaction button 
			
							
			Thread.sleep(3000);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "test");  // enter Reason
			
			Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // Click on Void Entire Loan button 				
			
			Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustomerInfo_lnkName']");  // Click on customer id 	
			
			
		
			Thread.sleep(3000);	
			 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			 
			 Thread.sleep(2000);
			 driver.close();
			 
			 System.out.println("Customer_creation_Age_Verification    --  Passed");
			 System.out.println("_12_Verify_Loan_under18_Years    --  Passed");
		}
	

		
	

}
