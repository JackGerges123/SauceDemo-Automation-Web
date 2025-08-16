package automation.Tests.Cart;

import drivers.BrowserFactory;
import automation.pageObjects.CartPage;
import automation.pageObjects.CheckoutPage;
import automation.pageObjects.InventoryPage;
import automation.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import utils.LogsUtil;

public class CartTests {
    private WebDriver d;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private String counter;


    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser){
        d = BrowserFactory.getDriver(browser);
        LogsUtil.info("Driver created on :" ,browser);
        LoginPage login = new LoginPage(d);
        login.visit();
        inventoryPage = login.executeLogin("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());
        counter = inventoryPage.addProductsToCart();
        cartPage = inventoryPage.clickOnCart();
    }

    @AfterMethod(alwaysRun = true)
    public void End(){
        d.quit();
    }

    @Test
    public void ValidClickOnCheckOut(){
        CheckoutPage checkoutPage = cartPage.ClickOnCheckoutButton();
        Assert.assertEquals(checkoutPage.getCurrentUrl() , "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.getPageSource().contains("Checkout: Your Information"));
    }

    @Test
    public void ValidClickOnContinueShopping(){
        InventoryPage inventoryPage = cartPage.ClickOnContinueShoppingButton();
        Assert.assertEquals(inventoryPage.getCurrentUrl() , "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.getPageSource().contains("Products"));
    }

    @Test
    public void RemoveItemsFromCart(){
        counter = cartPage.ClickOnRemoveButtonItem1(counter);
        counter = cartPage.ClickOnRemoveButtonItem2(counter);
        Assert.assertEquals(cartPage.getCartCounterNumber(),String.valueOf(counter));
    }

    @Test
    public void AllItemsButtonTest(){
        cartPage.clickOnAllItemsButton();
        Assert.assertEquals(cartPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
}
