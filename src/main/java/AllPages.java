import com.google.gson.annotations.Since;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.aspectj.weaver.ast.And;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import sun.jvm.hotspot.runtime.Thread;

public class AllPages {

    static String AllUser = "";

    static public void start(AndroidDriver driver){
        String source = driver.getPageSource();
        System.out.println(source);
        MobileElement list_view = Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/list_view",0);
        List<MobileElement> contents = list_view.findElementsById("com.alibaba.android.rimet:id/rl_content");
        for(MobileElement element : contents){
            MobileElement titleEle = element.findElementById("com.alibaba.android.rimet:id/tv_dept_name");
            System.out.println(titleEle.getText());
            scanFromElement(driver,titleEle);
        }
    }

    static public void scanFromElement(AndroidDriver driver,MobileElement rootElement){
        try {
            rootElement.click();
            java.lang.Thread.sleep(3000);//等待页面加载
            String source = driver.getPageSource();
            System.out.println(source);

            MobileElement list_view = Utils.waitForElementById(driver,"com.alibaba.android.rimet:id/list_view",0);

            List<MobileElement> items = list_view.findElementsById("com.alibaba.android.rimet:id/tv_contact_name");
            for (MobileElement item : items) {
                System.out.println(item.getText());
                getPersenalInfoWithElement(driver,item);
            }

            List<MobileElement> contents = list_view.findElementsById("com.alibaba.android.rimet:id/rl_content");
            for(MobileElement element : contents){
                try {
                    MobileElement titleEle = element.findElementById("com.alibaba.android.rimet:id/tv_dept_name");
                    System.out.println(titleEle.getText());
                    scanFromElement(driver,titleEle);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            MobileElement up = (MobileElement) driver.findElementByXPath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout[last()-1]");
            up.click();
            java.lang.Thread.sleep(3000);//等待页面加载
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void getPersenalInfoWithElement(AndroidDriver driver,MobileElement element){
        try {
            String fileName = element.getText();
            element.click();
            java.lang.Thread.sleep(3000);//等待页面加载
            String source = driver.getPageSource();
            writeFile(fileName,source);
            System.out.println(source);

            getUserInfo(driver);

            MobileElement back =(MobileElement) driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"返回\"]");
            back.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static public void writeFile(String fileName,String content){
        try{
            FileWriter writer = new FileWriter("/Users/cai/Desktop/appium/_333/files/"+fileName+".xml");
            writer.write(content);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static public void getUserInfo(AndroidDriver driver){

        try{

            String userInfo = "";

            List<MobileElement> keys1 = driver.findElementsById("com.alibaba.android.rimet:id/cell_title");
            List<MobileElement> values1 = driver.findElementsById("com.alibaba.android.rimet:id/cell_subTitle");

            for (int i=0;i<keys1.size() && i<values1.size();i++){
                try {
                    MobileElement keyEle = keys1.get(i);
                    MobileElement valueEle = values1.get(i);
                    String key = keyEle.getText();
                    String value = valueEle.getText();
                    userInfo = userInfo+key+":"+value+",";
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            try{
                MobileElement mobileValueEle =(MobileElement) driver.findElementById("com.alibaba.android.rimet:id/user_mobile_info_content_tv");
                String mobile = mobileValueEle.getText();
                userInfo = userInfo+"mobile:"+mobile+",";
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                MobileElement mailValueEle =(MobileElement) driver.findElementById("com.alibaba.android.rimet:id/user_mail_info_content_tv");
                String email = mailValueEle.getText();
                userInfo = userInfo+"email:"+email+",";
            }catch (Exception e){
                e.printStackTrace();
            }
            AllUser = AllUser + userInfo + "\n";
            System.out.println(AllUser);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


