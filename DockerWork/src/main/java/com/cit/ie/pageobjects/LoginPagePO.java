package com.cit.ie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.base.HelperMethods;

/**
 * @name LoginPage PageObject
 * @description Page Objects for login Page
 * @author gearyk2
 */
public class LoginPagePO extends HelperMethods
{
	public LoginPagePO(WebDriver wdriver) throws InterruptedException {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1920, 1080));
		System.out.println("Window size = "+driver.manage().window().getSize());
		driver.get("https://10.73.28.70:8443/univmax/jsclient/#/login");
		PageFactory.initElements(driver, this);
		//System.out.println("Initialise LoginPO");
	}

	//LOCATORS
	public final String LOGIN_PAGE_TITLE_XPATH = ".//*[@class='login_product_name layout-row']";
	public final String USERNAME_FIELD_XPATH = ".//input[@name='uName']";
	public final String PASSWORD_FIELD_XPATH = ".//input[@name='pWrd']";
	public final String LOGIN_BUTTON_XPATH = ".//*/button[@aria-label='Login']";
	public final String VERSION_NUMBER_XPATH = ".//*/div[text()[contains(.,'Version')]]";
	public final String LOGIN_LOGO_XPATH = ".//*[@id='login_logo']";
	public final String BAD_LOGIN_XPATH=".//*/span[text()='Error Logging In']";

	//WEB ELEMENTS
	@FindBy(xpath=LOGIN_PAGE_TITLE_XPATH)
	public WebElement loginPageTitle;
	@FindBy(xpath=USERNAME_FIELD_XPATH)
	public WebElement usernameField;
	@FindBy(xpath=PASSWORD_FIELD_XPATH)
	public WebElement passwordField;
	@FindBy(xpath=LOGIN_BUTTON_XPATH)
	public WebElement loginButton;
	@FindBy(xpath=LOGIN_LOGO_XPATH)
	public WebElement loginLogo;
	@FindBy(xpath=VERSION_NUMBER_XPATH)
	public WebElement versionNumber;
	@FindBy(xpath=BAD_LOGIN_XPATH)
	public WebElement errorLoggingIn;

	public void waitForLoginPageObjects() throws InterruptedException{
		waitForElementVisiblity(LOGIN_LOGO_XPATH);
	}

	public WebElement loginPage(){
		WebElement element = (new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_PAGE_TITLE_XPATH)));
		return element;
	}

	public void doLogin(String username, String password){
		usernameField.click();
		usernameField.sendKeys(username);
		passwordField.click();
		passwordField.sendKeys(password);
		loginButton.click();
	}

}
