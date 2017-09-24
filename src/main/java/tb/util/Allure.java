package tb.util;

import io.qameta.allure.Attachment;

public class Allure {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
