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
	//STROAGE RESOURCE POOL
	public static final String SRP_LABEL_XPATH = ".//label[text()='Storage Resource Pool']";
	public static final String SRP_LIST_BOX = ".//md-select[contains(@aria-label,'Storage Resource Pool')]";
	public static final String DEFAULT_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='DEFAULT_SRP']";
	public static final String NONE_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='None']";
	public static final String SRP2_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='SRP_2']";
	//STANDALONE STORAGE GROUPS
	//SERVICE LEVEL
	public static final String SERVICELEVEL_LIST_BOX_XPATH="//div[@ng-if='showColumnPolicy.showServiceLevel']/md-input-container";
	public static final String SL_OPTIMIZED=".//div[text()='Optimized']";
	public static final String SL_DIAMOND=".//div[text()='Diamond']";
	public static final String SL_PLATINUM=".//div[text()='Plantinum']";
	public static final String SL_GOLD=".//div[text()='Gold']";
	public static final String SL_SILVER=".//div[text()='Silver']";
	public static final String SL_BRONZE=".//div[text()='Bronze']";
	public static final String SL_NONE=".//div[text()='None']";
	//WORKLOAD TYPE
	public static final String WORKLOAD_LIST_BOX_XPATH="//div[@ng-if='showColumnPolicy.showWorkload']/md-input-container";
	public static final String WL_OLTP=".//div[text()='OLTP']";
	public static final String WL_OLTP_REP=".//div[text()='OLTP + Rep']";
	public static final String WL_DSS=".//div[text()='DSS']";
	public static final String WL_DSS_REP=".//div[text()='DSS + Rep']";
	//NUMBER OF VOLUMES
	public static final String VOLUMES_NUM_XPATH="//input[@aria-label='childSgVols']";
	//VOLUME CAPACITY
	public static final String VOLUMES_SIZE__XPATH="//input[@aria-label='sgCap']";
	public static final String VOLUMES_CAPACITY_UNITS_XPATH="//div[@ng-if='showColumnPolicy.showVolsCap']/md-input-container";
	public static final String UNIT_MB="//div[text()='MB']";
	public static final String UNIT_GB="//div[text()='GB']";
	public static final String UNIT_TB="//div[text()='TB']";
	public static final String UNIT_CYL="//div[text()='Cyl']";
	
	//BUTTONS
	public static final String CREATE_STORAGE_GROUP_RUN_NOW_BUTTON_XPATH="//section[@wz-title='Create Storage Group(s)']//button[text()='Run Now']";
	public static final String CANCEL_STORAGE_GROUP_DIALOG_XPATH="//section[@wz-title='Create Storage Group(s)']//span[text()='CANCEL']";
	public static final String ADD_STORAGE_GROUP_BUTTON_XPATH="//button[@aria-label='Add Storage Group']";
	public static final String SET_IO_HOST_LIMITS_BUTTON_XPATH=".//button[@aria-label='Set Host I/O Limits']/span[@class='ng-scope']";
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
	public WebElement worloadListBox;
	@FindBy(xpath=WL_OLTP)
	public WebElement oltp;
	@FindBy(xpath=WL_OLTP_REP)
	public WebElement oltp_rep;
	@FindBy(xpath=WL_DSS)
	public WebElement dss;
	@FindBy(xpath=WL_DSS_REP)
	public WebElement dss_rep;
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
		
	
	//CASCADED SGs
	//CASCADED STORAGE GROUPS
	//SERVICE LEVEL
	//u4v-sg-step[1]//
	public void setRowForChildSG(int i){
		childSGRow=Integer.toString(i);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(childSGRow);
	}
	public static String CSG_SERVICELEVEL_LIST_BOX_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showServiceLevel']/md-input-container";
	public static String CSG_SL_OPTIMIZED="//u4v-sg-step["+childSGRow+"]//div[text()='Optimized']";
	public static String CSG_SL_DIAMOND="//u4v-sg-step["+childSGRow+"]//div[text()='Diamond']";
	public static String CSG_SL_PLATINUM="//u4v-sg-step["+childSGRow+"]//div[text()='Plantinum']";
	public static String CSG_SL_GOLD="//u4v-sg-step["+childSGRow+"]//div[text()='Gold']";
	public static String CSG_SL_SILVER="//u4v-sg-step["+childSGRow+"]//div[text()='Silver']";
	public static String CSG_SL_BRONZE="//u4v-sg-step["+childSGRow+"]//div[text()='Bronze']";
	public static String CSG_SL_NONE="//u4v-sg-step["+childSGRow+"]//div[text()='None']";
	//WORKLOAD TYPE
	public static String CSG_WORKLOAD_LIST_BOX_XPATH="//u4v-sg-step["+childSGRow+"]//div[@ng-if='showColumnPolicy.showWorkload']/md-input-container";
	public static String CSG_WL_OLTP="//u4v-sg-step["+childSGRow+"]//div[text()='OLTP']";
	public static String CSG_WL_OLTP_REP="//u4v-sg-step["+childSGRow+"]//div[text()='OLTP + Rep']";
	public static String CSG_WL_DSS="//u4v-sg-step["+childSGRow+"]//div[text()='DSS']";
	public static String CSG_WL_DSS_REP="//u4v-sg-step["+childSGRow+"]//div[text()='DSS + Rep']";
	//NUMBER OF VOLUMES
	public static String CSG_VOLUMES_NUM_XPATH="//u4v-sg-step["+childSGRow+"]//input[@aria-label='childSgVols']";
	//VOLUME CAPACITY
	public static String CSG_VOLUMES_SIZE__XPATH="//u4v-sg-step["+childSGRow+"]//input[@aria-label='sgCap']";
	public static String CSG_VOLUMES_CAPACITY_UNITS_XPATH="//u4v-sg-step["+childSGRow+"]//div[@ng-if='showColumnPolicy.showVolsCap']/md-input-container";
	public static String CSG_UNIT_MB="//u4v-sg-step["+childSGRow+"]//div[text()='MB']";
	public static String CSG_UNIT_GB="//u4v-sg-step["+childSGRow+"]//div[text()='GB']";
	public static String CSG_UNIT_TB="//u4v-sg-step["+childSGRow+"]//div[text()='TB']";
	public static String CSG_UNIT_CYL="//u4v-sg-step["+childSGRow+"]//div[text()='Cyl']";
	
	
	
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
	public WebElement noneCSG(){
		return findByXPath(CSG_SL_NONE);	
	}

