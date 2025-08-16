package automation.Tests.checkoutOverview;

import drivers.BrowserFactory;
import automation.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class CheckoutOverviewTests {
    private WebDriver d;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private String counter;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;


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
    }

    @AfterMethod(alwaysRun = true)
    public void End(){
        d.quit();
    }

    @Test
    public void ItemTotalPriceSummationTest(){
        Assert.assertEquals(checkoutOverviewPage.getSumOfProductsPrices() ,checkoutOverviewPage.getItemTotalPrice());
    }

    @Test
    public void SummationBetweenItemTotalAndTaxTest(){
        Double totalPrice = checkoutOverviewPage.getItemTotalPrice() + checkoutOverviewPage.getTaxPrice();
        Assert.assertEquals(totalPrice,checkoutOverviewPage.getTotalPrice());
    }

    @Test
    public void ClickOnFinishButtonTest(){
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(checkoutCompletePage.getPageSource().contains("Thank you for your order!"));
        Assert.assertTrue(checkoutCompletePage.isSuccessfulllogoDisplayed());
    }

    @Test
    public void ClickOnCancelButtonTest(){
        checkoutOverviewPage.clickOnCancelButton();
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
}
