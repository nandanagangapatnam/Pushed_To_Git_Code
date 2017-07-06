/**
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.Assert;
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


public class _1_Create_LOC_ThreshholdMaxAmount_Credit {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late;
	String Error_Msg;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
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
		
		xlResults = Utils.readXL(OR.getProperty("vLOCpath"),"VA_LOC");
		
		xlCustFName = xlData [1][4];
		//System.out.println(xlCustFName);// Printing Customer First Name
		
		xlCustLName = xlData [1][5];
		//System.out.println(xlCustLName);// Printing Customer Last Name
		
		xlssn = xlData [1][6];
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
		
		Thread.sleep(1500);
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

		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Phone Type 
		Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect4.selectByIndex(2);
		
		Thread.sleep(2000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
		Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phone no 
	
		
		//Entering Bank Details
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);	
		
	
		Thread.sleep(2000);		
		xlRoutingno = xlData [1][8];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
	 	Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
		
		xlaccno = xlData [1][9];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);
		
//		Thread.sleep(1000);
		xlbankname = xlData [1][10];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbankname);
	
	//  entering Identification 

		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
		Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
		Idnt.selectByIndex(1);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("123456789");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
		Select state = new Select(driver.findElement(By.xpath(xpath)));
		state.selectByIndex(11);
		
		Thread.sleep(1000);
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
		PayFreq.selectByIndex(3);
		
	//	Thread.sleep(2000);
		
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
	
	// New Loan Creation  - working fine
	@Test (priority = 4)
	public void  _4_LOC_creation_ThreshholdDraw() throws Exception {
	
		
		System.out.println("Ecash New Loan Creation   --  Started");
		
	//	Thread.sleep(2000); 
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	//	Function_Classes.field_click(driver,xpath);
		
		for (int i = 1; i < 26; i = i + 1){
			
			String loanAmount = xlData [i][11];
			String intialDraw = xlData [i][12];
			xlResults[i][1] = loanAmount;	
			xlResults[i][2] = intialDraw;
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_vwS2_SelLoanClass']";  // Clicking on loanmodel dropdowm
			String loanmodel=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			xlResults[i][10]=loanmodel;

			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(loanAmount);
		//	Function_Classes.field_sendKeys(driver, xpath,loanAmount);
			
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
		//	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			Function_Classes.field_clear(driver, xpath);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount
		//	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(intialDraw);
			Function_Classes.field_sendKeys(driver, xpath,intialDraw);
				
			
		//	Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		 	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click(); // Click on Next button 

			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_upnlErr']/div/div[2]";
		//	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Thread.sleep(1500);
			if(driver.findElements(By.xpath(xpath)).size() != 0){
				
				Error_Msg =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); // Click on Next button
		
					if(Error_Msg.contains("The Initial Draw Amount cannot be greater than the Loc Loan Amount")){
				
						xlResults[i][3] = Error_Msg;	
				
			
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
						String correctinitialDraw = xlData [i][13];
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						Thread.sleep(1000);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(correctinitialDraw);
						xlResults[i][4] = correctinitialDraw;	
			
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
											
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));				
						Late = Function_Classes.field_gettext(driver,xpath);
		//				System.out.println(Late);
						xlResults[i][6] = Late;
			
						Thread.sleep(1500);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
						DueDate = Function_Classes.field_gettext(driver,xpath);
		//				System.out.println(DueDate);
						xlResults[i][7] = DueDate;
			
		//				Thread.sleep(1500);
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  // FP Due date
						String FPDueDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//				System.out.println(FPDueDate);
						xlResults[i][8] = FPDueDate;
			
		//				Thread.sleep(1500);
						xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
						BusDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			//			System.out.println(BusDate);
						String BDate = BusDate.substring(15);
						xlResults[i][9] = BDate;
				
						xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
						loanlink =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						System.out.println(loanlink);
						xlResults[i][5] = loanlink;
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
						Function_Classes.field_sendKeys(driver, xpath, "Test" );  // Enter Reason
				
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkViewAll']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						
					}
			
					else {
						
						
						xlResults[i][3] = "Failed- Error Message did not pop-up";	
						
						xlResults[i][4] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
						xlResults[i][5] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
						xlResults[i][6] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
						xlResults[i][7] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
						xlResults[i][8] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
						xlResults[i][9] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
									
						xlResults[i][10] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustomerInfo_lnkName']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
				}
					Utils.writeXL(OR.getProperty("vLOCpath"),"VA_LOC",xlResults);
				//	Assert.assertEquals(Error_Msg,"Please correct the following errors before continuing: The Initial Draw Amount cannot be greater than the Loc Loan AmountThe Initial Draw Amount cannot be greater than the Loc Loan Amount");
			}
		else{
			
			xlResults[i][3] = "Failed";	
			
			xlResults[i][5] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
			
			xlResults[i][6] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
			
			xlResults[i][7] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
			
			xlResults[i][8] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
			
			xlResults[i][9] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
						
			xlResults[i][10] = "Loan Amount" +xlData [i][12]+ " must be less than/equal to " +xlData [i][11];
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustomerInfo_lnkName']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		}
			Thread.sleep(1000);
			Utils.writeXL(OR.getProperty("vLOCpath"),"VA_LOC",xlResults);
			
		}
		
		
		System.out.println("LOCs created successfully   --  Passed");		
	}	

			
	@Test (priority = 6)
	public void _5_log_out() throws Exception {
	
		
		//	Thread.sleep(1500);
			xpath=".//*[@id='menuv2']/li[7]/a";
		//	Function_Classes.field_click(driver,".//*[@id='menuv2']/li[7]/a");
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1500);
			Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			
			Thread.sleep(1000);
			driver.close();

		}
	
	}	
	
	
		

	
