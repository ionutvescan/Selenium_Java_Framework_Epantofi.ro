package ionutvescan.PageObjects;

import ionutvescan.ReusableComponents.ReusableComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends ReusableComponents {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class='product-desc']")
    private WebElement pickedProduct;
    @FindBy(css = "a[class='product-desc']")
    private List<WebElement> pickedProductsList;
    @FindBy(css = "button[name='checkout']")
    private WebElement checkout;
    @FindBy(xpath = "//a[@class='btn btn-primary btn-block']")
    private WebElement continueWithoutAccount;

    By pickedProducts = By.cssSelector("a[class='product-desc']");

    public Boolean verifyProductDisplay(String productName) {
        Boolean match = pickedProduct.getText().equalsIgnoreCase(productName);
        return match;
    }

    private List<WebElement> getProductList(){
        windowScroll(0,200);
        waitForElementToAppear(pickedProducts);
        return pickedProductsList;
    }
    private WebElement getProductByName(String productName){
        return getProductList().stream().filter(p->
                p.getText().equals(productName)).findFirst().orElse(null);
    }
    public Boolean verifyProducts(String firstProduct, String secondProduct){
        WebElement product1 = getProductByName(firstProduct);
        WebElement product2 = getProductByName(secondProduct);
        if (product1.getText().equalsIgnoreCase(firstProduct) && product2.getText().equalsIgnoreCase(secondProduct)){
            return true;
        }
        return false;
    }

    public CheckoutPage goToCheckout(){
        checkout.click();
        continueWithoutAccount.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }
}

