package florida_regression;


import Utility.Utils;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import Functions.Function_Classes;

public class Florida_PRA_Ratesheet {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,new_number;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlLoanAmt,xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, Status;
	String xlLoan;
	String xlInterest,xlVeritec,loanlink;
	String xlFee;
	String xlTotal;
	String path="C:/Excel/Florida_Ratesheet_Results.xls";
	int xRows, xCols;
	String[][] xlData;
	String[][] xl_FL_Data;
	String[][] xl_FL_Results;
	String[][] xlStatus;
	

	public static Properties OR=null;
	
//	@BeforeTest	
	@Test (priority = 1)
	public void _1_navigate_to_eCashURL () throws InterruptedException{		
		Thread.sleep(1000);
		driver = new FirefoxDriver();			 
		driver.manage().window().maximize();		
	// Ecash site access
		Function_Classes.navigate_to(driver, URL);			
}
	
	
	// To Open / reopen the Cash Drawer
	@Test (priority = 2)
	public  void  _2_tellerlogin () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("fpath"),"TestData");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
		
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
		
	//	Thread.sleep(vSleep+3000);			

	}
	
			////////////////
	@Test (priority = 3)
			public void _3_Verify_PRA_Amt() throws Exception{
		

				xlCustFName = xlData [1][4];
				
				xl_FL_Data = Utils.readXL(OR.getProperty("ratepath"),"TestData");

				System.out.println("Verify_Loan_CustSearch     -- Started");
				for (int i = 1; i < 11; i = i + 1){
					//System.out.println(xRows);
						xlLoan = xl_FL_Data [i][0];
					//	System.out.println(xlLoan);
						
						xlInterest = xl_FL_Data [i][1];
					//	System.out.println(xlInterest);
						
						xlVeritec = xl_FL_Data [i][2];
					//	System.out.println(xlVeritec);
						
						xlFee = xl_FL_Data [i][3];
					//	System.out.println(xlFee);
						
						xlTotal = xl_FL_Data [i][4];
					//	System.out.println(xlTotal);
													
				 
							
			
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//	Function_Classes.field_click(driver, xpath);
					
			//	Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustFName);
			//	Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				//Thread.sleep(1500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				//Function_Classes.field_click(driver,xpath);
				
			
			//	Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//	Function_Classes.field_click(driver,xpath);

			//	Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//	Function_Classes.field_click(driver,xpath);
								
			//	Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_chkIsCheckOnDemand']";  // click on check box
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//	Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
				String checkAmount = xlLoan;
				Function_Classes.field_sendKeys(driver, xpath, checkAmount);
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
				Function_Classes.field_click(driver,xpath);  // Click on Next button*/
					
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
				Function_Classes.field_click(driver,xpath);  // Click on Next button*/
				
			//	Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // click on Next button 
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//	Function_Classes.field_click(driver,xpath);
					
			
			//	Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched_ctl03_lblPrinTotal']";  // get Principal amount button 
				String Principal = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			//	String Principal = Function_Classes.field_gettext(driver,xpath);
				String PAmt = Principal.substring(1,Principal.indexOf(".")+2);
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched_ctl03_lblFeeTotal']";  // get total Fee 
				String Fee = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				//String Fee = Function_Classes.field_gettext(driver,xpath);
				String FAmt = Fee.substring(1,Fee.indexOf("."));
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched_ctl03_lblPmtTotal']";  // get total payoff button 
				String Payoff = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				//	String Payoff = Function_Classes.field_gettext(driver,xpath);
				String PayAmt = Payoff.substring(1,Payoff.indexOf(".")+2);
				xl_FL_Results=Utils.readXL(OR.getProperty("ratepath_results"),"TestData");;
				

				
				if (PAmt.equals(xlLoan) && FAmt.equals(xlFee)&& PayAmt.equals(xlTotal))
					{

					
					Status="Pass";
			/*		System.out.println(Status);
					System.out.println(PAmt);
					System.out.println(FAmt);
					System.out.println(PayAmt);
					System.out.println(xlLoan);
					System.out.println(xlFee);
					System.out.println(xlTotal);*/
					xl_FL_Results[i][5]= PayAmt;
					xl_FL_Results[i][6]= Status;
					//Utils.writeXL(OR.getProperty("ratepath_results"),"TestData",xl_FL_Results);


						}
				else {
					Status="Fail";
		/*			System.out.println(Status);
					System.out.println(PAmt);
					System.out.println(FAmt);
					System.out.println(PayAmt);
					System.out.println(xlLoan);
					System.out.println(xlFee);
					System.out.println(xlTotal);*/
					xl_FL_Results[i][5]= PayAmt;
					xl_FL_Results[i][6]= Status;
					//Utils.writeXL(OR.getProperty("ratepath_results"),"TestData",xl_FL_Results);
					
				}
	
				//Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // click on Finish button 
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			//	Function_Classes.field_click(driver,xpath);
				
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  // status
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));				
				String Late = Function_Classes.field_gettext(driver,xpath);
		//		System.out.println(Late);
				xl_FL_Results[i][8] = Late;
			
			//	Thread.sleep(1500);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));				
				String DueDate = Function_Classes.field_gettext(driver,xpath);
		//		System.out.println(DueDate);
				xl_FL_Results[i][9] = DueDate;
			
			//	Thread.sleep(1500);
				xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));				
				String BusDate = Function_Classes.field_gettext(driver,xpath);
		//		System.out.println(BusDate);
		 		String BDate = BusDate.substring(15);
				xl_FL_Results[i][10] = BDate;
				


				//Thread.sleep(3000);
				xpath= ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";
				loanlink = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				xl_FL_Results[i][7] = loanlink;		

				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");

				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(2000);
				Utils.writeXL(OR.getProperty("ratepath_results"),"TestData",xl_FL_Results);
				
				}
	}
	
	@Test (priority = 4)
	public void _4_teller_log_out() throws Exception {
				
						xpath=".//*[@id='menuv2']/li[7]/a";
						new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						Thread.sleep(1500);
						Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
						
						Thread.sleep(1000);
						driver.close();

					}
		
	}

			
				
			
			