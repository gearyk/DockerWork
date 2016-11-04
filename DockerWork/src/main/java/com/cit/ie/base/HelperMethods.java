package com.cit.ie.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.selenium.WebDriverManager;

public class HelperMethods extends WebDriverManager {

		public static WebDriver driver;
	    protected int timeOut = 30;
	    public WebDriverWait wait;
	    

	    @SuppressWarnings("static-access")
		public HelperMethods(WebDriver adriver)
	    {
	        driver=adriver;
	        wait = new WebDriverWait(this.driver,timeOut);
	    }
	    
	    
	}

