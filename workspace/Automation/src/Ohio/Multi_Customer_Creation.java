package Ohio;

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

import Functions.Function_Classes;

public class Multi_Customer_Creation {
	
	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	String path = "C:/Excel/TestData_2007.xls";
	String wpath = "C:/1/Seleniumproject/TestResult_2007.xls";
	int xRows, xCols;
	
	@BeforeTest	
	public void Url_search (){		
		
		driver = new FirefoxDriver();			 
	driver.manage().window().maximize();		
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);			
}
	
	
	@Test (priority = 1)
	public void  StroreLogin () throws Exception{
		
				
		xlData = Utils.readXL(path,"TestData");	
				
		//Utils.setExcelFile(xlPath,"Login");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 1000;

		
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
		//String keys = "9998 Business date is always current date, TN";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+2000);			

	}
	
	// New Customer Creation  - working fine
				@Test (priority = 2)
				public void newcustomer() throws Exception {
					xlData = Utils.readXL(path,"TestData");	
					
					for (int i = 1; i < 5; i = i + 1){
						System.out.println(xRows);
						
									
					xlCustFName = xlData [i][4];
					xlCustLName = xlData [i][5];
					xlssn = xlData [i][6];
					xldob = xlData [i][7];
					
						
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
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xldob);
					
					// Entering Address
					
					Thread.sleep(2000);
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
					
					Thread.sleep(2000);
					xpath = ".//*[@id='FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
					Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect2.selectByIndex(1);
					
					xlRoutingno = xlData [i][8];
					xpath =".//*[@id='FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
					Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
					
					
					xlaccno = xlData [i][9];
					xpath =".//*[@id='FrmCustBankAccount_txtAccountNum']"; // entering Account Number
					Function_Classes.field_sendKeys(driver, xpath, xlaccno);
					
					xlbankname = xlData [i][10];
					xpath =".//*[@id='FrmCustBankAccount_txtBankName']"; // entering Bank Name
					Function_Classes.field_sendKeys(driver, xpath, xlbankname);
		
					Thread.sleep(2000);
					xpath = ".//*[@id='btnSubmit']";  // Clicking on Save Button
					Function_Classes.field_click(driver,xpath);
					
					
					xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
					String Address_Check = Function_Classes.field_gettext(driver, xpath);
					System.out.println(Address_Check);
					
					if(Address_Check.contains("The address you entered is similar to an address")){
						xpath = ".//*[@id='btnContinue']";
						Function_Classes.field_click(driver,xpath);					
					}
				
					
					Thread.sleep(3000);		
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblFirstName']";	
					
					String xlrCustFName = Function_Classes.field_gettext(driver,xpath);
					System.out.println(xlrCustFName);
					//xlData[1][14] = Function_Classes.field_gettext(driver,xpath);
					System.out.println("yes");
					xlData[i][14] = xlrCustFName;
					//xlData[0][3] = "a";
					Thread.sleep(2000);
					System.out.println("yes1");
											
					 Utils.writeXL(wpath,"Login",xlData);
					
					
					
					
	//  loan creation 
					
					xlCustFName = xlData [i][4];
					
					xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
						Function_Classes.field_click(driver, xpath);
						
						xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
						Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
						
					
					Thread.sleep(3000);
					xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
					Function_Classes.field_click(driver,xpath);
					
					//Employer Details
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_lnkListAddNew']";  // click on New Employer link
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3000);
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
					Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
					IncomeType.selectByIndex(1);
					Thread.sleep(3000);
					
					
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']", "ABC Company"); // Employer Name
					
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']", "Software"); // Occupation  Name
				
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']", "4000"); // Pay Amount
					
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']", "10/01/2015"); // Pay Amount
					
					Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
					PayFreq.selectByIndex(12);
					
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_btnSubmit']"); //Click on Save button
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // selecting loan Type  "TN PRA Type A"
					Select Loan = new Select(driver.findElement(By.xpath(xpath)));
					Loan.selectByIndex(5);
					
			        Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(2000);
					xlcheckno = xlData [1][11];
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtCciCheckNumber']";  // entering the Check Number 
					Function_Classes.field_sendKeys(driver, xpath, xlcheckno);	
					
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  // entering the Loan Amount 
					String checkAmount = xlData [1][12];
					Function_Classes.field_sendKeys(driver, xpath, checkAmount);
					
					Thread.sleep(2000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");  // Click on Next button		
					
				    Thread.sleep(2000);	           
		           Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
					
		           Thread.sleep(2000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
					
					Thread.sleep(3000);
					
	/*				Alert simpleAlert = driver.switchTo().alert();
					String alertText = simpleAlert.getText();
					System.out.println("Alert text is " + alertText);
					//simpleAlert.accept();
					if (alertText.contentEquals("Alert text is Privacy Policy was printed and must be given to customer."))
					{
					simpleAlert.accept();
					}
					else 
					{
						simpleAlert.dismiss();
					}
					Thread.sleep(2000);*/
					
					
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
					
					}
					
				}

				
}
