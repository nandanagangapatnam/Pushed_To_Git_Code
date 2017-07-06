package california_regression;

import Utility.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_Classes;


public class Create_EPP {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	WebDriver driver1;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1 = "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=207";
	String Username, Password, LoggedName;

	String loanlink,Status,DueDate,OriDate,BusDate;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	String[][] xlResults1;
	public static Properties OR=null;
	

	//@BeforeTest	
	@Test (priority = 1)
	public void Url_search (){		
		System.out.println("Ecash URL  --  Started");
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);		
	
}
	@Test (priority = 2)
	public void  _1_admin_login () throws Exception{

		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("cpath"),"TestData_CA");
		
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
		Function_Classes.field_sendKeys(driver1, xpath, xlData[1][3]);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver1, xpath);
		
	
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/18/2017");
				
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    System.out.println(newBusDate);
	    
		System.out.println("_Business_Date is changed successfully  --  Passed"); 


}

	
	@Test (priority = 3)
	public void  _2_store_login () throws Exception{

		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];


			System.out.println("Ecash Teller Login   --  Started");
			Thread.sleep(1000);
			xpath=".//*[@id='txtUsername']";  // Clearing User Name field
			Function_Classes.field_clear(driver, xpath );
			
			Thread.sleep(1000);
			xpath=".//*[@id='txtUsername']";  // Passing User name 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlUsername);
			
			Thread.sleep(1000);
			xpath=".//*[@id='txtPasswd']";  // Clearing Password field
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			
			Thread.sleep(1000);
			xpath=".//*[@id='txtPasswd']";  // Passing Password
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlPassword);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='btnSubmit']"; // Click on go button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath=".//*[@id='ddlStore']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlStore);
	
			Thread.sleep(1000);
			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			
			System.out.println("Ecash Teller Login   --  Passed");
	}
	
	
	
	// New Customer Creation  - working fine
	@Test (priority = 4)
	public void _3_newcustomer_Creation() throws Exception {
	
		
		System.out.println("Ecash New Customer Creation   --  Started");
		
		xlResults1 = Utils.readXL(OR.getProperty("cwpath"),"CA_EPP");
		
		for (int i = 1; i < 5; i = i + 1){
			//System.out.println(xRows);
	
			
			xlCustFName = xlData [i][4];
			//System.out.println(xlCustFName);// Printing Customer First Name
			
			xlCustLName = xlData [i][5];
			//System.out.println(xlCustLName);// Printing Customer Last Name
			
			xlssn = xlData [i][6];
			//System.out.println(xlssn); // Printing Customer SSN
			
			Thread.sleep(1000);
			
				// Entering General Information			
			xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtFirstName']";  // First Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustFName);
			
	
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtLastName']";  // Last Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustLName);
			
	   
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + xlssn);
			
	
			String xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/01/1970");
			
			// Entering Address
			
	
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboType']";  // Select Address Type :  Home
			Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
			oSelect.selectByIndex(2);
			
			Thread.sleep(2000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtAddrLine1']"; // entering Address
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("124 Main St");
		
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtCity']"; // entering City
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Florida");
		
	
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State :  Ohio
			Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
			oSelect1.selectByIndex(11);
			
		
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("37343");
			
				// entering Phone number 
	
	
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Address Type :  Phone type  home no
			Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
			oSelect4.selectByIndex(2);
			
			Thread.sleep(2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1234567890");  // enter phone no 
		
			
			//Entering Bank Details
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
			Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
			oSelect2.selectByIndex(1);
			
			Thread.sleep(2000);		
			xlRoutingno = xlData [i][8];
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlRoutingno);
			
			xlaccno = xlData [i][9];
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);
			
			xlbankname = xlData [i][10];
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbankname);

		

			Thread.sleep(1000);
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
			Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
			IncomeType.selectByIndex(1);
			
			Thread.sleep(2000);
			xpath= ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']"; // Employer Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( "ABC Company");

			xpath= ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']"; // Employer Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( "Software");
		
			xpath= ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']"; // Employer Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( "4000");

			xpath= ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']"; // Employer Name
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( "3/15/2017");

	
//			Thread.sleep(1000);
			Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
			PayFreq.selectByVisibleText("1st & 15th");
			
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
			Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
			Idnt.selectByIndex(1);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("123456789");
			
//			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
			Select state = new Select(driver.findElement(By.xpath(xpath)));
			state.selectByIndex(11);
			
