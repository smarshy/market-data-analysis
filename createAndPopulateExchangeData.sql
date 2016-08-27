create database exchangemarket;

use exchangemarket;
create table exchanges(
	marketID int PRIMARY KEY,
	marketName varchar(50)
);

use exchangemarket;
create table forex(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	market_ID int DEFAULT 1,
	FOREIGN KEY (market_ID) REFERENCES exchanges (marketID)
);

use exchangemarket;
create table liffe(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	market_ID int DEFAULT 2,
	FOREIGN KEY (market_ID) REFERENCES exchanges (marketID)
);

use exchangemarket;
create table nasdaq(
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
insert into exchanges values(1,'forex');
insert into exchanges values(2,'liffe');
insert into exchanges values(3,'nasdaq');


use exchangemarket;
load data local infile 'C:\\Users\\Grad_43\\Downloads\\Market Data Analysis\\Exchange Market Database\\forex.csv'
into table forex
fields terminated by ','
lines terminated by '\n'
IGNORE 1 LINES;

use exchangemarket;
load data local infile 'C:\\Users\\Grad_43\\Downloads\\Market Data Analysis\\Exchange Market Database\\liffe.csv'
into table liffe
fields terminated by ','
lines terminated by '\n'
IGNORE 1 LINES;

use exchangemarket;
load data local infile 'C:\\Users\\Grad_43\\Downloads\\Market Data Analysis\\Exchange Market Database\\nasdaq.csv'
into table nasdaq
fields terminated by ','
lines terminated by '\n'
IGNORE 1 LINES;