use exchangemarketbeta;

ALTER TABLE nyse
ADD indexkey int auto_increment primary key;

ALTER TABLE liffe
ADD indexkey int auto_increment primary key;

ALTER TABLE nasdaq
ADD indexkey int auto_increment primary key;

create table stockDetails(
	stockID int auto_increment primary key, 
    tickerName varchar(50)
);

ALTER TABLE stock
ADD ticker varchar(50);

INSERT INTO stock(ticker)
SELECT ticker
FROM
(
    SELECT ticker FROM liffe 
    UNION DISTINCT
    SELECT t2.ticker FROM nasdaq as t2
    JOIN liffe as t1 ON t2.ticker = t1.ticker
) AS t;

INSERT INTO stockDetails(tickerName)
SELECT ticker
FROM
(
    SELECT ticker FROM stock
    UNION DISTINCT
    SELECT t2.ticker FROM nyse as t2
    JOIN stock as t1 ON t2.ticker = t1.ticker
) AS t;


create table nasdaq(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	market_ID int DEFAULT 3,
	FOREIGN KEY (market_ID) REFERENCES exchanges (marketID),
    indexkey int auto_increment primary key
);

INSERT INTO nasdaq1
SELECT * FROM nasdaq LIMIT 700237;

use exchangemarketbeta;
create table stockDetails(
	exchangeDate int primary key,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	stock_ID int DEFAULT 3,
	FOREIGN KEY (stock_ID) REFERENCES stock(stockID)
);

use exchangemarket;
UPDATE liffe SET volume = FLOOR(20000 + RAND( ) *1000000);
UPDATE nyse SET volume = FLOOR(20000 + RAND( ) *1000000);

use exchangemarketbeta;
insert into stock(tickerName)
select distinct ticker from liffe;

use exchangemarketbeta;
insert into stock(tickerName)
select distinct ticker from nasdaq;

select distinct count(ticker) from liffe;

use exchangemarketbeta;
ALTER TABLE nasdaq
ADD sector varchar(50);
ALTER TABLE nasdaq
ADD region varchar(50);


use exchangemarketbeta;
UPDATE nasdaq SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL LIMIT 2215;

use exchangemarketbeta;
UPDATE liffe SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL LIMIT 227;

use exchangemarketbeta;
UPDATE nyse SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL LIMIT 227;


use exchangemarketbeta;
UPDATE nasdaq SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL LIMIT 2215;

use exchangemarketbeta;
UPDATE liffe SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL LIMIT 227;

use exchangemarketbeta;
UPDATE nyse SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL LIMIT 227;

use exchangemarketbeta;
create table sectorRegionMapping(
	sectorID int auto_increment primary key, 
    tickerName varchar(50),
    sectorName varchar(50),
    regionName varchar(50)
);

INSERT INTO sectorRegionMapping(tickerName, sectorName, regionName)
SELECT ticker, sector, region FROM liffe LIMIT 227;
INSERT INTO sectorRegionMapping(tickerName, sectorName, regionName)
SELECT ticker, sector, region FROM nasdaq LIMIT 2215;


use exchangemarketbeta;
UPDATE nasdaq
INNER JOIN sectorRegionMapping ON (nasdaq.ticker LIKE sectorRegionMapping.tickerName)
SET nasdaq.region = sectorRegionMapping.regionName;

use exchangemarketbeta;
UPDATE nasdaq SET region = 'APAC'
WHERE region is NULL;

use exchangemarketbeta;
UPDATE nasdaq
INNER JOIN sectorRegionMapping ON (nasdaq.ticker LIKE sectorRegionMapping.tickerName)
SET nasdaq.sector = sectorRegionMapping.sectorName;

use exchangemarketbeta;
UPDATE nasdaq SET sector = 'Technology'
WHERE sector is NULL;


show variables like 'innodb_lock_wait_timeout';
SET innodb_lock_wait_timeout = 500;
show variables like 'innodb_lock_wait_timeout';

select count(sector LIKE NULL) from nyse;



use exchangemarket;
UPDATE nasdaq SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL;

UPDATE liffe SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL;

UPDATE nyse SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL;

UPDATE nasdaq SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL;

UPDATE liffe SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL;

UPDATE nyse SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL;
