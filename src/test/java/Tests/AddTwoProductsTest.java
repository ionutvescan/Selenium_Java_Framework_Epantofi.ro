package Tests;

import Tests.TestComponents.BaseTest;
import Tests.TestComponents.Retry;
import ionutvescan.CartPage;
import ionutvescan.LandingPage;
import ionutvescan.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddTwoProductsTest extends BaseTest{
    @Test(dataProvider = "getData", retryAnalyzer = Retry.class)
    public void addProductsToCart(HashMap<String,String> input) throws IOException{
        LandingPage landingPage = launchApplication();
        landingPage.acceptCookies();
        ProductPage productPage = landingPage.searchItems();
        productPage.add2ProductsToCart(input.get("firstProductName"), input.get("secondProductName"));
        String productsMatch = productPage.verifyNumbersOfProductsDisplayed();
        Assert.assertTrue(productsMatch.equalsIgnoreCase("(2 produse)"));
        CartPage cartPage = productPage.goToCartPage();
        Boolean productMatch = cartPage.verifyProducts(input.get("firstProductName"), input.get("secondProductName"));
        Assert.assertTrue(productMatch);

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonData("/Users/Ionut/IdeaProjects/CommerceWebApp/src/test/java/Tests/Data/AddTwoProductsData.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}
