package Ohio;

import Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.util.Properties;
import Functions.Function_Classes;

public class _57_Void_Interstore_Transfer {
	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	//String path = "C:/1/Seleniumproject/TestData_2007.xls";
	//String wpath = "C:/1/Seleniumproject/TestResult_2007.xls";
	public static Properties OR=null;
	
	//@BeforeTest	
	@Test (priority = 1)
	public void Url_search (){		
		
		driver = new FirefoxDriver();			 
	driver.manage().window().maximize();		
	// Ecash site access
	Function_Classes.navigate_to(driver, URL);			
}
	
	
	// To Open / reopen the Cash Drawer
	@Test (priority = 2)
	public  void  StroreLogin () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);
		xlData = Utils.readXL(OR.getProperty("path"),"TestData");
				
		//Utils.setExcelFile(xlPath,"Login");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 2000;

			Thread.sleep(3500);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(3500);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		
		Thread.sleep(3500);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(3500);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		
		Thread.sleep(3500);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(3500);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		//String keys = "9998 Business date is always current date, TN";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(3500);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3500);			

	}
	
	

	// To Open / reopen the Cash Drawer
			@Test (priority = 3)
			public void Open_Drawer() throws Exception {
							
				//xlData = Utils.readXL(path,"TestData");	
				
				String Close_Submit = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDenomSubmit']";  // click on  Close Submit button
				
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_AppLeftNav_lnkOpenDrawer']";
				Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
				
				String Drawer_status = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_lnkOpenClose']";  // Drawer status
				String drawer_name = Function_Classes.field_gettext(driver, Drawer_status); // reading the value (open or close)
				System.out.println("Drawer Amount       " +drawer_name);
				Thread.sleep(vSleep);
				
				if (drawer_name.equals("Open")){
				System.out.println("Open");
				
				Function_Classes.field_click(driver, Drawer_status);   // clicking on  Open button		 
				Function_Classes.field_click(driver, Close_Submit);  // clicking on submit				
				System.out.println("Its now in Close status");																  
				}
				
				
				if (drawer_name.equals("Close")){	
					System.out.println("Close");
				    
					
					Thread.sleep(3500);
					Function_Classes.field_click(driver, Drawer_status);  // clicking on close 
	                
					Thread.sleep(3500);
					Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	
					Thread.sleep(3500);
					
					
					 try   
					  {    
						 if (driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblConfirmation']")).isDisplayed())   
					    {      
							 System.out.println("Finish Button visible");
								
								String Finish_button = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']";  // clicking on Finish button	
								Function_Classes.field_click(driver, Finish_button);  // cliking on finish button
								Thread.sleep(3500);
								
								Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		
								System.out.println("Opening again");
								
								Thread.sleep(3500);
								Function_Classes.field_click(driver, Close_Submit);  // clicking on submit	  
								System.out.println("Its now in Close status");	
								Thread.sleep(3500);	    
					    }    
					  }      
					  catch(Exception e)     
					  {       
						  System.out.println("Finish Button NOT visible");
							Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		 
							System.out.println("Opening again");
							
							Thread.sleep(3500);
							Function_Classes.field_click(driver, Close_Submit);  // clicking on submit
							System.out.println("Its now in Close status");	
							Thread.sleep(3500);   
					  }       
					
									 
				}
				System.out.println("close  765753");
				
			}
			
			
			// To Open / reopen the Valut Drawer
						@Test (priority = 4)
						public void Valut_Open_Drawer() throws Exception {
							System.out.println("Open   3");
										
						//	//xlData = Utils.readXL(path,"TestData");	
							
							String Close_Submit1 = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnDenomSubmit']";  // click on  Close Submit button
							
							/*Thread.sleep(3500);
							xpath = ".//*[@id='ctl00_AppLeftNav_lnkOpenDrawer']";
							Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
*/							
							Thread.sleep(3500);
							String Drawer_status = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl02_lnkOpenClose']";  // Valut Drawer status
							String drawer_name = Function_Classes.field_gettext(driver, Drawer_status); // reading the value (open or close)
							System.out.println("Drawer Amount       " +drawer_name);
							Thread.sleep(vSleep);
							
							//Thread.sleep(2000);
							if (drawer_name.equals("Open")){
							System.out.println("Open");
							
							Function_Classes.field_click(driver, Drawer_status);   // clicking on  Open button	
							
							Thread.sleep(2000);
							Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptVault_Items_ctl01_txtDenomination']", "1");   // clicking on  Open button	
							
							Thread.sleep(2000);
							Function_Classes.field_click(driver, Close_Submit1);  // clicking on submit	
							
							Thread.sleep(2000);
							Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']");  // clicking on finish button	
							
							System.out.println("Its now in Close status");																  
							}
							
							
							//Thread.sleep(2000);
							if (drawer_name.equals("Close")){	
								System.out.println("Close");
							    
								
								//Thread.sleep(3500);
								Function_Classes.field_click(driver, Drawer_status);  // clicking on close 
				                
								Thread.sleep(3500);
								Function_Classes.field_click(driver, Close_Submit1);  // clicking on submit	
								Thread.sleep(3500);
								
								
								 try   
								  {    
									 if (driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblConfirmation']")).isDisplayed())   
								    {      
										 System.out.println("Finish Button visible");
											
											String Finish_button = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']";  // clicking on Finish button	
											Function_Classes.field_click(driver, Finish_button);  // cliking on finish button
											Thread.sleep(3500);
											
											Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		
											System.out.println("Opening again");
											
											Thread.sleep(2000);
											Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptVault_Items_ctl01_txtDenomination']", "1");   // clicking on  Open button	
											
											Thread.sleep(2000);
											
											Thread.sleep(3500);
											Function_Classes.field_click(driver, Close_Submit1);  // clicking on submit	  
											
											Thread.sleep(2000);
											Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']");  // clicking on finish button	
											
											System.out.println("Its now in Close status");	
											Thread.sleep(3500);	    
								    }    
								  }      
								  catch(Exception e)     
								  {       
									  System.out.println("Finish Button NOT visible");
										Function_Classes.field_click(driver, Drawer_status);  // cliking on close / Open button		 
										System.out.println("Opening again");
										
										Thread.sleep(2000);
										Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptVault_Items_ctl01_txtDenomination']", "1");   // clicking on  Open button	
										
										Thread.sleep(2000);
										
										Thread.sleep(3500);
										Function_Classes.field_click(driver, Close_Submit1);  // clicking on submit
										
										Thread.sleep(2000);
										Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnBalancesDone']");  // clicking on finish button
										
										System.out.println("Its now in Close status");	
										Thread.sleep(3500);   
								  }       
																				 
							}
							
							
							
							
							
							System.out.println("close  3");
						}
			
			
			
			@Test (priority = 5)
			public void Inter_Strore_Transfer() throws Exception {
				//xlData = Utils.readXL(path,"TestData");	
				 System.out.println("Inter_Strore_Transfer   -- Started");

				 xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_trstore_vault_counts']/td[7]";
				 String drawerinitialamt = Function_Classes.field_gettext(driver, xpath);
				 System.out.println(drawerinitialamt);
				 
				 
				 
				 xlData[1][15] = drawerinitialamt;
				 Utils.writeXL(OR.getProperty("wpath"),"Login",xlData);
				 Thread.sleep(3500);  
				
				xpath = ".//*[@id='Left']/a[7]";  // clicking on tools left navigation
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(vSleep+2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liInterStoreTransfer']/a"; // clicking on Inter-store link
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3500);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkReport']");  //  click on send money
				
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cboTransferTo']";
				//Function_Classes.index_select(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cboTransferTo']",143);  //  click 9989 AUtomation TN	
				
				Function_Classes.field_sendKeys(driver, xpath, "9989 Automation Test Store, TN");//Automation Test Store, TN
				
				
				Thread.sleep(3500);
				Function_Classes.field_sendKeys(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBill100']", "1");  //  enter tghe $100 builll  amonut
				
				Thread.sleep(3500);
				Function_Classes.field_sendKeys(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']", "test");  //  enter notes
				
				Thread.sleep(3500);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  //  click on submit button
				
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_ctl00_AppLeftNav_lnkOpenDrawer']";
				Function_Classes.field_click (driver, xpath);  // clicking on the left navigation to Open the DRawer
				
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_lblBalance']";
				 String draweramt = Function_Classes.field_gettext(driver, xpath);
				 System.out.println("Drawer Amount     "  + draweramt);
				
				
				
				
				// changig the date 
				Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
				
				xlUsername = xlData [1][0];
				xlPassword = xlData [1][1];
				xlStore = xlData [1][2];
				xlLoggedname = xlData [1][3];	
				vSleep = 2000;

				Thread.sleep(3500);
			xpath=".//*[@id='txtUsername']";  // Clearing User Name field
			Function_Classes.field_clear(driver, xpath );
			
			Thread.sleep(3500);
			xpath=".//*[@id='txtUsername']";  // Passing User name 
			Function_Classes.field_sendKeys(driver, xpath, xlUsername);
			
			Thread.sleep(3500);
			xpath=".//*[@id='txtPasswd']";  // Clearing Password field
			Function_Classes.field_clear(driver, xpath );
			
			Thread.sleep(3500);
			xpath=".//*[@id='txtPasswd']";  // Passing Password
			Function_Classes.field_sendKeys(driver, xpath, xlPassword);
			
			Thread.sleep(3500);
			xpath = ".//*[@id='btnSubmit']"; // Click on go button
			Function_Classes.field_click(driver, xpath);
			
			Thread.sleep(3500);
			xpath=".//*[@id='ddlStore']";  // selecting the state 
			//String keys = "9989 Automation Test Store, TN";
			Function_Classes.field_sendKeys(driver, xpath, "9989 Automation Test Store, TN");

			Thread.sleep(3500);
			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			Function_Classes.field_click (driver, xpath);
			
			Thread.sleep(3500);	
			Function_Classes.field_click (driver, ".//*[@id='Left']/a[7]");  // Tools link 
			
			Thread.sleep(3500);	
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liInterStoreTransfer']/a");  // Click on Interstore link
			
			Thread.sleep(3500);	
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_Hyperlink1']");  // Click on REceive MOney link
			
			
			Thread.sleep(3500);	
			String Tra_amt = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptPendingTransfers_ctl01_lblAmount']");  // Reading money 
			System.out.println("Moeny Transfered " + Tra_amt);
			
			Thread.sleep(3500);	
			Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			
			//xlData = Utils.readXL(path,"TestData");	
			
			//Utils.setExcelFile(xlPath,"Login");
			
				xlUsername = xlData [1][0];
				xlPassword = xlData [1][1];
				xlStore = xlData [1][2];
				xlLoggedname = xlData [1][3];	
				vSleep = 2000;

				Thread.sleep(3500);
			xpath=".//*[@id='txtUsername']";  // Clearing User Name field
			Function_Classes.field_clear(driver, xpath );
			
			Thread.sleep(3500);
			xpath=".//*[@id='txtUsername']";  // Passing User name 
			Function_Classes.field_sendKeys(driver, xpath, xlUsername);
			
			Thread.sleep(3500);
			xpath=".//*[@id='txtPasswd']";  // Clearing Password field
			Function_Classes.field_clear(driver, xpath );
			
			Thread.sleep(3500);
			xpath=".//*[@id='txtPasswd']";  // Passing Password
			Function_Classes.field_sendKeys(driver, xpath, xlPassword);
			
			Thread.sleep(3500);
			xpath = ".//*[@id='btnSubmit']"; // Click on go button
			Function_Classes.field_click(driver, xpath);
			
			Thread.sleep(3500);
			xpath=".//*[@id='ddlStore']";  // selecting the state 
			//String keys = "9998 Business date is always current date, TN";
			Function_Classes.field_sendKeys(driver, xpath, xlStore);

			Thread.sleep(3500);
			xpath = ".//*[@id='btnSelStore']"; // clicking ok 
			Function_Classes.field_click (driver, xpath);
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='Left']/a[7]");  // clicking on tools
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_liVoid']/a");  // cliking on  Void transction
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnGo']");  // cliking on submit button
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgList_ctl02_lnkStore']");  // cliking on link  button
			
			Thread.sleep(3500);			
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "Test");  // Enter Reason 
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']");  // cliking on submit   button
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_AppLeftNav_lnkOpenDrawer']");  // cliking on Open drawer
			
			
			Thread.sleep(3500);			
			String final_val= Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_lblBalance']");  // reading the drawer value
			System.out.println("Drawer Amount     "  + final_val);
			
			Thread.sleep(3500);	
			Function_Classes.logout1(driver, ".//*[@id='ctl00_ctl00_AppHeader_liTools']/a", ".//*[@id='ctl00_ctl00_AppHeader_liTools_Transferv2']/a"); // drawer transfer
			
			
			Thread.sleep(3500);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cboTransferFrom']", "Drawer 3");// select Drawer 3
			
			Thread.sleep(3500);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cboTransferTo']", "Outer Vault");// select Outer Vault
			
			Thread.sleep(3500);
			Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']", "100");// select Outer Vault
			
			Thread.sleep(3500);
			Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnTransferSubmit']");  // click on Transfer button
			
			Thread.sleep(3500);
			Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']");  // click on continue  button
			
			
			Thread.sleep(3500);			
			Function_Classes.field_click (driver, ".//*[@id='ctl00_ctl00_AppLeftNav_lnkOpenDrawer']");  // cliking on Open drawer
			
			
			Thread.sleep(3500);			
			String final_val1= Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_rptstore_vault_counts_ctl03_lblBalance']");  // reading the drawer value
			System.out.println("Drawer Amount     "  + final_val1);
					
			
			
			
			Thread.sleep(3500);	
			Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 
			
			System.out.println("Inter_Strore_Transfer   -- Passed");
			
			 Thread.sleep(2000);
			 driver.close();	
			}	
			
			
			
				
			
			
			
			
			

}
