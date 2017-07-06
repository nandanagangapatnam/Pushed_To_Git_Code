package VA;
import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;
import org.openqa.selenium.Keys;

public class _4_a_Verify_Loan_Amt_VA {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,new_number;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;

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

		xlData = Utils.readXL(OR.getProperty("vpath"),"TestData");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 2000;

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
		//String keys = "2989 Business date is always current date, VA";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(1000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3000);			

	}
	
			
			@Test (priority = 5)
			public void Verify_Loan_Amt_VA () throws Exception{
				//xlData = Utils.readXL(path,"TestData");	
				xlCustFName = xlData [1][4];
							
				System.out.println("Verify_Loan_Amt_VA     -- Started");
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
					Function_Classes.field_click(driver, xpath);
					
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
					Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				
				Thread.sleep(3500);
				String status = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblStatus']");// reading customer status
				System.out.println("status  is "  + status);
				
				Thread.sleep(3500);
				if (status.equals("Bad") ){
					
					Thread.sleep(2000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkEditCustomer']");// click on edit customer link
				
					Thread.sleep(3500);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_cboStatus']", "Good");  // changing status to Good
				
					Thread.sleep(3500);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_btnSubmit']");// click on save button
				
					Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					Function_Classes.field_click(driver,xpath);
				}
				else {
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					Function_Classes.field_click(driver,xpath);
					
				}
				
			
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']","VA LOC Credit" ); // Loan selection
				
		        Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
		        Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']","2000" ); // Loc amount selection
			
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the Loan Amount 
				Function_Classes.field_sendKeys(driver, xpath, "2001");				
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the Loan Amount 
				Function_Classes.field_sendKeys(driver, xpath, "2001");				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");  // Click on Next button
				
				Thread.sleep(3000);
				String Loan_Check = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_upnlErr']/div/div[2]/ul/li");
				System.out.println(Loan_Check);
				
				
				Thread.sleep(3000);
				/*if (driver.findElements( By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_upnlErr']/div/div[2]/ul/li")).size() != 0){
					Thread.sleep(3500);
					//xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
					String Loan_Check = Function_Classes.field_gettext(driver, xpath);
					System.out.println("error mg "  + Loan_Check);
					}*/
				
					
					if(Loan_Check.contains("The Initial Draw Amount cannot be greater than the Loc Loan Amount")) {
						Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Cancel']");			
						}
				
				
				
				Thread.sleep(3000);	
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']","VA LOC Credit" ); // Loan selection
				
		        Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
		        Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']","2000" ); // Loc amount selection
			
			
				Thread.sleep(vSleep+2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering Amount
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(3500);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				Function_Classes.field_sendKeys(driver,xpath , "2000");  // Enter Amount
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");  // Click on Next button	
								
			    Thread.sleep(3000);	           
	           Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
				
	           Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
				
				Thread.sleep(3500);
				
				

				Thread.sleep(3500);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" );  // Clicking on Loan ID
				
				Thread.sleep(3000);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
				
				Thread.sleep(3000);				
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "Test" );  // Enter Reason
				
				Thread.sleep(3000);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']" );  // Clicking on Void Entire Loan button		
				
				Thread.sleep(3000);	
				 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 

				 System.out.println("Verify_Loan_Amt_VA     -- Passed");
				 System.out.println("_4_a_Verify_Loan_Amt_VA     -- Passed");

					 Thread.sleep(2000);
					 driver.close();

			}
				
				
		}