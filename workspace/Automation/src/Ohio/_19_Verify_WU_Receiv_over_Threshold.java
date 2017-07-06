package Ohio;

import Utility.Utils;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import Functions.Function_Classes;

public class _19_Verify_WU_Receiv_over_Threshold {
	
	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	//String path = "C:/1/Seleniumproject/TestData_2007.xls";
	//String OR.getProperty("wpath") = "C:/1/Seleniumproject/TestResult_2007.xls";
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
		//String keys = "9998 Business date is always current date, TN";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(2000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3000);			

	}
	
/*	// To Open / reopen the Cash Drawer
	@Test (priority = 3)
	public void Open_Drawer() throws Exception {
		//xlData = Utils.readXL(path,"TestData");	
		
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
						Thread.sleep(3000);
						
						Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		
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
					Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		 
					System.out.println("Opening again");
					
					Thread.sleep(3000);
					Function_Classes.field_click(driver, Close_Submit);  // clicking on submit
					System.out.println("Its now in Close status");	
					Thread.sleep(3000);  
					

					 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_trstore_vault_counts']/td[7]";
					 String drawerinitialamt = Function_Classes.field_gettext(driver, xpath);
					 System.out.println("Drawer Amount       "+ drawerinitialamt);
					 xlData[1][15] = drawerinitialamt;
					 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					 Thread.sleep(3000);
			  }       
			
							 
		}

	}	*/
	
	 // MO for more than $3500
		@Test (priority = 4)
		public void WU_Receive_over_Threshold() throws Exception {
			//xlData = Utils.readXL(path,"TestData");	
			 System.out.println("WU_Receive_over_Threshold   -- Started");
  
			 
			 // creating customer 
	/*		 	xlCustFName = xlData [1][4];
				xlCustLName = xlData [1][5];
				xlssn = xlData [1][6];
				xldob = xlData [1][7];*/
				
				
				
				xlCustFName = xlData [1][4];
				String[] arr = xlCustFName.split(" ");    

				 for(int i=0; i<arr.length;i++) {
				     System.out.println(arr[i]);
				}

				int a = Integer.parseInt(arr[1]);
				a = a+2;
				String new_number = Integer.toString(a);
				System.out.println("new number is "  + new_number);
				xlCustFName= arr[0]+" " +new_number;
				System.out.println(xlCustFName);
				
						
				xlCustLName = xlData [1][5];
				//xlssn = xlData [1][6];
				xlssn = new_number+new_number+new_number;
				System.out.println("new ssn is " + xlssn);
	
				xldob = xlData [1][7];
				
				System.out.println(xlCustFName+ xlCustLName + xlssn + xldob );
				// Entering General Information			
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				Function_Classes.field_click(driver, xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
				Function_Classes.field_click(driver, xpath);
				
				Thread.sleep(3000);
				xpath=".//*[@id='FrmCustGeneral_txtFirstName']";  // First Name
				Function_Classes.field_sendKeys(driver, xpath, xlCustFName);
				
				Thread.sleep(3000);
				xpath=".//*[@id='FrmCustGeneral_txtLastName']";  // Last Name
				Function_Classes.field_sendKeys(driver, xpath, xlCustLName);
				
				Thread.sleep(3000);
				xpath=".//*[@id='FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xlssn);
				
				Thread.sleep(3000);
				String xpath=".//*[@id='FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xldob);
			 
			 
				Thread.sleep(3000);
				xpath = ".//*[@id='btnSubmit']";  // Clicking on Save Button
				Function_Classes.field_click(driver,xpath);
				
					Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the Ms button
					Function_Classes.field_click(driver,xpath);
	
					Thread.sleep(vSleep+2000);
					//Select oSelect = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']")));   // selecting the Wire Trans Receive - WU
					//oSelect.selectByIndex(22);	
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']", "Wire Trans Receive – WU");
					
					Thread.sleep(3000);
					Function_Classes.field_click(driver, ".//*[@id='Main']/div[1]");
					
					Thread.sleep(3500);
					xpath =  ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // entering Amount
					Function_Classes.field_click(driver,xpath);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(3000);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Function_Classes.field_sendKeys(driver,xpath , "3004");  // Enter Amount
					//driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB); 			
					
					Thread.sleep(vSleep+2000);			
					xpath =  ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  // entering Fee
					Function_Classes.field_click(driver,xpath);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(3000);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Function_Classes.field_sendKeys(driver,xpath, "2");  // enter Fees		
						
					
					Thread.sleep(vSleep+2000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']");
					Thread.sleep(3000);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']", "test");  // enter comments

					Thread.sleep(3000);				 
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on submit button
				
					Thread.sleep(3000);				 
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on submit button
	
					Thread.sleep(3500);		
					String error = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlUpdateArea']/div[1]/div[2]" );
					System.out.println(error);
					
					if (error.contains("Please correct the following errors before continuing:")) {
						xlData[1][14] = "Pass";
						
					    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					}
					else {
						xlData[1][14] = "Fail";
						
					    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					}
	
					// Editing Customer info 
					Thread.sleep(3000);
					xpath =" .//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkCust']";   // clicking on customer link 
					Function_Classes.field_click(driver, xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkEditCustomer']";  // click on Edit customer link 
					Function_Classes.field_click(driver, xpath);
							
					// Entering Address
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_lnkListAddNew']";  // clik on Edit Address link 
					Function_Classes.field_click(driver, xpath);
					
					Thread.sleep(2000);
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboType']";  // Select Address Type :  Home
					Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect1.selectByIndex(2);
					
					Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtAddrLine1']"; // entering Address
					Function_Classes.field_sendKeys(driver, xpath, "124 Main St");
					
					Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtCity']"; // entering City
					Function_Classes.field_sendKeys(driver, xpath, "Columbus");
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State :  Ohio
					Select oSelect3 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect3.selectByIndex(43);
					
					Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
					Function_Classes.field_sendKeys(driver, xpath, "43201");
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_btnSave']";  //  click on save button
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_pnlInterface']/div[1]/div[2]/ul/li";
					String Add_Check = Function_Classes.field_gettext(driver, xpath);
					System.out.println(Add_Check);
					
					Thread.sleep(3500);
					if(Add_Check.contains("The address you entered is similar to an address in use by ")){
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_btnContinue']";
						Function_Classes.field_click(driver,xpath);	 
					}	
					
					
					
					
					
					// entering Phone number 
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_lnkListAddNew']";  // clik on edit phone no
					Function_Classes.field_click(driver,xpath);
							
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Address Type :  Phone type  home no
					Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect4.selectByIndex(2);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
					Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phoe no 
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_btnSubmit']";  //  click on save button
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_pnlInterface']/div[1]/div[2]/ul/li";
					String Phone_Check = Function_Classes.field_gettext(driver, xpath);
					System.out.println(Phone_Check);
					
					Thread.sleep(3500);
					if(Phone_Check.contains("The phone number you entered is already in use by Thomas Barrett, at another store.")){
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_btnContinue']";
						Function_Classes.field_click(driver,xpath);	 
					}	
					
							
					
					
							
					// entering Identification 
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_lnkListAddNew']";  // clik on edit button for identification
					Function_Classes.field_click(driver,xpath);
					
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
					Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
					Idnt.selectByIndex(1);
					
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
					Function_Classes.field_sendKeys(driver, xpath, "123456789");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
					Select state = new Select(driver.findElement(By.xpath(xpath)));
					state.selectByIndex(43);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtIssuedOnDate']";   // issue date
					Function_Classes.field_sendKeys(driver, xpath, "1/1/2010");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtExpirationDate']";   // expiry date
					Function_Classes.field_sendKeys(driver, xpath, "1/1/2020");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_btnSubmit']";  // Clicking on Save Button
					Function_Classes.field_click(driver,xpath);
					
								
						//Employer Details
						Thread.sleep(3000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_lnkListAddNew']";  // click on New Employer link
						Function_Classes.field_click(driver,xpath);
						
						Thread.sleep(3000);
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
						Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
						IncomeType.selectByIndex(1);
						Thread.sleep(3000);
						
						
						Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']", "ABC Company"); // Employer Name
						
						Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']", "Software"); // Occupation  Name
					
						Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']", "4000"); // Pay Amount
						
						Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']", "10/01/2015"); // Pay Amount
						
						Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
						PayFreq.selectByIndex(12);
						
						Thread.sleep(3000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_btnSubmit']";  // click on save 
						Function_Classes.field_click(driver,xpath);
						
						Thread.sleep(3000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_btnSubmit']";  // another save button 
						Function_Classes.field_click(driver,xpath);
								
				
					
					Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewMiscSale']"; // clicking on the Ms button
					Function_Classes.field_click(driver,xpath);
					
					
					Thread.sleep(vSleep+2000);
					//Select oSelect7 = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']")));   // selecting the Wire Trans Receive - WU
					//oSelect7.selectByIndex(22);	
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']", "Wire Trans Receive – WU");
					
					Thread.sleep(3000);
					Function_Classes.field_click(driver, ".//*[@id='Main']/div[1]");
					
					Thread.sleep(3500);
					xpath =  ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // entering Amount
					Function_Classes.field_click(driver,xpath);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(3000);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Function_Classes.field_sendKeys(driver,xpath , "3004");  // Enter Amount
					//driver.findElement(By.xpath(xpath)).sendKeys(Keys.TAB); 			
					
					Thread.sleep(vSleep+2000);			
					xpath =  ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  // entering Fee
					Function_Classes.field_click(driver,xpath);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(3000);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.DELETE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					driver.findElement(By.xpath(xpath)).sendKeys(Keys.BACK_SPACE);
					Function_Classes.field_sendKeys(driver,xpath, "2");  // enter Fees		
						
					
					Thread.sleep(vSleep+2000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']");
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']", "test");  // enter comments

					Thread.sleep(3000);				 
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on submit button
					
					Thread.sleep(3500);		
					//xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlConfirm']/div/div[1]";  // capturing the confirmation message
					String misc_assert_val = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlConfirm']/div/div[1]" );
					System.out.println(misc_assert_val);
					
					if (misc_assert_val.equals("Miscellaneous Sales Confirmation")) {
						xlData[1][14] = "Pass";
						
					    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					}
					else {
						xlData[1][14] = "Fail";
						
					    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					}
					
					   
					Thread.sleep(3000);	  
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']"; // cick on submit button
					Function_Classes.field_click(driver,xpath);
					
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
					 
					 Thread.sleep(3500);	 
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

					Thread.sleep(vSleep+3000);	
					System.out.println("WU_Receive_over_Threshold   --  Passed");
					
					Thread.sleep(3000);
					Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
				
					 Thread.sleep(2000);
					 driver.close();
					 
					
		}

}