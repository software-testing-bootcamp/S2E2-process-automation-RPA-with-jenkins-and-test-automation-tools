package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DatabaseManager;
import utils.PlatformManager;

import java.net.MalformedURLException;

import static io.restassured.RestAssured.given;

public class APIAutomation extends PlatformManager {

    DatabaseManager databaseManager;

    @Parameters("platform")
    @BeforeClass(groups = {"hook"})
    void beforeClass(@Optional("platform") String platform) throws MalformedURLException {
        setDriver("api");
        databaseManager = new DatabaseManager();
    }

    @AfterClass(groups = {"hook"})
    void teardown() {
    }


    @Test(groups = {"api"})
    public void getAndSetPriceFromParibu() {
        RestAssured.baseURI = "https://www.paribu.com";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/ticker");
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        System.out.println(bodyAsString);
        Assert.assertTrue(bodyAsString.contains("BTC_TL"), "String did not found");

        JsonPath jsonPath = response.jsonPath();
        String BTC_lowestAsk = jsonPath.get("BTC_TL.lowestAsk").toString();
        String BTC_highestBid = jsonPath.get("BTC_TL.highestBid").toString();
        String BTC_volume = jsonPath.get("BTC_TL.volume").toString();
        System.out.println(BTC_lowestAsk);
        System.out.println(BTC_highestBid);
        System.out.println(BTC_volume);
        databaseManager.updateCoinTable("TRY", BTC_lowestAsk, BTC_highestBid, BTC_volume, "BTC", "Paribu");
        databaseManager.insertCoinHistoryTable("BTC", 1, "TRY", BTC_lowestAsk, BTC_highestBid, BTC_volume);
    }

    @Test(groups = {"api"})
    public void getAndSetPriceFromBtcTurk() {
        RestAssured.baseURI = "https://api.btcturk.com";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/api/v2/ticker");
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        System.out.println(bodyAsString);
        Assert.assertTrue(bodyAsString.contains("BTCTRY"), "String did not found");

        JsonPath jsonPath = response.jsonPath();
        String BTC_lowestAsk = jsonPath.get("data.ask[0]").toString();
        String BTC_highestBid = jsonPath.get("data.bid[0]").toString();
        String BTC_volume = jsonPath.get("data.volume[0]").toString();
        //System.out.println(BTC_lowestAsk);
        //System.out.println(BTC_highestBid);
        //System.out.println(BTC_volume);
        databaseManager.updateCoinTable("TRY", BTC_lowestAsk, BTC_highestBid, BTC_volume, "BTC", "BTCTurk");
        databaseManager.insertCoinHistoryTable("BTC", 2, "TRY", BTC_lowestAsk, BTC_highestBid, BTC_volume);
    }

    @Test(groups = {"api"})
    public void getAndSetPriceFromBinance() {
        RestAssured.baseURI = "https://api.binance.com";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/api/v3/ticker/24hr");
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        //System.out.println(bodyAsString);
        Assert.assertTrue(bodyAsString.contains("BTCTRY"), "String did not found");

        String btcAsString0 = bodyAsString.substring(bodyAsString.indexOf("{\"symbol\":\"BTCTRY\"") + 1);
        String btcAsString = btcAsString0.substring(0, btcAsString0.indexOf("},"));
        System.out.println(btcAsString);
        String BTC_lowestAsk0 = btcAsString.substring(btcAsString.indexOf("askPrice\":\"") + 11);
        String BTC_lowestAsk = BTC_lowestAsk0.substring(0, BTC_lowestAsk0.indexOf("\","));
        String BTC_highestBid0 = btcAsString.substring(btcAsString.indexOf("bidPrice\":\"") + 11);
        String BTC_highestBid = BTC_highestBid0.substring(0, BTC_highestBid0.indexOf("\","));
        String BTC_volume0 = btcAsString.substring(btcAsString.indexOf("volume\":\"") + 9);
        String BTC_volume = BTC_volume0.substring(0, BTC_volume0.indexOf("\","));
        //System.out.println(BTC_lowestAsk);
        //System.out.println(BTC_highestBid);
        //System.out.println(BTC_volume);
        databaseManager.updateCoinTable("TRY", BTC_lowestAsk, BTC_highestBid, BTC_volume, "BTC", "Binance");
        databaseManager.insertCoinHistoryTable("BTC", 3, "TRY", BTC_lowestAsk, BTC_highestBid, BTC_volume);
    }
}


