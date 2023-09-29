package api.endpoints;

/*
 * Swagger URL: https://petstore.swagger.io
 * 
 * Create user(Post): https://petstore.swagger.io/v2/user
 * Get user(GET): https://petstore.swagger.io/v2/user/{username}
 * Update user(PUT): https://petstore.swagger.io/v2/user/{username}
 * Delete user(DELETE): https://petstore.swagger.io/v2/user/{username}
 * Base url + endpoint: (https://petstore.swagger.io/v2) + (/user/{username})
 */

public class Routes {

	//here we keep the base url as:
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User module
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//Store module
	
	  //Here you will create Store module URL's
	
	//Pet module
	
	  //Here you will create Pet module URL's
	
	
	
	
}
