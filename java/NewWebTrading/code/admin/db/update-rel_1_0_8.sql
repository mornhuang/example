update NW_RTQ_APP set RTQ_URL='https://secure.aastocks.com/pkages/broker/login_broker/auto_all.asp' where PROD_ID in ('SSTR_AAST', 'SSTR_AAST_CN');
update NW_RTQ_APP set RTQ_URL='https://iq6.etnet.com.hk/HttpServer/jsp/IQ_Web/Login.jsp' where PROD_ID in ('SSTR_IQS');

delete from nw_acl_role_fnctn_prmisn where ACL_ROLE_FNCTN_PRMISN_ID='129479671411524';
insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411524', 'admin', 'RTQ_Memo_Debit_menu', 'A', 'system', null, 'system', sysdate);


delete nw_cs_parameter where key_pk in ('pagesize', 'NewETNetDemo', 'NewETNetRequest');
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('pagesize','20','5','Character',0,'pagesize for adminportal',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('NewETNetDemo','1','5','Character',0,'1 开发测试阶段, 0 生产阶段',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('NewETNetRequest','login','5','Character',0,'NewETNetRequest',sysdate);
