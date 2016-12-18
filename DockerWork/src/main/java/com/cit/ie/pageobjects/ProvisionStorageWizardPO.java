package com.cit.ie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProvisionStorageWizardPO extends StorageGroupsPO{

	public ProvisionStorageWizardPO(WebDriver wdriver) {

		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	private static String childSGRow;
	//LOCATORS
	public static final String PROVISION_STORAGE_TITLE_XPATH = "//h2[text()='Provision Storage']";	
	public static final String STORAGE_GROUP_NAME_LABEL_XPATH = ".//label[text()='Storage Group Name']";
	public static final String STORAGE_GROUP_NAME_TF = ".//label[text()='Storage Group Name']/following-sibling::input";
	//STORAGE RESOURCE POOL
	public static final String SRP_LABEL_XPATH = ".//label[text()='Storage Resource Pool']";
	public static final String SRP_LIST_BOX = ".//md-select[contains(@aria-label,'Storage Resource Pool')]";
	public static final String DEFAULT_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='DEFAULT_SRP']";
	public static final String NONE_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='None']";
	public static final String SRP2_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='SRP_2']";
	
	//STANDALONE STORAGE GROUPS
	//SERVICE LEVEL
	public static final String SERVICELEVEL_LIST_BOX_XPATH="//div[@ng-if='showColumnPolicy.showServiceLevel']/md-input-container";
	public static final String SL_OPTIMIZED=".//md-option/div[text()='Optimized']";
	public static final String SL_DIAMOND=".//md-option/div[text()='Diamond']";
	public static final String SL_PLATINUM=".//md-option/div[text()='Platinum']";
	public static final String SL_GOLD=".//md-option/div[text()='Gold']";
	public static final String SL_SILVER=".//md-option/div[text()='Silver']";
	public static final String SL_BRONZE=".//md-option/div[text()='Bronze']";
	public static final String SL_NONE=".//*[@ng-value='selectedSloInfoObject']/div[text()='None']";
	//WORKLOAD TYPE
	public static final String WORKLOAD_LIST_BOX_XPATH="//div[@ng-if='showColumnPolicy.showWorkload']/md-input-container";
	public static final String WL_OLTP=".//div[text()='OLTP']";
	public static final String WL_OLTP_REP=".//div[text()='OLTP + Rep']";
	public static final String WL_DSS=".//div[text()='DSS']";
	public static final String WL_DSS_REP=".//div[text()='DSS + Rep']";
	public static final String WL_NOT_SPECIFIED=".//md-option/div[text()='Not Specified']";
	
	//NUMBER OF VOLUMES
	public static final String VOLUMES_NUM_XPATH="//input[@aria-label='childSgVols']";
	//VOLUME CAPACITY
	public static final String VOLUMES_SIZE__XPATH="//input[@aria-label='sgCap']";
	public static final String VOLUMES_CAPACITY_UNITS_XPATH="//div[@ng-if='showColumnPolicy.showVolsCap']/md-input-container";
	public static final String UNIT_MB="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='MB']";
	public static final String UNIT_GB="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='GB']";
	public static final String UNIT_TB="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='TB']";
	public static final String UNIT_CYL="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='Cyl']";
	//EDIT OPTIONS
	public static final String EDIT_ICON_XPATH="//button[@aria-label='editSgByName']";
	public static final String ALLOC_CAPACITY="//md-checkbox[@aria-label='allocCapForVol']";
		//	+ "/div[1]/div[@class='md-icon']";
	public static final String PERSIST_CAPACITY="//md-checkbox[@aria-label='persist']";
	//BUTTONS
	public static final String CREATE_STORAGE_GROUP_RUN_NOW_BUTTON_XPATH="//section[@wz-title='Create Storage Group(s)']//button[text()='Run Now']";
	public static final String CANCEL_STORAGE_GROUP_DIALOG_XPATH="//section[@wz-title='Create Storage Group(s)']//span[text()='CANCEL']";
	public static final String NEXT_PAGE_BUTTON_XPATH="//section[@wz-title='Create Storage Group(s)']//button[@aria-label='NEXT']";
	public static final String ADD_STORAGE_GROUP_BUTTON_XPATH="//button[@aria-label='Add Storage Group']";
	public static final String SET_IO_HOST_LIMITS_BUTTON_XPATH=".//button[@aria-label='Set Host I/O Limits']/span[@class='ng-scope']";
	
	//ERRORS
	public static final String SG_ALREADY_EXISTS="//p[text()=\"Error in Creating Storage Group '000DOCK23_1': Cannot use the specified name because it's already in use\"]";
	public static final String RETRIEVING ="//div[@aria-hidden='false']//div[text()='Retrieving data']";
	
	//WEB ELEMENTS
	//BUTTONS/ICONS
	@FindBy(xpath=PROVISION_STORAGE_TITLE_XPATH)
	public WebElement provisionStorageTitle;
	@FindBy(xpath=STORAGE_GROUP_NAME_LABEL_XPATH)
	public WebElement storageGroupNameLabel;
	@FindBy(xpath=STORAGE_GROUP_NAME_TF)
	public WebElement storageGroupNameTextField;
	@FindBy(xpath=CREATE_STORAGE_GROUP_RUN_NOW_BUTTON_XPATH)
	public WebElement createSgRunNow;
	@FindBy(xpath=CANCEL_STORAGE_GROUP_DIALOG_XPATH)
	public WebElement cancelSgDialog;
	@FindBy(xpath=ADD_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement addStorageGroupButton;
	@FindBy(xpath=SET_IO_HOST_LIMITS_BUTTON_XPATH)
	public WebElement setIOHostLimitsButton;
	@FindBy(xpath=EDIT_ICON_XPATH)
	public WebElement editStorageGroupIcon;
	@FindBy(xpath=NEXT_PAGE_BUTTON_XPATH)
	public WebElement nextPageButton;
	@FindBy(xpath=RETRIEVING)
	public WebElement retrievingData;
	
	//SRP
	@FindBy(xpath=SRP_LIST_BOX)
	public WebElement srpListBox;
	
	@FindBy(xpath=DEFAULT_SRP_MENUITEM_XPATH)
	public WebElement defaultSRP;
	@FindBy(xpath=NONE_SRP_MENUITEM_XPATH)
	public WebElement noneSRP;
	@FindBy(xpath=SRP2_SRP_MENUITEM_XPATH)
	public WebElement srp2SRP;
	//SLO
	@FindBy(xpath=SERVICELEVEL_LIST_BOX_XPATH)
	public WebElement sloListBox;
	@FindBy(xpath=SL_OPTIMIZED)
	public WebElement optimized;
	@FindBy(xpath=SL_DIAMOND)
	public WebElement diamond;
	@FindBy(xpath=SL_PLATINUM)
	public WebElement platinum;
	@FindBy(xpath=SL_GOLD)
	public WebElement gold;
	@FindBy(xpath=SL_SILVER)
	public WebElement silver;
	@FindBy(xpath=SL_BRONZE)
	public WebElement bronze;
	@FindBy(xpath=SL_NONE)
	public WebElement none;
	//WORKLOAD
	@FindBy(xpath=WORKLOAD_LIST_BOX_XPATH)
	public WebElement workloadListBox;
	@FindBy(xpath=WL_OLTP)
	public WebElement oltp;
	@FindBy(xpath=WL_OLTP_REP)
	public WebElement oltp_rep;
	@FindBy(xpath=WL_DSS)
	public WebElement dss;
	@FindBy(xpath=WL_DSS_REP)
	public WebElement dss_rep;
	@FindBy(xpath=WL_NOT_SPECIFIED)
	public WebElement no_workload;
	
	//VOLUMES
	@FindBy(xpath=VOLUMES_NUM_XPATH)
	public WebElement numberOfVolumes;
	@FindBy(xpath=VOLUMES_SIZE__XPATH)
	public WebElement volumeSize;
	@FindBy(xpath=VOLUMES_CAPACITY_UNITS_XPATH)
	public WebElement volumeUnit;
	@FindBy(xpath=UNIT_MB)
	public WebElement MB;
	@FindBy(xpath=UNIT_GB)
	public WebElement GB;
	@FindBy(xpath=UNIT_TB)
	public WebElement TB;
	@FindBy(xpath=UNIT_CYL)
	public WebElement CYL;
	//ADVANCED
	@FindBy(xpath=ALLOC_CAPACITY)
	public WebElement allocateCapacityCB;
	@FindBy(xpath=PERSIST_CAPACITY)
	public WebElement persistCapacityCB;
	
	//CASCADED SGs
	//CASCADED STORAGE GROUPS
	//SERVICE LEVEL
	//u4v-sg-step[1]//
	public void setRowForChildSG(int i){
		childSGRow=Integer.toString(i);
	}
	public static String CSG_SERVICELEVEL_LIST_BOX_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showServiceLevel']/md-input-container";
	public static String CSG_SL_OPTIMIZED=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Optimized']";
	public static String CSG_SL_DIAMOND=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Diamond']";
	public static String CSG_SL_PLATINUM=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Platinum']";
	public static String CSG_SL_GOLD=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Gold']";
	public static String CSG_SL_SILVER=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Silver']";
	public static String CSG_SL_BRONZE=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Bronze']";
	public static String CSG_SL_NONE=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='None']";
	//WORKLOAD TYPE
	public static String CSG_WORKLOAD_LIST_BOX_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showWorkload']/md-input-container";
	public static String CSG_WL_OLTP=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='OLTP']";
	public static String CSG_WL_OLTP_REP=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='OLTP + Rep']";
	public static String CSG_WL_DSS=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='DSS']";
	public static String CSG_WL_DSS_REP=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='DSS + Rep']";
	public static String CSG_WL_NONE=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Not Specified']";
	//NUMBER OF VOLUMES
	public static String CSG_VOLUMES_NUM_XPATH="//u4v-sg-step[]//input[@aria-label='childSgVols']";
	//VOLUME CAPACITY
	public static String CSG_VOLUMES_CAPACITY_XPATH="//u4v-sg-step[]//input[@aria-label='sgCap']";
	
	public static String CSG_VOLUMES_CAPACITY_UNITS_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showVolsCap']/md-input-container";
	public static String CSG_UNIT_MB="//div[@class='md-select-menu-container md-active md-clickable']//div[text()='MB']";
	public static String CSG_UNIT_GB=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='GB']";
	public static String CSG_UNIT_TB=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='TB']";
	public static String CSG_UNIT_CYL=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Cyl']";
	
	public static String CSG_MB="test";
	
	//WEB ELEMENTS
	//BUTTONS/ICONS
	//SLO
	//FINDBY annotation have to be constants so they are unusable for dynamic content as found in locating CHILD SGs with variable rows
	public WebElement sloListBoxCSG(){
		return findByXPath(CSG_SERVICELEVEL_LIST_BOX_XPATH);	
	}
	public WebElement optimizedCSG(){
		return findByXPath(CSG_SL_OPTIMIZED);	
	}
	public WebElement diamondCSG(){
		return findByXPath(CSG_SL_DIAMOND);
	}
	public WebElement platinumCSG(){
		return findByXPath(CSG_SL_PLATINUM);	
	}
	public WebElement goldCSG(){
		return findByXPath(CSG_SL_GOLD);
	}
	public WebElement silverCSG(){
		return findByXPath(CSG_SL_SILVER);	
	}
	public WebElement bronzeCSG(){
		return findByXPath(CSG_SL_BRONZE);
	}
	public WebElement noSloCSG(){
		return findByXPath(CSG_SL_NONE);	
	}
	

	//WORKLOAD
	public WebElement workloadListBoxCSG(){
		return findByXPath(CSG_WORKLOAD_LIST_BOX_XPATH);	
	}
	public WebElement oltpCSG(){
		return findByXPath(CSG_WL_OLTP);	
	}
	public WebElement oltpRepCSG(){
		return findByXPath(CSG_WL_OLTP_REP);
	}
	public WebElement dssCSG(){
		return findByXPath(CSG_WL_DSS);	
	}
	public WebElement dssRepCSG(){
		return findByXPath(CSG_WL_DSS_REP);
	}
	public WebElement notSpecifiedWLCSG(){
		return findByXPath(CSG_WL_NONE);	
	}
	
	//VOLUMES
	public WebElement numberOfVoumesCSG(){
		return findByXPath(CSG_VOLUMES_NUM_XPATH);	
	}
	public WebElement volumeCapacityCSG(){
		return findByXPath(CSG_VOLUMES_CAPACITY_XPATH);	
	}
	public WebElement volumeUnitDropdownCSG(){
		return findByXPath(CSG_VOLUMES_CAPACITY_UNITS_XPATH);	
	}
	
	@FindBy(xpath="//div[@class='md-select-menu-container md-active md-clickable']//div[text()='MB']")
	public WebElement csgMB;
	@FindBy(xpath=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='GB']")
	public WebElement csgGB;
	@FindBy(xpath=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='TB']")
	public WebElement csgTB;
	@FindBy(xpath=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Cyl']")
	public WebElement csgCYL;

	
	//ERRORS
	@FindBy(xpath=SG_ALREADY_EXISTS)
	public WebElement errorSGAlreadyExists;
	
	/**
	 * @author gearyk2
	 * @param xpath
	 * @return web element
	 * @description specialized findByXpath for Child Storage Group rows
	 */
	public static WebElement findByXPath(String xpath){
		WebElement element = driver.findElement(By.xpath(xpath.replace("//u4v-sg-step[","//u4v-sg-step["+childSGRow)));
		return element;
	}

}
