package com.revstox.dao;


import com.revstox.model.Stock;
import java.util.*;

public interface StockDAO
{
    void insert(Stock stock);
    Stock fetchBySymbol(String symbol);
    List<Stock> fetchAll();
    void deleteBySymbol(String symbol);
    void deleteAll();
}