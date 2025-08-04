package com.revstox.service;

import com.revstox.dao.StockDAO;
import com.revstox.dao.StockDAOImpl;
import com.revstox.model.Stock;

import java.util.*;

public class StockServiceImpl implements StockService
{
    private StockDAO stockDAO = new StockDAOImpl();

    public void addStock(Stock stock) {
        if (stock != null && stock.getSymbol() != null && !stock.getSymbol().isEmpty())
        {
            stockDAO.insert(stock);
        }
    }

    public Stock fetchStock(String symbol)
    {
        return stockDAO.fetchBySymbol(symbol);
    }

    public List<Stock> listAllStocks()
    {
        return stockDAO.fetchAll();
    }

    public void deleteStock(String symbol)
    {
        stockDAO.deleteBySymbol(symbol);
    }

    public void deleteAllStocks()
    {
        stockDAO.deleteAll();
    }
}
