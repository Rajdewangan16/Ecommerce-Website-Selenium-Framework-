package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.ProductCatalog;
import ecommerce.testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(dataProvider = "emailErrorLoginData" , groups = {"errorHandle" , "smoke" })
	public void emailErrorValidation(HashMap<String, String> input) {
		landingPage.loginApplication(input.get("email"), input.get("password"));
		String errorMessage = landingPage.getEmailErrorMessage();
		Assert.assertEquals(input.get("actualErrorMessage") ,errorMessage);
	}
	
	@Test(dataProvider = "passwordErrorLoginData" , groups = {"errorHandle"})
	public void blankPasswordErrorValidation(HashMap<String, String> input) {
		landingPage.loginApplication(input.get("email"), input.get("password"));
		String errorMessage = landingPage.getBlankPasswordErrorMessage();
		Assert.assertEquals(input.get("actualErrorMessage") ,errorMessage);
	}
	
	@Test(dataProvider = "errorLoginData" , groups = {"errorHandle"})
	public void loginbErrorValidation(HashMap<String, String> input) {
		landingPage.loginApplication(input.get("email"), input.get("password"));
		String errorMessage = landingPage.getLoginErrorMessage();
		Assert.assertEquals(input.get("actualErrorMessage") ,errorMessage);
	}
	
	@Test(dataProvider = "userData" , groups = {"errorHandle"})
	public void productErrorValidation(HashMap<String, String> input) throws IOException {
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean matchProduct = cartPage.verifyProductDisplay("Zara COAT 33");
		Assert.assertFalse(matchProduct);
	}
	
	@DataProvider(name="emailErrorLoginData")
	public Object[][] getDataEmail() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\ecommerce\\datas\\ErrorLoginData.json");
		return new Object[][]  {{data.get(0)}};
	}
	@DataProvider(name="passwordErrorLoginData")
	public Object[][] getDataPassword() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\ecommerce\\datas\\ErrorLoginData.json");
		return new Object[][]  {{data.get(1)}};
	}
	@DataProvider(name="errorLoginData")
	public Object[][] getDataLogin() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\ecommerce\\datas\\ErrorLoginData.json");
		return new Object[][]  {{data.get(2)}};
	}
	@DataProvider(name="userData")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\ecommerce\\datas\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)} , {data.get(1)}};
	}
}
