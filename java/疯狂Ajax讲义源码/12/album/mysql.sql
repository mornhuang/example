drop database if exists album;

create database album;

use album;

create table user_table
(
 user_id int auto_increment primary key,
 name varchar(255) unique,
 pass varchar(255)
);

create table photo_table
(
 photo_id int auto_increment primary key,
 title varchar(255),
 fileName varchar(255),
 owner_id int,
 foreign key(owner_id) references user_table(user_id)
);


