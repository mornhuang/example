drop database javaee;
create database javaee;
use javaee;

create table users_table(
 userid int auto_increment primary key,
 username varchar(64) unique, 
 password VARCHAR(64)
);
create table roles_table
(
 roleid  int auto_increment primary key,
 username varchar(64),
 userRoles varchar(32)
);
# 插入一个用户
insert into users_table 
values(null, 'yeeku','123');
# 插入一个用户，以及它对应的角色
insert into roles_table 
values(null ,'yeeku','crazyit');
