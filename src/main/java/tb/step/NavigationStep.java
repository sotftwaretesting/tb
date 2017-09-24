package tb.step;

import io.qameta.allure.Step;
import tb.page.NavigationMenu;

public class NavigationStep {


    @Step("пользователь переходит на страницу платежей")
    public static void goToPayment() {
        new NavigationMenu().clickPayment();
    }

}
