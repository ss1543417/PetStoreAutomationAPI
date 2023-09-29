package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for perform Create, Read, Update, Delete requests to the user API.

public class UserEndPoints {

	/*
	 * Here for all endpoints method implementation, the parameters values we will 
	 * send through the test cases and validate the response through the test cases
	 */
	
	//Implementation of POST method
	public static Response createUser(User payload) //here payload is for userdata which actually consists UserBody
	{ //Here we imported User class from payload package
		
	Response response =	given()//Pre-requiste
		    .contentType(ContentType.JSON) //these two params we are passing as headers in our request body
		    .accept(ContentType.JSON)
		    .body(payload) //here we are passing the request body
		.when()
		.post(Routes.post_url); //here we are passing the request url
		
	    return response;
		
	   //here we don't have then as we return the response
		
	}
	
	//Implementation of GET method
	public static Response readUser(String userName) //here the mtd will accept the param reqd for GET mtd
	{
		
	 Response response = given()//Pre-requiste
		    .pathParam("username", userName) 
		.when()
		.get(Routes.get_url); 
	//here "username" variable should be same as defined in the geturl so that it will be substituted inside the getURL
	    return response;
		
	   //here we don't have then as we return the response
		
	}
	
	//Implementation of PUT method
	public static Response updateUser(String userName,User payload) //here payload is for userdata which actually consists UserBody
	{
		
	Response response =	given()//Pre-requiste
		    .contentType(ContentType.JSON) //these two params we are passing as headers in our request body
		    .accept(ContentType.JSON)
		    .pathParam("username", userName)
		    .body(payload) //here we are passing the request body
		.when()
		.put(Routes.update_url); //here we are passing the update url
		
	    return response;
		
	   //here we don't have then as we return the response
		
	}
	
	//Implementation of DELETE method
	public static Response deleteUser(String userName) //here the mtd will accept the param reqd for GET mtd
	{
		
	 Response response = given()//Pre-requiste
		    .pathParam("username", userName) 
		.when()
		.delete(Routes.delete_url); 
	//here "username" variable should be same as defined in the geturl so that it will be substituted inside the getURL
	    return response;
		
	   //here we don't have then as we return the response
		
	}
}
