package StepDefinitions;

import Tests.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ionutvescan.PageObjects.CartPage;
import ionutvescan.PageObjects.LandingPage;
import ionutvescan.PageObjects.ProductPage;
import org.testng.Assert;

import java.io.IOException;

public class AddProductStepDefinition extends BaseTest {
    public LandingPage landingPage;
    @Given("I landed on EcommercePage")
    public void I_landed_on_EcommercePage() throws IOException {
        landingPage = launchApplication();
        landingPage.acceptCookies();
    }
    @When("Go to the productPage")
    public void Go_to_the_productPage()
    {
        landingPage.searchManPerfumes();
    }
    @Then("^Add the (.+) to cart$")
    public void add_the_product_to_cart(String productName){
        ProductPage productPage = new ProductPage(driver);
        productPage.add1ProductToCart(productName);
    }
    @Then ("^Go to cart page and verify the (.+)$")
    public void go_to_cart_page_and_verify_the_product(String productName){
        ProductPage productPage =new ProductPage(driver);
        CartPage cartPage = productPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        driver.close();
    }

    @Then("^Add (.+) and then add (.+)$")
    public void add_the_products_to_cart(String firstProductName, String secondProductName){
        ProductPage productPage = new ProductPage(driver);
        productPage.add2ProductsToCart(firstProductName, secondProductName);
    }
    @And("Verify the number of products displayed")
    public void verify_the_number_of_products_displayed(){
        ProductPage productPage = new ProductPage(driver);
        String productsMatch = productPage.verifyNumbersOfProductsDisplayed();
        Assert.assertTrue(productsMatch.equalsIgnoreCase("(2 produse)"));
    }
    @Then ("^Go to cartPage and verify the display of the (.+) and the (.+)$")
    public void go_to_cartPage_and_verify_the_display_of_the_two_products(String firstProductName, String secondProductName){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage.goToCartPage();
        Boolean productMatch = cartPage.verifyProducts(firstProductName, secondProductName);
        Assert.assertTrue(productMatch);
        driver.close();
    }
}
