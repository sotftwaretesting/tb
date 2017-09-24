package tb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tb.constant.Const;
import tb.util.CommonUtil;
import tb.webdriver.WaitToBeVisible;

@WaitToBeVisible(xpath = Const.XPATH_LOG)
public class PaymentDetails extends AbstractPage {

    @FindBy(xpath = "(//span[@class='ui-menu-second__title'])[2]")
    private WebElement pay;

    @FindBy(id = "payerCode")
    private WebElement payerCode;

    @FindBy(css = "input[name='provider-period']")
    private WebElement timePeriod;

    @FindBy(xpath = "(//div[@class='ui-input__column'])[4]/input")
    private WebElement amount;

    @FindBy(xpath = "//h2[contains(text(),'Оплатить')]/..")
    private WebElement paymentButton;


    public void clickPay() {
        pay.click();
    }

    private String xPathFromElement = ".//ancestor::div[@class='ui-form__field']/div[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']";

    public boolean checkPayerCodeField(String inputData, String errorMessage) {
        CommonUtil.pause(1);
        clearAllFields();
        payerCode.sendKeys(inputData);
        paymentButton.click();
        return errorMessage.equals(payerCode.findElement(By.xpath(xPathFromElement)).getText().trim());
    }

    public boolean checkPaymentPeriodField(String inputData, String errorMessage) {
        CommonUtil.pause(1);
        clearAllFields();
        timePeriod.sendKeys(inputData);
        paymentButton.click();
        return errorMessage.equals(timePeriod.findElement(By.xpath(xPathFromElement)).getText().trim());
    }

    public boolean checkAmountField(String inputData, String errorMessage) {
        CommonUtil.pause(1);
        clearAllFields();
        amount.sendKeys(inputData);
        paymentButton.click();
        return errorMessage.equals(amount.findElement(By.xpath(xPathFromElement)).getText().trim());
    }

    private void clearAllFields() {
        payerCode.sendKeys(Keys.CONTROL + "a");
        payerCode.sendKeys(Keys.DELETE);
        timePeriod.sendKeys(Keys.CONTROL + "a");
        timePeriod.sendKeys(Keys.DELETE);
        amount.sendKeys(Keys.CONTROL + "a");
        amount.sendKeys(Keys.DELETE);
    }
}
