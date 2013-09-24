drop database javaee;
create database javaee;
use javaee;
create table person_table
(
 id int primary key auto_increment,
 person_type varchar(255),
 name varchar(255),
 gender varchar(2),
 address_country varchar(255),
 address_detail varchar(255),
 address_zip varchar(255),
 comments varchar(255),
 salary double,
 title varchar(255),
 department varchar(255),
 employee_id int,
 manager_id int,
 foreign key(employee_id) references person_table(id),
 foreign key(manager_id ) references person_table(id)
);

insert into person_table
values
(null , '普通人' , 'Yeeku' , '男' , '中国' , '天河' , '434333' , NULL , NULL , NULL , NULL , NULL , NULL),
(null , '经理' , 'Grace' , '女' , '美国' , '加州' , '523034' , NULL , 12000 , '项目经理' , '研发部' , NULL , NULL),
(null , '员工' , '老朱' , '男' , '中国' , '广州' , '523034' , NULL , 4500 , '项目组长' , NULL , NULL , 2),
(null , '员工' , '张美丽' , '女' , '中国' , '广州' , '523034' , NULL , 5500 , '项目分析' , NULL , NULL , 2),
(null , '顾客' , '小贺' , '男' , '中国' , '湖南' , '233034' , '喜欢购物' , NULL , NULL , NULL , 2 , NULL);
