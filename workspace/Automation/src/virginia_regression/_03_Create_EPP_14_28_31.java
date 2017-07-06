/**
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression;

import Utility.Utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import Functions.Function_Classes;


public class _03_Create_EPP_14_28_31 {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
		WebDriver driver;
		WebDriver driver1;
		String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
		String URL1 = "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=220";
		String Username, Password;
		String loanlink,Status,DueDate,OriDate,BusDate,Tot,Balance,S_Balance,S_num1,S_num2,S_num3,S_num4;
		String xpath,mainWinID,newWinID;
		String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
		String[][] xlData;
		String[][] xlResults;
		String[] expected = {"100", "200", "300", "400", "500"};
		public static Properties OR=null;
		
		//@BeforeTest	
		@Test (priority = 1)
		public void  _1_admin_login () throws Exception{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Automation QA\\chromedriver.exe");
		
		
			OR = new Properties();
			FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
			OR.load(fn);
	
			xlData = Utils.readXL(OR.getProperty("vpath"),"TestData_VA");
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
			Function_Classes.field_sendKeys(driver1, xpath, xlData[15][3]);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='btnSubmit']"; // Click on go button
			Function_Classes.field_click(driver1, xpath);
			
		
		    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + xlData[49][15]);
					
		    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
		    new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		    
		    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
		    String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		    System.out.println(newBusDate);
		    
			System.out.println("_Business_Date is changed successfully  --  Passed"); 

	
	}
	
	@Test (priority = 2)
	public void _2_navigate_to_eCashURL (){		
		System.out.println("Ecash URL  --  Started");
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);		
	
}
	
	
	@Test (priority = 3)
	public void  _3_teller_login () throws Exception{

		
		xlUsername = xlData [1][0];
		xlPassword = xlData [1][1];
		xlStore = xlData [16][2];
			
		System.out.println("Ecash Store Login   --  Started");
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(1000);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		Function_Classes.field_sendKeys(driver, xpath, xlStore);
		System.out.println(xlStore);

		Thread.sleep(1000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		
		System.out.println("Ecash Store Login   --  Passed");
	}
	
	
	
	// New Customer Creation  - working fine
	@Test (priority = 4)
	public void _5_EPP_Creation() throws Exception {
	
		
			System.out.println("Ecash New Customer Creation   --  Started");
			xlResults = Utils.readXL(OR.getProperty("vwpath"),"VA_EPP_14");	
	
			
			for (int i = 49,j=15; i <= 63; i = i+1, j++){
	
				
					xlCustFName = xlData [i][4];
					//System.out.println(xlCustFName);// Printing Customer First Name
					
					xlCustLName = xlData [i][5];
					//System.out.println(xlCustLName);// Printing Customer Last Name
					
					xlssn = xlData [i][6];
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
				
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State 
					Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect1.selectByIndex(11);
				
				//  Thread.sleep(1000);
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("37343");

				//	Entering Phone Details
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Phone Type 
					Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect4.selectByIndex(2);
					
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
					Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phone no 

					//Entering Bank Details
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
					Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
					oSelect2.selectByIndex(1);
					
					Thread.sleep(2000);		
					xlRoutingno = xlData [i][8];
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
				 	Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);
					
//				 	Thread.sleep(2000);	
					xlaccno = xlData [i][9];
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);

					xlbankname = xlData [i][10];
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbankname);

				// entering Identification 

					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboType']";  // Select Identification  Type :  Driver's License type  
					Select Idnt = new Select(driver.findElement(By.xpath(xpath)));
					Idnt.selectByIndex(1);
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtNumber']";   // Driver's license no
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("123456789");
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_cboState']";  // Select State  Type :  Driver's License type  
					Select state = new Select(driver.findElement(By.xpath(xpath)));
					state.selectByIndex(11);
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustIdentification_txtExpirationDate']"; //Selecting Expiration date
					Function_Classes.field_sendKeys(driver, xpath, "12/12/2020");
							
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
					Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
					IncomeType.selectByIndex(1);
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']";  // employer
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("ABC Company");

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']";  // Occupation
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Software");

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']";  // JobPayAmt
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("10000");

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']";  // Pay Date
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[i][19]);

					Thread.sleep(1000);
					Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
					PayFreq.selectByVisibleText(xlData[i][18]);
					
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
					xlResults[j][1] = xlrCustFName;
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblLastName']";	
					String xlrCustLName = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					xlResults[j][2] = xlrCustLName;
				
					
					System.out.println("Ecash New Loan Creation   --  Started");
						

					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // Clicking on loanmodel dropdowm
					Select loanmodel = new Select(driver.findElement(By.xpath(xpath))); // loan model 
					if( i<=53){
						loanmodel.selectByIndex(1);
					}
					if( i>=54 && i <=58){
						loanmodel.selectByIndex(2);
					}
					if( i>=59 && i <=63){
						loanmodel.selectByIndex(3);
					}
						
						
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next 
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				 	Thread.sleep(2000);
					xlcheckno = xlData [i][11];
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtCciCheckNumber']";  // entering the Check Number 
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlcheckno);

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlData[i][12]);

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
					Function_Classes.field_click(driver,xpath);  // Click on Next button again*/

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Next Button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  //loan link
					loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(loanlink);
					xlResults[j][3] = loanlink;
					
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwPrinBal']";  // status
					String Amount =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Amount);
					xlResults[j][4] = Amount;
					
					
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // status
					Status =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					System.out.println(Status);
					xlResults[j][5] = Status;
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
					DueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
					System.out.println(DueDate);
					xlResults[j][6] = DueDate;
						
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
					OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
					System.out.println(OriDate);
					xlResults[j][7] = OriDate;
						

		
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // Next Button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkCreatePmtPlan']/span";  // click on EPP button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl01_txtPmtPlan_ChkNum']";  // check#1
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1");
	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl02_txtPmtPlan_ChkNum']";  // check#2
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("2");
	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl03_txtPmtPlan_ChkNum']";  // check#3
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3");
	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl04_txtPmtPlan_ChkNum']";  // check#4
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("4");
				

				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl01_txtPmtPlan_PmtAmt']";  // Next Button
				String InstAmt1=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
		
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl02_txtPmtPlan_PmtAmt']";  // Next Button
				String InstAmt2=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
		
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl03_txtPmtPlan_PmtAmt']";  // Next Button
				String InstAmt3=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");

				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPmtPlan_Plan_ctl04_txtPmtPlan_PmtAmt']";  // Next Button
				String InstAmt4=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");

				DecimalFormat decimalFormat = new DecimalFormat("###.00");
				float num1 = Float.parseFloat(InstAmt1);
				float num2 = Float.parseFloat(InstAmt2);
				float num3 = Float.parseFloat(InstAmt3);
				float num4 = Float.parseFloat(InstAmt4);
								
				float total = num1 + num2 + num3 + num4 ;
				S_num1 = decimalFormat.format(num1);
				S_num2 = decimalFormat.format(num2);
				S_num3 = decimalFormat.format(num3);
				S_num4 = decimalFormat.format(num4);
				Tot = decimalFormat.format(total);
