package tb.step;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import tb.page.PaymentDetails;

import java.util.HashMap;
import java.util.Map;

public class PaymentDetailsStep {

    @Step("пользователь переходит на вкладку: {whereToGo}")
    public static void whereToGo(WhereToGo whereToGo) {
        PaymentDetails paymentDetails = new PaymentDetails();
        switch (whereToGo) {
            case PAY:
                paymentDetails.clickPay();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Step("польватель проверяет поле 'Код плательщика'")
    public static Map<String,Boolean> checkPayerCodeField() {
        Map<String, Boolean> checkResult = new HashMap<>();
        PaymentDetails paymentDetails = new PaymentDetails();
        boolean empty = paymentDetails.checkPayerCodeField("", "Поле обязательное");
        boolean notFull = paymentDetails.checkPayerCodeField(RandomStringUtils.randomNumeric(1, 10), "Поле неправильно заполнено");
        checkResult.put("Код плательщика, пустое значение", empty);
        checkResult.put("Код плательщика, неполное значение", notFull);
        return checkResult;
    }

    @Step("польватель проверяет поле 'Период платежа'")
    public static Map<String,Boolean> checkPaymentPeriodField() {
        Map<String, Boolean> checkResult = new HashMap<>();
        PaymentDetails paymentDetails = new PaymentDetails();
        boolean empty = paymentDetails.checkPaymentPeriodField("", "Поле обязательное");
        boolean lowNotCorrect = paymentDetails.checkPaymentPeriodField("00.0000", "Поле заполнено некорректно");
        boolean upNotCorrect = paymentDetails.checkPaymentPeriodField("13.0000", "Поле заполнено некорректно");
        checkResult.put("Период, пустое значение", empty);
        checkResult.put("Период, значение 00.0000", lowNotCorrect);
        checkResult.put("Период, значение 13.0000", upNotCorrect);
        return checkResult;
    }

    @Step("польватель проверяет поле 'Сумма платежа'")
    public static Map<String,Boolean> checkAmountField() {
        Map<String, Boolean> checkResult = new HashMap<>();
        PaymentDetails paymentDetails = new PaymentDetails();
        boolean empty = paymentDetails.checkAmountField("", "Поле обязательное");
        boolean lowNotCorrect = paymentDetails.checkAmountField("9", "Минимальная сумма перевода - 10 \u20BD");
        boolean upNotCorrect = paymentDetails.checkAmountField("15001", "Максимальная сумма перевода - 15 000 \u20BD");
        checkResult.put("Сумма платежа, пустое значение", empty);
        checkResult.put("Сумма платежа, значение 9", lowNotCorrect);
        checkResult.put("Сумма платежа, значение 15001", upNotCorrect);
        return checkResult;
    }


    public enum WhereToGo {
        PAY, ARREARS;

        @Override
        public String toString() {
            switch (this) {
                case PAY:
                    return "Узнать задолженность";
                case ARREARS:
                    return "Оплатить";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }


}
