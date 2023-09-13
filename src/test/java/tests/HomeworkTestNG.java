package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pagesPOM.AmazonHomePOM;
import pagesPOM.GuruPOM;
import utils.DataReader;
import utils.Driver;

public class HomeworkTestNG {
	
	AmazonHomePOM amazon = new AmazonHomePOM();
	
	@BeforeTest
	public void startup() {
		Driver.getDriver().manage().window().maximize();
	}
	
	@BeforeMethod
	public void commonSteps() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void case1(){
		AmazonHomePOM amazon = new AmazonHomePOM();
//	    go to amazon.com 
		Driver.getDriver().get(DataReader.getData("amazonUrl"));
//	     get all the options in the departments select dropdown
	    Select select = new Select(amazon.departmentsDropdown);
	    List<WebElement> options = select.getOptions();
//	    verify there are a total 59 departments. And print them out.
	    System.out.println(options.size());
	    assertEquals(options.size(), 56);
	    
	    for(int i=0; i<options.size(); i++) {
	    	System.out.println("Department: "+options.get(i).getText());
	    }
	    
	
	}
	
	@Test
	public void case2() throws InterruptedException {
		AmazonHomePOM amazon = new AmazonHomePOM();
//	     go to amazon.com
		Driver.getDriver().get(DataReader.getData("amazonUrl"));
//	     verify that you are on the amazon home page. (verify with title).
		assertEquals(Driver.getDriver().getTitle(), "Amazon.com. Spend less. Smile more.");
//	     verify dropdown value is by default “All Departments”
		Select select = new Select(amazon.departmentsDropdown);
		assertEquals(select.getFirstSelectedOption().getText(), "All Departments");
//	     select department Home & Kitchen, and search for a coffee mug.
		select.selectByVisibleText("Home & Kitchen");
		
		amazon.homeSearchBox.sendKeys("spatula"+Keys.ENTER);
		
//	     verify you are on the coffee mug search results page (use title).
		
		assertTrue(Driver.getDriver().getTitle().contains("spatula"));
//	     verify you are in the Home & Kitchen department.
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Home & Kitchen" );

	}
	
	@Test
	public void case3() {
		GuruPOM guru = new GuruPOM();
//	    go to http://demo.guru99.com/popup.php
		Driver.getDriver().get(DataReader.getData("guruUrl"));
//	     Get the title and store it in a variable.
		String title = Driver.getDriver().getTitle();
//	     Click on Click Here button and switch over to next window, provide an email click Submit. 
		guru.clickHereBtn.click();
		Set<String> IDs = Driver.getDriver().getWindowHandles();
		Iterator<String> iter = IDs.iterator();
		String firstWindow = iter.next();
		String secondWindow = iter.next();
		Driver.getDriver().switchTo().window(secondWindow);
		guru.emailIdField.sendKeys("m.uhaidat877@yahoo.com");
		guru.submitButton.click();
//	    Get text of the user ID and password and store them in variables, print them out. 
		String userTxt = guru.userIdValue.getText();
		String passwordTxt = guru.passwordValue.getText();		
//	    Then verify the text “This access is valid only for 20 days.”
		assertEquals(guru.accessibiltyText.getText(), "This access is valid only for 20 days.");
//	    Close the window.
		Driver.getDriver().close();
//	    Go back to the main window, and get the title then verify it equals to your stored title.
		Driver.getDriver().switchTo().window(firstWindow);

	}
	
	@AfterTest
	public void cleanup() {
		Driver.quitDriver();
	}
	





}
