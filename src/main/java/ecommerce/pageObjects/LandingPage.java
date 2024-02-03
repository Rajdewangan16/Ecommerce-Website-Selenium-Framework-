package ecommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail") private WebElement userEmail;
	@FindBy(id="userPassword") private WebElement userPassword;
	@FindBy(id="login") private WebElement submit;
	@FindBy(xpath="//div[contains(text(),'*Enter Valid Email')]") private WebElement errorEmailText;
	@FindBy(xpath="//div[contains(text(),'*Password is required')]") private WebElement errorPasswordText;
	@FindBy(css="div[aria-label='Incorrect email or password.']") private WebElement errorLoginText;
	
	public ProductCatalog loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getEmailErrorMessage() {
		waitForElementToAppear(errorEmailText);
		return errorEmailText.getText();
	}
	
	public String getBlankPasswordErrorMessage() {
		waitForElementToAppear(errorPasswordText);
		return errorPasswordText.getText();
	}

	public String getLoginErrorMessage() {
		waitForElementToAppear(errorLoginText);
		return errorLoginText.getText();
	}
}
