drop database if exists hibernate;

create database hibernate;

use hibernate;

CREATE TABLE course_inf (
  course_code varchar(255) NOT NULL,
  name varchar(255) default NULL,
  PRIMARY KEY  (course_code)
);

INSERT INTO course_inf VALUES 
('001','疯狂Java讲义'),
('002','轻量级Java EE企业应用实战'),
('003','疯狂Ajax讲义');

CREATE TABLE student_inf (
  student_id int NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY  (student_id)
);

INSERT INTO student_inf VALUES
(20050231,'孙悟空'),
(20050232,'猪八戒'),
(20050233,'牛魔王');



CREATE TABLE enrolment_inf (
  enrolment_id int(11) NOT NULL auto_increment,
  semester int(11) NOT NULL,
  year int(11) NOT NULL,
  student_id int default NULL,
  course_code varchar(255) default NULL,
  PRIMARY KEY  (enrolment_id),
  KEY FKEB4882C47EDE09EE (course_code),
  KEY FKEB4882C4109881D8 (student_id),
  FOREIGN KEY (student_id) REFERENCES student_inf (student_id),
  FOREIGN KEY (course_code) REFERENCES course_inf (course_code)
);

INSERT INTO enrolment_inf VALUES
(1,3,2005,20050232,'001'),
(2,2,2005,20050232,'003'),
(3,2,2005,20050233,'002'),
(4,3,2005,20050233,'003'),
(5,1,2005,20050231,'002');


-- 创建一个简单的存储过程
create procedure select_all_student()
select *
from student_inf;

