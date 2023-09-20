package craterPagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CommonPOM {

	public CommonPOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath = "//a[contains(text(),'Items')]")
	public WebElement itemsTab;
	
	@FindBy(xpath = "//button[@id='headlessui-menu-button-3']")
	public WebElement profileButton;
	
	@FindBy(xpath = "(//a[@class='text-gray-700 group flex items-center px-4 py-2 text-sm font-normal'])[2]")
	public WebElement logoutLink;
	
}
