create database wiring_db;

use wiring_db;

create table testTable(
	var1 varchar(45),
    var2 varchar(45)
);

insert into testTable values(
	'Hello',
    'The Wiring is up and running!'
);