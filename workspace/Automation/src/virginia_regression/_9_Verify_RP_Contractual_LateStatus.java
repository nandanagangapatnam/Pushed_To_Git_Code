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

import com.gargoylesoftware.htmlunit.javascript.host.intl.DateTimeFormat;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import Functions.Function_Classes;


public class _9_Verify_RP_Contractual_LateStatus {
	
	WebDriver driver,driver1,driver2;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1 = "http://ecashtest.corp.checksmart.com/setup/store.aspx?action=view&id=167";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,OriDate,DueDate,FPDueDate,BusDate,Late,contractualdate;
	String xlCdtlimit , xlDraw;
	String xpath,EarnedPrincoutput;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustFName, xlCustLName,xlssn,xlRoutingno,xlaccno,xlbank,xlOrigDate;
	String	ActualRPDueDate;
	String[][] xlData;
	String[][] xlResults;
	public static Properties OR=null;

	
	@Test (priority = 1)
	public void  _1_businessdatechange  () throws Exception{
		

		
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
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/29/2017");
					
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    String newBusDate=new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    System.out.println(newBusDate);
    
		System.out.println("_Business_Date is changed successfully  --  Passed"); 

   
	}
	
	
	
	@Test (priority = 2)
	public void _2_navigate_to_eCashURL (){		
		System.out.println("Ecash URL  --  Started");
		
	
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver, URL);	


}
	
	@Test (priority =3)
	public void  _3_storelogin () throws Exception {

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
	
	

	// New LOCs Creation  - working fine
	@Test (priority = 4)
	public void _4_newLOC_creation() throws Exception {

		
		xlResults=Utils.readXL(OR.getProperty("vLOCRPContractualLate"), "VA_LOC");	
		System.out.println("Ecash New LOC Creation   --  Started");
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("RPCONTRACTDUEDATE TEST");
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			

		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dgList_ctl02_btnEdit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_dbxJobLastPayDate_txtDate']";  // Business Date
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "3/15/2017");
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustEmploymentInfo_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
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
		Function_Classes.field_sendKeys(driver, xpath,"100");
				

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwOrigDate']";  // ori date
		OriDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
		String DueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  //FP due date
		String FPDueDate =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  

		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  //FP due date
		String Late =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  


		Assert.assertEquals(OriDate, "03/29/2017");
		Assert.assertEquals(DueDate, "04/23/2017");
		Assert.assertEquals(Late, "Not Late" );
	//	Assert.assertEquals(FPDueDate, "04/15/2017");

	}

	
	@Test (priority = 5)
	public void  _5_busineesdate_et_rapidpayduedate() throws Exception {
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "04/15/2017");
						
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    String newBusDate=new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    System.out.println(newBusDate);
	    xlResults[1][3]=newBusDate;
	    xlResults[1][2]=OriDate;
		    
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][1]=loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
		String DueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][5]=DueDate1;
		System.out.println(DueDate1);
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  //FP due date
		String FPDueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[1][4]=FPDueDate1;
	//	System.out.println(FPDueDate1);
		
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  //FP due date
		String Late1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[1][6]=Late1;		
