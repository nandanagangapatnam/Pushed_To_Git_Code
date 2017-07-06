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

public class _20_Verify_check_over_10000_Threshold {
	

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
	
	// To Open / reopen the Cash Drawer
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
					 System.out.println("Drawer Amount       " +drawerinitialamt);
					 xlData[1][15] = drawerinitialamt;
					 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					 Thread.sleep(3000);
			  }       
			
							 
		}
		
	}
	
	
	 // MO for more than $3500
		@Test (priority = 4)
		public void Check_creation() throws Exception {
			//xlData = Utils.readXL(path,"TestData");	
			 System.out.println("Check_creation  --  Started");
		 
			 // creating customer 
			    /*xlCustFName = xlData [1][4];
				xlCustLName = xlData [1][5];
				xlssn = xlData [1][6];
				xldob = xlData [1][7];
				xlaccno = xlData [1][9];*/
			 
				xlCustFName = xlData [1][4];
				String[] arr = xlCustFName.split(" ");    

				 for(int i=0; i<arr.length;i++) {
				     System.out.println(arr[i]);
				}

				int a = Integer.parseInt(arr[1]);
				a = a+3;
				String new_number = Integer.toString(a);
				System.out.println("new number is "  + new_number);
				xlCustFName= arr[0]+" " +new_number;
				System.out.println(xlCustFName);
				
				Thread.sleep(2000);		
				xlCustLName = xlData [1][5];
				//xlssn = xlData [1][6];
				xlssn = new_number+new_number+new_number;
				System.out.println("new ssn is " + xlssn);
	
				xldob = xlData [1][7];
				Thread.sleep(2000);
				System.out.println(xlCustFName+ xlCustLName + xlssn + xldob );
				// Entering General Information			
				Thread.sleep(2000);
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				Function_Classes.field_click(driver, xpath);
				Thread.sleep(2000);
				xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
				Function_Classes.field_click(driver, xpath);
				Thread.sleep(2000);
				xpath=".//*[@id='FrmCustGeneral_txtFirstName']";  // First Name
				Function_Classes.field_sendKeys(driver, xpath, xlCustFName);
				Thread.sleep(2000);
				xpath=".//*[@id='FrmCustGeneral_txtLastName']";  // Last Name
				Function_Classes.field_sendKeys(driver, xpath, xlCustLName);
				Thread.sleep(2000);	   
				xpath=".//*[@id='FrmCustGeneral_txtSsn']";  // Passing Password  SSN/ITIN is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xlssn);
				Thread.sleep(2000);		
				String xpath=".//*[@id='FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xldob);
			 
			 
				Thread.sleep(3000);
				xpath = ".//*[@id='btnSubmit']";  // Clicking on Save Button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']";  // clicking on $  symbol
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewCheck']";  // clicking on New Check  symbol
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='txtMakerName']";  // Enter the Maker Name 
				Function_Classes.field_sendKeys(driver, xpath, "Auto Test");
				
				Thread.sleep(3000);
				xpath = ".//*[@id='btnSubmit']";  // clicking Search button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(vSleep+2000);
				Select Check_Type = new Select(driver.findElement(By.xpath(".//*[@id='cboCheckType']")));   // selecting the Check Type	
				Check_Type.selectByIndex(8);
				
				String check = xlData [1][12];
				Thread.sleep(3000);
				xpath = ".//*[@id='txtCheckNum']";  // Enter the Maker Name 
				Function_Classes.field_sendKeys(driver, xpath, check+3);
				
				Thread.sleep(2000);
				 String new_date = Function_Classes.date_auto_change(-1);  // changing date 
				 System.out.println(new_date);
				 
				Thread.sleep(3000);
				xpath = ".//*[@id='CheckDate_txtDate']";  // Enter the date 
				Function_Classes.field_sendKeys(driver, xpath, new_date);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='txtAmount']";  // Enter the Amount 
				Function_Classes.field_sendKeys(driver, xpath, "12000");
				
				Thread.sleep(3000);
				xpath = ".//*[@id='btnStep1Submit']";  // clcik  the Next button 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='pnlUpdateArea']/div[1]/div[2]";
				String max_check = Function_Classes.field_gettext(driver, xpath);
				
				if (max_check.contains("Please correct the following errors before continuing:")) {
					xlData[1][14] = "Pass";
					
				    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
				}
				else {
					xlData[1][14] = "Fail";
					
				    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
				}
				
				Thread.sleep(3000);
				xpath = ".//*[@id='CustomerInfo_lnkName']";  // clicking on Customer name 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkEditCustomer']";  // click on Edit customer link 
				Function_Classes.field_click(driver, xpath);
						
				// Entering Address
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_lnkListAddNew']";  // clik on Edit Address link 
				Function_Classes.field_click(driver, xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboType']";  // Select Address Type :  Home
				Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
				oSelect1.selectByIndex(2);
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtAddrLine1']"; // entering Address
				Function_Classes.field_sendKeys(driver, xpath, "124 Main St");
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtCity']"; // entering City
				Function_Classes.field_sendKeys(driver, xpath, "Columbus");
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State :  Ohio
				Select oSelect3 = new Select(driver.findElement(By.xpath(xpath)));
				oSelect3.selectByIndex(43);
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
				Function_Classes.field_sendKeys(driver, xpath, "43201");
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_btnSave']";  //  click on save button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_pnlInterface']/div[1]/div[2]/ul/li";
				String Add_Check = Function_Classes.field_gettext(driver, xpath);
				System.out.println(Add_Check);
				
				Thread.sleep(2000);
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
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_pnlInterface']/div[1]/div[2]/ul/li";
				String Phone_Check = Function_Classes.field_gettext(driver, xpath);
				System.out.println(Phone_Check);
				
				Thread.sleep(2000);
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
					
					Thread.sleep(2000);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']", "ABC Company"); // Employer Name
					
					Thread.sleep(2000);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']", "Software"); // Occupation  Name
				
					Thread.sleep(2000);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']", "4000"); // Pay Amount
					
					Thread.sleep(2000);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']", "7/01/2016"); // Pay Amount
					
					Thread.sleep(2000);
					Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
					PayFreq.selectByIndex(12);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_btnSubmit']";  // click on save 
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_btnSubmit']";  // another save button 
					Function_Classes.field_click(driver,xpath);
							
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']";  // clicking on $  symbol
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewCheck']";  // clicking on New Check  symbol
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='txtMakerName']";  // Enter the Maker Name 
					Function_Classes.field_sendKeys(driver, xpath, "Auto Test");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='btnSubmit']";  // clicking Search button
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(vSleep+2000);
					Select Check_Type1 = new Select(driver.findElement(By.xpath(".//*[@id='cboCheckType']")));   // selecting the Check Type	
					Check_Type1.selectByIndex(4);
					
					//check = xlData [1][12];
					Thread.sleep(3000);
					xpath = ".//*[@id='txtCheckNum']";  // Enter the Maker Name 
					Function_Classes.field_sendKeys(driver, xpath, xlssn + 1);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='CheckDate_txtDate']";  // Enter the date 
					Function_Classes.field_sendKeys(driver, xpath, "7/01/2016");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='txtAmount']";  // Enter the Amount 
					Function_Classes.field_sendKeys(driver, xpath, "12000");
					
					Thread.sleep(3000);
					xpath = ".//*[@id='btnStep1Submit']";  // clcik  the Next button 
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='btnStep2Submit']";  // clcik  the Submit button 
					Function_Classes.field_click(driver,xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='lblPayoutMsg']";   //  checking for the confirmtion
					String check_cashing = Function_Classes.field_gettext(driver, xpath);
					System.out.println(check_cashing);
					
	
					if (check_cashing.contains("Amount to Customer:")) {
						xlData[1][14] = "Pass";
						
					    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					}
					else {
						xlData[1][14] = "Fail";
						
					    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
					}
					
					
					Thread.sleep(3000);
					xpath = ".//*[@id='btnContinue']";  // clcik  the continue button 
					Function_Classes.field_click(driver,xpath);
					
					System.out.println("Check_creation  --  Passed");
		}
	
		
		

		 // MO for more than $3500
		@Test (priority = 5)
		public void Check_Void() throws Exception {
			//xlData = Utils.readXL(path,"TestData");		
			
			System.out.println("Check_Void  --  Started");
			Thread.sleep(3000);
			xpath = ".//*[@id='dgOutstanding']/tbody/tr[2]/td[1]/a";  // clcik  the Outstanding check link  
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='lnkDelete']";  // click  the Void Entire Check link  
			Function_Classes.field_click(driver,xpath);
			
			Thread.sleep(3000);
			xpath = ".//*[@id='txtDelReason']";  // Enter the Reason   
			Function_Classes.field_sendKeys(driver, xpath, "test");
			
			Thread.sleep(3000);
			xpath = ".//*[@id='btnDelSubmit']";  // clcik  Submit button 
			Function_Classes.field_click(driver,xpath);
			
						
			Thread.sleep(2000);
			xpath = ".//*[@id='dgPrevious']/tbody/tr[2]/td[6]";   //  checking for the confirmation
			String check_void = Function_Classes.field_gettext(driver, xpath);
			System.out.println(check_void);
			

			if (check_void.contains("Deleted")) {
				xlData[1][14] = "Pass";
				
			    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			}
			else {
				xlData[1][14] = "Fail";
				
			    Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
			}
			System.out.println("Check_Void  --  Passed");
			
					 
			 Thread.sleep(2500);	
			 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			 
			 Thread.sleep(2000);
			 driver.close();	
			 System.out.println("_20_Verify_check_over_10000_Threshold   --  passed");
		}
				
		
}
