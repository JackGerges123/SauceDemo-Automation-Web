package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver getDriver(String browser){
        WebDriver d;
        switch(browser.toLowerCase()){
            case "edge":
                d = new EdgeDriver();
                break;
            case "firefox":
                d = new FirefoxDriver();
                break;
            default:
                d = new ChromeDriver();
                break;
        }
    return d;
    }
}
