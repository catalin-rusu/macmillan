package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by DeLuTz on 2/5/2016.
 */
public class WebDriverHelper {

    static WebDriver driver;

    public static WebDriver open() {
        if (Const.BROWSER.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Please use BROWSER firefox or add support for another browser. See Const file");
        }
        return driver;
    }

    public static void goToHomePage() {
        driver.get(Const.URL);
    }
}
