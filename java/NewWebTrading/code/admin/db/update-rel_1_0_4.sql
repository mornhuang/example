truncate table AUTO_PURC;
drop table AUTO_PURC cascade constraints;
create table AUTO_PURC  (
   AUTO_PURC_ID         VARCHAR2(32)          not null,
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   STATUS               VARCHAR2(20),
   PRICE_IN_HKD         NUMBER(12),			
   DEBT_REMARKS         VARCHAR2(1000),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index AUTO_PURC_PK on  AUTO_PURC (AUTO_PURC_ID) tablespace nwebidx01;
Alter table AUTO_PURC add constraint AUTO_PURC_PK primary key (AUTO_PURC_ID);

delete acl_role_fnctn_prmisn where ACL_ROLE_FNCTN_PRMISN_ID='129479671411523';
insert into acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411523', 'admin', 'Auto_Purchase_Report_menu', 'A', 'system', null, 'system', sysdate);

delete CS_PARAMETER where KEY_PK in ('BIG5_WEB_OpenAccountUrl', 'BIG5_WEB_TradeServiceUrl', 'BIG5_WEB_SimTrade', 'GB_WEB_OpenAccountUrl', 'GB_WEB_TradeServiceUrl', 'GB_WEB_SimTrade', 'ENG_WEB_OpenAccountUrl', 'ENG_WEB_TradeServiceUrl', 'ENG_WEB_SimTrade');
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_WEB_OpenAccountUrl','http://marry-zhang:8080/htisec/jsp/zh-HK/Account-Open-Method.jsp','5','Character',0,'开设户口',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_WEB_TradeServiceUrl','http://marry-zhang:8080/htisec/jsp/zh-HK/Service-Model.jsp','5','Character',0,'交易服务示范',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_WEB_SimTrade','#','5','Character',0,'模拟网上交易',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_WEB_OpenAccountUrl','http://marry-zhang:8080/htisec/jsp/zh-CN/Account-Open-Method.jsp','5','Character',0,'开设户口',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_WEB_TradeServiceUrl','http://marry-zhang:8080/htisec/jsp/zh-CN/Service-Model.jsp','5','Character',0,'交易服务示范',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_WEB_SimTrade','#','5','Character',0,'模拟网上交易',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_WEB_OpenAccountUrl','http://marry-zhang:8080/htisec/jsp/en/Account-Open-Method.jsp','5','Character',0,'开设户口',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_WEB_TradeServiceUrl','http://marry-zhang:8080/htisec/jsp/en/Service-Model.jsp','5','Character',0,'交易服务示范',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_WEB_SimTrade','#','5','Character',0,'模拟网上交易',sysdate);

grant select,update,delete,insert,debug on AUTO_PURC  to nwebaccess;
grant select on AUTO_PURC  to nwebselect;
create synonym nweb.AUTO_PURC for nwebapp.AUTO_PURC;
create synonym nwebpatch.AUTO_PURC for nwebapp.AUTO_PURC;
create synonym nwebqry.AUTO_PURC for nwebapp.AUTO_PURC;