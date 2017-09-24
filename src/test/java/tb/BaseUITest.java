package tb;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import tb.testng.TestNGListener;
import tb.webdriver.Init;


@Listeners(TestNGListener.class)
public class BaseUITest {

     WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = Init.getWebDriver();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
