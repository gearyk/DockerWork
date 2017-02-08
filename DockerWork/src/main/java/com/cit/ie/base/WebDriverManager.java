package com.cit.ie.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



/**
 * @author gearyk2
 * @description Create an instance of WebDriverManager for each thread
 */
@SuppressWarnings("deprecation")
public class WebDriverManager {

	protected ThreadLocal<RemoteWebDriver> threadDriver = null;
	//public ThreadLocal<WebDriver> driver=null;
	public static String appURL;

	@Parameters({"browser","hubIP","applicationURL"})
	@BeforeClass()
	public void launchbrowser(@Optional("firefox")String browser,@Optional("10.73.28.229")String hubIP,@Optional("https://10.73.28.70:8443/univmax") String applicationURL) throws MalformedURLException, InterruptedException {
		
		appURL=applicationURL;
		System.out.println("Setting URL to : "+appURL);
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
			cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);
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
	
	public String getApplicationURL(){
		return appURL;
	}

	public WebDriver getDriver()
	{
		//	        System.out.println(threadDriver);
		//	        if(threadDriver==null)
		//	        {
		//	            System.out.println("returning driver");
		//	            //return driver.get();
		//	        }
		//	        else
		//	        {
		//System.out.println("returning thread driver");
		return threadDriver.get();
		//	        }
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		// if(threadDriver!=null)
		if(!getDriver().toString().contains("null"))
		{   
			System.out.println("Driver has not been closed. Take screenshot and close");
			File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			String SessionID=threadDriver.get().getSessionId().toString();
			FileUtils.copyFile(scrFile, new File("c:\\screenshot\\screenshot_"+SessionID+".png"));

			System.out.println("Browser Still Open: ");	
			getDriver().quit();
			long id = Thread.currentThread().getId();
			System.out.println("Webdriver quit for thread: " + id);	
		}
		else
		{
			//driver.get().quit();
		}
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


