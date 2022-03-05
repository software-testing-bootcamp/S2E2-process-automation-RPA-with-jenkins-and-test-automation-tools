package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class PlatformManager {
    public AppiumDriver appiumDriver;
    public WebDriver webDriver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public String baseURL;

    public void setDriver(String testPlatform) throws MalformedURLException {
        switch (testPlatform) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                //chromeOptions.addArguments("--headless");
                webDriver = new ChromeDriver(chromeOptions);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                baseURL = "https://coinmarketcap.com/currencies/bitcoin/";
                wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
                System.out.println("***** Browser is chrome-bonigarcia *****");
                break;
            }
            case "api": {
                System.out.println("***** API *****");
                break;
            }
            default: {
                DesiredCapabilities androidCaps = new DesiredCapabilities();
                androidCaps.setCapability("appium:deviceName", "Pixel XL API 30");
                androidCaps.setCapability("appium:automationName", "UIAutomator2");
                androidCaps.setCapability("appium:udid", "emulator-5554"); //DeviceId from "adb devices" command
                androidCaps.setCapability("appium:platformName", "Android");
                androidCaps.setCapability("appium:platformVersion", "11.0");
                androidCaps.setCapability("appium:skipUnlock", "true");
                androidCaps.setCapability("appium:app", System.getProperty("user.dir") + "/src/test/resources/files/coinMarketCap-latest.apk");
                androidCaps.setCapability("appium:fullReset", "true");
                androidCaps.setCapability("appium:noReset", "false");
                androidCaps.setCapability("appPackage", "com.coinmarketcap.android");
                androidCaps.setCapability("appActivity", "com.coinmarketcap.android.LaunchActivity");
                appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), androidCaps);
                appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
                System.out.println("***** Android App *****");
                break;
            }
        }


    }


}
