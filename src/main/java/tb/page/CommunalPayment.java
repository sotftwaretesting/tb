package tb.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tb.constant.Const;
import tb.util.UiUtil;
import tb.webdriver.Init;
import tb.webdriver.WaitToBeVisible;

@WaitToBeVisible(xpath = Const.XPATH_LOG)
public class CommunalPayment extends AbstractPage {

    @FindBy(xpath = "//span[@class='ui-link payment-page__title_inner']")
    private WebElement location;



    public void changePaymentLocation(String region) {
        UiUtil.waitToBeClickable(location);
        String currentLocation = location.getText().trim();
        if (!StringUtils.equalsIgnoreCase(region, currentLocation)) {
            location.click();
            UiUtil.waitToBeVisible(By.xpath("//div[@class='ui-modal__container ui-modal__container_regions']"));
            String toChange = String.format("(//span[contains(text(),'%s')])[last()] ", region);
            UiUtil.waitToBeClickable(Init.getWebDriver().findElement(By.xpath(toChange))).click();
        }
    }

    public String selectSupplierAndGetName(int positionInList) {
        String XpathName = String.format("(//div[@class='ui-page-frame__content']//li[%d]//a)[2]", positionInList);
        WebElement element = UiUtil.waitToBeVisible(By.xpath(XpathName));
        String companyName = element.getAttribute("title");
        UiUtil.waitToBeClickable(element).click();
        UiUtil.waitToBeVisible(By.xpath("//h1[contains(text(),'Узнайте задолженность')]"));
        return companyName.trim();
    }

    public boolean isNotExist(String name){
        String xPath = String.format("//span[text()='%s']",name);
        return UiUtil.isElementPresent(xPath);
    }
}