//			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtExpirationDate']"; //Selecting Expiration date
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("12/12/2020");
					
				
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Clicking on Save Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
			String Address_Check=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
			if(Address_Check.contains("The address you entered is similar to an address")){
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			}
	
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblFirstName']";	
			String xlrCustFName = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			//System.out.println(xlrCustFName);
			xlResults1[i][1] = xlrCustFName;
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblLastName']";	
			String xlrCustLName = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			xlResults1[i][2] = xlrCustLName;
		
			Thread.sleep(1000); 
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
//			Thread.sleep(3000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // Clicking on loanmodel dropdowm
			Select loanmodel = new Select(driver.findElement(By.xpath(xpath))); // loan model 
			loanmodel.selectByVisibleText("PRA");
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // entering the Check Number 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
			
			Thread.sleep(1000);
			xlcheckno = xlData [i][11];
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtCciCheckNumber']";  // entering the Check Number 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlcheckno);	
			
			
//			Thread.sleep(2000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[i][12]);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();  // Click on Next button

			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();  // Click on Next button again
			
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();  // Click on Next button*/	
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Next Button
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();  // Click on Next button	

//			Thread.sleep(3000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";
			loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
			System.out.println(loanlink);
			xlResults1[i][3] = loanlink;
			
//			Thread.sleep(3000);
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // check#2
			Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			System.out.println(Status);
			xlResults1[i][4] = Status;
			
//			Thread.sleep(3000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // check#2
			DueDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			System.out.println(DueDate);
			xlResults1[i][5] = DueDate;
			
//			Thread.sleep(3000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // check#2
			OriDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			System.out.println(OriDate);
			xlResults1[i][6] = OriDate;
			
			
