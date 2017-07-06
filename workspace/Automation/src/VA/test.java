package VA;

import Utility.Utils;

import org.openqa.selenium.Alert;
//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class test {
	
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
	

	@BeforeTest	
	public void Url_search (){		
		
		driver = new FirefoxDriver();			 
	driver.manage().window().maximize();		
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);			
}
	
	@Test (priority = 1)
	public void  StroreLogin () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("vpath"),"TestData");

		//xlData = Utils.readXL(path,"TestData");	
				
		//Utils.setExcelFile(xlPath,"Login");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 2000;

		
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		//String keys = "2989 Business date is always current date, VA";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3000);			

	}
	
	
	
	// New Customer Creation  - working fine
	@Test (priority = 3)
	public void newcustomer() throws Exception {
		//xlData = Utils.readXL(path,"TestData");	
		//Utils.setExcelFile(xlPath,"Login");
		
		xlCustFName = xlData [1][4];
		xlCustLName = xlData [1][5];
		xlssn = xlData [1][6];
		xldob = xlData [1][7];
		
		System.out.println(xlCustFName+ xlCustLName + xlssn + xldob );
		// Entering General Information			
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		Function_Classes.field_click(driver, xpath);
		
		xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
		Function_Classes.field_click(driver, xpath);
		
		xpath=".//*[@id='FrmCustGeneral_txtFirstName']";  // First Name
		Function_Classes.field_sendKeys(driver, xpath, xlCustFName);
		
		xpath=".//*[@id='FrmCustGeneral_txtLastName']";  // Last Name
		Function_Classes.field_sendKeys(driver, xpath, xlCustLName);
			   
		xpath=".//*[@id='FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
		Function_Classes.field_click(driver,xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xlssn);
				
		String xpath=".//*[@id='FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
		Function_Classes.field_click(driver,xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + "01/01/2015");
		
		// Entering Address
		
		Thread.sleep(3000);
		xpath = ".//*[@id='FrmCustAddress_cboType']";  // Select Address Type :  Home
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByIndex(2);
		
		xpath =".//*[@id='FrmCustAddress_txtAddrLine1']"; // entering Address
		Function_Classes.field_sendKeys(driver, xpath, "124 Main St");
		
		xpath =".//*[@id='FrmCustAddress_txtCity']"; // entering City
		Function_Classes.field_sendKeys(driver, xpath, "Columbus");
		
		xpath = ".//*[@id='FrmCustAddress_cboState']";  // Select State :  Ohio
		Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect1.selectByIndex(43);
		
		xpath =".//*[@id='FrmCustAddress_txtZip']"; // entering zip code
		Function_Classes.field_sendKeys(driver, xpath, "43201");
					
		//Entering Bank Details
		
		Thread.sleep(3000);
		xpath = ".//*[@id='FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);
		
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
			
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']", "5/01/2016"); // Pay Amount
			
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
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // selecting loan Type  "LOC"
			Select Loan = new Select(driver.findElement(By.xpath(xpath)));
			Loan.selectByIndex(1);
			
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
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // selecting loan Type  "TN PRA Type A"
			Select Loan1 = new Select(driver.findElement(By.xpath(xpath)));
			Loan1.selectByIndex(1);
			
	        Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xlcheckno = xlData [1][11];
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtCciCheckNumber']";  // entering the Check Number 
			Function_Classes.field_sendKeys(driver, xpath, xlcheckno);	
			
			
			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  // entering the Loan Amount 
			String checkAmount = xlData [1][12];
			Function_Classes.field_sendKeys(driver, xpath, checkAmount);
			
			Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");  // Click on Next button		
			
		    Thread.sleep(3000);	           
           Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
			
           Thread.sleep(3000);
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
			
			Thread.sleep(3500);
			
			
			//Function_Classes.isAlertPresent(driver);
			
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
			 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			 
			 Thread.sleep(2000);
			 driver.close();
		}
	

		
	

}

