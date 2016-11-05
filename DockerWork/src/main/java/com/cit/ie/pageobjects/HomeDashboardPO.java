package com.cit.ie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.base.HelperMethods;



/**
 * @name LoginPage PageObject
 * @description Page Objects for login Page
 * @author gearyk2
 */
public class HomeDashboardPO extends HelperMethods
{
	public HomeDashboardPO(WebDriver wdriver) {
		 super(wdriver);
	     driver = wdriver;
	     wait = new WebDriverWait(driver, timeOut);
	     driver.manage().window().maximize();
	     driver.get("https://10.73.28.71:8443/univmax/jsclient/#/home");
	    }
	
	//Object Locators
	public static final String DELL_EMC_LOGO_XPATH = ".//*[@id='dell-emc-logo']";
	public static final String U4V_LOGO_XPATH = ".//span[text()[contains(.,'Unisphere')] AND text()[contains(.,'VMAX')]]";

	//SIDEMENU
	public static final String STORAGE_SIDEMENU_XPATH=".//a[@ng-if='item.items']/div[text()='STORAGE']";
	public static final String STORAGE_GROUPS_SIDEMENU_XPATH=".//nav[@ng-if='item.items']/div[text()='STORAGE']/parent::nav/menu/ul/li[@class='emc-menu-subitem-storage_groups']/a/div";

	
	@FindBy(xpath=DELL_EMC_LOGO_XPATH)
	public WebElement dellEmcLogo;
	
	@FindBy(xpath=U4V_LOGO_XPATH)
	public WebElement usernameField;
	
	@FindBy(xpath=STORAGE_SIDEMENU_XPATH)
	public WebElement storageSideMenuItem;
	
	@FindBy(xpath=STORAGE_GROUPS_SIDEMENU_XPATH)
	public WebElement storageGroupsSideMenuItem;
	
	
	
	
}
