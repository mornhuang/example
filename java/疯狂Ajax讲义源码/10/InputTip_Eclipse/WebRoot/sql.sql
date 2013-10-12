drop database if exists tips;
create database tips;
use tips;

create table brand
(b_id integer auto_increment,
 b_name varchar(255) not null unique,
 primary key(b_id));

create table model
(m_id integer auto_increment,
 m_name varchar(255) not null unique,
 m_desc text,
 b_id integer,
 primary key(m_id),
 foreign key(b_id) references brand(b_id));

insert into brand(b_name) values('IBM');
insert into brand(b_name) values('DELL');
insert into brand(b_name) values('SONY');
insert into brand(b_name) values('TOSHIBA');
insert into brand(b_name) values('SAMSUNG');

#IBM
insert into model(m_name , m_desc , b_id)
values('T60 2007DT2' , '处理器:Intel Core Duo(Yonah) T2600(2.16G)<br>内存容量:1024M<br>硬盘容量:100G<br>光驱类型:内置,DVD±RW<br>屏幕尺寸:14.1寸<BR>显示屏类型:SXGA+<br>显示芯片:独立,ATI Mobility Radeon X1400<br>重量:约2.32Kg<br>操作系统:Windows XP Professional' , 1);
insert into model(m_name , m_desc , b_id)
values('T60p 20079EC' , '处理器:Intel Core Duo(Yonah) T2700(2.33G)<br>内存容量:1024M<br>硬盘容量:100G<br>光驱类型:内置,Rambo<br>屏幕尺寸:15.1寸<br>显示屏类型:UXGA+<br>显示芯片:独立,ATI Mobility FireGL V5200<br>重量:约2.81Kg<br>操作系统:Windows XP Professional' , 1);
insert into model(m_name , m_desc , b_id)
values('T60p 200783C ' , '处理器:Intel Core Duo(Yonah) T2600(2.16G)<br>内存容量:1024M<br>硬盘容量:100G<br>光驱类型:内置,Rambo<br>屏幕尺寸:14.1寸<br>显示屏类型:SXGA+<br>显示芯片:独立,ATI Mobility FireGL V5200<br>重量:约2.49Kg<br>操作系统:Windows XP Professional' , 1);
#DELL
insert into model(m_name , m_desc , b_id)
values('戴尔 Inspiron 9400(N511010)' , '处理器:Intel Core2 Duo(Merom) T5600(1.83G)<br>内存容量:1024M<br>硬盘容量:120G<br>光驱类型:内置,DVD±RW<br>屏幕尺寸:17寸<br>显示屏类型:WXGA+<br>显示芯片:独立,ATI Mobility Radeon X1400<br>重量:约3.49Kg<br>操作系统:Windows XP Home' , 2);
insert into model(m_name , m_desc , b_id)
values('戴尔 Inspiron 9400(N511009)' , '处理器:Intel Core2 Duo(Merom) T5600(1.83G)<br>内存容量:1024M<br>硬盘容量:120G<br>光驱类型:内置,Combo<br>屏幕尺寸:17寸<br>显示屏类型:WXGA+<br>显示芯片:集成Intel GMA950芯片<br>重量:约3.49Kg<br>操作系统:Windows XP Home' , 2);
insert into model(m_name , m_desc , b_id)
values('戴尔 XPS M1710(N511014)' , '处理器:Intel Core2 Duo(Merom) T7200(2.0G)<br>内存容量:1024M<br>硬盘容量:120G<br>光驱类型:内置,DVD±RW<br>屏幕尺寸:17寸<br>显示屏类型:UXGA+<br>显示芯片:独立,nVidia Geforce Go7900GTX<br>重量:约4.0Kg<br>操作系统:Windows XP Home' , 2);
#SONY
insert into model(m_name , m_desc , b_id)
values('索尼 VGN-UX18C' , '处理器:Intel Core Solo(Yonah) U1400(1.2G)<br>内存容量:512M<br>硬盘容量:闪存16G<br>光驱类型:无光驱<br>屏幕尺寸:4.5寸<br>显示屏类型:WSVGA<br>显示芯片:集成Intel GMA950芯片<br>重量:约0.480Kg<br>操作系统:Windows XP Home' , 3);
insert into model(m_name , m_desc , b_id)
values('索尼 VGN-AR18CP' , '处理器:Intel Core Duo(Yonah) T2600(2.16G)<br>内存容量:2048M<br>硬盘容量:160G<br>光驱类型:内置,蓝光刻录光驱<br>屏幕尺寸:17寸<br>显示屏类型:WUXGA<br>显示芯片:独立,nVidia Geforce Go7600GT<br>重量:约3.8Kg<br>操作系统:Windows XP Professional' , 3);
insert into model(m_name , m_desc , b_id)
values('索尼 VGN-UX17C' , '处理器:Intel Celeron M 423(1.06G)<br>内存容量:512M<br>硬盘容量:30G<br>光驱类型:无光驱<br>屏幕尺寸:4.5寸<br>显示屏类型:WSVGA<br>显示芯片:集成Intel GMA950芯片<br>重量:约0.517Kg<br>操作系统:Windows XP Home' , 3);
#TOSHIBA
insert into model(m_name , m_desc , b_id)
values('东芝 Qosmio F30 PQF30Q-01U012' , '处理器:Intel Core Duo(Yonah) T2400(1.83G)<br>内存容量:512M<br>硬盘容量:100G<br>光驱类型:内置,DVD-SuperMulti<br>屏幕尺寸:15.4寸<br>显示屏类型:WXGA<br>显示芯片:独立nVidia Geforce Go7600<br>电视模块:内置电视调谐器<br>重量:约4.3Kg<br>操作系统:Windows XP Media Center' , 4);
insert into model(m_name , m_desc , b_id)
values('东芝 Qosmio E10 PQE10Q-009008' , '处理器:Intel Pentium M(Dothan) 735(1.7G)<br>内存容量:512M<br>硬盘容量:60G<br>光驱类型:DVD-SuperMulit<br>屏幕尺寸:15寸<br>显示屏类型:XGA<br>显示芯片:独立,nVidia Geforce 5200Go<br>重量:约3.7Kg' , 4);
insert into model(m_name , m_desc , b_id)
values('东芝 Qosmio E10 PQE10Q-015008' , '处理器:Intel pentium M(Dothan) 745(1.8G)<br>内存容量:768M<br>硬盘容量:80G<br>光驱类型:DVD±RW<br>屏幕尺寸:15寸<br>显示屏类型:XGA<br>显示芯片:独立,nVidia Geforce 5200Go<br>电视模块:内置电视模块<br>重量:约3.7Kg' , 4);
#SAMSUNG
insert into model(m_name , m_desc , b_id)
values('三星 X11-CV0F' , '处理器:Intel Core2 Duo(Merom) T5600(1.83G)<br>内存容量:512M<br>硬盘容量:100G<br>光驱类型:内置,DVD-SuperMulti<br>屏幕尺寸:14寸<br>显示屏类型:WXGA<br>显示芯片:独立,nVidia Geforce Go7400<br>重量:约2.2Kg<br>操作系统:Windows XP Home' , 5);
insert into model(m_name , m_desc , b_id)
values('三星 X11-CV0B' , '处理器:Intel Core2 Duo(Merom) T7200(2.0G)<br>内存容量:1024M<br>硬盘容量:100G<br>光驱类型:内置,DVD-SuperMulti<br>屏幕尺寸:14寸<br>显示屏类型:WXGA<br>显示芯片:独立,nVidia Geforce Go7400<br>重量:约2.2Kg<br>操作系统:Windows XP Home' , 5);
insert into model(m_name , m_desc , b_id)
values('三星 X11-CV0G' , '处理器:Intel Core2 Duo(Merom) T5500(1.66G)<br>内存容量:512M<br>硬盘容量:100G<br>光驱类型:内置,DVD-SuperMulti<br>屏幕尺寸:14寸<br>显示屏类型:WXGA<br>显示芯片:独立,nVidia Geforce Go7400<br>重量:约2.2Kg<br>操作系统:Windows XP Home' , 5);
