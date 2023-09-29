package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for perform Create, Read, Update, Delete requests to the user API.

public class UserEndPoints2 {

	/*
	 * Here for all endpoints method implementation, the parameters values we will 
	 * send through the test cases and validate the response through the test cases
	 */
	
  static ResourceBundle getURL(){ //this is a userDefined mtd which is used to take url's from properties file
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		//so getBundle will point the properties file under resources folder we just have to provide properties file name not name with extension properties
		//so basically its loading properties file and returning it as ResourceBundle java object
		return routes;
	}
	
	//Implementation of POST method
	public static Response createUser(User payload) //here payload is for userdata which actually consists UserBody
	{ //Here we imported User class from payload package
		
		//How we are reading url from properties file into Post implementation method
		
	String post_url= getURL().getString("post_url"); //getURL is returning routes which consists all properties file data
	//then we are using getString to get the String url value by passing KeyValue as post_url which is there in properties file
	
		
	Response response =	given()//Pre-requiste
		    .contentType(ContentType.JSON) //these two params we are passing as headers in our request body
		    .accept(ContentType.JSON)
		    .body(payload) //here we are passing the request body
		.when()
		.post(post_url); //here we are passing the request url
		
	    return response;
		
	   //here we don't have then as we return the response
		
	}
	
	//Implementation of GET method
	public static Response readUser(String userName) //here the mtd will accept the param reqd for GET mtd
	{
		String get_url= getURL().getString("get_url");
		
	 Response response = given()//Pre-requiste
		    .pathParam("username", userName) 
		.when()
		.get(get_url); 
	//here "username" variable should be same as defined in the geturl so that it will be substituted inside the getURL
	    return response;
		
	   //here we don't have then as we return the response
		
	}
	
	//Implementation of PUT method
	public static Response updateUser(String userName,User payload) //here payload is for userdata which actually consists UserBody
	{
		
		String update_url= getURL().getString("update_url");
		
	Response response =	given()//Pre-requiste
		    .contentType(ContentType.JSON) //these two params we are passing as headers in our request body
		    .accept(ContentType.JSON)
		    .pathParam("username", userName)
		    .body(payload) //here we are passing the request body
		.when()
		.put(update_url); //here we are passing the update url
		
	    return response;
		
	   //here we don't have then as we return the response
		
	}
	
	//Implementation of DELETE method
	public static Response deleteUser(String userName) //here the mtd will accept the param reqd for GET mtd
	{
	
		String delete_url= getURL().getString("delete_url");
		
	 Response response = given()//Pre-requiste
		    .pathParam("username", userName) 
		.when()
		.delete(delete_url); 
	//here "username" variable should be same as defined in the geturl so that it will be substituted inside the getURL
	    return response;
		
	   //here we don't have then as we return the response
		
	}
}
