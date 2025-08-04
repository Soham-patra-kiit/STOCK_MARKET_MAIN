package com.revstox.model;

import java.sql.Date;

public class DailyPrice
{
    private String symbol;
    private Date tradeDate;
    private double openPrice, highPrice, lowPrice, closePrice;
    private long volume;
    private double turnover;

    public DailyPrice()
    {

    }

    public DailyPrice(String symbol, Date tradeDate, double openPrice, double highPrice,
                      double lowPrice, double closePrice, long volume, double turnover)
    {
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.turnover = turnover;
    }

    public String getSymbol()
    {
        return symbol;
    }
    public Date getTradeDate()
    {
        return tradeDate;
    }
    public double getOpenPrice()
    {
        return openPrice;
    }
    public double getHighPrice()
    {
        return highPrice;
    }
    public double getLowPrice()
    {
        return lowPrice;
    }
    public double getClosePrice()
    {
        return closePrice;
    }
    public long getVolume()
    {
        return volume;
    }
    public double getTurnover()
    {
        return turnover;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public void setTradeDate(Date tradeDate)
    {
        this.tradeDate = tradeDate;
    }

    public void setOpenPrice(double openPrice)
    {
        this.openPrice = openPrice;
    }

    public void setHighPrice(double highPrice)
    {
        this.highPrice = highPrice;
    }

    public void setLowPrice(double lowPrice)
    {
        this.lowPrice = lowPrice;
    }

    public void setClosePrice(double closePrice)
    {
        this.closePrice = closePrice;
    }

    public void setVolume(long volume)
    {
        this.volume = volume;
    }

    public void setTurnover(double turnover)
    {
        this.turnover = turnover;
    }

    @Override
    public String toString()
    {
        return "DailyPrice{" +
                "symbol='" + symbol + '\'' +
                ", tradeDate=" + tradeDate +
                ", openPrice=" + openPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", closePrice=" + closePrice +
                ", volume=" + volume +
                ", turnover=" + turnover +
                '}';
    }
}