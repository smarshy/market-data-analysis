use exchangemarket;

create table nasdaq1(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	market_ID int DEFAULT 3,
	FOREIGN KEY (market_ID) REFERENCES exchanges (marketID)
);

use exchangemarket;
load data local infile 'C:\\Users\\Grad_43\\Downloads\\Market Data Analysis\\Exchange Market Database\\nasdaq.csv'
into table nasdaq1
fields terminated by ','
lines terminated by '\n'
IGNORE 1 LINES;

ALTER TABLE nasdaq1
ADD indexkey int auto_increment primary key;

use exchangemarket;
ALTER TABLE nasdaq1
ADD sector varchar(50);
ALTER TABLE nasdaq1
ADD region varchar(50);

use exchangemarket;
UPDATE nasdaq1 SET sector = ELT(1 + FLOOR(RAND()*6), 'Banking/Finance', 'Technology', 'Pharmaceuticals', 'Engineering', 'Automotive', 'Oil and Gas')
WHERE sector is NULL;
UPDATE nasdaq1 SET region = ELT(1 + FLOOR(RAND()*4), 'NAM', 'APAC', 'EMEA', 'Latin America')
WHERE region is NULL;

DROP TABLE nasdaq;

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
    indexkey int auto_increment primary key,
    sector varchar(50),
    region varchar(50)
);


INSERT INTO nasdaq
SELECT * FROM nasdaq1 LIMIT 100000;

DROP TABLE nasdaq1;