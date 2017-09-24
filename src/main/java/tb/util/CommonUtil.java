package tb.util;

public class CommonUtil {

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {

        }
    }
}
