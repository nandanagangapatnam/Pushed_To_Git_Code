/**
 * 
 */
/**
 * @author Garima
 *
 */
package aml_regression;


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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import Functions.Function_AMLReports;
import Functions.Function_Classes;


public class _1_AML_BusinessReports {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver ;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int len_Eprincipal,len_AvailCdt;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late,xlVoid;
	String xlCdtlimit , xlDraw,I_xlDraw,I_xlCdtlimit;
	String xpath,xpath1,EarnedPrincoutput,AvailCdt,AvailCdt1,AvailCdtoutput1,AvailCdtoutput,EPrincipal;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustName, xlTransaction,xlVerification, xlAggregate,xlCheck,Final_Aggregate;
	String[][] xlData ,Data1;
	String[][] xlResults;
	String xlCashIn,xlCashOut;
	String Data;
	String[] MO_number,Exp_total;
	String[] Type;
	String[] Amount;
	String[] Store;
	String mainWinID,newWinID;
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
	public void  _2_store_login () throws Exception{

		System.out.println("Ecash Teller Login   --  Started");
			
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);
		

		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath,"gsrivastavteller");
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath,"changeme123");
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(1000);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		Function_Classes.field_sendKeys(driver, xpath, "9998 Some Place, TN");

		Thread.sleep(1000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
			
		System.out.println("Ecash Store Login   --  Passed");
		
		
	}
	

	@Test (priority = 5)
	public void _5_ReportsValidation() throws Exception {	
		
		xlData = Utils.readXL(OR.getProperty("amlpath_results"),"AML");	
		Thread.sleep(2000);
		for ( int i = 123 ; i < 129 ; i+=3){	
		
			String xpath,Final_Value,Final_Value1,xlValue,xlValue1,xlConductor,xlCheckDate;
			xlCustName = xlData[i][1];
			System.out.println(xlCustName);
			xlConductor = xlData[i+1][1];
			xlVerification = xlData[i][14];
			System.out.println(xlVerification);
			xlCheckDate = xlData[i][11];
			System.out.println(xlCheckDate);
			xlValue =xlData[i+1][3];
			xlValue1 =xlData[i+2][3];
			
			DecimalFormat Formatter = new DecimalFormat("#,###.00");
			Formatter.setMaximumFractionDigits(2);
			DecimalFormat Formatter1 = new DecimalFormat("###.00");
			Formatter1.setMaximumFractionDigits(2);
			
		
			float I_Value = Float.parseFloat(xlValue) ;
			float I_Value1 = Float.parseFloat(xlValue1) ;
			
				if (I_Value >= 1000 || I_Value1 >=1000){
					
					Final_Value = Formatter.format(I_Value);
					Final_Value1 = Formatter.format(I_Value1);
					
				}
				
				else{
					
					Final_Value = Formatter1.format(I_Value);
					Final_Value1 = Formatter.format(I_Value1);
					
				}
				
				Thread.sleep(1000);
				
				xpath =".//*[@id='Left']/a[8]";  // Reports 
			
				String WinID[] = Function_AMLReports.AMLLogReport(driver,xpath,"3000","1500000",xlCheckDate,xlCheckDate);

		        driver.switchTo().window(WinID[1]);
		        System.out.println(driver.getTitle());
		        
		        Thread.sleep(2000);		
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[3]";
		        String Business = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		        
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td[3]";
		        String Conductor = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		       
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[1]";
		        String Date1 = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td[1]";
		        String Date2 = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
		        if (Business .contains(xlCustName) && Conductor.contains(xlConductor) && Date1 .contains(xlCheckDate) && Date2 .equals("")){
		        
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
		        		xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[1]/td[2]";			
		        		String CashIn= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		        		if(j==2){
			        		Assert.assertEquals( CashIn, "$"+xlData[i+2][6]);	
		        		}
		        		else{
		        			Assert.assertEquals( CashIn, "$0.00");
		        		}
		        	} 		
					
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
						String CashOut= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						
		        		if(j==2){
		        			Assert.assertEquals( CashOut, "$"+xlData[i+1][7]);		
		        		}
		        		else{
		        			Assert.assertEquals( CashOut, "$0.00");
		           		}
		        		
		        	}	
				
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[2]";			
						String MoneyOrders= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						
						if(j==2){
							Assert.assertEquals( MoneyOrders, "$"+Final_Value1);		
		        		}
		        		else{
		        			Assert.assertEquals( MoneyOrders, "$0.00");
		           		}
		        	}	
						
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
		        		for (int k=1 ; k <=8 ; k++ ){
		        		
		        			xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr["+k+"]/td[3]";
				        	String MO= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

				        	if(j==2 && k==3){
				        		Assert.assertEquals( MO.trim(), "1");
				        	}
				        	else{
				        		Assert.assertEquals( MO.trim(), "");
				        	}
		        		}
		        	}	
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
						String Checks= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

						if(j==2){
							Assert.assertEquals( Checks, "$"+Final_Value);
						}
						else{
							Assert.assertEquals( Checks, "$0.00");
						}
		        	}
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[5]/td[2]";			
						String CardLoads= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( CardLoads, "$0.00");
		        	}
		        	
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[6]/td[2]";			
						String Billpay= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Billpay,  "$0.00");
		        	}
		        	
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[7]/td[2]";			
						String Wiretransfersend= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Wiretransfersend,  "$0.00");
		        	}
		        	
		        	for (int j=2 ; j <=3 ; j++ ){
		        		
			        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[8]/td[2]";			
						String Wiretransferreceive= new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Wiretransferreceive,  "$0.00");
		        	}

					
					xlData[i][15] = "PASS";
					
					System.out.println("PASS"+i);
					
								
			        Thread.sleep(2000);
			        driver.close();
		        }
		        else{
		        	
		        	xlData[i][15] = "FAIL";
		        	System.out.println("FAIL"+i);
		        	       	
		        	System.out.println("Check the data for AML"+xlVerification);	
					
			        Thread.sleep(2000);
			        driver.close();
		        	
		        }
		        
		        Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
		        
		        Thread.sleep(1000);
		        driver.switchTo().window(WinID[0]);
		        System.out.println(driver.getTitle());
		        
		  
		     }
		
		
		    Thread.sleep(2000);
		    driver.close();
	}
}	
