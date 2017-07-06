/**
 * 
 */
/**
 * @author Garima
 *
 */
package virginia_regression;

import Utility.Utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_Classes;


public class _5_IncreaseCreditLimit {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late;
	String xlCdtlimit , xlDraw;
	String xpath,EarnedPrincoutput;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustFName, xlCustLName;
	String[][] xlData;
	String[][] xlResults;
	public static Properties OR=null;


	//@BeforeTest	
	@Test (priority = 1)
	public void _1_navigate_to_eCashURL (){		
		System.out.println("Ecash URL  --  Started");
		
	
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver, URL);	
		      //add fail entry to the excel sheet

}
	
	@Test (priority = 2)
	public void  _2_teller_login () throws Exception{

			System.out.println("Ecash Teller Login   --  Started");
			
			OR = new Properties();
			FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
			OR.load(fn);
		

			xlData = Utils.readXL(OR.getProperty("vpath"),"VA_LOC_credit");
	//		xlResults = Utils.readXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC");
			
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
			
		System.out.println("Ecash Store Login   --  Passed");
		
		
	}
	
	
	
	// New Loan Creation  - working fine
	@Test (priority = 3)
	public void _3_newLOCcreation_$100initialCreditLimit() throws Exception {
		
		xlResults=Utils.readXL(OR.getProperty("vIncCdtLmtpath"), "IncreaseCreditLimit");	
		System.out.println("Ecash New LOC Creation   --  Started");
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("LOCINCREASE TEST");
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("100");

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
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_btnHistory']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_dgHistory_ctl05_lblType']";
		
		if (driver.findElements(By.xpath(xpath)).size() !=0){
				String history1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(history1);
				xlResults[1][8]=("FAIL: " +history1 + " is present under Loan history");
				Utils.writeXL(OR.getProperty("vIncCdtLmtpath"),"IncreaseCreditLimit",xlResults);
				WebElement history_link = driver.findElement(By.xpath(xpath));
				Assert.assertEquals(false, history_link.isDisplayed());

			}
		else{
			xlResults[1][8]=("PASS: Credit Line Adjustment is not present under Loan history");
		}
	}
	
	@Test (priority = 4)
	public void _4_increaseCreditLimittoMaximumAmount() throws Exception {
	
	//	xlResults=Utils.readXL(OR.getProperty("vIncCdtLmtpath"), "IncreaseCreditLimit");
		
		String formattedCdtlimit;
		String sValue;
		
		Thread.sleep(1500);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][1]=loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocIncreaseLine']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";
		String Cdtlimit = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		Assert.assertEquals(Cdtlimit,"$100.00");
		xlResults[1][2]= Cdtlimit ;
		Assert.assertEquals(Cdtlimit,"$100.00");
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
		String EPrincipal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		Assert.assertEquals(EPrincipal,"$100.00");
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocIncreaseLine']/span";
	//	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		WebElement IncreaseCreditLimitButton = driver.findElement(By.xpath(xpath));
		Assert.assertTrue(IncreaseCreditLimitButton.isDisplayed());
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Select NewCdtLimit = new Select(driver.findElement(By.xpath(xpath)));
		List <WebElement> elementCount = NewCdtLimit.getOptions();
		int iSize = elementCount.size();
		System.out.println(iSize);
		
		for(int i =0 ,j=2,k=1; i < iSize ; i++,j++,k++){
			sValue = elementCount.get(i).getText();
			int len_Cdtlimit = xlData[j][11].length();
			
			if (len_Cdtlimit <= 3){
				
				Float I_CdtLimit = Float.valueOf(xlData[j][11]);
				DecimalFormat DrawFormatter = new DecimalFormat("###.00");
				formattedCdtlimit = DrawFormatter.format(I_CdtLimit);
			}
			else{
				Float I_CdtLimit = Float.valueOf(xlData[j][11]);
				DecimalFormat DrawFormatter = new DecimalFormat("#,###.00");
				formattedCdtlimit = DrawFormatter.format(I_CdtLimit);

			}
			xlResults[k][1]=loanlink;
			xlResults[k][3]=formattedCdtlimit;
			xlResults[k][4]=sValue;
			Assert.assertEquals(sValue,formattedCdtlimit);

	}
		
		NewCdtLimit.selectByIndex(iSize-1); //select the maximum credit limit i.e $5000 
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnLimit_Submit']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		Thread.sleep(3000);
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";
		String NewCdtlimitPostIncrease = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][5]="$5,000.00";
		xlResults[1][6]=NewCdtlimitPostIncrease;
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_btnHistory']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_dgHistory_ctl05_lblType']";
		String history = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();


		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocIncreaseLine']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Select NewCdtLimit1 = new Select(driver.findElement(By.xpath(xpath)));
		List <WebElement> elementCount1 = NewCdtLimit1.getOptions();
		int iSize1 = elementCount1.size();
		if (iSize1 == 0){
				
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnLimit_Submit']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']/div[3]/div[2]/ul/li";
			String Msg=new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
				if(NewCdtlimitPostIncrease.equals("$5,000.00") && history.equals("Credit Line Adjustment") && Msg.contains("Fee amount for 0.00 Not found")){
					xlResults[1][7]="PASS" ;
					xlResults[1][9]="PASS: " +history+ "is present under Loan History" ;
				}
				else{
					xlResults[1][7]="FAIL";
					xlResults[1][9]="FAIL: " +history+ "is present under Loan History";
				}
				Utils.writeXL(OR.getProperty("vIncCdtLmtpath"),"IncreaseCreditLimit",xlResults);	
				Assert.assertEquals(iSize1,0);
				Assert.assertEquals(NewCdtlimitPostIncrease,"$5,000.00");
				Assert.assertEquals(history,"Credit Line Adjustment");
				Assert.assertEquals(Msg,"Fee amount for 0.00 Not found");
			}
		
		else{
				xlResults[1][7]="FAIL ";
				xlResults[1][9]="FAIL - Dropdown for Credit Limit should not have any Amount";
				Utils.writeXL(OR.getProperty("vIncCdtLmtpath"),"IncreaseCreditLimit",xlResults);
		}

	}	
		
	@Test (priority = 5)
	public void _5_void_loan() throws Exception {
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		

	}
	
	@Test (priority = 6)
	public void _6_log_out() throws Exception {
	
/*			xpath=".//*[@id='menuv2']/li[7]/a";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1500);
			Function_Classes.logout(driver, xpath); // for log out */
			
			Thread.sleep(1000);
			driver.close();

	}
	
}