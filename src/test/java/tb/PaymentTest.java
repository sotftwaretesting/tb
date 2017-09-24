package tb;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tb.step.ChooseCommunalSupplierPaymentStep;
import tb.step.ChoosePaymentTypeStep;
import tb.step.NavigationStep;
import tb.step.PaymentDetailsStep;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class PaymentTest extends BaseUITest {

    @Test
    public void testPayment() {
        driver.get("https://www.tinkoff.ru/");
        List<String> urlAndCompany = checkObligatoryField();
        checkPageUrlAndCompanyLocation(urlAndCompany.get(0), urlAndCompany.get(1));
        checkNotExist(urlAndCompany.get(1));
    }


    private void checkMapForResult(Map<String, Boolean> result) {
        SoftAssert softAssert = new SoftAssert();
        result.forEach((k, v) -> softAssert.assertTrue(v, k));
        softAssert.assertAll();
    }

    private List<String> checkObligatoryField() {
        Map<String, Boolean> obligatoryFields = new LinkedHashMap<>();
        NavigationStep.goToPayment();
        ChoosePaymentTypeStep.goToPaymentType("Коммунальные платежи");
        ChooseCommunalSupplierPaymentStep.choosePaymentLocation("Москва");
        String companyName = ChooseCommunalSupplierPaymentStep.choosePaymentSupplier(1);
        Assert.assertTrue("ЖКУ-Москва".equals(companyName), "Полученное значение не соответствует 'ЖКУ-Москва'");
        String urlByLink = driver.getCurrentUrl();
        PaymentDetailsStep.whereToGo(PaymentDetailsStep.WhereToGo.PAY);
        obligatoryFields.putAll(PaymentDetailsStep.checkPayerCodeField());
        obligatoryFields.putAll(PaymentDetailsStep.checkPaymentPeriodField());
        obligatoryFields.putAll(PaymentDetailsStep.checkAmountField());
        checkMapForResult(obligatoryFields);
        List<String> toReturn = new ArrayList<>();
        toReturn.add(urlByLink);
        toReturn.add(companyName);
        return toReturn;
    }

    private void checkPageUrlAndCompanyLocation(String urlByLink, String company) {
        SoftAssert softAssert = new SoftAssert();
        NavigationStep.goToPayment();
        ChoosePaymentTypeStep.searchCompany(company);
        softAssert.assertTrue(ChoosePaymentTypeStep.isFirstPositionAndClick(company),
                "Компания " + company + " не на первой позиции в списке подсказок");
        String urlBySearch = driver.getCurrentUrl();
        // можно получить <body> и сравнивать его
        softAssert.assertEquals(urlByLink, urlBySearch, "Страница 'Жку-Москва' имеет разные URL по поиску и по переходу по ссылкам");
        softAssert.assertAll();
    }

    private void checkNotExist(String company) {
        NavigationStep.goToPayment();
        ChoosePaymentTypeStep.goToPaymentType("Коммунальные платежи");
        ChooseCommunalSupplierPaymentStep.choosePaymentLocation("г. Санкт-Петербург");
        Assert.assertFalse(ChooseCommunalSupplierPaymentStep.isExist(company), company + " найдена в г. Санкт-Петербург");
    }
}
