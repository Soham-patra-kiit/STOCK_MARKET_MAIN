package com.revstox.dao;

import com.revstox.model.Stock;
import com.revstox.database.Database_Connection;

import java.sql.*;
import java.util.*;

public class StockDAOImpl implements StockDAO {
    public void insert(Stock stock)
    {
        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO stocks VALUES (?, ?, ?, ?)");) {
            stmt.setString(1, stock.getSymbol());
            stmt.setString(2, stock.getCompanyName());
            stmt.setString(3, stock.getSector());
            stmt.setDouble(4, stock.getMarketCap());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Stock fetchBySymbol(String symbol)
    {
        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM stocks WHERE symbol = ?");) {
            stmt.setString(1, symbol);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return new Stock(
                        rs.getString("symbol"),
                        rs.getString("company_name"),
                        rs.getString("sector"),
                        rs.getDouble("market_cap")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Stock> fetchAll()
    {
        List<Stock> stocks = new ArrayList<>();
        try (Connection conn = Database_Connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM stocks");) {
            while (rs.next())
            {
                stocks.add(new Stock(
                        rs.getString("symbol"),
                        rs.getString("company_name"),
                        rs.getString("sector"),
                        rs.getDouble("market_cap")
                ));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return stocks;
    }

    public void deleteBySymbol(String symbol)
    {
        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM stocks WHERE symbol = ?");) {
            stmt.setString(1, symbol);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAll()
    {
        try (Connection conn = Database_Connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM stocks");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

