drop database if exists hrSystem;

create database hrSystem;

use hrSystem;

create table type_table
(
 type_id int auto_increment,
 type_name varchar(50) not null,
 amerce_amount double not null,
 primary key(type_id)
);

create table emp_table
(
 emp_id int auto_increment,
 emp_type int,
 emp_name varchar(50) not null,
 emp_pass varchar(50) not null,
 emp_salary double not null,
 mgr_id int,
 dept_name varchar(50),
 primary key(emp_id),
 unique key(emp_name),
 foreign key(mgr_id) references emp_table(emp_id)
);

create table attend_table
(
 attend_id int auto_increment,
 duty_day varchar(50) not null,
 punch_time datetime,
 is_come boolean not null,
 type_id int not null,
 emp_id int not null,
 primary key(attend_id),
 foreign key(type_id) references type_table(type_id),
 foreign key(emp_id) references emp_table(emp_id)
);

create table app_table
(
 app_id int auto_increment,
 attend_id int not null,
 app_reason varchar(255),
 app_result boolean,
 type_id int not null,
 primary key(app_id),
 foreign key(type_id) references type_table(type_id),
 foreign key(attend_id) references attend_table(attend_id)
);

create table  pay_table
(
 pay_id int auto_increment,
 pay_month varchar(50) not null,
 pay_amount double not null,
 emp_id int not null,
 primary key(pay_id),
 foreign key(emp_id) references emp_table(emp_id)
);

create table  check_table
(
 check_id int auto_increment,
 app_id int not null,
 check_result boolean not null,
 check_reason varchar(255),
 mgr_id int not null,
 primary key(check_id),
 foreign key(app_id) references app_table(app_id),
 foreign key(mgr_id) references emp_table(emp_id)
);

insert into type_table ( type_name , amerce_amount)
	values ( '正常', 0);
insert into type_table ( type_name , amerce_amount)
	values ( '事假', -20);
insert into type_table ( type_name , amerce_amount)
	values ( '病假', -10);
insert into type_table ( type_name , amerce_amount)
	values ( '迟到', -10);
insert into type_table ( type_name , amerce_amount)
	values ( '早退', -10);
insert into type_table ( type_name , amerce_amount)
	values ( '旷工', -30);
insert into type_table ( type_name , amerce_amount)
	values ( '出差', 10);

# 插入经理
insert into emp_table (emp_type , emp_name , emp_pass , emp_salary , mgr_id , dept_name)
	values (2, 'oracle', 'oracle' , 5000 , null , 'DB部');
insert into emp_table (emp_type , emp_name , emp_pass , emp_salary , mgr_id , dept_name)
	values (2, 'weblogic', 'weblogic' , 6000 , null , 'Server部');
# 员工
insert into emp_table (emp_type , emp_name , emp_pass , emp_salary , mgr_id)
	values (1 , 'mysql', 'mysql' , 3000 , 1);
insert into emp_table (emp_type , emp_name , emp_pass , emp_salary , mgr_id)
	values (1 , 'hsql', 'hsql' , 3200 , 1);
insert into emp_table (emp_type , emp_name , emp_pass , emp_salary , mgr_id)
	values (1 , 'tomcat', 'tomcat' , 2800 , 2);
insert into emp_table (emp_type , emp_name , emp_pass , emp_salary , mgr_id)
	values (1 , 'jetty', 'jetty' , 2560 , 2);
