package ionutvescan.PageObjects;

import ionutvescan.ReusableComponents.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {
    WebDriver driver;
    public ProductPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    @FindBy(css = "div[class='product-detail']")
    private List<WebElement> productsDetail;
    @FindBy(css = "button[class*='js-add-to-cart']")
    private WebElement addToCart;
    @FindBy (css = "a[class*='btn-lg']")
    private WebElement goToCart;
    @FindBy(xpath = "//span[@class='products pull-right']")
    private WebElement numberOfProducts;

//    @FindBy(css = ".product-list.row.js-productlist .current-price")
//    private List<WebElement> currentPrices;
//    @FindBy(css = "a[data-testing-id = 'filter-link-[100.0 TO 200.0]'] span")
//    private WebElement checkbox100_200Price;

    private final By products = By.cssSelector("div[class='product-detail']");
    private final By addProduct = By.cssSelector("a[class='product-title']");
    private final By cartBtn = By.cssSelector("a[class*='btn-lg']");

    private List<WebElement> getProductList(){
        windowScroll(0,200);
        waitForElementToAppear(products);
        return productsDetail;
    }
    private WebElement getProductByName(String productName){
        return getProductList().stream().filter(p->
                p.findElement(By.cssSelector("h2")).getText().equals(productName)).findFirst().orElse(null);
    }
    public void add1ProductToCart(String productName){
        WebElement product = getProductByName(productName);
        product.findElement(addProduct).click();
        addToCart.click();
        waitForElementToAppear(cartBtn);
    }
    public void add2ProductsToCart(String firstProductName, String secondProductName){
        WebElement firstProduct = getProductByName(firstProductName);
        firstProduct.findElement(addProduct).click();
        addToCart.click();
        driver.navigate().back();
        waitForElementToAppear(products);

        WebElement secondProduct = getProductByName(secondProductName);
        secondProduct.findElement(addProduct).click();
        addToCart.click();
        waitForElementToAppear(cartBtn);
    }
    public String verifyNumbersOfProductsDisplayed(){
        return numberOfProducts.getText();
    }

    public CartPage goToCartPage(){
        goToCart.click();
        return new CartPage(driver);
    }
}
