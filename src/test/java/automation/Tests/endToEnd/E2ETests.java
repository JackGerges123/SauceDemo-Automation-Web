package automation.Tests.endToEnd;

import automation.pageObjects.*;
import drivers.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.FilesUtils;
import utils.JsonUtils;
import utils.LogsUtil;
import utils.ScreenshotsUtils;

import java.io.File;
import java.util.List;

public class E2ETests {
    private WebDriver driver;
    JsonUtils testdata;
    LoginPage login;
    File allure_results =new File("test-logs/allure-results");

    @BeforeSuite
    public void beforeSuite(){
        FilesUtils.deleteFiles(allure_results);
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser){
        driver = BrowserFactory.getDriver(browser);
        testdata = new JsonUtils("test-data");
        login = new LoginPage(driver);
        login.visit();

    }

    @AfterMethod(alwaysRun = true)
    public void End(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            LogsUtil.error("Test FAILED: " + result.getName());
            LogsUtil.error("Reason: " + result.getThrowable().getMessage());
            ScreenshotsUtils.takeScreenshot(driver, "Error Screenshot");
        }
        driver.quit();
    }


    @Test
    public void postiveEndToEndScenario_1(){
        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                                                        ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());
        String counter = inventoryPage.addProductsToCart();
        Assert.assertEquals(inventoryPage.getCartCounterNumber(),counter);
        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed(),"Remove button not displayed");

        CartPage cartPage = inventoryPage.clickOnCart();
        CheckoutPage checkoutPage = cartPage.ClickOnCheckoutButton();
        Assert.assertEquals(checkoutPage.getCurrentUrl() , "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.getPageSource().contains("Checkout: Your Information"));

        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.executeContinueCheckout
                 (testdata.getJsonData("valid-checkout-information.first-name")
                , testdata.getJsonData("valid-checkout-information.last-name")
                , testdata.getJsonData("valid-checkout-information.zip-code"));
        Assert.assertEquals(checkoutOverviewPage.getCurrentUrl() ,"https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkoutOverviewPage.getPageSource().contains("Checkout: Overview"));
        Assert.assertTrue(checkoutOverviewPage.getPageSource().contains("Payment Information:"));

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(checkoutCompletePage.getPageSource().contains("Thank you for your order!"));
        Assert.assertTrue(checkoutCompletePage.isSuccessfulllogoDisplayed());

        checkoutCompletePage.clickOnBackHomeButton();
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void postiveEndToEndScenario_2(){

        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                                                       ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());

        inventoryPage.clickOnProductImage();
        Assert.assertTrue(inventoryPage.isBackToProductsDisplayed(),"Back to product link not found");
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory-item.html?id=0");
        inventoryPage.clickOnBackToProduct();

        inventoryPage.clickOnProductName();
        Assert.assertTrue(inventoryPage.isBackToProductsDisplayed(),"Back to product link not found");
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory-item.html?id=4");
        inventoryPage.clickOnBackToProduct();

        inventoryPage.clickOnDropDown("az");
        List<String> actualNames = inventoryPage.getProductsNames();
        Assert.assertEquals(actualNames,inventoryPage.sortProductsNames(actualNames), "Product names are not sorted A-Z");

        inventoryPage.clickOnDropDown("za");
        Assert.assertEquals(actualNames,inventoryPage.sortReverseProductsNames(actualNames), "Product names are not sorted A-Z");

        inventoryPage.clickOnDropDown("lohi");
        List<Double> actualPrices = inventoryPage.getProductPrices();
        Assert.assertEquals(actualPrices, inventoryPage.sortProductsPrices(actualPrices), "Prices are not sorted from Low to High");

        inventoryPage.clickOnDropDown("hilo");
        Assert.assertEquals(actualPrices, inventoryPage.sortReverseProductsPrices(actualPrices), "Prices are not sorted from High to Low");
    }

    @Test
    public void postiveEndToEndScenario_3(){
        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                                                        ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());
        String counter = inventoryPage.addProductsToCart();
        inventoryPage.clickOnLogOutButton();
        login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                          ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCartCounterNumber(),counter);
        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed(),"Remove button not displayed");
    }

    @Test
    public void postiveEndToEndScenario_4(){
        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                                                        ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());
        inventoryPage.addProductsToCart();
        CartPage cartPage = inventoryPage.clickOnCart();
        cartPage.removeProductsFromCart();
        cartPage.clickOnAllItemsButton();
        Assert.assertEquals(cartPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertFalse(inventoryPage.isCounterDisplayed());
    }

    @Test
    public void postiveEndToEndScenario_5() {
        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());
        inventoryPage.addProductsToCart();
        inventoryPage.clickOnResetAppStateButton();
        Assert.assertFalse(inventoryPage.isCounterDisplayed());
        Assert.assertFalse(inventoryPage.isRemoveButtonDisplayed(),"Remove button is still displayed after click on reset app state button");
    }

    @Test
    @Parameters({"testKeys"})
    public void negativeEndToEndScenario_1(String testKeys){
        String[] keysArray = testKeys.split(",");
        for (String testKey : keysArray) {
            login.visit();
            login.executeLogin(
                    testdata.getJsonData(testKey.trim() + ".username"),
                    testdata.getJsonData(testKey.trim() + ".password")
            );
            Assert.assertEquals(
                    login.getErrorMessage(),
                    testdata.getJsonData(testKey.trim() + ".error-message"),
                    "Test failed for key: " + testKey
            );
        }
    }

    @Test
    public void negativeEndToEndScenario_2(){
        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                ,testdata.getJsonData("valid-Login-credentials.password"));
        inventoryPage.addProductsToCart();
        CartPage cartPage = inventoryPage.clickOnCart();
        cartPage.removeProductsFromCart();
        cartPage.ClickOnCheckoutButton();
        Assert.assertEquals(cartPage.getCurrentUrl(),"https://www.saucedemo.com/cart.html","Cart empty cannot navigate to checkout page");
    }
}
