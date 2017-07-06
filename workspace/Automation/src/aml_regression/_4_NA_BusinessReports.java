/**
 * 
 */
/**
 * @author Garima
 *
 */
package aml_regression;


import Utility.Utils;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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


public class _4_NA_BusinessReports {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver ;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, word, LoggedName;
	int len_Eprincipal,len_AvailCdt;
	String loanlink,Status,DueDate,FPDueDate,BusDate,Late,xlCheckDate,xlVoid;
	String xlCdtlimit , xlDraw,I_xlDraw,I_xlCdtlimit;
	String xpath,xpath1,EarnedPrincoutput,AvailCdt,AvailCdt1,AvailCdtoutput1,AvailCdtoutput,EPrincipal;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustName, xlTransaction,xlVerification, xlNotes;
	String[][] xlData ;
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
	

		
		@Test (priority = 4)
		public void _4_NAReportsValidation() throws Exception {	
		
				xlData = Utils.readXL(OR.getProperty("amlpath_results"),"AML");	
				Thread.sleep(2000);
				
					
				Thread.sleep(1000);
				xlCheckDate=xlData[3][11];
				xpath =".//*[@id='Left']/a[8]";  // Reports 

				String WinID[] = Function_AMLReports.CTRLogReport(driver,xpath,"3000","1500000",xlCheckDate,xlCheckDate);

		        driver.switchTo().window(WinID[1]);
		        System.out.println(driver.getTitle());
   		
				for (int i = 1; i < 194; i++){
					
					xlCustName = xlData[i][1];
					xlVerification = xlData[i][14];
					xlNotes=xlData[i][9];
					
					
					if(xlVerification.equals("N/A") && xlNotes .equals("Yes")){

      
						if(driver.getPageSource().contains(xlCustName)){
							
								xlData[i][16] = "PASS";
								System.out.println("PASS"+i);
						}	    	
						else {
								
								xlData[i][16] = "FAIL";
								System.out.println("FAIL"+i);
						}
						
						Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
					
					}
					
					else if(xlVerification.equals("N/A") && xlNotes .equals("No")){

					      
						if(driver.getPageSource().contains(xlCustName)){
							
								xlData[i][16] = "FAIL";
								System.out.println("FAIL"+i);
						}	    	
						else {
								
								xlData[i][16] = "PASS";
								System.out.println("PASS"+i);
						}
						
						Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
					
					}
			    }
				
				Thread.sleep(2000);
				driver.close();
				      
				driver.switchTo().window(WinID[0]);
		        System.out.println(driver.getTitle());
			        
				Thread.sleep(2000);
				driver.close();
	}
}	


