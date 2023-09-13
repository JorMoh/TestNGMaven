package tests;

import org.testng.annotations.Test;

import pagesPOM.AmazonHomePOM;
import utils.DataReader;
import utils.Driver;
import utils.ExcelUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;

public class ProviderExcel {
 
  @BeforeMethod
  public void setup() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  Driver.getDriver().manage().window().maximize();
  }

  @AfterTest
  public void cleanup() {
	  Driver.quitDriver();
  }
  
  @DataProvider
  public String[] testData() {
	  String[] items = ExcelUtils.getExcelDataInAColumn("./src/test/resources/TestData/AmazonSearchItems.xlsx", "sheet1");
	  return items;
  }
  
  @Test(dataProvider = "testData")
  public void amazonSearch(String item) throws InterruptedException {
	  AmazonHomePOM home = new AmazonHomePOM();
//	  1. go to amazon.com
	  Driver.getDriver().get(DataReader.getData("amazonUrl"));
//	  2. verify that you are on the home page
	 
//	  3. search {TestData} and click search
	  home.homeSearchBox.sendKeys(item, Keys.ENTER);
	 
//	  4. wait for the element to be visible - this could be any searched item or the any text unique to
//	  the search.
//	  5. get text of the search criteria text element
	  String searchResult = home.searchedItemText.getText().substring(1, item.length()+1);
//	  6. verify it matches the search input
	  assertEquals(searchResult, item);

  }

}
