package Functions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Utility.Utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.ie.InternetExplorerDriver;

//import Framework.$missing$;

public class Function_Classes {
	
	
	// Functions 
			public static void navigate_to(WebDriver mD, String fURL) {
				mD.navigate().to(fURL);
			}
			
			public static void field_clear(WebDriver mD, String Xpath){
			mD.findElement(By.xpath(Xpath)).clear();
			}
			
			public static void field_sendKeys(WebDriver mD, String Xpath, String Value){
				mD.findElement(By.xpath(Xpath)).sendKeys(Value);
			}
		
			
			public static void field_click (WebDriver mD, String Xpath){
				mD.findElement(By.xpath(Xpath)).click();
			}
			
			
			public static void field_submit (WebDriver mD, String Xpath){
				mD.findElement(By.xpath(Xpath)).submit();
			}
			
			public static String field_gettext (WebDriver mD, String Xpath){
				return mD.findElement(By.xpath(Xpath)).getText();
			}
			
			public static boolean field_exist (WebDriver mD, String Xpath){
				//return mD.findElement(By.xpath(Xpath)).isDisplayed();
				return mD.findElement(By.linkText(Xpath)).isDisplayed();		
						
			}
			
			public static void field_link (WebDriver mD, String name){
				mD.findElement(By.linkText(name)).click();
			}

	/*		public static void logout (WebDriver mD, String Xpath, int sleep1, String log  ) throws InterruptedException{
				mD.findElement(By.xpath(Xpath)).click();
				Thread.sleep(sleep1);
				mD.findElement(By.linkText(log)).click();
				//mD.close();
				Thread.sleep(sleep1);*/
				
				
			
			public static void logout (WebDriver mD, String Xpath) throws InterruptedException
			{
				Thread.sleep(1000);
				WebElement element = mD.findElement(By.xpath(Xpath));	// log out 		 
		        Actions action1 = new Actions(mD);	 
		        action1.moveToElement(element).moveToElement(mD.findElement(By.xpath(".//*[@id='ctl00_ctl00_AppHeader_btnLogout']"))).click().build().perform();
			}
			
			
			
			public static void logout1 (WebDriver mD, String Xpath, String Xpath1) throws InterruptedException
			{
				Thread.sleep(2000);
				WebElement element = mD.findElement(By.xpath(Xpath));	// log out 		 
		        Actions action1 = new Actions(mD);	 
		        action1.moveToElement(element).moveToElement(mD.findElement(By.xpath(Xpath1))).click().build().perform();
			}
			
			
			
			
			
			public static void dropdown (WebDriver mD, String header_link, String inner_button) throws InterruptedException
			{
				Thread.sleep(2000);
				WebElement element = mD.findElement(By.xpath(header_link));	// log out 		 
		        Actions action1 = new Actions(mD);	 
		        action1.moveToElement(element).moveToElement(mD.findElement(By.xpath(inner_button))).click().build().perform();
			}
			
			
			public static void Admin_Setup (WebDriver mD) throws InterruptedException{
				
				Thread.sleep(2000);									
				WebElement element3 = mD.findElement(By.xpath(".//*[@id='ctl00_AppHeader_liSetup']/a"));		 // clicking on Setup 	 
		        Actions action3 = new Actions(mD);	 
		        action3.moveToElement(element3).moveToElement(mD.findElement(By.xpath(".//*[@id='ctl00_AppHeader_liSetup_Stores']/a"))).click().build().perform();	
			}

			
			
			public static String Date_Change(String date, int days) {
				
				
				String format = "mm/dd/yyyy";
			   // date = "11/06/2015";
			   String d = date;
			    SimpleDateFormat simpleFormat = new SimpleDateFormat("mm/dd/yyyy");

			    java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			    java.util.Calendar calendar = java.util.Calendar.getInstance();

			    try {
			        calendar.setTime(df.parse(d));
			        calendar.add(java.util.Calendar.DAY_OF_MONTH, days);
			        String formatted = simpleFormat.format(calendar.getTime());
			       // System.out.println(formatted);
			        return formatted;
			    } catch (Exception ex) {
			        System.out.println("Error: " + ex.toString());
			        return ex.toString();
			    }
			    
			}
			
			public static String current_date () {
				 
				 // Create object of SimpleDateFormat class and decide the format
				 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
				 
				 //get current date time with Date()
				 Date date = new Date();
				 
				 // Now format the date
				 String date1= dateFormat.format(date);
				 
				 // Print the Date
				 System.out.println(date1);
				return date1;
				 
				 
				 }
			
			
		
			// use the below FUNCTION FOR ALL DATE FUNCTIONS
			
			public static String date_auto_change(int days) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Now use today date.
				c.add(Calendar.DATE, days); // Adding 5 days
				String output = sdf.format(c.getTime());
				System.out.println(output);
			    return output;
			}
			

			
	public static void index_select (WebDriver mD,String Xpath, int indexvalue) throws InterruptedException {
		
		Select IncomeType = new Select(mD.findElement(By.xpath(Xpath)));
		IncomeType.selectByIndex(indexvalue);
	}
			
			
	public static  String split(WebDriver mD, String Xpath, int value){
		String b = null;
		   String strMain = mD.findElement(By.xpath(Xpath)).getText();
		    String[] arrSplit = strMain.split(" ");
		    for (int i=value; i< arrSplit.length; i++)
		    {
		    	 String a =   (arrSplit[i]);
		    	 b = a;
		     }
			return b;	    
		    
		  }	
			
	
	
	
	public static  String split1(WebDriver mD, String Xpath, int value){
		String b = null;
		   String strMain = mD.findElement(By.name(Xpath)).getText();
		    String[] arrSplit = strMain.split(" ");
		    for (int i=value; i< arrSplit.length; i++)
		    {
		    	 String a =   (arrSplit[i]);
		    	 b = a;
		     }
			return b;	    
		    
		  }	
			
	
	
	
	
	public static void selectChk(WebDriver mD,String v) throws Exception {
		try {
		    mD.findElement(By.xpath(".//* [@id='ctl00_ctl00_MainContent_ChildContent1_frmClear_dgCashed_ctl"+v+"_chkSelect']")).click();
		    Thread.sleep(1000);
		} catch (AssertionError Ae) {
		    Ae.printStackTrace();
		}
		}
			
	
	
	
	
	
	public static void window(WebDriver mD) throws InterruptedException{
	
	String parent=mD.getWindowHandle();
    
	Set<String>s1=mD.getWindowHandles();
	
	Iterator<String> I1= s1.iterator();
	 
	while(I1.hasNext())
	{
	 
	   String child_window=I1.next();
	
	   if(!child_window.equals(parent))
	   {
		   mD.switchTo().window(child_window);
	    
	   System.out.println(mD.switchTo().window(child_window).getTitle());
	   Thread.sleep(2000);
	    
	   mD.close();
	   
	  
	   }
	   
	   
	   }
	mD.switchTo().window(parent);
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
