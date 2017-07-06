package Ohio;
import Utility.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _9_10_Verify_Patial_Full_Loan_Amt {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,new_number;
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
	
	
	// To Open / reopen the Cash Drawer
	@Test (priority = 2)
	public  void  StroreLogin () throws Exception{
		
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("path"),"TestData");
		
		////xlData = Utils.readXL(path,"TestData");		
				
		//Utils.setExcelFile(xlPath,"Login");
		
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
		//String keys = "9998 Business date is always current date, TN";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(3000);
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
					  }       
					
									 
				}
				
			}
			
			*/
			
	/*		
			// New Customer Creation  - working fine
			@Test (priority = 4)
			public void New_Customer_Creation() throws Exception {
				//xlData = Utils.readXL(path,"TestData");		
				//Utils.setExcelFile(xlPath,"Login");
				System.out.println("New_Customer_Creation    --  Started");
				
				xlCustFName = xlData [1][4];
				xlCustLName = xlData [1][5];
				xlssn = xlData [1][6];
				xldob = xlData [1][7];
				
				xlCustFName = xlData [1][4];
				String[] arr = xlCustFName.split(" ");    

				 for(int i=0; i<arr.length;i++) {
				     System.out.println(arr[i]);
				}

				int a = Integer.parseInt(arr[1]);
				a = a+1;
				new_number = Integer.toString(a);
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
						
				String xpath=".//*[@id='FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + xldob);
				
				// Entering Address
				
				Thread.sleep(3000);
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
				
				Thread.sleep(3000);
				xpath = ".//*[@id='FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
				Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
				oSelect2.selectByIndex(1);
				
				xlRoutingno = xlData [1][8];
				xpath =".//*[@id='FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
				Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
				
				
				xlaccno = xlData [1][9];
				xpath =".//*[@id='FrmCustBankAccount_txtAccountNum']"; // entering Account Number
				Function_Classes.field_sendKeys(driver, xpath, new_number);
				
				xlbankname = xlData [1][10];
				xpath =".//*[@id='FrmCustBankAccount_txtBankName']"; // entering Bank Name
				Function_Classes.field_sendKeys(driver, xpath, xlbankname);
	
				Thread.sleep(3000);
				xpath = ".//*[@id='btnSubmit']";  // Clicking on Save Button
				Function_Classes.field_click(driver,xpath);
				
				
				Thread.sleep(3000);
				if (driver.findElements( By.xpath(".//*[@id='Block']/div[1]/div[2]/ul/li")).size() != 0){
					Thread.sleep(3500);
					//xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
					String Address_Check = Function_Classes.field_gettext(driver, xpath);
					System.out.println(Address_Check);
					
					//if(Address_Check.contains("The address you entered is similar to an address")){
						Address_Check.contains("The address you entered is similar to an address");
						xpath = ".//*[@id='btnContinue']";
						Function_Classes.field_click(driver,xpath);					
						//}
						}
				
				
				Thread.sleep(3500);		
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblFirstName']";	
				
				String xlrCustFName = Function_Classes.field_gettext(driver,xpath);
				System.out.println(xlrCustFName);
				//xlData[1][14] = Function_Classes.field_gettext(driver,xpath);
				System.out.println("yes");
				xlData[1][14] = xlrCustFName;
				//xlData[0][3] = "a";
				Thread.sleep(3000);
				System.out.println("yes1");
										
				 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
				
				 System.out.println("New_Customer_Creation    --  Passed");
				}
			
			
			*/
			
			
			
			////////////////
			
			@Test (priority = 5)
			public void Verify_Partial_full_Loan_payment () throws Exception{
				//xlData = Utils.readXL(path,"TestData");		
				xlCustFName = xlData [1][4];
				System.out.println( "Verify_Partial_full_Loan_payment   --  Started");
							
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
					Function_Classes.field_click(driver, xpath);
					
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value   Good64
					Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3500);
				
				/*//Employer Details
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_lnkListAddNew']";  // click on New Employer link
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3500);
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
				Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
				IncomeType.selectByIndex(1);
				Thread.sleep(3500);
				
				
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']", "ABC Company"); // Employer Name
				
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']", "Software"); // Occupation  Name
			
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']", "4000"); // Pay Amount
				
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']", "10/01/2015"); // Pay Amount
				
				Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
				PayFreq.selectByIndex(12);
				
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_btnSubmit']"); //Click on Save button
				*/
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				//xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // selecting loan Type  "TN PRA Type A"
				//Select Loan1 = new Select(driver.findElement(By.xpath(xpath)));
				//Loan1.selectByIndex(6);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']", "Consumer Loan");
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
				
		        Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  // entering the Loan Amount 
				String checkAmount = xlData [1][12];
				Function_Classes.field_sendKeys(driver, xpath, checkAmount);
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");  // Click on Next button		
				
			    Thread.sleep(3000);	           
	           Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
				
	           Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
				
				Thread.sleep(3500);
				
				
				//Function_Classes.isAlertPresent(driver);
				
				boolean presentFlag = false;
				try {

					   // Check the presence of alert
					   Alert alert = driver.switchTo().alert();
					   // Alert present; set the flag
					   presentFlag = true;
					   // if present consume the alert
					   alert.accept();

					  } catch (NoAlertPresentException ex) {
					   // Alert not present
					   ex.printStackTrace();
					  }
				
				
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
				
				Thread.sleep(3000);
				Function_Classes.Admin_Setup(driver);
				
				Thread.sleep(3000);
		         Function_Classes.field_link(driver, "7989 Automation Test Store, OH");// clicking on  Automation store
		         
		         Thread.sleep(2000);
			     String d = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']");
			     System.out.println(d);
 
		        Thread.sleep(2500);
		        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // clicking on  edit button
		        Function_Classes.field_click(driver, xpath);
		        
		        Thread.sleep(2000);
		        Function_Classes.field_clear(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']" );  // clear the field
		        
		        String new_date2 = Function_Classes.date_auto_change(3);  // changing date 
			       
			        Thread.sleep(3000);
			        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
			        Function_Classes.field_sendKeys(driver, xpath, new_date2 );
			        
			        Thread.sleep(3000);
			        xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			        Function_Classes.field_click(driver, xpath);

			        Thread.sleep(3000);        
			        Function_Classes.logout (driver, ".//*[@id='menuv2']/li[9]/a" ); // for log out 
				
			       
			    	Thread.sleep(3000);
					xpath=".//*[@id='txtUsername']";  // Clearing User Name field
					Function_Classes.field_clear(driver, xpath );
					
					Thread.sleep(3000);
					xpath=".//*[@id='txtUsername']";  // Passing User name 
					Function_Classes.field_sendKeys(driver, xpath, xlUsername);
					
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
					//String keys = "7989 Business date is always current date, OH";
					Function_Classes.field_sendKeys(driver, xpath, xlStore);

					Thread.sleep(2000);
					xpath = ".//*[@id='btnSelStore']"; // clicking ok 
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep+3000);	
					xpath =".//*[@id='Left']/a[5]";  // click on Customers button
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter the customer name
					Function_Classes.field_sendKeys(driver, xpath, xlData [1][4]);
					
					Thread.sleep(vSleep);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on search button
					Function_Classes.field_click (driver, xpath);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // clicking on the loan id 
					Function_Classes.field_click (driver, xpath);
					
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span");  // Click on Make Payment button 
				
			/*	Thread.sleep(3000);
				String pay_val= driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']")).getAttribute("value"); // read the payment amount
				System.out.println("Pay" + pay_val);*/
				
				Thread.sleep(3000);
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";  // Clearing Payment Amount field
				Function_Classes.field_clear(driver, xpath );
				
				
				Thread.sleep(2500);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']", "50");	// entering the Payment Amount
				
				Thread.sleep(2500);
				xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']"; 
				Function_Classes.field_clear(driver, xpath );
				Thread.sleep(2500);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']", "50");	// entering the amount
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // click on next button 
				
				Thread.sleep(3000);
				String Model = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLoanModel']" );
				System.out.println(Model);
				
				String Status = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']" );
				System.out.println(Status);
				
				String Principal = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwPrinBal']" );
				System.out.println(Principal);
				
				String Fees = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFeeBal']" );
				System.out.println(Fees);
				
				String Balance = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwBalance']" );
				System.out.println(Balance);
				
				String Originated_Date = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']" );
				System.out.println(Originated_Date);
				
				String Due_Date = Function_Classes.field_gettext(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']" );
				System.out.println(Due_Date);
				
				
				
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']");  // clkick Loan ID
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkVoid']/span");  // Click on Void a Transaction button 
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgDataList_ctl05_btnEdit']");  // Click on date link button 
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "test");  // enter Reason
				
				Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // Click on Void Entire Loan button 				
				
				
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
				
				Thread.sleep(3000);
				Function_Classes.Admin_Setup(driver);
				
				Thread.sleep(4000);
		         Function_Classes.field_link(driver, "7989 Automation Test Store, OH");// clicking on  Automation store
 
		        Thread.sleep(2500);
		        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // clicking on  edit button
		        Function_Classes.field_click(driver, xpath);
		        
		        Thread.sleep(3000);
		        Function_Classes.field_clear(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']" );  // clear the field
		        
		       // String new_date3 = Function_Classes.date_auto_change(0);  // changing date 
			       
			        /*Thread.sleep(3500);
			        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
			        Function_Classes.field_sendKeys(driver, xpath, new_date3 );*/
			        
			        
			        Thread.sleep(3500);
			        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
			        Function_Classes.field_sendKeys(driver, xpath, d );
			        
			        Thread.sleep(3000);
			        xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			        Function_Classes.field_click(driver, xpath);

			        Thread.sleep(3000);        
			        Function_Classes.logout (driver, ".//*[@id='menuv2']/li[9]/a" ); // for log out 
			        
					 Thread.sleep(2000);
					 driver.close();	
				
					 System.out.println( "Verify_Partial_full_Loan_payment   --  Passed");
					 System.out.println("_9_10_Verify_Patial_Full_Loan_Amt   --  Passed");

			}
				
				
		}
			
				
			
			