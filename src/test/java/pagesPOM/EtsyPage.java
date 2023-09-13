package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class EtsyPage {

	public EtsyPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//span[contains(text(), 'Jewelry & Accessories')]")
	public WebElement jewelryAcc;
	
	@FindBy(xpath="//span[contains(text(), 'Bags & Purses')]")
	public WebElement bagsPurses;
	
	@FindBy(xpath="//a[contains(text(), 'Shoulder Bags')]")
	public WebElement shoulderBags;
	
	@FindBy(xpath="//h1[text()='Shoulder Bags']")
	public WebElement shoulderBagsTxt;
}
