/**
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression ;

import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_Classes;


public class _2_Verify_LOC_RPAmt_Draw_100_to_5000 {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late,expctdpmt;
	String Error_Msg, S_AmtDue,S_RapidPAmt;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustFName, xlCustLName, xlssn, xlRoutingno, xlbankname, xlaccno;
	String[][] xlData;
	String[][] xlResults;
	public static Properties OR=null;
	

	//@BeforeTest	
	@Test (priority = 1)
	public void _1_navigate_to_eCashURL (){		
		System.out.println("Ecash URL  --  Started");
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);		
	
}
	
	@Test (priority = 2)
	public void  _2_teller_login () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("vpath"),"VA_LOC_credit");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			
			vSleep = 1000;

			System.out.println("Ecash Teller Login   --  Started");
			
			
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
			//String keys = "9998 Business date is always current date, TN";
			Function_Classes.field_sendKeys(driver, xpath, xlStore);

			Thread.sleep(1000);
			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			Function_Classes.field_click (driver, xpath);
		
		
		System.out.println("Ecash Store Login   --  Passed");
	}
	
	
	
	// New Customer Creation  - working fine
	@Test (priority = 3)
	public void _3_newcustomer_Creation() throws Exception {
	
		
		System.out.println("Ecash New Customer Creation   --  Started");
		

		
		xlCustFName = xlData [2][4];
		//System.out.println(xlCustFName);// Printing Customer First Name
		
		xlCustLName = xlData [2][5];
		//System.out.println(xlCustLName);// Printing Customer Last Name
		
		xlssn = xlData [2][6];
		//System.out.println(xlssn); // Printing Customer SSN
		
		//  Entering General Information			
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtFirstName']";  // First Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Function_Classes.field_sendKeys(driver, xpath, xlCustFName);

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtLastName']";  // Last Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustLName);
   
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlssn);

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/01/1970");

	// Entering Address
		
	//	Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboType']";  // Select Address Type :  Home
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByIndex(2);
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtAddrLine1']"; // entering Address
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("124 Main St");

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtCity']"; // entering City
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Florida");
	
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State 
		Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect1.selectByIndex(11);
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("37343");

		
		// entering Phone number 

	//	Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Phone Type 
		Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect4.selectByIndex(2);
		
		Thread.sleep(2000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
		Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phone no 
	
		
		//Entering Bank Details
		
	//	Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);
		
		Thread.sleep(2000);		
		xlRoutingno = xlData [2][8];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
	 	Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);

		xlaccno = xlData [2][9];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);
		
//		Thread.sleep(1000);
		xlbankname = xlData [2][10];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbankname);

	// entering Identification 

//		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
		Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
		Idnt.selectByIndex(1);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("123456789");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
		Select state = new Select(driver.findElement(By.xpath(xpath)));
		state.selectByIndex(11);
		
	//	Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtExpirationDate']"; //Selecting Expiration date
		Function_Classes.field_sendKeys(driver, xpath, "12/12/2020");
		
	//	Employer Details
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
		Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
		IncomeType.selectByIndex(1);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']";  // employer
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("ABC Company");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']";  // Occupation
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Software");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']";  // JobPayAmt
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("10000");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']";  // Pay Date
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("03/15/2017"); // Pay Amount
	
		Thread.sleep(1000);
		Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
		PayFreq.selectByVisibleText("15th & Last Day");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Clicking on Save Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
		String Address_Check=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		if(Address_Check.contains("The address you entered is similar to an address")){
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		}
			
			System.out.println("Customer created successfully");
			
	}
	
	// New Loan Creation  for intial draw of $100 
	@Test (priority = 4)
	public void _4_newLOC_creation_CdtLimitof5000() throws Exception {
	
		xlResults = Utils.readXL(OR.getProperty("vLOCPAmt"),"VA_LOC");
		System.out.println("Ecash $5000 LOCA for initial principal of $100 started  --  Started");
		
	//	Thread.sleep(2000); 
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xlResults[1][1] = "5000";	
		xlResults[1][3] = "100.00";
		expctdpmt= "14.00";
		xlResults[1][5] = expctdpmt;
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("5000");
	//	Function_Classes.field_sendKeys(driver, xpath,loanAmount);
			
			
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
		//	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		Function_Classes.field_clear(driver, xpath);
			
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount
		//	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(intialDraw);
		Function_Classes.field_sendKeys(driver, xpath,"100");
			
		//	Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		//	Function_Classes.field_click(driver,xpath);  // Click on Next button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//	Function_Classes.field_click(driver,xpath);  // Click on Next button*/	
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
		String AvailCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
