package com.revstox.service;

import com.revstox.model.Stock;
import java.util.*;

public interface StockService
{
    void addStock(Stock stock);
    Stock fetchStock(String symbol);
    List<Stock> listAllStocks();
    void deleteStock(String symbol);
    void deleteAllStocks();
}