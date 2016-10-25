package com.cit.ie.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



/**
 * 
 * @author gearyk2
 * @description Create an instance of WebDriverManager for each thread
 */
public class WebDriverManager {

	protected WebDriverManager(){
	}

	private static WebDriverManager instance = new WebDriverManager();

	public static WebDriverManager getInstance()
	{
		return instance;
	}


	ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>(){
		@Override
		protected WebDriver initialValue()
		{
			System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
			return new ChromeDriver(); // can be replaced with other browser drivers
		}
	};

	public WebDriver getDriver() // call this method to get the driver object and launch the browser
	{
		return threadLocal.get();
	}

	public void setDriver(WebDriver driver) {
		threadLocal.set(driver);
	}

	public void closeDriver() {
		if (getDriver() != null) {
			try {
				getDriver().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				getDriver().quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		threadLocal.remove();
	}


}
