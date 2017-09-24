package tb.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class Init {

    private static WebDriver driver;
    public static final int implicitlyTimeOut = 30;

    public static WebDriver getWebDriver() {
        if (driver == null) {
            DesiredCapabilities conf = configChromeDriver();
            driver = new ChromeDriver(conf);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(implicitlyTimeOut, TimeUnit.SECONDS);
            initWrapper(driver);
            return driver;
        }
        return driver;
    }

    private static void initWrapper(WebDriver driver) {
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        Init.driver = eventDriver.register(new EventHandler());
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
