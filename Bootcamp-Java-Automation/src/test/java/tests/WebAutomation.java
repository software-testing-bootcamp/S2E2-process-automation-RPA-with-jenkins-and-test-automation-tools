package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import utils.DatabaseManager;
import utils.PlatformManager;

import java.net.MalformedURLException;

public class WebAutomation extends PlatformManager {
    DatabaseManager databaseManager;
    By currencyBy = By.xpath("//button[@title='Select Currency']");
    By searchBy = By.xpath("//input[@placeholder='Search']");
    By tryBy = By.xpath("//span[contains(text(),'TRY')]");
    By priceBy = By.xpath("//div[@class='priceValue smallerPrice']");

    @Parameters("platform")
    @BeforeClass(groups = {"hook"})
    void beforeClass(@Optional("platform") String platform) throws MalformedURLException {
        setDriver("chrome");
        databaseManager = new DatabaseManager();
    }

    @AfterClass(groups = {"hook"})
    void teardown() {
        webDriver.quit();
    }

    @Test(groups = {"chrome"})
    public void coinMarketCapTest() throws Exception {
        webDriver.get(baseURL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(currencyBy)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBy)).sendKeys("TRY");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tryBy)).click();
        Thread.sleep(2000);
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(priceBy)).getText();
        System.out.println(priceText);
        databaseManager.updateCoinTable("TRY", priceText, priceText, "0", "BTC", "CoinMarketCap-Web");
        databaseManager.insertCoinHistoryTable("BTC", 5, "TRY", priceText, priceText, "0");
    }

}
