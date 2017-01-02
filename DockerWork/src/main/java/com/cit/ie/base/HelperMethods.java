package com.cit.ie.base;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperMethods extends WebDriverManager {

	public static WebDriver driver;
	protected int timeOut = 500;
	public WebDriverWait wait;

	//@SuppressWarnings("static-access")
	public HelperMethods(WebDriver adriver)
	{
		driver=adriver;
		//wait = new WebDriverWait(this.driver,timeOut);
		new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	public void waitForElementVisiblity(String xpath) throws InterruptedException{
		wait=new WebDriverWait(driver, 60, 4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		System.out.println("Element is visible");
		Thread.sleep(8000);
	

	}
	
	public void waitForElementClickability(String xpath) throws InterruptedException{
		wait=new WebDriverWait(driver, 60, 4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		System.out.println("Element is clickable");
		Thread.sleep(8000);

	}
	
	
	public void waitForLoad() {
	    new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
	
	public void waitForElementToDisappear(String text) throws InterruptedException{
		wait=new WebDriverWait(driver, 180, 4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(text)));
		Thread.sleep(3500);

	}
	
	public void haltTest() throws InterruptedException{
		driver.close();
		Thread.sleep(1000);
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

