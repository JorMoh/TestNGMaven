package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class AmazonHomePOM {
	
	public AmazonHomePOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//a[@data-nav-role='signin']")
	public WebElement homeSignInBtn;
	
	@FindBy(css=".nav-progressive-search-dropdown")
	public WebElement departmentsDropdown;
	
    @FindBy(css="#twotabsearchtextbox")
	public WebElement homeSearchBox;
    
    @FindBy(id="nav-search-submit-button")
    public WebElement searchBtn;
    
    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    public WebElement searchedItemText;

}
