drop database hibernate;
create database hibernate;
use hibernate;
create table news_table
(
id int auto_increment primary key,
title varchar(255) not null,
content varchar(255),
full_content varchar(255)
);
DELIMITER |
create trigger t_full_content_gen BEFORE INSERT ON news_table
	FOR EACH ROW BEGIN
		set new.full_content=concat(new.title,new.content);
	END;
|
DELIMITER ;