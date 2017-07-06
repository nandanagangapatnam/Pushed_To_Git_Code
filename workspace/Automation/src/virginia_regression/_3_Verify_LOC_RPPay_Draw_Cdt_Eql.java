/**
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression;

import Utility.Utils;

import org.junit.Assert;
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
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_Classes;


public class _3_Verify_LOC_RPPay_Draw_Cdt_Eql {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late;
	String Error_Msg, S_AmtDue,S_RapidPAmt,PAmt,num2,expPayment,AmtVal1;
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
		

	
	
	
	// New Loan Creation  - working fine
	@Test (priority = 3)
	public void _3_LOC_RPDraw_equals_Credit() throws Exception {
	
		xlResults = Utils.readXL(OR.getProperty("vLOCDrawCdtEQL"),"VA_LOC");
		System.out.println("Ecash New Loan Creation   --  Started");
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("VALOCRPPAY");

		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	
		for (int i = 1; i < 26; i = i + 1){
			
			String loanAmount = xlData [i][11];
			String intialDraw = xlData [i][13];
			String expPayment = xlData [i][15];
			xlResults[i][2] = loanAmount;	
			xlResults[i][3] = intialDraw;
	
		
			int num = Integer.parseInt(loanAmount);
			double num1 = (num * 0.05) + 10;
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			String num2 = decimalFormat.format(num1);
		//	String num2 = Double.toString(num1);
			
			System.out.println("num2 is : "+num2);
			
	
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//	Function_Classes.field_click(driver,xpath);
			
		//	Thread.sleep(2000);
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
				
		//	Thread.sleep(1500);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		//	Function_Classes.field_click(driver,xpath);  // Click on Next button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//	Function_Classes.field_click(driver,xpath);  // Click on Next button*/	
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
			loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	System.out.println(loanlink);
			xlResults[i][1] = loanlink;
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//	driver.findElement(By.xpath(xpath)).click();
			
				
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//	Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
			
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_PmtSched_dgPmtHistory']/tbody/tr[2]/td[3]";
			String AmtVal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			String AmtVal1 = AmtVal.substring(1);
			System.out.println("AmtVal1 :" +AmtVal1);

			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";
			String PAmt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");

			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblPmtDueToday']";
			String Amtdue = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			S_AmtDue = Amtdue.substring(1);
	//		System.out.println(S_AmtDue);
				
		//	Thread.sleep(2000);				
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_RapidPaySched_dgList']/tbody/tr[2]/td[5]" ;  // Clicking on View All loans
			if(driver.findElements(By.xpath(xpath)).size() != 0){
				
					String RapidPAmt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					S_RapidPAmt = RapidPAmt.substring(1);
			//		System.out.println(S_RapidPAmt);
				}
				else{
					S_RapidPAmt="0";
			//		System.out.println(S_RapidPAmt);
				}
				
				System.out.println(expPayment);		
			
				if(S_AmtDue.equals(expPayment) && PAmt.equals(expPayment) && S_RapidPAmt.equals(expPayment)){//&& num2.equals(AmtVal1)){
					
						String Status = "PASS";
						
						xlResults[i][6] = S_RapidPAmt;
						
			//			xlResults[i][9] = AmtVal1;
						
						xlResults[i][8] = expPayment;
						
						xlResults[i][10] = Status;
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']" ; 
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(S_RapidPAmt); //make payment
	
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']" ; //Next Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblPmtMethod']" ; //Postpayment
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
						String AvailCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	//					System.out.println(AvailCdt);
						xlResults[i][4] = AvailCdt;
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
						String EPrincipal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						String S_EPrincipal2 = EPrincipal.substring(1);
						xlResults[i][5] = S_EPrincipal2;
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwBalance']";
						String Bal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						String Bal1 = Bal.substring(1);
						xlResults[i][7] = Bal1;
						
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
						Utils.writeXL(OR.getProperty("vLOCDrawCdtEQL"),"VA_LOC",xlResults);
						
						Assert.assertEquals(S_AmtDue,expPayment);
						Assert.assertEquals(PAmt,expPayment);
						Assert.assertEquals(S_RapidPAmt,expPayment);
				//		Assert.assertEquals(num2,AmtVal1);
					
				}		
		
				else {
						String Status = "FAIL";
						
						xlResults[i][6] = S_RapidPAmt;
						
				//		xlResults[i][9] = AmtVal1;
						
						xlResults[i][8] = expPayment;
						
						xlResults[i][10] = Status;
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
						String AvailCdt = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	//					System.out.println(AvailCdt);
						xlResults[i][4] = AvailCdt;
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
				// 		EPrincipal2 =Function_Classes.field_gettext(driver,xpath);
						String EPrincipal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						String S_EPrincipal2 = EPrincipal.substring(1);
				//		System.out.println(S_EPrincipal2);
						xlResults[i][5] = S_EPrincipal2;
						
						xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwBalance']";
				// 		EPrincipal2 =Function_Classes.field_gettext(driver,xpath);
						String Bal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						String Bal1 = Bal.substring(1);
				//		System.out.println(Bal1);
						xlResults[i][7] = Bal1;
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" ; //loan link
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						Utils.writeXL(OR.getProperty("vLOCDrawCdtEQL"),"VA_LOC",xlResults);
						
						
						Assert.assertEquals(S_AmtDue,expPayment);
						Assert.assertEquals(PAmt,expPayment);
						Assert.assertEquals(S_RapidPAmt,expPayment);
						Assert.assertEquals(num2,AmtVal1);
						
					}

				
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");
	
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkViewAll']"; // clicking on the $ button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		
		}			
		Utils.writeXL(OR.getProperty("vLOCDrawCdtEQL"),"VA_LOC",xlResults);
		

	}


	@Test (priority = 4)
	public void _4_log_out() throws Exception {
	
		
	/*	//	Thread.sleep(1500);
			xpath=".//*[@id='menuv2']/li[7]/a";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1500);
			Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			
			Thread.sleep(1000);
			driver.close();*/

		}
	
	}	
	
	
		

	
