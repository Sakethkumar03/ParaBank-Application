package DemoWebsite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM {

	public WebDriver bank;
	
	
	@FindBy(xpath="//input[contains(@name,'username')]")
	WebElement  username;
	
	@FindBy(xpath="//input[contains(@name,'password')]")
	WebElement  password;
	
	@FindBy(xpath="//input[contains(@class,'button')]")
	WebElement  button;
	
	
	
	public POM(WebDriver bank) {
		this.bank = bank;
		PageFactory.initElements(bank, this);
	}
	
	
	public void enterUsername(String username) {
		this.username.sendKeys(username);
		
	}

	public void enterPassword(String password) {
		this.password.sendKeys(password);
		
	}

	public void ClickButton() {
		button.click();
	}

	
//	@FindBy(xpath="")
//	WebElement  ;
//	
//	@FindBy(xpath="")
//	WebElement  ;
}
