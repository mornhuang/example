drop database if exists hibernate;

create database hibernate;

use hibernate;

CREATE TABLE event_inf (
  event_id int(11) NOT NULL auto_increment,
  happenDate date default NULL,
  title varchar(255) default NULL,
  PRIMARY KEY  (event_id)
);

INSERT INTO event_inf VALUES
(1,'2004-10-03','很高兴的事情'),
(2,'2005-10-03','很普通事情'),
(3,'2004-10-04','疯狂Java筹备中'),
(4,'2005-10-05','疯狂Java开始培训');

CREATE TABLE person_inf (
  person_id int(11) NOT NULL auto_increment,
  name varchar(255) default NULL,
  age int(11) default NULL,
  PRIMARY KEY  (person_id)
);


INSERT INTO person_inf VALUES
(1,'yeeku',30),
(2,'老朱',30);


CREATE TABLE person_email (
  person_id int(11) NOT NULL,
  email varchar(255) default NULL,
  KEY FKECD3B632CC53FFDC (person_id),
  FOREIGN KEY (person_id) REFERENCES person_inf (person_id)
);

INSERT INTO person_email VALUES 
(1,'kongyeeku@apache.org'),
(1,'kongyeeku@mysql.com'),
(2,'dddd@163.com'),
(2,'vvvvvv@163.com');

CREATE TABLE person_event (
  person_id int(11) NOT NULL,
  event_id int(11) NOT NULL,
  PRIMARY KEY  (person_id,event_id),
  KEY FKECD7DD30273C5F2C (event_id),
  KEY FKECD7DD30CC53FFDC (person_id),
  FOREIGN KEY (person_id) REFERENCES person_inf (person_id),
  FOREIGN KEY (event_id) REFERENCES event_inf (event_id)
);


INSERT INTO person_event VALUES
(1,1),
(1,2),
(2,2),
(2,3),
(1,4);

