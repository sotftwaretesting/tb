package tb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tb.constant.Const;
import tb.util.UiUtil;
import tb.webdriver.WaitToBeVisible;

@WaitToBeVisible(xpath = Const.XPATH_LOG)
public class Payment extends AbstractPage {

    @FindBy(xpath = "(//a[@title='Коммунальные платежи'])[1]")
    private WebElement communalPayment;

    @FindBy(xpath = "//span[text()='Что оплатить или куда перевести?']/following-sibling::input")
    private WebElement search;

    public void searchForCompany(String name) {
        search.sendKeys(name);
    }

    public void clickCommunalPayment() {
        UiUtil.waitToBeClickable(communalPayment).click();
    }


    public boolean isFirstAndClick(String name) {
        String wasFound = String.format("//div[text()='%s']/ancestor::div[3][not(preceding-sibling::div)]", name);
        String toClick = String.format("//div[text()='%s']", name);
        UiUtil.waitToBeVisible(By.xpath(toClick));
        boolean result = UiUtil.isElementPresent(wasFound);
        if (result) {
            UiUtil.waitToBeVisible(By.xpath(toClick)).click();
        }
        return result;
    }

}
