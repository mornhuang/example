drop database javaee;
create database javaee;
use javaee;

create table person_table
(
 personid int primary key auto_increment,
 name varchar(255),
 age int
);

create table address_table
(
 addressid int primary key auto_increment,
 detail varchar(255),
 person_id int,
 foreign key(person_id) references person_table(personid)
);

insert into person_table
values
(null, '孙悟空' , 500),
(null, '猪八戒' , 380),
(null, '白晶晶' , 21),
(null, '杏仙' , 21);

insert into address_table
values
(null, '花果山福地 水帘洞洞天' , 1),
(null, '西天取经路' , 1),
(null, '高老庄' , 2),
(null, '云栈洞' , 2),
(null, '盘丝洞' , 3);
