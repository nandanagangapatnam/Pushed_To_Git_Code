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


public class _6_DecreaseCreditLimit {
	
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
public void _1_navigate_to_eCashteller (){		
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
			
		System.out.println("Ecash Teller Login   --  Passed");
		
		
	}
	
	
	// New Loan Creation  - working fine
@Test (priority = 3)
public void _3_newLOCcreation_$5000initialCreditLimit() throws Exception {
		
			
		System.out.println("Ecash New LOC Creation   --  Started");
		
		xlResults=Utils.readXL(OR.getProperty("vDecCdtLmtpath"), "DecreaseCreditLimit");
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("LOCDECREASE TEST");
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
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
		Function_Classes.field_sendKeys(driver, xpath,"5000");
				

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
	//	Thread.sleep(1000);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][1]=loanlink;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		System.out.println(loanlink);
		
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocDecreaseLine']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();//click on Decrease Credit Button
		
			if(driver.findElements( By.id("ctl00_ctl00_MainContent_ChildContent1_btnLimit_Submit") ).size() != 0){

		
				xlResults[1][7] ="Button is ENABLED for Tellers- FAILED" ;
					
			}
		
			else{
		
				xlResults[1][7] ="Button is DISABLED for Tellers- PASSED" ;
			}

		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_btnHistory']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_dgHistory_ctl05_lblType']";
			
		if (driver.findElements(By.xpath(xpath)).size() !=0){
				String history1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				System.out.println(history1);
				xlResults[1][10]=("FAIL: " +history1 + " is present under Loan history");
				Utils.writeXL(OR.getProperty("vIncCdtLmtpath"),"IncreaseCreditLimit",xlResults);
				WebElement history_link = driver.findElement(By.xpath(xpath));
				Assert.assertEquals(false, history_link.isDisplayed());

			}
		else{
				xlResults[1][10]=("PASS: Credit Line Adjustment is not present under Loan history");
			}
		Thread.sleep(1000);
		Utils.writeXL(OR.getProperty("vDecCdtLmtpath"),"DecreaseCreditLimit",xlResults);
	}

@Test (priority = 4)
public void _4_log_out_teller() throws Exception {
	
	//	Thread.sleep(1000);
		xpath=".//*[@id='menuv2']/li[7]/a";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		Thread.sleep(2000);
		Function_Classes.logout(driver, xpath); // for log out 
			
		Thread.sleep(1000);
		driver.close();

	} 

@Test (priority = 5)
public void _5_navigate_to_eCash_as_Admin (){		
		System.out.println("Ecash URL  --  Started");
		
	
		driver = new FirefoxDriver(); //	firefox driver
		driver.manage().window().maximize();	
		 
		Function_Classes.navigate_to(driver, URL);	
		      //add fail entry to the excel sheet

}

@Test (priority = 6)
public void _6_admin_login () throws Exception{		
	
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver,xpath, "dateadm");
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath,"changeme");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		System.out.println("Ecash Store Login   --  Passed");
		
}
	
@Test (priority = 7)
public void _7_decreaseCreditLimittoMinimumAmount() throws Exception {
	
		xlResults=Utils.readXL(OR.getProperty("vDecCdtLmtpath"), "DecreaseCreditLimit");
		
		String formattedCdtlimit;
		String sValue;
		
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtLoanId']"; // enter loan  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlResults[1][1]);
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		
		xpath= ".//a[starts-with(@id, 'ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ct')]"; 
		if (xlResults[1][1].equals(driver.findElement(By.xpath(xpath)).getText())){	
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			System.out.println(xlResults[1][1]);
				
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocDecreaseLine']/span";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();//click on Decrease Credit Button
			
				if(driver.findElements( By.id("ctl00_ctl00_MainContent_ChildContent1_btnLimit_Submit") ).size() != 0){
				
					xlResults[1][8]="Button is ENABLED for Admins- PASSED" ;
				
							
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";
					String Cdtlimit = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					xlResults[1][2]= Cdtlimit;
					Assert.assertEquals(Cdtlimit,"$5,000.00");
			
					
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
					String EPrincipal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					Assert.assertEquals(EPrincipal,"$5,000.00");
					
					
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLimitNewAmount']";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select NewCdtLimit = new Select(driver.findElement(By.xpath(xpath)));
					List <WebElement> elementCount = NewCdtLimit.getOptions();
					int iSize = elementCount.size();
					Assert.assertNotEquals(iSize,0);
					System.out.println(iSize);
					
					for(int i =0 ,j=1,k=1; i < iSize ; i++,j++,k++){
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
	//					System.out.println("Actual: " +formattedCdtlimit);
	//					System.out.println("Expected: " +sValue);
						xlResults[k][1]=loanlink;
						xlResults[k][3]=formattedCdtlimit;
						xlResults[k][4]=sValue;
						Assert.assertEquals(sValue,formattedCdtlimit);
		
					}
					NewCdtLimit.selectByIndex(0); //select the minimum credit limit ie $100 
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnLimit_Submit']";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(3000);
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocCreditLimit']";
					String NewCdtlimitPostDecrease = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					xlResults[1][5]="$100.00";
					xlResults[1][6]=NewCdtlimitPostDecrease;
					
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_btnHistory']";
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_dgHistory_ctl05_lblType']";
					String history = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			
			
					xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkLocDecreaseLine']/span";
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
						System.out.println(Msg);
					
							if(NewCdtlimitPostDecrease.equals("$100.00") && history.equals("Credit Line Adjustment") && Msg.contains("Fee amount for 0.00 Not found")){
								xlResults[1][9]="PASS";
								xlResults[1][11]="PASS: " +history+ "is present under Loan History" ;
							}
							else{
								xlResults[1][9]="FAIL";
								xlResults[1][11]="FAIL: " +history+ " is present under Loan History" ;
							}
						Utils.writeXL(OR.getProperty("vDecCdtLmtpath"),"DecreaseCreditLimit",xlResults);
						Assert.assertEquals(Msg,"Fee amount for 0.00 Not found");
						Assert.assertEquals(iSize1,0);
						Assert.assertEquals(NewCdtlimitPostDecrease,"$100.00");
						Assert.assertEquals(history,"Credit Line Adjustment");
					}
					else{
						xlResults[1][9]="FAIL - Dropdown for Credit Limit should not have any Amount ";
						Utils.writeXL(OR.getProperty("vDecCdtLmtpath"),"DecreaseCreditLimit",xlResults);
					}
					
	
				}
				else{
					
					xlResults[1][8]="FAIL -Button is not DISABLED for Admins";
				}
			}
		else {
			System.out.println("Enter the correct Loan id");

		}
	}	
		
@Test (priority = 8)
public void _8_void_loan() throws Exception {
	
	
	
	Thread.sleep(1000);	
	xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
	
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlStoreDrawer']";  // open drawer
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))) ;
		
		Select oSelect = new Select(driver.findElement(By.xpath(xpath)));
		oSelect.selectByVisibleText("Garima Srivastav - Drawer 3");
				
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");
	
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();		
}
	
@Test (priority = 9)
public void _9_log_out() throws Exception {
	
/*		xpath=".//*[@id='menuv2']/li[9]/a";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		Thread.sleep(1500);
		Function_Classes.logout(driver, xpath); // for log out */
			
		Thread.sleep(1000);
		driver.close();

	}
}