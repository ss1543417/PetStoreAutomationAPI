package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker; //here faker is used to generate the data
	User userPayload;
	
	public Logger logger; //Logger class will be used to genrate logs
	
	@BeforeClass
	public void setup() {
		
		//Here we will generate the data required for POST request using Faker library
		//Then we will send this data to pojo class
		//From Pojo class we will pass data to UserEndPoint request
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode()); 
		//Using userPayload we are calling setId to set Id value using faker class calling its idNumber mtd and hashCode to generate random value
		
		userPayload.setUsername(faker.name().username()); 
		userPayload.setFirstName(faker.name().firstName()); 
		userPayload.setLastName(faker.name().lastName()); 
		userPayload.setEmail(faker.internet().safeEmailAddress()); 
		userPayload.setPassword(faker.internet().password(5, 10)); 
		userPayload.setPhone(faker.phoneNumber().cellPhone()); 
		
		//Now this userPayload is having all the data which we will pass in test cases
		
		//logs
		logger = LogManager.getLogger(this.getClass()); //this LogManager will initate logger from Logger class
		
		logger.debug("debugging.............");
	}

    @Test(priority=1)
	public void testPostUser(){
		
    logger.info("********* Creating User *********"); //now this logger will be used to generate logs
    	
	Response response =	UserEndPoints.createUser(userPayload);
	//Here we are calling createUser mtd and passing userPayload data and in return its sending response which we are storing in variable
	
	response.then().log().all(); //It will log all the response
	
	AssertJUnit.assertEquals(response.getStatusCode(), 200); //Validating status code as 200
	
	logger.info("********* User is created *********");
	}
    
    @Test(priority=2)
    public void testGetUserByName() {
    	
    	logger.info("********* Reading User Info *********");
    	
    	Response response =	UserEndPoints.readUser(this.userPayload.getUsername());
    //here we are passing username value from Payload class and applying in readUser mtd from userEndPoints and hence getting the response
    	
    	response.then().log().all(); //It will log all the response
    	response.statusCode(); //this will also get the statusCode of response
    	AssertJUnit.assertEquals(response.getStatusCode(), 200); //Validating status code as 200
    	
    	logger.info("********* User info is displayed *********");
    	
    }
    
    @Test(priority=3)
    public void testUpdateUserByName() {
    	
    	logger.info("********* Updating User *********");
    	
    	//update data using Payload
    	userPayload.setFirstName(faker.name().firstName()); 
		userPayload.setLastName(faker.name().lastName()); 
		userPayload.setEmail(faker.internet().safeEmailAddress()); //Here we are creating another set of data using faker mtd to update value
		
		
    	
    	Response response =	UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
    	//here as username is not updated so we are taking that and updated userPayLoad from above rest all params are same
    	//response.then().log().all(); //It will log all the response
    	response.then().log().body(); //this will also log the body response
    	response.then().log().body().statusCode(200); //this is rest assured assertion method
    	//Assert.assertEquals(response.getStatusCode(), 200); //Validating status code as 200
    	
    	logger.info("********* User is updated *********");
    	//Checking data after update using GET method
    	Response responseAfterUpdate =	UserEndPoints.readUser(this.userPayload.getUsername());
    	AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }
    
    @Test(priority=4)
    public void testDeleteUserByName() {
    	
    	logger.info("********* Deleting User *********");
    	
     Response response =UserEndPoints.deleteUser(this.userPayload.getUsername());
     //this will take username from userPayLoad and calling deleteUser mtd from UserEndPoints
     AssertJUnit.assertEquals(response.getStatusCode(), 200);
     
     logger.info("********* User deleted *********");
    }
}