//	//WORKLOAD
//	@FindBy(xpath=CSG_WORKLOAD_LIST_BOX_XPATH)
//	public WebElement worloadListBoxCSG;
//	@FindBy(xpath=CSG_WL_OLTP)
//	public WebElement oltpCSG;
//	@FindBy(xpath=CSG_WL_OLTP_REP)
//	public WebElement oltp_repCSG;
//	@FindBy(xpath=CSG_WL_DSS)
//	public WebElement dssCSG;
//	@FindBy(xpath=CSG_WL_DSS_REP)
//	public WebElement dss_repCSG;
//	//VOLUMES
//	@FindBy(xpath=CSG_VOLUMES_NUM_XPATH)
//	public WebElement numberOfVolumesCSG;
//	@FindBy(xpath=CSG_VOLUMES_SIZE__XPATH)
//	public WebElement volumeSizeCSG;
//	@FindBy(xpath=CSG_VOLUMES_CAPACITY_UNITS_XPATH)
//	public WebElement volumeUnitCSG;
//	@FindBy(xpath=CSG_UNIT_MB)
//	public WebElement csgMB;
//	@FindBy(xpath=CSG_UNIT_GB)
//	public WebElement csgGB;
//	@FindBy(xpath=CSG_UNIT_TB)
//	public WebElement csgTB;
//	@FindBy(xpath=CSG_UNIT_CYL)
//	public WebElement csgCYL;

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
