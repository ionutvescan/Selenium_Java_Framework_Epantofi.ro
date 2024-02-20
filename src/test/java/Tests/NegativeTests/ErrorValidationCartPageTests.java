package Tests.NegativeTests;

import Tests.TestComponents.BaseTest;
import Tests.TestComponents.Retry;
import ionutvescan.PageObjects.CartPage;
import ionutvescan.PageObjects.CheckoutPage;
import ionutvescan.PageObjects.LandingPage;
import ionutvescan.PageObjects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationCartPageTests extends BaseTest {
    @Test(dataProvider = "getData", retryAnalyzer = Retry.class)
    public void addProductToCartError(HashMap<String, String> input) throws IOException {
        LandingPage landingPage = launchApplication();
        landingPage.acceptCookies();
        ProductPage productPage = landingPage.searchManPerfumes();
        productPage.add1ProductToCart(input.get("productName"));
        CartPage cartPage = productPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(
                "/Users/Ionut/IdeaProjects/CommerceWebApp/src/test/java/Tests/Data/AddOneProductErrorData.json");
        return new Object[][]{{data.get(0)}};
    }
}
