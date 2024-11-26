package DemoWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Base {

	public static WebDriver bank ;
	
	
@BeforeTest
	public void launch() {
	     
	WebDriverManager.chromedriver().setup();
	    bank = new ChromeDriver();
        bank.manage().window().maximize();
		bank.get("https://parabank.parasoft.com/");
		
		String actualURL = bank.getTitle();
		Assert.assertEquals("ParaBank | Welcome | Online Banking", actualURL);
		System.out.println("Website :- " +actualURL);
		
	}
		

public boolean isElementDisplayed(By xpath) {
    try {
        WebElement element = bank.findElement(xpath);
        return element.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
@AfterTest		
		public void close() {
			
			bank.close();
			bank.quit();
			System.out.println("Testing completed");
		}
		
}
