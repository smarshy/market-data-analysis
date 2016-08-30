use ExchangeMarket;
create table exchanges(
	marketID int PRIMARY KEY,
	marketName varchar(50),
);

use ExchangeMarket;
create table forex(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	marketID int DEFAULT 1,
	FOREIGN KEY (marketID) REFERENCES exchanges (marketID)
);

use ExchangeMarket;
create table liffe(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	marketID int DEFAULT 2,
	FOREIGN KEY (marketID) REFERENCES exchanges (marketID)
);

use ExchangeMarket;
create table nasdaq(
	ticker varchar(50),
	exchangeDate int,
	openingPrice decimal(10,4),
	high decimal(10,4),
	low decimal(10,4),
	closingPrice decimal(10,4),
	volume int,
	marketID int DEFAULT 3,
	FOREIGN KEY (marketID) REFERENCES exchanges (marketID)
);

insert into exchanges values(1,'forex');
insert into exchanges values(2,'liffe');
insert into exchanges values(3,'nasdaq');
