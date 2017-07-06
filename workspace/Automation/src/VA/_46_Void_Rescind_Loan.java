package VA;

import Utility.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _46_Void_Rescind_Loan {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,new_number;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	Boolean a1;

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
		
		Thread.sleep(vSleep+2000);			

	}
	
	
			
			@Test (priority = 5)
			public void Void_Resicnd_Loan () throws Exception{
				//xlData = Utils.readXL(path,"TestData");	
				xlCustFName = xlData [1][4];
							
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
					Function_Classes.field_click(driver, xpath);
					
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
					Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				System.out.println("Void_Resicnd_Loan    --  Started");
				
				Thread.sleep(3500);
				
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
				Function_Classes.field_click(driver,xpath);
				
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
				
				
	
				Thread.sleep(3000);
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";
				date = Function_Classes.field_gettext(driver,xpath);
				System.out.println(date);
				//date1 = date + 1;	
				
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']");  // Click on Loan Id link
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkRescind']/span");  // Click on  Rescind Button

				// VOIDING SCRIPT	STARTS		
				
				
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgCreditDraw_ctl02_lnkDrawId']");  // Click on link
				
				/*Thread.sleep(vSleep);
				String Rescind_amt = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']");
				System.out.println(Rescind_amt);*/
				
				Thread.sleep(2000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']","100");// amount from customer
				
				Thread.sleep(2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // Click on Next button
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwPrinBal']";
				String Principal = Function_Classes.field_gettext(driver,xpath);
				System.out.println(Principal);
							
							
				
							// voiding 
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // clicking on the loan id 
							Function_Classes.field_click (driver, xpath);
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkVoid']/span";  // clicking on Void transaction 
							Function_Classes.field_click (driver, xpath);
							
							Thread.sleep(2000);
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgDataList_ctl04_btnEdit']";  // click on link 
							Function_Classes.field_click (driver, xpath);
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";   // reason 
							Function_Classes.field_sendKeys(driver, xpath, "test");
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // submit
							Function_Classes.field_click (driver, xpath);
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";  // click on Void Entire Loan button
							Function_Classes.field_click (driver, xpath);
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";   // reason 
							Function_Classes.field_sendKeys(driver, xpath, "test");
							
							Thread.sleep(2000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // submit
							Function_Classes.field_click (driver, xpath);
							
								
			// VOIDING SCRIPT	ENDS
				
				
				Thread.sleep(2000);
				Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
				
				 Thread.sleep(2000);
				 driver.close();
				
				 System.out.println("Void_Resicnd_Loan    --  Passed");	
				 System.out.println("_46_Void_Rescind_Loan    --  Passed");	
		}	

}
