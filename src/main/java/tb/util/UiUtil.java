package tb.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tb.webdriver.Init;

import java.util.concurrent.TimeUnit;

public class UiUtil {

    private static int timeOut = 30;

    public static WebElement waitToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Init.getWebDriver(), timeOut);
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(Init.getWebDriver(), timeOut);
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public static boolean isElementPresent(String xPathLocator) {
        Init.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean isFind = Init.getWebDriver().findElements(By.xpath(xPathLocator)).size() == 1;
        Init.getWebDriver().manage().timeouts().implicitlyWait(Init.implicitlyTimeOut, TimeUnit.SECONDS);
        return isFind;
    }

    public static void scrollDown(String xPathLast, String xPathIntermittentlyLast) {
        while (!UiUtil.isElementPresent(xPathLast)) {
            WebElement el = Init.getWebDriver().findElement(By.xpath(xPathIntermittentlyLast));
            JavascriptExecutor js = (JavascriptExecutor) Init.getWebDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", el);
        }
    }
}
