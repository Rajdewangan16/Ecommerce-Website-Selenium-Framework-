package ecommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractComponents.AbstractComponents;

public class PlaceOrderPage extends AbstractComponents{

	public WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary") private WebElement confirmationMessage;
	
	public String getOrderMessage() {
		return confirmationMessage.getText();
	}
}
