package RateSheets;
import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import Functions.Function_Classes;

public class Florida_Ratesheet {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,new_number;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlLoanAmt,xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
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
	
	//@BeforeTest	
	@Test (priority = 1)
	public void Url_search () throws InterruptedException{		
		Thread.sleep(1000);
		driver = new FirefoxDriver();			 
	driver.manage().window().maximize();		
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);			
}
	
	
	// To Open / reopen the Cash Drawer
	@Test (priority = 2)
	public  void  StroreLogin () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("fpath"),"TestData");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 2000;


		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );

		Thread.sleep(2000);
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
		
		Thread.sleep(vSleep+3000);			

	}
	
			////////////////
	@Test (priority = 3)
			public void Verify_Loan_Amt_CustSearch () throws Exception{
		

				xlCustFName = xlData [1][4];
				
				xl_FL_Data = Utils.readXL(OR.getProperty("ratepath"),"TestData");

				
				for (int i = 1; i < 11; i = i + 1){
					//System.out.println(xRows);
						xlLoan = xl_FL_Data [i][0];
						System.out.println(xlLoan);
						
						xlInterest = xl_FL_Data [i][1];
						System.out.println(xlInterest);
						
						xlVeritec = xl_FL_Data [i][2];
						System.out.println(xlVeritec);
						
						xlFee = xl_FL_Data [i][3];
						System.out.println(xlFee);
						
						xlTotal = xl_FL_Data [i][4];
						System.out.println(xlTotal);
													
				 
							
				System.out.println("Verify_Loan_CustSearch     -- Started");
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				Function_Classes.field_click(driver, xpath);
					
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value  
				Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(1500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				
				Thread.sleep(3000);
				String status = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblStatus']");// reaing customer status
				System.out.println("status  is "  + status);
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
				Function_Classes.field_click(driver,xpath);

				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
				Function_Classes.field_click(driver,xpath);
								
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_chkIsCheckOnDemand']";  // click on check box 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
				String checkAmount = xlLoan;
				Function_Classes.field_sendKeys(driver, xpath, checkAmount);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
				Function_Classes.field_click(driver,xpath);  // Click on Next button*/
				
			
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
				Function_Classes.field_click(driver,xpath);  // Click on Next button*/
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
					
			
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched_ctl03_lblPrinTotal']";  // get Principal amount  
				String Principal = Function_Classes.field_gettext(driver,xpath);
				String PAmt = Principal.substring(1,Principal.indexOf(".")+2);
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched_ctl03_lblFeeTotal']";  // get total Fee 
				String Fee = Function_Classes.field_gettext(driver,xpath);
				String FAmt = Fee.substring(1,Fee.indexOf("."));
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched_ctl03_lblPmtTotal']";  // get total payoff button 
				String Payoff = Function_Classes.field_gettext(driver,xpath);
				String PayAmt = Payoff.substring(1,Payoff.indexOf(".")+2);
				xl_FL_Results=Utils.readXL(OR.getProperty("ratepath_results"),"TestData");;
				

				
				if (PAmt.equals(xlLoan) && FAmt.equals(xlFee)&& PayAmt.equals(xlTotal))
					{

					
					status="Pass";
					System.out.println(status);
					System.out.println(PAmt);
					System.out.println(FAmt);
					System.out.println(PayAmt);
					System.out.println(xlLoan);
					System.out.println(xlFee);
					System.out.println(xlTotal);
					xl_FL_Results[i][5]= PayAmt;
					xl_FL_Results[i][6]= status;
					//Utils.writeXL(OR.getProperty("ratepath_results"),"TestData",xl_FL_Results);


						}
				else {
					System.out.println("Fail");
					System.out.println(PAmt);
					System.out.println(FAmt);
					System.out.println(PayAmt);
					System.out.println(xlLoan);
					System.out.println(xlFee);
					System.out.println(xlTotal);
					xl_FL_Results[i][5]= PayAmt;
					xl_FL_Results[i][6]= status;
					//Utils.writeXL(OR.getProperty("ratepath_results"),"TestData",xl_FL_Results);
					
				}
	
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // click on Finish button 
				Function_Classes.field_click(driver,xpath);

				Thread.sleep(3000);
				xpath= ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";
				loanlink = driver.findElement(By.id("ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId")).getText(); 
				Function_Classes.field_click(driver,xpath);
				System.out.println(loanlink);
				xl_FL_Results[i][7] = loanlink;		
				//Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" );  // Clicking on Loan ID
				
				Thread.sleep(3000);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
				
				Thread.sleep(3000);				
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "Test" );  // Enter Reason
				
				Thread.sleep(3000);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']" );  // Clicking on Void Entire Loan button
				
				
				Thread.sleep(2000);
				Utils.writeXL(OR.getProperty("ratepath_results"),"TestData",xl_FL_Results);
				
				}
				Thread.sleep(3000);	
				 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 

				 System.out.println("Verify_Loan_Amt_FL     -- Passed");
				 Thread.sleep(2000);
				 driver.close();

			
			}
		
	}

			
				
			
			