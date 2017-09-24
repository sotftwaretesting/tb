package tb.constant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tb.webdriver.EventHandler;
import tb.webdriver.Init;
import tb.util.UiUtil;

public class Const {

    public static final String XPATH_LOG = "//span[text()='Тинькофф']";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver(configChromeDriver());
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
            EventHandler handler = new EventHandler();
            eventDriver.register(handler);
            eventDriver.get("http://automation-remarks.com");
            WebElement element = eventDriver.findElement(By.id("target"));
            element.click();
    }
    private static DesiredCapabilities configChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setCapability(ChromeOptions.CAPABILITY, options);
        return dc;
    }
}
