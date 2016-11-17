package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProvisionStorageWizardPO extends StorageGroupsPO{

	public ProvisionStorageWizardPO(WebDriver wdriver) {

		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	//LOCATORS
	public static final String PROVISION_STORAGE_TITLE_XPATH = "//h2[text()='Provision Storage']";	
	public static final String STORAGE_GROUP_NAME_LABEL_XPATH = ".//label[text()='Storage Group Name']";
	public static final String STORAGE_GROUP_NAME_TF = ".//label[text()='Storage Group Name']/following-sibling::input";
	public static final String SRP_LABEL_XPATH = ".//label[text()='Storage Resource Pool']";
	public static final String SRP_LIST_BOX = ".//md-select[contains(@aria-label,'Storage Resource Pool')]";
	public static final String CREATE_STORAGE_GROUP_RUN_NOW_BUTTON_XPATH="//section[@wz-title='Create Storage Group(s)']//button[text()='Run Now']";
	public static final String DEFAULT_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='DEFAULT_SRP']";
	public static final String NONE_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='None']";
	public static final String SRP2_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='SRP_2']";
	//WEB ELEMENTS
	//BUTTONS/ICONS
	@FindBy(xpath=PROVISION_STORAGE_TITLE_XPATH)
	public WebElement provisionStorageTitle;
	@FindBy(xpath=STORAGE_GROUP_NAME_LABEL_XPATH)
	public WebElement storageGroupNameLabel;
	@FindBy(xpath=STORAGE_GROUP_NAME_TF)
	public WebElement storageGroupNameTextField;
	@FindBy(xpath=SRP_LIST_BOX)
	public WebElement srpListBox;
	@FindBy(xpath=CREATE_STORAGE_GROUP_RUN_NOW_BUTTON_XPATH)
	public WebElement createSgRunNow;
	@FindBy(xpath=DEFAULT_SRP_MENUITEM_XPATH)
	public WebElement defaultSRP;
	@FindBy(xpath=NONE_SRP_MENUITEM_XPATH)
	public WebElement noneSRP;
	@FindBy(xpath=SRP2_SRP_MENUITEM_XPATH)
	public WebElement srp2SRP;
	

}
