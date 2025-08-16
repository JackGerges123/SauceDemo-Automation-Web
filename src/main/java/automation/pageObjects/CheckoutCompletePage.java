package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By successfulLogoLocator = By.xpath("//*[@id=\"checkout_complete_container\"]/img");
    private final By backHomeButtonLocator = By.id("back-to-products");
    public CheckoutCompletePage(WebDriver driver){
         super(driver);
    }

    public boolean isSuccessfulllogoDisplayed(){
        return super.isExist(successfulLogoLocator);
    }

    @Step("Click on back to home button")
    public void clickOnBackHomeButton(){
        super.clickWhenClickable(backHomeButtonLocator);
    }
}
