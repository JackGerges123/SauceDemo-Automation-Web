package automation.Tests.checkout;

import drivers.BrowserFactory;
import automation.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.JsonUtils;

public class CheckoutTests {
    private WebDriver d;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private String counter;
    JsonUtils testdata;
    private CheckoutPage checkoutPage;


    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser){
        d = BrowserFactory.getDriver(browser);

        LoginPage login = new LoginPage(d);
        login.visit();
        testdata = new JsonUtils("test-data");
        inventoryPage = login.executeLogin("standard_user", "secret_sauce");
        counter = inventoryPage.addProductsToCart();
        cartPage = inventoryPage.clickOnCart();
        checkoutPage = cartPage.ClickOnCheckoutButton();
    }

    @AfterMethod(alwaysRun = true)
    public void End(){
        d.quit();
    }

    @Test
    public void PostiveContinueCheckoutTest(){
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.executeContinueCheckout(
                testdata.getJsonData("valid-checkout-information.first-name")
                ,testdata.getJsonData("valid-checkout-information.last-name")
                ,testdata.getJsonData("valid-checkout-information.zip-code"));
        Assert.assertEquals(checkoutOverviewPage.getCurrentUrl() ,"https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkoutOverviewPage.getPageSource().contains("Checkout: Overview"));
        Assert.assertTrue(checkoutOverviewPage.getPageSource().contains("Payment Information:"));
    }

    @Parameters("testkey")
    @Test
    public void InvalidContinueCheckoutTest(String testkey){
        checkoutPage.executeContinueCheckout(testdata.getJsonData(testkey + ".first-name")
                                            ,testdata.getJsonData(testkey + ".last-name")
                                            ,testdata.getJsonData(testkey + ".zip-code"));
        Assert.assertEquals(checkoutPage.getErrorMessage(), testdata.getJsonData(testkey + ".error-message"));
    }

    @Test
    public void ClickOnCancelButtonTest(){
        checkoutPage.clickOnCancelButton();
        Assert.assertEquals(cartPage.getCurrentUrl() , "https://www.saucedemo.com/cart.html");
    }
}
