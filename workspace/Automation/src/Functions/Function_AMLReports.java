package Functions;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Function_AMLReports {
	
		  public static String[] AMLLogReport (WebDriver driver,String Xpath,String DollarValue1, String DollarValue2,String Date1,String Date2) throws InterruptedException{
			  
			  	Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				String xpath,mainWinID,newWinID;
				
				xpath =".//*[@id='ctl00_MainContent_rptMenu_ctl06_lnkMenu']";  // Reports 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				xpath =".//*[@id='ctl00_MainContent_rptReport_ctl02_lnkReport']";  // AML Log Report V2 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_MainContent_ctl00_dbDefDateBegin_txtDate']";  // Begin Xsaction Date
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
				
				xpath =".//*[@id='ctl00_MainContent_ctl00_dbDefDateEnd_txtDate']";  // End Xaction Date
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date2);

				xpath =".//*[@id='ctl00_MainContent_ctl00_txtAmountBegin']";  // Begin Threshold Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(DollarValue1);
				
				xpath =".//*[@id='ctl00_MainContent_ctl00_txtAmountEnd']";  // End Threshold Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(DollarValue2);
				
		
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_MainContent_ctl00_btnSubmit']";  // Run Report 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				
				Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
		        Iterator<String> itererator = windowId.iterator();   

		        mainWinID = itererator.next();
		        newWinID = itererator.next();
		        
		        return new String[]{mainWinID,newWinID};
		 			

		  }
		  
		  public static String[] CTRLogReport (WebDriver driver,String Xpath,String DollarValue1, String DollarValue2,String Date1,String Date2) throws InterruptedException{
			  
			  	Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				String xpath,mainWinID,newWinID;
				
				xpath =".//*[@id='ctl00_MainContent_rptMenu_ctl06_lnkMenu']";  // Reports 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				xpath =".//*[@id='ctl00_MainContent_rptReport_ctl02_lnkReport']";  // AML Log Report V2 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_MainContent_ctl00_dbDefDateBegin_txtDate']";  // Begin Xsaction Date
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
				
				xpath =".//*[@id='ctl00_MainContent_ctl00_dbDefDateEnd_txtDate']";  // End Xaction Date
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date2);

				xpath =".//*[@id='ctl00_MainContent_ctl00_txtAmountBegin']";  // Begin Threshold Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(DollarValue1);
				
				xpath =".//*[@id='ctl00_MainContent_ctl00_txtAmountEnd']";  // End Threshold Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(DollarValue2);
				
		
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_MainContent_ctl00_chkIncludeCTRDetail']";  // Run Report 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_MainContent_ctl00_btnSubmit']";  // Run Report 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			
				Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
		        Iterator<String> itererator = windowId.iterator();   

		        mainWinID = itererator.next();
		        newWinID = itererator.next();
		        
		        return new String[]{mainWinID,newWinID};
		 			

		  }

	}

			
