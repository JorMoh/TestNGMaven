package tests;

import org.testng.annotations.Test;

import pagesPOM.AmazonHomePOM;
import utils.DataReader;
import utils.Driver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Provider {
  @BeforeMethod
  public void beforeMethod() {
	  Driver.getDriver().manage().window().maximize();
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  }
	
  @DataProvider
  public String[] testData() {
	  String[] items = new String[3];
	  items[0] = "fork";
	  items[1] = "spoon";
	  items[2] = "knife";
	  return items;
	  
  }
	
  @Test(dataProvider = "testData")
  public void searchTest(String item) {
	  AmazonHomePOM home = new AmazonHomePOM();
     //1. go to amazon.com
	  Driver.getDriver().get(DataReader.getData("amazonUrl"));
	 //2. search {TestData} and click search
	  WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
	  wait.until(ExpectedConditions.visibilityOf(home.homeSearchBox));
	  home.homeSearchBox.sendKeys(item, Keys.ENTER);
	 //3. get text of the search criteria text element
	 String searchResult = home.searchedItemText.getText().substring(1, item.length()+1);
	 //4.verify searched item text matches searched item excluding quotations.
	 assertEquals(searchResult, item);
  }
 

  @AfterTest
  public void afterTest() {
	  Driver.quitDriver();
  }

}
