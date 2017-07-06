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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import Functions.Function_AMLReports;
import Functions.Function_Classes;


public class _2_CTR_BusinessReports {
	
	String vBrowser = "FireFox";
	//set driver path 
	
 
	WebDriver driver ;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore,  xlCustName, xlTransaction,xlVerification, xlAggregate,xlCheck,Final_Aggregate;
	String[][] xlData ,xlResults;

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
	public void _3_CTR_ReportsValidation() throws Exception {	
		
		xlData = Utils.readXL(OR.getProperty("amlpath_results"),"AML");	
		Thread.sleep(2000);
		


		DecimalFormat Formatter = new DecimalFormat("#,###.00");
		Formatter.setMaximumFractionDigits(2);
		DecimalFormat Formatter1 = new DecimalFormat("###.00");
		Formatter1.setMaximumFractionDigits(2);
	
		for ( int i = 129 ; i <= 132 ; i+=2){	
			
			String Final_Value,xlValue,xlConductor,xlCheckDate;
			xlCustName = xlData[i][1];
			xlConductor = xlData[i+1][1];
			xlVerification = xlData[i][14];
			xlCheckDate = xlData[i][11];
			xlValue = xlData[i+1][3];
					
	
			float I_Value = Float.parseFloat(xlValue) ;

			if (I_Value >= 1000 ){
					
				Final_Value = Formatter.format(I_Value);
				
			}
				
			else{
					
				Final_Value = Formatter1.format(I_Value);
				
			}
				
				Thread.sleep(1000);
				xpath =".//*[@id='Left']/a[8]";  // Reports 
				
				String WinID[] = Function_AMLReports.CTRLogReport(driver,xpath,"10000","1500000",xlCheckDate,xlCheckDate);

		        driver.switchTo().window(WinID[1]);
		        
		        Thread.sleep(2000);		
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[3]";
		        String Business = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		        
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[1]";
		        String Date1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		    
		        
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[1]";
		        String Date2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		     
		        
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[2]";
		        String BusConductor = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

		        
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[3]";
		        String Customer = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				    
		      
		        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[2]";
		        String Conductor_Details = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		     		       
		        if (Business .contains(xlCustName) && BusConductor.contains(xlConductor) && Conductor_Details.contains(xlConductor) && Date2 .contains(xlCheckDate) && Customer .contains(xlConductor)){//&& Date1 .contains(xlCheckDate)){
		        	
		        	for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[1]/td[2]";			
						String CashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( CashIn, "$0.00");	
		        	}

		        	for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
						String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						
						if(j==2){
							Assert.assertEquals( CashOut, "$"+xlData[i+1][7]);
						}
						else{
							Assert.assertEquals( CashOut, "$0.00");
						}
		        	}

				
		        	for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[2]";			
						String MoneyOrders= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( MoneyOrders,"$0.00");
		        	}

