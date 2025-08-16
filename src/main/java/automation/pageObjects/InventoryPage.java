package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventoryPage extends BasePage{

    private final By addToCartButtonLocators = By.xpath("//button[text()='Add to cart']");
    private final By productsIconLocator = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    private final By sauceLabsBackpackLocator = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    private final By sauceBikeLightImageLocator = By.xpath("//*[@id=\"item_0_img_link\"]/img");
    private final By backToProductsLocator = By.id("back-to-products");
    private final By dropdownElementClassName = By.className("product_sort_container");
    private final By productsNameCssSelector = By.cssSelector(".inventory_item_name");
    private final By cartlocator = By.xpath("//*[@id=\"shopping_cart_container\"]/a");


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add all products to cart")
    public String addProductsToCart() {
        List<WebElement> addToCartButtons = driver.findElements(addToCartButtonLocators);
        int counter = 0;
        for (WebElement btns : addToCartButtons){
            btns.click();
            counter++ ;
        }
        return String.valueOf(counter);
    }

    public boolean isRemoveButtonDisplayed() {
        List<WebElement> addToCartButtons = driver.findElements(addToCartButtonLocators);
        for (WebElement btns : addToCartButtons) {
            if (!btns.getText().equals("Remove")) {
                return false;
            }
        }
        return true;
    }


    public boolean isProductsIconDisplayed() {
      return super.isIconDisplayed(productsIconLocator);
    }

    @Step("Click on product name")
    public void clickOnProductName(){
        clickWhenClickable(sauceLabsBackpackLocator);
    }
    @Step("Click on product image")
    public void clickOnProductImage(){
        clickWhenClickable(sauceBikeLightImageLocator);
    }

    public boolean isBackToProductsDisplayed() {
        return super.isIconDisplayed(backToProductsLocator);
    }

    @Step("Click on back to product button")
    public void clickOnBackToProduct(){
        clickWhenClickable(backToProductsLocator);
    }

    @Step("Select filter ({0}) to order product")
    public void clickOnDropDown(String order){
        WebElement dropdownElement = driver.findElement(dropdownElementClassName);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(order);
    }

    public List<String> getProductsNames(){
        List<WebElement> names = driver.findElements(productsNameCssSelector);
        List<String> productNames = new ArrayList<>();

        for (WebElement element : names) {
            productNames.add(element.getText());
        }
        return productNames;
    }

    public List<String> sortProductsNames(List<String> expectedNames){
        Collections.sort(expectedNames);
        return expectedNames;
    }

    public List<String> sortReverseProductsNames(List<String> expectedNames){
        expectedNames.sort(Comparator.reverseOrder());
        return expectedNames;
    }



    public List<Double> sortProductsPrices(List<Double> expectedPrices){
        Collections.sort(expectedPrices);
        return expectedPrices;
    }

    public List<Double> sortReverseProductsPrices(List<Double> expectedPrices){
        expectedPrices.sort(Comparator.reverseOrder());
        return expectedPrices;
    }

    @Step("Click on cart")
    public CartPage clickOnCart(){
        super.clickWhenClickable(cartlocator);
        return new CartPage(driver);
    }

    public List<Double> getProductPrices(){
        List<Double> prices =super.getPrices();
        return prices;
    }

    public boolean isCounterDisplayed(){
        return super.isIconDisplayed(cartCounterLocator);
    }

}
