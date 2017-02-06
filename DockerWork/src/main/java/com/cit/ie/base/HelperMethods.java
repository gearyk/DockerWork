package com.cit.ie.base;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cit.ie.pageobjects.HomeDashboardPO;
import com.cit.ie.pageobjects.LoginPagePO;
import com.cit.ie.pageobjects.ProvisionStorageWizardPO;
import com.cit.ie.rest.RESTClient;

public class HelperMethods extends WebDriverManager {

	public WebDriver driver;
	protected int timeOut = 500;
	public WebDriverWait wait;
	private String baseURL="https://10.73.28.70:8443/univmax/restapi/sloprovisioning/symmetrix/000196700348/storagegroup/";

	//@SuppressWarnings("static-access")
	public HelperMethods(WebDriver adriver){
		try {
			driver=adriver;
			//wait = new WebDriverWait(this.driver,timeOut);
			new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) wd ->
			((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		} catch (UnhandledAlertException e) {
            System.err.println("Caught UnhandledAlertException in Helper Constructor: RETRYING");
            driver=adriver;
            new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) wd ->
			((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        }
	}

	public void waitForElementVisiblity(String xpath) throws InterruptedException{
		
		
		try {
			wait=new WebDriverWait(driver, 180, 4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			Thread.sleep(8000);
		} catch (UnhandledAlertException e) {
            System.err.println("Caught UnhandledAlertException in Wait for Element Visability: RETRYING"); 
            driver.switchTo().alert().accept();
            wait=new WebDriverWait(driver, 180, 4000);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    		Thread.sleep(8000);
        }
	}

	public void waitForElementClickability(String xpath) throws InterruptedException{
		
		
		try {
			wait=new WebDriverWait(driver, 120, 4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			Thread.sleep(8000);
		} catch (UnhandledAlertException e) {
            System.err.println("Caught UnhandledAlertException in Wait for Element Clickability: RETRYING"); 
            driver.switchTo().alert().accept();
            wait=new WebDriverWait(driver, 120, 4000);
    		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    		Thread.sleep(8000);
        }
	}
	
	public void jsClickElement(WebElement webEl) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()",webEl);
		Thread.sleep(3000);
	}


	public void waitForLoad() {
	
		try {
			new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
			((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		} catch (UnhandledAlertException e) {
            System.err.println("Caught UnhandledAlertException in Wait for Page Load: RETRYING"); 
            driver.switchTo().alert().accept();
        	new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
    		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        }
		
	}

	public void waitForElementToDisappear(String text) throws InterruptedException {
		
		try {
			Thread.sleep(1500);
			wait=new WebDriverWait(driver,800,4000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(text)));
			Thread.sleep(3500);
		} catch (UnhandledAlertException e) {
            System.err.println("Caught UnhandledAlertException in Wait for Element to disappear: RETRYING"); 
            wait=new WebDriverWait(driver,800,4000);
            driver.switchTo().alert().accept();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(text)));
			Thread.sleep(3500);
        }
			
	}

	public void haltTest() throws InterruptedException{
		driver.close();
		Thread.sleep(1000);
	}

	public static boolean assertElementNotPresent (WebDriver driver, String text) throws Exception {
		List<WebElement> els = driver.findElements(By.xpath(text));
		if (els.isEmpty() == false) {
			throw new Exception (text + " (element is present)");
		}
		//System.out.println("Element is not present");
		return true;
	}
	
	public static void printTimeStart(String test){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("*************************************************");
		System.out.println(test +" starting at : " +timeStamp);
		System.out.println("*************************************************");
	}
	
	public static void printTimeFinish(String test){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("*************************************************");
		System.out.println(test +" has finished at : " +timeStamp);
		System.out.println("*************************************************");
	}
	
	/**
	 * @author gearyk2
	 * @param sgName
	 * @throws InterruptedException
	 * @description verify the response code of the RESTGET for this storage group
	 * and then call a REST DELETE for the storage group
	 */
	public void verifyAndCleanup(String sgName) throws InterruptedException {
		//VERIFY THAT GROUP HAS BEEN CREATED
		//RESTClient.refreshRestDB(baseURL);
		RESTClient.GET(baseURL+sgName);
		Assert.assertEquals(RESTClient.responseStatus,200);
		//CLEANUP
		//RESTClient.DELETE(baseURL+sgName);
		RESTClient.DELETE(baseURL+sgName);
		    
	}
	
	public void getScreenshot(){
		
	}
	
	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param srp
	 * @throws InterruptedException
	 */
	public void setSrpInformation(ProvisionStorageWizardPO pswpo,String srp) throws InterruptedException {

		pswpo.jsClickElement(pswpo.srpListBox);
		Thread.sleep(2000);
		switch(srp.toLowerCase()){
		case "default_srp":
			pswpo.jsClickElement(pswpo.defaultSRP);
			break;
		case "srp_2":
			pswpo.jsClickElement(pswpo.srp2SRP);
			break;
		case "none":
			pswpo.jsClickElement(pswpo.noneSRP);
			break;
		default:
			pswpo.jsClickElement(pswpo.noneSRP);
			break;
		}
		Thread.sleep(3000);
	}
	
	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param slo
	 * @throws InterruptedException
	 */
	public void setSloInformation(ProvisionStorageWizardPO po, String slo) throws InterruptedException {
		po.jsClickElement(po.sloListBox);
		Thread.sleep(2000);
		switch(slo.toLowerCase()){
		case "platinum":
			//po.platinum.click();
			po.jsClickElement(po.platinum);
			break;
		case "diamond":
			//po.diamond.click();
			po.jsClickElement(po.diamond);
			break;
		case "gold":
			//po.gold.click();
			po.jsClickElement(po.gold);
			break;
		case "silver":
			//po.silver.click();
			po.jsClickElement(po.silver);
			break;
		case "bronze":
			//po.bronze.click();
			po.jsClickElement(po.bronze);
			break;
		case "optimized":
			//po.optimized.click();
			po.jsClickElement(po.optimized);
			break;
		case "none":
			//po.none.click();
			po.jsClickElement(po.none);
			break;
		default:
			//po.none.click();
			po.jsClickElement(po.none);
			break;
		}
		Thread.sleep(1500);
	}
	
	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param workload
	 * @throws InterruptedException
	 */
	public void setWorkloadInformation(ProvisionStorageWizardPO pswpo,String workload) throws InterruptedException {
		//pswpo.workloadListBox.click();
		pswpo.jsClickElement(pswpo.workloadListBox);
		Thread.sleep(1500);
		switch(workload.toLowerCase()){
		case "oltp":
			//pswpo.oltp.click();
			pswpo.jsClickElement(pswpo.oltp);
			break;
		case "oltp_rep":
			//pswpo.oltp_rep.click();
			pswpo.jsClickElement(pswpo.oltp_rep);
			break;
		case "dss":
			//pswpo.dss.click();
			pswpo.jsClickElement(pswpo.dss);
			break;
		case "dss_rep":
			//pswpo.dss_rep.click();
			pswpo.jsClickElement(pswpo.dss_rep);
			break;
		case "none":
			//pswpo.no_workload.click();
			pswpo.jsClickElement(pswpo.none);
			break;
		default:
			break;
		}
		Thread.sleep(1500);
	}
	
	/**
	 * @author gearyk2
	 * @param pswpo
	 * @param numberOfVolumes
	 * @param volumeSize
	 * @param volumeUnit
	 * @throws InterruptedException
	 */
	public void setVolumeInformation(ProvisionStorageWizardPO pswpo,String numberOfVolumes, String volumeSize, String volumeUnit) throws InterruptedException {
		//pswpo.numberOfVolumes.click();
		pswpo.jsClickElement(pswpo.numberOfVolumes);
		Thread.sleep(1500);
		pswpo.numberOfVolumes.clear();
		pswpo.numberOfVolumes.sendKeys(numberOfVolumes);
		//pswpo.volumeUnit.click();
		pswpo.jsClickElement(pswpo.volumeUnit);
		Thread.sleep(1500);
		switch(volumeUnit.toLowerCase()){
		case "gb":
			//pswpo.GB.click();
			pswpo.jsClickElement(pswpo.GB);
			break;
		case "mb":
			//pswpo.MB.click();
			pswpo.jsClickElement(pswpo.MB);
			break;
		case "tb":
			//pswpo.TB.click();
			pswpo.jsClickElement(pswpo.TB);
			break;
		case "cyl":
			//pswpo.CYL.click();
			pswpo.jsClickElement(pswpo.CYL);
			break;
		default:
			//pswpo.GB.click();
			pswpo.jsClickElement(pswpo.GB);
			break;
		}
		Thread.sleep(1500);
		//pswpo.volumeSize.click();
		pswpo.jsClickElement(pswpo.volumeSize);
		Thread.sleep(1500);
		pswpo.volumeSize.clear();
		pswpo.volumeSize.sendKeys(volumeSize);
		Thread.sleep(1500);
	}
	
	/**
	 * @author gearyk2
	 * @description Navigate to Storage Groups Page
	 * @throws InterruptedException
	 */
	public void gotoStorageGroupsPage(LoginPagePO lppo) throws InterruptedException {
		
		lppo.waitForLoginPageObjects();
		lppo.doLogin("smc","smc");
		lppo.waitForElementToDisappear(lppo.USERNAME_FIELD_XPATH);
		Thread.sleep(5000);
		Thread.sleep(5000);
		HomeDashboardPO hdpo=new HomeDashboardPO(driver);
		Thread.sleep(5000);
		hdpo.waitForHomeDashboardPageObjects();
		hdpo.navigateToStorageGroups();

	}
	


}

