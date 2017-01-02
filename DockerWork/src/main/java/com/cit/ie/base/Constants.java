package com.cit.ie.base;

import org.testng.Assert;

/**
 * @name Constants
 * @author gearyk2
 */
public class Constants extends Assert
{

	public static final String V3_348 = "000196700348";

	/**
	 * USER CREDENTIALS
	 */
	public static String ADMIN = "smc";
	public static String MONITOR = "monitor";

	/**
	 * REST QUERY FILTERS
	 */
	public static final String GREATER_THAN = "%3e";
	public static final String LESS_THAN = "%3c";
	public static final String LIKE = LESS_THAN+"like"+GREATER_THAN;
	public static final String SPACE = "%20";

	/**
	 * REST HTTP STATUS CODES
	 */ 
	//1XX - informational
	public static final int CONTINUE = 100;
	//2XX - success
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int ACCEPTED = 202;
	public static final int NO_CONTENT = 204;
	//3XX - redirection
	public static final int NOT_MODIFIED = 304;
	//4XX - client error
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int CONFLICT = 409;
	//5XX - server error
	public static final int INTERNAL_SERVER_ERROR = 500;

	/**
	 * REST METHODS
	 */
	public static final String GET = "GET";
	public static final String DELETE = "DELETE";
	public static final String POST = "POST";
	public static final String PUT = "PUT";

	/**
	 * MISC
	 */
	public static final String EMPTY_VALUE = "EMPTY";
	public static final long SYMAPI_NA = 4294967295L;
	public static final int VOLUME_LENGTH = 5;
	public static final String BASE_REST_URL="/univmax/restapi/sloprovisioning/symmetrix/";
	public static final String RETRIEVING ="//div[@aria-hidden='false']//div[text()='Retrieving data']";
	public static final String BAD_LOGIN_XPATH=".//*/span[text()='Error Logging In']";

}

