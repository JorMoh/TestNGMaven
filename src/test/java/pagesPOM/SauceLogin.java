package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class SauceLogin {
	
	public SauceLogin() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(css="#user-name")
	public WebElement userField;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement passwordField;
	
	@FindBy(css="#login-button")
	public WebElement loginBtn;
	
	@FindBy(xpath="//span[text()='Products']")
	public WebElement productsText;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Username and password do not match any user in this service']")
	public WebElement credentialsDontMatchError;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Sorry, this user has been locked out.']")
	public WebElement lockedOutUserError;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Username is required']")
	public WebElement userNameRequired;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Password is required']")
	public WebElement passwordRequired;
	
	
	
	

}
