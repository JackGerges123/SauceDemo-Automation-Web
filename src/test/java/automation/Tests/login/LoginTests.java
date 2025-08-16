package automation.Tests.login;
import automation.pageObjects.InventoryPage;
import automation.pageObjects.LoginPage;
import drivers.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.FilesUtils;
import utils.JsonUtils;

import java.io.File;

public class LoginTests {
    private WebDriver d;
    JsonUtils testdata;
    File allure_results =new File("test-logs/allure-results");

    @BeforeSuite
    public void beforeSuite(){
        FilesUtils.deleteFiles(allure_results);
    }
    @BeforeMethod(alwaysRun = true)
    public void SetUp(@Optional("chrome") String browser){
        d = BrowserFactory.getDriver(browser);
        testdata = new JsonUtils("test-data");
    }
    @AfterMethod(alwaysRun = true)
    public void End(){
        d.quit();
    }
    @Test
    public void PostiveLoginTest(){

        LoginPage login = new LoginPage(d);
        login.visit();
        InventoryPage inventoryPage = login.executeLogin(testdata.getJsonData("valid-Login-credentials.username")
                                                        ,testdata.getJsonData("valid-Login-credentials.password"));
        Assert.assertEquals(inventoryPage.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.isProductsIconDisplayed());
    }

    @Parameters({"testKey"})
    @Test
    public void NegativeLoginTest(String testKey){
        LoginPage login = new LoginPage(d);
        login.visit();
        login.executeLogin(testdata.getJsonData(testKey + ".username")
                          ,testdata.getJsonData(testKey + ".password"));
        Assert.assertEquals(login.getErrorMessage(),testdata.getJsonData(testKey + ".error-message"));
    }


}

