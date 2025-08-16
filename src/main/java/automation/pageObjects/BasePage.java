package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final By cartCounterLocator = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    private final By productsPricesLocator = By.xpath("//div[@class='inventory_item_price']");
    private final By menuButtonLocator = By.xpath("//*[@id=\"react-burger-menu-btn\"]");
    private final By allItemsButtonLocator = By.xpath("//*[@id=\"inventory_sidebar_link\"]");
    private final By aboutButtonLocator = By.xpath("//*[@id=\"about_sidebar_link\"]");
    private final By logOutButtonLocator = By.xpath("//*[@id=\"logout_sidebar_link\"]");
    private final By resetButtonLocator = By.xpath("//*[@id=\"reset_sidebar_link\"]");
    private final By removeFromCartButtonLocators = By.xpath("//button[text()='Remove']");
    List<WebElement> prices;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    protected void visitUrl(String url){
        driver.get(url);
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    protected WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isExist(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isIconDisplayed(By locator) {
        try {
            waitForElement(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickWhenClickable(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public String getCartCounterNumber(){
        return waitForElement(cartCounterLocator).getText();
    }

    public List<Double> getPrices() {
        prices = driver.findElements(productsPricesLocator);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : this.prices) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public Double extractOnlyPrice(By locator){
        String text = driver.findElement(locator).getText();
        String priceText = text.replaceAll("[^\\d.]", "");
        return Double.parseDouble(priceText);
    }

    @Step("Click on menu button then click on all items button")
    public void clickOnAllItemsButton(){
        clickWhenClickable(menuButtonLocator);
        clickWhenClickable(allItemsButtonLocator);
    }

    @Step("Click on menu button then click on about")
    public void clickOnAboutButton(){
        clickWhenClickable(menuButtonLocator);
        clickWhenClickable(aboutButtonLocator);
    }

    @Step("Click on menu button then click logout")
    public void clickOnLogOutButton(){
        clickWhenClickable(menuButtonLocator);
        clickWhenClickable(logOutButtonLocator);
    }
    @Step("Click on menu button then click on reset app state")
    public void clickOnResetAppStateButton(){
        clickWhenClickable(menuButtonLocator);
        clickWhenClickable(resetButtonLocator);
    }

    @Step("Remove all products from cart page")
    public void removeProductsFromCart() {
        List<WebElement> addToCartButtons = driver.findElements(removeFromCartButtonLocators);
        for (WebElement btns : addToCartButtons){
            btns.click();
        }
    }
}


