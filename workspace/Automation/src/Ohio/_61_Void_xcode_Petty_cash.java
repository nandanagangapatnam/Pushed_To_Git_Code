package Ohio;

import Utility.Utils;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import Functions.Function_Classes;


	public class _61_Void_xcode_Petty_cash {
		
		String vBrowser = "FireFox";
		WebDriver driver;
		String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
		String Username, Password, LoggedName;
		int vSleep;
		String xpath;
		String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
		String[][] xlData;
		//String path = "C:/1/Seleniumproject/TestData_2007.xls";
		//String wpath = "C:/1/Seleniumproject/TestResult_2007.xls";
		
		public static Properties OR=null;
		
		//@BeforeTest	
		@Test (priority = 1)
		public void Url_search (){		
			
			driver = new FirefoxDriver();			 
		driver.manage().window().maximize();		
		// Ecash site access
		Function_Classes.navigate_to(driver, URL);			
	}
		
		
		@Test (priority = 2)
		public void  StroreLogin () throws Exception{
			OR = new Properties();
			FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
			OR.load(fn);

			
			xlData = Utils.readXL(OR.getProperty("path"),"TestData");
			
					
			//Utils.setExcelFile(xlPath,"Login");
			
				xlUsername = xlData [1][0];
				xlPassword = xlData [1][1];
				xlStore = xlData [1][2];
				xlLoggedname = xlData [1][3];	
				vSleep = 2000;

				Thread.sleep(3500);
			xpath=".//*[@id='txtUsername']";  // Clearing User Name field
			Function_Classes.field_clear(driver, xpath );
			Thread.sleep(3500);
			xpath=".//*[@id='txtUsername']";  // Passing User name 
			Function_Classes.field_sendKeys(driver, xpath, xlUsername);
			Thread.sleep(3500);
			xpath=".//*[@id='txtPasswd']";  // Clearing Password field
			Function_Classes.field_clear(driver, xpath );
			Thread.sleep(3500);
			xpath=".//*[@id='txtPasswd']";  // Passing Password
			Function_Classes.field_sendKeys(driver, xpath, xlPassword);
			Thread.sleep(3500);
			xpath = ".//*[@id='btnSubmit']"; // Click on go button
			Function_Classes.field_click(driver, xpath);
			Thread.sleep(3500);
			xpath=".//*[@id='ddlStore']";  // selecting the state 
			//String keys = "9998 Business date is always current date, TN";
			Function_Classes.field_sendKeys(driver, xpath, xlStore);
			Thread.sleep(3500);
			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			Function_Classes.field_click (driver, xpath);
			
			Thread.sleep(vSleep+3500);			

		}
		
		// To Open / reopen the Cash Drawer
		@Test (priority = 3)
		public void Open_Drawer() throws Exception {
		//	xlData = Utils.readXL(path,"TestData");	
			
			String Close_Submit = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDenomSubmit']";  // click on  Close Submit button
			
			xpath = ".//*[@id='ctl00_AppLeftNav_lnkOpenDrawer']";
			Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
			
			String Drawer_status = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_lnkOpenClose']";  // Drawer status
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
							Function_Classes.field_click(driver, Finish_button);  // cliking on finish button
							Thread.sleep(3500);
							
							Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		
							System.out.println("Opening again");
							
							Thread.sleep(3500);
							Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	  
							System.out.println("Its now in Close status");	
							Thread.sleep(3500);	    
				    }    
				  }      
				  catch(Exception e)     
				  {       
					  System.out.println("Finish Button NOT visible");
						Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		 
						System.out.println("Opening again");
						
						Thread.sleep(3500);
						Function_Classes.field_click(driver, Close_Submit);  // clicking on submit
						System.out.println("Its now in Close status");	
						Thread.sleep(3500);   
				  }       
				
								 
			}
			
		}
	

		
			
		@Test (priority = 4)
		public void xcode_Petty_No_Cust_No_Cart() throws Exception {
			//xlData = Utils.readXL(path,"TestData");	

			 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_trstore_vault_counts']/td[7]";
			 String drawerinitialamt = Function_Classes.field_gettext(driver, xpath);
			 System.out.println("Drawer Amount       " +drawerinitialamt);
			 xlData[1][15] = drawerinitialamt;
			 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			 Thread.sleep(3500);  
			
			xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(vSleep+2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liPettyCash']/a"; // clicking on Petty Cash link
			Function_Classes.field_click(driver,xpath);
			

			Thread.sleep(vSleep+2000);
			//Select oSelect = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cboReason']")));   // selecting theX General Insurance
			//oSelect.selectByIndex(25);	
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cboReason']", "X General Insurance");
			
			Thread.sleep(vSleep+2000);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtVendor']", "Test");  // Enter Vendor name
			//driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB); 
			
			Thread.sleep(vSleep+2000);			 
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "test");  // enter comments
		
			Thread.sleep(vSleep+2000);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']", "100");  // Enter Amount
			//driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB);					
			
			Thread.sleep(vSleep+2000);			 
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "test");  // enter comments
			
			Thread.sleep(3500);				 
			Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on submit button
			
			Thread.sleep(3500);		
			//xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlConfirm']/div/div[1]";  // capturing the confirmation message
			String misc_assert_val = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlConfirm']/div/div[1]" );
			System.out.println(misc_assert_val);
			
			if (misc_assert_val.equals("Petty Cash Confirmation")) {
				xlData[1][14] = "Pass";
				
			    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			}
			else {
				xlData[1][14] = "Fail";
				
			    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			}
			
			   
			Thread.sleep(3500);	  
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']"; // cick on submit button
			Function_Classes.field_click(driver,xpath);
			Thread.sleep(vSleep+3500);	
			System.out.println("Pass2");
			
		
		
			Thread.sleep(3500);	 
		
		xpath = ".//*[@id='ctl00_ctl00_AppLeftNav_lnkCloseDrawer']";
		Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
		
		Thread.sleep(3500);	 
		 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_trstore_vault_counts']/td[7]";
		 String drawerfinalamt = Function_Classes.field_gettext(driver, xpath);
		 System.out.println("Drawer Amount" + drawerfinalamt);
		 xlData[1][16] = drawerfinalamt;
		 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
		 
		 if (drawerfinalamt.equals("-$100.00")){
			 xlData[1][18] = "Verifying Petty_No_Cust_No_Cart sucessfull";
			 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
		 }
		 else
		 {
			 xlData[1][18] = "Verifying Petty_No_Cust_No_Cart Failed";
			 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
		 }
		 
		 xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
			Function_Classes.field_click(driver,xpath);
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liVoid']/a";  // click on  Void transaction
			Function_Classes.field_click(driver,xpath);
					
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
			 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_trstore_vault_counts']/td[7]";
			 String drawerfinalamt1 = Function_Classes.field_gettext(driver, xpath);
			 System.out.println("Drawer Amount" + drawerfinalamt1);
			 xlData[1][17] = drawerfinalamt1;
			 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			 
			 if (drawerfinalamt1.equals("$0.00")){
				 xlData[1][20] = "Voiding Petty_No_Cust_No_Cart sucessfull";
				 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			 }
			 else
			 {
				 xlData[1][20] = "Voiding Petty_No_Cust_No_Cart Failed";
				 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			 }
			 
			 System.out.println(" xcode_Petty_No_Cust_No_Cart  --  Passed" );
			 
			 Thread.sleep(3500);	
			 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			 
			 Thread.sleep(2000);
			 driver.close();	
	}

		
		
}
