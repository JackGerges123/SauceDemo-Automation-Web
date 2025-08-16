package automation.Tests.inventory;

import automation.pageObjects.CartPage;
import automation.pageObjects.InventoryPage;
import automation.pageObjects.LoginPage;
import drivers.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTests {
    private WebDriver d;
    private InventoryPage inventoryPage;
    LoginPage login;


    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser){
        d = BrowserFactory.getDriver(browser);

        login = new LoginPage(d);
        login.visit();
        inventoryPage = login.executeLogin("standard_user", "secret_sauce");

    }

    @AfterMethod(alwaysRun = true)
    public void End(){
        d.quit();
    }

    @Test
    public void AddProductsToCart(){
        String counter = inventoryPage.addProductsToCart();
        Assert.assertEquals(inventoryPage.getCartCounterNumber(),counter);
        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed(),"Remove button not displayed");
    }

    @Test
    public void ClickOnImageOfProducts(){
        inventoryPage.clickOnProductImage();
        Assert.assertTrue(inventoryPage.isBackToProductsDisplayed(),"Back to product link not found");
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory-item.html?id=0");
        inventoryPage.clickOnBackToProduct();
    }

    @Test
    public void ClickOnNameOfProducts(){
        inventoryPage.clickOnProductName();
        Assert.assertTrue(inventoryPage.isBackToProductsDisplayed(),"Back to product link not found");
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory-item.html?id=4");
        inventoryPage.clickOnBackToProduct();
    }

    @Test
    public void SortProductsNameFromA_Z(){
        inventoryPage.clickOnDropDown("az");
        List<String> actualNames = inventoryPage.getProductsNames();
        Assert.assertEquals(actualNames,inventoryPage.sortProductsNames(actualNames), "Product names are not sorted A-Z");
    }

    @Test
    public void SortProductsNameFromZ_A(){
        inventoryPage.clickOnDropDown("za");
        List<String> actualNames = inventoryPage.getProductsNames();
        Assert.assertEquals(actualNames,inventoryPage.sortReverseProductsNames(actualNames), "Product names are not sorted A-Z");
    }

    @Test
    public void SortPricesFromLowToHigh() {
        inventoryPage.clickOnDropDown("lohi");
        List<Double> actualPrices = inventoryPage.getProductPrices();
        Assert.assertEquals(actualPrices, inventoryPage.sortProductsPrices(actualPrices), "Prices are not sorted from Low to High");
    }

    @Test
    public void SortPricesFromHighToLow() {
        inventoryPage.clickOnDropDown("hilo");
        List<Double> actualPrices = inventoryPage.getProductPrices();
        Assert.assertEquals(actualPrices, inventoryPage.sortReverseProductsPrices(actualPrices), "Prices are not sorted from High to Low");
    }

    @Test
    public void ValidClickOnCart(){
        CartPage cartPage = inventoryPage.clickOnCart();
        Assert.assertEquals(cartPage.getCurrentUrl(),"https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.getPageSource().contains("Your Cart"));
    }

    @Test
    public void LogOutButtonTest(){
        String counter = inventoryPage.addProductsToCart();
        inventoryPage.clickOnLogOutButton();
        Assert.assertEquals(login.getCurrentUrl(),"https://www.saucedemo.com/");
        login.executeLogin("standard_user","secret_sauce");
        Assert.assertEquals(inventoryPage.getCartCounterNumber(),counter);
        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed(),"Remove button not displayed");
    }

    @Test
    public void AboutButtonTest(){
        inventoryPage.clickOnAboutButton();
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://saucelabs.com/");
    }

    @Test
    public void ResetButtonTest(){
       inventoryPage.addProductsToCart();
       inventoryPage.clickOnResetAppStateButton();
       Assert.assertFalse(inventoryPage.isCounterDisplayed());
    }
}
