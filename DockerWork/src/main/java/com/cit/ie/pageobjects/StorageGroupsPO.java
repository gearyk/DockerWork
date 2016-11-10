package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StorageGroupsPO extends HomeDashboardPO{

	public StorageGroupsPO(WebDriver wdriver) {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	//Object Locators
	public static final String STORAGE_GROUPS_PAGE_TITLE_XPATH = ".//div[@data-view-id='StorageGroupListView']/div/div[text()='Storage Groups']";
	public static final String CREATE_STORAGE_GROUP_BUTTON_XPATH = ".//button[@aria-label='create']";
	public static final String EDIT_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='edit']";
	public static final String DELETE_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='delete']";

	//Object WebElements
	@FindBy(xpath=STORAGE_GROUPS_PAGE_TITLE_XPATH)
	public WebElement storageGroupPageTitle;

	@FindBy(xpath=CREATE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement createStorageGroupButton;

	@FindBy(xpath=EDIT_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement editStorageGroupButton;

	@FindBy(xpath=DELETE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement deleteStorageGroupButton;




	//Wait for this page to load
	public void waitForStorageGroupsPageObjects() throws InterruptedException{
		try {
			elementWait(createStorageGroupButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
	}


}
