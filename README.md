# 📊 RevStox - Stock Market Analytics Platform

RevStox is a Java-based data analytics application that processes and analyzes historical stock market data stored in a MySQL database. 
It provides insights like stock volatility, price movements, moving averages, volume-weighted average price (VWAP), and more using Java + SQL.

 🚀 Features

- 1. Add, view, and delete stocks
- 2. Import daily stock price data from CSV files
- 3. Perform price and volume analysis
- 4. Calculate stock volatility
- 5. Compute 7-day and 30-day moving averages
- 6. Store and view precomputed stock analytics
- 7. Validate user inputs and log system events

---
Technologies Used

- **Java (JDK 17+)**
- **JDBC** for database access
- **MySQL** for storing stock data
- **SQL (Window + Aggregate Functions)**
- **Maven (optional for logging libs)**
- **LogManager** for application logging

---

 Folder Structure


src/
├── com.revstox
│ └── AppMain.java
│
├── com.revstox.database
│ └── Database_Connection.java
│
├── com.revstox.model
│ ├── Stock.java
│ ├── DailyPrice.java
│ └── StockAnalytics.java
│
├── com.revstox.dao
│ ├── StockDAO.java(interface)
│ ├── StockDAOImpl.java
│ ├── DailyPriceDAO.java(interface)
│ ├── DailyPriceDAOImpl.java
│ ├── StockAnalyticsDAO.java(interface)
│ └── StockAnalyticsDAOImpl.java
│
└── com.revstox.service
├── StockService.java(interface)
├── StockServiceImpl.java
├── StockAnalyticsService.java(interface)
└── StockAnalyticsServiceImpl.java

CSV should be in the following format:

| Column       | Type       | Description                      |
|--------------|------------|----------------------------------|
| date         | `YYYY-MM-DD` | Trade date                     |
| symbol       | `String`   | Stock symbol                     |
| open         | `Double`   | Opening price                    |
| high         | `Double`   | Highest price                    |
| low          | `Double`   | Lowest price                     |
| close        | `Double`   | Closing price                    |
| volume       | `Long`     | Number of shares traded          |
| turnover     | `Double`   | close_price × volume             |


Main Menu Options :
1. Add Stock
2. View Stock by Symbol
3. View All Stocks
4. Show Stocks by Sector
5. Show Total Number of Stocks
6. Delete Stock by Symbol
7. Check if Stock Exists
8. Clear All Stocks
9. View Historical Price Analysis (Volatility, VWAP, Turnover)
10. Import Daily Prices from CSV
11. Generate Stock Analytics (Volatility + Moving Averages)
12. Display Precomputed Analytics
13. Exit

 Database Table Overview:


stocks:
symbol VARCHAR(10) PRIMARY KEY,
company_name VARCHAR(100),
sector VARCHAR(50),
market_cap DOUBLE

daily_prices:
symbol VARCHAR(10),
trade_date DATE,
open_price DOUBLE,
high_price DOUBLE,
low_price DOUBLE,
close_price DOUBLE,
volume BIGINT,
turnover DOUBLE


stock_analytics:
symbol VARCHAR(10),
date DATE,
volatility DOUBLE,
moving_avg_7 DOUBLE,
moving_avg_30 DOUBLE




