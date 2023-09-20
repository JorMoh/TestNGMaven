package craterTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import craterPagesPOM.CommonPOM;
import craterPagesPOM.ItemsPOM;
import craterPagesPOM.LoginPOM;
import utils.BrowserUtils;
import utils.DButils;
import utils.DataReader;
import utils.Driver;
import utils.ReusableMethods;

public class DataBaseValidation {
	BrowserUtils browser = new BrowserUtils();
	DButils db = new DButils();
	CommonPOM common = new CommonPOM();
	ItemsPOM items = new ItemsPOM();
	LoginPOM login = new LoginPOM();
	String addedItem = "Detergent";

	@BeforeMethod
	public void commonSteps() {
		ReusableMethods.browserSetup();
		
	}

	@Test
	public void testCase1() {
//	  Create an item on UI.
		Driver.getDriver().get(DataReader.getData("craterUrl"));
		ReusableMethods.craterLogin();
		common.itemsTab.click();
		items.addItemBtn.click();
		addedItem = addedItem + browser.randomNumber();
		items.nameField.sendKeys(addedItem);
		browser.clearTextOfTheFieldWindows(items.priceField);
		items.priceField.sendKeys("12000");
		items.unitField.click();
		items.unitFieldbox.click();
		items.descriptionField.sendKeys("Persil Detergent");
		items.saveItemBtn.click();

//	  Then go to database, and query from the items table using select * to get the information
		List<String> recordData = db.selectArecord("select * from items where name ='" + addedItem + "'");
		for (int i = 0; i < recordData.size(); i++) {
			System.out.println(recordData.get(i));

//	  Then verify the information that you have provided on UI is correct.
			assertEquals(recordData.get(1), addedItem);
			assertEquals(recordData.get(3), "12000");

//	  Then update your item on the UI,
			WebElement newItem = Driver.getDriver().findElement(By.xpath("//a[text()='" + addedItem + "']"));
			newItem.click();
			browser.clearTextOfTheFieldWindows(items.priceField);
			items.priceField.sendKeys("1200");
			items.updateItemBtn.click();

//	  come back to database and verify the update is in effect.
			List<String> updatedData = db.selectArecord("select * from items where name ='" + addedItem + "' ");
			assertEquals(updatedData.get(3), "1200");
			

//			  Then delete the item on the UI, come back to database and verify the estimate no longer
//			  exist.
//		
			items.threeDotsBtn.click();
			items.ItemDeleteBtn.click();
			items.deleteOkButton.click();
			assertTrue(items.itemDeletedMsg.isDisplayed());
			
			List<String> deletedData = db.selectArecord("select * from items where name ='"+addedItem+"'");
			assertTrue(deletedData.isEmpty());
			break;
			
		}

	}
	@Test
	public void testCase2() throws InterruptedException {
//		  Create an item on UI.
		common.itemsTab.click();
		Thread.sleep(2000);
		items.addItemBtn.click();
		addedItem = addedItem + browser.randomNumber();
		items.nameField.sendKeys(addedItem);
		browser.clearTextOfTheFieldWindows(items.priceField);
		items.priceField.sendKeys("12000");
		items.unitField.click();
		items.unitFieldbox.click();
		items.descriptionField.sendKeys("Persil Detergent");
		items.saveItemBtn.click();
		
//		  Then go to database, and query from the items table using select * to get the information
		
		List<String> recordData = db.selectArecord("select * from items where name = '"+addedItem+"'");
//		  Then verify the information that you have provided on UI is correct.
		assertEquals(recordData.get(1), addedItem);
//		  Then update your Item using DB, and come back to UI and verify the update is in effect.
		db.updateRecord("update items set price =1200 where name ='"+addedItem+"'");
		Driver.getDriver().navigate().refresh();
		assertEquals(items.addedItemPrice.getText(), "$ 12.00");
		
//		  Then delete the Item using DB, and come back to UI and verify the item no longer exists.
		db.deleteRecord("delete from items where name='"+addedItem+"'");
		Driver.getDriver().navigate().refresh();
		assertEquals(Driver.getDriver().findElements(By.xpath("//a[text()='"+addedItem+"']")).size(), 0);




	}

	@AfterTest
	public void cleanup() {
		Driver.quitDriver();
	}
}
