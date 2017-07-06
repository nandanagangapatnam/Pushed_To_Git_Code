package Ohio;
import Utility.Utils;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import Functions.Function_Classes;

public class Multiple {
	
	String vBrowser = "FireFox";
	WebDriver driver1, driver2 , driver3, driver4 ;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	int tcRows, tsRows; // Row count for each sheet.
	String path = "C:/1/Seleniumproject/TestData_2007.xls";
	String wpath = "C:/1/Seleniumproject/TestResult_2007.xls";
	int xRows, xCols;
	@Test 
	public void  StroreLogin () throws Exception{
	
	
	
	
	
	driver1 = new FirefoxDriver();			 
driver1.manage().window().maximize();		
// Ecash site access
Function_Classes.navigate_to(driver1, URL);	

xlData = Utils.readXL(path,"TestData");	

//Utils.setExcelFile(xlPath,"Login");

	xlUsername = xlData [1][0];
	xlPassword = xlData [1][1];
	xlStore = xlData [1][2];
	xlLoggedname = xlData [1][3];	
	vSleep = 1000;


xpath=".//*[@id='txtUsername']";  // Clearing User Name field
Function_Classes.field_clear(driver1, xpath );

xpath=".//*[@id='txtUsername']";  // Passing User name 
Function_Classes.field_sendKeys(driver1, xpath, xlUsername);

xpath=".//*[@id='txtPasswd']";  // Clearing Password field
Function_Classes.field_clear(driver1, xpath );

xpath=".//*[@id='txtPasswd']";  // Passing Password
Function_Classes.field_sendKeys(driver1, xpath, xlPassword);

xpath = ".//*[@id='btnSubmit']"; // Click on go button
Function_Classes.field_click(driver1, xpath);

xpath=".//*[@id='ddlStore']";  // selecting the state 
//String keys = "9998 Business date is always current date, TN";
Function_Classes.field_sendKeys(driver1, xpath, xlStore);

xpath = ".//*[@id='btnSelStore']"; // clicking ok 
Function_Classes.field_click (driver1, xpath);

Thread.sleep(2000);	
Function_Classes.field_click (driver1, ".//*[@id='ctl00_AppHeader_liReports']/a");


Thread.sleep(2000);	
Function_Classes.field_click (driver1, ".//*[@id='ctl00_MainContent_rptMenu_ctl04_lnkMenu']");

Thread.sleep(2000);	
Function_Classes.field_click (driver1, ".//*[@id='ctl00_MainContent_rptReport_ctl03_lnkReport']");

Thread.sleep(1000);
xpath = ".//*[@id='ctl00_MainContent_ctl00_chkIncludeNonAML']";// clicking on the Process Manually check box
if (!driver1.findElement(By.xpath(xpath)).isSelected())
{
	System.out.println("Yes");
Function_Classes.field_click(driver1,xpath);	
}



Thread.sleep(1000);
xpath = ".//*[@id='ctl00_MainContent_ctl00_chkIncludeCTRDetail']";// clicking on the Process Manually check box
if (!driver1.findElement(By.xpath(xpath)).isSelected())
{
	System.out.println("Yes");
Function_Classes.field_click(driver1,xpath);	
}



Thread.sleep(1000);	
Function_Classes.field_click (driver1, ".//*[@id='ctl00_MainContent_ctl00_btnSubmit']");
	}
	
	
//===========================
	
	@Test 
	public void  StroreLogin1 () throws Exception{

driver2 = new FirefoxDriver();			 
driver2.manage().window().maximize();	
Function_Classes.navigate_to(driver2, URL);	

xlData = Utils.readXL(path,"TestData");	

//Utils.setExcelFile(xlPath,"Login");

	xlUsername = xlData [1][0];
	xlPassword = xlData [1][1];
	xlStore = xlData [1][2];
	xlLoggedname = xlData [1][3];	
	vSleep = 1000;


xpath=".//*[@id='txtUsername']";  // Clearing User Name field
Function_Classes.field_clear(driver2, xpath );

xpath=".//*[@id='txtUsername']";  // Passing User name 
Function_Classes.field_sendKeys(driver2, xpath, xlUsername);

xpath=".//*[@id='txtPasswd']";  // Clearing Password field
Function_Classes.field_clear(driver2, xpath );

xpath=".//*[@id='txtPasswd']";  // Passing Password
Function_Classes.field_sendKeys(driver2, xpath, xlPassword);

xpath = ".//*[@id='btnSubmit']"; // Click on go button
Function_Classes.field_click(driver2, xpath);

xpath=".//*[@id='ddlStore']";  // selecting the state 
//String keys = "9998 Business date is always current date, TN";
Function_Classes.field_sendKeys(driver2, xpath, xlStore);

xpath = ".//*[@id='btnSelStore']"; // clicking ok 
Function_Classes.field_click (driver2, xpath);

Thread.sleep(2000);	
Function_Classes.field_click (driver2, ".//*[@id='ctl00_AppHeader_liReports']/a");


Thread.sleep(2000);	
Function_Classes.field_click (driver2, ".//*[@id='ctl00_MainContent_rptMenu_ctl04_lnkMenu']");

Thread.sleep(2000);	
Function_Classes.field_click (driver2, ".//*[@id='ctl00_MainContent_rptReport_ctl03_lnkReport']");

Thread.sleep(1000);
xpath = ".//*[@id='ctl00_MainContent_ctl00_chkIncludeNonAML']";// clicking on the Process Manually check box
if (!driver2.findElement(By.xpath(xpath)).isSelected())
{
	System.out.println("Yes");
Function_Classes.field_click(driver2,xpath);	
}



Thread.sleep(1000);
xpath = ".//*[@id='ctl00_MainContent_ctl00_chkIncludeCTRDetail']";// clicking on the Process Manually check box
if (!driver2.findElement(By.xpath(xpath)).isSelected())
{
	System.out.println("Yes");
Function_Classes.field_click(driver2,xpath);	
}



Thread.sleep(1000);	
Function_Classes.field_click (driver2, ".//*[@id='ctl00_MainContent_ctl00_btnSubmit']");



	}










	

}
