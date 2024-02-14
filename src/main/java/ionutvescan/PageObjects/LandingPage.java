package ionutvescan.PageObjects;

import ionutvescan.ReusableComponents.ReusableComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends ReusableComponents {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
    private WebElement acceptCookies;
    @FindBy(linkText = "Parfumuri")
    private WebElement parfumuri;
    @FindBy(xpath = "//strong[normalize-space()='Parfumuri barbati']")
    private WebElement parfumuriBarbati;

    public void goTo(){
        driver.get("https://www.elefant.ro/");
    }

    public void acceptCookies(){
        acceptCookies.click();
    }

    public ProductPage searchItems(){
        windowScroll(0,200);
        Actions actions = new Actions(driver);
        actions.moveToElement(parfumuri).build().perform();
        parfumuriBarbati.click();
        ProductPage productPage = new ProductPage(driver);
        return productPage;
    }
}