//		System.out.println(AvailCdt);
		xlResults[1][4] = AvailCdt;
		
			
		//	Thread.sleep(1500);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	//	System.out.println(loanlink);
		xlResults[1][2] = loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//	driver.findElement(By.xpath(xpath)).click();
		

				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblPmtDueToday']";
		String AmtDue = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		S_AmtDue = AmtDue.substring(1);
		System.out.println(S_AmtDue);
			
		//	Thread.sleep(1000);
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";
		String PAmt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
		System.out.println(PAmt);
			
		//	Thread.sleep(2000);				
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_RapidPaySched_dgList']/tbody/tr[2]/td[5]" ;  // Clicking on View All loans
		if(driver.findElements(By.xpath(xpath)).size() != 0){
			
			String RapidPAmt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			S_RapidPAmt = RapidPAmt.substring(1);
			System.out.println(S_RapidPAmt);
		 	}
			else{
			S_RapidPAmt="0";
			System.out.println(S_RapidPAmt);
			}
			
		System.out.println(expctdpmt);			
			if( S_AmtDue.equals("14.00") && PAmt.equals("14.00") && S_RapidPAmt.equals("14.00")){
				
				String Status = "PASS";
				
				xlResults[1][7] = Status;
				
				xlResults[1][5] = expctdpmt;
				
				xlResults[1][6] = S_RapidPAmt;
				
	
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			}		
	
			else {
				
				xlResults[1][7] = "FAIL";
				
				xlResults[1][5] = expctdpmt;
				
				xlResults[1][6] = S_RapidPAmt;
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				}
	
			Thread.sleep(1000);
			Utils.writeXL(OR.getProperty("vLOCPAmt"),"VA_LOC",xlResults);

	
	}
				
	
	
	// $5000 LOC for principal in an increment of 50 
	@Test (priority = 5)
	public void _5_LOC_draw_100to5000 () throws Exception {
		
			
			System.out.println("Ecash $5000 LOC for  principal till $1000   --  Started");
			
		for (int j=2 ; j < 20 ; j=j+1){
				
			
			String S_RapidPAmt, RapidPAmt,AmtDue,S_AmtDue,PAmt;
			String S_EPrincipal,EPrincipal;
			expctdpmt = xlData[j][14];
			
			xlResults[j][2] =loanlink;
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDraw']/span";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtDraw_Amount']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			Thread.sleep(1000);
			Function_Classes.field_sendKeys(driver, xpath,"50" );  // Enter  draw
			
			
			Thread.sleep(1000);
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDraw_Submit']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		//	Thread.sleep(2000);
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblFundMethod_Value']";

			if(driver.findElements(By.xpath(xpath)).size()!= 0){
				
				System.out.println("IF Part");
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
				String AvailCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				xlResults[j][4] = AvailCdt;
			
				Thread.sleep(2000);
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
				EPrincipal =Function_Classes.field_gettext(driver,xpath);
				S_EPrincipal = EPrincipal.substring(1);
				System.out.println(S_EPrincipal);
				xlResults[j][3] = S_EPrincipal;
				

						
			
			} else{
				System.out.println("ELSE Part");
				
				Thread.sleep(1000);
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDraw_Submit']";
					if(driver.findElements(By.xpath(xpath)).size()!= 0){
						
						System.out.println("Button Present");
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblFundMethod_Value']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
						String AvailCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						System.out.println(AvailCdt);
						xlResults[j][4] = AvailCdt;
				
	
						Thread.sleep(2000);
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
						EPrincipal =Function_Classes.field_gettext(driver,xpath);
						S_EPrincipal = EPrincipal.substring(1);
						System.out.println(S_EPrincipal);
						xlResults[j][3] = S_EPrincipal;
					}
					
					else{
						
						System.out.println("Button Not Present");
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
						String AvailCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						System.out.println(AvailCdt);
						xlResults[j][4] = AvailCdt;
		

						Thread.sleep(2000);
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
						EPrincipal =Function_Classes.field_gettext(driver,xpath);
						S_EPrincipal = EPrincipal.substring(1);
						System.out.println(S_EPrincipal);
						xlResults[j][3] = S_EPrincipal;

					}

			}
			
		//	Thread.sleep(2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']"; // clicking on the loanlink
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();


			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblPmtDueToday']";
			AmtDue = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	S_AmtDue = AmtDue.substring(1,AmtDue.indexOf(".")+2);
			S_AmtDue = AmtDue.substring(1);
			System.out.println(S_AmtDue);
				
			//	Thread.sleep(1000);
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";
			PAmt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
		//	String S_PAmt = PAmt.substring(0,PAmt.indexOf(".")+2);
			String S_PAmt = PAmt.substring(0);
			System.out.println(S_PAmt);
				
			//	Thread.sleep(2000);				
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_RapidPaySched_dgList']/tbody/tr[2]/td[5]" ;  // Clicking on View All loans
			if(driver.findElements(By.xpath(xpath)).size() != 0){
				
				RapidPAmt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			//	S_RapidPAmt = RapidPAmt.substring(1,RapidPAmt.indexOf(".")+2);
				S_RapidPAmt = RapidPAmt.substring(1);
				System.out.println(S_RapidPAmt);
			 	}
				else{
				S_RapidPAmt="0";
				System.out.println(S_RapidPAmt);
				}
				
			System.out.println(expctdpmt);
		
				if( S_AmtDue.equals(expctdpmt) && S_PAmt.equals(expctdpmt) && S_RapidPAmt.equals(expctdpmt)){
					
					String Status="PASS";
					
					xlResults[j][7] = "PASS";
					
					xlResults[j][5] = expctdpmt;
					
					xlResults[j][6] = S_RapidPAmt;
					
					System.out.println(Status);
			
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				}		
		
				else {
					
					String Status = "FAIL";
					
					xlResults[j][7] = "FAIL";
					
					xlResults[j][5] = expctdpmt;
					
					xlResults[j][6] = S_RapidPAmt;
					
					System.out.println(Status);
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			}
				Thread.sleep(1000);
				Utils.writeXL(OR.getProperty("vLOCPAmt"),"VA_LOC",xlResults);
					
		}		
				for (int k=20 ; k < 24 ; k=k+1){
					
					
					String value1 ="500";
					String S_RapidPAmt1, RapidPAmt1,AmtDue1,S_AmtDue1,PAmt1;
					String EPrincipal1,S_EPrincipal1;
					
					expctdpmt = xlData[k][14];
					
					xlResults[k][2] =loanlink;
					
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDraw']/span";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					//	Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
					
					Thread.sleep(1000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtDraw_Amount']";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					Thread.sleep(1500);
					Function_Classes.field_sendKeys(driver, xpath,value1 );  // Enter  draw
					
					Thread.sleep(1000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDraw_Submit']";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(2000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblFundMethod_Value']";
					if(driver.findElements(By.xpath(xpath)).size()!= 0){
					
						System.out.println("IF Part");
					
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
						String AvailCdt1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				//		System.out.println(AvailCdt1);
						xlResults[k][4] = AvailCdt1;
						
						Thread.sleep(2000);
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
						EPrincipal1 =Function_Classes.field_gettext(driver,xpath);
						//	EPrincipal1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						S_EPrincipal1 = EPrincipal1.substring(1);
						System.out.println(S_EPrincipal1);
						xlResults[k][3] = S_EPrincipal1;

					
					} else{
						
							System.out.println("ELSE Part");
							
							Thread.sleep(1000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDraw_Submit']";
								if(driver.findElements(By.xpath(xpath)).size()!= 0){
									System.out.println("Button Present");
									//		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblFundMethod_Value']";
											new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
						
											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
											String AvailCdt1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								//			System.out.println(AvailCdt1);
											xlResults[k][4] = AvailCdt1;
						
											Thread.sleep(2000);
											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
											EPrincipal1 =Function_Classes.field_gettext(driver,xpath);
									//		EPrincipal1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
											S_EPrincipal1 = EPrincipal1.substring(1);
											System.out.println(S_EPrincipal1);
											xlResults[k][3] = S_EPrincipal1;

								}
								else{
									
											System.out.println("Button Not Present");
											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
											String AvailCdt1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								//			System.out.println(AvailCdt1);
											xlResults[k][4] = AvailCdt1;
						
	
											Thread.sleep(2000);
											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
											EPrincipal1 =Function_Classes.field_gettext(driver,xpath);
											S_EPrincipal1 = EPrincipal1.substring(1);
											System.out.println(S_EPrincipal1);
											xlResults[k][3] = S_EPrincipal1;

								}
					
						}	
			//		Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']"; // clicking on the loanlink
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//		Function_Classes.field_click(driver, xpath);

					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					//	Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
							
					//	Thread.sleep(2000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblPmtDueToday']";
					AmtDue1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
//					S_AmtDue1 = AmtDue1.substring(1,AmtDue1.indexOf(".")+2);
					S_AmtDue1 = AmtDue1.substring(1);
					System.out.println(S_AmtDue1);
						
					//	Thread.sleep(1000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";
					PAmt1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
//					String S_PAmt1= PAmt1.substring(0,PAmt1.indexOf(".")+2);
					String S_PAmt1= PAmt1.substring(0);
					System.out.println(S_PAmt1);
						
					//	Thread.sleep(2000);				
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_RapidPaySched_dgList']/tbody/tr[2]/td[5]" ;  // Clicking on View All loans
					if(driver.findElements(By.xpath(xpath)).size() != 0){
						
							RapidPAmt1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
//							S_RapidPAmt1 = RapidPAmt1.substring(1,RapidPAmt1.indexOf(".")+2);
							S_RapidPAmt1 = RapidPAmt1.substring(1);
							System.out.println(S_RapidPAmt1);
					 	}
						else{
							S_RapidPAmt1 ="0";
							System.out.println(S_RapidPAmt1);
						}
						
					System.out.println(expctdpmt);		
						
						if( S_AmtDue1.equals(expctdpmt) && S_PAmt1.equals(expctdpmt) && S_RapidPAmt1.equals(expctdpmt)){
							
							xlResults[k][7] = "PASS";
							
							xlResults[k][5] = expctdpmt;
							
							xlResults[k][6] = S_RapidPAmt1;
							
					
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						}		
				
						else {
							
							xlResults[k][7] = "FAIL";
							
							xlResults[k][5] = expctdpmt;
							
							xlResults[k][6] = S_RapidPAmt1;
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					}
						Thread.sleep(1000);
						Utils.writeXL(OR.getProperty("vLOCPAmt"),"VA_LOC",xlResults);
						
				}
						
						for (int l=24 ; l < 26 ; l=l+1){
							
							
							String S_RapidPAmt2, RapidPAmt2,AmtDue2,S_AmtDue2,PAmt2;
							String EPrincipal2,S_EPrincipal2;
							
							expctdpmt = xlData[l][14];
							xlResults[l][2] =loanlink;
							
							Thread.sleep(1000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDraw']/span";
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							//	Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
							
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtDraw_Amount']";
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							Thread.sleep(1500);
							Function_Classes.field_sendKeys(driver, xpath,"1000" );  // Enter  draw
							
							Thread.sleep(1000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDraw_Submit']";
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							//new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							
							Thread.sleep(2000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblFundMethod_Value']";
							
							if(driver.findElements(By.xpath(xpath)).size()!= 0){

								xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblFundMethod_Value']";
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
											
								xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
								String AvailCdt2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					//			System.out.println(AvailCdt2);
								xlResults[l][4] = AvailCdt2;	
									
								Thread.sleep(2000);
								xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
								EPrincipal2 =Function_Classes.field_gettext(driver,xpath);
							//  EPrincipal2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								S_EPrincipal2 = EPrincipal2.substring(1);
								System.out.println(S_EPrincipal2);
								xlResults[l][3] = S_EPrincipal2;
								

							
							} else
								
								{
								System.out.println("ELSE Part");
									
								Thread.sleep(1000);
								xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDraw_Submit']";
										if(driver.findElements(By.xpath(xpath)).size()!= 0){
											System.out.println("Button Present");
										
											new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
													xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
													String AvailCdt2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
													xlResults[l][4] = AvailCdt2;
									
													
													Thread.sleep(2000);
													xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
													EPrincipal2 =Function_Classes.field_gettext(driver,xpath);
													S_EPrincipal2 = EPrincipal2.substring(1);
													System.out.println(S_EPrincipal2);
													xlResults[l][3] = S_EPrincipal2;
							

										}
										else{
											
											System.out.println("Button Not Present");
											
											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
											String AvailCdt2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
											xlResults[l][4] = AvailCdt2;
						
	
											Thread.sleep(2000);
											xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
											EPrincipal2 =Function_Classes.field_gettext(driver,xpath);
											S_EPrincipal2 = EPrincipal2.substring(1);
											System.out.println(S_EPrincipal2);
											xlResults[l][3] = S_EPrincipal2;

										}
								}	

							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']"; // clicking on the loanlink
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblPmtDueToday']";
							AmtDue2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							S_AmtDue2 = AmtDue2.substring(1);
							System.out.println(S_AmtDue2);
							
								
							//	Thread.sleep(1000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";
							PAmt2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
							String S_PAmt2 = PAmt2.substring(0);
							System.out.println(S_PAmt2);
								
							//	Thread.sleep(2000);				
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_RapidPaySched_dgList']/tbody/tr[2]/td[5]" ;  // Clicking on View All loans
							if(driver.findElements(By.xpath(xpath)).size() != 0){
								
									RapidPAmt2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									S_RapidPAmt2 = RapidPAmt2.substring(1);
									System.out.println(S_RapidPAmt2);
							 	}
								else{
									S_RapidPAmt2 ="0";
									System.out.println(S_RapidPAmt2);
								}
								
											
							System.out.println(expctdpmt);
								
							if( S_AmtDue2.equals(expctdpmt) && S_PAmt2.equals(expctdpmt) && S_RapidPAmt2.equals(expctdpmt)){
									
									xlResults[l][7] = "PASS";
									
									xlResults[l][5] = expctdpmt;
									
									xlResults[l][6] = S_RapidPAmt2;
									
		
									xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
										

								}		
						
								else {
									
									xlResults[l][7] = "FAIL";
									
									xlResults[l][5] = expctdpmt;
									
									xlResults[l][6] = S_RapidPAmt2;
									
									xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
								
							}
								Thread.sleep(1000);
								Utils.writeXL(OR.getProperty("vLOCPAmt"),"VA_LOC",xlResults);

				
		}	
						
		
		 System.out.println("Loans created successfully   --  Passed");
			 
	}
		
@Test (priority = 6)
public void _6_log_out() throws Exception {

	
		xpath=".//*[@id='menuv2']/li[7]/a";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		Thread.sleep(1500);
		Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
		
		Thread.sleep(1000);
		driver.close();

	}
}
	
	
	
	
		

	
