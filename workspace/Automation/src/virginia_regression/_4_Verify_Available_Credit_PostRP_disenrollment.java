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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_Classes;


public class _4_Verify_Available_Credit_PostRP_disenrollment {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver, driver1;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String URL1 = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int len_Eprincipal,len_AvailCdt;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late;
	String xlCdtlimit , xlDraw,I_xlDraw,I_xlCdtlimit;
	String xpath,EarnedPrincoutput,AvailCdt,AvailCdt1,AvailCdtoutput1,AvailCdtoutput,EPrincipal;
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
public void  _2_admin_login  () throws Exception{
		
	OR = new Properties();
	FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
	OR.load(fn);
	

	xlData = Utils.readXL(OR.getProperty("vpath"),"VA_LOC_credit");
		
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
		
		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
    
	}
	
	@Test (priority = 3)
	public void  _3_store_login () throws Exception{

		System.out.println("Ecash Teller Login   --  Started");
			

		xlResults = Utils.readXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC");
			
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
	@Test (priority = 4)
	public void _4_RapidPay_disenrollment() throws Exception {
	
		System.out.println("Ecash New Loan Creation   --  Started");
		
		xlCdtlimit = xlData [28][11];
		xlDraw = xlData [28][12];
	
		
		Float I_Draw = Float.valueOf(xlDraw);
		DecimalFormat DrawFormatter = new DecimalFormat("####.00");
		I_xlDraw = DrawFormatter.format(I_Draw);
		xlResults[1][3] = I_xlDraw;
		System.out.println( "I_xlDraw: "  +I_xlDraw );
		
		DecimalFormat PrinFormatter = new DecimalFormat("#,###.00");
		EarnedPrincoutput = PrinFormatter.format(I_Draw);
		System.out.println("EarnedPrincoutput: "+EarnedPrincoutput);
		
		Float I_Cdtlimit = Float.valueOf(xlCdtlimit);
		DecimalFormat CdtFormatter = new DecimalFormat("####.00");
		I_xlCdtlimit = CdtFormatter.format(I_Cdtlimit);
		System.out.println("I_xlCdtlimit: "+I_xlCdtlimit);
		xlResults[1][2] = I_xlCdtlimit;
		
		Float I_AvailCdt = I_Cdtlimit - I_Draw ;
		
		System.out.println(I_AvailCdt);
		
		if (I_Cdtlimit .equals(I_Draw)){
			
			AvailCdtoutput= "0.00";
			AvailCdtoutput1= "0.00";
			
		}
		else {
			
			DecimalFormat I_AvailCdtFormatter = new DecimalFormat("#,###.00");
			DecimalFormat I_AvailCdtFormatter1 = new DecimalFormat("####.00");
			AvailCdtoutput = I_AvailCdtFormatter.format(I_AvailCdt); // Avlb Credit : #,###.00
			AvailCdtoutput1 = I_AvailCdtFormatter1.format(I_AvailCdt);// Avlb Credit : ####.00
			System.out.println("AvailCdtoutput: "+AvailCdtoutput);
			System.out.println("AvailCdtoutput1: "+AvailCdtoutput1);
		}

		xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("VALOCRPPAY");
		
		xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanAmount']";  // entering the Loan Amount 
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(xlCdtlimit);

		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount 
		Function_Classes.field_clear(driver, xpath);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtInitialDraw']";  // entering the first draw Amount
		Function_Classes.field_sendKeys(driver, xpath,xlDraw);
				

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Next Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  // Finish Button
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			
		Thread.sleep(1500);
		xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
		loanlink = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		xlResults[1][1] = loanlink;
		System.out.println("loanlink: " +loanlink);
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
		AvailCdt1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span" ; //Rapid Pay
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		if (AvailCdt1 .equals("$0.00 - RP")) {
			
			System.out.println(AvailCdt1);
				
			Thread.sleep(1500);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_flexPay_btnRemoveRapidPay']" ; //Disenroll RP
//			WebElement DisenrollRP = driver.findElement(By.xpath(xpath));

				if(driver.findElements( By.xpath(xpath) ).size() != 0){
			
					xlResults[1][8] = "Button is ENABLED for Tellers- FAILED" ;
					Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);
//					Assert.assertEquals(false, DisenrollRP.isDisplayed());
					
				}	
		
				else {
			
//					xpath = ".//*[@id='Left']/a[5]"; // enter cust search  value
//					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("VALOCRPPAY");
					
					xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					xpath= ".//a[starts-with(@id, 'ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ct')]"; 
					new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
					System.out.println(driver1.findElement(By.xpath(xpath)).getText());
					
					if (xlResults[1][1].equals(driver1.findElement(By.xpath(xpath)).getText())){	
				
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							System.out.println("xlResults[1][1] :" +xlResults[1][1]);
								
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span" ; //Rapid Pay
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_flexPay_btnRemoveRapidPay']" ; //Unenroll RP
							new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
							Thread.sleep(1500);
								
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkFlexPay']/span" ; //Un enroll RP
							WebElement DisenrollRP1 = driver1.findElement(By.xpath(xpath));
				
								if(driver1.findElements( By.xpath(xpath) ).size() != 0){
					
									xlResults[1][8] = "Button is ENABLED for Admins and DISABLED for Tellers - PASSED" ;
										
									xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwLocAvailableCredit']";
									AvailCdt = new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							//			System.out.println("AvailCdt from the eCash: " +AvailCdt);
									String S_AvailCdt=AvailCdt.substring(1);
									len_AvailCdt= S_AvailCdt.length();
										
									xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_vwEarnedPrin']";
									EPrincipal = new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									String S_EPrincipal=EPrincipal.substring(1); 
								//		System.out.println("S_EPrincipal from eCash: "+S_EPrincipal);
								//		Assert.assertEquals(true, DisenrollRP1.isDisplayed());
					
									len_Eprincipal= S_EPrincipal.length();
									System.out.println("len_Eprincipal: "+len_Eprincipal);
														
									if(len_Eprincipal >= 7){
						
										System.out.println("Principal >=  $1,000.00");
												
										System.out.println("AvailCdt from the eCash: " +AvailCdt);
												
										System.out.println("S_EPrincipal from eCash: "+S_EPrincipal);
												
											
										if(len_AvailCdt >= 7){
      
											System.out.println("Available Credit >= $1000.00");
					
												if(AvailCdt .equals("$"+AvailCdtoutput) && EPrincipal.equals("$"+EarnedPrincoutput)){
												
																	System.out.println("PASS");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and Earned Principal:  "+ EPrincipal +" are matching with expected  Available credit: $"+AvailCdtoutput+ " and Principal:  $"+EarnedPrincoutput +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+EarnedPrincoutput;
																	xlResults[1][7] = AvailCdtoutput;
																	xlResults[1][9] = "PASS";
										  			
															}
															else{
																	System.out.println("FAIL");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and Earned Principal:  "+ EPrincipal +" are not matching with expected  Available credit: $"+AvailCdtoutput+ " and Principal:  $"+EarnedPrincoutput +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+EarnedPrincoutput;
																	xlResults[1][7] = AvailCdtoutput;
																	xlResults[1][9] = "FAIL";
															}
														Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);
														Assert.assertEquals(AvailCdt, "$"+AvailCdtoutput);
														Assert.assertEquals(EPrincipal, "$"+EarnedPrincoutput);
													
													}
													else if(len_AvailCdt < 7) {
														
														System.out.println("Available Credit < $1000.00");
													
														
															if(AvailCdt .equals("$"+ AvailCdtoutput1) && EPrincipal.equals("$"+EarnedPrincoutput)){
																									
																	System.out.println("PASS");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and principal:  "+ EPrincipal +" are matching with expected  Available credit: $"+AvailCdtoutput1+ " and Principal:  $"+EarnedPrincoutput +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+EarnedPrincoutput;
																	xlResults[1][7] = AvailCdtoutput1;
																	xlResults[1][9] = "PASS";
															
															}
															else{
																
																	System.out.println("FAIL");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and principal:  "+ EPrincipal +" are not matching with expected  Available credit: $"+AvailCdtoutput1+ " and Principal:  $"+EarnedPrincoutput +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+EarnedPrincoutput;
																	xlResults[1][7] = AvailCdtoutput1;
																	xlResults[1][9] = "FAIL";
													
														}
										
														Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);							
														Assert.assertEquals(AvailCdt, "$"+AvailCdtoutput1);
														Assert.assertEquals(EPrincipal, "$"+EarnedPrincoutput);
													
													}
														
												}
							
	
				
											else if (len_Eprincipal < 7){
		
													System.out.println("Principal <  $1,000.00");
													
													System.out.println("AvailCdt from the eCash: " +AvailCdt);
													
													System.out.println("EPrincipal from eCash: "+ EPrincipal);
										

													if(len_AvailCdt >= 7){
											
														System.out.println("Availble cdt >= $1000.00");
												
															if(AvailCdt .equals("$"+AvailCdtoutput) && EPrincipal.equals("$"+I_xlDraw)){
													
																 	System.out.println("PASS ");
																 	System.out.println("Actual Available credit: "+ AvailCdt +" and Earned Principal:  "+ EPrincipal +" are matching with expected  Available credit: $"+AvailCdtoutput+ " and Principal:  $"+I_xlDraw +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+I_xlDraw;
																	xlResults[1][7] = "$"+AvailCdtoutput;
																	xlResults[1][9] = "PASS";
															
												
															}
															else{
																	System.out.println("FAIL ");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and Earned Principal:  "+ EPrincipal +" are not matching with expected  Available credit: $"+AvailCdtoutput+ " and Principal:  $"+I_xlDraw +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+I_xlDraw;
																	xlResults[1][7] = "$"+AvailCdtoutput;
																	xlResults[1][9] = "FAIL";
															}
													
								   						Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);							
														Assert.assertEquals(AvailCdt, "$"+AvailCdtoutput);
														Assert.assertEquals(EPrincipal, "$"+I_xlDraw);
							   						}
													else if (len_AvailCdt < 7){
											
														System.out.println("Availble cdt < $1000.00");
												
									
															if(AvailCdt .equals("$"+AvailCdtoutput1) && EPrincipal.equals("$"+I_xlDraw)){
																
																	System.out.println("PASS");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and Earned Principal:  "+ EPrincipal +" are matching with expected  Available credit: $"+AvailCdtoutput1+ " and Principal:  $"+I_xlDraw +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+I_xlDraw;
																	xlResults[1][7] = "$"+AvailCdtoutput1;
																	xlResults[1][9] = "PASS";
																							
																}
																else{
																	System.out.println("FAIL");
																	System.out.println("Actual Available credit: "+ AvailCdt +" and Earned Principal:  "+ EPrincipal +" are not matching with expected  Available credit: $"+AvailCdtoutput1+ " and Principal:  $"+I_xlDraw +" for credit limit $"+I_xlCdtlimit );
																	xlResults[1][4] = EPrincipal;
																	xlResults[1][5] = AvailCdt;
																	xlResults[1][6] = "$"+I_xlDraw;
																	xlResults[1][7] = "$"+AvailCdtoutput1;
																	xlResults[1][9] = "FAIL";
																}
							   						
													Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);							
													Assert.assertEquals(AvailCdt, "$"+AvailCdtoutput);
													Assert.assertEquals(EPrincipal, "$"+I_xlDraw);
											}		
										}
									}
							
									else {
									
								
										xlResults[1][8] = "Button is DISABLED for Admins - FAILED" ;
										Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);
										Assert.assertEquals(true, DisenrollRP1.isDisplayed());
						
									}
								}
						else {
							
							xlResults[1][9] = "Loan numbers do not match-check the data";
							System.out.println("Loan numbers do not match");
							Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);
						}
					  }
					}
		
				else{
					
						xlResults[1][9] = "FAIL-Available Credit for rapid is not equal to : $0.00 - RP";
						Utils.writeXL(OR.getProperty("VLOCvlbCdtPostUnenrolpath"),"VA_LOC",xlResults);
						Assert.assertEquals(AvailCdt1,"$0.00 - RP" );
		     }
	    }
	
	@Test (priority = 5)
	public void _5_void_loan() throws Exception {
		
				
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  // loan link
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
			
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
			//new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("Test");
			
	
			xpath=".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			

	} 

	
	@Test (priority = 6)
	public void _6_log_out_admin() throws Exception {
	
			Thread.sleep(2000);
			xpath=".//*[@id='menuv2']/li[9]/a";
	//		new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			Function_Classes.logout(driver1, ".//*[@id='menuv2']/li[9]/a"); // for log out 
			
			Thread.sleep(1000);
			driver1.close();

	}
	
	@Test (priority = 7)
	public void _7_log_out_teller() throws Exception {
	
		

			xpath=".//*[@id='menuv2']/li[7]/a";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			
			Thread.sleep(1000);
			driver.close();

	}
	
}