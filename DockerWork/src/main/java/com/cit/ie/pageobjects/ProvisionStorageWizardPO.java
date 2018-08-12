package com.cit.ie.pageobjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProvisionStorageWizardPO extends StorageGroupsPO {
	private WebDriver driver;
	public ProvisionStorageWizardPO(WebDriver wdriver) {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	private String childSGRow;
	//LOCATORS
	public final String PROVISION_STORAGE_TITLE_XPATH = "//h2[text()='Provision Storage']";	
	public final String STORAGE_GROUP_NAME_LABEL_XPATH = ".//label[text()='Storage Group Name']";
	public final String STORAGE_GROUP_NAME_TF = ".//label[text()='Storage Group Name']/following-sibling::input";
	//STORAGE RESOURCE POOL
	public final String SRP_LABEL_XPATH = ".//label[text()='Storage Resource Pool']";
	public final String SRP_LIST_BOX = ".//md-select[contains(@aria-label,'Storage Resource Pool')]";
	public final String DEFAULT_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='DEFAULT_SRP']";
	public final String NONE_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='None']";
	public final String SRP2_SRP_MENUITEM_XPATH=".//md-option[@ng-value='selectedSrp']/div[text()='SRP_2']";
	public final String WAIT_FOR_PAGELOAD="//span/div[text()='DEFAULT_SRP']";
	//STANDALONE STORAGE GROUPS
	//SERVICE LEVEL
	public final String SERVICELEVEL_LIST_BOX_XPATH="//div[@ng-if='showColumnPolicy.showServiceLevel']/md-input-container";
	public final String SL_OPTIMIZED=".//md-option/div[text()='Optimized']";
	public final String SL_DIAMOND=".//md-option/div[text()='Diamond']";
	public final static String SL_PLATINUM=".//md-option/div[text()='Platinum']";
	public final String SL_GOLD=".//md-option/div[text()='Gold']";
	public final static String SL_SILVER=".//md-option/div[text()='Silver']";
	public final String SL_BRONZE=".//md-option/div[text()='Bronze']";
	public final String SL_NONE=".//*[@ng-value='selectedSloInfoObject']/div[text()='None']";
	//WORKLOAD TYPE
	public final String WORKLOAD_LIST_BOX_XPATH="//div[@ng-if='showColumnPolicy.showWorkload']/md-input-container";
	public final String WL_OLTP=".//div[text()='OLTP']";
	public final String WL_OLTP_REP=".//div[text()='OLTP + Rep']";
	public final static String WL_DSS=".//div[text()='DSS']";
	public final String WL_DSS_REP=".//div[text()='DSS + Rep']";
	public final String WL_NOT_SPECIFIED=".//md-option/div[text()='Not Specified']";
	//NUMBER OF VOLUMES
	public final String VOLUMES_NUM_XPATH="//input[@aria-label='childSgVols']";
	//VOLUME CAPACITY
	public final String VOLUMES_SIZE__XPATH="//input[@aria-label='sgCap']";
	public final String VOLUMES_CAPACITY_UNITS_XPATH="//div[@ng-if='showColumnPolicy.showVolsCap']/md-input-container";
	public final String UNIT_MB="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='MB']";
	public final String UNIT_GB="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='GB']";
	public final String UNIT_TB="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='TB']";
	public final String UNIT_CYL="//md-option[@ng-repeat='capUnit in sg.volumeCapacityUnits']/div[text()='Cyl']";
	//EDIT OPTIONS
	public final String EDIT_ICON_XPATH="//button[@aria-label='editSgByName']";
	public final String ALLOC_CAPACITY="//md-checkbox[@aria-label='allocCapForVol']";
		//	+ "/div[1]/div[@class='md-icon']";
	public final String PERSIST_CAPACITY="//md-checkbox[@aria-label='persist']";
	//BUTTONS
	public final String CREATE_STORAGE_GROUP_RUN_NOW_BUTTON_XPATH="//section[@wz-title='Create Storage Group(s)']//button[text()='Run Now']";
	public final String CANCEL_STORAGE_GROUP_DIALOG_XPATH="//section[@wz-title='Create Storage Group(s)']//span[text()='CANCEL']";
	public final String NEXT_PAGE_BUTTON_XPATH="//section[@wz-title='Create Storage Group(s)']//button[@aria-label='NEXT']";
	public final String ADD_STORAGE_GROUP_BUTTON_XPATH="//button[@aria-label='Add Storage Group']";
	public final String SET_IO_HOST_LIMITS_BUTTON_XPATH=".//button[@aria-label='Set Host I/O Limits']/span[@class='ng-scope']";
	//ERRORS
	public final String SG_ALREADY_EXISTS="//p[text()=\"Error in Creating Storage Group '00DC23_1': Cannot use the specified name because it's already in use\"]";
	public final String RETRIEVING ="//div[@aria-hidden='false']//div[text()='Retrieving data']";
	
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
	
	//*******************************  CASCADED STORAGE GROUPS ***********************************
	//SERVICE LEVEL
	public void setRowForChildSG(int i){
		System.out.println("**************");
		childSGRow=Integer.toString(i);
		System.out.println("setting to row " +i);
		System.out.println("setting to row " +childSGRow);
		long id = Thread.currentThread().getId();
		System.out.println("setting row. Thread id is: " + id);	
		
	}
	public String CSG_SERVICELEVEL_LIST_BOX_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showServiceLevel']/md-input-container";
	public String CSG_SL_OPTIMIZED=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Optimized']";
	public String CSG_SL_DIAMOND=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Diamond']";
	public String CSG_SL_PLATINUM=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Platinum']";
	public String CSG_SL_GOLD=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Gold']";
	public String CSG_SL_SILVER=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Silver']";
	public String CSG_SL_BRONZE=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Bronze']";
	public String CSG_SL_NONE=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='None']";
	//WORKLOAD TYPE
	public String CSG_WORKLOAD_LIST_BOX_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showWorkload']/md-input-container";
	public String CSG_WL_OLTP=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='OLTP']";
	public String CSG_WL_OLTP_REP=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='OLTP + Rep']";
	public String CSG_WL_DSS=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='DSS']";
	public String CSG_WL_DSS_REP=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='DSS + Rep']";
	public String CSG_WL_NONE=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Not Specified']";
	//NUMBER OF VOLUMES
	public String CSG_VOLUMES_NUM_XPATH="//u4v-sg-step[]//input[@aria-label='childSgVols']";
	//VOLUME CAPACITY
	public String CSG_VOLUMES_CAPACITY_XPATH="//u4v-sg-step[]//input[@aria-label='sgCap']";
	public String CSG_VOLUMES_CAPACITY_UNITS_XPATH="//u4v-sg-step[]//div[@ng-if='showColumnPolicy.showVolsCap']/md-input-container";
	public String CSG_UNIT_MB="//div[@class='md-select-menu-container md-active md-clickable']//div[text()='MB']";
	public String CSG_UNIT_GB=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='GB']";
	public String CSG_UNIT_TB=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='TB']";
	public String CSG_UNIT_CYL=".//div[@class='md-select-menu-container md-active md-clickable']//div[text()='Cyl']";	
	public String CSG_MB="test";
	
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
	 * @param xpath
	 * @return web element
	 * @description specialized findByXpath for Child Storage Group rows
	 */
	public WebElement findByXPath(String xpath){
		WebElement element=null;
		try {
			element = driver.findElement(By.xpath(xpath.replace("//u4v-sg-step[","//u4v-sg-step["+childSGRow)));		
		} catch (Exception e) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			try {
				System.out.println("**************WRITING FILE:**********");
				FileUtils.copyFile(scrFile, new File("/failXpath/testScreenShot.jpg"));
				System.out.println("**************WRITTEN FILE:**********");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return element;
	}

}
