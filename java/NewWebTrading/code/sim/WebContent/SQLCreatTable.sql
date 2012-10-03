
    create table USERPROFILE(
       "LOGINID" VARCHAR(26) not null,
       "PASSWORD" VARCHAR(60) not null,
       "EMAIL" VARCHAR(50) not null,
       "ADDNO" VARCHAR(20) not null,
       "TELEPHONE" VARCHAR(20) not null,
       "USERNAME" VARCHAR(26) not null,
       "CLIENT" VARCHAR(20) not null,
       "CLIENTMONEY" DOUBLE not null,
       "transactionProtection" char(1) not null,
       "allowTradeStatusFlag" char(1) not null,
       "tradeAccount" VARCHAR(50) not null,
       "CLIENTNO" VARCHAR(20),
       "LASTUPDATE" CHAR(19),
       primary key ("LOGINID")
    );
    create table admin(
    	"uname" varchar(20) not null,
    	"upass" varchar(35) not null,
    	primary key("uname")
    );
    insert into admin values('admin','670b14728ad9902aecba32e22fa4f6bd');/* 默认密码:000000 */
    create table parameter(
    	"pname" varchar(20) not null,
    	"pvalue" varchar(20) not null,
    	"pdesc" varchar(100) not null,
    	primary key("pname")
    );
    insert into parameter values('money','10000000','初始金额'); 
    insert into parameter values('stime','9','交易开始时间');
    insert into parameter values('etime','17','交易结束时间');
 