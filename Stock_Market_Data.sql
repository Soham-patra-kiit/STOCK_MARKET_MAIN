-- CREATE DATABASE IF NOT EXISTS rev1stox;
-- USE rev1stox;

-- CREATE TABLE stocks 
-- (
--     symbol VARCHAR(10) PRIMARY KEY,
--     company_name VARCHAR(100),
--     sector VARCHAR(50),
--     market_cap DOUBLE
-- );

-- CREATE TABLE daily_prices 
-- (
--     symbol VARCHAR(10),
--     trade_date DATE,
--     open_price DOUBLE,
--     high_price DOUBLE,
--     low_price DOUBLE,
--     close_price DOUBLE,
--     volume BIGINT,
--     turnover DOUBLE,
--     PRIMARY KEY (symbol, trade_date),
--     FOREIGN KEY (symbol) REFERENCES stocks(symbol)
-- );

-- CREATE TABLE stock_analytics 
-- (
--     symbol VARCHAR(10),
--     date DATE,
--     volatility DOUBLE,
--     moving_avg_7 DOUBLE,
--     moving_avg_30 DOUBLE,
--     PRIMARY KEY (symbol, date),
--     FOREIGN KEY (symbol) REFERENCES stocks(symbol)
-- );

USE rev1stox;
-- Select * from stock_analytics;
-- DELIMITER $$

-- CREATE PROCEDURE insert_stocks()
-- BEGIN
--     DECLARE i INT DEFAULT 1;
--     WHILE i <= 1000 DO
--         INSERT INTO stocks (symbol, company_name, sector, market_cap)
--         VALUES (
--             CONCAT('SYM', LPAD(i, 4, '0')),
--             CONCAT('Company_', i),
--             ELT(1 + FLOOR(RAND() * 5), 'Technology', 'Finance', 'Healthcare', 'Energy', 'Retail'),
--             ROUND(RAND() * 100000 + 1000, 2)
--         );
--         SET i = i + 1;
--     END WHILE;
-- END $$

-- DELIMITER ;

-- CALL insert_stocks();

-- DELIMITER $$

-- CREATE PROCEDURE insert_daily_prices()
-- BEGIN
--     DECLARE i INT DEFAULT 1;
--     DECLARE j INT DEFAULT 0;
--     DECLARE base_date DATE DEFAULT '2025-07-01';

--     WHILE i <= 100 DO
--         SET j = 0;
--         WHILE j < 10 DO
--             SET @sym = CONCAT('SYM', LPAD(i, 4, '0'));
--             SET @dt = DATE_ADD(base_date, INTERVAL j DAY);
--             SET @open = ROUND(RAND() * 1000 + 50, 2);
--             SET @close = @open + ROUND((RAND() - 0.5) * 20, 2);
--             SET @high = GREATEST(@open, @close) + ROUND(RAND() * 10, 2);
--             SET @low = LEAST(@open, @close) - ROUND(RAND() * 10, 2);
--             SET @vol = FLOOR(RAND() * 10000 + 1000);
--             SET @turnover = ROUND((@open + @close) / 2 * @vol, 2);

--             INSERT INTO daily_prices
--             VALUES (@sym, @dt, @open, @high, @low, @close, @vol, @turnover);

--             SET j = j + 1;
--         END WHILE;
--         SET i = i + 1;
--     END WHILE;
-- END $$

-- DELIMITER ;

-- CALL insert_daily_prices();

-- DELIMITER $$

-- CREATE PROCEDURE insert_stock_analytics()
-- BEGIN
--     DECLARE i INT DEFAULT 1;
--     DECLARE j INT DEFAULT 0;
--     DECLARE base_date DATE DEFAULT '2025-07-01';

--     WHILE i <= 100 DO
--         SET j = 0;
--         WHILE j < 10 DO
--             SET @sym = CONCAT('SYM', LPAD(i, 4, '0'));
--             SET @dt = DATE_ADD(base_date, INTERVAL j DAY);
--             SET @volatility = ROUND(RAND() * 5, 2);
--             SET @ma7 = ROUND(RAND() * 1000, 2);
--             SET @ma30 = ROUND(RAND() * 1000, 2);

--             INSERT INTO stock_analytics
--             VALUES (@sym, @dt, @volatility, @ma7, @ma30);

--             SET j = j + 1;
--         END WHILE;
--         SET i = i + 1;
--     END WHILE;
-- END $$

-- DELIMITER ;

-- CALL insert_stock_analytics();


-- SELECT COUNT(*) FROM stocks;
-- SELECT COUNT(*) FROM daily_prices;
-- SELECT COUNT(*) FROM stock_analytics;

-- Select * from Stocks;