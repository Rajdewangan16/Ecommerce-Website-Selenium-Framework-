package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.pageObjects.ProductCatalog;
import ecommerce.testComponents.BaseTest;

public class ProductTest extends BaseTest {

	@Test(dataProvider= "userData" , groups = {"smoke"})
	public void filterByProductName(HashMap<String, String> input) throws InterruptedException {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalog.searchProductName(input.get("productName"));
		Boolean match = productCatalog.verifySearchProduct();
		Assert.assertTrue(match);
	}
	
	@DataProvider(name="userData")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
				"\\src\\test\\java\\ecommerce\\datas\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)} , {data.get(1)}};
	}
}
