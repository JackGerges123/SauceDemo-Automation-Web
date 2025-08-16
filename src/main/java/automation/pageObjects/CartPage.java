package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    private final By checkoutButtonLocator = By.id("checkout");
    private final By continueShoppingButtonLocator = By.id("continue-shopping");
    private final By removeButtonBackpack= By.id("remove-sauce-labs-backpack");
    private final By removeButtonOnesie= By.id("remove-sauce-labs-onesie");


    public CartPage (WebDriver driver){
        super(driver);
    }

    @Step("Click on checkout button")
    public CheckoutPage ClickOnCheckoutButton(){
        super.clickWhenClickable(checkoutButtonLocator);
        return new CheckoutPage(driver);
    }

    @Step("Click on continue shopping button")
    public InventoryPage ClickOnContinueShoppingButton(){
        super.clickWhenClickable(continueShoppingButtonLocator);
        return new InventoryPage(driver);
    }

    @Step("Remove one item from cart")
    public String ClickOnRemoveButtonItem1(String counter){
        super.clickWhenClickable(removeButtonBackpack);
        int count = Integer.parseInt(counter);
        count--;
        return String.valueOf(count);
    }

    @Step("Remove another item from cart")
    public String ClickOnRemoveButtonItem2(String counter){
        super.clickWhenClickable(removeButtonOnesie);
        int count = Integer.parseInt(counter);
        count--;
        return String.valueOf(count);
    }

}
