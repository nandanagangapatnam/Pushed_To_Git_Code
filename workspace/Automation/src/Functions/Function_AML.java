package Functions;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Function_AML {
	
			public static void MoneyOrder_NoCart (WebDriver driver, String Xpath, String Value, String CustName,String Void) throws InterruptedException
			{
				
				String xpath,Final_Total;
				float I_Value = Float.parseFloat(Value) ;			
				float E_Total =  I_Value +2;
				
						
				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				if(E_Total >= 1000){
					 Final_Total = Formatter.format(E_Total);
				}
				else{
					Final_Total = Formatter1.format(E_Total);
				}

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
					
			try{
				Thread.sleep(2000);
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlConductor']";  // Select Conductor
				int size_cond = driver.findElements(By.xpath(xpath)).size();
				
				if (size_cond != 0){
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Conductor = new Select(driver.findElement(By.xpath(xpath)));
					
					if (CustName .equals("Emmet Brickowoski")){
					
						Conductor.selectByVisibleText("Emmet Brickowoski - Principal");
					}
					else if (CustName .equals("President Business")){
						
						Conductor.selectByVisibleText("President Business - Principal");
					}
					
					else if (CustName .equals("Rick Grimes")){
						
						Conductor.selectByVisibleText("Rick Grimes - Officer");
					}
					else if (CustName .equals("Kia Armstrong")){
						
						Conductor.selectByVisibleText("Kia Armstrong - Director");
					}
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select MO Western Union 
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Item = new Select(driver.findElement(By.xpath(xpath)));
					Item.selectByVisibleText("MO Western Union");
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_pnlList']/table/tbody/tr/td";  // Western Union Money Order Sale  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_cbNonIntegrated']";  // Process Manually
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			
				}
				
				else{
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select MO Western Union 
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Item = new Select(driver.findElement(By.xpath(xpath)));
					Item.selectByVisibleText("MO Western Union");
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_pnlList']/table/tbody/tr/td";  // Western Union Money Order Sale  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_cbNonIntegrated']";  // Process Manually
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				}
				

						
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtAmount']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtAmount']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.TAB);
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtPayee']";  // Payee
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
		
				if (CustName .equals("Justin Barber") && Void .equals("Void Transaction")){
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_txtAmountFromCustomer']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					Thread.sleep(1500);
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("10000");
			
					
				}
				else if(CustName .equals("Xavier McDaniel") || CustName .equals("Owen Wilson")){
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_txtAmountFromCustomer']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					
					if(CustName .equals("Xavier McDaniel")){
						
						Thread.sleep(1500);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("50000");
				
					}
					
					if(CustName .equals("Owen Wilson")){
						
						Thread.sleep(1500);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3800");
				
					}
				}
				else{
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_txtAmountFromCustomer']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					Thread.sleep(1500);
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Final_Total);
				}	
					
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtMONumber']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1");
					
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_btnSubmit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_lblStatus']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String Status = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))) .getText();
				Assert.assertEquals(Status , "Processed Manually");
					
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_btnConfirmSubmit']";  // Continue Button
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //Home Page
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}
			
		catch	(Exception e){
			
			System.out.println("Exception has been handled" + e);
		}
	}		
			public static void MoneyOrder_WithCart (WebDriver driver, String Xpath, String Value,String Aggregate,String CustName,String Void) throws InterruptedException
			{
				
				String xpath,Final_Aggregate;
		
				float I_Aggregate = Float.parseFloat(Aggregate) ;				
								
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				
				if (I_Aggregate >= 1000){
					
					Final_Aggregate = Formatter.format(I_Aggregate);
				
				}
				else{
					
					Final_Aggregate = Formatter1.format(I_Aggregate);
					
				}
					
				


				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				Thread.sleep(2000);
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlConductor']";  // Select Conductor
				int size_cond = driver.findElements(By.xpath(xpath)).size();
				
				if (size_cond != 0){
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Conductor = new Select(driver.findElement(By.xpath(xpath)));
					
					if (CustName .equals("Emmet Brickowoski")){
						
						Conductor.selectByVisibleText("Emmet Brickowoski - Principal");
					}
					else if (CustName .equals("President Business")){
						
						Conductor.selectByVisibleText("President Business - Principal");
					}
					
					else if (CustName .equals("Rick Grimes")){
						
						Conductor.selectByVisibleText("Rick Grimes - Officer");
					}
					else if (CustName .equals("Kia Armstrong")){
						
						Conductor.selectByVisibleText("Kia Armstrong - Director");
					}
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select MO Western Union 
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Item = new Select(driver.findElement(By.xpath(xpath)));
					Item.selectByVisibleText("MO Western Union");
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_pnlList']/table/tbody/tr/td";  // Western Union Money Order Sale  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_cbNonIntegrated']";  // Process Manually
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
			
				}
				
				else{
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select MO Western Union 
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Item = new Select(driver.findElement(By.xpath(xpath)));
					Item.selectByVisibleText("MO Western Union");
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_pnlList']/table/tbody/tr/td";  // Western Union Money Order Sale  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_cbNonIntegrated']";  // Process Manually
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				}
				
				
				Thread.sleep(2000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtAmount']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
							
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtAmount']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.TAB);
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtPayee']";  // Payee
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_rptMOItems_ctl01_txtMONumber']";  // MO Number
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("1");
					
					
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ctl20_btnSubmitCart']";  //Add to Cart
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String NetTotal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))) .getText();
					
				if (NetTotal .equals("$"+Final_Aggregate)){
					
					if (CustName .equals("Justin West") && Void .equals("Void Transaction")){
						
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt from Cust Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						Thread.sleep(1000);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("10000");
								
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //Home Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					
						
					}
					
					else if(CustName .equals("Xerlotta Martin") || CustName .equals("Patsy Duck")){
						
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt from Cust Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
					
						if(CustName .equals("Xerlotta Martin")){
							
							Thread.sleep(1500);
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("50000");
					
						}
						
						if(CustName .equals("Patsy Duck")){
							
							Thread.sleep(1500);
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("3800");
					
						}
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //Home Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					}
					
					else {
						
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
						int size2 = driver.findElements(By.xpath(xpath)).size();
						
						if (size2 == 0 && NetTotal .equals("$"+Final_Aggregate)){
		
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

							xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
			
						}
					
						else if (size2 != 0 && NetTotal .equals("$"+Final_Aggregate)){
							
							Thread.sleep(1000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt from Cust Button
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							Thread.sleep(1000);
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);
									
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
							xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //Home Page
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
							
							
							
					}
				
						else{
							
							System.out.println("do nothing");
									
						}

					}
				}		
	
			}
			
			
			
			public static void BillPay_NoCart (WebDriver driver, String Xpath, String Value) throws InterruptedException
			{
				
				String xpath;
				String Final_Value;
				float I_Value = Float.parseFloat(Value) ;
				
						
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				if (I_Value >= 1000){
					
					 Final_Value = Formatter.format(I_Value);
					
				}
				
				else{
					
					 Final_Value = Formatter1.format(I_Value);
					
				}
				
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item_2 = new Select(driver.findElement(By.xpath(xpath)));
				Item_2.selectByVisibleText("Bill Pay – WU CONV Pay");
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				if ( Tax .equals("$0.00") && Fee .equals("0.00") ){
				
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						Thread.sleep(1500);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Final_Value);
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Submit Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtFromCust']";  // AmtFrom Customer
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
						String AmtFrmCust = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
						Assert.assertEquals(AmtFrmCust, "$"+Final_Value);
					
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
				
			
				}
					
				else{
						
						System.out.println("Values are not correct");
						Assert.assertEquals(Tax , "$0.00");
						Assert.assertEquals(Fee , "0.00");

					
					}
	
				}	
			
			public static void BillPay_WithCart (WebDriver driver, String Xpath, String Value,String Aggregate) throws InterruptedException
			{
			
				
				
				String xpath,Final_Aggregate;
				float I_Aggregate = Float.parseFloat(Aggregate) ;				
								
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				
				if (I_Aggregate >= 1000){
					
					Final_Aggregate = Formatter.format(I_Aggregate);
				
				}
				else{
					
					Final_Aggregate = Formatter1.format(I_Aggregate);
					
				}
					
				
				
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item_2 = new Select(driver.findElement(By.xpath(xpath)));
				Item_2.selectByVisibleText("Bill Pay – WU CONV Pay");
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				if ( Tax .equals("$0.00") && Fee .equals("0.00") ){
				
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']";  // Add To Cart
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  // NetTotal
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					String NetTotal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					
					if (NetTotal .equals("$"+Final_Aggregate)){
	
					
							Thread.sleep(1000);
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
							int size2 = driver.findElements(By.xpath(xpath)).size();
							
							if (size2 == 0 && NetTotal .equals("$"+Final_Aggregate)){
			
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Continue
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

								xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					
				
							}
						
							else if (size2 != 0 && NetTotal .equals("$"+Final_Aggregate)){
								
								Thread.sleep(1000);
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt from Cust Button
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								Thread.sleep(1000);
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);
										
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Continue
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

								xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
									
								
								
						}
					
							else{
								
								System.out.println("do nothing");
										
							}
						
					}
				
					else{
					
						System.out.println("do nothing");
				
					}

				}
			
			else{
				
					Assert.assertEquals(Tax, "$0.00");
					Assert.assertEquals(Fee, "0.00");

			
				}
			}


	
			public static void InsightCardLoad_NoCart (WebDriver driver, String Xpath, String Value) throws InterruptedException
			{
				
				String xpath,Final_Total;
				float I_Value = Float.parseFloat(Value) ;
				float E_Total =  I_Value +2;
				
				
	
				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				if (E_Total >= 1000){
					
					Final_Total = Formatter.format(E_Total);
					
				}
				
				else{
					
					Final_Total = Formatter1.format(E_Total);
					
				}


				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item = new Select(driver.findElement(By.xpath(xpath)));
				Item.selectByVisibleText("Insight Card Load");
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cbNonIntegrated']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
				Thread.sleep(1500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']"; 
				String Fees = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
	
				if (Tax .equals("$0.00") && Fees .equals("2.00") ){
	
				
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Final_Total);
				
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtFromCust']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					String AmtFrmCust = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))) .getText();
				
					if (AmtFrmCust .equals("$"+Final_Total)){
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']";  // Continue Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //Home Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
					
					}
					
					else{
						
						System.out.println(AmtFrmCust + "not equals $"+Final_Total);
						Assert.assertEquals(AmtFrmCust , "$"+Final_Total);
					}
				
				}
				
				else{
					
					System.out.println("Values are not correct");
					
					Assert.assertEquals(Tax, "$0.00");
					Assert.assertEquals(Fees, "2.00");

				}
			}

			public static void InsightCardLoad_WithCart (WebDriver driver, String Xpath, String Value,String Aggregate,String CustName) throws InterruptedException
			{
				
				String xpath,Final_Aggregate;
				float I_Aggregate = Float.parseFloat(Aggregate) ;
					
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
	
				
				if (I_Aggregate >= 1000){
					
					Final_Aggregate = Formatter.format(I_Aggregate);
			
				}
				else{
					
					Final_Aggregate = Formatter1.format(I_Aggregate);
				}


				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item = new Select(driver.findElement(By.xpath(xpath)));
				Item.selectByVisibleText("Insight Card Load");
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_cbNonIntegrated']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				
				Thread.sleep(1500);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']"; 
				String Fees = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
		
					
	
				if (Tax .equals("$0.00") && Fees .equals("2.00") ){
	
				
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']";  //Add to Cart
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  //Net Total
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					String NetTotal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();

					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
					int size2 = driver.findElements(By.xpath(xpath)).size();
					
					if (size2 == 0 && NetTotal .equals("$"+Final_Aggregate)){
	
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
		
					}
				
					else if (size2 != 0 && NetTotal .equals("$"+Final_Aggregate)){
						
							if(CustName .equals("Edward Snowden") ){
							
								Thread.sleep(1000);
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
						
								Thread.sleep(1000);
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("21112");

						
							}
							

							else{
								
								Thread.sleep(1000);
								xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								Thread.sleep(1000);
								new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);
							}		
					
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					

						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
	
					}
			
					else{
						
						System.out.println("do nothing");
								
					}
				}	
				
				else{
					
					System.out.println("Values are not correct");
					
					Assert.assertEquals(Tax, "$0.00");
					Assert.assertEquals(Fees, "2.00");

				}
			}

			public static void Check_NoCart (WebDriver driver, String Xpath, String Value,String check_no, String Business_date, String Aggregate, String CustName) throws InterruptedException
			{
				
				String xpath;
				String Final_Aggregate;
				float I_Value = Float.parseFloat(Value) ;
				float Check_fee = (float) (.05 * I_Value);
				float I_Aggregate = Float.parseFloat(Aggregate) ;
				
				String Final_Check_fee ="0";
				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				if (Check_fee < 5 ){
					
					Final_Check_fee = "5.00";
				}
				else if (Check_fee < 1000 ){
					
					Final_Check_fee = Formatter1.format(Check_fee);
				}
				else if (Check_fee >= 1000 ){
					
					Final_Check_fee = Formatter.format(Check_fee);
				}
				
				
				if (I_Aggregate >= 1000){
					 
					Final_Aggregate = Formatter.format(I_Aggregate);
				}
				else{
					
					Final_Aggregate = Formatter1.format(I_Aggregate);
				}
				
				
				

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewCheck']";  // Select MO Western Union 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		
				xpath = ".//*[@id='dgPrevList_ctl02_lnkName']";  // New Check  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
				xpath = ".//*[@id='cboCheckType']";  // Check Type
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Check = new Select(driver.findElement(By.xpath(xpath)));
				Check.selectByVisibleText("Money Order");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='cboSubMaker']";  //SubMaker
				int size = driver.findElements(By.xpath(xpath)).size();
				if (size !=0){
					
					Select Submaker = new Select(driver.findElement(By.xpath(xpath)));
					Submaker.selectByIndex(2);
				}
				
				Thread.sleep(1000);
				xpath = ".//*[@id='cboBank']";  // BankAcct
				int size1 = driver.findElements(By.xpath(xpath)).size();
				if (size1 !=0){
					
					Select BankAcct = new Select(driver.findElement(By.xpath(xpath)));
					BankAcct.selectByIndex(1);
				}
				
				Thread.sleep(1000);
				
				xpath = ".//*[@id='cboConductor']";  // Select Conductor
				int size_cond = driver.findElements(By.xpath(xpath)).size();
				
				if (size_cond != 0){
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Conductor = new Select(driver.findElement(By.xpath(xpath)));
					
					if (CustName .equals("Kia Armstrong")){
						
						Conductor.selectByVisibleText("Kia Armstrong - Director");
					}
					
					if (CustName .equals("Emmet Brickowoski")){
						
						Conductor.selectByVisibleText("Emmet Brickowoski - Principal");
					}
					
					if (CustName .equals("michael bryant")){
						
						Conductor.selectByVisibleText("michael bryant - Principal");
					}
					if (CustName .equals("Nicki Bellomy")){
						
						Conductor.selectByVisibleText("Nicki Bellomy - Principal");
					}
			
					if (CustName .equals("Ladawnya Adair")){
						
						Conductor.selectByVisibleText("Ladawnya Adair - Principal");
					}
					if (CustName .equals("Mecha Godzilla")){
						
						Conductor.selectByVisibleText("Mecha Godzilla - Officer");
					}
					if (CustName .equals("Lord Business")){
						
						Conductor.selectByVisibleText("Lord Business - Principal");
					}
					if (CustName .equals("President Business")){
						
						Conductor.selectByVisibleText("President Business - Principal");
					}
					
					 if (CustName .equals("Rick Grimes")){
						
						Conductor.selectByVisibleText("Rick Grimes - Officer");
					}
				}
				
				Thread.sleep(1000);
				xpath = ".//*[@id='txtCheckNum']";  // CheckNum
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(check_no);
				

				xpath = ".//*[@id='txtAmount']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
				
				xpath = ".//*[@id='CheckDate_txtDate']";  // Date
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Business_date);
				
				Thread.sleep(1000);
				xpath = ".//*[@id='btnStep1Submit']";  // Next
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath = ".//*[@id='lblChkCashFee']";  // lblChkCashFee
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
				xpath = ".//*[@id='lblPayToCustomer']";  // lblPayToCustomer
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String PayToCustomer = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
	
				if (Fee .equals("$"+Final_Check_fee) && PayToCustomer .equals("$"+Final_Aggregate) ){
					
					xpath = ".//*[@id='btnStep2Submit']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					Thread.sleep(1000);
					xpath = ".//*[@id='rptTrans_ctl00_rptDocs_ctl00_lblDocName']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					
					xpath = ".//*[@id='btnContinue']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					
					xpath = ".//*[@id='Block']/table[1]/tbody/tr/td[1]";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
				}
				else{
					
					Assert.assertEquals(Fee, "$"+Final_Check_fee);
					Assert.assertEquals(PayToCustomer, "$"+Final_Aggregate);
				}
			}
			
			public static void Check_WithCart (WebDriver driver, String Xpath, String Value,String check_no, String Business_date, String Aggregate,String CustName) throws InterruptedException
			{
				
				String xpath;
				String Final_Check_fee ="0";
				String Final_Aggregate;
				float I_Value = Float.parseFloat(Value) ;
				System.out.println(I_Value);
				float Check_fee = (float) (.05 * I_Value);
				float I_Aggregate = Float.parseFloat(Aggregate) ;
				System.out.println(I_Aggregate);
				
				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);

				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				if (Check_fee < 5 ){
					
					Final_Check_fee = "5.00";
				}
				else if (Check_fee < 1000 ){
					
					Final_Check_fee = Formatter1.format(Check_fee);
				}
				else if (Check_fee >= 1000 ){
					
					Final_Check_fee = Formatter.format(Check_fee);
				}

				
				
				if (I_Aggregate >= 1000){
					 Final_Aggregate = Formatter.format(I_Aggregate);
				}
				else{
					 Final_Aggregate = Formatter1.format(I_Aggregate);
				}
				
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewCheck']";  // Select MO Western Union 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		
				xpath = ".//*[@id='dgPrevList_ctl02_lnkName']";  // New Check  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
				xpath = ".//*[@id='cboCheckType']";  // Check Type
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Check = new Select(driver.findElement(By.xpath(xpath)));
				Check.selectByVisibleText("Money Order");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='cboSubMaker']";  //SubMaker
				int size = driver.findElements(By.xpath(xpath)).size();
				if (size !=0){
					
					Select Submaker = new Select(driver.findElement(By.xpath(xpath)));
					Submaker.selectByIndex(2);
				}
				
				Thread.sleep(1000);
				xpath = ".//*[@id='cboBank']";  // BankAcct
				int size1 = driver.findElements(By.xpath(xpath)).size();
				if (size1 !=0){
					
					Select BankAcct = new Select(driver.findElement(By.xpath(xpath)));
					BankAcct.selectByIndex(1);
				}
				
				Thread.sleep(1000);
				
				xpath = ".//*[@id='cboConductor']";  // Select Conductor
				int size_cond = driver.findElements(By.xpath(xpath)).size();
				
				if (size_cond != 0){
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					Select Conductor = new Select(driver.findElement(By.xpath(xpath)));
					
					if (CustName .equals("Kia Armstrong")){
						
						Conductor.selectByVisibleText("Kia Armstrong - Director");
					}
					
					if (CustName .equals("Emmet Brickowoski")){
						
						Conductor.selectByVisibleText("Emmet Brickowoski - Principal");
					}
					
					if (CustName .equals("Michael Bryant")){
						
						Conductor.selectByVisibleText("michael bryant - Principal");
					}
					if (CustName .equals("Nicki Bellomy")){
						
						Conductor.selectByVisibleText("Nicki Bellomy - Principal");
					}
					if (CustName .equals("Ladawnya Adair")){
						
						Conductor.selectByVisibleText("Ladawnya Adair - Principal");
					}
					if (CustName .equals("Mecha Godzilla")){
						
						Conductor.selectByVisibleText("Mecha Godzilla - Officer");
					}
					if (CustName .equals("Lord Business")){
						
						Conductor.selectByVisibleText("Lord Business - Principal");
					}
					
					if (CustName .equals("President Business")){
						
						Conductor.selectByVisibleText("President Business - Principal");
					}
					
					 if (CustName .equals("Rick Grimes")){
						
						Conductor.selectByVisibleText("Rick Grimes - Officer");
					}
				}
				
				Thread.sleep(1000);
				xpath = ".//*[@id='txtCheckNum']";  // CheckNum
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(check_no);
				

				xpath = ".//*[@id='txtAmount']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
				
				xpath = ".//*[@id='CheckDate_txtDate']";  // Date
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Business_date);
				
				Thread.sleep(1000);
				xpath = ".//*[@id='btnStep1Submit']";  // Next
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath = ".//*[@id='lblChkCashFee']";  // lblChkCashFee
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		
				if (Fee .equals("$"+Final_Check_fee) ){

					xpath = ".//*[@id='btnSubmitCart']";  //Add to Cart
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  // NetTotal
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					String NetTotal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					
					Thread.sleep(1000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
					int size2 = driver.findElements(By.xpath(xpath)).size();
					
					if (size2 == 0 && NetTotal .equals("$"+Final_Aggregate)){
	
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
		
					}
				
					else if (size2 != 0 && NetTotal .equals("$"+Final_Aggregate)){
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						Thread.sleep(1000);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);

					
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
						
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					

						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
	
					}
				
					else{
					
							System.out.println("do nothing");
				
					}

				}
					
				else{
					
						Assert.assertEquals(Fee, "$"+Final_Check_fee);

					}
				
				}
			
			
			public static void WireTransferReceive_NoCart (WebDriver driver, String Xpath, String Value) throws InterruptedException
			{
				
				String xpath;

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
		
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item_2 = new Select(driver.findElement(By.xpath(xpath)));
				Item_2.selectByVisibleText("Wire Trans Receive – WU");
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtField1Value']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				
				if (Tax .equals("$0.00") && Fee .equals("0.00") ){
				
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Submit Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
					
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
				
			
				}
					
				else{
						
						System.out.println("Values are not correct");
						Assert.assertEquals(Tax , "$0.00");
						Assert.assertEquals(Fee , "0.00");

					
					}
	
				}
			
			public static void WireTransferReceive_WithCart (WebDriver driver, String Xpath, String Value, String Aggregate) throws InterruptedException
			{
				
				String xpath,Final_Aggregate;
				float I_Aggregate = Float.parseFloat(Aggregate) ;				
				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				
				if (I_Aggregate >= 1000){
					
					Final_Aggregate = Formatter.format(I_Aggregate);
				
				}
				else{
					
					Final_Aggregate = Formatter1.format(I_Aggregate);
					
				}
					

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
		
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item_2 = new Select(driver.findElement(By.xpath(xpath)));
				Item_2.selectByVisibleText("Wire Trans Receive – WU");
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtField1Value']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				
				if (Tax .equals("$0.00") && Fee .equals("0.00") ){
				
						
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']";  // Add To Cart
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					String NetTotal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					
					if (NetTotal .equals("$"+Final_Aggregate)){
						
						Thread.sleep(1000);			
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
					}
				
					else{
					
						System.out.println("Do nothing");
				
					}
					
				}
			
				else{
				
						System.out.println("Values are not correct");
						Assert.assertEquals(Tax , "$0.00");
						Assert.assertEquals(Fee , "0.00");

				
					}	
				
				}

			
			public static void WireTransferSend_NoCart (WebDriver driver, String Xpath, String Value) throws InterruptedException
			{
				
				String xpath;

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
		
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item_2 = new Select(driver.findElement(By.xpath(xpath)));
				Item_2.selectByVisibleText("Wire Trans Send WU");
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtField1Value']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				
				if (Tax .equals("$0.00") && Fee .equals("0.00") ){
				
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Submit Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
						Thread.sleep(1000);
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
			
										
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  // Submit Button
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
					
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']";  //Continue
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
						xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
				
			
				}
					
				else{
						
						System.out.println("Values are not correct");
						Assert.assertEquals(Tax , "$0.00");
						Assert.assertEquals(Fee , "0.00");

					
					}
	
				}
			
			public static void WireTransferSend_WithCart (WebDriver driver, String Xpath, String Value, String Aggregate) throws InterruptedException
			{
				
				String xpath,Final_Aggregate;
				float I_Aggregate = Float.parseFloat(Aggregate) ;				
				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				
				
				if (I_Aggregate >= 1000){
					
					Final_Aggregate = Formatter.format(I_Aggregate);
				
				}
				else{
					
					Final_Aggregate = Formatter1.format(I_Aggregate);
					
				}
					

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
		
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Item_2 = new Select(driver.findElement(By.xpath(xpath)));
				Item_2.selectByVisibleText("Wire Trans Send WU");
	
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtField1Value']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtFees']";  
				String Fee = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getAttribute("value");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblTax']";  
				String Tax = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				
				
				if (Tax .equals("$0.00") && Fee .equals("0.00") ){
					
					
						
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']";  // Add To Cart
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					String NetTotal = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
					
					if (NetTotal .equals("$"+Final_Aggregate)){
															
						Thread.sleep(1000);
						xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
						int size2 = driver.findElements(By.xpath(xpath)).size();
						
						if (size2 == 0 && NetTotal .equals("$"+Final_Aggregate)){
		
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

							xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
			
						}
					
						else if (size2 != 0 && NetTotal .equals("$"+Final_Aggregate)){
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							Thread.sleep(1000);
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);

						
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
							
							xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
						

							xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
							new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
		
						}
					
						else{
						
								System.out.println("do nothing");
					
						}
					}
					
				}
			
				else{
				
						System.out.println("Values are not correct");
						Assert.assertEquals(Tax , "$0.00");
						Assert.assertEquals(Fee , "0.00");

				
					}	
				
				}	
		
		public static void TitleLoan_NoCart (WebDriver driver, WebDriver driver1,String Xpath, String Value,String Date,String Date1) throws InterruptedException
			{
		
				String xpath,Final_Value;

				float I_Value = Float.parseFloat(Value) ;	

				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				

				
				if (I_Value >= 1000){
					
					Final_Value = Formatter.format(I_Value);
				
				}
				else{
					
					Final_Value = Formatter1.format(I_Value);
					
				}
				
				Thread.sleep(2000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date);
		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewTitle']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Loanmodel = new Select(driver.findElement(By.xpath(xpath)));
				Loanmodel.selectByVisibleText("TN TTL");
			
			
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // Next
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ucCustProperty_dgList_ctl03_chkVehicle']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("2500");
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Add To Cart
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtToCust']";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
		//		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		//		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtFromCust']";  //$2500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String AmtfromCustomer =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();;
				Assert.assertEquals(AmtfromCustomer,"$"+Final_Value );
				
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
			
			}	

		public static void TitleLoan_WithCart (WebDriver driver,WebDriver driver1,String Xpath, String Value,String Aggregate,String Date,String Date1) throws InterruptedException
		
		{
	
			String xpath,Final_Aggregate;

			float I_Aggregate = Float.parseFloat(Aggregate) ;	
			
			DecimalFormat Formatter = new DecimalFormat("#,###.00");
			Formatter.setMaximumFractionDigits(2);
			DecimalFormat Formatter1 = new DecimalFormat("###.00");
			Formatter1.setMaximumFractionDigits(2);
			

			
			
			if (I_Aggregate >= 1000){
				
				Final_Aggregate = Formatter.format(I_Aggregate);
			
			}
			else{
				
				Final_Aggregate = Formatter1.format(I_Aggregate);
				
			}
				
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date);
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
		
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewTitle']";  // Select Bill Pay – WU CONV Pay 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // Select Bill Pay – WU CONV Pay 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Select Loanmodel = new Select(driver.findElement(By.xpath(xpath)));
			Loanmodel.selectByVisibleText("TN TTL");
		
		
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // Next
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ucCustProperty_dgList_ctl03_chkVehicle']"; 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("2500");
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtToCust']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
	
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
//			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
			
	//		Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
	//		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);

	
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String NetTotal =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();;
			
			
			if (NetTotal .equals("$"+Final_Aggregate)){
				
				Thread.sleep(1000);	
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
				
			}
			
			
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
		}		
		
		
		public static void LoanPayment_NoCart (WebDriver driver, WebDriver driver1,String Xpath, String Value,String Date,String Date1) throws InterruptedException
			{
		
				String xpath,Final_Value;

				float I_Value = Float.parseFloat(Value) ;	

				
				DecimalFormat Formatter = new DecimalFormat("#,###.00");
				Formatter.setMaximumFractionDigits(2);
				DecimalFormat Formatter1 = new DecimalFormat("###.00");
				Formatter1.setMaximumFractionDigits(2);
				

				
				if (I_Value >= 1000){
					
					Final_Value = Formatter.format(I_Value);
				
				}
				else{
					
					Final_Value = Formatter1.format(I_Value);
					
				}
				
				Thread.sleep(2000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date);
		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
								
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // Select Bill Pay – WU CONV Pay 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				Select Loanmodel = new Select(driver.findElement(By.xpath(xpath)));
				Loanmodel.selectByVisibleText("TN PRA Type 1");
			
			
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // Next
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_chkIsCheckOnDemand']"; 
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("425");
				
			
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Add To Cart
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
					
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtToCust']";  //$425.00
				new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  //$500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";  //$500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
				
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";  //$500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']";  //$500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	//			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //$500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				Thread.sleep(1000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtFromCust']";  //$500
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String AmtfromCustomer =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();;
				Assert.assertEquals(AmtfromCustomer,"$"+Final_Value );
				
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
				
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
		
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Thread.sleep(1000);
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
				new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	
			
			}	
		
		public static void LoanPayment_WithCart (WebDriver driver,WebDriver driver1,String Xpath, String Value,String Aggregate,String Date,String Date1) throws InterruptedException
		
		{
	
			String xpath,Final_Aggregate;

			float I_Aggregate = Float.parseFloat(Aggregate) ;	
			
			DecimalFormat Formatter = new DecimalFormat("#,###.00");
			Formatter.setMaximumFractionDigits(2);
			DecimalFormat Formatter1 = new DecimalFormat("###.00");
			Formatter1.setMaximumFractionDigits(2);
			

			
			
			if (I_Aggregate >= 1000){
				
				Final_Aggregate = Formatter.format(I_Aggregate);
			
			}
			else{
				
				Final_Aggregate = Formatter1.format(I_Aggregate);
				
			}
				
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date);
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
		
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Select Bill Pay – WU CONV Pay 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
							
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']";  // Select Bill Pay – WU CONV Pay 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Select Loanmodel = new Select(driver.findElement(By.xpath(xpath)));
			Loanmodel.selectByVisibleText("TN PRA Type 1");
		
		
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // Next
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_chkIsCheckOnDemand']"; 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("425");
			
		
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']";  // Add To Cart
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtToCust']";  //$425.00
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
			
	
			
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']";  //$500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
		
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkMakePmt']/span";  //$500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();	
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPaymentAmount']";  //$500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmountFromCustomer']";  //$500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	//		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);


	
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmitCart']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblNetTotal']";  //$2500
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String NetTotal =new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();;
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
			int size2 = driver.findElements(By.xpath(xpath)).size();
			
			if (size2 == 0 && NetTotal .equals("$"+Final_Aggregate)){

				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

				xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	

			}
		
			else if (size2 != 0 && NetTotal .equals("$"+Final_Aggregate)){
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtAmount']";  // Amt From Customer
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Aggregate);

			
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  //Submit
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_pnlForm']";  //Continue
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnContinue']";  //Submit
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			

				xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //HOme Page
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	

			}
		
			else{
			
					System.out.println("do nothing");
		
			}

				
			
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkEdit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

			
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date1);
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			
			Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblBusDate']";
			new WebDriverWait(driver1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			
		}
		public static void ATM_Withdrawals (WebDriver driver, String Xpath, String Value) throws InterruptedException
		{
			
			String xpath,Final_Total;
			float I_Value = Float.parseFloat(Value) ;
			float E_Total =  I_Value +2;
			
			

			
			DecimalFormat Formatter = new DecimalFormat("#,###.00");
			Formatter.setMaximumFractionDigits(2);
			DecimalFormat Formatter1 = new DecimalFormat("###.00");
			Formatter1.setMaximumFractionDigits(2);
			
			if (E_Total >= 1000){
				
				Final_Total = Formatter.format(E_Total);
				
			}
			
			else{
				
				Final_Total = Formatter1.format(E_Total);
				
			}


			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlItems']";  // Select Bill Pay – WU CONV Pay 
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Select Item = new Select(driver.findElement(By.xpath(xpath)));
			Item.selectByVisibleText("ATM Withdrawals");
			
			
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtPrice']";  // Amount
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.DELETE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Value);
			
			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtComments']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			

		

			Thread.sleep(1000);
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			

			
			Thread.sleep(2000);
				
			xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lblAmtToCust']";  
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String AmtFrmCust = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))) .getText();
			
			if (AmtFrmCust .equals("$1,466.25")){
					
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnConfirmSubmit']";  // Continue Button
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

					xpath = ".//*[@id='ctl00_ctl00_MainContent_TabNav_lnkGeneral']";  //Home Page
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				
				}
				
				else{
					
					System.out.println(AmtFrmCust + "not equals $1,466.25");
					Assert.assertEquals(AmtFrmCust , "$1,466.25");
				}
			

		}
		
		public static void Void_transaction (WebDriver driver,String Xpath,String Value, String Date,String Transaction) throws InterruptedException
		{
			
			String xpath,Final_Value;
			
			
			DecimalFormat Formatter = new DecimalFormat("#,###.00");
			Formatter.setMaximumFractionDigits(2);
			DecimalFormat Formatter1 = new DecimalFormat("###.00");
			Formatter1.setMaximumFractionDigits(2);
			
			if (Transaction .equals("Money Order Non-Integrated no Cart") || Transaction .equals("Money Order Non-Integrated with Cart")){
				
				float I_Value = Float.parseFloat(Value) + 2;
				
				if (I_Value >= 1000){
					
					Final_Value = Formatter.format(I_Value);
					
				}
				
				else{
					
					Final_Value = Formatter1.format(I_Value);
					
				}
				
			}
			
			else{
				
				float I_Value = Float.parseFloat(Value);
				
				if (I_Value >= 1000){
					
					Final_Value = Formatter.format(I_Value);
					
				}
				
				else{
					
					Final_Value = Formatter1.format(I_Value);
					
				}
			}
			
						
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath))).click();
	
			Thread.sleep(1000); 	
			xpath =".//*[@id='ctl00_ctl00_AppHeader_liTools_Void']/a";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	 
			
			Thread.sleep(1000); 	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_DateBegin_txtDate']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date);
			
			
			Thread.sleep(1000); 	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_DateEnd_txtDate']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(Date);
		
		
			Thread.sleep(1000); 	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnGo']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	 
			Thread.sleep(3000); 
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgList']";
		    WebElement table = driver.findElement(By.xpath(xpath)) ;
		    //get all rows
		    List<WebElement> allRows = table.findElements(By.tagName("tr"));
		    
 
		    //iterate through the rows
		    for (WebElement row : allRows) {
		            //get the rowCells in each row
		            List<WebElement> cells  = row.findElements(By.tagName("td"));
 
		            //get the column which contains the Final_Value and get text
		            String Amount = cells.get(10).getText();
 
		            if (Amount.equals("$"+Final_Value)) {
		               
		            	System.out.println("Table Data : " + Amount);
		            	
		            	Thread.sleep(1000); 
		            	cells.get(3).findElement(By.tagName("a")).click();
		            	

		            }
		    }
			
		    Thread.sleep(1000);
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("TEST VOID");
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	
			xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnGo']";
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}
		
		public static void Void_transaction_Check (WebDriver driver,String Xpath,String Value,String Date) throws InterruptedException
		{
			
			String xpath,Final_Value;
			float I_Value = Float.parseFloat(Value) ;
	
				
			DecimalFormat Formatter = new DecimalFormat("#,###.00");
			Formatter.setMaximumFractionDigits(2);
			DecimalFormat Formatter1 = new DecimalFormat("###.00");
			Formatter1.setMaximumFractionDigits(2);
			
			if (I_Value >= 1000){
				
				Final_Value = Formatter.format(I_Value);
				
			}
			
			else{
				
				Final_Value = Formatter1.format(I_Value);
				
			}	
			
				xpath =".//*[@id='ctl00_ctl00_MainContent_ChildContent1_gvOpenLoans_ctl02_lnkLoanId']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();



				xpath =".//*[@id='lnkDelete']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				xpath =".//*[@id='txtDelReason']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys("TEST VOID");
	
				
				xpath =".//*[@id='btnDelSubmit']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
				
				Alert alert = driver.switchTo().alert();
			  	alert.accept();
			  	
			  	Thread.sleep(1000);
			  	xpath =".//*[@id='lblOutstanding']";
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				String Text = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
				Assert.assertEquals(Text, "There are no outstanding checks at this time."); 
				

			}
	
				

}
			
