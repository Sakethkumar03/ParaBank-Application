package DemoWebsite;

import org.testng.annotations.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bank extends Base {

	@Test(priority = 1)
	public void login() throws InterruptedException {

		test = extent.createTest("Test Case: " + this.getClass().getSimpleName());
		POM PageFactory = new POM(bank);

		PageFactory.enterUsername("Admin");
		PageFactory.enterPassword("admin");
		PageFactory.ClickButton();

		test.pass("Logined successfully");

		System.out.println("LOGINED successfully");
		Thread.sleep(3000);

		// Check if the error message appears ("The username and password could not be verified")

		if (isElementDisplayed(By.xpath("//p[contains(text(), 'The username and password could not be verified.')]"))) {
			System.out.println("Login failed. Redirecting to registration page...");

			test = extent.createTest("Registration");

			bank.findElement(By.linkText("Register")).click();
			bank.findElement(By.id("customer.firstName")).sendKeys("B.");
			bank.findElement(By.id("customer.lastName")).sendKeys("SK");
			bank.findElement(By.id("customer.address.street")).sendKeys("NONE");
			bank.findElement(By.id("customer.address.city")).sendKeys("HYD");
			bank.findElement(By.id("customer.address.state")).sendKeys("TG");
			bank.findElement(By.id("customer.address.zipCode")).sendKeys("500032");
			bank.findElement(By.id("customer.phoneNumber")).sendKeys("6543217890");
			bank.findElement(By.id("customer.ssn")).sendKeys("12345");

			bank.findElement(By.id("customer.username")).sendKeys("Admin");
			bank.findElement(By.id("customer.password")).sendKeys("Admin");
			bank.findElement(By.id("repeatedPassword")).sendKeys("Admin");
			bank.findElement(By.xpath("//input[@value='Register']")).click();
			System.out.println("USER Registrated successful.");
			test.pass("Registration test passed successfully.");

			Thread.sleep(5000);
		
//		PageFactory.enterUsername("Admin");
//		PageFactory.enterPassword("admin");
//		PageFactory.ClickButton();

			test.pass("After Registration User Logined successfully.");
			System.out.println("LOGINED successfully");
			WebElement LogoutButton = bank.findElement(By.xpath("//a[text() ='Log Out']"));

//ASSERTION 		
			Assert.assertTrue(LogoutButton.isDisplayed());
			System.out.println("USER successfully LOGIN");
			Functionalities();
		}

		else {
			System.out.println(" LOGIN with valid credentials");
		}
	}
		private void Functionalities() throws InterruptedException {
//1-AccountOverview Functionality			
			System.out.println("Login successful. Proceeding with other functionalities.");
			bank.findElement(By.xpath("//a[text() ='Accounts Overview']")).click();

			System.out.println("Customer successfully enterned into Accounts Overview section");

			String CurrentTitle1 = bank.getTitle();
			System.out.println("CurrentTitle1:- " + CurrentTitle1);

//ASSERTION		
			Assert.assertEquals("ParaBank | Accounts Overview", CurrentTitle1);

			WebElement ac = bank.findElement(By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a"));
			String account = ac.getText();
			System.out.println("CurrentAccountNo :-" + account);

			WebElement bal = bank.findElement(By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[2]"));
			String balance = bal.getText();

			System.out.println("CurrentAccount balance :-" + balance);

			System.out.println("1- Accounts Overview Completed");
			test.pass("AccountOverview test passed successfully.");
			Thread.sleep(3000);

//2-TransferFunds Functionality
			bank.findElement(By.xpath("//a[text() ='Transfer Funds']")).click();

			System.out.println("Customer successfully enterned into Tranfer section");

			bank.findElement(By.xpath("//input[@id='amount']")).sendKeys("100000");
			bank.findElement(By.xpath("//input[@class='button']")).click();

			WebElement t = bank.findElement(By.xpath("//h1[text()='Transfer Complete!']"));
			String text = t.getText();
			System.out.println("Message:-" + text);
			Thread.sleep(4000);
			System.out.println("2-Fund Transfer Completed");

			String CurrentTitle2 = bank.getTitle();
			System.out.println("CurrentTitle2:- " + CurrentTitle2);
			test.pass("TransferFunds test passed successfully.");

//ASSERTION		
			Assert.assertEquals(CurrentTitle2, "ParaBank | Transfer Funds");

//3- BillPay Functionality
			bank.findElement(By.xpath("//a[text() ='Bill Pay']")).click();

			System.out.println("3- Customer successfully enterned into Bill section");
			Thread.sleep(3000);
			String CurrentTitle3 = bank.getTitle();
			System.out.println("CurrentTitle3:- " + CurrentTitle3);
			test.pass("BillPay test passed successfully.");

//ASSERTION		
			Assert.assertEquals(CurrentTitle3, "ParaBank | Bill Pay");

//4- Logout Functionality
			WebElement LogoutButton = bank.findElement(By.xpath("//a[text() ='Log Out']"));

//ASSERTION
			Assert.assertTrue(LogoutButton.isDisplayed());
			LogoutButton.click();
			System.out.println("4- Customer successfully LOGOUT ");

			test.pass("Logout test passed successfully.");
//5- Back To HomePage Functionality

			WebElement ho = bank.findElement(By.xpath("//*[@id=\"leftPanel\"]/h2"));
			String home = ho.getText();
			System.out.println("BACK TO HOME:-" + home);
			test.pass("ALL Functionality test passed successfully.");
			test.info(home);
		}

	}


//ASSERTION
//		WebElement welcome = bank.findElement(By.xpath("//p[@class='smallText']"));
//		Assert.assertTrue(welcome.isDisplayed());
//		System.out.println(welcome.getText());

// bank.findElement(By.xpath("text()='Your account was created successfully. You are now logged in.'")