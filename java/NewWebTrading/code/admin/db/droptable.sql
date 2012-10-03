create or replace procedure drop_nweb_table(tbl_name	in	varchar2) is
  cursor c1 is select 1 from user_tables where user_tables.table_name = upper(tbl_name);
  dummy c1%rowtype;
begin
  open c1;
  fetch c1 into dummy;
  if c1%found then
    execute immediate 'truncate table '||tbl_name;
    execute immediate 'drop table '||tbl_name||' cascade constraints';
  end if;
  close c1;
end drop_nweb_table;
/

create or replace procedure drop_nweb_seq(seq_name	in	varchar2) is
  cursor c1 is select 1 from user_sequences where user_sequences.sequence_name = upper(seq_name);
  dummy c1%rowtype;
begin
  open c1;
  fetch c1 into dummy;
  if c1%found then
    execute immediate 'drop sequence '||seq_name;
  end if;
  close c1;
end drop_nweb_seq;
/

begin
  drop_nweb_table('NW_FUND_DEPOSIT');
  drop_nweb_table('NW_ACL_FUNC');
  drop_nweb_table('NW_ACL_PRMSN');
  drop_nweb_table('NW_ACL_ROLE');
  drop_nweb_table('NW_ACL_ROLE_FNCTN_PRMISN');
  drop_nweb_table('NW_ACL_USR_PROF');
  drop_nweb_table('NW_BROADCAST');
  drop_nweb_table('NW_CHL_SYS_PARA');
  drop_nweb_table('NW_CHL_SYS_PARA_HIS');
  drop_nweb_table('NW_COMMENTARY');
  drop_nweb_table('NW_CS_BROADCAST');
  drop_nweb_table('NW_CS_ONLINEUSER');
  drop_nweb_table('NW_CS_PARAMETER');
  drop_nweb_table('NW_CS_SERVICEMONITOR');
  drop_nweb_table('NW_CS_SET_PARAMETER');
  drop_nweb_table('NW_CS_USERACTIONLOG');
  drop_nweb_table('NW_DEFT_DEBT_ACC_CHNG_LOG');
  drop_nweb_table('NW_NOTF_MDIA');
  drop_nweb_table('NW_PROD');
  drop_nweb_table('NW_PROD_CHG_LOG');
  drop_nweb_table('NW_RTQ_ACC');
  drop_nweb_table('NW_RTQ_ACC_ASGN');
  drop_nweb_table('NW_RTQ_ACC_ASGN_LOG');
  drop_nweb_table('NW_RTQ_APP');
  drop_nweb_table('NW_SERVER_STATUS');
  drop_nweb_table('NW_SERVER_STATUS_DESCR');
  drop_nweb_table('NW_SERVICE_PARAM');
  drop_nweb_table('NW_SERVICE_REMARK');
  drop_nweb_table('NW_SERVICE_STATUS');
  drop_nweb_table('NW_SVCS_ACES_LOG');
  drop_nweb_table('NW_USR_PROD');
  drop_nweb_table('NW_USR_PROD_ALLT');
  drop_nweb_table('NW_USR_PROD_ALLT_LOG');
  drop_nweb_table('NW_USR_PROD_LOG');
  drop_nweb_table('NW_USR_PROD_PAY');
  drop_nweb_table('NW_AUTO_PURC');
  drop_nweb_table('NW_USR_PROF');
  drop_nweb_table('NW_WEB_ADMIN');
  drop_nweb_table('NW_WEB_SERVER');  
end;
/

begin
	drop_nweb_seq('NW_APPL_ID');
	drop_nweb_seq('NW_BROADCAST_ID_SEQ');
	drop_nweb_seq('NW_COMMENTARY_ID_SEQ');
	drop_nweb_seq('NW_CS_BROADCAST_SEQNO');
	drop_nweb_seq('NW_CS_SERVICEMONITOR_SEQNO');
	drop_nweb_seq('NW_CS_USERACTIONLOG_SEQNO');
	drop_nweb_seq('NW_SERVICE_ID_SEQ');
	drop_nweb_seq('NW_SEQ_FUND_DEPOSIT');
end;


