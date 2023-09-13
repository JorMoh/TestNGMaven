package tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pagesPOM.SauceLogin;
import utils.DataReader;
import utils.Driver;

public class SauceTestNG {
	
	SauceLogin sauce = new SauceLogin();
  
	@BeforeTest
	public void statrtup() {
		Driver.getDriver().manage().window().maximize();
	}
	
	@BeforeMethod
	public void commonSteps() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Driver.getDriver().get(DataReader.getData("sauceUrl"));
		sauce.passwordField.sendKeys(DataReader.getData("saucePassword"));
		
	}
	
	@Test
	public void validUserLogin() {
//		go to https://saucedemo.com
//		log in with valid username = standard_user password = secret_sauce
		sauce.userField.sendKeys(DataReader.getData("sauceValidUser"));
		sauce.loginBtn.click();
//		Verify that you are logged in successfully.
		Assert.assertTrue(sauce.productsText.isDisplayed());
	}
	
	@Test
	public void invalidUserLogin() {
//		go to https://saucedemo.com
//		log in with invalid username = invalid_user password = secret_sauce
		sauce.userField.sendKeys("invalid_user");
		sauce.loginBtn.click();
//		Verify that you are not logged in, and you get the error message of:
//		“
//		Epic sadface: Username and password do not match any user in this service
		Assert.assertTrue(sauce.loginBtn.isDisplayed());
		Assert.assertTrue(sauce.credentialsDontMatchError.isDisplayed());
	}
	
	@Test
	public void lockedOutUser() {
//		go to https://saucedemo.com
//		log in with locked username = locked_out_user password = secret_sauce
		sauce.userField.sendKeys("locked_out_user");
//		Verify that you are not logged in, and you get the error message of:
//		“Epic sadface: Sorry, this user has been locked out.”
		sauce.loginBtn.click();
		Assert.assertTrue(sauce.loginBtn.isDisplayed());
		Assert.assertTrue(sauce.lockedOutUserError.isDisplayed());
	}
	
	@AfterTest
	public void cleanup() {
		Driver.quitDriver();
	}
}
