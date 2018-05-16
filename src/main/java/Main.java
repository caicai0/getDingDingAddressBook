

import com.google.gson.annotations.Since;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import sun.jvm.hotspot.runtime.Thread;

public class Main {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "4.4.4");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.alibaba.android.rimet");
        desiredCapabilities.setCapability("appActivity", "com.alibaba.android.rimet.biz.SplashActivity");
        desiredCapabilities.setCapability("remoteAdbHost", "localhost");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        try {
            java.lang.Thread.sleep(3000);
            MobileElement el1 = (MobileElement) Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/login_slide_btn",0);
            el1.click();
            MobileElement el2 = (MobileElement) Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/et_phone_input",0);
           
            MobileElement el3 = (MobileElement) Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/et_pwd_login",0);

            MobileElement el4 = (MobileElement) Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/btn_next",0);
            el4.click();
            MobileElement el5 = (MobileElement) Utils.waitForElementById(driver,"android:id/button1",0);
            el5.click();
            MobileElement el6 = (MobileElement) Utils.waitForElementByAccessibilityId(driver,"同意",0);
            el6.click();

            MobileElement el7 = (MobileElement) Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/home_bottom_tab_button_contact",0);
            el7.click();

            List<MobileElement> el8s = driver.findElementsById("com.alibaba.android.rimet:id/cell_title");
            for(MobileElement element : el8s){
                if ("组织架构".equals(element.getText())){
                    element.click();
                    AllPages.start(driver);
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}