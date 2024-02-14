package ionutvescan.PageObjects;

import ionutvescan.ReusableComponents.ReusableComponents;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class CheckoutPage extends ReusableComponents {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "email_Email")
    private WebElement email;
    @FindBy(id = "shipping_LastName")
    private WebElement lastName;
    @FindBy(id = "shipping_FirstName")
    private WebElement firstName;
    @FindBy(id = "shipping_PhoneHome")
    private WebElement phoneNumber;
    @FindBy(id = "shipping_State")
    private WebElement county;
    @FindBy(name = "shipping_City")
    private WebElement city;
    @FindBy(id = "shipping_Address1")
    private WebElement address;
    @FindBy(css = "div[id='continue-checkout'] button")
    private WebElement continueCheckout;
    @FindBy(css = "div[class='col-xs-8'] h1")
    private WebElement message;
    @FindBy(css = "label[for='terms-conditions-agree']")
    private WebElement acceptTerms;
    @FindBy(id = "finalSubmitOrder")
    private WebElement submitOrder;

    public void selectDeliveryAndBillingInfo(String number) throws InterruptedException {
        windowScroll(0,200);
        email.sendKeys("ionut.ionut@gmail.com");
        lastName.sendKeys("Ionut");
        firstName.sendKeys("Ionut");
        phoneNumber.sendKeys(number);
        Select dropdownCounty = new Select(county);
        dropdownCounty.selectByVisibleText("Cluj");
        Thread.sleep(3000);
        Select dropdownCity = new Select(city);
        dropdownCity.selectByValue("Cluj-Napoca");
        address.sendKeys("Plevnei, 19");
        windowScroll(0,200);
        continueCheckout.click();
    }

    public String getCardMessage(){
       return message.getText();
    }

    public void placeOrder() throws IOException{
        windowScroll(0,200);
        acceptTerms.click();
        Actions action = new Actions(driver);
        action.moveToElement(submitOrder).build().perform();
        File file = submitOrder.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("SubmitOrder.png"));
    }
}
