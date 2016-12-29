package com.cit.ie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.base.Constants;

public class StorageGroupsPO extends HomeDashboardPO{

	public StorageGroupsPO(WebDriver wdriver) {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	//LOCATORS
	//BUTTONS
	public static final String STORAGE_GROUPS_PAGE_TITLE_XPATH = ".//div[@data-view-id='StorageGroupListView']/div/div[text()='Storage Groups']";
	public static final String CREATE_STORAGE_GROUP_BUTTON_XPATH = ".//button[@aria-label='create']";
	public static final String EDIT_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='edit']";
	public static final String DELETE_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='delete']";
	public static final String MOREACTIONS_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='more_vert']";
	public static final String SEARCH_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='search']";
	public static final String FILTER_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='filter']";
	public static final String VIEWDETAILS_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='view details']";
	//COLUMN HEADERS
	public static final String STORAGEGROUP_HEADER_XPATH=".//span[text()='Storage Group']";
	public static final String COMPLIANCE_HEADER_XPATH=".//span[text()='Compliance']";
	public static final String SRP_HEADER_XPATH=".//span[text()='SRP']";
	public static final String SLO_HEADER_XPATH=".//span[text()='SLO']";
	public static final String CAPACITY_HEADER_XPATH=".//span[text()='Capacity (GB)']";
	public static final String EMULATION_HEADER_XPATH=".//span[text()='Emulation']";
	public static final String COLUMN_FILTER_HEADER_XPATH=".//i[@aria-label='Grid Menu']";
	//SELECT ROW
	public static final String ROW_WITG_SG_NAME="//div[text()='']/child::div";
	public static final String ROW_WITG_SG_NAME_COMPLIANCE="//div[text()='']/child::div";
	//MOREACTIONS
	public static final String CHANGE_SRP_XPATH=".//button[@aria-label='Change SRP']";
	public static final String SET_IO_HOST_LIMITS_XPATH=".//button[@aria-label='Set Host I/O Limits']";
	
	//POPUPS
	public static final String DELETE_STORAGE_GROUP_POPUP_OK_BUTTON_XPATH="//button[@aria-label='OK Button']";
	public static final String SUCCESS_STORAGE_GROUP_DELETED_XPATH="//p[text()='Storage Group(s) Successfully deleted']";
	public static final String ACKNOWLEDGE_SUCCESS_STORAGE_GROUP_DELETED_XPATH="//button[@aria-label='OK']";
	//WEB ELEMENTS
	
	//BUTTONS/ICONS
	@FindBy(xpath=STORAGE_GROUPS_PAGE_TITLE_XPATH)
	public WebElement storageGroupPageTitle;
	@FindBy(xpath=CREATE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement createStorageGroupButton;
	@FindBy(xpath=EDIT_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement editStorageGroupButton;
	@FindBy(xpath=DELETE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement deleteStorageGroupButton;
	@FindBy(xpath=MOREACTIONS_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement moreActionsStorageGroupButton;
	@FindBy(xpath=SEARCH_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement searchStorageGroupButton;
	@FindBy(xpath=FILTER_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement filterStorageGroupButton;
	@FindBy(xpath=VIEWDETAILS_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement viewDetailsStorageGroupButton;
	@FindBy(xpath=CHANGE_SRP_XPATH)
	public WebElement changeSRPButton;
	@FindBy(xpath=SET_IO_HOST_LIMITS_XPATH)
	public WebElement setIOLimitsButton;
	//HEADERS
	@FindBy(xpath=STORAGEGROUP_HEADER_XPATH)
	public WebElement storageGroupHeader;
	@FindBy(xpath=COMPLIANCE_HEADER_XPATH)
	public WebElement complianceHeader;
	@FindBy(xpath=SRP_HEADER_XPATH)
	public WebElement srpHeader;
	@FindBy(xpath=SLO_HEADER_XPATH)
	public WebElement sloHeader;
	@FindBy(xpath=CAPACITY_HEADER_XPATH)
	public WebElement capacityHeader;
	@FindBy(xpath=EMULATION_HEADER_XPATH)
	public WebElement emulationHeader;
	@FindBy(xpath=COLUMN_FILTER_HEADER_XPATH)
	public WebElement columnFilterButton;
	@FindBy(xpath=DELETE_STORAGE_GROUP_POPUP_OK_BUTTON_XPATH)
	public WebElement deleteSGPopupOKButton;
	@FindBy(xpath=SUCCESS_STORAGE_GROUP_DELETED_XPATH)
	public WebElement successStorageGroupDeleted;
	@FindBy(xpath=ACKNOWLEDGE_SUCCESS_STORAGE_GROUP_DELETED_XPATH)
	public WebElement acknowledgeSGDeletedButton;
	
	public WebElement sgRow(String sgname){
		return findByXPath(ROW_WITG_SG_NAME,sgname);	
	}
	
	public WebElement sgRowCompliance(String sgname){
		return findByXPath(ROW_WITG_SG_NAME_COMPLIANCE,sgname);	
	}
	
	/**
	 * @author gearyk2
	 * @param xpath
	 * @return web element
	 * @description specialized findByXpath for clicking on Rows
	 */
	public static WebElement findByXPath(String xpath,String sgname){
		WebElement element = driver.findElement(By.xpath(xpath.replace("//div[text()='","//div[text()='"+sgname)));
		return element;
	}
	
	

	//Wait for this page to load
	public void waitForStorageGroupsPageObjects() throws InterruptedException{
		try {
			waitForElementToDisappear(Constants.RETRIEVING);
			waitForElementClickability(CREATE_STORAGE_GROUP_BUTTON_XPATH);
			waitForLoad();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}


}
