---------------------------------------------------
------table=CS_PARAMETER
---------------------------------------------------
update CS_PARAMETER set KEY_PK='NWEB.ChannelID', VALUE='NWEB01', DESCRIPTION='NWEB.ChannelID' where KEY_PK='WEB.ChannelID';
update CS_PARAMETER set KEY_PK='NWEB.ViewProvider', DESCRIPTION='NWEB.ViewProvider' where KEY_PK='WEB.ViewProvider';
update CS_PARAMETER set KEY_PK='NPS', DESCRIPTION='new stt Channel' where KEY_PK='STT';
update CS_PARAMETER set KEY_PK='NWEB', DESCRIPTION='new web Channel' where KEY_PK='WEB';
delete CS_PARAMETER where KEY_PK in ('AAS_URL', 'emc_url', 'emc_index_url');
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('AAS_URL','https://stt.htisec.com:18443/aa/index.do','0','Character',0,'AAS_URL',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('emc_url','http://10.100.53.74/emc/auth',0,'Character',0,'EMC_URL',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('emc_index_url','http://10.100.53.74/emc/inbox',0,'Character',0,'EMC_INDEX_URL',sysdate);

---------------------------------------------------
------table=acl_role_fnctn_prmisn
---------------------------------------------------
delete acl_role_fnctn_prmisn where ACL_ROLE_FNCTN_PRMISN_ID='129479671411518';
insert into acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE) values ('129479671411518', 'admin', 'Reset_Password_menu', 'A', 'system', null, 'system', sysdate);
delete acl_role_fnctn_prmisn where ACL_ROLE_FNCTN_PRMISN_ID='129479671411522';
insert into acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411522', 'admin', 'Day_End_Process_menu', 'A', 'system', null, 'system', sysdate);

---------------------------------------------------
------table=prod
---------------------------------------------------
truncate table prod;
insert into PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_AAST','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','NONE',180,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'ADMINS',sysdate,'');
insert into PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_AAST_CN','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','PRC',180,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'ADMINS',sysdate,'');
insert into PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_IQS_CN','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','PRC',180,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'ADMINS',sysdate,'');
insert into PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SHK','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','PRC',180,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'ADMINS',sysdate,'');
insert into PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('NO_EMAIL','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','NONE',180,to_date('1970-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss'),to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'ADMINS',sysdate,'');
insert into PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_IQS','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','NONE',180,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'ADMINS',sysdate,'ADMINS');

---------------------------------------------------
------table=svcs_aces_log
---------------------------------------------------
alter table svcs_aces_log modify(aces_url varchar2(200));

---------------------------------------------------
------table=usr_prof
---------------------------------------------------
alter table usr_prof drop column clnt_login_id;
alter table usr_prof add(clnt_login_id varchar2(20));
update usr_prof set clnt_login_id=clnt_id;