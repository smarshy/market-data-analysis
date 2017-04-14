create database wiring_db;

use wiring_db;

create table testTable(
	pk int(11) auto_increment primary key,
	var1 varchar(45),
    var2 varchar(45)
);

insert into testTable(var1, var2) values(
	'Hello',
    'The Wiring is up and running!'
);

insert into testTable(var1, var2) values(
	'Hello',
    'The Wiring is still up and running!'
);

insert into testTable(var1, var2) values(
	'Hello',
    'How is the Wiring still up and running?'
);