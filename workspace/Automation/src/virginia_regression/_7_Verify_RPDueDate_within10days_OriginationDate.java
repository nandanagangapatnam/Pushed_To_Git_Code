/**
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;


public class _7_Verify_RPDueDate_within10days_OriginationDate {
	
	WebDriver driver,driver1,driver2;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1 = "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=167";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,OriDate,FPDueDate,BusDate,Late;
	String xlCdtlimit , xlDraw;
	String xpath,EarnedPrincoutput;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustFName, xlCustLName,xlssn,xlRoutingno,xlaccno,xlbank,xlOrigDate;
	String	ActualRPDueDate;
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
        
	//	System.out.println("_Business_Date is changed successfully  --  Passed"); 
		 
	}
	
	// New LOCs Creation  - working fine
	@Test (priority = 4)
	public void _4_newLOCs_Creation() throws Exception {

	xlResults = Utils.readXL(OR.getProperty("VLOCRPDueDatepath"),"VA_LOC");	
		
	for (int i = 31 , j=1; i < 34  ; i = i + 1, j=j+1){


		xlCustFName = xlData [i][4];
		//System.out.println(xlCustFName);// Printing Customer First Name
		
		xlCustLName = xlData [i][5];
		//System.out.println(xlCustLName);// Printing Customer Last Name
		
		xlssn = xlData [i][6]; //SSN
		
		xlOrigDate=xlData[i][13];
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

    	xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
       	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
       	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + xlOrigDate);
      
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

    	xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";// Current Date
    	new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//    	xlResults[j][4] = new WebDriverWait(driver1, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    	System.out.println(xlResults[j][4]);
    	
    	
    	
		
		if ( j == 1) {
			System.out.println("RapidPay Due Date for loans originated before 10days-Origination Date: " +xlResults[j][4] );
		}
		else if ( j == 2){
			System.out.println("RapidPay Due Date for loans originated on 10days-Origination Date: " +xlResults[j][4] );
		}
		else {
			System.out.println("RapidPay Due Date for loans originated within 10days-Origination Date: " +xlResults[j][4] );
		}
	    	
		
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath = ".//*[@id='Block']/div[1]/a"; // Click on New Customer button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtFirstName']";  // First Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Function_Classes.field_sendKeys(driver, xpath, xlCustFName);
		xlResults[j][1]=xlCustFName;
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtLastName']";  // Last Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCustLName);
		xlResults[j][2]=xlCustLName;
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtSsn']";  // Passing Password    SSN/ITIN is required
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlssn);

		String xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_txtBirthDate']";  // Date of Birth is required
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "01/01/1970");
		
		Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboType']";  // Select Address Type :  Home
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByIndex(2);
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtAddrLine1']"; // entering Address
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("124 Main St");

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtCity']"; // entering City
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Florida");
	
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_cboState']";  // Select State :  Ohio
		Select oSelect1 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect1.selectByIndex(11);
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustAddress_txtZip']"; // entering zip code
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("37343");

		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_cboType']";  // Select Address Type :  Phone type  home no
		Select oSelect4 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect4.selectByIndex(2);
		
		Thread.sleep(2000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustPhone_txtNumber']";
		Function_Classes.field_sendKeys(driver, xpath, "1234567890");  // enter phone no 

		Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_cboType']";  // Select Account Type :  Checking
		Select oSelect2 = new Select(driver.findElement(By.xpath(xpath)));
		oSelect2.selectByIndex(1);
		
		Thread.sleep(1000);		
		xlRoutingno = xlData [i][8];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtRoutingNum']"; // entering Routing Number
		Function_Classes.field_sendKeys(driver, xpath, xlRoutingno);

	//	Thread.sleep(2000);	
		xlaccno = xlData [i][9];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtAccountNum']"; // entering Account Number
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlaccno);

		xlbank = xlData [i][10];
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustBankAccount_txtBankName']"; // entering Bank Name
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlbank);

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
				
		
		Thread.sleep(1500);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboIncomeType']";  // selecting Income type: Regular Job 
		Select IncomeType = new Select(driver.findElement(By.xpath(xpath)));
		IncomeType.selectByIndex(1);

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtEmployer']";  // employer
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("ABC Company");
	
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtOccupation']";  // Occupation
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("CEO");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_txtJobPayAmt']";  // JobPayAmt
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("100000");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']";  // Pay Date
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("03/15/2020");
		
		Thread.sleep(1000);
		Select PayFreq = new Select(driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_cboJobPayFreq']"))); // Pay Freq 
		PayFreq.selectByIndex(3);
		
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Clicking on Save Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='Block']/div[1]/div[2]/ul/li";
		String Address_Check=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		
		if(Address_Check.contains("The address you entered is similar to an address")){
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			}

		System.out.println("Ecash New LOC Creation   --  Started");

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("5000");

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
		xlResults[j][3] = loanlink;
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
		OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		System.out.println(OriDate);
		xlResults[j][4] = OriDate;
			
		
		Utils.writeXL(OR.getProperty("VLOCRPDueDatepath"),"VA_LOC",xlResults);
	}
			
	System.out.println("LOCs created successfully   --  Passed");
}
	
@Test (priority = 5)
public void  _5_rapidpay_duedate_BEFORE_10days_of_loanOrigination()throws Exception{
		
	System.out.println("RapidPay Due Date for loans originated before 10days-Origination Date: " +xlResults[1][4]);
	
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( xlResults[1][3]);

		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		Thread.sleep(2500);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  // FP Due Date
		if(driver.findElements(By.xpath(xpath)).size()!= 0){
		
			ActualRPDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
		
			System.out.println(ActualRPDueDate);
		
		if (ActualRPDueDate .equals("04/01/2020")){
						
				xlResults[1][6] = "04/01/2020"; //Expected RP Due Date
				xlResults[1][5] = ActualRPDueDate; //Actual RP Due Date
				xlResults[1][7] = "PASS"; //Status
				}
		else{
				xlResults[1][6] = "04/01/2020"; //Expected RP Due Date
				xlResults[1][5] = ActualRPDueDate; //Actual RP Due Date
				xlResults[1][7] = "FAIL"; //Status
			}
			Utils.writeXL(OR.getProperty("VLOCRPDueDatepath"),"VA_LOC",xlResults);
			Assert.assertEquals(ActualRPDueDate,"04/01/2020");
		}		
}

@Test (priority = 6)
public void  _6_rapidpay_duedate_ON_10days_of_loanOrigination()throws Exception{

	System.out.println("RapidPay Due Date for loans on 10days-Origination Date: " +xlResults[2][4]);
	
	xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
	xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( xlResults[2][3]);

	xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	Thread.sleep(2500);
	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  // FP Due Date
	if(driver.findElements(By.xpath(xpath)).size()!= 0){
	
		ActualRPDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
	
		System.out.println(ActualRPDueDate);
		
				if (ActualRPDueDate .equals("04/01/2020")){
				
					xlResults[2][6] = "04/01/2020"; //Expected RP Due Date
					xlResults[2][5] = ActualRPDueDate; //Actual RP Due Date
					xlResults[2][7] = "PASS"; //Status
				}
				else{
					xlResults[2][6] = "04/01/2020"; //Expected RP Due Date
					xlResults[2][5] = ActualRPDueDate; //Actual RP Due Date
					xlResults[2][7] = "FAIL"; //Status
				}
		
				Utils.writeXL(OR.getProperty("VLOCRPDueDatepath"),"VA_LOC",xlResults);
		
				Assert.assertEquals(ActualRPDueDate,"04/01/2020");
		}		
	}

	

@Test (priority = 7)
	public void  _7_rapidpay_WITHIN_10days_of_loanOrigination()throws Exception{

	System.out.println("RapidPay Due Date for loans on 10days-Origination Date: " +xlResults[3][4]);
	
	xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
	xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan id  value  
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys( xlResults[3][3]);

	xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";  // click on loan id link
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	Thread.sleep(2500);
	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  // FP Due Date
	if(driver.findElements(By.xpath(xpath)).size()!= 0){
	
		ActualRPDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText(); 
	
		System.out.println(ActualRPDueDate);
		
					if (ActualRPDueDate .equals("04/15/2020")){
			
						xlResults[3][6] = "04/15/2020"; //Expected RP Due Date
						xlResults[3][5] = ActualRPDueDate; //Actual RP Due Date
						xlResults[3][7] = "PASS"; //Status
					}
					else{
						xlResults[3][6] = "04/15/2020"; //Expected RP Due Date
						xlResults[3][5] = ActualRPDueDate; //Actual RP Due Date
						xlResults[3][7] = "FAIL"; //Status
					}
	
					Utils.writeXL(OR.getProperty("VLOCRPDueDatepath"),"VA_LOC",xlResults);
	
					Assert.assertEquals(ActualRPDueDate,"04/15/2020");
			}		
}


@Test (priority = 8)
public void _8_log_out() throws Exception {

	xpath=".//*[@id='menuv2']/li[7]/a";
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	Thread.sleep(1000);
	Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
	
	Thread.sleep(1000);
	driver.close();

}		


@Test (priority = 9)
public void _9_businessdatebacktoOriginal() throws Exception {
	
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
        	
           	Thread.sleep(2000);	
			xpath = ".//*[@id='menuv2']/li[9]/a";
			Function_Classes.logout(driver1, xpath); // for log out
	        
	    	Thread.sleep(1000);
	    	driver1.close();	
	    	
	    	System.out.println("_Business_Date is changed successfully  --  Passed"); 		

		}

		else{
			System.out.println("Change business date to : 03/29/2017 "); 
		}
    	
	}
}	
	