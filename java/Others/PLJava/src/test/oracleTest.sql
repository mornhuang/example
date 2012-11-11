-- Create table for persons
drop table person;
create table person (
	id number not null,
	name varchar2(255) not null,
	last_update_time timestamp not null);
-- Fill person table with values
insert into person (id, name) values (1, 'John');
insert into person (id, name) values (2, 'Nick');
insert into person (id, name) values (3, 'Mary');
insert into person (id, name) values (4, 'Natalie');


  -- Create table for managers
drop table manager;
create table manager (
	id number not null,
	department varchar2(255) not null);


-- Create table for employeers
drop table employee;
create table employee (
	id number not null,
	position_id number not null);
-- Fill employee table with values
insert into employee (id, position_id) values (1, 21);
insert into employee (id, position_id) values (3, 22);

-- Create table for tasks
drop table task;
create table task (
	id number not null,
	employee_id number not null,
	name varchar2(255));
insert into task (id, employee_id, name) values (1, 1, 'Finish communication module');
insert into task (id, employee_id, name) values (2, 1, 'Prepare module for testing');
insert into task (id, employee_id, name) values (3, 1, 'Negotiate requirements');
insert into task (id, employee_id, name) values (4, 3, 'Perform tests');
insert into task (id, employee_id, name) values (5, 3, 'Create report');


-- Create table for positions
drop table position;
create table position (
	id number not null,
	name varchar2(255) not null);
-- Fill position table with values
insert into position (id, name) values (21, 'Software developer');
insert into position (id, name) values (22, 'Quality analysis');

-- Create sequence for id generator
drop sequence id_generator;
create sequence id_generator;
