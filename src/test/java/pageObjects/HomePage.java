package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//span[contains(text(),'My Account')]") 
WebElement lnkMyaccount;

@FindBy(xpath="//a[contains(text(),'Register')]") 
WebElement lnkRegister;

@FindBy(xpath = "//a[contains(text(),'Login')]")   // Login link added in step5
WebElement linkLogin;



public void clickMyAccount()
{
	lnkMyaccount.click();
}

public void clickRegister()
{
	
	lnkRegister.click();
}

public void clickLogin()    // added in step5
{
	linkLogin.click();
}

}