		        	for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[3]";
						String MO= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( MO.trim(), "");
		        	}
		        	

		        	for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
						String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						
						if(j==2){
							Assert.assertEquals( Checks, "$"+Final_Value);
						}
						else{
							Assert.assertEquals( Checks, "$0.00");
						}
		        	}

			        for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[5]/td[2]";			
						String CardLoads= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( CardLoads, "$0.00");
			        }
			        
			        for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[6]/td[2]";			
						String Billpay= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Billpay,  "$0.00");
			        }
			        
			        for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[7]/td[2]";			
						String Wiretransfersend= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Wiretransfersend,  "$0.00");
			        }
			        
			        for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[8]/td[2]";			
						String Wiretransferreceive= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Wiretransferreceive,  "$0.00");
			        }
			        
			        for (int j=2 ; j <=5 ; j+=3 ){
						xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[9]/td[2]";			
						String Other= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals( Other, "$0.00");	
			        }	

					
					xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[8]";			
					String CTR_CashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					Assert.assertEquals( CTR_CashIn, "$"+xlData[i+1][6]);	
					
					xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[3]/td[8]";			
					String CTR_CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					Assert.assertEquals( CTR_CashOut, "$"+xlData[i+1][7]);	
					
					xlData[i][16] = "PASS";
					
					 System.out.println("PASS"+i);
		        }
		        else{
		        	
					xlData[i][16] = "FAIL";
					
				
					System.out.println("FAIL"+i);
		        	
		        	System.out.println("Check the data for CTR Construction Business ");	
		        }
		        
		        Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
				
		        Thread.sleep(2000);
		        driver.close();
		        driver.switchTo().window(WinID[0]);
       
		       
	     }
		
		for ( int i = 133 ; i <= 138 ; i+=3){
				
			String xlValue,xlValue1,xlValue2,xlValue3,Final_Total,Check_Total,xlConductor,xlConductor1,xlCheckDate;
			xlCustName = xlData[i][1];
			xlConductor = xlData[i+1][1];
			xlConductor1 = xlData[i+2][1];
			xlVerification = xlData[i][14];
			xlCheckDate = xlData[i][11];
			xlValue =  xlData[i+1][3];
			xlValue1 = xlData[i+2][3];
			xlValue2 = xlData[i+1][7];
			xlValue3 = xlData[i+2][7];
				
		
			float I_Value = Float.parseFloat(xlValue) ;
			float I_Value1 = Float.parseFloat(xlValue1) ;

			float I_Total =  I_Value + I_Value1;
			float C_Total = (float)(I_Total - (.05 * I_Total));
	
				
			if ( C_Total >= 1000 || I_Total >=1000){
						
				Final_Total = Formatter.format(I_Total);
				Check_Total = Formatter.format(C_Total);
	
			}
			else{
						
				Final_Total = Formatter1.format(I_Total);
				Check_Total = Formatter1.format(C_Total);
						
			}
					
			Thread.sleep(1000);
			xpath =".//*[@id='Left']/a[8]";  // Reports 
			
			String WinID1[] = Function_AMLReports.CTRLogReport(driver,xpath,"10000","1500000",xlCheckDate,xlCheckDate);
			driver.switchTo().window(WinID1[1]);
		
			        
		   Thread.sleep(2000);		
		   xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[3]";
		   String Business = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[1]";
	        String FirstCondDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			        
	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[4]/td[1]";
	        String SecondCondDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[1]"; //Individual1
	        String Date1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[8]/td[1]"; //Individual2
	        String Date2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[2]";
	        String BusConductor1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[4]/td[2]";
	        String BusConductor2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[3]";
	        String FirstCustomer = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[8]/td[3]";
	        String SecondCustomer = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

	        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[2]";
	        String Conductor_Details = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		        
			    			       
	        if (Business .contains(xlCustName) && BusConductor1.contains(xlConductor) && BusConductor2.contains(xlConductor1) && Conductor_Details.contains(xlConductor) && 
	        		Conductor_Details.contains(xlConductor1)/*  && FirstCondDate .contains(xlCheckDate) && 
	        		SecondCondDate .contains(xlCheckDate)*/ && Date1 .equals("") && Date2 .equals("") && (FirstCustomer .contains(xlConductor) || FirstCustomer .contains(xlConductor1))  && 
	        		(SecondCustomer .contains(xlConductor) || SecondCustomer .contains(xlConductor1)) ){
			        	
						for( int j=3 ; j<=6 ; j+=3){
							if(j==3){
					        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr["+j+"]/td[8]";			
								String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								Assert.assertEquals( IndvCashOut, "$"+xlValue2);
							}
							if(j==6){
					        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr["+j+"]/td[8]";		
								String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								Assert.assertEquals( IndvCashOut, "$"+xlValue3);
							}	
							
						}
			
						
						for( int j=2 ; j<=8 ; j+=3){
				        	
				        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[1]/td[2]";			
							String CashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( CashIn, "$0.00");	
						}
			
			        	
						for( int j=2 ; j<=8 ; j+=3){
							
							if(j==2){
								xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
								String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								Assert.assertEquals( CashOut, "$"+Check_Total);	
							}
							else{
								xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
								String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								Assert.assertEquals( CashOut, "$0.00");
							}
						}	
						
						for( int j=2 ; j<=8 ; j+=3){
					
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[2]";			
							String MoneyOrders= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( MoneyOrders,"$0.00");
						}
						
						for( int j=2 ; j<=8 ; j+=3){
							
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[3]";
							String MO= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( MO.trim(), "");
							
						}
						
						for( int j=2 ; j<=8 ; j+=3){
							
							if(j==2){
								xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
								String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								Assert.assertEquals( Checks, "$"+Final_Total);	
							}
							else{
								xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
								String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
								Assert.assertEquals( CashOut, "$0.00");
							}
						}	
						for( int j=2 ; j<=8 ; j+=3){
						
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[5]/td[2]";			
							String CardLoads= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( CardLoads, "$0.00");
						
						}	
					
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[6]/td[2]";			
							String Billpay= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Billpay,  "$0.00");
						
						}	
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[7]/td[2]";			
							String Wiretransfersend= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Wiretransfersend,  "$0.00");
						
						}	
						
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[8]/td[2]";			
							String Wiretransferreceive= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Wiretransferreceive,  "$0.00");
						
						}	
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[9]/td[2]";			
							String Other= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Other,  "$0.00");
						
						}	

						
						xlData[i][16] = "PASS";
						
						System.out.println("PASS"+i);
			  }
			  else{
			        	
					xlData[i][16] = "FAIL";
						
					System.out.println("FAIL"+i);
			       	System.out.println("Check the data for"+xlVerification);	
			  }
			       
			   Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
			        
		       Thread.sleep(2000);
		       driver.close();
		       driver.switchTo().window(WinID1[0]);
		}
			
			for ( int i = 139 ; i <= 146 ; i+=4){
				
				String xlCustName,xlCustName1,xlValue,xlValue1,xlValue2,xlValue3,Final_Value,Final_Value1,xlConductor,xlCheckDate;
				String S_Business2 = "";
				xlCustName = xlData[i][1];
				xlCustName1 = xlData[i+2][1];
				xlConductor = xlData[i+1][1];
				xlVerification = xlData[i][14];
				xlCheckDate = xlData[i][11];
				xlValue =  xlData[i+1][3];
				xlValue1 = xlData[i+3][3];
				xlValue2 = xlData[i+1][7];
				xlValue3 = xlData[i+3][7];
				
	
			
				float I_Value = Float.parseFloat(xlValue) ;
					float I_Value1 = Float.parseFloat(xlValue1) ;

	
				
					if (I_Value >= 1000 || I_Value1 >= 1000 ){
						

						Final_Value = Formatter.format(I_Value);
						Final_Value1 = Formatter.format(I_Value1);
	
						
					}
					
					else{
						

						Final_Value = Formatter1.format(I_Value);
						Final_Value1 = Formatter1.format(I_Value1);;
						
					}
					
					Thread.sleep(1000);
					xpath =".//*[@id='Left']/a[8]";  // Reports 
					
					String WinID2[] = Function_AMLReports.CTRLogReport(driver,xpath,"10000","1500000",xlCheckDate,xlCheckDate);
	
			        driver.switchTo().window(WinID2[1]);
		//	        System.out.println(driver.getTitle());
			        
			        Thread.sleep(2000);		
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[3]";
			        String Business1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[3]";
			        String Business2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			        if (Business2 .contains("My least favorite business")){
			        	 S_Business2 = Business2.substring(0,26); 
			        }	
			        else if (Business2 .contains("My Favorite Business")){
			        	 S_Business2 = Business2.substring(0,20);
			        	 System.out.println( S_Business2 );
			        }
			        else if (Business2 .contains("Construction Business")){
			        	 S_Business2 = Business2.substring(0,21);
			        }
			        else if (Business2 .contains("Another Business")){
			        	 S_Business2 = Business2.substring(0,16);
			        }
			        

			                
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[1]";
			        String FirstCondDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	        System.out.println(FirstCondDate);
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[1]/td[1]";
			        String SecondCondDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	        System.out.println(SecondCondDate);
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[2]";
			        String BusConductor1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	        System.out.println(BusConductor1);
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[1]/td[2]";
			        String BusConductor2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	        System.out.println(BusConductor2);

			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[8]/td[3]";
			        String Customer = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	        System.out.println(Customer);
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[2]";
			        String Conductor_Details1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			 //       System.out.println(Conductor_Details1);
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[2]";
			        String Conductor_Details2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		//	        System.out.println(Conductor_Details2);
	
			        
			       
			        if (Conductor_Details1.contains(xlConductor) && Conductor_Details2.contains(xlConductor)&& Customer .contains(xlConductor) && 
			        	BusConductor1.contains(xlConductor) && BusConductor2.contains(xlConductor)/* && FirstCondDate .contains(xlCheckDate) && SecondCondDate .contains(xlCheckDate)*/){

			        	if (Business1 .contains(xlCustName)){
			        		for( int j=3 ; j<=6 ; j+=3){
			        	
			        			if(j==3){
						        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[3]/td[8]";			
									String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( IndvCashOut, "$"+xlValue2);
					        	
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[2]/td[8]";			
									String IndvCashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( IndvCashIn, "$0.00");
			        			}
								if(j==6){
						        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[3]/td[8]";		
									String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( IndvCashOut, "$"+xlValue3);
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[2]/td[8]";			
									String IndvCashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( IndvCashIn, "$0.00");
								}	
								
			        		}
			        		
							for( int j=2 ; j<=8 ; j+=3){
					        	
								if(j==2){
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";						
									String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( CashOut, "$"+xlValue2);
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
									String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( Checks, "$"+Final_Value);	
								}
								if(j==5){
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";				
									String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( CashOut, "$"+xlValue3);
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
									String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( Checks, "$"+Final_Value1);
									
								}	
								
								if(j==8){
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";				
									String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( CashOut, "$0.00");
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
									String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( Checks, "$0.00");
									
								}
								

							}

							Assert.assertEquals(S_Business2 , xlCustName1);
			        	}		
			        	else if (Business1 .contains(xlCustName1)){
				        		for( int j=3 ; j<=6 ; j+=3){
				        	
				        			if(j==3){
							        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[3]/td[8]";			
										String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( IndvCashOut, "$"+xlValue3);
						        	
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[2]/td[8]";			
										String IndvCashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( IndvCashIn, "$0.00");
				        			}
									if(j==6){
							        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[3]/td[8]";		
										String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( IndvCashOut, "$"+xlValue2);
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[2]/td[8]";			
										String IndvCashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( IndvCashIn, "$0.00");
									}	
										
				        		}
						
				        		for( int j=2 ; j<=8 ; j+=3){
						        	
									if(j==2){
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";						
										String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( CashOut, "$"+xlValue3);
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
										String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( Checks, "$"+Final_Value1);	
									}
									if(j==5){
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";				
										String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( CashOut, "$"+xlValue2);
										
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
										String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( Checks, "$"+Final_Value);	
										
									}	
									
									if(j==8){
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";				
										String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( CashOut, "$0.00");
										
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
										String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( Checks, "$0.00");	
										
									}

				        		Assert.assertEquals(S_Business2 , xlCustName);
				        	}	
			        	}
						for( int j=2 ; j<=8 ; j+=3){
							
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[1]/td[2]";			
							String CashIn= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( CashIn,"$0.00");

						}

						
						for( int j=2 ; j<=8 ; j+=3){
							
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[2]";			
							String MoneyOrders= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( MoneyOrders,"$0.00");
						}

						for( int j=2 ; j<=8 ; j+=3){
							
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[3]/td[3]";
							String MO= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( MO.trim(), "");
							
						}
						
					
						for( int j=2 ; j<=8 ; j+=3){
						
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[5]/td[2]";			
							String CardLoads= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( CardLoads, "$0.00");
						
						}	
					
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[6]/td[2]";			
							String Billpay= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Billpay,  "$0.00");
						
						}	
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[7]/td[2]";			
							String Wiretransfersend= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Wiretransfersend,  "$0.00");
						
						}	
						
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[8]/td[2]";			
							String Wiretransferreceive= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Wiretransferreceive,  "$0.00");
						
						}	
						for( int j=2 ; j<=8 ; j+=3){
							xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[9]/td[2]";			
							String Other= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
							Assert.assertEquals( Other,  "$0.00");
						
						}	
						
						
						xlData[i][16] = "PASS";
						System.out.println("PASS"+i);
			        }
			        else{
			        	
						xlData[i][16] = "FAIL";
						
						System.out.println("FAIL"+i);		   
			        	System.out.println("Check the data for"+Business1 +Business2);	
			        }
			        	
			        	Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);		
		
			        	Thread.sleep(2000);
			        	driver.close();
			        	
			        	Thread.sleep(1000);
			        	driver.switchTo().window(WinID2[0]);

			     }
			
		for ( int i = 155 ; i <= 174 ; i+=5){
				
				String C_Total,Final_Value3;
				String xlCustName,xlCustName1,C_xlValue1,C_xlValue2,C_xlValue3,Total,xlValue1,xlValue2,xlValue3,xlConductor1,xlConductor2,xlCheckDate;
				String S_Business1 = "";
				xlCustName = xlData[i][1];
				xlCustName1 = xlData[i+3][1];
				xlConductor1 = xlData[i+1][1];
				xlConductor2 = xlData[i+2][1];
				xlVerification = xlData[i][14];
				xlCheckDate = xlData[i][11];
				xlValue1 =  xlData[i+1][3];
				xlValue2 = xlData[i+2][3];
				xlValue3 = xlData[i+4][3];
				C_xlValue1=xlData[i+1][7];
				C_xlValue2 = xlData[i+2][7];
				C_xlValue3 = xlData[i+4][7];

			
					float I_Value1 = Float.parseFloat(xlValue1) ;
					float I_Value2 = Float.parseFloat(xlValue2) ;
					float Sum = I_Value1 + I_Value2;
					float Check_total =  (float) (Sum - (0.05 * Sum)) ;
					float I_Value3 = Float.parseFloat(xlValue3) ;
						
					if (Sum >= 1000 || Check_total >= 1000 || I_Value3 >= 1000 ){
						

						Final_Value3 = Formatter.format(I_Value3);
						Total = Formatter.format(Sum);
						C_Total = Formatter.format(Check_total);
	
						
					}
					
					else{
						

						Final_Value3 = Formatter1.format(I_Value3);
						Total = Formatter1.format(Sum);
						C_Total = Formatter1.format(Check_total);
						
					}
					
					Thread.sleep(2000);
					xpath =".//*[@id='Left']/a[8]";  // Reports
					
					String WinID3[] = Function_AMLReports.CTRLogReport(driver,xpath,"10000","1500000",xlCheckDate,xlCheckDate);
	
			        driver.switchTo().window(WinID3[1]);
		        
			        Thread.sleep(3000);	              
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[2]";
			        String Conductor_Details1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[2]";
			        String Conductor_Details2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[2]/td[3]";
			        String Business1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        if (Business1 .contains("My least favorite business")){
			        	 S_Business1 = Business1.substring(0,26); 
			        }	

			        else if (Business1 .contains("Construction Business")){
			        	 S_Business1 = Business1.substring(0,21);
			        }
			        else if (Business1 .contains("My Favorite Business")){
			        	 S_Business1 = Business1.substring(0,20);
			        }
			        else if (Business1 .contains("Another Business")){
			        	 S_Business1 = Business1.substring(0,16);
			        }
			        
			        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[5]/td[3]";
			        String Business2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
			        
			        if (Business2 .contains(xlCustName)){
			        	
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[1]";
				        String FirstCondDate   = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[2]";
				        String BusConductor1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[1]/td[1]";
				        String SecondCondDate  = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[1]/td[2]";
				        String BusConductor2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[4]/td[1]";
				        String ThirdCondDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[4]/td[2]";
				        String BusConductor3 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

			        	
			        		if ( BusConductor1.contains(xlConductor2)&&  BusConductor2.contains(xlConductor1)&& BusConductor3 .contains(xlConductor2)  
			        			/* FirstCondDate .contains(xlCheckDate) && SecondCondDate .contains(xlCheckDate)&& ThirdCondDate .contains(xlCheckDate) */ &&
			        			 Conductor_Details1 .contains(xlConductor2) && Conductor_Details2 .contains(xlConductor1) && Conductor_Details2 .contains(xlConductor2) ){
			        			
			        			
			        			
			        			for( int j=2 ; j<=11 ; j+=3){
			        				
			        				if( j==2){
			        				
							        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
										String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( CashOut, "$"+C_xlValue3);
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
										String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( Checks, "$"+Final_Value3 );	

	
			        				}
			        				
			        				else if( j==5){
				        				
							        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
										String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( CashOut, "$"+C_Total);
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
										String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( Checks, "$"+Total );	
	
			        				}
			        				
			        				else {
				        				
							        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[2]/td[2]";			
										String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( CashOut, "$0.00");
										
										
										xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
										String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
										Assert.assertEquals( Checks, "$0.00" );	
	
			        				}

			        			}
			        			
								
			        			for( int j=3 ; j<=6 ; j+=3){
			        	
	        				
			        				if (j==3){
			        					
							        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[3]/td[8]";			
											String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
											Assert.assertEquals( IndvCashOut,  "$"+C_xlValue3);

			        				}
			        				else {
			        					
			        					for (int k=3 ; k<=6 ; k+=3){

			        						if (k==3){
					        					xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr["+k+"]/td[8]";			
												String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
												Assert.assertEquals( IndvCashOut, "$"+C_xlValue1);
			        						}
			        						if (k==6){
					        					xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr["+k+"]/td[8]";			
												String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
												Assert.assertEquals( IndvCashOut, "$"+C_xlValue2);
			        						}
			        					}	
		        					}
			        			}
								
								xlData[i][16] = "PASS";
								System.out.println("PASS" +i);	

			        		}
			        		
			        		else{
			        			
								xlData[i][16] = "FAIL";
								System.out.println("FAIL" +i);
			   
								
			        		}	
			        		
			        		Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
			        			Assert.assertEquals(S_Business1 , xlCustName1);
								
						        Thread.sleep(2000);
						        driver.close();
						        
								driver.switchTo().window(WinID3[0]);

			        	}

     
			        else if (Business2 .contains(xlCustName1)){
			        	
			        	
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[1]";
				        String FirstCondDate   = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[2]";
				        String BusConductor1 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[4]/td[1]";
				        String SecondCondDate  = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[3]/td/table/tbody/tr[4]/td[2]";
				        String BusConductor2 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[1]/td[1]";
				        String ThirdCondDate = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				        
				        xpath =".//*[@id='RptBody']/table[1]/tbody/tr[6]/td/table/tbody/tr[1]/td[2]";
				        String BusConductor3 = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

	
			        	
		        		if ( BusConductor1.contains(xlConductor1)&&  BusConductor2.contains(xlConductor2)&& BusConductor3 .contains(xlConductor2) && 
		        			/* FirstCondDate .contains(xlCheckDate) && SecondCondDate .contains(xlCheckDate)&&ThirdCondDate .contains(xlCheckDate)  &&*/
		        			 Conductor_Details1 .contains(xlConductor2) && Conductor_Details1 .contains(xlConductor1) && Conductor_Details2 .contains(xlConductor2) ){
		        			
		        			
		        			
		        			for( int j=2 ; j<=11 ; j+=3){
		        				
		        				if( j==2){
		        					
						        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
									String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( CashOut, "$"+C_Total);
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
									String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( Checks, "$"+Total );	
								


		        				}
		        				
		        				else if( j==5){		     
		        					
		        					
						        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[2]/td[2]";			
									String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( CashOut, "$"+C_xlValue3);
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
									String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( Checks, "$"+Final_Value3 );

	        				

		        				}
		        				
		        				else {
			        				
						        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[2]/td[2]";			
									String CashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( CashOut, "$0.00");
									
									
									xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td[6]/table/tbody/tr[4]/td[2]";			
									String Checks= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( Checks, "$0.00" );	

		        				}

		        			}
		        			
							
		        			for( int j=3 ; j<=6 ; j+=3){
		        	
        				
		        				if (j==3){
		        					
		        					for (int k=3 ; k<=6 ; k+=3){
		        						if (k==3){
				        					xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr["+k+"]/td[8]";			
											String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
											Assert.assertEquals( IndvCashOut, "$"+C_xlValue1);
		        						}
		        						if (k==6){
				        					xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr["+k+"]/td[8]";			
											String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
											Assert.assertEquals( IndvCashOut, "$"+C_xlValue2);
		        						}

		        					}
		        				}
		        				else {
		        				

						        	xpath =".//*[@id='RptBody']/table[1]/tbody/tr["+j+"]/td/table/tbody/tr[3]/td[8]";			
									String IndvCashOut= new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
									Assert.assertEquals( IndvCashOut,  "$"+C_xlValue3);
		        				
		        				}
		        			}	
		        		
							xlData[i][16] = "PASS";
							System.out.println("PASS" +i);	
	
		        		}
		        		
		        		else{
		        			
							xlData[i][16] = "FAIL";
							System.out.println("FAIL" +i);	
							
		        		}

				
		        		Utils.writeXL(OR.getProperty("amlpath_results"),"AML", xlData);	
		        		Assert.assertEquals(S_Business1 , xlCustName);
			        	
						Thread.sleep(2000);
						driver.close();
									        
						driver.switchTo().window(WinID3[0]);
					

			        }			        

			}

			Thread.sleep(2000);
			driver.close();
	
	}
}
		
