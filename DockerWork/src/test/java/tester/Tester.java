package tester;
import java.util.UUID;

import com.cit.ie.rest.RESTClient;

/**
 * 
 * @author gearyk2
 * @description Class to test things as a I go - will eventuall be deleted
 */
public class Tester extends RESTClient{

	public static void main(String[] args) {
	
		RESTClient.username="smc";
		RESTClient.password="smc";
		//GET("https://152.62.122.187:8443/univmax/restapi/82/sloprovisioning/symmetrix/000196700348");
		//System.out.println(responseOutput);
		String rand=UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(rand.substring(0, 6));;

	}

}
