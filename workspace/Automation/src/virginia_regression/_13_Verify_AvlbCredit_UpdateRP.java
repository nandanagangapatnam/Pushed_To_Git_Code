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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_Classes;


public class _13_Verify_AvlbCredit_UpdateRP {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late,expctdpmt;
	String Final_LastLoanAmt,Final_FirstLoanAmt;
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

		xlData = Utils.readXL(OR.getProperty("vpath"),"VA_LOC_UpdatedRapidPay");
		
			xlUsername = xlData [4][0];
			xlPassword = xlData [4][1];
			xlStore = xlData [4][2];
			
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
		

		
		xlCustFName = xlData [4][4];
		//System.out.println(xlCustFName);// Printing Customer First Name
		
		xlCustLName = xlData [4][5];
		//System.out.println(xlCustLName);// Printing Customer Last Name
		
		xlssn = xlData [4][6];
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
		
		Thread.sleep(1000);
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

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Phone Type 
		Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect4.selectByIndex(2);
		
		Thread.sleep(2000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
		Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phone no 
	
		
	//Entering Bank Details
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);
		
		Thread.sleep(2000);		
		xlRoutingno = xlData [4][8];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
	 	Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);

		xlaccno = xlData [4][9];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);
		
		xlbankname = xlData [4][10];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbankname);

	// entering Identification 


		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
		Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
		Idnt.selectByIndex(1);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("123456789");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
		Select state = new Select(driver.findElement(By.xpath(xpath)));
		state.selectByIndex(11);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtExpirationDate']"; //Selecting Expiration date
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("12/12/2020");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
		Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
		IncomeType.selectByIndex(1);
		
		Thread.sleep(1000);
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
	public void _4_UpdateRapidPay_AvlbCdt_100to5000() throws Exception {
		
		xlResults = Utils.readXL(OR.getProperty("vLOCUpdatedRPAvlbCdt"),"VA_LOC");
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("5000");
		//	Function_Classes.field_sendKeys(driver, xpath,loanAmount);
			
			
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
	//	Function_Classes.field_clear(driver, xpath);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("100");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";
		String CdtLimit = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		System.out.println(CdtLimit);
		String S_CdtLimit = CdtLimit.substring(1);
		xlResults[1][2] = CdtLimit;
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwPrinBal']";
		String BalPrincipal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][3] = BalPrincipal;
		
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
		String EPrincipal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		String S_EPrincipal = EPrincipal.substring(1);
		String S1_EPrincipal =EPrincipal.substring(1, EPrincipal.indexOf("."));
		System.out.println(S1_EPrincipal);
			
	//	Thread.sleep(1500);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		Thread.sleep(1000);
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_flexPay_ddlLoanAmount']";
		if(driver.findElements(By.xpath(xpath)).size()== 0){
		
			System.out.println("Loan Amount Drop Down is not present");
			xlResults[1][8] = "FAIL-Loan Amount Drop Down is not present" ;
			Utils.writeXL(OR.getProperty("vLOCUpdatedRPAvlbCdt"), "VA_LOC" , xlResults);
			
			Assert.assertEquals(driver.findElements(By.xpath(xpath)).size(), 1);
			
		}	
		else{
		
			
			System.out.println("Loan Amount Drop Down present");
			
			Select NewRP = new Select(driver.findElement(By.xpath(xpath)));
			List <WebElement> elementCount = NewRP.getOptions();
			int iSize = elementCount.size();
//			System.out.println(iSize);
			
			String LastLoanAmt = elementCount.get(iSize-1).getText();
			String FirstLoanAmt = elementCount.get(0).getText();
			Final_FirstLoanAmt= FirstLoanAmt+".00";
			
			if(LastLoanAmt .equals("5000")){

				
				 Final_LastLoanAmt = "5,000.00";
				
				
			}
			
			else{
				
				 Final_LastLoanAmt = LastLoanAmt+".00";
			}
			

			
				if ( Final_LastLoanAmt .equals(S_CdtLimit) && Final_FirstLoanAmt .equals (S_EPrincipal)){
				
					System.out.println("PASS");
				
					int j = 0;
					for(int i =0,k=1  ; i < iSize - 1    ; i++,k++){
						
						xlResults[k][1] = loanlink;
						xlResults[1][4] = S_EPrincipal;
						xlResults[1][5] = S_CdtLimit;
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_flexPay_ddlLoanAmount']";
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
						Select NewRP1 = new Select(driver.findElement(By.xpath(xpath)));
						List <WebElement> elementCount1 = NewRP1.getOptions();
						String	sValue = elementCount1.get(i).getText();
	
						int int_EPrincipal = Integer.parseInt(S1_EPrincipal) + j;
						System.out.println(int_EPrincipal);
						String DropDown = Integer.toString(int_EPrincipal);
						
						if ( DropDown .equals(sValue) && i < 18){
							
							System.out.println("Dropdown value is being incremented by 50");
							
							NewRP1.selectByIndex(i);
							
							Thread.sleep(1000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_flexPay_btnAdd']"; // Update Rapid Pay
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlCurrency']/div[1]"; // Update Rapid Pay
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

							
							Thread.sleep(1000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
							String UpdatedAvlbCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							String S1_UpdatedAvlbCdt =UpdatedAvlbCdt.substring(1, CdtLimit.indexOf("."));
							float UAvlbCdt = Float.valueOf(S1_UpdatedAvlbCdt);

							System.out.println(S1_UpdatedAvlbCdt);
							
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
							String UpdatedEPrinc = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							String S1_UpdatedEPrinc =UpdatedEPrinc.substring(1, CdtLimit.indexOf("."));
							float UEPrinc = Float.valueOf(S1_UpdatedEPrinc);

//							System.out.println(S1_UpdatedEPrinc);
							
							float total = UAvlbCdt + UEPrinc;
							DecimalFormat formatter_total = new DecimalFormat("###.00");
							String formattedtotal = formatter_total.format(total);
//							System.out.println(formattedtotal);
					
							
							float newDropdown = Float.valueOf(sValue);
							DecimalFormat formatter_Dropdown = new DecimalFormat("###.00");
							String formattedDropdown = formatter_Dropdown.format(newDropdown);
//							System.out.println(formattedDropdown);
							
							if (formattedDropdown .equals(formattedtotal)){
								
									xlResults[k][6] = formattedDropdown;
									xlResults[k][7] = formattedtotal;
									xlResults[k][8] = "PASS";
										
									j= j+50;
									System.out.println("J++ " +j);
									
									xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
									xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span";
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
									
								}
						
							else{
								
									System.out.println("Values do not match");
									
									xlResults[k][6] = formattedDropdown;
									xlResults[k][7] = formattedtotal;
									xlResults[k][8] = "FAIL";
									
									xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
									xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span";
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
							}
							Utils.writeXL(OR.getProperty("vLOCUpdatedRPAvlbCdt"), "VA_LOC" , xlResults);
							Assert.assertEquals(formattedDropdown, formattedtotal);
							
						}	
						else if(DropDown .equals(sValue) && i >=18 ){

							
							System.out.println("Dropdown value is  being incremented by 250");
							
							NewRP1.selectByIndex(i);
							
							Thread.sleep(1000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_flexPay_btnAdd']"; // Update Rapid Pay
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlCurrency']/div[1]"; 
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

							
							Thread.sleep(1000);
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
							String UpdatedAvlbCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							String S1_UpdatedAvlbCdt =UpdatedAvlbCdt.substring(1, CdtLimit.indexOf("."));
							String S2_UpdatedAvlbCdt = S1_UpdatedAvlbCdt.replace(",", "");
							float UAvlbCdt = Float.valueOf(S2_UpdatedAvlbCdt);

					
							xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
							String UpdatedEPrinc = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							String S1_UpdatedEPrinc =UpdatedEPrinc.substring(1, CdtLimit.indexOf("."));
							float UEPrinc = Float.valueOf(S1_UpdatedEPrinc);

							
							float total = UAvlbCdt + UEPrinc;
							DecimalFormat formatter_total = new DecimalFormat("####.00");
							String formattedtotal = formatter_total.format(total);

					
							
							float newDropdown = Float.valueOf(sValue);
							DecimalFormat formatter_Dropdown = new DecimalFormat("####.00");
							String formattedDropdown = formatter_Dropdown.format(newDropdown);

							
							if (formattedDropdown .equals(formattedtotal)){
								
							
									j= j+250;
									System.out.println("J++ " +j);
									
									xlResults[k][6] = formattedDropdown;
									xlResults[k][7] = formattedtotal;
									xlResults[k][8] = "PASS";
									
									xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
									xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span";
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
									
								}
						
							else{
								
									System.out.println("Values do not match");
									
									xlResults[k][6] = formattedDropdown;
									xlResults[k][7] = formattedtotal;
									xlResults[k][8] = "FAIL";
									
									
									xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
									xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span";
									new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
							}
							Utils.writeXL(OR.getProperty("vLOCUpdatedRPAvlbCdt"), "VA_LOC" , xlResults);
							Assert.assertEquals(formattedDropdown, formattedtotal);
							
						}
						else if (!DropDown .equals(sValue)){
							
							
							System.out.println("Dropdown value is not being incremented by 250/50");
							
							xlResults[k][6] = DropDown;
							xlResults[k][7] = sValue;
							xlResults[k][8] = "FAIL";
							
						}
						Utils.writeXL(OR.getProperty("vLOCUpdatedRPAvlbCdt"), "VA_LOC" , xlResults);
						Assert.assertEquals(DropDown, sValue);	
					}
			
				}
				
				else{
					
					System.out.println("First and Last Amounts in Drop down are not coming correctly") ;
					xlResults[1][8] = "FAIL- First and Last Amounts in Drop down are not coming correctly";
				}
				Utils.writeXL(OR.getProperty("vLOCUpdatedRPAvlbCdt"), "VA_LOC" , xlResults);
				Assert.assertEquals(Final_LastLoanAmt, S_CdtLimit);
				Assert.assertEquals(Final_FirstLoanAmt ,S_EPrincipal);
			}	

		
	}
	
	@Test (priority = 5)
	public void _5_teller_logout() throws Exception {
	
		xpath =".//*[@id='menuv2']/li[7]/a";  //My Account Button
	    new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	       
	    Thread.sleep(1000);
	    Function_Classes.logout(driver, xpath); // for log out
		        
	    Thread.sleep(1000);
		driver.close();	
	   
		System.out.println("_logged out successfully  --  Passed"); 
		 
	}
	
}		
		
	