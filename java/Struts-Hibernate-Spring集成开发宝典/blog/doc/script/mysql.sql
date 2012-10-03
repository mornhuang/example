CREATE DATABASE blog;

GRANT ALL ON blog.* TO blog@localhost IDENTIFIED BY "blog";

USE blog;

CREATE TABLE userinfo (
	id			VARCHAR(32) NOT NULL,
	name		VARCHAR(32) NOT NULL,
	password	VARCHAR(32) NOT NULL,
	CONSTRAINT userinfo_pk PRIMARY KEY (id),
	CONSTRAINT userinfo_unique_name UNIQUE ( name )
);

CREATE TABLE message (
	id			VARCHAR(32) NOT NULL,
	title		VARCHAR(32) NOT NULL,
	content		VARCHAR(2000),
	pubdate		DATETIME,
	user_id		VARCHAR(32),
	CONSTRAINT message_pk PRIMARY KEY (id),
	CONSTRAINT message_user FOREIGN KEY (user_id) REFERENCES userinfo(id) 
);

CREATE TABLE replymessage (
	id			VARCHAR(32) NOT NULL,
	username	VARCHAR(32) NOT NULL,
	title		VARCHAR(32) NOT NULL,
	content		VARCHAR(2000),
	pubdate		DATETIME,
	message_id		VARCHAR(32),
	CONSTRAINT replymessage_pk PRIMARY KEY (id),
	CONSTRAINT replymessage_message FOREIGN KEY (message_id) REFERENCES message(id) 
);
	