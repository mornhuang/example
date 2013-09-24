drop database online_inf;

create database online_inf;

use online_inf;

create table online_inf
(
 session_id varchar(255) primary key,
 username varchar(255),
 user_ip varchar(255),
 access_res varchar(255),
 last_access bigint
);
