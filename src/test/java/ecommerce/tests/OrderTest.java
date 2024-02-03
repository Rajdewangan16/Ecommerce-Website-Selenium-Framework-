package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.CheckOutPage;
import ecommerce.pageObjects.OrderPage;
import ecommerce.pageObjects.PlaceOrderPage;
import ecommerce.pageObjects.ProductCatalog;
import ecommerce.testComponents.BaseTest;

public class OrderTest extends BaseTest{

	@Test(dataProvider= "userData" , groups = {"purchase"})
	public void purchaseItem(HashMap<String,String> input) throws InterruptedException{
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean matchProduct = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(matchProduct);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		
		checkOutPage.writeCountryName(input.get("writeCountryName"));
		checkOutPage.selectCountry(input.get("selectCountry"));
		PlaceOrderPage placeOrderPage = checkOutPage.confirmPlaceOrder();
		
		String message = placeOrderPage.getOrderMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dataProvider= "userData" , groups = {"purchase"})
	public void purchaseMultipleItems(HashMap<String,String> input) throws InterruptedException{
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalog.addMultipleProductToCart(getItemsData());
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean matchProduct = cartPage.verifyMultipleProductDisplay(getItemsData());
		Assert.assertTrue(matchProduct);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		
		checkOutPage.writeCountryName(input.get("writeCountryName"));
		checkOutPage.selectCountry(input.get("selectCountry"));
		PlaceOrderPage placeOrderPage = checkOutPage.confirmPlaceOrder();
		
		String message = placeOrderPage.getOrderMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods= {"purchaseItem"} , dataProvider="userData" )
	public void verifyOrderList(HashMap<String,String> input) {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		OrderPage orderPage = productCatalog.goToOrderPage();
		Boolean verifyOrder = orderPage.verifyOrderDisplay(input.get("productName"));
		Assert.assertTrue(verifyOrder);
	}
	
	@Test(dependsOnMethods= {"purchaseMultipleItems"} , dataProvider="userData")
	public void verifyMultipleOrderList(HashMap<String,String> input) {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		OrderPage orderPage = productCatalog.goToOrderPage();
		Boolean verifyMultipleOrder = orderPage.verifyMultipleOrderDisplay(getItemsData());
		Assert.assertTrue(verifyMultipleOrder);
	}
	
	@Test(dataProvider="userData" , groups = {"smoke"})
	public void verifyEmptyCart(HashMap<String,String> input) {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		CartPage cartPage = productCatalog.goToCartPage();
		String emptyCartMessage = cartPage.getEmptyCartMessage();
		Assert.assertTrue(emptyCartMessage.equalsIgnoreCase("No Products in Your Cart !"));
	}
	
	@DataProvider(name="userData")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\ecommerce\\datas\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)} , {data.get(1)}};
	}

}
