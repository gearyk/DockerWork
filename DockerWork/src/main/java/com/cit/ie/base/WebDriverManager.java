package com.cit.ie.base;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



/**
 * @author gearyk2
 * @description Create an instance of WebDriverManager for each thread
 */
@SuppressWarnings("deprecation")
public class WebDriverManager {

	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters({"browser","hubIP"})
	@BeforeMethod()
	public void launchbrowser(@Optional("firefox")String browser,@Optional("10.73.28.229")String hubIP) throws MalformedURLException, InterruptedException {
		System.out.println("BEFORE METHOD CALLED");	
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://"+hubIP+":4444/wd/hub";
			threadDriver = new ThreadLocal<RemoteWebDriver>();
			threadDriver.set(new RemoteWebDriver(new URL(Node), cap));
			long id = Thread.currentThread().getId();
			System.out.println("TestRunning. Thread id is: " + id);		
		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println(" Executing on FIREFOX");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setCapability("marionette", false); 
			cap.setCapability(FirefoxDriver.PROFILE, new FirefoxProfile()); 
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			String Node = "http://"+hubIP+":4444/wd/hub";
			threadDriver = new ThreadLocal<RemoteWebDriver>();
			threadDriver.set(new RemoteWebDriver(new URL(Node), cap));
			long id = Thread.currentThread().getId();
			System.out.println("TestRunning. Thread id is: " + id);	
		}

		else {
			//driver = new ThreadLocal<WebDriver>();
			//driver.set(new ChromeDriver());
			System.out.println("in webdriver manager setup Chrome driver");
		}
	}

	public WebDriver getDriver(){
		return threadDriver.get();
	}

	@AfterMethod
	public void closeBrowser() {
		System.out.println("BEFORE METHOD CALLED");	
		getDriver().quit();

	}

	@SuppressWarnings("resource")
	public void findRemote(RemoteWebDriver driver) throws IOException,JSONException {
		HttpCommandExecutor ce = (HttpCommandExecutor)driver.getCommandExecutor();
		ce.getAddressOfRemoteServer();
		ce.getAddressOfRemoteServer().getHost();
		String HubIP=ce.getAddressOfRemoteServer().getHost();
		int HubPort=ce.getAddressOfRemoteServer().getPort();
		HttpHost host = new HttpHost(ce.getAddressOfRemoteServer().getHost(), HubPort);
		DefaultHttpClient client = new DefaultHttpClient();
		URL testSessionApi = new URL("http://" + HubIP + ":" + HubPort + "/grid/api/testsession?session=" +  driver.getSessionId());
		BasicHttpEntityEnclosingRequest r = new  BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm());
		HttpResponse response  = client.execute(host,r);
		System.out.println(response);
		System.out.println(response.getEntity());
		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		@SuppressWarnings("unused")
		String proxyID =(String) object.get("proxyId");
		System.out.println("in find remote - leaving");
	}

}


