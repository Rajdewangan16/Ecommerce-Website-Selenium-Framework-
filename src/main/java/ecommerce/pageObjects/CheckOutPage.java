package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	public WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']") private WebElement countryBox;
	@FindBy(css=".ta-results button") private List<WebElement> countryOptions;
	@FindBy(css=".actions a") private WebElement submitPlaceOrder;
	
	private By dropDownList = By.cssSelector(".ta-results");
	private By confirmMessage = By.cssSelector(".hero-primary");
	
	public void writeCountryName(String countryName) {
		countryBox.sendKeys(countryName);
	}
	public void selectCountry(String country) {
		
		WebElement countryClick = countryOptions.stream()
							.filter(c -> c.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		waitForElementToAppearBy(dropDownList);
		countryClick.click();
	}
	public PlaceOrderPage confirmPlaceOrder() {
		scrollIntoView(submitPlaceOrder);
		waitForElementToClickable(submitPlaceOrder);
		submitPlaceOrder.click();
		waitForElementToAppearBy(confirmMessage);
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}
}
