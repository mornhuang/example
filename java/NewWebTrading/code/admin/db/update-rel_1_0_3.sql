update prod set svc_mode='FREE' where prod_id='NO_EMAIL';

delete cs_parameter where key_pk like 'TradeDay%' or key_pk in ('BIG5_WEB_AAS_URL', 'ENG_WEB_AAS_URL', 'GB_WEB_AAS_URL');
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay01','1100000110000011000001100000110','5','Character',0,'一月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay02','0211110000011000001100000110','5','Character',0,'二月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay03','0000110000011000001100000110000','5','Character',0,'三月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay04','011010001100000110000111100001','5','Character',0,'四月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay05','1100001101000110000011000001100','5','Character',0,'五月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay06','000111000011000001100000110000','5','Character',0,'六月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay07','1110000011000001100000110000011','5','Character',0,'七月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay08','0000011000001100000110000011000','5','Character',0,'八月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay09','001100000110100011000001100000','5','Character',0,'九月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay10','1100100110000011000001100000110','5','Character',0,'十月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay11','000011000001100000110000011000','5','Character',0,'十一月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values('TradeDay12','0011000001100000110000011110001','5','Character',0,'十二月可交易日，0为交易日，1为非交易日，2为半交易日',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('BIG5_WEB_AAS_URL','https://stt.htisec.com:18443/aa/index.do?lang=zh_TW','5','Character',0,'BIG5_WEB_AAS_URL',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('ENG_WEB_AAS_URL','https://stt.htisec.com:18443/aa/index.do?lang=en_US','5','Character',0,'ENG_WEB_AAS_URL',sysdate);
insert into CS_PARAMETER (KEY_PK,VALUE,CLASSID,DATATYPE,READONLY,DESCRIPTION,UPDATE_TIME) values ('GB_WEB_AAS_URL','https://stt.htisec.com:18443/aa/index.do?lang=zh_CN','5','Character',0,'GB_WEB_AAS_URL',sysdate);