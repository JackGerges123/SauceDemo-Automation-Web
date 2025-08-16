package automation.Tests.checkoutComlete;

import drivers.BrowserFactory;
import automation.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class CheckoutCompleteTests {
    private WebDriver d;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private String counter;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;


    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser){
        d = BrowserFactory.getDriver(browser);

        LoginPage login = new LoginPage(d);
        login.visit();
        inventoryPage = login.executeLogin("standard_user", "secret_sauce");
        counter = inventoryPage.addProductsToCart();
        cartPage = inventoryPage.clickOnCart();
        checkoutPage = cartPage.ClickOnCheckoutButton();
        checkoutOverviewPage = checkoutPage.executeContinueCheckout("Jack","Gerges","12345");
        checkoutCompletePage = checkoutOverviewPage.clickOnFinishButton();
    }

    @AfterMethod(alwaysRun = true)
    public void End(){
        d.quit();
    }

    @Test
    public void ClickOnBackHomeButtonTest(){
        checkoutCompletePage.clickOnBackHomeButton();
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
}
