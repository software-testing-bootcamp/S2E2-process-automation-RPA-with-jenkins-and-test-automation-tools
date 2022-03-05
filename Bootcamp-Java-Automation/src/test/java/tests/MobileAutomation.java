package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import utils.DatabaseManager;
import utils.PlatformManager;

import java.net.MalformedURLException;

public class MobileAutomation extends PlatformManager {
    DatabaseManager databaseManager;
    By settingsBy = By.xpath("//android.widget.FrameLayout[@content-desc='Settings']/android.widget.ImageView");

    @Parameters("platform")
    @BeforeClass(groups = {"hook"})
    void beforeClass(@Optional("platform") String platform) throws MalformedURLException {
        setDriver(platform);
        databaseManager = new DatabaseManager();
    }

    @AfterClass(groups = {"hook"})
    void teardown() {
        appiumDriver.quit();
    }

    @Test(groups = {"android"})
    public void mobileAppTest() throws Exception {
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/onboarding_home")).click();
        appiumDriver.findElement(By.xpath("//android.widget.FrameLayout/android.widget.Button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsBy)).click();
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/default_currencies_text")).click();
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/search_button")).click();
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/search_src_text")).sendKeys("TRY");
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/currency_logo")).click();
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/search_close_btn")).click();
        appiumDriver.findElement(By.id("com.coinmarketcap.android:id/search_close_btn")).click();
        appiumDriver.findElement(By.xpath("//android.widget.ImageButton")).click();
        //Slow Xpath problem
        appiumDriver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc='Home']")).click();
        appiumDriver.findElement(By.xpath("//*[contains(@text , 'Bitcoin')]")).click();
        //Slow Xpath problem
        Thread.sleep(4000);
        String priceText = appiumDriver.findElement(By.id("com.coinmarketcap.android:id/main_price")).getText();
        System.out.println(priceText);
        databaseManager.updateCoinTable("TRY", priceText, priceText, "0", "BTC", "CoinMarketCap-Mobile");
        databaseManager.insertCoinHistoryTable("BTC", 4, "TRY", priceText, priceText, "0");

        // String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(MY_ASSERTION_EL)).getText();
        // Assert.assertEquals(actualText, "TRY");
        // Thread.sleep(2000);
    }

}
