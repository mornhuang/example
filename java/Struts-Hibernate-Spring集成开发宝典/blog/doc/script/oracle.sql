-- 创建表空间
CREATE TABLESPACE blog
DATAFILE '/opt/oracle/oradata/test/blog01.dbf' size 200M
EXTENT MANAGEMENT local;

-- 创建用户
CREATE USER blog IDENTIFIED BY blog
DEFAULT TABLESPACE blog;

-- 授予权限
GRANT connect, resource TO blog;

-- 使用blog用户连接数据库
CONNECT blog/blog;

-- 创建博客信息表
CREATE TABLE userinfo (
	id		VARCHAR2(32) NOT NULL,
	name		VARCHAR2(32) NOT NULL,
	password	VARCHAR2(32) NOT NULL,
	CONSTRAINT userinfo_pk PRIMARY KEY (id),
	CONSTRAINT userinfo_unique_name UNIQUE ( name )
);

-- 创建网络日志表
CREATE TABLE message (
	id			VARCHAR2(32) NOT NULL,
	title			VARCHAR2(32) NOT NULL,
	content		VARCHAR2(2000),
	user_id		VARCHAR2(32),
	CONSTRAINT message_pk PRIMARY KEY (id),
	CONSTRAINT message_user FOREIGN KEY (user_id) REFERENCES userinfo(id) 
);

-- 创建读者评论信息表
CREATE TABLE replymessage (
	id			VARCHAR2(32) NOT NULL,
	username	VARCHAR2(64) NOT NULL,
	title		VARCHAR2(32) NOT NULL,
	content		VARCHAR2(2000),
	message_id	VARCHAR2(32) NOT NULL,
	CONSTRAINT replymessage_pk PRIMARY KEY (id),
	CONSTRAINT replymessage_message FOREIGN KEY (message_id) REFERENCES message(id)
);