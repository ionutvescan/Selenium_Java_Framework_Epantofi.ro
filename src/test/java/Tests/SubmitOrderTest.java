package Tests;

import Tests.TestComponents.BaseTest;
import Tests.TestComponents.Retry;
import ionutvescan.CartPage;
import ionutvescan.CheckoutPage;
import ionutvescan.LandingPage;
import ionutvescan.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest{
    @Test(dataProvider = "getData", retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
        LandingPage landingPage = launchApplication();
        landingPage.acceptCookies();
        ProductPage productPage = landingPage.searchItems();
        productPage.add1ProductToCart(input.get("productName"));
        CartPage cartPage = productPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectDeliveryAndBillingInfo(input.get("phone"));
        String message = checkoutPage.getCardMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Vă rugăm să introduceți datele cardului de credit în pagina următoare."));
        checkoutPage.placeOrder();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonData("/Users/Ionut/IdeaProjects/CommerceWebApp/src/test/java/Tests/Data/AddOneProductData.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}