//			Thread.sleep(2000);
			xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
			BusDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			System.out.println(BusDate);
			String BDate = BusDate.substring(15);
			xlResults1[i][7] = BDate;
			
	
			Utils.writeXL(OR.getProperty("cwpath"),"CA_EPP",xlResults1);
		}
			
	
		System.out.println("Customers created successfully   --  Passed");
	}
	

		
	@Test (priority = 5)
	public void  ChkEPPERA () throws Exception{
			
			
			System.out.println("EPP_ON/AFTER_Origination_DueDate  --  Started");
			
			for (int i = 1; i < 5; i = i + 1){
				//System.out.println(xRows);
			if ( i==1)
				
			{
				System.out.println("EPP ON Origination Date  --  Started");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
	//			Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlResults1[1][3]);	
				
	//			Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
	//			Thread.sleep(3000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
	//			Thread.sleep(3500);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkCreatePmtPlan']/span";  // click on Payment button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl01_txtPmtPlan_ChkNum']";  // check#1
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1");
				
		//		Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl02_txtPmtPlan_ChkNum']";  // check#2
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("2");
				
		//		Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl03_txtPmtPlan_ChkNum']";  // check#3
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3");
				
		//		Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl04_txtPmtPlan_ChkNum']";  // check#4
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("4");
				
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnPmtPlan_Submit']";  // Submit
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // check#2
				Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(Status);
				xlResults1[1][8] = Status;
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // check#2
				DueDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(DueDate);
				xlResults1[1][9] = DueDate;
				
	//			Thread.sleep(2000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // check#2
				OriDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(OriDate);
				xlResults1[1][10] = OriDate;
				
			
				
	//			Thread.sleep(2000);
				xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
				BusDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(BusDate);
				String BDate = BusDate.substring(15);
				xlResults1[1][11] = BDate;
				
			}
					
			else if  ( i == 2 )
					{
						

					    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
					    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/24/2017");
								
				        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				        
				        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				        
						System.out.println("_Business_Date is changed successfully  --  Passed"); 
								
										
						 //crating EPP after origination date and before due date 
						 
						 System.out.println("EPP After Origination Date and before due date  --  Started");
						 
								
							Thread.sleep(1000);
							xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
		//					Thread.sleep(3500);
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlResults1[2][3]);	
							
		//					Thread.sleep(3500);
							xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
		//					Thread.sleep(3000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
	//						Thread.sleep(3500);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkCreatePmtPlan']/span";  // click on Payment button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						 
				
							Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl01_txtPmtPlan_ChkNum']";  // check#1
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1");
				
	//						Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl02_txtPmtPlan_ChkNum']";  // check#2
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("2");
				
	//						Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl03_txtPmtPlan_ChkNum']";  // check#3
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3");
							
	//						Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl04_txtPmtPlan_ChkNum']";  // check#1
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("4");
				
							
							Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnPmtPlan_Submit']";  // Save
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
	//						Thread.sleep(2000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // check#2
							Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(Status);
							xlResults1[2][8] = Status;
							
	//						Thread.sleep(2000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // check#2
							DueDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(DueDate);
							xlResults1[2][9] = DueDate;
							
	//						Thread.sleep(2000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // check#2
							OriDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(OriDate);
							xlResults1[2][10] = OriDate;
							
						
							
		//					Thread.sleep(2000);
							xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
							BusDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(BusDate);
							String BDate = BusDate.substring(15);
							xlResults1[2][11] = BDate;
							
					
					}	
				
					else if  ( i == 3 )
					{
						
						
						
					        
					    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
					    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/25/2017");
								
				        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				        
				        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
				        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				        
						System.out.println("_Business_Date is changed successfully  --  Passed"); 
						 
						System.out.println("EPP ON due date  --  Started");
						 

					
							Thread.sleep(1000);
							xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
		//					Thread.sleep(3500);
							xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlResults1[3][3]);	
							
		//					Thread.sleep(3500);
							xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
		//					Thread.sleep(3000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//					
		//					Thread.sleep(3500);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkCreatePmtPlan']/span";  // click on Payment button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						 
				
							Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl01_txtPmtPlan_ChkNum']";  // check#1
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1");
				
			//				Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl02_txtPmtPlan_ChkNum']";  // check#2
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("2");
				
			//				Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl03_txtPmtPlan_ChkNum']";  // check#3
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3");
							
			//				Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl04_txtPmtPlan_ChkNum']";  // check#1
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("4");
							
			//				Thread.sleep(2500);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnPmtPlan_Submit']";  // Save
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							Thread.sleep(1000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // check#2
							Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(Status);
							xlResults1[3][8] = Status;
							
			//				Thread.sleep(2000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // check#2
							DueDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(DueDate);
							xlResults1[3][9] = DueDate;
							
			//				Thread.sleep(2000);
							xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // check#2
							OriDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(OriDate);
							xlResults1[3][10] = OriDate;
							
						
							
			//				Thread.sleep(2000);
							xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
							BusDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							System.out.println(BusDate);
							String BDate = BusDate.substring(15);
							xlResults1[3][11] = BDate;
	
							
					}	
			
					else if  ( i == 4 )
					{
						
				
				System.out.println("EPP_AFTER_DueDate  --  Started");
				
				
		        
			    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/26/2017");
						
		        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		        
		        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
		        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
		        
				System.out.println("_Business_Date is changed successfully  --  Passed"); 
				 
				 
				 System.out.println("EPP After Due Date  --  Started");
					
					Thread.sleep(1000);
					xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
					
			//		Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlResults1[4][3]);	
					
			//		Thread.sleep(3500);
					xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			//		Thread.sleep(3000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			//		Thread.sleep(3000);
				 	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkCreatePmtPlan']/span";  // click on Payment button
				 	
				 	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				 	
				 	if(driver.findElements( By.id("ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl01_txtPmtPlan_ChkNum") ).size() != 0){
					
	 			
				 	System.out.println("Create ERR/EPP Button is enabled-- Failed");
	
	
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // check#2
					Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Status);
					xlResults1[4][8] = Status;
					
		//			Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // check#2
					DueDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(DueDate);
					xlResults1[4][9] = DueDate;
					
		//			Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // check#2
					OriDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(OriDate);
					xlResults1[4][10] = OriDate;
					
				
					
		//			Thread.sleep(2000);
					xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
					BusDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(BusDate);
					String BDate = BusDate.substring(15);
					xlResults1[4][11] = "EPP is enabled post duedate" +BDate;
			
					//xlResults1[4][11] = "EPP is enabled post duedate ---- Failed";
				
		
					}
					else 
					{
					System.out.println("Create ERR/EPP Button is disabled-- Passed");
						
					Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // check#2
					Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Status);
					xlResults1[4][8] = Status;
					
			//		Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // check#2
					DueDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(DueDate);
					xlResults1[4][9] = DueDate;
					
			//		Thread.sleep(2000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // check#2
					OriDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(OriDate);
					xlResults1[4][10] = OriDate;
					
				
					
			//		Thread.sleep(2000);
					xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
					BusDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(BusDate);
					String BDate = BusDate.substring(15);
					xlResults1[4][11] = "EPP can not be done post due date" +BDate;
			
				//	xlResults1[4][11] = "01/03/2017-EPP can not be done post due date";

				}
			//		Thread.sleep(2000);
	
					 
					System.out.println("Passed"); 
				
				
			}
				Utils.writeXL(OR.getProperty("cwpath"),"CA_EPP",xlResults1);
				}				 
				 
				 
		}	
		
	@Test (priority = 7)
	public void  BusinessdatebacktoOriginal () throws Exception{
		
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

        
        Thread.sleep(3000);
        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // BusinessDate
        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		driver1.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + "01/18/2017");
		
		
        Thread.sleep(3000);
        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		Function_Classes.field_click(driver1,xpath);
		
		Thread.sleep(3000);	
        Function_Classes.logout(driver1, ".//*[@id='menuv2']/li[9]/a"); // for log out
        
		System.out.println("_Business_Date is changed successfully  --  Passed"); 
		 
		 Thread.sleep(1000);
		 driver1.close();	
		 
		 Thread.sleep(1000);
		 driver.close();	
		 
	}
			
}
	
