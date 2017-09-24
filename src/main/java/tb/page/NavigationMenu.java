package tb.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tb.constant.Const;
import tb.webdriver.WaitToBeVisible;


@WaitToBeVisible(xpath = Const.XPATH_LOG)
public class NavigationMenu extends AbstractPage {

    @FindBy(xpath = "(//a[@href='/payments/'])[2]")
    private WebElement payment;

    public void clickPayment() {
        payment.click();
    }

}
