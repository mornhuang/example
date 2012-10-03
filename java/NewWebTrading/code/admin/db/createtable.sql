create sequence NW_APPL_ID
increment by 1
start with 30120
 maxvalue 999999999999999
 minvalue 30000
nocycle
noorder;

create sequence NW_BROADCAST_ID_SEQ
increment by 1
start with 1
 maxvalue 99999999999999999999
 nominvalue
nocycle
order;

create sequence NW_COMMENTARY_ID_SEQ
increment by 1
start with 1
 maxvalue 99999999999999999999
 nominvalue
nocycle
order;

create sequence NW_CS_BROADCAST_SEQNO
increment by 1
start with 301
 nomaxvalue
 nominvalue
nocycle
noorder;

create sequence NW_CS_SERVICEMONITOR_SEQNO
increment by 1
start with 1
 nomaxvalue
 nominvalue
nocycle
noorder;

create sequence NW_CS_USERACTIONLOG_SEQNO
increment by 1
start with 47121
 nomaxvalue
 nominvalue
nocycle
noorder;

create sequence NW_SERVICE_ID_SEQ
increment by 1
start with 1
 maxvalue 99999999999999999999
 nominvalue
nocycle
order;

create sequence NW_SEQ_FUND_DEPOSIT
increment by 1
start with 1
 maxvalue 1000000000000000000000000000
 minvalue 1
nocycle
order;

create table NW_FUND_DEPOSIT (
    RECEIVE_DATE         TIMESTAMP(6), 
	FUND_DEPOSIT_ID      NUMBER(18), 
	DEPOSIT_DATE         TIMESTAMP(6), 
	ACCOUNT_NAME         VARCHAR2(70), 
	ACCOUNT_NO           VARCHAR2(20), 
	CURRENCY             VARCHAR2(8), 
	AMOUNT               NUMBER(18), 
	BANK                 VARCHAR2(20), 
	BANK_ACC             VARCHAR2(20), 
	SOURCE               VARCHAR2(40), 
	REFERENCE            VARCHAR2(40), 
	REMARKS              VARCHAR2(1000), 
	ISSENDED             VARCHAR2(1),
	REQUESTNO            VARCHAR2(30)
)tablespace nwebtbs01;
Create unique index NW_FUND_DEPOSIT_PK on  NW_FUND_DEPOSIT (FUND_DEPOSIT_ID) tablespace nwebidx01;
Create index NW_FUND_DEPOSIT_IE1 on  NW_FUND_DEPOSIT (RECEIVE_DATE) tablespace nwebidx01;
Alter table NW_FUND_DEPOSIT add constraint NW_FUND_DEPOSIT_PK primary key (FUND_DEPOSIT_ID);

