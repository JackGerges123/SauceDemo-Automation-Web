package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By usernameInputLocator = By.id("user-name");
    private final By passwordInputLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessageLocator = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @Step("Navigate to login page ")
    public void visit(){
        super.visitUrl("https://www.saucedemo.com/");
    }

    @Step("Add username: {0}")
    public void enterUsername(String username) {
        driver.findElement(usernameInputLocator).sendKeys(username);
    }
    @Step("Add password: {0}")
    public void enterPassword(String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    @Step("Click on login button")
    public void clickSubmitButton() {
        driver.findElement(loginButtonLocator).click();
    }


    public InventoryPage executeLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmitButton();
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = waitForElement(errorMessageLocator);
        return errorMessageElement.getText();
    }
}
