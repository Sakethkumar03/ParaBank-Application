package DemoWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver bank;
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	public ExtentTest test;

	@BeforeTest
	public void launch() {

		WebDriverManager.chromedriver().setup();
		bank = new ChromeDriver();
		bank.manage().window().maximize();

// Setup ExtentReports

		extent = new ExtentReports();
		spark = new ExtentSparkReporter("ExtentReport.html");
		test = extent.createTest("Launch");
		extent.attachReporter(spark);
		bank.get("https://parabank.parasoft.com/");
		String actualURL = bank.getTitle();

//ASSERTION
		Assert.assertEquals("ParaBank | Welcome | Online Banking", actualURL);
		System.out.println("Website :- " + actualURL);
		test.info(actualURL);

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
		extent.flush();
	}

}
