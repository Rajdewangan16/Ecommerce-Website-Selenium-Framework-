package ecommerce.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.OrderPage;

public class AbstractComponents {

	public WebDriver driver;
	private WebDriverWait wait;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']") private WebElement cartLink;
	@FindBy(css="[routerlink*='myorders']") private WebElement orderLink;
	
	private By myCartText = By.cssSelector("div[class='heading cf'] h1");
	
	public CartPage goToCartPage() {
		cartLink.click();
		waitForElementToAppearBy(myCartText);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrderPage() {
		orderLink.click();
		waitForElementToAppear(cartLink);
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	public void waitForElementToAppearBy(By findBy) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement element) {
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
