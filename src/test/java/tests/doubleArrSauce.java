package tests;

import org.testng.annotations.Test;

import pagesPOM.SauceLogin;
import utils.DataReader;
import utils.Driver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;

public class doubleArrSauce {
 
  @BeforeMethod
  public void setup() {
	  Driver.getDriver().manage().window().maximize();
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterTest
  public void cleanup() {
	  Driver.quitDriver();
  }
  
  @DataProvider
  public String[][] testCreds() {
	  String[][] creds = new String[4][2];
	 
	  creds[0][0] = "standard_user"; 
	  creds[0][1] = "dfhdfgh";
	  creds[1][0] = "invalid";
	  creds[1][1] = "secret_sauce";
	  creds[2][0] = "";
	  creds[2][1] = "secret_sauce";
	  creds[3][0] = "standard_user";
	  creds[3][1] = "";
	  
			  
	  return creds;
  }

  @Test(dataProvider="testCreds")
  public void login(String username, String password) {
	  SauceLogin sauce = new SauceLogin();
	  //go to sauceDemo.com
	  Driver.getDriver().get(DataReader.getData("sauceUrl"));
	  //enter a valid username
	  sauce.userField.sendKeys(username);
	  //enter an invalid password
	  sauce.passwordField.sendKeys(password);
	  //click login
	  sauce.loginBtn.click();
	  //verify your error message contains "Epic sadface:"
	  if(username.isBlank()) {
		  assertTrue(sauce.userNameRequired.isDisplayed());
	  }
	  else if(password.isBlank()) {
		  assertTrue(sauce.passwordRequired.isDisplayed());
	  }else {
		  assertTrue(sauce.credentialsDontMatchError.isDisplayed());
	  }
	  
	  

  }
}
