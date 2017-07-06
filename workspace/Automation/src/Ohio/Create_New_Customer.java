package Ohio;

import Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import Functions.Function_Classes;

public class Create_New_Customer {
	
	
	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	String path = "C:/1/Seleniumproject/TestData_2007.xls";
	String wpath = "C:/1/Seleniumproject/TestResult_2007.xls";
	
	@BeforeTest	
	public void Url_search (){		
		
		driver = new FirefoxDriver();			 
	driver.manage().window().maximize();		
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);			
}
	
	
	@Test (priority = 1)
	public void  StroreLogin () throws Exception{
		xlData = Utils.readXL(path,"TestData");	
				
		//Utils.setExcelFile(xlPath,"Login");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 1000;

		
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
		//String keys = "9998 Business date is always current date, TN";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+2000);			

	}
	
	
/*	// To Open / reopen the Cash Drawer
			@Test (priority = 2)
			public void Open_Drawer() throws Exception {
				xlData = Utils.readXL(path,"TestData");	
				
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
				    
					
					Thread.sleep(3000);
					Function_Classes.field_click(driver, Drawer_status);  // clicking on close 
	                
					Thread.sleep(3000);
					Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	
					Thread.sleep(3000);
					
					
					 try   
					  {    
						 if (driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblConfirmation']")).isDisplayed())   
					    {      
							 System.out.println("Finish Button visible");
								
								String Finish_button = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']";  // clicking on Finish button	
								Function_Classes.field_click(driver, Finish_button);  // cliking on finish button
								Thread.sleep(2000);
								
								Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		
								System.out.println("Opening again");
								
								Thread.sleep(2000);
								Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	  
								System.out.println("Its now in Close status");	
								Thread.sleep(2000);	    
					    }    
					  }      
					  catch(Exception e)     
					  {       
						  System.out.println("Finish Button NOT visible");
							Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		 
							System.out.println("Opening again");
							
							Thread.sleep(2000);
							Function_Classes.field_click(driver, Close_Submit);  // clicking on submit
							System.out.println("Its now in Close status");	
							Thread.sleep(2000);   
					  }       
					
									 
				}
				
			}
			*/
			
			
			
			// New Customer Creation  - working fine
			@Test (priority = 3)
			public void newcustomer() throws Exception {
				xlData = Utils.readXL(path,"TestData");	
				//Utils.setExcelFile(xlPath,"Login");
				
				xlCustFName = xlData [1][4];
				xlCustLName = xlData [1][5];
				xlssn = xlData [1][6];
				xldob = xlData [1][7];
				
				System.out.println(xlCustFName+ xlCustLName + xlssn + xldob );
				// Entering General Information			
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				Function_Classes.field_click(driver, xpath);
				
				xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
				Function_Classes.field_click(driver, xpath);
				
				xpath=".//*[@id='FrmCustGeneral_txtFirstName']";  // First Name
				Function_Classes.field_sendKeys(driver, xpath, xlCustFName);
				
				xpath=".//*[@id='FrmCustGeneral_txtLastName']";  // Last Name
				Function_Classes.field_sendKeys(driver, xpath, xlCustLName);
					   
				xpath=".//*[@id='FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xlssn);
						
				String xpath=".//*[@id='FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xldob);
				
				// Entering Address
				
				Thread.sleep(2000);
				xpath = ".//*[@id='FrmCustAddress_cboType']";  // Select Address Type :  Home
				Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
				oSelect.selectByIndex(2);
				
				xpath =".//*[@id='FrmCustAddress_txtAddrLine1']"; // entering Address
				Function_Classes.field_sendKeys(driver, xpath, "124 Main St");
				
				xpath =".//*[@id='FrmCustAddress_txtCity']"; // entering City
				Function_Classes.field_sendKeys(driver, xpath, "Columbus");
				
				xpath = ".//*[@id='FrmCustAddress_cboState']";  // Select State :  Ohio
				Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
				oSelect1.selectByIndex(43);
				
				xpath =".//*[@id='FrmCustAddress_txtZip']"; // entering zip code
				Function_Classes.field_sendKeys(driver, xpath, "43201");
							
				//Entering Bank Details
				
				Thread.sleep(2000);
				xpath = ".//*[@id='FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
				Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
				oSelect2.selectByIndex(1);
				
				xlRoutingno = xlData [1][8];
				xpath =".//*[@id='FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
				Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
				
				
				xlaccno = xlData [1][9];
				xpath =".//*[@id='FrmCustBankAccount_txtAccountNum']"; // entering Account Number
				Function_Classes.field_sendKeys(driver, xpath, xlaccno);
				
				xlbankname = xlData [1][10];
				xpath =".//*[@id='FrmCustBankAccount_txtBankName']"; // entering Bank Name
				Function_Classes.field_sendKeys(driver, xpath, xlbankname);
	
				Thread.sleep(2000);
				xpath = ".//*[@id='btnSubmit']";  // Clicking on Save Button
				Function_Classes.field_click(driver,xpath);
				
				
				xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
				String Address_Check = Function_Classes.field_gettext(driver, xpath);
				System.out.println(Address_Check);
				
				if(Address_Check.contains("The address you entered is similar to an address")){
					xpath = ".//*[@id='btnContinue']";
					Function_Classes.field_click(driver,xpath);					
				}
			
				
				Thread.sleep(3000);		
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblFirstName']";	
				
				String xlrCustFName = Function_Classes.field_gettext(driver,xpath);
				System.out.println(xlrCustFName);
				//xlData[1][14] = Function_Classes.field_gettext(driver,xpath);
				System.out.println("yes");
				xlData[1][14] = xlrCustFName;
				//xlData[0][3] = "a";
				Thread.sleep(2000);
				System.out.println("yes1");
										
				 Utils.writeXL(wpath,"Login",xlData);
				
				}
			
				
/*			
			@AfterTest
			public void aftertest2() throws InterruptedException{
			String logvalue = "Log Out";
			//String xpath; 
			//int vSleep = 1000;
					
			xpath = ".//*[@id='menuv2']/li[7]/a";  // logout script		
			Function_Classes.logout (driver, xpath, vSleep, logvalue);
		}	*/
	
	

}



