package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

public class Driver {
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		if(driver == null) {
			switch(DataReader.getData("browser")) {
			
			case "chrome":
                ChromeDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
				
			case "firefox":
                FirefoxDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "edge":
				EdgeDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
				
			default:
				ChromeDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
		}
		return driver;
	}
	
	public static void quitDriver() {
		if(driver!=null) {
			driver.quit();
			driver=null;
		}
	}

}
