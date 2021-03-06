package VA;
import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _15_Verify_ATM_with_Cart {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,store,cust;
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

			Thread.sleep(3000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(3000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		
		Thread.sleep(3000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(3000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		
		Thread.sleep(3000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(3000);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		//String keys = "2989 Business date is always current date, VA";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(3000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3000);			

	}
	
	
	// To Open / reopen the Cash Drawer
			@Test (priority = 3)
			public void Open_Drawer() throws Exception {
							
				//xlData = Utils.readXL(path,"TestData");	
				
				String Close_Submit = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDenomSubmit']";  // click on  Close Submit button
				
				xpath = ".//*[@id='ctl00_AppLeftNav_lnkOpenDrawer']";
				Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
				
				String Drawer_status = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl05_lnkOpenClose']";  // Drawer status
				String drawer_name = Function_Classes.field_gettext(driver, Drawer_status); // reading the value (open or close)
				System.out.println(drawer_name);
				Thread.sleep(vSleep);
				
				if (drawer_name.equals("Open")){
				System.out.println("Open");
				
				Function_Classes.field_click(driver, Drawer_status);   // clicking on  Open button		 
				Function_Classes.field_click(driver, Close_Submit);  // clicking on submit				
				System.out.println("Its now in Close status");																  
				}
				
				
				if (drawer_name.equals("Close")){	
					System.out.println("Close");
				    
					
					Thread.sleep(3500);
					Function_Classes.field_click(driver, Drawer_status);  // clicking on close 
	                
					Thread.sleep(3500);
					Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	
					Thread.sleep(3500);
					
					
					 try   
					  {    
						 if (driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblConfirmation']")).isDisplayed())   
					    {      
							 System.out.println("Finish Button visible");
								
								String Finish_button = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']";  // clicking on Finish button	
								Function_Classes.field_click(driver, Finish_button);  // clicking on finish button
								Thread.sleep(3000);
								
								Function_Classes.field_click(driver, Drawer_status);  // clicking on close / Open button		
								System.out.println("Opening again");
								
								Thread.sleep(3000);
								Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	  
								System.out.println("Its now in Close status");	
								Thread.sleep(3000);	    
					    }    
					  }      
					  catch(Exception e)     
					  {       
						  System.out.println("Finish Button NOT visible");
							Function_Classes.field_click(driver, Drawer_status);  // clicking on close / Open button		 
							System.out.println("Opening again");
							
							Thread.sleep(3000);
							Function_Classes.field_click(driver, Close_Submit);  // clicking on submit
							System.out.println("Its now in Close status");	
							Thread.sleep(3000);   
					  }       
									 
				}
				
			}
			
			
			@Test (priority = 4)
			public void ATM_With_Cust_with_Cart() throws Exception {

				System.out.println("ATM_With_Cust_with_Cart  -- Started");	
				
				Thread.sleep(3000);	
				xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liMiscSales']/a";  // click on Misc link
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);	
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnCust']";   // click on customer 
				Function_Classes.field_click(driver,xpath);
				
				store = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_AppHeader_lblStore']/strong");
				if (store.contains("VA") ) {
					 cust= "Good 522" ;
				}
				
				Thread.sleep(3000);	
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustSearch_txtName']";  // enter the First name 
				Function_Classes.field_sendKeys(driver, xpath, cust); 
				
				Thread.sleep(3000);	
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustSearch_btnSubmit']";  // clicking on search button 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);	
				Function_Classes.field_link(driver, "Customer, "+cust);// clicking on  Automation store
			
				

				Thread.sleep(vSleep+2000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']", "ATM Withdrawals");
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver, ".//*[@id='Main']/div[1]");
				
				Thread.sleep(3500);
				xpath =  ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // entering Amount
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(3000);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
				Function_Classes.field_sendKeys(driver,xpath , "100");  // Enter Amount
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB); 			
								
				Thread.sleep(vSleep+2000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']");
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']", "test");  // enter comments
		
				Thread.sleep(3000);				 
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']");  // click on Add to Cart button
				
				
				Thread.sleep(3000);	
				xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liMiscSales']/a";  // click on Misc link
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(vSleep+2000);
				Function_Classes.index_select(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']", 5);
				
				Thread.sleep(3000);				 
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']");  // click on Add to Cart button
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlCart']", "Customer, "+cust); // selecting the cart
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSelCart']" ); // clicking on Go button
				
				
				Thread.sleep(3000);
				String ATM_error = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlUpdateArea']/div[1]/div[2]/ul/li");
				System.out.println(ATM_error);  // reading the error 
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_AppLeftNav_lnkCart']");  // clicking on Cart in left navigation 
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cart2_rptcart_ctl01_lblcustomer_name']");  // clicking on Name link
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // clicking on submit button
				
				
				Thread.sleep(3500);	 
				
				xpath = ".//*[@id='ctl00_ctl00_AppLeftNav_lnkCloseDrawer']";
				Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
				
				Thread.sleep(3500);	 
				 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_trstore_vault_counts']/td[7]";
				 String drawerfinalamt = Function_Classes.field_gettext(driver, xpath);
				 System.out.println(drawerfinalamt);
				 xlData[2][16] = drawerfinalamt;
				 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
				 
				 if (drawerfinalamt.equals("$0.50")){
					 xlData[2][18] = "Verifying General Insurance sucessfull";
					 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
				 }
				 else
				 {
					 xlData[2][18] = "Verifying General Insurance Failed";
					 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
				 }
				 
				 	xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);	 
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liVoid']/a";  // click on  Void transaction
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);	 
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnGo']";  // click on submit button
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);	 
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgList_ctl02_lnkEdit00']";  // clicking on the transaction
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);	 
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']"; // entering value for Reason field
					Function_Classes.field_sendKeys(driver, xpath, "test");
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // clicking on submit button
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);	 
					xpath = ".//*[@id='ctl00_ctl00_AppLeftNav_lnkCloseDrawer']";
					Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
					
					Thread.sleep(3500);	 
					 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl05_trstore_vault_counts']/td[7]";
					 String drawerfinalamt1 = Function_Classes.field_gettext(driver, xpath);
					 System.out.println(drawerfinalamt1);
					 xlData[2][17] = drawerfinalamt1;
					 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
					 
					 if (drawerfinalamt1.equals("$0.00")){
						 xlData[2][20] = "Voiding General Insurance sucessfull";
						 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
					 }
					 else
					 {
						 xlData[2][20] = "Voiding General Insurance Failed";
						 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
					 }
					 
					 Thread.sleep(3000);	
					 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
					 Thread.sleep(2000);
					 driver.close();
					 
					 System.out.println("ATM_With_Cust_with_Cart  -- Started");	
		}
			
		
		}		