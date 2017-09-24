package tb.step;

import io.qameta.allure.Step;
import tb.page.CommunalPayment;
import tb.util.UiUtil;

public class ChooseCommunalSupplierPaymentStep {

    @Step("пользователь выбирает локацию платежа: {region}")
    public static void choosePaymentLocation(String region) {
        new CommunalPayment().changePaymentLocation(region);
    }

    @Step("пользователь выбирает фирму для платежа, позиция в списке: {position}")
    public static String choosePaymentSupplier(int position) {
       return new CommunalPayment().selectSupplierAndGetName(position);
    }

    @Step("пользователь проверяет отсутствие компании в списке: {name}")
    public static boolean isExist(String name){
        UiUtil.scrollDown("//span[text()='ТСЖ Родник (СПб)']", "//section//li[last()]");
        return new CommunalPayment().isNotExist(name);
    }
}
