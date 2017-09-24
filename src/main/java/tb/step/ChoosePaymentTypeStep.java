package tb.step;

import io.qameta.allure.Step;
import tb.page.Payment;

public class ChoosePaymentTypeStep {


    @Step("пользователь выбирает платежи типа: {type}")
    public static void goToPaymentType(String type) {
        switch (type) {
            case "Коммунальные платежи":
                new Payment().clickCommunalPayment();
                break;
            default:
                throw new IllegalArgumentException("Не найден переход на тип платежа :" + type);
        }
    }

    @Step("пользователь ищет компанию: {companyName}")
    public static void searchCompany(String companyName) {
        new Payment().searchForCompany(companyName);
    }

    @Step("пользователь проверяет, что в списке предложенных провайдеров искомый поставщик первый: {name} и нажимает на неё")
    public static boolean isFirstPositionAndClick(String name) {
        return new Payment().isFirstAndClick(name);
    }
}