create table NW_ACL_FUNC  (
   FUNC_ID              VARCHAR2(50)                    not null,
   FUNC_NME             VARCHAR2(200),
   FUNC_TYP             VARCHAR2(2),
   URL_LINK             VARCHAR2(200),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01; 
Create unique index NW_ACL_FUNC_PK on  NW_ACL_FUNC (FUNC_ID) tablespace nwebidx01;
Create  index NW_ACL_FUNC_IE1 on  NW_ACL_FUNC (FUNC_NME) tablespace nwebidx01;
Create  index NW_ACL_FUNC_IE2 on  NW_ACL_FUNC (FUNC_TYP) tablespace nwebidx01;
Alter table NW_ACL_FUNC add constraint NW_ACL_FUNC_PK primary key (FUNC_ID);


create table NW_ACL_PRMSN  (
   PRMISN_CODE          VARCHAR2(10)                    not null,
   PRMISN_NME           VARCHAR2(50),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_ACL_PRMSN_PK on  NW_ACL_PRMSN (PRMISN_CODE) tablespace nwebidx01;
Create index NW_NW_ACL_PRMSN_IE1 on  NW_ACL_PRMSN (PRMISN_NME) tablespace nwebidx01;
Alter table NW_ACL_PRMSN add constraint NW_ACL_PRMSN_PK primary key (PRMISN_CODE);


create table NW_ACL_ROLE  (
   ROLE_ID              VARCHAR2(32)                    not null,
   DESCR                VARCHAR2(100),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01; 
Create unique index NW_ACL_ROLE_PK on  NW_ACL_ROLE (ROLE_ID) tablespace nwebidx01;
Alter table NW_ACL_ROLE add constraint NW_ACL_ROLE_PK primary key (ROLE_ID);
   

create table NW_ACL_ROLE_FNCTN_PRMISN  (
   ACL_ROLE_FNCTN_PRMISN_ID VARCHAR2(32)                    not null,
   ROLE_ID              VARCHAR2(32),
   FNCTN_ID             VARCHAR2(50),
   PRMISN_CDE           VARCHAR2(10),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;    
Create unique index NW_ACL_ROLE_FNCTN_PRMISN_PK on  NW_ACL_ROLE_FNCTN_PRMISN (ACL_ROLE_FNCTN_PRMISN_ID) tablespace nwebidx01;
Create index NW_ACL_ROLE_FNCTN_PRMISN_IE1 on  NW_ACL_ROLE_FNCTN_PRMISN (ROLE_ID) tablespace nwebidx01;
Alter table NW_ACL_ROLE_FNCTN_PRMISN add constraint NW_ACL_ROLE_FNCTN_PRMISN_PK primary key (ACL_ROLE_FNCTN_PRMISN_ID);     

create table NW_ACL_USR_PROF  (
   LOGN_ID              VARCHAR2(32)                    not null,
   ROLE_ID              VARCHAR2(32),
   DEPT_NME             VARCHAR2(50),
   USR_NME              VARCHAR2(20),
   PWD                  VARCHAR2(20),
   EMAIL_ADDR           VARCHAR2(100),
   STATUS               VARCHAR2(10),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_ACL_USR_PROF_PK on  NW_ACL_USR_PROF (LOGN_ID) tablespace nwebidx01;
Create index NW_ACL_USR_PROF_IE1 on  NW_ACL_USR_PROF (ROLE_ID) tablespace nwebidx01;
Alter table NW_ACL_USR_PROF add constraint NW_ACL_USR_PROF_PK primary key (LOGN_ID);    


create table NW_BROADCAST  (
   BROADCAST_ID         NUMBER(18)                      not null,
   BROADCAST_DATETIME   TIMESTAMP                       not null,
   MSG                  CLOB,
   LANG                 VARCHAR2(10)                    not null,
   VALID_STATUS         VARCHAR2(1),
   PERIOD               VARCHAR2(1),
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01
LOB ( MSG ) STORE AS (tablespace nweblobtbs01);
Create unique index NW_BROADCAST_PK on  NW_BROADCAST (BROADCAST_ID) tablespace nwebidx01;
Alter table NW_BROADCAST add constraint NW_BROADCAST_PK primary key (BROADCAST_ID);   


create table NW_CHL_SYS_PARA  (
   PARA_CODE            NUMBER(22)                      not null,
   PARA_VALUE           VARCHAR2(30),
   PARA_DESC            VARCHAR2(300)
)tablespace nwebtbs01;
Create unique index NW_CHL_SYS_PARA_PK on  NW_CHL_SYS_PARA (PARA_CODE) tablespace nwebidx01;
Alter table NW_CHL_SYS_PARA add constraint NW_CHL_SYS_PARA_PK primary key (PARA_CODE);   
 

create table NW_CHL_SYS_PARA_HIS  (
   PARA_CODE            NUMBER(22),
   PARA_VALUE           VARCHAR2(30),
   PARA_DESC            VARCHAR2(300),
   INTO_DATE            DATE
)tablespace nwebtbs01;


create table NW_COMMENTARY  (
   COMMENTARY_ID        NUMBER(18)                      not null,
   COMMENTARY_DATETIME  TIMESTAMP                       not null,
   TITLE                VARCHAR2(100),
   CONTENT              CLOB,
   LANG                 VARCHAR2(10)                    not null,
   CATG_ID              VARCHAR2(10),
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01
LOB ( CONTENT) STORE AS (tablespace nweblobtbs01);
Create unique index NW_COMMENTARY_PK on  NW_COMMENTARY (COMMENTARY_ID) tablespace nwebidx01;
Alter table NW_COMMENTARY add constraint NW_COMMENTARY_PK primary key (COMMENTARY_ID);  


create table NW_CS_BROADCAST  (
   SEQNO_PK             NUMBER(19)                      not null,
   STARTTIME            TIMESTAMP                       not null,
   ENDTIME              TIMESTAMP                       not null,
   BROADCAST_LEVEL      VARCHAR2(40)                    not null,
   TYPE                 VARCHAR2(40)                    not null,
   CHANNELS             VARCHAR2(255)                   not null,
   CONTENT_EN_US        VARCHAR2(255),
   CONTENT_ZH_CN        VARCHAR2(255),
   CONTENT_ZH_TW        VARCHAR2(255),
   LASTUPDATETIME       TIMESTAMP
)tablespace nwebtbs01;
Create unique index NW_CS_BROADCAST_PK on  NW_CS_BROADCAST (SEQNO_PK) tablespace nwebidx01;
Alter table NW_CS_BROADCAST add constraint NW_CS_BROADCAST_PK primary key (SEQNO_PK);  
 

create table NW_CS_ONLINEUSER  (
   CHANNELCODE_PK       VARCHAR2(255)                   not null,
   USERID_PK            VARCHAR2(40)                    not null,
   SESSIONID            VARCHAR2(255),
   LOGINTIME            TIMESTAMP
)tablespace nwebtbs01;
Create unique index NW_CS_ONLINEUSER_PK on  NW_CS_ONLINEUSER (CHANNELCODE_PK,USERID_PK) tablespace nwebidx01;
Alter table NW_CS_ONLINEUSER add constraint NW_CS_ONLINEUSER_PK primary key (CHANNELCODE_PK,USERID_PK); 


create table NW_CS_PARAMETER  (
   KEY_PK               VARCHAR2(100)                   not null,
   VALUE                VARCHAR2(3200)                  not null,
   CLASSID              VARCHAR2(10)                    not null,
   DATATYPE             VARCHAR2(20)                   default 'Character' not null,
   READONLY             NUMBER(1)                      default 0 not null,
   DESCRIPTION          VARCHAR2(255),
   UPDATE_TIME          TIMESTAMP
)tablespace nwebtbs01;
Create unique index NW_CS_PARAMETER_PK on  NW_CS_PARAMETER (KEY_PK) tablespace nwebidx01;
Alter table NW_CS_PARAMETER add constraint NW_CS_PARAMETER_PK primary key (KEY_PK); 


create table NW_CS_SERVICEMONITOR  (
   SEQNO_PK             NUMBER(19)                      not null,
   SERVICENAME          VARCHAR2(40)                    not null,
   STATUS               VARCHAR2(20)                    not null,
   ACTION_ID            VARCHAR2(40)                    not null,
   IP                   VARCHAR2(15)                    not null,
   ACCESSTIME           TIMESTAMP                       not null,
   EXPENDTIME           NUMBER(19)                      not null
)tablespace nwebtbs01;
Create unique index NW_CS_SERVICEMONITOR_PK on  NW_CS_SERVICEMONITOR (SEQNO_PK) tablespace nwebidx01;
Alter table NW_CS_SERVICEMONITOR add constraint NW_CS_SERVICEMONITOR_PK primary key (SEQNO_PK); 


create table NW_CS_SET_PARAMETER  (
   PARAM_NAME           VARCHAR2(30)                    not null,
   PARAM_VALUE          VARCHAR2(250)                   not null,
   DESCRIPTION          VARCHAR2(250),
   UPDATE_TIME          TIMESTAMP
)tablespace nwebtbs01;
Create unique index NW_CS_SET_PARAMETER_PK on  NW_CS_SET_PARAMETER (PARAM_NAME, PARAM_VALUE) tablespace nwebidx01;
Alter table NW_CS_SET_PARAMETER add constraint NW_CS_SET_PARAMETER_PK primary key (PARAM_NAME, PARAM_VALUE); 


create table NW_CS_USERACTIONLOG  (
   SEQNO_PK             NUMBER(19)                      not null,
   USERID               VARCHAR2(40)                    not null,
   ACTIONID             VARCHAR2(40)                    not null,
   CHANNELTYPE          VARCHAR2(20),
   IP                   VARCHAR2(15)                    not null,
   OPERATIONTIME        TIMESTAMP
)tablespace nwebtbs01;
Create unique index NW_CS_USERACTIONLOG_PK on  NW_CS_USERACTIONLOG (SEQNO_PK) tablespace nwebidx01;
Alter table NW_CS_USERACTIONLOG add constraint NW_CS_USERACTIONLOG_PK primary key (SEQNO_PK); 


create table NW_DEFT_DEBT_ACC_CHNG_LOG  (
   DEF_DEBT_ACC_CHG_LOG_ID VARCHAR2(32)                    not null,
   CLNT_ID              VARCHAR2(20),
   DEF_DEBT_ACC         VARCHAR2(20),
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20),
   ACTN_TYPE            VARCHAR2(10),
   ACTN_DATE            DATE,
   ACTN_BY              VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_DEFT_DEBT_ACC_CHNG_LOG_PK on  NW_DEFT_DEBT_ACC_CHNG_LOG (DEF_DEBT_ACC_CHG_LOG_ID) tablespace nwebidx01;
Create  index NW_DEFT_DEBT_ACC_CHNG_LOG_IE1 on  NW_DEFT_DEBT_ACC_CHNG_LOG (CLNT_ID) tablespace nwebidx01;
Create  index NW_DEFT_DEBT_ACC_CHNG_LOG_IE2 on  NW_DEFT_DEBT_ACC_CHNG_LOG (DEF_DEBT_ACC) tablespace nwebidx01;
Alter table NW_DEFT_DEBT_ACC_CHNG_LOG add constraint NW_DEFT_DEBT_ACC_CHNG_LOG_PK primary key (DEF_DEBT_ACC_CHG_LOG_ID); 


create table NW_NOTF_MDIA  (
   NOTF_MDIA_ID         VARCHAR2(32)                    not null,
   CLNT_ID              VARCHAR2(20),
   NOTF_CHNNL           VARCHAR2(20),
   NOTF_TYPE            VARCHAR2(10),
   PREF_LANG            VARCHAR2(20),
   EMAIL_ADDR           VARCHAR2(40),
   PHONE_NUM            VARCHAR2(20),
   SMS_PRDR             VARCHAR2(40),
   SMS_REGN_CODE        VARCHAR2(10),
   SMS_CNTRY_CODE       VARCHAR2(10),
   ORD_TYPE             VARCHAR2(20),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_NOTF_MDIA_PK on  NW_NOTF_MDIA (NOTF_MDIA_ID) tablespace nwebidx01;
Create index NW_NOTF_MDIA_IE1 on  NW_NOTF_MDIA (CLNT_ID) tablespace nwebidx01;
Create index NW_NOTF_MDIA_IE2 on  NW_NOTF_MDIA (NOTF_CHNNL) tablespace nwebidx01;
Create index NW_NOTF_MDIA_IE3 on  NW_NOTF_MDIA (NOTF_TYPE) tablespace nwebidx01;
Alter table NW_NOTF_MDIA add constraint NW_NOTF_MDIA_PK primary key (NOTF_MDIA_ID); 


create table NW_PROD  (
   PROD_ID              VARCHAR2(32)                    not null,
   PROD_STATUS          VARCHAR2(10),
   QUOTA                NUMBER(12),
   ALLT_QUOTA           NUMBER(12),
   SVC_MODE             VARCHAR2(10),
   VALT_OF_SVC          VARCHAR2(15),
   ACES_UNIT            NUMBER(12),
   BILL_TYPE            VARCHAR2(4),
   DISC_TYPE            VARCHAR2(4),
   PRICE_IN_HKD         NUMBER(12),
   EFF_DATE             DATE,
   EXPR_DATE            DATE,
   REMARKS              VARCHAR2(1000),
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_PROD_PK on  NW_PROD (PROD_ID) tablespace nwebidx01;
Alter table NW_PROD add constraint NW_PROD_PK primary key (PROD_ID); 


create table NW_PROD_CHG_LOG  (
   PROD_CHG_LOG_ID      VARCHAR2(32)                    not null,
   PROD_ID              VARCHAR2(32),
   PROD_STATUS          VARCHAR2(10),
   QUOTA                NUMBER(12),
   ALLT_QUOTA           NUMBER(12),
   SVC_MODE             VARCHAR2(10),
   VALT_OF_SVC          VARCHAR2(15),
   ACES_UNIT            NUMBER(12),
   BILL_TYPE            VARCHAR2(4),
   DISC_TYPE            VARCHAR2(4),
   PRICE_IN_HKD         NUMBER(12),
   EFF_DATE             DATE,
   EXPR_DATE            DATE,
   REMARKS              VARCHAR2(1000),
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20),
   ACTN_TYPE            VARCHAR2(10),
   ACTN_DATE            DATE,
   ACTN_BY              VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_PROD_CHG_LOG_PK on  NW_PROD_CHG_LOG (PROD_CHG_LOG_ID) tablespace nwebidx01;
Alter table NW_PROD_CHG_LOG add constraint NW_PROD_CHG_LOG_PK primary key (PROD_CHG_LOG_ID); 


create table NW_RTQ_ACC  (
   PROD_ID              VARCHAR2(32)                    not null,
   RTQ_LOGN_ID          VARCHAR2(200)                   not null,
   RTQ_LOGN_PWD         VARCHAR2(100),
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_RTQ_ACC_PK on  NW_RTQ_ACC (PROD_ID, RTQ_LOGN_ID) tablespace nwebidx01;
Alter table NW_RTQ_ACC add constraint NW_RTQ_ACC_PK primary key (PROD_ID, RTQ_LOGN_ID); 


create table NW_RTQ_ACC_ASGN  (
   PROD_ID              VARCHAR2(32)                    not null,
   RTQ_LOGN_ID          VARCHAR2(200)                   not null,
   RTQ_LOGN_PWD         VARCHAR2(100)                   not null,
   CLNT_ID              VARCHAR2(20)                    not null,
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_RTQ_ACC_ASGN_PK on  NW_RTQ_ACC_ASGN (PROD_ID, RTQ_LOGN_ID, CLNT_ID) tablespace nwebidx01;
Alter table NW_RTQ_ACC_ASGN add constraint NW_RTQ_ACC_ASGN_PK primary key (PROD_ID, RTQ_LOGN_ID, CLNT_ID); 


create table NW_RTQ_ACC_ASGN_LOG  (
   RTQ_ACC_ASGN_LOG_ID  VARCHAR2(32)                    not null,
   PROD_ID              VARCHAR2(32),
   RTQ_LOGN_ID          VARCHAR2(200),
   RTQ_LOGN_PWD         VARCHAR2(100),
   CLNT_ID              VARCHAR2(20),
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20),
   ACTN_TYPE            VARCHAR2(10),
   ACTN_DATE            DATE,
   ACTN_BY              VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_RTQ_ACC_ASGN_LOG_PK on  NW_RTQ_ACC_ASGN_LOG (RTQ_ACC_ASGN_LOG_ID) tablespace nwebidx01;
Alter table NW_RTQ_ACC_ASGN_LOG add constraint NW_RTQ_ACC_ASGN_LOG_PK primary key (RTQ_ACC_ASGN_LOG_ID); 


create table NW_RTQ_APP  (
   PROD_ID              VARCHAR2(32)                    not null,
   RTQ_PRDR             VARCHAR2(40),
   RTQ_URL              VARCHAR2(200),
   RTQ_STATUS           VARCHAR2(30),
   INIT_DATE            DATE,
   INIT_BY              VARCHAR2(20),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_RTQ_APP_PK on  NW_RTQ_APP (PROD_ID) tablespace nwebidx01;
Alter table NW_RTQ_APP add constraint NW_RTQ_APP_PK primary key (PROD_ID); 

create table NW_SERVER_STATUS  (
   IP_ADDR              VARCHAR2(30)                    not null,
   SERVICE_NAME         VARCHAR2(30)                    not null,
   STATUS               VARCHAR2(30)                    not null,
   STATUS_MSG_CODE      VARCHAR2(10)                    not null,
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01;
Create unique index NW_SERVER_STATUS_PK on  NW_SERVER_STATUS (IP_ADDR, SERVICE_NAME) tablespace nwebidx01;
Alter table NW_SERVER_STATUS add constraint NW_SERVER_STATUS_PK primary key (IP_ADDR, SERVICE_NAME); 


create table NW_SERVER_STATUS_DESCR  (
   STATUS_MSG_CODE      VARCHAR2(10)                    not null,
   LANG                 VARCHAR2(10)                    not null,
   CHANNEL              VARCHAR2(10)                    not null,
   DESCR                CLOB,
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01
LOB ( DESCR) STORE AS (tablespace nweblobtbs01);
Create unique index NW_SERVER_STATUS_DESCR_PK on  NW_SERVER_STATUS_DESCR (STATUS_MSG_CODE, LANG, CHANNEL) tablespace nwebidx01;
Alter table NW_SERVER_STATUS_DESCR add constraint NW_SERVER_STATUS_DESCR_PK primary key (STATUS_MSG_CODE, LANG, CHANNEL); 


create table NW_SERVICE_PARAM  (
   PARAM                VARCHAR2(20)                    not null,
   SERVICE_ID           VARCHAR2(20)                    not null,
   VAL                  VARCHAR2(30),
   DATA_TYPE            VARCHAR2(30),
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01;
Create unique index NW_SERVICE_PARAM_PK on  NW_SERVICE_PARAM (PARAM, SERVICE_ID) tablespace nwebidx01;
Alter table NW_SERVICE_PARAM add constraint NW_SERVICE_PARAM_PK primary key (PARAM, SERVICE_ID); 


create table NW_SERVICE_REMARK  (
   SERVICE_ID           VARCHAR2(20)                    not null,
   STATUS               VARCHAR2(30)                    not null,
   LANG                 VARCHAR2(30)                    not null,
   REMARKS              CLOB,
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01
LOB ( REMARKS ) STORE AS (tablespace nweblobtbs01);
Create unique index NW_SERVICE_REMARK_PK on  NW_SERVICE_REMARK (SERVICE_ID, STATUS, LANG) tablespace nwebidx01;
Create index NW_SERVICE_REMARK_IE1 on  NW_SERVICE_REMARK (SERVICE_ID) tablespace nwebidx01;
Alter table NW_SERVICE_REMARK add constraint NW_SERVICE_REMARK_PK primary key (SERVICE_ID, STATUS, LANG); 


create table NW_SERVICE_STATUS  (
   SERVICE_ID           VARCHAR2(20)                    not null,
   SERVICE_NAME         VARCHAR2(30),
   STATUS               VARCHAR2(30),
   VALID_PERIOD         VARCHAR2(240),
   TIMEZONE             VARCHAR2(30),
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01;
Create unique index NW_SERVICE_STATUS_PK on  NW_SERVICE_STATUS (SERVICE_ID) tablespace nwebidx01;
Alter table NW_SERVICE_STATUS add constraint NW_SERVICE_STATUS_PK primary key (SERVICE_ID); 


create table NW_SVCS_ACES_LOG  (
   SVC_ACES_LOG_ID      VARCHAR2(32)                    not null,
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   RTQ_PRDR             VARCHAR2(40),
   RTQ_UR               VARCHAR2(200),
   RTQ_STATUS           VARCHAR2(30),
   RTQ_LOGN_ID          VARCHAR2(200),
   RTQ_LOGN_PWD         VARCHAR2(100),
   REMOTE_CLNT_IP       VARCHAR2(20),
   ACES_URL             VARCHAR2(200),
   ACES_TIME            DATE,
   ACES_CHNNL           VARCHAR2(32),
   REMARKSS             VARCHAR2(100),
   ACTN_TYPE            VARCHAR2(10),
   ACTN_DATE            DATE,
   ACTN_BY              VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_SVCS_ACES_LOG_PK on  NW_SVCS_ACES_LOG (SVC_ACES_LOG_ID) tablespace nwebidx01;
Alter table NW_SVCS_ACES_LOG add constraint NW_SVCS_ACES_LOG_PK primary key (SVC_ACES_LOG_ID); 


create table NW_USR_PROD  (
   CLNT_ID              VARCHAR2(20)                    not null,
   PROD_ID              VARCHAR2(32)                    not null,
   ALLW_RENL            CHAR,
   EXPR_DATE            DATE,
   STATUS               VARCHAR2(10),
   ACES_UNIT_TOTAL      VARCHAR2(20),
   ACES_UNIT_USED       VARCHAR2(20),
   ACES_UNIT_EXPR       VARCHAR2(20),
   REMARKS              VARCHAR2(1000),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_USR_PROD_PK on  NW_USR_PROD (CLNT_ID, PROD_ID) tablespace nwebidx01;
Create index NW_USR_PROD_IE1 on  NW_USR_PROD (EXPR_DATE) tablespace nwebidx01;
Alter table NW_USR_PROD add constraint NW_USR_PROD_PK primary key (CLNT_ID, PROD_ID); 


create table NW_USR_PROD_ALLT  (
   USR_PROD_ALLT_ID     VARCHAR2(20)                    not null,
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   EFF_DATE             DATE,
   EXP_DATE             DATE,
   ALLT_TIME            DATE,
   ALLT_STATUS          VARCHAR2(10),
   ACES_UNIT_TOTAL      VARCHAR2(20),
   ACES_UNIT_USED       VARCHAR2(20),
   ACES_UNIT_EXPR       VARCHAR2(20),
   PAY_REQ_ID           VARCHAR2(32),
   REMARKS              VARCHAR2(1000),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_USR_PROD_ALLT_PK on  NW_USR_PROD_ALLT (USR_PROD_ALLT_ID) tablespace nwebidx01;
Create index NW_USR_PROD_ALLT_IE1 on  NW_USR_PROD_ALLT (CLNT_ID) tablespace nwebidx01;
Create index NW_USR_PROD_ALLT_IE2 on  NW_USR_PROD_ALLT (PROD_ID) tablespace nwebidx01;
Create index NW_USR_PROD_ALLT_IE3 on  NW_USR_PROD_ALLT (EXP_DATE) tablespace nwebidx01;
Alter table NW_USR_PROD_ALLT add constraint NW_USR_PROD_ALLT_PK primary key (USR_PROD_ALLT_ID); 


create table NW_USR_PROD_ALLT_LOG  (
   USR_PROD_ALLT_LOG_ID VARCHAR2(32)                    not null,
   USR_PROD_ALLT_ID     VARCHAR2(32),
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   EFF_DATE             DATE,
   EXP_DATE             DATE,
   ALLT_TIME            DATE,
   ALLT_STATUS          VARCHAR2(10),
   ACES_UNIT_TOTAL      VARCHAR2(20),
   ACES_UNIT_USED       VARCHAR2(20),
   ACES_UNIT_EXPR       VARCHAR2(20),
   PAY_REQ_ID           VARCHAR2(32),
   REMARKS              VARCHAR2(100),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE,
   ACTN_TYPE            VARCHAR2(20),
   ACTN_BY              VARCHAR2(20),
   ACTN_DATE            DATE
)tablespace nwebtbs01;
Create unique index NW_USR_PROD_ALLT_LOG_PK on  NW_USR_PROD_ALLT_LOG (USR_PROD_ALLT_LOG_ID) tablespace nwebidx01;
Create index NW_USR_PROD_ALLT_LOG_IE1 on  NW_USR_PROD_ALLT_LOG (CLNT_ID) tablespace nwebidx01;
Create index NW_USR_PROD_ALLT_LOG_IE2 on  NW_USR_PROD_ALLT_LOG (PROD_ID) tablespace nwebidx01;
Create index NW_USR_PROD_ALLT_LOG_IE3 on  NW_USR_PROD_ALLT_LOG (EXP_DATE) tablespace nwebidx01;
Alter table NW_USR_PROD_ALLT_LOG add constraint NW_USR_PROD_ALLT_LOG_PK primary key (USR_PROD_ALLT_LOG_ID); 


create table NW_USR_PROD_LOG  (
   USR_PROD_LOG_ID      VARCHAR2(20)                    not null,
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   ALLW_RENL            CHAR,
   EXPR_DATE            DATE,
   STATUS               VARCHAR2(10),
   ACES_UNIT_TOTAL      VARCHAR2(20),
   ACES_UNIT_USED       VARCHAR2(20),
   ACES_UNIT_EXPR       VARCHAR2(20),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE,
   ACTN_TYPE            VARCHAR2(10),
   ACTN_DATE            DATE,
   ACTN_BY              VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_USR_PROD_LOG_PK on  NW_USR_PROD_LOG (USR_PROD_LOG_ID) tablespace nwebidx01;
Create index NW_USR_PROD_LOG_IE1 on  NW_USR_PROD_LOG (CLNT_ID) tablespace nwebidx01;
Create index NW_USR_PROD_LOG_IE2 on  NW_USR_PROD_LOG (PROD_ID) tablespace nwebidx01;
Create index NW_USR_PROD_LOG_IE3 on  NW_USR_PROD_LOG (EXPR_DATE) tablespace nwebidx01;
Alter table NW_USR_PROD_LOG add constraint NW_USR_PROD_LOG_PK primary key (USR_PROD_LOG_ID); 


create table NW_USR_PROD_PAY  (
   USR_PROD_PAY_ID      VARCHAR2(32)                    not null,
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   PRICE_HKD            VARCHAR2(20),
   DEF_DEBT_ACC         VARCHAR2(20),
   REQ_SYS              VARCHAR2(20),
   REQ_TIME             DATE,
   PAY_SATUS            VARCHAR2(10),
   MEMO_CODE            VARCHAR2(20),
   DEBT_REMARKS         VARCHAR2(1000),
   RES_MESSAGE          VARCHAR2(20),
   RES_TIME             DATE,
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_USR_PROD_PAY_PK on  NW_USR_PROD_PAY (USR_PROD_PAY_ID) tablespace nwebidx01;
Create index NW_USR_PROD_PAY_IE1 on  NW_USR_PROD_PAY (CLNT_ID) tablespace nwebidx01;
Create index NW_USR_PROD_PAY_IE2 on  NW_USR_PROD_PAY (PROD_ID) tablespace nwebidx01;
Create index NW_USR_PROD_PAY_IE3 on  NW_USR_PROD_PAY (DEF_DEBT_ACC) tablespace nwebidx01;
Alter table NW_USR_PROD_PAY add constraint NW_USR_PROD_PAY_PK primary key (USR_PROD_PAY_ID); 

create table NW_AUTO_PURC  (
   AUTO_PURC_ID         VARCHAR2(32)          not null,
   CLNT_ID              VARCHAR2(20),
   PROD_ID              VARCHAR2(32),
   STATUS               VARCHAR2(20),
   PRICE_IN_HKD         NUMBER(12),			
   DEBT_REMARKS         VARCHAR2(1000),
   UPD_DATE             DATE,
   UPD_BY               VARCHAR2(20)
)tablespace nwebtbs01;
Create unique index NW_AUTO_PURC_PK on  NW_AUTO_PURC (AUTO_PURC_ID) tablespace nwebidx01;
Alter table NW_AUTO_PURC add constraint NW_AUTO_PURC_PK primary key (AUTO_PURC_ID); 

create table NW_USR_PROF  (
   CLNT_ID              VARCHAR2(20)                    not null,
   CLNT_LOGIN_ID        VARCHAR2(20),
   DEF_DEBT_ACC         VARCHAR2(20),
   CN_DISC_FLAG         VARCHAR2(20),
   INIT_BY              VARCHAR2(20),
   INIT_DATE            DATE,
   UPD_BY               VARCHAR2(20),
   UPD_DATE             DATE
)tablespace nwebtbs01;
Create unique index NW_USR_PROF_PK on  NW_USR_PROF (CLNT_ID) tablespace nwebidx01;
Create index NW_USR_PROF_IE1 on  NW_USR_PROF (DEF_DEBT_ACC) tablespace nwebidx01;
Alter table NW_USR_PROF add constraint NW_USR_PROF_PK primary key (CLNT_ID); 


create table NW_WEB_ADMIN  (
   USER_ID              VARCHAR2(20)                    not null,
   USER_NAME            VARCHAR2(30),
   PWD                  VARCHAR2(100),
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01;
Create unique index NW_WEB_ADMIN_PK on  NW_WEB_ADMIN (USER_ID) tablespace nwebidx01;
Alter table NW_WEB_ADMIN add constraint NW_WEB_ADMIN_PK primary key (USER_ID); 


create table NW_WEB_SERVER  (
   SERVER_ID            VARCHAR2(20)                    not null,
   IP_ADDR              VARCHAR2(30),
   STATUS               VARCHAR2(30),
   SUSPENDED_REASON     CLOB,
   UPD_SEQ_NUM          NUMBER(18)                      not null,
   LAST_MODIFIED        TIMESTAMP                       not null,
   UPD_TOKEN            VARCHAR2(50)
)tablespace nwebtbs01
LOB ( SUSPENDED_REASON ) STORE AS (tablespace nweblobtbs01);
Create unique index NW_WEB_SERVER_PK on  NW_WEB_SERVER (SERVER_ID) tablespace nwebidx01;
Alter table NW_WEB_SERVER add constraint NW_WEB_SERVER_PK primary key (SERVER_ID); 

alter table NW_SERVICE_PARAM
   add constraint NW_SERVICE_PARAM_R_STATUS foreign key (SERVICE_ID)
      references NW_SERVICE_STATUS (SERVICE_ID)
      on delete set null
      not deferrable;

alter table NW_SERVICE_REMARK
   add constraint NW_SERVICE_REMARK_R_STATUS foreign key (SERVICE_ID)
      references NW_SERVICE_STATUS (SERVICE_ID)
      not deferrable;
      



