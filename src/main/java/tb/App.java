package tb;

import org.openqa.selenium.WebDriver;
import tb.webdriver.Init;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        WebDriver driver = Init.getWebDriver();
        Init.getWebDriver().get("https://www.tinkoff.ru/");
    }
}
