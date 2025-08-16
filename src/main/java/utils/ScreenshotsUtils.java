package utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotsUtils {
    public static final String SCREENSHOTS_PATH = "test-logs/screenshots/";
    private ScreenshotsUtils(){super();}

    public static void attachScreenshotToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
        } catch (Exception e) {
            LogsUtil.error("Failed to attach screenshot to Allure report: " + e.getMessage());
        }
    }

    public static void takeScreenshot(WebDriver driver , String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + ".png");
            FileUtils.copyFile(screenshot, screenshotFile);
            attachScreenshotToAllure(screenshotName, screenshotFile.getPath());
        } catch (Exception e) {
            LogsUtil.error("Failed to take screenshot: " + e.getMessage());
        }
    }

}
