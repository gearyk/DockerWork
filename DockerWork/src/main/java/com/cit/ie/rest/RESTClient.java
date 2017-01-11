package com.cit.ie.rest;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.ws.rs.core.Response.Status;

import org.testng.Assert;

import com.cit.ie.base.Constants;
import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author gearyk2
 */
@SuppressWarnings("deprecation")
public class RESTClient extends Constants
{
	public static String responseFormat = "json";
	public static String username, password = null;
	public static int responseStatus;
	public static Status responseStatusText;
	public static String responseOutput;
	public static URI responseLocation;
	public static String previousCall;
	public static String url;
	public static String param;
	public static String parsed, value = null;
   
	/**
	 * @name GETString
	 * @description REST GET call
	 * @param restCall
	 * @return responseOutput
	 */
	public static String GETString(String restCall) {
		GET(restCall);
		return responseOutput;
	}
	
	/**
	 * @name GET
	 * @description REST GET call
	 * @param restCall
	 * @return responseStatus
	 */
	public static int GET(String restCall) {
		url = restCall;
		previousCall = GET;
		if(restCall.contains(" ")) {
			restCall = restCall.replaceAll(" ", "%20");
		}
		ssl();
		if(username == null && password == null){
			username = ADMIN;
			password = ADMIN;
		} 
        String authString = username+":"+password;
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client client = Client.create();
        WebResource wr = client.resource(restCall);
        ClientResponse response = null;
        if(isJson()) {
        	response = wr.accept("application/json").header("Authorization", "Basic " + authStringEnc)
        		.get(ClientResponse.class);
        } else if(isXml()){
        	response = wr.accept("application/xml").header("Authorization", "Basic " + authStringEnc)
            		.get(ClientResponse.class);
        }
    
        responseLocation = response.getLocation();
        responseOutput = response.getEntity(String.class);
        responseStatus = response.getStatus();
        responseStatusText = response.getResponseStatus(); 
        return responseStatus;
	}
	
	/**
	 * @name DELETEString
	 * @description REST DELETE call
	 * @param restCall
	 * @return responseOutput
	 */
	public static String DELETEString(String restCall) {
		DELETE(restCall);
		return responseOutput;
	}
	
	/**
	 * @name DELETE
	 * @description REST DELETE call
	 * @param restCall
	 * @return responseStatus
	 */
	public static int DELETE(String restCall) {
		url = restCall;
		previousCall = DELETE;
		ssl();
		if(username == null && password == null){
			username = ADMIN;
			password = ADMIN;
		}
        String authString = username+":"+password;
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client client = Client.create();
		WebResource wr = client.resource(restCall);
		ClientResponse response = null;
		if(isJson()){
			response = wr.type("application/json").header("Authorization", "Basic " + authStringEnc)
					.delete(ClientResponse.class);
		} else if(isXml()){
			response = wr.type("application/xml").header("Authorization", "Basic " + authStringEnc)
					.delete(ClientResponse.class);
		}
		
        responseLocation = response.getLocation();
        responseStatus = response.getStatus();
        responseStatusText = response.getResponseStatus();  
        if (responseStatus != 204) {
            responseOutput = response.getEntity(String.class);
		}
        
        return responseStatus;
	}
	
	/**
	 * @name POSTString
	 * @description REST POST call
	 * @param restCall
	 * @return responseOutput
	 */
	public static String POSTString(String restCall, String params) {
		POST(restCall, params);
		return responseOutput;
	}
	
