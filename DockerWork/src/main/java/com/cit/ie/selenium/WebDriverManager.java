package com.cit.ie.selenium;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;



/**
 * 
 * @author gearyk2
 * @description Create an instance of WebDriverManager for each thread
 */
@SuppressWarnings("deprecation")
public class WebDriverManager {
	
	public String URL, Node;
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
    public ThreadLocal<WebDriver> driver=null;
    public static String baseURL;

    @Parameters("browser")
	@BeforeTest()
	public void launchbrowser(String browser) throws MalformedURLException {
		
    	if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://locahost:4444/wd/hub";
			threadDriver = new ThreadLocal<RemoteWebDriver>();
			threadDriver.set(new RemoteWebDriver(new URL(Node), cap));
		}
			
		else {
				driver = new ThreadLocal<WebDriver>();
                driver.set(new ChromeDriver());
		}
	}
	
	
	@AfterMethod
    public void closeBrowser() {
        if(threadDriver!=null)
        {
            getDriver().quit();
        }
        else
        {
           driver.get().quit();
        }

    }
	
	 public WebDriver getDriver()
	    {
	        System.out.println(threadDriver);
	        if(threadDriver==null)
	        {
	            System.out.println("returning driver");
	            return driver.get();
	        }
	        else
	        {
	            System.out.println("returning thread driver");
	            return threadDriver.get();
	        }
	    }
	 
	 @SuppressWarnings("resource")
	public void findRemote(RemoteWebDriver driver) throws IOException,JSONException {
	        
		HttpCommandExecutor ce = (HttpCommandExecutor) driver.getCommandExecutor();
        ce.getAddressOfRemoteServer();
        ce.getAddressOfRemoteServer().getHost();
        String HubIP=ce.getAddressOfRemoteServer().getHost();
        int HubPort=ce.getAddressOfRemoteServer().getPort();
        HttpHost host = new HttpHost(ce.getAddressOfRemoteServer().getHost(), HubPort);
        @SuppressWarnings("deprecation")
		DefaultHttpClient client = new DefaultHttpClient();
        URL testSessionApi = new URL("http://" + HubIP + ":" + HubPort + "/grid/api/testsession?session=" +  driver.getSessionId());
        BasicHttpEntityEnclosingRequest r = new  BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm());
        HttpResponse response  = client.execute(host,r);
        System.out.println(response);
        System.out.println(response.getEntity());
        JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
        @SuppressWarnings("unused")
		String proxyID =(String) object.get("proxyId");

	    }
	 
	 
	

}


