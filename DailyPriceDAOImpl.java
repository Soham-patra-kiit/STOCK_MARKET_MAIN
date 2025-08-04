package com.revstox.dao;

import com.revstox.model.DailyPrice;
import com.revstox.database.Database_Connection;

import java.sql.*;
import java.util.*;

public class DailyPriceDAOImpl implements DailyPriceDAO
{
    public void insert(DailyPrice dp) {
        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO daily_prices(symbol, trade_date, open_price, high_price, low_price, close_price, volume, turnover) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");) {
            stmt.setString(1, dp.getSymbol());
            stmt.setDate(2, dp.getTradeDate());
            stmt.setDouble(3, dp.getOpenPrice());
            stmt.setDouble(4, dp.getHighPrice());
            stmt.setDouble(5, dp.getLowPrice());
            stmt.setDouble(6, dp.getClosePrice());
            stmt.setLong(7, dp.getVolume());
            stmt.setDouble(8, dp.getTurnover());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DailyPrice> getPricesBySymbol(String symbol)
    {
        List<DailyPrice> prices = new ArrayList<>();
        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM daily_prices WHERE symbol = ? ORDER BY trade_date");) {
            stmt.setString(1, symbol);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                prices.add(new DailyPrice(
                        rs.getString("symbol"),
                        rs.getDate("trade_date"),
                        rs.getDouble("open_price"),
                        rs.getDouble("high_price"),
                        rs.getDouble("low_price"),
                        rs.getDouble("close_price"),
                        rs.getLong("volume"),
                        rs.getDouble("turnover")
                ));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return prices;
    }
}

