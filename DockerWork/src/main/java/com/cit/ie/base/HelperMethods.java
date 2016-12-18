package com.cit.ie.base;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperMethods extends WebDriverManager {

	public static WebDriver driver;
	protected int timeOut = 500;
	public WebDriverWait wait;

	@SuppressWarnings("static-access")
	public HelperMethods(WebDriver adriver)
	{
		driver=adriver;
		wait = new WebDriverWait(this.driver,timeOut);
	}

	public void elementWait(WebElement we) throws InterruptedException{
		wait=new WebDriverWait(driver, 30, 2000);
		wait.until(ExpectedConditions.visibilityOf(we));
		Thread.sleep(5000);

	}
	
	public static boolean assertElementNotPresent (WebDriver driver, String text) throws Exception {
		List<WebElement> els = driver.findElements(By.xpath(text));
		  if (els.isEmpty() == false) {
		    throw new Exception (text + " (element is present)");
		  }
		System.out.println("Element is not present");
		return true;
		}


}

