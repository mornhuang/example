update prod set prod_status='UNAVAIL' where prod_id='SSTR_IQS_CN';

delete CS_PARAMETER where KEY_PK in ('FUNDTRANSFER_MIN_LIMIT', 'FUNDTRANSFER_MAX_LIMIT', 'AGENT_PCCLIENT_EN_URL', 'MisDayEndProcessingFlag', 'afxnewsBIG5', 'afxnewsByStockCodeBIG5', 'afxnewsByStockCodeEN', 'afxnewsByStockCodeGB', 'afxnewsEN', 'afxnewsGB', 'PAGESIZE', 'PSRTQPRODUCT')
		or KEY_PK like 'BIG5_WEB_%' or KEY_PK like 'ENG_WEB_%' or KEY_PK like 'GB_WEB_%' or KEY_PK like 'webAfxnews%';
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('FUNDTRANSFER_MIN_LIMIT','1','5','Character',0,'FUNDTRANSFER_MIN_LIMIT',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('FUNDTRANSFER_MAX_LIMIT','1000000000','5','Character',0,'FUNDTRANSFER_MAX_LIMIT',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('MisDayEndProcessingFlag','N','5','Character',0,'MIS日结标志，值有：Y/N，admin端做自动续期、购买和memo debit；trade端客户购买时都需要用到。MIS在做日结时，再做自动续期等操作，需要管理员手动将其值设为Y',sysdate);
update CS_PARAMETER set value = 'http://10.101.15.184:1080/emc/inbox' where key_pk = 'emc_index_url';
update CS_PARAMETER set value = 'http://10.101.15.184:1080/emc/auth' where key_pk = 'emc_url';
update CS_PARAMETER set CLASSID = '5';

delete acl_role_fnctn_prmisn where ACL_ROLE_FNCTN_PRMISN_ID='129479671411522' or fnctn_id like 'Day_End_Process%';
insert into acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411522', 'admin', 'Day_End_Process_menu', 'A', 'system', null, 'system', sysdate);