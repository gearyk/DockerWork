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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
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
	public void launchbrowser(@Optional("Chrome")String browser) throws MalformedURLException, InterruptedException {

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://152.62.122.187:4444/wd/hub";
			threadDriver = new ThreadLocal<RemoteWebDriver>();
			threadDriver.set(new RemoteWebDriver(new URL(Node), cap));


		}

		else {
			driver = new ThreadLocal<WebDriver>();
			driver.set(new ChromeDriver());
		}
	}


	@AfterTest
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
			// System.out.println("ThreadDriver is Null. Returning driver");
			return driver.get();
		}
		else
		{
			// System.out.println("ThreadDriver is Not Null. Returning thread driver");
			return threadDriver.get();
		}
	}

	//@SuppressWarnings("resource")
	public void findRemote(RemoteWebDriver driver) throws IOException,JSONException {

		HttpCommandExecutor ce = (HttpCommandExecutor) driver.getCommandExecutor();
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

	}






}