//				System.out.println(total);
//				System.out.println(Tot);
				
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnPmtPlan_Submit']";  // Submit
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
				Thread.sleep(2000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwStatus']";  // Status
				Status = Function_Classes.field_gettext(driver,xpath);
				xlResults[j][8] = Status;
				
	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // Due Date
				DueDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(DueDate);
				xlResults[j][9] = DueDate;
				
	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // Ori Date
				OriDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				xlResults[j][10] = OriDate;
				
	
				xpath =".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // business date
				BusDate=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
//				System.out.println(BusDate);
				String BDate = BusDate.substring(15);
				xlResults[j][11] = BDate;
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwBalance']";  // business date
				Balance=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				S_Balance=Balance.substring(1);
				System.out.println(S_Balance);
				xlResults[j][12] = InstAmt1+"/"+InstAmt2+"/"+InstAmt3+"/"+InstAmt4 + " :"+S_Balance;
				
				if (Tot .equals(S_Balance)){// && num1 == num2 && num2==num3  && num3==num4 ){
					
					xlResults[j][13] = "YIPEE-PASS";
					
				}
				else{
					
					xlResults[j][13] = "BUMMER";
					
					Assert.assertEquals(Tot,S_Balance);
		//			Assert.assertEquals(S_num1,S_num2);
		//			Assert.assertEquals(S_num2,S_num3);
		//			Assert.assertEquals(S_num3,S_num4);
				}
				
				Utils.writeXL(OR.getProperty("vwpath"),"VA_EPP_14",xlResults);
				
			}

			
			System.out.println("EPPs created successfully   --  Passed");
	
	}	

		@Test (priority = 6)
		public void  _6_businessdatebacktoOriginal () throws Exception{
	
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
	        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
	        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + xlData[49][17]);
	
	        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	        new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	        String newBusDate=new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	        System.out.println(newBusDate);
	
		}
		
		@Test (priority = 7)
		public void  _7_logout_admin () throws Exception{
			
			Thread.sleep(1500);	
	 		xpath = ".//*[@id='menuv2']/li[9]/a";
	        Function_Classes.logout(driver1, xpath); // for log out
		        
	        Thread.sleep(1000);
			driver1.close();	
	       
			System.out.println("_logged out successfully  --  Passed"); 
			 
		}

		@Test (priority = 8)
		public void  _8_logout_teller () throws Exception{
			

		    Thread.sleep(1000);
			driver.close();	
	       
			System.out.println("_logged out successfully  --  Passed"); 
			 
		}
}	
			

	
