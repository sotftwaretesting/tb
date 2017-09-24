package tb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import tb.util.UiUtil;
import tb.webdriver.Init;
import tb.webdriver.WaitToBeVisible;

public abstract class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(Init.getWebDriver(), this);
        WaitToBeVisible waitFor = this.getClass().getAnnotation(WaitToBeVisible.class);
        UiUtil.waitToBeVisible(By.xpath(waitFor.xpath()));
    }
}
