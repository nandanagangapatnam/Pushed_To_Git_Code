package RateSheets;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Functions.Function_Classes;
import Utility.Utils;
import jxl.read.biff.BiffException;

public class ConsumerLoanRatesheet_OH {

	String vBrowser = "FireFox";
	WebDriver driver;
	String URL = "http://ecashtest.corp.checksmart.com/logon.aspx?ReturnUrl=%2f";
	String Username, Password, LoggedName;
	int vSleep;
	String xpath, date, date1, Loan_number,new_number;
	String xlURL, xlUsername, xlPassword, xlStore, xlLoggedname, xlLoanAmt,xlCustFName, xlCustLName, xlssn, xldob, xlRoutingno, xlbankname, xlaccno, xlcheckno;
	String[][] xlData;
	String[][] xl_OH_Data;
	

	public static Properties OR=null;
	
	//@BeforeTest	
	@Test (priority = 1)
	public void Url_search () throws InterruptedException{		
		Thread.sleep(1000);
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
		
		//xlData = Utils.readXL(path,"TestData");	
				
		//Utils.setExcelFile(xlPath,"Login");
		
			xlUsername = xlData [1][0];
			xlPassword = xlData [1][1];
			xlStore = xlData [1][2];
			xlLoggedname = xlData [1][3];	
			vSleep = 2000;

			Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );

		Thread.sleep(2000);
		xpath=".//*[@id='txtUsername']";  // Clearing User Name field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtUsername']";  // Passing User name 
		Function_Classes.field_sendKeys(driver, xpath, xlUsername);
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Clearing Password field
		Function_Classes.field_clear(driver, xpath );
		
		Thread.sleep(1000);
		xpath=".//*[@id='txtPasswd']";  // Passing Password
		Function_Classes.field_sendKeys(driver, xpath, xlPassword);
		
		Thread.sleep(1000);
		xpath = ".//*[@id='btnSubmit']"; // Click on go button
		Function_Classes.field_click(driver, xpath);
		
		Thread.sleep(1000);
		xpath=".//*[@id='ddlStore']";  // selecting the state 
		//String keys = "9998 Business date is always current date, TN";
		Function_Classes.field_sendKeys(driver, xpath, xlStore);

		Thread.sleep(1000);
		xpath = ".//*[@id='btnSelStore']"; // clicking ok 
		Function_Classes.field_click (driver, xpath);
		
