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
		GET("https://x.x.x.x:8443/univmax/restapi/82/sloprovisioning/symmetrix/000196700xxx");
		System.out.println(responseOutput);

	}

}
