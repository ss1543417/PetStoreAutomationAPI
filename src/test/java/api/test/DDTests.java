package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	
	@Test(priority=1, dataProvider ="Data", dataProviderClass=DataProviders.class ) //we will provide data from DataProvider method which we created 
	//If DataProvider method is present in same class then we don't have to provide 3rd parameter above
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph){ //Creating User by using "Data" name DataProvider method
	//the parameters should be in same order as per provided in excel sheet
		
		User userPayload = new User(); //Here User is the Pojo class
		
		//userPayload obj is used to set the values taken from excel sheet
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName); 
		userPayload.setFirstName(fname); 
		userPayload.setLastName(lname); 
		userPayload.setEmail(useremail); 
		userPayload.setPassword(pwd); 
		userPayload.setPhone(ph); 
		
		Response response =	UserEndPoints.createUser(userPayload);
		//Here we are calling createUser mtd and passing userPayload data and in return its sending response which we are storing in variable
		
	    AssertJUnit.assertEquals(response.getStatusCode(), 200); //Validating status code as 200
		
		}
	
//	@Test(priority=2, dataProvider ="UserNames", dataProviderClass=DataProviders.class)
//	public void testGETUserByName(String userName) { //this mtd will get the User with the Username taken
//		
//		Response response =	UserEndPoints.readUser(userName);
//		Assert.assertEquals(response.getStatusCode(), 200); 
//	}
//	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) { //this mtd will delete the User with the Username taken
		
		Response response =	UserEndPoints.deleteUser(userName);
		AssertJUnit.assertEquals(response.getStatusCode(), 200); 
	}
	
	
}
