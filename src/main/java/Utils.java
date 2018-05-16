
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.lang.InterruptedException;
import java.lang.IllegalArgumentException;

import javax.xml.datatype.Duration;

public class Utils {
    static public MobileElement waitForElementById(AndroidDriver driver, String elementId, int timeout){
        int cout = 0;
        while (timeout == 0 || timeout>0 && cout<timeout) {
            try {
                return (MobileElement) driver.findElementById(elementId);
            } catch (Exception e) {
                cout = cout + 1;
                System.out.println(elementId+":"+cout);
            }
        }
        return null;
    }

    static public MobileElement waitForElementByAccessibilityId(AndroidDriver driver, String AccessibilityId, int timeout){
        int cout = 0;
        while (timeout == 0 || timeout>0 && cout<timeout) {
            try {
                return (MobileElement) driver.findElementById(AccessibilityId);
            } catch (Exception e) {
                cout = cout + 1;
                System.out.print(AccessibilityId+":"+cout);
            }
        }
        return null;
    }
}
