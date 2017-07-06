package VA;
import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _47_Void_Loan_Payoff {

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
	public void Url_search (){		
		
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

			Thread.sleep(2000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(2000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		
		Thread.sleep(2000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(2000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		
		Thread.sleep(2000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(2000);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		//String keys = "2989 Business date is always current date, VA";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(2000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+2000);			

	}
	
			
			@Test (priority = 5)
			public void Void_Loan_Payoff () throws Exception{
				//xlData = Utils.readXL(path,"TestData");	
				
			
				
				xlCustFName = xlData [1][4];
				System.out.println("Void_Loan_Payoff    --    Started");
							
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
					Function_Classes.field_click(driver, xpath);
					
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
					Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3500);
				String status = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblStatus']");// reaing customer status
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
				
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(2000);
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
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']", "100");// entering the Loan Amount 
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']", "100");// entering the Loan Amount 
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");  // Click on Next button		
				
			    Thread.sleep(2000);	           
	           Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
				
	           Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
				
				Thread.sleep(3500);
				
				
					
				
				Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtUsername']";  // Clearing User Name field
				Function_Classes.field_clear(driver, xpath );
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtUsername']";  // Passing User name 
				Function_Classes.field_sendKeys(driver, xpath, "dateadm");
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtPasswd']";  // Clearing Password field
				Function_Classes.field_clear(driver, xpath );
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtPasswd']";  // Passing Password
				Function_Classes.field_sendKeys(driver, xpath, "changeme123");
				
				Thread.sleep(2000);
				xpath = ".//*[@id='btnSubmit']"; // Click on go button
				Function_Classes.field_click(driver, xpath);
				
				Thread.sleep(2000);
				Function_Classes.Admin_Setup(driver);
				
				Thread.sleep(2000);
		         Function_Classes.field_link(driver, "2989 Automation Test Store, VA");// clicking on  Automation store
		         
		         Thread.sleep(2000);
			     String d = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']");
			     System.out.println(d);
 
		        Thread.sleep(2500);
		        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // clicking on  edit button
		        Function_Classes.field_click(driver, xpath);
		        
		        Thread.sleep(2000);
		        Function_Classes.field_clear(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']" );  // clear the field
		        
		        String new_date2 = Function_Classes.date_auto_change(3);  // changing date 
			       
			        Thread.sleep(2000);
			        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
			        Function_Classes.field_sendKeys(driver, xpath, new_date2 );
			        
			        Thread.sleep(2000);
			        xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			        Function_Classes.field_click(driver, xpath);

			        Thread.sleep(2000);        
			        Function_Classes.logout (driver, ".//*[@id='menuv2']/li[9]/a" ); // for log out 
				
			       
			    	Thread.sleep(2000);
					xpath=".//*[@id='txtUsername']";  // Clearing User Name field
					Function_Classes.field_clear(driver, xpath );
					
					Thread.sleep(2000);
					xpath=".//*[@id='txtUsername']";  // Passing User name 
					Function_Classes.field_sendKeys(driver, xpath, xlUsername);
					
					xpath=".//*[@id='txtPasswd']";  // Clearing Password field
					Function_Classes.field_clear(driver, xpath );
					
					Thread.sleep(2000);
					xpath=".//*[@id='txtPasswd']";  // Passing Password
					Function_Classes.field_sendKeys(driver, xpath, xlPassword);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='btnSubmit']"; // Click on go button
					Function_Classes.field_click(driver, xpath);
					
					Thread.sleep(2000);
					xpath=".//*[@id='ddlStore']";  // selecting the state 
					//String keys = "2998 Business date is always current date, VA";
					Function_Classes.field_sendKeys(driver, xpath, xlStore);

					Thread.sleep(2000);
					xpath = ".//*[@id='btnSelStore']"; // clicking ok 
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep+2000);	
					xpath =".//*[@id='Left']/a[5]";  // click on Customers button
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter the customer name
					Function_Classes.field_sendKeys(driver, xpath, xlData [1][4]);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on search button
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // clicking on the loan id 
					Function_Classes.field_click (driver, xpath);
					
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span");  // Click on Make Payment button 
				
				Thread.sleep(2000);
				String pay_val= driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']")).getAttribute("value"); // read the payment amount
				System.out.println("Pay" + pay_val);
				
				Thread.sleep(2500);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']", pay_val);	// entering the amount
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on next button 
				
				Thread.sleep(3500);
				
							
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']");  // click Loan ID
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkVoid']/span");  // Click on Void a Transaction button 
				 
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgDataList_ctl04_btnEdit']");  // Click on date link button 
				
				Thread.sleep(2000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "test");  // enter Reason
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // Click on Void Entire Loan button 				
				
								
				Thread.sleep(3000);
				Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			
								
				Thread.sleep(2000);
				xpath=".//*[@id='txtUsername']";  // Clearing User Name field
				Function_Classes.field_clear(driver, xpath );
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtUsername']";  // Passing User name 
				Function_Classes.field_sendKeys(driver, xpath, "dateadm");
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtPasswd']";  // Clearing Password field
				Function_Classes.field_clear(driver, xpath );
				
				Thread.sleep(2000);
				xpath=".//*[@id='txtPasswd']";  // Passing Password
				Function_Classes.field_sendKeys(driver, xpath, "changeme123");
				
				Thread.sleep(2000);
				xpath = ".//*[@id='btnSubmit']"; // Click on go button
				Function_Classes.field_click(driver, xpath);
				
				Thread.sleep(2000);
				Function_Classes.Admin_Setup(driver);
				
				Thread.sleep(4000);
		         Function_Classes.field_link(driver, "2989 Automation Test Store, VA");// clicking on  Automation store
 
		        Thread.sleep(2500);
		        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // clicking on  edit button
		        Function_Classes.field_click(driver, xpath);
		        
		        Thread.sleep(2000);
		        Function_Classes.field_clear(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']" );  // clear the field
		        
		       // String new_date3 = Function_Classes.date_auto_change(0);  // changing date 
			       
			       /* Thread.sleep(3500);
			        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
			        Function_Classes.field_sendKeys(driver, xpath, new_date3 );*/
			        
			        
			        Thread.sleep(3500);
			        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
			        Function_Classes.field_sendKeys(driver, xpath, d );
			        
			        Thread.sleep(2000);
			        xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			        Function_Classes.field_click(driver, xpath);

			        Thread.sleep(2000);        
			        Function_Classes.logout (driver, ".//*[@id='menuv2']/li[9]/a" ); // for log out 
			        
			        Thread.sleep(2000);
					xpath=".//*[@id='txtUsername']";  // Clearing User Name field
					Function_Classes.field_clear(driver, xpath );
					
					Thread.sleep(2000);
					xpath=".//*[@id='txtUsername']";  // Passing User name 
					Function_Classes.field_sendKeys(driver, xpath, xlUsername);
					
					xpath=".//*[@id='txtPasswd']";  // Clearing Password field
					Function_Classes.field_clear(driver, xpath );
					
					Thread.sleep(2000);
					xpath=".//*[@id='txtPasswd']";  // Passing Password
					Function_Classes.field_sendKeys(driver, xpath, xlPassword);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='btnSubmit']"; // Click on go button
					Function_Classes.field_click(driver, xpath);
					
					Thread.sleep(2000);
					xpath=".//*[@id='ddlStore']";  // selecting the state 
					//String keys = "2989 Business date is always current date, VA";
					Function_Classes.field_sendKeys(driver, xpath, xlStore);

					Thread.sleep(2000);
					xpath = ".//*[@id='btnSelStore']"; // clicking ok 
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep+2000);	
					xpath =".//*[@id='Left']/a[5]";  // click on Customers button
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter the customer name
					Function_Classes.field_sendKeys(driver, xpath, xlData [1][4]);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on search button
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // clicking on the loan id 
					Function_Classes.field_click (driver, xpath);
			        
			        
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // clicking on the loan id 
					Function_Classes.field_click (driver, xpath);
		
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";  // clicking on the Void Entire Loan 
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']"; // enter the Reason
					Function_Classes.field_sendKeys(driver, xpath, "test");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // clicking on the Void Entire Transaction  
					Function_Classes.field_click (driver, xpath);
			        
					Thread.sleep(3000);
					Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out        
			        
				
					 Thread.sleep(2000);
					 driver.close();
				
					 System.out.println("Void_Loan_Payoff    --    Passed");
					 System.out.println("_47_Void_Loan_Payoff    --    Passed");
			}
				
				
		}
			

				
			
			