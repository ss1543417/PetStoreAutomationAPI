package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		
		String path = System.getProperty("user.dir")+"/testData/Userdata.xlsx";
		//String path = "/PetStoreAutomationAPI/testData/Userdata.xlsx";
		//here we are passing the path for excel file
		XLUtility xl = new XLUtility(path); //we are passing the path in XLUtility to get to the excel file
		
		int rownum = xl.getRowCount("Sheet1"); //here using obj of XLUtility we are getting the rowcount by passing sheetname
		int colcount = xl.getCellCount("Sheet1", 1); //here using obj of XLUtility we are getting the colcount by passing sheetname and rownumber
		
		String apidata[][] = new String[rownum][colcount]; //here we are storing size of excel in 2D array
		//here apidata 2D array can store n no.of rows and col provided by excelsheet as we are passing rownum and colcount to 2D array
		for(int i=1;i<rownum;i++) { //this loop is used to store excel data in above 2D array
			
			for(int j=0;j<colcount;j++) {
				
				apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata; //this is returning all the data of the User from excel sheet
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException { //here we are only passing UserNames of User i.e y we are creating 1D array
		
		String path = System.getProperty("user.dir")+"/testData/Userdata.xlsx";
		//String path = "/PetStoreAutomationAPI/testData/Userdata.xlsx";
		
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("Sheet1"); 
		
		String apidata[] = new String[rownum]; //we are only passing rownum not col as we don't need multiple data's
		
		for(int i=1;i<rownum;i++) {
			
			apidata[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}
	  
}
