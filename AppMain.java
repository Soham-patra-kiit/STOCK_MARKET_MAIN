package com.revstox;

import com.revstox.model.Stock;
import com.revstox.model.DailyPrice;
import com.revstox.service.StockService;
import com.revstox.service.StockServiceImpl;
import com.revstox.dao.DailyPriceDAO;
import com.revstox.dao.DailyPriceDAOImpl;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import java.io.*;

public class AppMain
{
    private static final Logger logger = Logger.getLogger(AppMain.class.getName());

    public static void main(String[] args)
    {
        try
        {
            LogManager.getLogManager().readConfiguration(AppMain.class.getResourceAsStream("/logging.properties"));
        }
        catch (Exception e)
        {
            logger.warning("Logging configuration not loaded: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        StockService stockService = new StockServiceImpl();
        DailyPriceDAO dailyPriceDAO = new DailyPriceDAOImpl();
        boolean running = true;

        while (running)
        {
            System.out.println("\n===== RevStox Menu =====");
            System.out.println("1. Add Stock");
            System.out.println("2. View Stock by Symbol");
            System.out.println("3. View All Stocks");
            System.out.println("4. Show Stocks by Sector");
            System.out.println("5. Show Total Number of Stocks");
            System.out.println("6. Delete Stock by Symbol");
            System.out.println("7. Check if Stock Exists");
            System.out.println("8. Clear All Stocks (Danger Zone)");
            System.out.println("9. Retrieve Historical Price Data (with Price & Volume Analysis)");
            System.out.println("10. Import CSV File");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.print("Enter symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter company name: ");
                    String company = scanner.nextLine();
                    System.out.print("Enter sector: ");
                    String sector = scanner.nextLine();
                    System.out.print("Enter market cap: ");
                    double cap = scanner.nextDouble();
                    stockService.addStock(new Stock(symbol, company, sector, cap));
                    logger.info("Stock added: " + symbol);
                    break;

                case 2:
                    System.out.print("Enter symbol: ");
                    String fetchSym = scanner.nextLine();
                    Stock s = stockService.fetchStock(fetchSym);
                    if (s != null) {
                        System.out.printf("%s | %s | %s | %.2f\n", s.getSymbol(), s.getCompanyName(), s.getSector(), s.getMarketCap());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 3:
                    List<Stock> allStocks = stockService.listAllStocks();
                    for (Stock stock : allStocks)
                    {
                        System.out.printf("%s | %s | %s | %.2f\n", stock.getSymbol(), stock.getCompanyName(), stock.getSector(), stock.getMarketCap());
                    }
                    break;

                case 4:
                    System.out.print("Enter sector: ");
                    String filterSector = scanner.nextLine();
                    for (Stock stock : stockService.listAllStocks()) {
                        if (stock.getSector().equalsIgnoreCase(filterSector)) {
                            System.out.printf("%s | %s | %s | %.2f\n", stock.getSymbol(), stock.getCompanyName(), stock.getSector(), stock.getMarketCap());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Total Stocks: " + stockService.listAllStocks().size());
                    break;

                case 6:
                    System.out.print("Enter symbol to delete: ");
                    String delSymbol = scanner.nextLine();
                    stockService.deleteStock(delSymbol);
                    logger.info("Deleted stock: " + delSymbol);
                    break;

                case 7:
                    System.out.print("Enter symbol to check: ");
                    String checkSym = scanner.nextLine();
                    Stock exists = stockService.fetchStock(checkSym);
                    System.out.println(exists != null ? "Stock exists." : "Stock not found.");
                    break;

                case 8:
                    stockService.deleteAllStocks();
                    System.out.println("All stocks deleted.");
                    break;

                case 9:
                    System.out.print("Enter symbol to fetch historical prices: ");
                    String histSymbol = scanner.nextLine();
                    List<DailyPrice> prices = dailyPriceDAO.getPricesBySymbol(histSymbol);
                    if (prices.isEmpty())
                    {
                        System.out.println("No historical data found.");
                    }
                    else
                    {
                        DailyPrice prev = null;
                        double vwapNumerator = 0, vwapDenominator = 0;
                        for (DailyPrice dp : prices) {
                            double volatility = (dp.getHighPrice() - dp.getLowPrice()) / dp.getOpenPrice() * 100;
                            double priceChange = (dp.getClosePrice() - dp.getOpenPrice()) / dp.getOpenPrice() * 100;
                            double gap = prev != null ? (dp.getOpenPrice() - prev.getClosePrice()) : 0.0;
                            double turnover = dp.getClosePrice() * dp.getVolume();
                            vwapNumerator += dp.getClosePrice() * dp.getVolume();
                            vwapDenominator += dp.getVolume();

                            System.out.printf("%s | %s | O: %.2f | H: %.2f | L: %.2f | C: %.2f | V: %d\n",
                                    dp.getSymbol(), dp.getTradeDate(), dp.getOpenPrice(),
                                    dp.getHighPrice(), dp.getLowPrice(), dp.getClosePrice(), dp.getVolume());
                            System.out.printf("Volatility: %.2f%% | Price Change: %.2f%% | Price Gap: %.2f | Turnover: %.2f\n\n",
                                    volatility, priceChange, gap, turnover);
                            prev = dp;
                        }
                        double vwap = (vwapDenominator != 0) ? (vwapNumerator / vwapDenominator) : 0.0;
                        System.out.printf("\nOverall VWAP: %.2f\n", vwap);
                    }
                    break;

                case 10:
                    System.out.print("Enter path to CSV file: ");
                    String csvPath = scanner.nextLine();
                    try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
                        String line;
                        br.readLine();
                        while ((line = br.readLine()) != null)
                        {
                            String[] parts = line.split(",");
                            DailyPrice dp = new DailyPrice(
                                    parts[1],
                                    Date.valueOf(parts[0]),
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    Double.parseDouble(parts[5]),
                                    Long.parseLong(parts[6]),
                                    Double.parseDouble(parts[7])
                            );
                            dailyPriceDAO.insert(dp);
                        }
                        System.out.println("Data imported successfully.");
                    }
                    catch (Exception e)
                    {
                        logger.warning("CSV import failed: " + e.getMessage());
                    }
                    break;

                case 11:
                    running = false;
                    System.out.println("Exiting RevStox. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
