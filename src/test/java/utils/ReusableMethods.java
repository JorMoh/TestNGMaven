package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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
}
