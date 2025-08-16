package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage{

    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By zipCodeLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");
    private final By errorMessageLocator = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]");
    private final By cancelButtonLocator = By.id("cancel");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    @Step("Enter first name : {0}")
    public void enterFirstname(String firstName) {
        driver.findElement(firstNameLocator).sendKeys(firstName);
    }

    @Step("Enter last name : {0}")
    public void enterLastname(String lastName) {
        driver.findElement(lastNameLocator).sendKeys(lastName);
    }

    @Step("Enter zip/postal code : {0}")
    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeLocator).sendKeys(zipCode);
    }

    @Step("Click on continue button")
    public void clickContinueButton() {
        driver.findElement(continueButtonLocator).click();
    }


    public CheckoutOverviewPage executeContinueCheckout(String firstName, String lastName , String zipCode) {
       enterFirstname(firstName);
       enterLastname(lastName);
       enterZipCode(zipCode);
       clickContinueButton();
       return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = waitForElement(errorMessageLocator);
        return errorMessageElement.getText();
    }

    @Step("Click on cancel button")
    public void clickOnCancelButton(){
        super.clickWhenClickable(cancelButtonLocator);
    }


}
