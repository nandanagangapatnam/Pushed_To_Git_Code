package Separate_Entities;

import Utility.Utils;

import org.apache.bcel.classfile.Constant;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.Alert;
//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import Utility.Constants;
import Functions.Function_Classes;

public class Current_SystemDate {
	
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
	
	
	
	
	//@Test
	@Test (priority = 2)
	public void  date () throws Exception{
		OR = new Properties();
		FileInputStream fn = new FileInputStream (System.getProperty("user.dir")+ "//src//Functions/OR.properties");
		OR.load(fn);

		xlData = Utils.readXL(OR.getProperty("Separate_Entities_path"),"Test_Data");
		
/*		xlUsername = xlData [1][0];
		xlPassword = xlData [1][1];
		//xlStore = xlData [1][2];
		xlLoggedname = xlData [1][3];*/	
		
		Thread.sleep(500);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1500);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, "dateadm");
		
		Thread.sleep(500);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1500);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, "changeme123");
		
		Thread.sleep(1500);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(3000);
		Function_Classes.Admin_Setup(driver);
		
		
		
		for (int i = 1; i < 7; i ++){
			Thread.sleep(3000);
			xlStore = xlData [i][2];
			Thread.sleep(3000);
	        Function_Classes.field_link(driver,xlStore );// clicking on  Automation store
	        
			
	        Thread.sleep(2500);
	        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";  // clicking on  edit button
	        Function_Classes.field_click(driver, xpath);
	        
	        Thread.sleep(3000);
	        Function_Classes.field_clear(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']" );  // clear the field
		
	        String curent_Date = Function_Classes.current_date();
			System.out.println("ddate " + curent_Date);
			
		        Thread.sleep(3000);
		        xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";  // change of date 
		        Function_Classes.field_sendKeys(driver, xpath, curent_Date );
		        
		        Thread.sleep(3000);
		        xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
		        Function_Classes.field_click(driver, xpath);
		        
		        Thread.sleep(3000);
				Function_Classes.Admin_Setup(driver);
				System.out.println("i is " + i);

		}
		
		
		  Thread.sleep(3000);        
	        Function_Classes.logout (driver, ".//*[@id='menuv2']/li[9]/a" ); // for log out 
	        
			 Thread.sleep(1000);
			 driver.close();	
	
	
	}

}
