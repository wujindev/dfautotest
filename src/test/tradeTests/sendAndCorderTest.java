package tradeTests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testScript.ScreenHot;
import testScript.loadInit;

/***
 * 买入
 */
public class sendAndCorderTest {

    private loadInit linit;

    private ScreenHot sc=new ScreenHot();

    private AndroidDriver driver;

    private loginTest _loginTest=new loginTest();

    /**
     * 执行 buy manage
     * 买入数量 为1000股
     */
    @Test
    public void manage()throws Exception{

        //click buy
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[2]")).click();

        //查看股东选择
        //driver.findElement(By.id("com.dfzq.winner:id/account_sp")).click();

        //输入股票代码
        driver.findElement(By.id("com.dfzq.winner:id/code_et")).click();
        driver.tap(1,212,1208,200);
        Thread.sleep(500);
        driver.tap(1,212,1208,200);
        Thread.sleep(500);
        driver.tap(1,357,1208,200);
        Thread.sleep(500);
        driver.tap(1,218,865,200);
        Thread.sleep(2000);
        //查看价格联动
        String price=driver.findElement(By.id("com.dfzq.winner:id/price_et")).getText();
        if(price.equals("")){
            sc.Screenshot(driver,"价格联动erro");
        }
        System.out.println(price);

        //price +
        driver.findElement(By.id("com.dfzq.winner:id/price_add")).click();

        //price -
        driver.findElement(By.id("com.dfzq.winner:id/price_sub")).click();

        //数量变化
        String enable_tv=driver.findElement(By.id("com.dfzq.winner:id/enable_tv")).getText();
        System.out.println(enable_tv);
        //购买1000股
        driver.findElement(By.id("com.dfzq.winner:id/amount_et")).sendKeys("200");

        //确认买入
        driver.findElement(By.id("com.dfzq.winner:id/trade_ok_btn")).click();

        //弹出信息
        String alert_message=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView")).getText();
        System.out.println(alert_message);

        //再次确认
        driver.findElement(By.id("android:id/button1")).click();
        //购买u成功提示
        String message=driver.findElement(By.id("android:id/message")).getText();
        System.out.println(message);

        if("委托失败,客户的股票不足".equals(message.trim())){
            driver.findElement(By.id("android:id/button2")).click();

        }else {
            //确认
            driver.findElement(By.id("android:id/button2")).click();
        }

        //撤单
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[3]")).click();
        Thread.sleep(2000);
        WebElement element=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
        String flagcode=element.getText();
        element.click();

        driver.findElement(By.id("android:id/button1")).click();
        String confirmMes=driver.findElement(By.id("android:id/message")).getText();
        System.out.println(confirmMes);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(2000);
        if(flagcode.equals(element.getText())){
            sc.Screenshot(driver,"价格联动erro");
        }

    }


    /***
     * 卖出扯淡案例
     * @throws Exception
     */
   @Test
   public void execSendTest()throws Exception{
       driver=_loginTest.init(driver);
       try{
           for(int i=0;i<=100;i++){
               this.manage();
               driver.findElement(By.id("com.dfzq.winner:id/left_back_button")).click();
           }
       }catch (Exception e){
           e.printStackTrace();
           sc.Screenshot(driver,"卖出撤单Erro");
           execSendTest();
       }

   }
    

}
