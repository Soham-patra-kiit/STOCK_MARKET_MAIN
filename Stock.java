package com.revstox.model;

public class Stock
{
    private String symbol;
    private String companyName;
    private String sector;
    private double marketCap;

    public Stock()
    {

    }

    public Stock(String symbol, String companyName, String sector, double marketCap)
    {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
    }

    public String getSymbol()
    {
        return symbol;
    }
    public String getCompanyName()
    {
        return companyName;
    }
    public String getSector()
    {
        return sector;
    }
    public double getMarketCap()
    {
        return marketCap;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public void setSector(String sector)
    {
        this.sector = sector;
    }

    public void setMarketCap(double marketCap)
    {
        this.marketCap = marketCap;
    }

    @Override
    public String toString()
    {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sector='" + sector + '\'' +
                ", marketCap=" + marketCap +
                '}';
    }
}

