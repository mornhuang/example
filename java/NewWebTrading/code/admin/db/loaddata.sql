
---------------------------------------------------
--   DATA FOR TABLE nw_acl_usr_prof
--   FILTER = none used
---------------------------------------------------
insert into nw_acl_usr_prof (LOGN_ID, ROLE_ID, DEPT_NME, USR_NME, PWD, EMAIL_ADDR, STATUS, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('admins', 'admin', 'itsz', 'Admin', '234567', 'itsz@itsz.cn', 'Y', 'system', sysdate, 'system', sysdate);

---------------------------------------------------
--   DATA FOR TABLE nw_acl_role
--   FILTER = none used
---------------------------------------------------
insert into nw_acl_role (ROLE_ID, DESCR, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('admin', 'admin', 'system', sysdate, 'system', sysdate);


---------------------------------------------------
--   DATA FOR TABLE nw_acl_role_fnctn_prmisn
--   FILTER = none used
---------------------------------------------------
insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140680', 'admin', 'Parameters_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140681', 'admin', 'Add_Parameter_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140682', 'admin', 'Web_Parameter_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140993', 'admin', 'Administrator_Manager_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140994', 'admin', 'Acl_UserProfile_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140995', 'admin', 'Acl_Role_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140996', 'admin', 'Change_Password_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140997', 'admin', 'User_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140998', 'admin', 'User_Profile_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('12947967140999', 'admin', 'Rtq_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409910', 'admin', 'Rtq_App_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409911', 'admin', 'Rtq_Acc_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409912', 'admin', 'Service_Product_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409913', 'admin', 'Service_Product_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409914', 'admin', 'Auto_Process_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409915', 'admin', 'Auto_Process_Purchase_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671409916', 'admin', 'NO_Date_Export_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411517', 'admin', 'NO_Date_Export_Maintenance_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411518', 'admin', 'Reset_Password_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411519', 'admin', 'Report_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411520', 'admin', 'Monthly_Purchase_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411521', 'admin', 'Reserve_Renewal_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411522', 'admin', 'Day_End_Process_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411523', 'admin', 'Auto_Purchase_Report_menu', 'A', 'system', null, 'system', sysdate);

insert into nw_acl_role_fnctn_prmisn (ACL_ROLE_FNCTN_PRMISN_ID, ROLE_ID, FNCTN_ID, PRMISN_CDE, INIT_BY, INIT_DATE, UPD_BY, UPD_DATE)
values ('129479671411524', 'admin', 'RTQ_Memo_Debit_menu', 'A', 'system', null, 'system', sysdate);

---------------------------------------------------
--   DATA FOR TABLE NW_SERVICE_STATUS
--   FILTER = none used
---------------------------------------------------
insert into NW_SERVICE_STATUS (SERVICE_ID,SERVICE_NAME,STATUS,VALID_PERIOD,TIMEZONE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','IPPS','ACTIVE','* 07-18 * * mon-fri','GMT+08:00',1,sysdate,'Y');
insert into NW_SERVICE_STATUS (SERVICE_ID,SERVICE_NAME,STATUS,VALID_PERIOD,TIMEZONE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','BOC','ACTIVE','* 07-18 * * mon-fri','GMT+08:00',1,sysdate,'Y');
insert into NW_SERVICE_STATUS (SERVICE_ID,SERVICE_NAME,STATUS,VALID_PERIOD,TIMEZONE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','Fund Transfer','ACTIVE','* 09-16 * * mon-fri','GMT+08:00',1,sysdate,'Y');

---------------------------------------------------
--   DATA FOR TABLE SERVICE_PARAM
--   FILTER = none used
---------------------------------------------------
insert into NW_SERVICE_PARAM (PARAM,SERVICE_ID,VAL,DATA_TYPE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('MINVAL','53','1','BigDecimal',1,sysdate,null);
insert into NW_SERVICE_PARAM (PARAM,SERVICE_ID,VAL,DATA_TYPE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('MAXVAL','53','100000','BigDecimal',1,sysdate,null);
insert into NW_SERVICE_PARAM (PARAM,SERVICE_ID,VAL,DATA_TYPE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('MINVAL','54','1','BigDecimal',1,sysdate,'Y');
insert into NW_SERVICE_PARAM (PARAM,SERVICE_ID,VAL,DATA_TYPE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('MAXVAL','54','1000000','BigDecimal',1,sysdate,'Y');
insert into NW_SERVICE_PARAM (PARAM,SERVICE_ID,VAL,DATA_TYPE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('MINVAL','55','1','BigDecimal',1,sysdate,'Y');
insert into NW_SERVICE_PARAM (PARAM,SERVICE_ID,VAL,DATA_TYPE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('MAXVAL','55','1000000000','BigDecimal',1,sysdate,null);

---------------------------------------------------
--   DATA FOR TABLE SERVICE_REMARK
--   FILTER = none used
---------------------------------------------------
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','ACTIVE','en_US','Invalid operation period. PPS transfer service is valid from 7:00 am to 6:00 pm Monday to Friday.',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','ACTIVE','zh_TW','非 有 效 轉 賬 時 段， 繳 費 服 務 時 間 為 逢 星 期 一 至 星 期 五 ，上 午 七 時 至 晚 上 六 時。',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','ACTIVE','zh_CN','非 有 效 转 帐 时 段， 缴 费 服 务 时 间 为 逢 星 期 一 至 星 期 五， 上 午 七 时 至 晚 上 六 时。',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','ACTIVE','en_US','iT''s Banking of Bank of China fund transfer service is valid from 7:00am to 7:00pm<Br>Monday to Friday<br>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','ACTIVE','zh_TW','中 銀 集 團 之 智 達 銀 行 繳 費 服 務 時 間 為 上 午 七 時 至 晚 上 七 時,<br> 星 期 一 至 星 期 五<br><Br>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','ACTIVE','zh_CN','<table align=center width=600>   <tr>      <td align=center>中 银 集 团 之 智 达 银 行 缴 费 服 务 时 间 为 上 午 七 时 至 晚 上 七时,星 期 一 至 星 期 五          <br><Br>      </td>   </tr>			</table><br><br><table align=center width=600>   <tr>      <td align=center><font color=red><b>注意 : </b></font>首 次 使 用 中 银 集 团 智 达 银 行 服 务 之客户，请于转账前先 <a href="javascript:popBOC()"><u><b>按此</b></u></a> 到 中 银 集 团 网 页 把 阁 下 之 交 易 限 额 提 高 ，最 高 交 易 限 额 为 港 币 $ 5 0 , 0 0 </td>   </tr></table>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','SUSPENDED','en_US','iPPS service is temporarily unavailable. ',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','SUSPENDED','zh_TW','由 於 繳 費 靈 轉 賬 系 統 現 正 進 行 緊 急 維 修， 繳 費 靈 轉 賬 暫 時 未 能 提 供 服 務 。 ',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','SUSPENDED','zh_CN','由 于 缴 费 灵 转 账 系 统 现 正 进 行 紧 急 维 修， 缴 费 灵 转 账 暂 时 未 能 提 供 服 务 。',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','SUSPENDED','en_US','iT''s Banking service is temporarily unavailable',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','SUSPENDED','zh_TW','中國銀行(香港)轉賬服務暫時未能提供服務',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','SUSPENDED','zh_CN','中国银行(香港)转账服务暂时未能提供服务',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','ACTIVE','en_US','<font size=3><BR><B>Please note that the time for fund transfer service is 9am to 5pm.</B><br><br>Tranfers between client securities account and client designated bank account submitted on or before 3:00 pm Monday to Friday will be processed on the same day.  Transfer requested after 3:00 pm will be processed on the next business day.</font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','ACTIVE','zh_TW','<font size=3><BR>請注意: 電子轉賬服務時間為上午九時至下午五時。<br><br>客戶欲提取證券戶口內之現金結存到閣下的指定銀行戶口，請於星期一至五下午三時前安排提款，逾時視作翌日處理。</font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','ACTIVE','zh_CN','<font size=3><BR>请注意: 电子转账服务时间为上午九时至下午五时。<br><br>客户欲提取证券户口内之现金结存到阁下的指定银行户口，请于星期一至五下午三时前安排提款，逾时视作翌日处理。</font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','SUSPENDED','en_US','<font size=3><BR><BR>Fund transfer service is temporarily unavailable.<BR><BR><BR><BR></font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','SUSPENDED','zh_TW','<font size=3><BR><BR>電 子 轉 賬 服 務 暫 時 未 能 提 供 服 務<BR><BR><BR><BR></font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','SUSPENDED','zh_CN','<font size=3><BR><BR>电 子 转 账 服 务 暂 时 未 能 提 供 服 务<BR><BR><BR><BR></font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','ACTIVE','ja_JP','利用時間ではありません。 PPS振替サービスは平日（月曜ー金曜）午前７時から午後７時までです。',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('53','SUSPENDED','ja_JP','IPPSサービスは一時停止になります。 ',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','ACTIVE','ja_JP','BOC 電子振替サービスは午前7時から午後7時までご使用できます。',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('54','SUSPENDED','ja_JP','BOCサービスは一時停止になります。',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','ACTIVE','ja_JP','<font size=3><BR>ご注意： 電子振替サービスは午前９時から午後５時までご使用できます。<br><br>証券口座から特定銀行口座への振替は、平日（月ー金）午後３時前にしてください。３時を過ぎると振替処理は翌日となることにご注意ください。</font>',0,sysdate,null);
insert into NW_SERVICE_REMARK (SERVICE_ID,STATUS,LANG,REMARKS,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('55','SUSPENDED','ja_JP','<font size=3><BR><BR>Fund transfer service is temporarily unavailable.<BR><BR><BR><BR></font>',0,sysdate,null);


---------------------------------------------------
--   DATA FOR TABLE NW_CS_ONLINEUSER
--   FILTER = none used
---------------------------------------------------
insert into NW_CS_ONLINEUSER (CHANNELCODE_PK,USERID_PK,SESSIONID,LOGINTIME) values ('ZZB','0100567','B24590D50CEA534BEF8EA99B349109A8.wmt93',sysdate);
insert into NW_CS_ONLINEUSER (CHANNELCODE_PK,USERID_PK,SESSIONID,LOGINTIME) values ('ZZB','0901605','B755ED3CFB71150DA531E7710ED152B9.wmt93',sysdate);


---------------------------------------------------
--   DATA FOR TABLE NW_CS_PARAMETER
--   FILTER = none used
---------------------------------------------------
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('Fund_Deposit_FilePath','/tmp/','5','Character',0,'Fund_Deposit_FilePath',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('fundDeposit_emailTarget','csdept@htisec.com','5','Character',0,'fundDeposit_emailTarget',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('Rtq_CustomerId','MONITOR','5','Character',0,'For Call QS Use Sys CustomerId',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('USAStockCode','4662,4363','5','Character',0,'4662,4363',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('IWEB.ChannelID','IWEB01','5','Character',0,'IWEB.ChannelID',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('IWEB.ViewProvider','com.itsz.view.provider.web.WEBViewProvider','5','Character',0,'IWEB.ViewProvider',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('IWIN.ChannelID','IWIN01','5','Character',0,'IWIN.ChannelID',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('IWIN.ViewProvider','com.itsz.sht.vp.ps.PsViewProvider','5','Character',0,'IWIN.ViewProvider',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('SimpleRtqDelayTime','15','5','Character',0,'Simple Rtq Delay Time',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('DelayRTQDelayTime','15','5','Character',0,'Delay RTQ Delay Time',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('BOCReturnURL','http://www.htisec.com','5','Character',0,'BOCReturnURL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('IpgClientURL','http://pa.htisec.com/','5','Character',0,'IpgClientURL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('ItsHandlerURL','https://its.bochk.com/servlet/Its_s_pg_handler','5','Character',0,'ItsHandlerURL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('SHK_URL','http://www.shkfd.com.hk/taifookbasic/login.php','5','Character',0,'SHK_URL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('SHK_KEY','119337','5','Character',0,'SHK_KEY',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('Holidays','2008-01-01,2008-02-07,2008-02-08,2008-02-09,2008-03-21,2008-03-22,2008-03-24,,2008-04-04,2008-05-01,2008-05-12,2008-06-09,2008-07-01,2008-09-15,2008-10-01,2008-10-07,2008-12-25,2008-12-26','5','Character',0,'stock holidays in H.K.(yyyy-mm-dd)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('STOCK_CODE_LENGTH','5','5','Character',0,'STOCK_CODE_LENGTH',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('BIG5_WEB_AAS_URL','https://stt.htisec.com:18443/aa/index.do?lang=zh_TW','5','Character',0,'BIG5_WEB_AAS_URL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('ENG_WEB_AAS_URL','https://stt.htisec.com:18443/aa/index.do?lang=en_US','5','Character',0,'ENG_WEB_AAS_URL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('GB_WEB_AAS_URL','https://stt.htisec.com:18443/aa/index.do?lang=zh_CN','5','Character',0,'GB_WEB_AAS_URL',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('FUNDTRANSFER_MIN_LIMIT','1','5','Character',0,'FUNDTRANSFER_MIN_LIMIT',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('FUNDTRANSFER_MAX_LIMIT','1000000000','5','Character',0,'FUNDTRANSFER_MAX_LIMIT',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('MisDayEndProcessingFlag','N','5','Character',0,'MIS日结标志，值有：Y/N，admin端做自动续期、购买和memo debit；trade端客户购买时都需要用到。MIS在做日结时，再做自动续期等操作，需要管理员手动将其值设为Y',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay01','1100000110000011000001100000110','5','Character',0,'一月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay02','0211110000011000001100000110','5','Character',0,'二月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay03','0000110000011000001100000110000','5','Character',0,'三月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay04','011010001100000110000111100001','5','Character',0,'四月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay05','1100001101000110000011000001100','5','Character',0,'五月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay06','000111000011000001100000110000','5','Character',0,'六月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay07','1110000011000001100000110000011','5','Character',0,'七月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay08','0000011000001100000110000011000','5','Character',0,'八月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay09','001100000110100011000001100000','5','Character',0,'九月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay10','1100100110000011000001100000110','5','Character',0,'十月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay11','000011000001100000110000011000','5','Character',0,'十一月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay12','0011000001100000110000011110001','5','Character',0,'十二月可交易日，0为交易日，1为非交易日，2为半交易日(一年一次修改)',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_WEB_OpenAccountUrl','http://marry-zhang:8080/htisec/jsp/zh-HK/Account-Open-Method.jsp','5','Character',0,'开设户口',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_WEB_TradeServiceUrl','http://marry-zhang:8080/htisec/jsp/zh-HK/Service-Model.jsp','5','Character',0,'交易服务示范',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_WEB_SimTrade','#','5','Character',0,'模拟网上交易',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_WEB_OpenAccountUrl','http://marry-zhang:8080/htisec/jsp/zh-CN/Account-Open-Method.jsp','5','Character',0,'开设户口',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_WEB_TradeServiceUrl','http://marry-zhang:8080/htisec/jsp/zh-CN/Service-Model.jsp','5','Character',0,'交易服务示范',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_WEB_SimTrade','#','5','Character',0,'模拟网上交易',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_WEB_OpenAccountUrl','http://marry-zhang:8080/htisec/jsp/en/Account-Open-Method.jsp','5','Character',0,'开设户口',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_WEB_TradeServiceUrl','http://marry-zhang:8080/htisec/jsp/en/Service-Model.jsp','5','Character',0,'交易服务示范',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_WEB_SimTrade','#','5','Character',0,'模拟网上交易',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('AH_LoginId','web','5','Character',0,'A+H Quote LoginId（A+H港股行情速递）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('AH_Pwd','000000','5','Character',0,'A+H Quote Password（A+H港股行情速递）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('AH_URL','http://202.22.244.84/AHStocks/AHStocks.jsp','5','Character',0,'A+H Quote Server（A+H港股行情速递）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('afxnewsBIG5','http://www.htisec.com/power_sec/afxnews/afx_news.jsp','5','Character',0,'afxnewsBIG5（每日新闻）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('afxnewsByStockCodeBIG5','http://www.htisec.com/power_sec/afxnews/newsearch.jsp?stock_code=','5','Character',0,'afxnewsByStockCodeBIG5（每日新闻）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('afxnewsEN','http://www.htisec.com/power_sec/afxnews/afx_news_eng.jsp','5','Character',0,'afxnewsEN（每日新闻）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('afxnewsByStockCodeEN','http://www.htisec.com/power_sec/afxnews/newsearch_e.jsp?stock_code=','5','Character',0,'afxnewsByStockCodeEN（每日新闻）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('afxnewsGB','http://www.htisec.com/power_sec/afxnews/afx_news_gb.jsp','5','Character',0,'afxnewsGB（每日新闻）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('afxnewsByStockCodeGB','http://gb.htisec.com/gb/www.htisec.com/power_sec/afxnews/newsearch.jsp?stock_code=','5','Character',0,'afxnewsByStockCodeGB（每日新闻）',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('EtnetLoginUrl','http://iq.etnet.com.hk/iqservice/jsp/CorpAccount/Login.jsp','5','Character',0,'https://iqdemo.etnet.com.hk/iqservice/jsp/IFC/IFCLogin.jsp
http://iq.etnet.com.hk/iqservice/jsp/CorpAccount/Login.jsp
http://202.62.221.6/TaiFook/VerifyUser.do',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('etnet_enabled','0','5','Character',0,'0 --false; etnet old version 
1 --true ; etnet new version ',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('aastock_enabled','0','5','Character',0,'0 --false; old version asstock
1 --true;  new version asstock  ',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('GB_IWIN_IWEBUrl','http://10.100.97.94:10080/sht/changeLang.do?CLV=WG25S'||chr(38)||'page=loginPage','5','Character',0,'从IWIN跳转到IWEB，简体',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ENG_IWIN_IWEBUrl','http://10.100.97.94:10080/sht/changeLang.do?CLV=WE25S'||chr(38)||'page=loginPage','5','Character',0,'从IWIN跳转到IWEB，英文',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('BIG5_IWIN_IWEBUrl','http://10.100.97.94:10080/sht/changeLang.do?CLV=WT25S'||chr(38)||'page=loginPage','5','Character',0,'从IWIN跳转到IWEB，繁体',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ePayment_authurl','/cps/jsp/AskForKey.do','5','Character',0,'ePayment',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ePayment_bankcode1','IPPS','5','Character',0,'ePayment',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ePayment_bankcode2','BOC','5','Character',0,'ePayment',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ePayment_forwardurl','/cps/jsp/PaymentGateway.do','5','Character',0,'ePayment',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ePayment_internal','http://10.100.97.94:7080','5','Character',0,'ePayment',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('ePayment_path','http://10.100.97.94:7080','5','Character',0,'ePayment',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('pagesize','20','5','Character',0,'pagesize for adminportal',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('NewETNetDemo','1','5','Character',0,'1 sit, 0 product',sysdate);
insert into NW_CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('NewETNetRequest','login','5','Character',0,'NewETNetRequest',sysdate);
	
---------------------------------------------------
--   DATA FOR TABLE NW_CS_SET_PARAMETER

--   FILTER = none used
---------------------------------------------------



---------------------------------------------------
--   DATA FOR TABLE NW_PROD
--   FILTER = none used
---------------------------------------------------
insert into NW_PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_AAST','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','NONE',400,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'admins',null,'');
insert into NW_PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_AAST_CN','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','PRC',300,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'admins',null,'');
insert into NW_PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_IQS_CN','UNAVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','PRC',300,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'admins',null,'');
insert into NW_PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SHK','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','PRC',400,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'admins',null,'');
insert into NW_PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('NO_EMAIL','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'FREE','NONE',0,to_date('1970-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss'),to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'admins',null,'');
insert into NW_PROD (PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_IQS','AVAIL',0,1,'MONTHLY','ONE_MONTH-END',0,'CHRG','NONE',400,sysdate,to_date('2049-12-31 23:59:59', 'yyyy-mm-dd hh24:mi:ss'),null,sysdate,'admins',null,'');



---------------------------------------------------
--   DATA FOR TABLE NW_RTQ_APP
--   FILTER = none used
---------------------------------------------------
insert into NW_RTQ_APP (PROD_ID,RTQ_PRDR,RTQ_URL,RTQ_STATUS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_AAST','Quote Winner','https://secure.aastocks.com/pkages/broker/login_broker/auto_all.asp','AVAIL',sysdate,'admins',null,null);
insert into NW_RTQ_APP (PROD_ID,RTQ_PRDR,RTQ_URL,RTQ_STATUS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_AAST_CN','Quote Winner(Limit mainland use)','https://secure.aastocks.com/pkages/broker/login_broker/auto_all.asp','AVAIL',sysdate,'admins',null,null);
insert into NW_RTQ_APP (PROD_ID,RTQ_PRDR,RTQ_URL,RTQ_STATUS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_IQS','ETNet IQ','https://iq6.etnet.com.hk/HttpServer/jsp/IQ_Web/Login.jsp','AVAIL',sysdate,'admins',null,null);
insert into NW_RTQ_APP (PROD_ID,RTQ_PRDR,RTQ_URL,RTQ_STATUS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SHK','SHSEC','http://www.shkfd.com.hk/taifook2.php?security=hdgfjkfdhsguisvcnbm234gbyudf65wgfii8','AVAIL',sysdate,'admins',null,null);
insert into NW_RTQ_APP (PROD_ID,RTQ_PRDR,RTQ_URL,RTQ_STATUS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY) values ('SSTR_IQS_CN','ETNet IQ(Limit mainland use)',null,'UNAVAIL',sysdate,'admins',null,null);


---------------------------------------------------
--   DATA FOR TABLE NW_SERVER_STATUS
--   FILTER = none used
---------------------------------------------------
insert into NW_SERVER_STATUS (IP_ADDR,SERVICE_NAME,STATUS,STATUS_MSG_CODE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('192.168.101.188','MCS','ACTIVE','1',1,sysdate,null);
insert into NW_SERVER_STATUS (IP_ADDR,SERVICE_NAME,STATUS,STATUS_MSG_CODE,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('192.168.101.189','MCS','ACTIVE','1',1,sysdate,null);



---------------------------------------------------
--   DATA FOR TABLE NW_WEB_ADMIN
--   FILTER = none used
---------------------------------------------------
insert into NW_WEB_ADMIN (USER_ID,USER_NAME,PWD,UPD_SEQ_NUM,LAST_MODIFIED,UPD_TOKEN) values ('admin','Testing Account 1','0665',1,sysdate,'Y');

