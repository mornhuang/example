drop database if exists javaee;
create database javaee;
use javaee;

create table news_inf
(
 news_id int auto_increment primary key,
 news_title varchar(255),
 news_content varchar(255)
);
