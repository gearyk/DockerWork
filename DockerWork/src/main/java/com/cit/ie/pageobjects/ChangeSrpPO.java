package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeSrpPO extends StorageGroupsPO {
	private WebDriver driver;
	public ChangeSrpPO(WebDriver wdriver) {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}
	
	//LOCATORS
	public final String SRP_LISTBOX_XPATH="//md-select[@name='srp']";
	public final String SRP_2_XPATH="//md-option[@ng-value='selectedSrpInfoObject']/div[text()='SRP_2']";
	public final String DEFAULT_SRP_XPATH="//md-option[@ng-value='selectedSrpInfoObject']/div[text()='DEFAULT_SRP']";
	public final String NONE_SRP_XPATH="//md-option[@ng-value='selectedSrpInfoObject']/div[text()='None']";
	public final String RUN_NOW_BUTTON_XPATH="//md-menu-bar[@ok-click='okClick()']";
	public final String SUCCESS_MSG_XPATH=".//p[text()='Successfully Changed SRP']";
	public final String CSG_FIRST_ROW_LISTBOX_SRP_XPATH=".//u4v-sg-step[1]/div/div/div[2]/md-input-container/md-select";
	public final String CSG_SRP_2_XPATH="//div[@aria-hidden='false']//div[text()='SRP_2']";
	public final String CSG_DEFAULT_SRP_XPATH="//div[@aria-hidden='false']//div[text()='DEFAULT_SRP']";
	public final String CSG_NONE_SRP_XPATH="//div[@aria-hidden='false']//div[text()='None']";
	
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
	@FindBy(xpath=CSG_FIRST_ROW_LISTBOX_SRP_XPATH)
	public WebElement csgSrpListBox;
	@FindBy(xpath=CSG_SRP_2_XPATH)
	public WebElement csgSrp2Button;
	@FindBy(xpath=CSG_DEFAULT_SRP_XPATH)
	public WebElement csgDeafultSRPButton;
	@FindBy(xpath=CSG_NONE_SRP_XPATH)
	public WebElement csgNoneSRPButton;
}
