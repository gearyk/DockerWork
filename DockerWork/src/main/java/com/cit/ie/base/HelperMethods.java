package com.cit.ie.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cit.ie.rest.RESTClient;

public class HelperMethods extends WebDriverManager {

	public WebDriver driver;
	protected int timeOut = 500;
	public WebDriverWait wait;
	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";

	//@SuppressWarnings("static-access")
	public HelperMethods(WebDriver adriver){
		driver=adriver;
		//wait = new WebDriverWait(this.driver,timeOut);
		new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	public void waitForElementVisiblity(String xpath) throws InterruptedException{
		wait=new WebDriverWait(driver, 120, 4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		//System.out.println("Element is visible");
		Thread.sleep(8000);
	}

	public void waitForElementClickability(String xpath) throws InterruptedException{
		wait=new WebDriverWait(driver, 120, 4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		//System.out.println("Element is clickable");
		Thread.sleep(8000);
	}


	public void waitForLoad() {
		new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	public void waitForElementToDisappear(String text) {
		try {
			wait=new WebDriverWait(driver, 300, 4000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(text)));
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			try {
				FileUtils.copyFile(scrFile, new File("/fail/testScreenShot.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
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
		//System.out.println("Element is not present");
		return true;
	}
	
	public static void printTimeStart(String test){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("*************************************************");
		System.out.println(test +" starting at : " +timeStamp);
		System.out.println("*************************************************");
	}
	
	public static void printTimeFinish(String test){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("*************************************************");
		System.out.println(test +" has finished at : " +timeStamp);
		System.out.println("*************************************************");
	}
	
	/**
	 * @author gearyk2
	 * @param sgName
	 * @throws InterruptedException
	 * @description verify the response code of the RESTGET for this storage group
	 * and then call a REST DELETE for the storage group
	 */
	public void verifyAndCleanup(String sgName) throws InterruptedException {
		//VERIFY THAT GROUP HAS BEEN CREATED
		RESTClient.refreshRestDB(baseURL);
		RESTClient.GET(baseURL+sgName);
		//RESTClient.printResponses();
		Assert.assertEquals(RESTClient.responseStatus,200);
		//CLEANUP
		RESTClient.DELETE(baseURL+sgName);
		//RESTClient.printResponses();
		Assert.assertEquals(RESTClient.responseStatus,204);
		Thread.sleep(2000);
	}
	
	public void getScreenshot(){
		
	}
	


}

