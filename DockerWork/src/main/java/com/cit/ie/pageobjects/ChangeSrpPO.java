package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeSrpPO extends StorageGroupsPO {
	
	public ChangeSrpPO(WebDriver wdriver) {

		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}
	
	//LOCATORS
	public static final String SRP_LISTBOX_XPATH="//md-select[@name='srp']";
	public static final String SRP_2_XPATH="//md-option[@ng-value='selectedSrpInfoObject']/div[text()='SRP_2']";
	public static final String DEFAULT_SRP_XPATH="//md-option[@ng-value='selectedSrpInfoObject']/div[text()='DEFAULT_SRP']";
	public static final String NONE_SRP_XPATH="//md-option[@ng-value='selectedSrpInfoObject']/div[text()='None']";
	public static final String RUN_NOW_BUTTON_XPATH="//md-menu-bar[@ok-click='okClick()']";
	public static final String SUCCESS_MSG_XPATH=".//p[text()='Successfully Changed SRP']";
	
	//WEBELEMENTS
	@FindBy(xpath=SRP_LISTBOX_XPATH)
	public WebElement srpListBox;
	@FindBy(xpath=SRP_2_XPATH)
	public WebElement srp2Button;
	@FindBy(xpath=DEFAULT_SRP_XPATH)
	public WebElement defaultSrpButton;
	@FindBy(xpath=NONE_SRP_XPATH)
	public WebElement noneSrpButton;
	@FindBy(xpath=RUN_NOW_BUTTON_XPATH)
	public WebElement runNowButton;
	@FindBy(xpath=SUCCESS_MSG_XPATH)
	public WebElement changeSRPSuccessMessage;
}
