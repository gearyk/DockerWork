package com.cit.ie.rest;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.cit.ie.base.Constants;
import com.cit.ie.rest.TrustAllX509TrustManager;

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
}
