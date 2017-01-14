package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetIOLimitsPO extends StorageGroupsPO{
	private WebDriver driver;
	public SetIOLimitsPO(WebDriver wdriver) {

		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}
	
	//LOCATORS
	public final String HOST_MB_LIMIT_CB_XPATH="//md-checkbox[@aria-label='hostMbSec']//div[1]";
	public final String HOST_IO_LIMIT_CB_XPATH="//md-checkbox[@aria-label='hostIOSec']//div[1]";
	public final String HOST_IO_LABEL_XPATH="//label[text()='Set Host IO Limit (MB/Sec)']";
	public final String HOST_MB_LABEL_XPATH="//label[text()='Set Host IO Limit (IO/Sec)']";
	
	
	@FindBy(xpath=HOST_MB_LIMIT_CB_XPATH)
	public WebElement hostMBcb;
	@FindBy(xpath=HOST_IO_LIMIT_CB_XPATH)
	public WebElement hostIOcb;
	@FindBy(xpath=HOST_IO_LABEL_XPATH)
	public WebElement hostIOlabel;
	@FindBy(xpath=HOST_MB_LABEL_XPATH)
	public WebElement hostMBlabel;
	
	
	
}
