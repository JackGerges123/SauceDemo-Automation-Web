package automation.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutOverviewPage extends BasePage{

    private final By itemTotalPriceLocator = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]");
    private final By taxPriceLocator = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]");
    private final By totalPriceLocaator = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]");
    private final By finishButtonLocator = By.id("finish");
    private final By cancelButtonLocator = By.id("cancel");

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }


    public double getSumOfProductsPrices() {
        List<Double> prices = super.getPrices();
        double total = 0.0;
        for (Double price : prices) {
            total += price;
        }
        return total;
    }

    public Double getItemTotalPrice(){
       return super.extractOnlyPrice(itemTotalPriceLocator);
    }

    public Double getTaxPrice(){
        return super.extractOnlyPrice(taxPriceLocator);
    }

    public Double getTotalPrice(){
        return super.extractOnlyPrice(totalPriceLocaator);
    }

    @Step("Click on finish button")
    public CheckoutCompletePage clickOnFinishButton(){
        super.clickWhenClickable(finishButtonLocator);
        return new CheckoutCompletePage(driver);
    }

    @Step("Click on cancel button")
    public void clickOnCancelButton(){
        super.clickWhenClickable(cancelButtonLocator);
    }
}