		Thread.sleep(vSleep+3000);			

	}
	
			////////////////
	@Test (priority = 5)
			public void Verify_Loan_Amt_OH () throws Exception{
		

				xlCustFName = xlData [1][4];
							
				System.out.println("Verify_Loan_Amt_OH     -- Started");
				xpath = ".//*[@id='Left']/a[5]"; // Click on Customer left navigation button
				Function_Classes.field_click(driver, xpath);
					
				Thread.sleep(3500);
				xpath = ".//*[@id='ctl00_MainContent_CustSrch_txtName']"; // enter cust search  value  
				Function_Classes.field_sendKeys(driver, xpath, xlCustFName);				
					
				
				Thread.sleep(3500);
				xpath =".//*[@id='ctl00_MainContent_CustSrch_btnSubmit']";  // click on Search button
				Function_Classes.field_click(driver,xpath);
				
				
				Thread.sleep(3500);
				String status = Function_Classes.field_gettext(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lblStatus']");// reaing customer status
				System.out.println("status  is "  + status);
				
				Thread.sleep(3500);
				if (status.equals("Bad") ){
					
					Thread.sleep(2000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkEditCustomer']");// click on edit customer link
				
					Thread.sleep(3500);
					Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_cboStatus']", "Good");  // changing status to Good
				
					Thread.sleep(3500);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_btnSubmit']");// click on save button
				
					Thread.sleep(3500);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					Function_Classes.field_click(driver,xpath);
				}
				else {
					Thread.sleep(2000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_FrmCustGeneral_lnkNewTrans']"; // clicking on the $ button
					Function_Classes.field_click(driver,xpath);
					
				}
				

				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkNewAdvance']";  // Clicking on New Advance
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_ddlLoanModel']", "Consumer Loan");
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
					
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep1_Submit']";  // click on Next button 
				Function_Classes.field_click(driver,xpath);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_chkIsCheckOnDemand']";  // click on check box 
				Function_Classes.field_click(driver,xpath);
	}
	
	@Test (priority = 6)
	public void readExcel() throws BiffException, IOException, InterruptedException {
		String FilePath = "C:\\Excel\\OhioConsumerLoanFeeCharteCash.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		HSSFWorkbook wb = new HSSFWorkbook(fs);


		// TO get the access to the sheet
		HSSFSheet sheet=wb.getSheetAt(0);
		
                      
		int	xRows = sheet.getLastRowNum();                                
		int	xCols = sheet.getRow(0).getLastCellNum();   
		System.out.println("Total Rows in Excel are " + xRows);
		System.out.println("Total Cols in Excel are " + xCols);
		String loanamount = "";
		xl_OH_Data = new String[xRows][xCols];        
		for (int i = 2; i < xRows; i++) {                           
				HSSFRow row = sheet.getRow(i);
				for (int j = 3; j < xCols; j++) {                               
					HSSFCell cell = row.getCell(j);
					if (cell!=null){
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						{
							//System.out.print(cell.getCellType());
							System.out.print(cell.getStringCellValue()+" ");
						}
						else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
						{
							//System.out.print(cell.getCellType());
							System.out.print(cell.getNumericCellValue()+" ");
						}
						else
						{
						    if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
						         System.out.println("Formula is " + cell.getCellFormula());
						         switch(cell.getCachedFormulaResultType()) {
						             case HSSFCell.CELL_TYPE_NUMERIC:
						                 System.out.println("Last evaluated as: " + cell.getNumericCellValue());
						                 break;
						             case HSSFCell.CELL_TYPE_STRING:
						                 System.out.println("Last evaluated as \"" + cell.getRichStringCellValue() + "\"");
						                 break;
						         }
						      }
						}
					}
					xl_OH_Data[i][j] = loanamount;   
					
					System.out.println(loanamount);
					
					Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  // entering the Loan Amount 
					//String checkAmount = xlData [1][12];
					Function_Classes.field_sendKeys(driver, xpath, loanamount);


					Thread.sleep(3000);	           
			        Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");// click on Next button
					
		     	    Thread.sleep(3000);	           
		            Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
		            
		            Thread.sleep(3000);
					xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched']/tbody/tr[2]/td[9]";  // checking the payoff amount 
					String payoff = Function_Classes.field_gettext(driver,xpath);// reading pay off amount"
					System.out.println(payoff);
					
	                Thread.sleep(3000);
					Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");  // Click on Finish button
					}        
				// System.out.println("");
				}                                      
		
		HSSFRow row; 
		HSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();
		
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
				    if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
				         System.out.println("Formula is " + cell.getCellFormula());
				         switch(cell.getCachedFormulaResultType()) {
				             case HSSFCell.CELL_TYPE_NUMERIC:
				                 System.out.println("Last evaluated as: " + cell.getNumericCellValue());
				                 break;
				             case HSSFCell.CELL_TYPE_STRING:
				                 System.out.println("Last evaluated as \"" + cell.getRichStringCellValue() + "\"");
				                 break;
				         }
				      }
				}
			}
			System.out.println();
		}
	
	}

				
	/*			Thread.sleep(3000);
				xl_OH_Data = Utils.readXL(OR.getProperty("ratepath"),"Sheet1");		
				
				xlLoanAmt = xl_OH_Data [2][3];
				System.out.println(xlLoanAmt);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtLoanAmount']";  // entering the Loan Amount 
				//String checkAmount = xlData [1][12];
				Function_Classes.field_sendKeys(driver, xpath, xlLoanAmt);
				
				Thread.sleep(3000);
				xpath = ".//*[@id='HeaderRight']/div/table/tbody/tr/td[1]/span[3]";  // checking the business date 
				String Business_date = Function_Classes.field_gettext(driver, xpath);
				System.out.println(Business_date);
				Function_Classes.field_click(driver,xpath);
				driver.findElement(By.xpath(xpath)).sendKeys(Keys.HOME + "02/14/2017"); */

	@Test (priority = 7)
	public void Verify_Loan_Amt_OH1 () throws Exception{
		
			/*	Thread.sleep(3000);	           
		        Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep2_Submit']");// click on Next button
				
	     	    Thread.sleep(3000);	           
	            Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep3_Submit']");// click on Next button
	            
	            Thread.sleep(3000);
				xpath = ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_dgS4PmtSched']/tbody/tr[2]/td[9]";  // checking the payoff amount 
				String payoff = Function_Classes.field_gettext(driver,xpath);// reading pay off amount" 
				System.out.println(payoff);
				
                Thread.sleep(3000);
				Function_Classes.field_click(driver,".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnStep4_Submit']");*/  // Click on Finish button
				
			/*	File f = new File("C:\\Automation QA\\garima.pdf");

			     PDFParser parser = new PDFParser(new FileInputStream(f));
			     parser.parse();

			     COSDocument cosDoc = parser.getDocument();
			     PDDocument pdDoc = new PDDocument(cosDoc);

			     PDFTextStripper pdfStripper = new PDFTextStripper();
			     String parsedText = pdfStripper.getText(pdDoc);

			//     System.out.println(parsedText);

			     //Write parsed text into a file
			     PrintWriter pw = new PrintWriter("Path_to_output_text_file");
			     pw.print(parsedText);
			     pw.close(); */
				
				Thread.sleep(3500);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_LoanDetail_lnkLoanId']" );  // Clicking on Loan ID
				
				Thread.sleep(3000);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_lnkDelete']/span" );  // Clicking on Void Entire Loan button
				
				Thread.sleep(3000);				
				Function_Classes.field_sendKeys(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_txtReason']", "Test" );  // Enter Reason
				
				Thread.sleep(3000);				
				Function_Classes.field_click(driver, ".//*[@id='ctl00_ctl00_MainContent_ChildContent1_btnSubmit']" );  // Clicking on Void Entire Loan button		
				
				Thread.sleep(3000);	
				 Function_Classes.logout(driver, ".//*[@id='menuv2']/li[7]/a"); // for log out 

				 System.out.println("Verify_Loan_Amt_TN     -- Passed");
				 System.out.println("_4_a_Verify_Loan_Amt_TN     -- Passed");

					 Thread.sleep(2000);
					 driver.close();

			}

	
				
		}
			
				
			
			