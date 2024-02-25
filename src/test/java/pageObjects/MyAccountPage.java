package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	//@FindBy(xpath = "//h2[text()='My Account']")
	@FindBy(xpath = "//h2[contains(text(),'My Account')]")// MyAccount Page heading  //h2[normalize-space()='My Account']
	WebElement msgHeading;
	
	//@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	@FindBy(xpath = "//body/main[1]/div[2]/div[1]/aside[1]/div[1]/a[13]")
	WebElement lnkLogout; 
	

	public boolean isMyAccountPageExists()   // MyAccount Page heading display status
	{
		try {
			return (msgHeading.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}

	public void clickLogout() {
		lnkLogout.click();

	}

}
