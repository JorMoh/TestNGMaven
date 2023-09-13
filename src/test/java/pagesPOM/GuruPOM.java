package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class GuruPOM {
	
	public GuruPOM() {
	PageFactory.initElements(Driver.getDriver(), this);
	}
    
	@FindBy(xpath="//a[text()='Click Here']")
	public WebElement clickHereBtn;
	
	@FindBy(name="emailid")
	public WebElement emailIdField;
	
	@FindBy(name="btnLogin")
	public WebElement submitButton;
	
	@FindBy(xpath="//td[text()='User ID :']/following-sibling::td")
	public WebElement userIdValue;
	
	@FindBy(xpath="//td[text()='Password :']/following-sibling::td")
	public WebElement passwordValue;
	
	@FindBy(xpath="//h3[text()='This access is valid only for 20 days.']")
	public WebElement accessibiltyText;
}
