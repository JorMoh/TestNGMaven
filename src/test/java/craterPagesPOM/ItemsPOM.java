package craterPagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class ItemsPOM {
	
   public ItemsPOM() {
	PageFactory.initElements(Driver.getDriver(), this);
   }
   
   @FindBy(xpath="(//span[@style='font-family: sans-serif;'])[1]")
   public WebElement addedItemPrice;
   
   @FindBy(xpath="//button[text()=' Add Item']")
   public WebElement addItemBtn;
   
   @FindBy(xpath="//button[text()='Filter ']")
   public WebElement filterItemBtn;
   
   @FindBy(xpath="//div[@name='name']/input")
   public WebElement filterNameField;
   
   //ADD ITEM FIELDS
   @FindBy(xpath="//h3[text()='New Item']")
   public WebElement newItemHeader;
   
   @FindBy(xpath="(//input[@type= 'text'])[2]")
   public WebElement nameField;
   
   @FindBy(xpath="(//input[@type= 'tel'])")
   public WebElement priceField;
   
   @FindBy(xpath="(//input[@type= 'text'])[3]")
   public WebElement unitField;
   
   @FindBy(xpath="(//span[text()='box'])")
   public WebElement unitFieldbox;
   
   @FindBy(xpath="//textarea[@name='description']")
   public WebElement descriptionField;
   
   @FindBy(xpath="//button[text()=' Save Item']")
   public WebElement saveItemBtn;
   
   @FindBy(xpath="//p[text()='Item created successfully']")
   public WebElement itemCreatedMsg;
// -----------------------------------------
 //UPDATE ITEM FIELDS
   @FindBy(xpath="//h3[text()='Edit Item']")
   public WebElement editItemHeader;
   
   @FindBy(xpath="//button[text()=' Update Item']")
   public WebElement updateItemBtn;
   
   @FindBy(xpath="//p[text()='Item updated successfully']")
   public WebElement itemUpdatedMsg;
   
   @FindBy(xpath="(//a[@class='font-medium text-primary-500'])[1]")
   public WebElement addedItem;
		   
   @FindBy(xpath="//a[text()='Book']")
   public WebElement bookItem;
   
   @FindBy(xpath="//span[text()='$ 30.00']")
   public WebElement bookPrice;
 
   @FindBy(xpath="(//a[@href='#'])[6]")
   public WebElement nextButton;
 
   @FindBy(xpath="(//button[@type='button'])[3]")
   public WebElement threeDotsBtn;
   
   @FindBy(xpath="//a[text()=' Delete']")
   public WebElement ItemDeleteBtn;
   
   @FindBy(xpath="//button[text()='Ok']")
   public WebElement deleteOkButton;
   
   @FindBy(xpath="//p[text()='Item deleted successfully']")
   public WebElement itemDeletedMsg;
   
   @FindBy(xpath= "//button[@id='headlessui-menu-button-3']")
   public WebElement primeTechBtn;
   
   @FindBy(partialLinkText = "Logout")
   public WebElement logoutBtn;
}
