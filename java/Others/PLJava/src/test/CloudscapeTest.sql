-- Create table for persons
drop table person;
create table person (
	id longint not null constraint pk_person  primary key,
	name varchar(255) not null);
-- Fill person table with values
insert into person (id, name) values (1, 'John');
insert into person (id, name) values (2, 'Nick');
insert into person (id, name) values (3, 'Mary');
insert into person (id, name) values (4, 'Natalie');


-- Create table for managers
drop table manager ;
create table manager (
	id longint not null constraint pk_manager  primary key,
	department varchar(255) not null);


-- Create table for employeers
drop table employee ;
create table employee (
	id longint not null constraint pk_employee  primary key,
	position_id longint not null);
-- Fill employee table with values
insert into employee (id, position_id) values (1, 21);
insert into employee (id, position_id) values (3, 22);

-- Create table for tasks
drop table task ;
create table task (
	id longint not null constraint pk_task  primary key,
	employee_id longint not null,
	name varchar(255));
insert into task (id, employee_id, name) values (1, 1, 'Finish communication module');
insert into task (id, employee_id, name) values (2, 1, 'Prepare module for testing');
insert into task (id, employee_id, name) values (3, 1, 'Negotiate requirements');
insert into task (id, employee_id, name) values (4, 3, 'Perform tests');
insert into task (id, employee_id, name) values (5, 3, 'Create report');


-- Create table for positions
drop table positionx;
create table positionx (
	id longint not null constraint pk_position  primary key,
	name varchar(255) not null);
-- Fill position table with values
insert into positionx (id, name) values (21, 'Software developer');
insert into positionx (id, name) values (22, 'Quality analysis');


-- Create table for id generator
drop table pl_id;
create table pl_id (
	last_high_value longint not null constraint pk_pl_id  primary key);

