package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	public WebDriver driver;
	private Actions action;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3") private List<WebElement> products;
	@FindBy(css = "div[class*='ngx-spinner-overlay']") private WebElement loadingOverlay;
	@FindBy(css="div[class='py-2 border-bottom ml-3'] input[placeholder='search']") private WebElement searchBox;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By loadingAnimation = By.cssSelector("div[class*='ng-animating']");

	public List<WebElement> getProductList() {

		waitForElementToAppearBy(productsBy);
		return products;
	}
	
	public void addProductToCart(String targetProduct) {

		WebElement prod = getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(targetProduct))
				.findFirst().orElse(null);
		
		prod.findElement(addToCart).click();
		waitForElementToAppearBy(loadingAnimation);
		waitForElementToDisappear(loadingOverlay);
	}
	
	public void addMultipleProductToCart(List<String> targetProducts ) {
		targetProducts.forEach(targetProduct -> { WebElement pro = getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(targetProduct)).findFirst()
				.orElse(null);
		
		if(pro != null) {
			pro.findElement(addToCart).click();
			waitForElementToAppearBy(loadingAnimation);
			waitForElementToDisappear(loadingOverlay);
		}	
		});
	}
	
	public boolean verifySearchProduct() {
		
	 	return getProductList().stream().anyMatch(s ->
	 			s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(searchBox.getAttribute("value")));
	}
	
	public void searchProductName(String productName) {
		action.moveToElement(searchBox).click().sendKeys(productName).sendKeys(Keys.ENTER).build().perform();
	
	}
	
}