//		System.out.println(Late1);
		
		if(Late1 .equals("Not Late") && FPDueDate1 .equals("04/15/2017") && DueDate1 .equals("04/23/2017")){
    
			xlResults[1][7]="PASS";
			
		}
		else{
			
			xlResults[1][7]="FAIL";
		}
		
		Utils.writeXL(OR.getProperty("vLOCRPContractualLate"),"VA_LOC",xlResults);
		Assert.assertEquals(newBusDate, "04/15/2017");
		Assert.assertEquals(DueDate1, "04/23/2017");	
		Assert.assertEquals(Late1, "Not Late");
		Assert.assertEquals(FPDueDate1, "04/15/2017");
	}
	
	@Test (priority = 6)
	public void  _6_busineesdate_gt_rapidpayduedate() throws Exception {
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "04/16/2017");
					
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    String newBusDate=new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    xlResults[2][3]= newBusDate;
	    
	    xlResults[2][2]= OriDate;

	    
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		xlResults[2][1]=loanlink;
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
		String DueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[2][5]= DueDate1;
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  //FP due date
		String FPDueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[2][4]= FPDueDate1;
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  //FP due date
		String Late1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[2][6]= Late1;
		
		if(Late1 .equals("Not Late") && FPDueDate1 .equals("04/15/2017 1 days late") && DueDate1 .equals("04/23/2017")){
    
			xlResults[2][7]="PASS";
			
		}
		else{
			
			xlResults[2][7]="FAIL";
		}
		
		Utils.writeXL(OR.getProperty("vLOCRPContractualLate"),"VA_LOC",xlResults);
		Assert.assertEquals(newBusDate, "04/16/2017");
		Assert.assertEquals(DueDate1, "04/23/2017");	
		Assert.assertEquals(Late1, "Not Late");
		Assert.assertEquals(FPDueDate1, "04/15/2017 1 days late");
  }
	
	@Test (priority = 7)
	public void  _7_businessdate_et_contractualduedate() throws Exception {
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "04/23/2017");
					
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    String newBusDate=new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    xlResults[3][3]= newBusDate;
	    
	    xlResults[3][2]= OriDate;
	    
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[3][1]=loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
		String DueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[3][5]= DueDate1;
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  //FP due date
		String FPDueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[3][4]= FPDueDate1;
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  //FP due date
		String Late1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[3][6]= Late1;
		
		if(Late1 .equals("Not Late") && FPDueDate1 .equals("04/15/2017 8 days late") && DueDate1 .equals("04/23/2017")){
    
			xlResults[3][7]="PASS";
			
		}
		else{
			
			xlResults[3][7]="FAIL";
		}

		Utils.writeXL(OR.getProperty("vLOCRPContractualLate"),"VA_LOC",xlResults);		
		Assert.assertEquals(newBusDate, "04/23/2017");
		Assert.assertEquals(DueDate1, "04/23/2017");	
		Assert.assertEquals(Late1, "Not Late");
		Assert.assertEquals(FPDueDate1, "04/15/2017 8 days late");
		
  }
	
	@Test (priority = 8)
	public void  _8_busineesdate_gt_contractualduedate() throws Exception {
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "04/24/2017");
					
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
	    new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		    
	    xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
	    String newBusDate=new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	    
	    xlResults[4][3]= newBusDate;
	    
	    xlResults[4][2]= OriDate;
	    
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[4][1]=loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwDueDate']";  // due date
		String DueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		xlResults[4][5]= DueDate1;
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwFpDueDate']";  //FP due date
		String FPDueDate1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		System.out.println(FPDueDate1);
		xlResults[4][4]= FPDueDate1;
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLate']";  //FP due date
		String Late1 =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();  
		System.out.println(Late1);
		xlResults[4][6]= Late1;
		
		if(Late1 .equals("1 Day Late") && FPDueDate1 .equals("04/15/2017 9 days late") && DueDate1 .equals("04/23/2017")){
    
			xlResults[4][7]="PASS";
			
		}
		else{
			
			xlResults[4][7]="FAIL";
		}

		Utils.writeXL(OR.getProperty("vLOCRPContractualLate"),"VA_LOC",xlResults);
		Assert.assertEquals(newBusDate, "04/24/2017");
		Assert.assertEquals(DueDate1, "04/23/2017");	
		Assert.assertEquals(Late1, "1 Day Late");
		Assert.assertEquals(FPDueDate1, "04/15/2017 9 days late");
  }
	
	@Test (priority = 9)
	public void  _9_admin_logout() throws Exception {
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // Edit button
    	new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();


    	xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // Business Date
    	new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
    	new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.HOME + "03/29/2017");

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Save 
    	new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
         
        xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";  // Business Date
        String newBusDate=new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
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

	
	@Test (priority = 10)
	public void  _10_void_loan() throws Exception {
		
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
	}
	
	@Test (priority = 11)
	public void  _11_teller_logout() throws Exception {
		
		xpath=".//*[@id='menuv2']/li[7]/a";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		Thread.sleep(1500);
		Function_Classes.logout(driver, xpath); // for log out 
		
		Thread.sleep(1000);
		driver.close();	
    	
	}
	
}
	