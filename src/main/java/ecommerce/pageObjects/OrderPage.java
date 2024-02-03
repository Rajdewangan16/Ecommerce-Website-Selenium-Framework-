package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	public WebDriver driver;
	private Boolean matches = null;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".table td:nth-child(3)")private List<WebElement> orderList;
	
	public boolean verifyOrderDisplay(String productName) {
		return orderList.stream().anyMatch(op -> op.getText().equalsIgnoreCase(productName));
	}

	public boolean verifyMultipleOrderDisplay(List<String> productLists) {
		productLists.forEach(productList -> { matches =
				orderList.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productList)); 
			});
			
			return matches;
	}
}
