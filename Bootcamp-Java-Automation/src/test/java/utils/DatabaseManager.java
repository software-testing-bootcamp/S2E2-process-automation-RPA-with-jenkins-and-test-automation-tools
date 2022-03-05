package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseManager {

    private final String url = "jdbc:mysql://127.0.0.1:3306/coin_test?characterEncoding=utf8";
    private final String username = "tester";
    private final String password = "test125";

    public void updateCoinTable(String currency, String lowestAsk, String highestBid, String volume, String symbol, String marketName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            String query = "UPDATE coin " +
                    "SET source='Java', currency=?, modifiedDate=?, " +
                    "lowestAsk=?,highestBid=?,volume=? " +
                    "WHERE symbol=? AND marketId=(SELECT Id FROM market WHERE marketName=?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, currency);
            preparedStmt.setString(2, getCurrentDateTime());
            preparedStmt.setString(3, lowestAsk);
            preparedStmt.setString(4, highestBid);
            preparedStmt.setString(5, volume);
            preparedStmt.setString(6, symbol);
            preparedStmt.setString(7, marketName);
            preparedStmt.executeUpdate();
            connection.close();
            System.out.println("Update Query executed");
        } catch (SQLException e) {
            System.err.println("DB exception on Update! ");
            System.err.println(e.getMessage());
        }

    }

    public void insertCoinHistoryTable(String symbol, Integer marketId, String currency, String lowestAsk, String highestBid, String volume) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            String query = "INSERT INTO coin_history (Id, coinId, currency, lowestAsk, highestBid, volume, source, marketId, createdDate) " +
                    "VALUES (NULL, (SELECT Id FROM coin WHERE symbol=? AND MarketId=? LIMIT 1), " +
                    "?, ?, ?, ?, 'Java', ?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, symbol);
            preparedStmt.setInt(2, marketId);
            preparedStmt.setString(3, currency);
            preparedStmt.setString(4, lowestAsk);
            preparedStmt.setString(5, highestBid);
            preparedStmt.setString(6, volume);
            preparedStmt.setInt(7, marketId);
            preparedStmt.setString(8, getCurrentDateTime());
            preparedStmt.executeUpdate();
            connection.close();
            System.out.println("Insert Query executed");
        } catch (SQLException e) {
            System.err.println("DB exception on Insert! ");
            System.err.println(e.getMessage());
        }

    }

    public String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}
