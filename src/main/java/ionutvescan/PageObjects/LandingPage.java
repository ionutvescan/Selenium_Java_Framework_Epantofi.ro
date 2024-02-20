package ionutvescan.PageObjects;

import ionutvescan.ReusableComponents.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LandingPage extends BasePage {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    @FindBy(id="CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
    private WebElement acceptCookies;
    @FindBy(linkText = "Parfumuri")
    private WebElement parfumuri;
    @FindBy(xpath = "//strong[normalize-space()='Parfumuri barbati']")
    private WebElement parfumuriBarbati;
    @FindBy(xpath = "//li[contains(@id, 'mobileCat')] / a")
    private List<WebElement> categories;

//    @FindBy(css = ".main-navigation-level1-item a strong")
//    private List<WebElement> subcategories;

    @FindBy(xpath = "//ul[@id = 'mobileCat-Carte'] / li / ul / li / a")
    private List<WebElement> bookCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-CarteStraina'] / li / ul / li / a")
    private List<WebElement> foriegnBookCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-JucariiCopiiBebe'] / li / ul / li / a")
    private List<WebElement> toysCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-Parfumuri'] / li / ul / li / a")
    private List<WebElement> parfumesCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-Cosmetice'] / li / ul / li / a")
    private List<WebElement> beautyCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-Ceasuri'] / li / ul / li / a")
    private List<WebElement> watchesCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-Electro'] / li / ul / li / a")
    private List<WebElement> electronicsCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-CasaSiGradina'] / li / ul / li / a")
    private List<WebElement> homeCategorylinks;
    @FindBy(xpath = "//ul[@id = 'mobileCat-Nutritie'] / li / ul / li / a")
    private List<WebElement> nutritionCategorylinks;

    private final By cookies = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");

    public void goTo(){
        driver.get("https://www.elefant.ro/");
    }

    public void acceptCookies() {
        waitForElementToAppear(cookies);
        acceptCookies.click();
    }

    public ProductPage searchManPerfumes(){
        Actions actions = new Actions(driver);
        actions.moveToElement(parfumuri).build().perform();
        parfumuriBarbati.click();
        return new ProductPage(driver);
    }

    public void selectCategory(String categoryName) {
        Actions actions = new Actions(driver);
        for(WebElement category : categories){
            if(category.getText().equalsIgnoreCase(categoryName)){
                actions.moveToElement(category).build().perform();
            }
        }
    }

    public void verifyCategoryLinks(String categoryName) throws IOException {
        switch (categoryName){
            case "Carte" :
                verifyBrokenLinks(bookCategorylinks);
                break;
            case "Carte straina" :
                verifyBrokenLinks(foriegnBookCategorylinks);
                break;
            case "Jucarii, Copii & Bebe" :
                verifyBrokenLinks(toysCategorylinks);
                break;
            case "Parfumuri" :
                verifyBrokenLinks(parfumesCategorylinks);
                break;
            case "Cosmetice & Ingrijire personala" :
                verifyBrokenLinks(beautyCategorylinks);
                break;
            case "Ceasuri & Accesorii" :
                verifyBrokenLinks(watchesCategorylinks);
                break;
            case "Electro IT" :
                verifyBrokenLinks(electronicsCategorylinks);
                break;
            case "Casa & Gradina" :
                verifyBrokenLinks(homeCategorylinks);
                break;
            case "Nutritie" :
                verifyBrokenLinks(nutritionCategorylinks);
                break;
        }
    }

    private void verifyBrokenLinks(List<WebElement> list) throws IOException {
        SoftAssert softAssert = new SoftAssert();
        for(WebElement link : list){
            String url = link.getAttribute("href");
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            softAssert.assertTrue(responseCode < 404,
                    "Link " + link.getText() + " has response code " + responseCode + " : ");
        }
        softAssert.assertAll();
    }
}
