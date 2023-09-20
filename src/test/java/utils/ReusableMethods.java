package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import craterPagesPOM.LoginPOM;

public class ReusableMethods {

	
	  public static void selectAllDelete(WebElement elem) {
		  elem.sendKeys(Keys.CONTROL+"a", Keys.BACK_SPACE);
	  }
	  
	  public static void Backspaces(WebElement elem) {
			String txt=  elem.getAttribute("value");
			for(int i=0; i<txt.length();++i) {
				elem.sendKeys(Keys.BACK_SPACE);
			}
		  }
	  
  //CRATER METHODS
	  
	  public static void craterLogin() {
		  LoginPOM login = new LoginPOM();
		  login.userEmailField.sendKeys(DataReader.getData("craterValidUserEmail"));
		  login.passwordField.sendKeys(DataReader.getData("craterValidPassword"));
		  login.loginButton.click();
	  }
	  
	  public static void browserSetup() {
		  Driver.getDriver().manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		  Driver.getDriver().manage().window().maximize();
	  }
}
