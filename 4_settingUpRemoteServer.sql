create user 'team20_dbserver'@'localhost' identified by 'password';
grant all privileges on *.* to 'team20_dbserver'@'localhost'
with grant option;

create user 'team20_dbserver'@'%' identified by 'password';
grant all privileges on *.* to 'team20_dbserver'@'%'
with grant option;