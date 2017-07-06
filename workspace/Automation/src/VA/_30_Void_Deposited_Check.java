package VA;

import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _30_Void_Deposited_Check {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number;
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
		
		Thread.sleep(vSleep+3000);			

	}
	
				
			@Test (priority = 5)
			public void Deposit_Check_Void () throws Exception{
			
				Thread.sleep(3000);
				System.out.println("Deposit_Check_Void    --   Started");
				
				xlCustFName = xlData [1][4];
				
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
					Function_Classes.field_click(driver, xpath);
					
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
					Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewCheck']"; // clicking on the New Check Link 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath =".//*[@id='txtMakerName']"; // entering Maker Number
				Function_Classes.field_sendKeys(driver, xpath, "Auto Test");
				
				Thread.sleep(3000);
				xpath = ".//*[@id='btnSubmit']"; // clicking on the Search Button
				Function_Classes.field_click(driver,xpath);				
				
				Thread.sleep(4000);
			   Function_Classes.index_select(driver, ".//*[@id='cboCheckType']", 8); // selecting MO 
			   
			   Thread.sleep(3000);
			   xlcheckno = xlData [1][11];
				xpath =".//*[@id='txtCheckNum']"; // entering check Number
				Function_Classes.field_sendKeys(driver, xpath, xlcheckno);
				
				Thread.sleep(3000);
				String check_date = Function_Classes.date_auto_change(-1);  // reducing the date less than current date
				System.out.println(check_date);
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='CheckDate_txtDate']", check_date); // check Amount
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='txtAmount']", "100"); // check Amount
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnStep1Submit']");  // clicking on Next button
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnStep2Submit']");  // clicking on cash check button
				
				Thread.sleep(3000);
				Function_Classes.field_gettext(driver, ".//*[@id='lblPayoutMsg']"); // confirmaing the transcation - reading  "Amount to Customer"
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnContinue']");  // clicking on continue button
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='dgOutstanding']/tbody/tr[2]/td[1]/a");  // clicking on Check hyperlink button
				
				Thread.sleep(3500);
				Function_Classes.logout1(driver, ".//*[@id='AppHeader_liTools']/a", ".//*[@id='AppHeader_liTools_DepChkCash']/a"); // clicking on Deposit Cashed checks
				//Function_Classes.logout1(driver, ".//*[@id='ctl00_AppHeader_liTools']/a", ".//*[@id='ctl00_AppHeader_liTools_DepChkCash']/a"); // clicking on Deposit Cashed checks
				
				/*Thread.sleep(3000);
				String va = Function_Classes.split(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_frmClear_dgCashed']/tbody/tr[last()]/td[1]/strong", 1);
				System.out.println ((va+1)  + "va");
				int val1 = Integer.parseInt(va);  // changing it to int 
				int vals = val1 +1;
				System.out.println ( (val1+1) + "va");
				
				String str = Integer.toString(vals);	// changing it to string 	
				
				Thread.sleep(3000);
				Function_Classes.selectChk(driver, str);  // clicking on check box*/
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_frmClear_dgCashed_ctl02_chkSelect']"); // clicking on check box
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_frmClear_btnSubmit']"); // clicking on Deposit Selected button
				
				
				Thread.sleep(3000);  //  refocussing on main window
			
				Function_Classes.window(driver);
				   
				   WebElement element = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_AppHeader_liTools']/a"));
				   WebDriverWait wait = new WebDriverWait(driver, 20); //here, wait time is 20 seconds

				   wait.until(ExpectedConditions.visibilityOf(element)); //this will wait for element to be visible for 20 seconds
				   element.click(); //now it clicks on element
				
				
				
				
				Thread.sleep(3000);
				Function_Classes.logout1(driver, ".//*[@id='ctl00_ctl00_AppHeader_liTools']/a", ".//*[@id='ctl00_ctl00_AppHeader_liTools_ClrChkCash']/a"); // clicking on Deposit Cashed checks
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_frmClear_dgChksOnHold_ctl02_chkSelect']"); // clicking on check box
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_frmClear_btnClear']"); // clicking on Clear Selected button
				
				
				Function_Classes.window(driver);	  //  switch to the active window			
				
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				Function_Classes.field_click(driver, xpath);
				
				xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
				Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
				
			
				Thread.sleep(3000);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
				Function_Classes.field_click(driver,xpath);
						
				
				Thread.sleep(3500);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgChkPrevious']/tbody/tr[2]/td[1]/a");
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='lnkVoid']");  // clicking on Check void last transaction button
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='dgHistory_ctl06_lnkDoVoid']");  // clicking on disburse to customer button				
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver,".//*[@id='txtReason']", "test");  // clicking on disburse to customer button
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnSubmit']");  // clicking on void transaction button	
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnNo']");  // clicking on NO button		
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='lnkVoid']");  // clicking on Check void last transaction button
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='dgHistory_ctl05_lnkDoVoid']");  // clicking on disburse to customer button				
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver,".//*[@id='txtReason']", "test");  // clicking on disburse to customer button
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnSubmit']");  // clicking on void transaction button	
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnNo']");  // clicking on NO button		
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='lnkDelete']");  // clicking on Void entire check	
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver,".//*[@id='txtDelReason']", "test");  // clicking on Reason
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='btnDelSubmit']");  // clicking on Void  check	
				
				Thread.sleep(3000);
				driver.switchTo().alert().accept();  // clicking on OK
				
				Thread.sleep(2000);
				Function_Classes.logout1(driver, ".//*[@id='menuv2']/li[7]/a", ".//*[@id='AppHeader_btnLogout']"); // clicking on Deposit Cashed checks// for log out 
				
				 Thread.sleep(2000);
				 driver.close();
					
				 System.out.println("Deposit_Check_Void    --   Passed");
			
			}
	
	
}
