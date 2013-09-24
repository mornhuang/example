drop database hibernate;

create database hibernate;

use hibernate;

create table news_inf
(
 news_id int primary key auto_increment,
 title varchar(255),
 content varchar(255)
);

insert into news_inf
values(null , '疯狂Java联盟' , '疯狂Java联盟成立了，网址是www.crazyit.org');

insert into news_inf
values(null , '天快亮了' , '等到那一天，四周一下光亮了，空气中酝酿着自由、民主的芬芳！');