	/**
	 * @name POST
	 * @description REST POST call
	 * @param restCall, params
	 * @return responseStatus
	 */
	public static int POST(String restCall, String params) {
		url = restCall;
		param = params;
		previousCall = POST;
		ssl();
		if(username == null && password == null){
			username = ADMIN;
			password = ADMIN;
		}
        String authString = username+":"+password;
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client client = Client.create();
        WebResource wr = client.resource(restCall);
        
        ClientResponse response = null;
        if(isJson()) {
        	response = wr.type("application/json").accept("application/json")
        			.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, params);
        } else if(isXml()) {
        	response = wr.type("application/xml").accept("application/json")
        			.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, params);
        }
        
        responseLocation = response.getLocation();
        responseOutput = response.getEntity(String.class);
        responseStatus = response.getStatus();
        responseStatusText = response.getResponseStatus();  
        return responseStatus;
	}
	
	/**
	 * @name PUTString
	 * @description REST PUT call
	 * @param restCall, params
	 * @return responseOutput
	 */
	public static String PUTString(String restCall, String params) {
		PUT(restCall, params);
		return responseOutput;
	}
	
	/**
	 * @name PUT
	 * @description REST PUT call
	 * @param restCall, params
	 * @return responseStatus
	 */
	public static int PUT(String restCall, String params) {
		url = restCall;
		param = params;
		previousCall = PUT;
		ssl();
		if(username == null && password == null){
			username = ADMIN;
			password = ADMIN;
		}
        String authString = username+":"+password;
        String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client client = Client.create();
        WebResource wr = client.resource(restCall);
        
        ClientResponse response = null;
        if(isJson()) {
	        response = wr.type("application/json").accept("application/json")
	        		.header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, params);
        } else if(isXml()) {
        	 response = wr.type("application/xml").accept("application/json")
 	        		.header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, params);
        }
        
        responseLocation = response.getLocation();
        responseStatus = response.getStatus();
        responseStatusText = response.getResponseStatus();  
        if (responseStatus != 204) {
            responseOutput = response.getEntity(String.class);
		}

        return responseStatus;
	}
	
	/**
	 * @name ssl
	 * @description Bypass ssl certs
	 */
	public static void ssl() {
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("TLSv1.2");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		try {
			sc.init(null, new TrustManager[] { new TrustAllX509TrustManager() }, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
		    public boolean verify(String string, SSLSession ssls) {
		        return true;
		    }
		});
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}
	
	/**
	 * @name printResponses();
	 * @description Prints responses and other information helpful for writing tests
	 */
     public static void printResponses() {
    	 System.out.println("Method: "+previousCall);
    	 System.out.println("URL: "+url);
    	 
    	 if(previousCall == "POST" || previousCall == "PUT"){
    		 System.out.println("Request Body: "+param);
    	 }
    	 
    	 if(responseLocation!=null) {
    		 System.out.println("Response Location: "+responseLocation);
    	 }
    	 
    	 System.out.println("Response Status: "+responseStatus);
    	 System.out.println("Response Status Text: "+responseStatusText);
    	 System.out.println("Response Output: "+responseOutput+"\n");
     }
	
	/**
	 * @name useJson
	 * @description Select json format
	 */
	public static void useJson() {
		RESTClient.responseFormat = "json";
	}

	/**
	 * @name useXml
	 * @description Select xml format
	 */
	public static void useXml() {
		RESTClient.responseFormat = "xml";
	}

	/**
	 * @name isJson
	 * @description Return if RESTClient.responseFormat equals json
	 * @return boolean
	 */
	public static boolean isJson() {
		if(RESTClient.responseFormat == "json"){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @name isXml
	 * @description Return if RESTClient.responseFormat equals xml
	 * @return boolean
	 */
	public static boolean isXml() {
		if(RESTClient.responseFormat == "xml"){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @name refreshRestDB
	 * @description A simple post to refresh the REST Database
	 * @param url
	 * @example https://10.73.28.231:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/
	 */
	public static void refreshRestDB(String storageGroupurl) {
		String rand=UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		String json="{\"srpId\": \"None\",\"storageGroupId\": \"0madeup"+rand+"\",\"create_empty_storage_group\": true}";
		POST(storageGroupurl,json);
		//printResponses();
		Assert.assertEquals(responseStatus,200);
		DELETE(storageGroupurl+"0madeup"+rand);
		//printResponses();
		Assert.assertEquals(responseStatus,204);
		
	}
	
	
	/**
	 * Turn any json into readable format. i.e storageGroup=exampleSg
	 * @param json
	 */
	public static String getReadableString(String json) {
		if(json.contains("[]")) {
			 json = json.replace("[]", "[EMPTY]");
		}
		if(json.contains("\":\"\"")) {
			json = json.replace("\":\"\"", "\":\"EMPTY\"");
		}
        try {
		parsed = json.replace("[", "");
		parsed = parsed.replace("]", "");
		parsed = parsed.replace("{", "");
		parsed = parsed.replace("}", "");
		parsed = parsed.replace("\":\"", "=");
		parsed = parsed.replace("\",\"", " ");
		parsed = parsed.replace(",\"", " ");
		parsed = parsed.replace("\":", "=");
		parsed = parsed.replace("\"", "");
		
        return parsed;
        } catch (com.codesnippets4all.json.exceptions.JSONParsingException e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	/**
	 * @name getReadableString
	 * @description Parses a GET or GETLIST JSON string from the standard JSON format, ie."num_of_masking_views": 0
	 * 				to a custom format ie. num_of_masking_views=0 so that it can more easily be used for other methods and for
	 * 				verification.
	 * @param json, key
	 * @return parsed
	 */
	public static String getReadableString(String json, String key) {
		if(json.contains("[]")) {
			 json = json.replace("[]", "[EMPTY]");
		}
		if(json.contains("\":\"\"")) {
			json = json.replace("\":\"\"", "\":\"EMPTY\"");
		}
		JsonParserFactory factory = JsonParserFactory.getInstance();
        JSONParser parser = factory.newJsonParser();
        try {
		Map<?, ?> jsonMap = parser.parseJson(json); 
		parsed = jsonMap.get(key).toString();
		parsed = parsed.replace("[", "");
		parsed = parsed.replace("]", "");
		parsed = parsed.replace("{", "");
		parsed = parsed.replace("}", "");
        return parsed;
        } catch (com.codesnippets4all.json.exceptions.JSONParsingException e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
