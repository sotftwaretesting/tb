package tb.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tb.util.Allure;
import tb.webdriver.Init;

import java.io.File;
import java.nio.file.Files;

public class TestNGListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult var1) {
        super.onTestFailure(var1);
        try {
            File shot = ((TakesScreenshot) Init.getWebDriver()).getScreenshotAs(OutputType.FILE);
            byte[] data = Files.readAllBytes(shot.toPath());
            Allure.saveScreenshot(data);
        } catch (Exception e) {
            throw new AssertionError("Возникли проблемы при сохранении скриншота");
        }

    }
}
