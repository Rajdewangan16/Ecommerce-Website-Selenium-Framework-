package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	private Boolean matches = null;
	public WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3") private List<WebElement> cartProducts;
	@FindBy(css=".totalRow button") private WebElement checkOutButton;
	@FindBy(css="div[class='ng-star-inserted'] h1") private WebElement emptyCartText;
	private By waitForCheckOutPage = By.cssSelector(".actions a");
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public Boolean verifyMultipleProductDisplay(List<String> productLists) {
		
		
		productLists.forEach(productList -> { matches =
			cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productList)); 
		});
		
		return matches;
	}
	
	public String getEmptyCartMessage() {
		return emptyCartText.getText();
	}
	
	public CheckOutPage goToCheckOutPage() throws InterruptedException {
		scrollIntoView(checkOutButton);
		Thread.sleep(500);
		checkOutButton.click();
		waitForElementToAppearBy(waitForCheckOutPage);
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
