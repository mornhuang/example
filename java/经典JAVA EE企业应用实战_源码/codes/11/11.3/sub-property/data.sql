drop database javaee;
create database javaee;
use javaee;
create table news_table
(
 id int primary key auto_increment,
 news_title varchar(50),
 content varchar(255)
);

insert into news_table
values
(null , '疯狂Java联盟' , '疯狂Java联盟成立了。'),
(null , 'Java6出来了' , 'Java6出来了，性能提升了不少。'),
(null , '天亮了' , '等到那一天，天亮了，空气中酝酿着自由的芬芳'),
(null , '新世界' , '有一个美丽的新世界，它在远方等我。那里有天真的孩子，还有姑娘的酒窝。');
