package com.revstox.dao;

import com.revstox.model.DailyPrice;
import java.util.List;

public interface DailyPriceDAO
{
    void insert(DailyPrice dp);
    List<DailyPrice> getPricesBySymbol(String symbol);
}