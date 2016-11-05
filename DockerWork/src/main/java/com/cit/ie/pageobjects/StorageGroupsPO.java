package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StorageGroupsPO extends HomeDashboardPO{

	public StorageGroupsPO(WebDriver wdriver) {
		super(wdriver);
		// TODO Auto-generated constructor stub
	}

	//Object Locators
	public static final String STORAGE_GROUPS_PAGE_TITLE_XPATH = ".//div[@data-view-id='StorageGroupListView']/div/div[text()='Storage Groups']";
	public static final String CREATE_STORAGE_GROUP_BUTTON_XPATH = ".//button[@aria-label='create']";
	public static final String EDIT_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='edit']";
	public static final String DELETE_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='delete']";

	@FindBy(xpath=STORAGE_GROUPS_PAGE_TITLE_XPATH)
	public WebElement storageGroupPageTitle;

	@FindBy(xpath=CREATE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement createStorageGroupButton;

	@FindBy(xpath=EDIT_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement editStorageGroupButton;

	@FindBy(xpath=DELETE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement deleteStorageGroupButton;


}
