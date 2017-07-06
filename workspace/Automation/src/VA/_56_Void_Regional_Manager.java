package VA;

import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _56_Void_Regional_Manager {
	
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
	
	@Test (priority = 2)
	public void  date () throws InterruptedException{
		
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
		
		Thread.sleep(3500);									
		WebElement element3 = driver.findElement(By.xpath(".//*[@id='ctl00_AppHeader_liAdmin']/a"));		 // clicking on Setup 	 
        Actions action3 = new Actions(driver);	 
        action3.moveToElement(element3).moveToElement(driver.findElement(By.xpath(".//*[@id='ctl00_AppHeader_liAdmin_Users']/a"))).click().build().perform();	
	
        
        Thread.sleep(3500);
        Function_Classes.field_link(driver, "Naresh DM");// clicking on Naresh DM 
        
        
        Thread.sleep(3500);         
        Function_Classes.field_click(driver, ".//*[@id='ctl00_MainContent_lnkEdit']"); // clicking on  edit button
        
        Thread.sleep(3500); 
        //Function_Classes.index_select(driver, ".//*[@id='ctl00_MainContent_cboUserType']", 3);  // user type
        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_cboUserType']", "Regional Manager");
                
        Thread.sleep(3500); 
        //Function_Classes.index_select(driver, ".//*[@id='ctl00_MainContent_cboRole']", 4);  // user role
        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_cboRole']", "Regional Manager");
        
        Thread.sleep(3500); 
        //Function_Classes.index_select(driver, ".//*[@id='ctl00_MainContent_cboRegion']", 6);  // Region
        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_cboRegion']", "Virginia");
        
        Thread.sleep(3500); 
        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_txtPasswdConfirm']", "changeme123");
        
              
        Thread.sleep(2000);
        Function_Classes.field_click(driver, ".//*[@id='ctl00_MainContent_btnSubmit']");  //  click on save
        
        Thread.sleep(3500);        
        Function_Classes.logout1 (driver, ".//*[@id='menuv2']/li[9]/a", ".//*[@id='ctl00_AppHeader_btnLogout']" ); // for log out 
	}
	
	@Test (priority = 3)
	public void  StroreLogin () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);
		
		Thread.sleep(3500);
		xlData = Utils.readXL(OR.getProperty("vpath"),"TestData");
		
		
		
		//xlData = Utils.readXL(path,"TestData");	
				
		//Utils.setExcelFile(xlPath,"Login");
		
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

		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3500);			

	}
	
	// To Open / reopen the Cash Drawer
	@Test (priority = 4)
	public void Open_Drawer() throws Exception {
		Thread.sleep(3500);
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
						Thread.sleep(3500);
						
						Function_Classes.field_click(driver, Drawer_status);  // clicking on close / Open button		
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
					Function_Classes.field_click(driver, Drawer_status);  // clicking on close / Open button		 
					System.out.println("Opening again");
					
					Thread.sleep(3500);
					Function_Classes.field_click(driver, Close_Submit);  // clicking on submit
					System.out.println("Its now in Close status");	
					Thread.sleep(3500);   
			  }       
			
							 
		}
		
	}


	
		
	@Test (priority = 5)
	public void Envelopes_No_Cust_No_Cart() throws Exception {
		//xlData = Utils.readXL(path,"TestData");	
		 System.out.println("Envelopes_No_Cust_No_Cart  -- Started");

		 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl05_trstore_vault_counts']/td[7]";
		 String drawerinitialamt = Function_Classes.field_gettext(driver, xpath);
		 System.out.println(drawerinitialamt);
		 xlData[1][15] = drawerinitialamt;
		 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
		 Thread.sleep(3500);  
		
		xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
		Function_Classes.field_click(driver,xpath);
		
		Thread.sleep(vSleep+2000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liMiscSales']/a"; // clicking on Misc Sales link
		Function_Classes.field_click(driver,xpath);
		
		Thread.sleep(vSleep+1000);
		Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']", "Envelopes");  // selecting the envelopes
		Thread.sleep(2500);
		Function_Classes.field_click(driver, ".//*[@id='Main']/div[1]");
		
		Thread.sleep(vSleep+2000);
		xpath =  ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtQuantity']";  // entering Amount
		Function_Classes.field_click(driver,xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(3500);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
		Function_Classes.field_sendKeys(driver,xpath , "2");  // Enter Amount
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB); 			
		
	
		Thread.sleep(vSleep+2000);
		Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']");
		Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']", "test");  // enter comments
		
		Thread.sleep(vSleep+2000);
		Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']");
		Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']", "0.20");  // Enter Amount from customer
		//driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB);	
		
		Thread.sleep(3500);				 
		Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on submit button
		
		Thread.sleep(3500);		
		//xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlConfirm']/div/div[1]";  // capturing the confirmation message
		String misc_assert_val = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlConfirm']/div/div[1]" );
		System.out.println(misc_assert_val);
		
		if (misc_assert_val.equals("Miscellaneous Sales Confirmation")) {
			xlData[1][14] = "Pass";
			
		    Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
		}
		else {
			xlData[1][14] = "Fail";
			
		    Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
		}
		
		   
		Thread.sleep(3500);	  
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']"; // click on continue  button
		Function_Classes.field_click(driver,xpath);
		Thread.sleep(vSleep+3500);	
		System.out.println("Pass2");
		
	
	
		Thread.sleep(3500);	 
	
	xpath = ".//*[@id='ctl00_ctl00_AppLeftNav_lnkCloseDrawer']";
	Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
	
	Thread.sleep(3500);	 
	 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl05_trstore_vault_counts']/td[7]";
	 String drawerfinalamt = Function_Classes.field_gettext(driver, xpath);
	 System.out.println(drawerfinalamt);
	 xlData[1][16] = drawerfinalamt;
	 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
	 
	 if (drawerfinalamt.equals("$0.20")){
		 xlData[1][18] = "Verifying General Insurance sucessfull";
		 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
	 }
	 else
	 {
		 xlData[1][18] = "Verifying General Insurance Failed";
		 Utils.writeXL(OR.getProperty("vwpath"),"Login",xlData);
	 }
	 

	 
	 Thread.sleep(3500);
	 Function_Classes.logout1(driver, ".//*[@id='menuv2']/li[7]/a", ".//*[@id='ctl00_ctl00_AppHeader_btnLogout']");  // log out
	 
	 
	 Thread.sleep(2000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(2000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, "nareshdm");
		
		Thread.sleep(2000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(2000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, "changeme123");
		
		Thread.sleep(2000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
	 
		Thread.sleep(3500);
		 Function_Classes.logout1(driver, ".//*[@id='ctl00_AppHeader_liTools']/a", ".//*[@id='ctl00_AppHeader_liTools_Void']/a");  // Void Transcation
		 		 
		 
		// Thread.sleep(3500);
		 //Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LocSelector_cboDistrict']", "Chattanooga District"); // selecting district
		 
		 Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LocSelector_cboStore']", "2989 Automation Test Store, VA"); // selecting Store
			 
		 
		 Thread.sleep(3500);
			Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnGo']");  // click on Submit button
			
			
			 Thread.sleep(3500);
		        Function_Classes.field_link(driver, "2989 Automation Test Store, VA");// clicking on transaction link
			
		        Thread.sleep(3500); 
		       // Function_Classes.index_select(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlStoreDrawer']", 4);  // select Drawer
		        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlStoreDrawer']", "Drawer 3");
			
			
			Thread.sleep(3500);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "Test");  // enter Reason 
			
			
			Thread.sleep(3500);
			Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on the submit button
			
	
			 Thread.sleep(3500);
			 Function_Classes.logout1(driver, ".//*[@id='menuv2']/li[7]/a", ".//*[@id='ctl00_ctl00_AppHeader_btnLogout']");  // log out
			 
			 
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

			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			Function_Classes.field_click (driver, xpath);
			
			Thread.sleep(vSleep+3500);	
			
			xpath = ".//*[@id='ctl00_AppLeftNav_lnkOpenDrawer']";
			Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
			
			Thread.sleep(vSleep+3500);	
			 String f_drawamount = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl05_lblBalance']");
			 System.out.println("f_drawamount" + f_drawamount);
			 
			 Thread.sleep(3500);
			 Function_Classes.logout1(driver, ".//*[@id='menuv2']/li[7]/a", ".//*[@id='ctl00_ctl00_AppHeader_btnLogout']");  // log out
			 
			 
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
				
				Thread.sleep(3500);									
				WebElement element3 = driver.findElement(By.xpath(".//*[@id='ctl00_AppHeader_liAdmin']/a"));		 // clicking on Setup 	 
		        Actions action3 = new Actions(driver);	 
		        action3.moveToElement(element3).moveToElement(driver.findElement(By.xpath(".//*[@id='ctl00_AppHeader_liAdmin_Users']/a"))).click().build().perform();	
			
		        
		        Thread.sleep(3500);
		        Function_Classes.field_link(driver, "Naresh DM");// clicking on Naresh DM 
		        
		        
		        Thread.sleep(3500);         
		        Function_Classes.field_click(driver, ".//*[@id='ctl00_MainContent_lnkEdit']"); // clicking on  edit button
		        	        
		        
		        Thread.sleep(3500); 
		       // Function_Classes.index_select(driver, ".//*[@id='ctl00_MainContent_cboUserType']", 2);  // user type
		        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_cboUserType']", "District Manager");
		        
		        Thread.sleep(3500); 
		        //Function_Classes.index_select(driver, ".//*[@id='ctl00_MainContent_cboRole']", 3);  // user role
		        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_cboRole']", "District Manager");
		        
		        Thread.sleep(3500); 
		        //Function_Classes.index_select(driver, ".//*[@id='ctl00_MainContent_trDistrict']/td[2]", 11);  // Region
		        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_cboDistrict']", "Virginia - Richmond");        
		        
		        
		        Thread.sleep(3500); 
		        Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_MainContent_txtPasswdConfirm']", "changeme123");
		        
		              
		        Thread.sleep(2000);
		        Function_Classes.field_click(driver, ".//*[@id='ctl00_MainContent_btnSubmit']");  //  click on save
		        
		        Thread.sleep(3500);        
		        Function_Classes.logout1 (driver, ".//*[@id='menuv2']/li[9]/a", ".//*[@id='ctl00_AppHeader_btnLogout']" ); // for log out 
			 
				 Thread.sleep(2000);
				 driver.close();	
			 
			 
			 
	 
}

	
	
	
	

}
