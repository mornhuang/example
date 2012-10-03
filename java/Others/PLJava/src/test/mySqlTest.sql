-- Create table for persons
drop table if exists person;
create table person (
	id bigint not null primary key,
	name varchar(255) not null,
	last_update_time timestamp not null);
-- Fill person table with values
insert into person (id, name) values (1, 'John');
insert into person (id, name) values (2, 'Nick');
insert into person (id, name) values (3, 'Mary');
insert into person (id, name) values (4, 'Natalie');


-- Create table for managers
drop table if exists manager;
create table manager (
	id bigint not null primary key,
	department varchar(255) not null);


-- Create table for employeers
drop table if exists employee;
create table employee (
	id bigint not null primary key,
	position_id bigint not null);
-- Fill employee table with values
insert into employee (id, position_id) values (1, 21);
insert into employee (id, position_id) values (3, 22);

-- Create table for tasks
drop table if exists task;
create table task (
	id bigint not null primary key,
	employee_id bigint not null,
	name varchar(255));
insert into task (id, employee_id, name) values (1, 1, 'Finish communication module');
insert into task (id, employee_id, name) values (2, 1, 'Prepare module for testing');
insert into task (id, employee_id, name) values (3, 1, 'Negotiate requirements');
insert into task (id, employee_id, name) values (4, 3, 'Perform tests');
insert into task (id, employee_id, name) values (5, 3, 'Create report');


-- Create table for positions
drop table if exists position;
create table position (
	id bigint not null,
	name varchar(255) not null);
-- Fill position table with values
insert into position (id, name) values (21, 'Software developer');
insert into position (id, name) values (22, 'Quality analysis');


-- Create table for id generator
drop table if exists pl_id;
create table pl_id (
	last_high_value bigint not null);
