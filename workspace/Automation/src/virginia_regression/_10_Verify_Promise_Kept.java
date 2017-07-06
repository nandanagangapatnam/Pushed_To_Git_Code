/*
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression;

import Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import Functions.Function_Classes;


public class _10_Verify_Promise_Kept {
	
	WebDriver driver,driver1;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1 = "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=167";
	String Username, Password, LoggedName;
	String loanlink,Status,OriDate,FPDueDate,BusDate,Late;
	String xlCdtlimit , xlDraw;
	String xpath,xpath1,EarnedPrincoutput;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustFName, xlCustLName,xlssn,xlRoutingno,xlaccno,xlbank,xlOrigDate;
	String	ActualRPDueDate,celltext,name;
	String[][] xlData;
	String[][] xlResults;
	public static Properties OR=null;

	
	@Test (priority = 1)
	public void _1_navigate_to_eCashURL (){		
		System.out.println("Ecash URL  --  Started");
		
	
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver, URL);	


}
	
	@Test (priority =2)
	public void  _2_teller_login () throws Exception {

			System.out.println("Ecash Teller Login   --  Started");
			
			OR = new Properties();
			FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
			OR.load(fn);
		

			xlData = Utils.readXL(OR.getProperty("vpath"),"VA_LOC_credit");
			
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
				
			Thread.sleep(1000);
			xpath=".//*[@id='txtUsername']";  // Clearing User Name field
			Function_Classes.field_clear(driver, xpath );
		
			Thread.sleep(1000);
			xpath=".//*[@id='txtUsername']";  // Passing User name 
			Function_Classes.field_sendKeys(driver, xpath,xlUsername);
		
			Thread.sleep(1000);
			xpath=".//*[@id='txtPasswd']";  // Clearing Password field
			Function_Classes.field_clear(driver, xpath );
		
			Thread.sleep(1000);
			xpath=".//*[@id='txtPasswd']";  // Passing Password
			Function_Classes.field_sendKeys(driver, xpath,xlPassword);
		
			Thread.sleep(1000);
			xpath = ".//*[@id='btnSubmit']"; // Click on go button
			Function_Classes.field_click(driver, xpath);
		
			Thread.sleep(1000);
			xpath=".//*[@id='ddlStore']";  // selecting the state 
			Function_Classes.field_sendKeys(driver, xpath, xlStore);

			Thread.sleep(1000);
			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			Function_Classes.field_click (driver, xpath);
		}
	
	
	
	@Test (priority = 3)
	public void  _3_businessdatechange  () throws Exception{
		

		
		System.setProperty("webdriver.chrome.driver", "C:\\Automation QA\\chromedriver.exe");
		
		driver1 = new ChromeDriver(); //	new chrome driver

		driver1.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver1, URL1);	

		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver1, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver1, xpath, "dateadm");
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver1, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver1, xpath, "changeme");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver1, xpath);
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

    	xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
       	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
       	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/29/2017");
      
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

    	xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";// Current Date
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

       
	}
	
	// New LOCs Creation  - working fine
	@Test (priority = 4)
	public void _4_LOC_Creation() throws Exception {

		xlResults = Utils.readXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC");	
			
		System.out.println("Ecash New Customer Creation   --  Started");
		

		xlCustFName = xlData [36][4];
		//System.out.println(xlCustFName);// Printing Customer First Name
		
		xlCustLName = xlData [36][5];
		//System.out.println(xlCustLName);// Printing Customer Last Name
		
		xlssn = xlData [36][6];
		//System.out.println(xlssn); // Printing Customer SSN
		
		//  Entering General Information			
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtFirstName']";  // First Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Function_Classes.field_sendKeys(driver, xpath, xlCustFName);

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtLastName']";  // Last Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustLName);
   
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlssn);

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/01/1970");

	// Entering Address
		
		Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboType']";  // Select Address Type :  Home
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByIndex(2);
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtAddrLine1']"; // entering Address
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("124 Main St");

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtCity']"; // entering City
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Florida");
	
//		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State 
		Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect1.selectByIndex(11);
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("37343");

		
	// entering Phone number 

		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Phone Type 
		Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect4.selectByIndex(2);
		
		Thread.sleep(2000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
		Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phone no 
	
		
	//Entering Bank Details
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);	
		
	
		Thread.sleep(2000);		
		xlRoutingno = xlData [36][8];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
	 	Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
		
		xlaccno = xlData [36][9];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);
		
		xlbank = xlData [36][10];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbank);
	
	//  entering Identification 

		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
		Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
		Idnt.selectByIndex(1);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("123456789");
		
//		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
		Select state = new Select(driver.findElement(By.xpath(xpath)));
		state.selectByIndex(11);
		
//		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtExpirationDate']"; //Selecting Expiration date
		Function_Classes.field_sendKeys(driver, xpath, "12/12/2020");
				
		
	//	Employer Details
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
		Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
		IncomeType.selectByIndex(1);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']";  // employer
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("ABC Company");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']";  // Occupation
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Software");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']";  // JobPayAmt
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("10000");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']";  // Pay Date
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("03/15/2017"); // Pay Amount
		
//		Thread.sleep(1000);
		Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
		PayFreq.selectByIndex(3);
		
		Thread.sleep(1000);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Clicking on Save Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
		String Address_Check=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
		if(Address_Check.contains("The address you entered is similar to an address")){
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		}
			
		System.out.println("Customer created successfully");
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3000");
	
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
		Function_Classes.field_clear(driver, xpath);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount
		Function_Classes.field_sendKeys(driver, xpath,"1000");
				
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  //loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		System.out.println(loanlink);
		xlResults[1][1] = loanlink;
		
	
		System.out.println("LOCs created successfully   --  Passed");
}
	
	@Test (priority = 5)
	public void  _5_businssdate_rolledforward_beyond_duedate()throws Exception{
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
	   	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
	   	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "05/01/2017");
	  
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";// Current Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
	   
	}

	@Test (priority = 6)
	public void  _6_homescreen_before_appointment_creation()throws Exception{

		name =xlCustLName+", "+xlCustFName;
		System.out.println(name);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click ();
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']"; // Click on Customer left navigation button
		String Late = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][2] = Late;
		
		if (Late .contains("Late")){
			
			xpath =".//*[@id=('Left')]/a[1]";  // HOME
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			xpath =".//*[@id='ctl00_MainContent_TabNav_lnkOverview']";  //Store Overview
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
		
			WebElement table1 = driver.findElement(By.id("ctl00_MainContent_pnlActionItems")); 
			
			Thread.sleep(1000);
			
			// Now get all the TR elements from the table 
			List<WebElement> Rows1 = table1.findElements(By.tagName("tr")); 
			
			int rows_count = Rows1.size();
			outerloop:	
			// And iterate over them, getting the cells 
			 for(int i=0; i<rows_count;i++)
			 {
			  List<WebElement> Cols1 = Rows1.get(i).findElements(By.tagName("td"));
			  int columns_count = Cols1.size();
			  
			  for(int j=0; j < columns_count;j++)
			  {

				  if(Cols1.get(j).getText() .equals(name))
				  	{
			   
		 				celltext = Cols1.get(j).getText();
						System.out.println("Cell Value Of row number " + i+ " and column number " + j + " Is " + celltext);
						System.out.println(celltext);
						break outerloop;
						
				  	}
				  else {
				   
					  	celltext = "Not Present";
					  	System.out.println(celltext);
											
				  	}
				   
				  
			  	} 
			 }
			
			System.out.println(celltext);
			
			Thread.sleep(2000);
			xpath =".//*[@id='ctl00_MainContent_gvAppts_ctl02_lnkEdit00']";  // Appointment
			
		
			if ( driver.findElements(By.xpath(xpath)).size() == 0 ){//&& celltext .equals(name)){
				
				System.out.println("PASS1");
				xlResults[1][3] ="APPT is coming under ActionItems and not Appointments on Home Screen before appt creation - PASSED";
				Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
			else{
			
				System.out.println("FAIL1");
				xlResults[1][3] ="APPT is not coming under ActionItems or coming under Appointments on Home Screen before appt creation - FAILED";
				Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}

			Assert.assertEquals(0, driver.findElements(By.xpath(xpath)).size());
//			Assert.assertEquals(celltext, name);
			
		  }
		
		else{
			
			System.out.println("Customer is not Late");
			xlResults[1][3] = "Customer is Not Late - Check data";
			Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
		}
	}	

	@Test (priority = 7)
	public void  _7_appointment_creation()throws Exception{
		
		xpath =".//*[@id=('Left')]/a[5]";  // Customers
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']";  // Search
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlResults[1][1]);;


		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // Search
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkAppointments']"; // Click on Appointments
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click ();
	
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_lnkListAddNew']";  // Add Appointment
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_txtNotes']";  // Select Address Type :  Home
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("TEST-CUSTOMER WILL COME");
			
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_ddlSubject']";  // Select Address Type :  Home
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByVisibleText("PTP");
			
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_ddlReference']";  // Select Address Type :  Home
		Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect1.selectByIndex(1);
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_txtApptDate']";  // Date
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("05/05/2017");
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_txtApptTime']";  // Time
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("09:00");
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_ddlApptTimeOfDay']";  // Time of Day
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByVisibleText("AM");
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_btnSubmit']";  // Save
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
	
		Thread.sleep(1500);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList_ctl02_btnEdit']";  //PTP link
	
			
		if ( driver.findElements(By.xpath(xpath)).size() !=0){
				
			System.out.println("Appointment Created successfully");
				
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList_ctl02_lblAppt']";  //Appointment Date
			String Date= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList']/tbody/tr[2]/td[6]";  // PromiseKept
			String PromiseKept = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList']/tbody/tr[2]/td[9]";  //Note
			String Note = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
				
			if (Date.equals("05/05/2017 9:00 AM") && PromiseKept .equals("N/A") && Note .equals("TEST-CUSTOMER WILL COME")){
				
					System.out.println("PASS");
					xlResults[1][4] = Date;
					xlResults[1][6] = PromiseKept;
					xlResults[1][5] = Note;	
	//				xlResults[1][6] = "YES -PASS";
					Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);

			}
			else{
					
					System.out.println("FAIL");
					xlResults[1][4] = Date;
					xlResults[1][6] = PromiseKept;
					xlResults[1][5] = Note;	
	//				xlResults[1][6] = "NO -FAIL";	
					Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
				
				Assert.assertEquals(Date, "05/05/2017 9:00 AM");
				Assert.assertEquals(PromiseKept, "N/A");
				Assert.assertEquals(Note, "TEST-CUSTOMER WILL COME");
		
		}
		
		
		else{
				
			System.out.println("Appointment Not created successfully");
			xlResults[2][4] = "Appointment Not created successfully" ;
			xlResults[2][5] = "Appointment Not created successfully" ;
			xlResults[2][6] = "Appointment Not created successfully" ;
			Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
		}
		
	}
			

	@Test (priority = 8)
	public void  _8_homeScreen_on_appointment_creationdt()throws Exception{
		
		if (xlResults[1][6] .equals("N/A")){
		
			//Home Screen
			
			xpath =".//*[@id=('Left')]/a[1]";  // HOME
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			xpath =".//*[@id='ctl00_MainContent_TabNav_lnkOverview']";  //Store Overview
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
			
			WebElement table1 = driver.findElement(By.id("ctl00_MainContent_pnlActionItems")); 
			
			// Now get all the TR elements from the table 
			List<WebElement> Rows1 = table1.findElements(By.tagName("tr")); 
			
			int rows_count = Rows1.size();
			outerloop:	
			// And iterate over them, getting the cells 
			 for(int i=0; i<rows_count;i++)
			 {
			  List<WebElement> Cols1 = Rows1.get(i).findElements(By.tagName("td"));
			  int columns_count = Cols1.size();
			  
			  for(int j=0; j < columns_count;j++)
			  {
	
				  if(Cols1.get(j).getText() .equals(name))
				  	{
			   
		 				celltext = Cols1.get(j).getText();
						System.out.println("Cell Value Of row number " + i+ " and column number " + j + " Is " + celltext);
						break outerloop;
						
				  	}
				  else {
				   
					  	celltext = "Not Present";
							
				  	}
				   
				  
			  	} 
			 }
			System.out.println(celltext);
			
			Thread.sleep(2000);
			xpath =".//*[@id='ctl00_MainContent_gvAppts_ctl02_lnkEdit00']";  // Appointment
			
			if ( driver.findElements(By.xpath(xpath)).size() == 0 ){//&& celltext .equals("Not Present")){
				
				System.out.println("PASS1");
				xlResults[1][7] ="APPT is not coming under ActionItems and Appointments on Home Screen - PASSED";
				Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
			else{
			
				System.out.println("FAIL1");
				xlResults[1][7] ="APPT is coming under ActionItems or Appointments on Home Screen - FAILED";
				Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
	
			Assert.assertEquals(0, driver.findElements(By.xpath(xpath)).size());
//			Assert.assertEquals(celltext, "Not Present");

		}
	else{
			
		System.out.println("Check the DATA");
		xlResults[1][7]="Check the DATA for Intial Promise Kept";
		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
		
	}
		
		Assert.assertEquals(xlResults[1][6], "N/A");
}			
		
	@Test (priority = 9)
	public void  _9_homeScreen_3days_priortoappointment_creationdt()throws Exception{
		
		if (xlResults[1][6] .equals("N/A")){
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		   	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		   	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "05/02/2017");
				  
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";// Current Date
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					
			xpath =".//*[@id=('Left')]/a[1]";  // Save
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
			xpath =".//*[@id=('ctl00_MainContent_TabNav_lnkOverview')]";  // Shome screen
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			
			WebElement table1 = driver.findElement(By.id("ctl00_MainContent_pnlActionItems")); 
			
			// Now get all the TR elements from the table 
			List<WebElement> Rows1 = table1.findElements(By.tagName("tr")); 
			
			int rows_count = Rows1.size();
			outerloop:	
			// And iterate over them, getting the cells 
			 for(int i=0; i<rows_count;i++)
			 {
			  List<WebElement> Cols1 = Rows1.get(i).findElements(By.tagName("td"));
			  int columns_count = Cols1.size();
			  
			  for(int j=0; j < columns_count;j++)
			  {

				  if(Cols1.get(j).getText() .equals(name))
				  	{
			   
		 				celltext = Cols1.get(j).getText();
						System.out.println("Cell Value Of row number " + i+ " and column number " + j + " Is " + celltext);
						break outerloop;
						
				  	}
				  else {
				   
					  	celltext = "Not Present";
							
				  	}
				
				  
			  	} 
			 }
			   System.out.println(celltext);
			   Thread.sleep(2000);
			xpath =".//*[@id='ctl00_MainContent_gvAppts_ctl02_lnkEdit00']";  // Appointment
				//	WebElement PTP1 = driver.findElement(By.xpath(xpath));
			
			
			if ( driver.findElements(By.xpath(xpath)).size() == 0 ){//&& celltext .equals("Not Present")){
						
				System.out.println("PASS1");
				xlResults[1][8] ="APPT is not coming under Action Items and Appointments on Home Screen - PASSED";
			
			}
			
			else{
					
				System.out.println("FAIL1");
				xlResults[1][8] ="APPT is coming under Action Items/Appointments on Home Screen - FAILED";
				
			
			}
			Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			Assert.assertEquals(0, driver.findElements(By.xpath(xpath)).size());
//			Assert.assertEquals(celltext, "Not Present");
		}			
	
		else{
				
		System.out.println("Check the DATA");
		xlResults[1][8]="Check the DATA for Intial Promise Kept";
		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
		
	}
		Assert.assertEquals(xlResults[1][6], "N/A");
}

	@Test (priority = 10)
	public void  _10_homescreen_2days_priortoapptdt()throws Exception{

		if (xlResults[1][6] .equals("N/A")){
		
			System.out.println(name);
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "05/03/2017");
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";// Current Date
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
			xpath =".//*[@id=('Left')]/a[5]";  // Customers
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']";  // Search
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
			xpath =".//*[@id=('Left')]/a[1]";  // HOME
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			xpath =".//*[@id='ctl00_MainContent_TabNav_lnkOverview']";  //Store Overview
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
		
			WebElement table1 = driver.findElement(By.id("ctl00_MainContent_pnlActionItems")); 
			
			// Now get all the TR elements from the table 
			List<WebElement> Rows1 = table1.findElements(By.tagName("tr")); 
			
			int rows_count = Rows1.size();
			outerloop:	
			// And iterate over them, getting the cells 
			 for(int i=0; i<rows_count;i++)
			 {
			  List<WebElement> Cols1 = Rows1.get(i).findElements(By.tagName("td"));
			  int columns_count = Cols1.size();
			  
			  for(int j=0; j < columns_count;j++)
			  {
	
				  if(Cols1.get(j).getText() .equals(name))
				  	{
			   
		 				celltext = Cols1.get(j).getText();
						System.out.println("Cell Value Of row number " + i+ " and column number " + j + " Is " + celltext);
						System.out.println(celltext);
						break outerloop;
				  	}
				  else {
				   
					  	celltext = "Not Present";
					  	System.out.println(celltext);
				  }
				  
			  	} 
			 }
			
			System.out.println(celltext);
			Thread.sleep(2000);
			xpath =".//*[@id='ctl00_MainContent_gvAppts_ctl02_lnkEdit00']";  // Save
			
			if ( driver.findElements(By.xpath(xpath)).size() !=0 ){//&& celltext .equals("Not Present")){
					
				System.out.println("PASS2");
				xlResults[1][9] ="APPT is coming under Appointments and not under ActionItems on Home Screen - PASSED";
			
		
			}
			else{
				
				System.out.println("FAIL2");
				xlResults[1][9] ="APPT is not coming under Appointments/coming under ActionItems on Home Screen - FAILED";
				
	
			}
		
			Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			Assert.assertEquals(1, driver.findElements(By.xpath(xpath)).size());
//			Assert.assertEquals(celltext, "Not Present");
	
		}
	else{	
		
		System.out.println("Check the DATA for Initial Promise Kept");
		xlResults[1][9]="Check the DATA for Initial Promise Kept";
		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
	}
		Assert.assertEquals(xlResults[1][6], "N/A");
		
}
		
	

@Test (priority = 11)
public void  _11_homescreen_on_apptdtbeforepayment()throws Exception{

	if (xlResults[1][6] .equals("N/A")){
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "05/05/2017");
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";// Current Date
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
		xpath =".//*[@id=('Left')]/a[5]";  // Customers
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']";  // Search
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
		xpath =".//*[@id=('Left')]/a[1]";  // Save
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		xpath =".//*[@id='ctl00_MainContent_TabNav_lnkOverview']";  // Save
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
		WebElement table = driver.findElement(By.id("ctl00_MainContent_pnlActionItems")); 
		
		// Now get all the TR elements from the table 
		List<WebElement> Rows = table.findElements(By.tagName("tr")); 
		
		int rows_count = Rows.size();
		outerloop:	
		// And iterate over them, getting the cells 
		 for(int i=0; i<rows_count;i++)
		 {
		  List<WebElement> Cols = Rows.get(i).findElements(By.tagName("td"));
		  int columns_count = Cols.size();
		  
		  for(int j=0; j < columns_count;j++)
		  {

			  if(Cols.get(j).getText() .equals(name))
			  	{
		   
	 				celltext = Cols.get(j).getText();
					System.out.println("Cell Value Of row number " + i+ " and column number " + j + " Is " + celltext);
					System.out.println(celltext);
					break outerloop;
			  	}
			  else {
			   
				  	celltext = "Not Present";
					 
			  }
			} 
		 }
		
		System.out.println(celltext);
		Thread.sleep(2000);
		xpath =".//*[@id='ctl00_MainContent_gvAppts_ctl02_lnkEdit00']";  
		
		if ( driver.findElements(By.xpath(xpath)).size() == 0 && !celltext .equals("Not Present")){
			
			System.out.println("FAIL3");
			xlResults[1][10]="APPT is not coming under Appointments and coming under Action Items on Home Screen before payment -FAILED";
			Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			Assert.assertEquals(0, driver.findElements(By.xpath(xpath)).size());
			Assert.assertEquals(celltext, name);
			
		}
		else if( driver.findElements(By.xpath(xpath)).size() ==1 || celltext .equals("Not Present")){
			
			System.out.println("PASS3");
			
			xlResults[1][10]="APPT is coming under Appointments and not coming under Action Items on Home Screen before payment -PASSED";
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkAppointments']"; 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList_ctl02_lblAppt']";  //Appointment Date
			String Date= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList']/tbody/tr[2]/td[6]";  // PromiseKept
			String PromiseKept = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList']/tbody/tr[2]/td[9]";  //Note
			String Note = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
			if (Date.equals("05/05/2017 9:00 AM") && PromiseKept .equals("N/A") && Note .equals("TEST-CUSTOMER WILL COME")){
			
				xpath =".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
					
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // Ref 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']"; 
				String PAMT = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				System.out.println(PAMT);
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(PAMT);
			
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtFromCustomer_Value']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_CustomerInfo_lnkName']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkAppointments']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList_ctl02_lblAppt']";  //Appointment Date
				String Date1= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList']/tbody/tr[2]/td[6]";  // PromiseKept
				String PromiseKept1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList']/tbody/tr[2]/td[9]";  //Note
				String Note1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			
			
				if (Date1.equals("05/05/2017 9:00 AM") && PromiseKept1 .equals("Yes") && Note1 .equals("TEST-CUSTOMER WILL COME")){
			
					System.out.println("PASS POST PAYMENT");
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_dgList_ctl02_btnEdit']"; //PTP
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Appointments_chkApptKept']"; //checkbox
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
									
					WebElement checkbox = driver.findElement(By.id("ctl00_ctl00_MainContent_ChildContent1_Appointments_chkApptKept"));
					if(checkbox.isSelected()){
						
						System.out.println("PASS CHKBOX");
						xlResults[1][11]="Appointment was Kept is checked post payment and Promise Kept is:" +PromiseKept1 +"PASSED";
				//		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
						
					}
					else{
					
						System.out.println("FAIL CHKBOX");
						xlResults[1][11]="Appointment was Kept is not checked post payment - FAILED";
				//		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
	
					}
					Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
					Assert.assertEquals(true, checkbox.isSelected());
				}
				else{
				
					System.out.println("FAIL POST PAYMENT");
					xlResults[1][11]="PromiseKept,Date or Note are not correct post payment -FAILED";
					Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
				}
				Assert.assertEquals(Date1, "05/05/2017 9:00 AM");
				Assert.assertEquals(PromiseKept1, "Yes");
				Assert.assertEquals(Note1, "TEST-CUSTOMER WILL COME");

			}
			
			else{
				
				xlResults[1][10]="PromiseKept,Date or Note are not correct before Payment -FAILED";
				Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
		}
	}
	
	else{	
		
		System.out.println("Check the DATA for Initial Promise Kept");
		xlResults[1][10]="Check the DATA for Initial Promise Kept";
		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
		
	}
	Assert.assertEquals(xlResults[1][6], "N/A");
}

	@Test (priority = 12)
	public void  _12_homescreen_on_apptdtpostpayment()throws Exception{

		if (xlResults[1][6] .equals("N/A")){
		//Home Screen post payment
			xpath =".//*[@id=('Left')]/a[1]";  // Save
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			
			xpath =".//*[@id=('ctl00_MainContent_TabNav_lnkOverview')]";  // Save
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
		
			WebElement table1 = driver.findElement(By.id("ctl00_MainContent_pnlActionItems")); 
	
				// Now get all the TR elements from the table 
			List<WebElement> Rows1 = table1.findElements(By.tagName("tr")); 
				
			int rows_count1 = Rows1.size();
				outerloop:	
				// And iterate over them, getting the cells 
			 for(int i=0; i<rows_count1;i++)
			 {
			  List<WebElement> Cols1 = Rows1.get(i).findElements(By.tagName("td"));
			  int columns_count1 = Cols1.size();
				  
			  for(int j=0; j < columns_count1;j++)
			  	{
				  if(Cols1.get(j).getText().contains(xlCustFName))
				  	{
				   
			 			celltext = Cols1.get(j).getText();
						System.out.println("Cell Value Of row number " + i+ " and column number " + j + " Is " + celltext);
						//	xlResults[1][10] ="APPT is coming on Home Screen - FAILED";
						break outerloop;
					  	}
				  else {
					   
					  	celltext = "Not Present";
					  }
			  	   } 
				}
				 
			System.out.println(celltext);
				   
			Thread.sleep(2000);				
			xpath =".//*[@id='ctl00_MainContent_gvAppts_ctl02_lnkEdit00']";  // Save
						
			if ( driver.findElements(By.xpath(xpath)).size() == 0 ){//&& celltext. equals("Not Present")){	
						
				System.out.println("PASS FINAL");
				xlResults[1][12] ="APPT is not coming under Appointments/Action Items on Home Screen post payment- PASSED";
				//		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
				
			else{
					
				System.out.println("FAIL FINAL");
				xlResults[1][12] ="APPT is coming under Appointments/Action Items on Home Screen post payment - FAILED";
				//		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
			}
		
				Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);

				Assert.assertEquals(0, driver.findElements(By.xpath(xpath)).size());
//				Assert.assertEquals(celltext,"Not Present");
		
		}
			
	else{
			
		System.out.println("Check the DATA");
		
		xlResults[2][12]="Check the DATA for initial promise kept - FAIL";
		Utils.writeXL(OR.getProperty("vLOCPromiseKept"),"VA_LOC",xlResults);
	
	}

		Assert.assertEquals(xlResults[1][6], "N/A");	
 }

	


@Test (priority = 13)
public void _13_businessdatebacktoOriginal() throws Exception {
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();


    	xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/29/2017");

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
         
        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
        String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
        System.out.println(newBusDate);
  	
        
        if (newBusDate .equals("03/29/2017")) {
        	
        	/*         	Thread.sleep(2000);	
			xpath = ".//*[@id='menuv2']/li[9]/a";
			Function_Classes.logout(driver1, xpath); // for log out */
	        
	    	Thread.sleep(1000);
	    	driver1.close();	
	    	
	    	System.out.println("_Business_Date is changed successfully  --  Passed"); 	

		}

		else{
			System.out.println("Change business date to : 03/29/2017 "); 
		}
    	
	}

@Test (priority = 14)
public void _14_log_out_teller() throws Exception {
	
	xpath =".//*[@id=('Left')]/a[5]";  // Save
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	xpath=".//*[@id='menuv2']/li[7]/a";
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	xpath=".//*[@id='ctl00_AppHeader_btnLogout']";
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	Thread.sleep(1500);
	driver.close();

	}	
}	
	
