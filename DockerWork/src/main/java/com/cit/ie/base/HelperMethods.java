package com.cit.ie.base;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.selenium.WebDriverManager;

public class HelperMethods extends WebDriverManager {

		public static WebDriver driver;
	    protected int timeOut = 300;
	    public WebDriverWait wait;
	    private WebElement proxy;

	    @SuppressWarnings("static-access")
		public HelperMethods(WebDriver adriver)
	    {
	        driver=adriver;
	        wait = new WebDriverWait(this.driver,timeOut);
	    }
	    
	   public void elWait(WebElement we){
		   
		WebDriverWait wait=new WebDriverWait(driver, 60, 1000);
		wait.until(ExpectedConditions.visibilityOf(we));
		   
	   }
	    
	    
	}

