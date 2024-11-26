package DemoWebsite;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Bank extends Base {


	@Test(priority=1)
	public void login() throws InterruptedException{
		 
		
		POM PageFactory = new POM(bank);
		
		PageFactory.enterUsername("Admin");
		PageFactory.enterPassword("admin");
		PageFactory.ClickButton();
		
		System.out.println("LOGINED successfully");
		Thread.sleep(3000);
		
	// Check if the error message appears ("The username and password could not be verified")		
		
		if(isElementDisplayed(By.xpath("//p[contains(text(), 'The username and password could not be verified.')]")))
			{
			System.out.println("Login failed. Redirecting to registration page...");
			
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
		bank.findElement(By.id("customer.password")).sendKeys("admin");
		bank.findElement(By.id("repeatedPassword")).sendKeys("admin");
		bank.findElement(By.xpath("//input[@value='Register']")).click();
		System.out.println("USER Registrated successful.");
		Thread.sleep(2000);
		
		PageFactory.enterUsername("Admin");
		PageFactory.enterPassword("admin");
		PageFactory.ClickButton();
		
		System.out.println("LOGINED successfully");
		WebElement LogoutButton= bank.findElement(By.xpath("//a[text() ='Log Out']"));
        
//ASSERTION 		
		Assert.assertTrue(LogoutButton.isDisplayed());
		LogoutButton.click();	
		System.out.println("USER successfully LOGIN");
	 }	
	
	
	else
	{
		
//1				
		System.out.println("Login successful. Proceeding with other functionalities.");
		bank.findElement(By.xpath("//a[text() ='Accounts Overview']"));
				
		System.out.println("Customer successfully enterned into Accounts Overview section");
		
		String CurrentTitle1 = bank.getTitle();
		System.out.println("CurrentTitle1:- "+CurrentTitle1);
		
//ASSERTION		
		assertEquals("ParaBank | Accounts Overview" ,CurrentTitle1);
		
		WebElement ac = bank.findElement(By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a"));
		String account= ac.getText();
		System.out.println("CurrentAccountNo :-" +account);
		
		WebElement bal = bank.findElement(By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[2]"));
		String balance=bal.getText();
		
		System.out.println("CurrentAccount balance :-" +balance);

		System.out.println("1- Accounts Overview Completed");
		Thread.sleep(3000);
		
//2
		bank.findElement(By.xpath("//a[text() ='Transfer Funds']")).click();
		
		System.out.println("Customer successfully enterned into Tranfer section");
		
		bank.findElement(By.xpath("//input[@id='amount']")).sendKeys("100000");
		bank.findElement(By.xpath("//input[@class='button']")).click();
		
		WebElement t =bank.findElement(By.xpath("//h1[text()='Transfer Complete!']"));
		String text= t.getText();
		System.out.println("Message:-" +text);
		Thread.sleep(4000);
		System.out.println("2-Fund Transfer Completed");
		
		String CurrentTitle2 = bank.getTitle();
		System.out.println("CurrentTitle2:- "+CurrentTitle2);
		
//ASSERTION		
		assertEquals(CurrentTitle2,"ParaBank | Transfer Funds");
		
//3
		bank.findElement(By.xpath("//a[text() ='Bill Pay']")).click();
		
		System.out.println("3- Customer successfully enterned into Bill section");
		Thread.sleep(3000);
		String CurrentTitle3 = bank.getTitle();
		System.out.println("CurrentTitle3:- "+CurrentTitle3);
		
//ASSERTION		
		assertEquals(CurrentTitle3,"ParaBank | Bill Pay");
		

//4
		WebElement LogoutButton= bank.findElement(By.xpath("//a[text() ='Log Out']"));
		
//ASSERTION
		Assert.assertTrue(LogoutButton.isDisplayed());
		LogoutButton.click();
		System.out.println("4- Customer successfully LOGOUT ");
		
//Back To HomePage	
		
		WebElement ho= bank.findElement(By.xpath("//*[@id=\"leftPanel\"]/h2"));
		String home= ho.getText();
		System.out.println("BACK TO HOME:-" +home);
		
	}
	
}
}

// //p[text()='The username and password could not be verified.']
// //h1[text()='Error!']