package ionutvescan.Reusables;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableComponents {
    WebDriver driver;
    public ReusableComponents(WebDriver driver){
        this.driver=driver;
    }

    public void waitForElementToAppear(By findBy){
        WebDriverWait explicitWAit = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWAit.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void windowScroll(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }
}
