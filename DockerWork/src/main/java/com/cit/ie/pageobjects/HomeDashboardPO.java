package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.base.HelperMethods;



/**
 * @name HomeDashboard PageObject
 * @description Page Objects for HomeDashboard
 * @author gearyk2
 */
public class HomeDashboardPO extends HelperMethods
{
	public HomeDashboardPO(WebDriver wdriver) {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	//Object Locators
	public static final String DELL_EMC_LOGO_XPATH = ".//*[@id='dell-emc-logo']";
	public static final String U4V_LOGO_XPATH = "//span[text()[contains(.,'Unisphere for VMAX')]]";

	//SIDEMENUs
	public static final String STORAGE_SIDEMENU_XPATH=".//a[@ng-if='item.items']/div[text()='STORAGE']";
	public static final String STORAGE_GROUPS_SIDEMENU_XPATH="//nav[@class='ng-scope emc-framework-menu-secondary-sub sub-menu-display']/menu/ul/li[contains(@class,'emc-menu-subitem-storage_groups')]/a/div";
															   
	@FindBy(xpath=DELL_EMC_LOGO_XPATH)
	public WebElement dellEmcLogo;

	@FindBy(xpath=U4V_LOGO_XPATH)
	public WebElement u4vLogo;

	@FindBy(xpath=STORAGE_SIDEMENU_XPATH)
	public WebElement storageSideMenuItem;

	@FindBy(xpath=STORAGE_GROUPS_SIDEMENU_XPATH)
	public WebElement storageGroupsSideMenuItem;


	public void waitForHomeDashboardPageObjects() throws InterruptedException{
		elementWait(u4vLogo);
	}

	//Navigate to this view
	public void navigateToSideMenu(){
		storageSideMenuItem.click();
	}

	//Navigate to this view
	public void navigateToStorageGroups(){
		navigateToSideMenu();
		storageGroupsSideMenuItem.click();	
	}
	
	
}

