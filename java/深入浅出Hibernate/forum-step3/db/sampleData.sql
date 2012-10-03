delete from vote_option;
delete from vote;
delete from article;
delete from board;
delete from user;

insert into user values (1,'shark','guessme');

insert into board (id,create_by,name) values (1,1,'人文');
insert into board (id,parent_id,create_by,name) values (2,1,1,'南冥天池');
insert into board (id,parent_id,create_by,name) values (3,1,1,'围炉夜话');

insert into board (id,create_by,name) values (4,1,'计算机技术');
insert into board (id,parent_id,create_by,name) values (5,4,1,'弹指神通');
insert into board (id,parent_id,create_by,name) values (6,4,1,'IT人');

insert into article (id,board_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(1,1,'Hibernate 3.0已经推出','详情:http://www.hibernate.org',1,1,'0001',1,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(2,1,1,'v3.0.1','实例',1,1,'0001.0001',2,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(3,1,1,'v3.0.2','实例',1,1,'0001.0002',2,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(4,1,1,'v3.0.3','实例',1,1,'0001.0002',2,1);
insert into article (id,board_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(5,1,'Hibernate v3.0.4开始，附带v3系列中文文档','由www.redsaga.com满江红团队完成',1,1,'0002',1,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(6,1,5,'测试1','xxxx',1,1,'0002.0001',2,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(7,1,5,'测试2','xxxx',1,1,'0002.0002',2,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(8,1,5,'测试3','xxxx',1,1,'0002.0003',2,1);
insert into article (id,board_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(9,1,'除了Hibernate 外，也有其他ORM产品','xxxx',1,1,'0003',1,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(10,1,9,'OJB','xxxx',1,1,'0003.0001',2,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(11,1,9,'iBatis','xxxx',1,1,'0003.0002',2,1);
insert into article (id,board_id,parent_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(12,1,9,'TopLink','xxxx',1,1,'0003.0003',2,1);
insert into article (id,board_id,title,body,create_by,last_update_by,tree_index,node_level,root_id) values(13,1,'你喜欢晴天还是雨天?','小调查',1,1,'',1,1);
insert into vote(id,count) values (13,0);
insert into vote_option( option_id, poll_id ,option_text, agree_number ) values (1,13,'晴天',2);
insert into vote_option( option_id, poll_id ,option_text, agree_number ) values (2,13,'雨天',1);

update article set bytes = length(body);
