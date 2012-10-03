/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     2005-3-19 2:26:10                            */
/*==============================================================*/


drop table if exists Article;

drop table if exists Board;

drop table if exists User;

drop table if exists Vote;

drop table if exists Vote_Option;

/*==============================================================*/
/* Table: Article                                               */
/*==============================================================*/
create table Article
(
   id                             int                            not null,
   parent_id                      int,
   board_id                       int                            not null,
   article_type                   int                            not null,
   title                          varchar(255)                   not null,
   body                           text,
   create_by                      int                            not null,
   create_time                    datetime                       not null,
   hits                           int                            not null,
   bytes                          int,
   last_update_by                 int                            not null,
   last_update_time               datetime                       not null,
   tree_index                     varchar(255),
   node_level                     int,
   root_id                        int,
   primary key (id)
);

/*==============================================================*/
/* Table: Board                                                 */
/*==============================================================*/
create table Board
(
   id                             int                            not null,
   create_by                      int                            not null,
   parent_id                      int,
   name                           varchar(50)                    not null,
   remark                         varchar(255),
   create_time                    datetime                       not null,
   primary key (id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   id                             int                            not null,
   name                           varchar(50)                    not null,
   pwd                            varchar(50)                    not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Vote                                                  */
/*==============================================================*/
create table Vote
(
   id                             int                            not null,
   count                          int,
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Index: "Reference_7_FK"                                            */
/*==============================================================*/
create index Reference_7_FK
(
   id
);

/*==============================================================*/
/* Table: Vote_Option                                           */
/*==============================================================*/
create table Vote_Option
(
   option_id                      int                            not null,
   poll_id                        int,
   "option"                       text,
   agree_number                   int,
   primary key (option_id)
)
type = InnoDB;

/*==============================================================*/
/* Index: "Reference_8_FK"                                            */
/*==============================================================*/
create index Reference_8_FK
(
   poll_id
);

alter table Article add constraint belongs_to foreign key (board_id)
      references Board (id);

alter table Article add constraint article_create_by foreign key (last_update_by)
      references User (id);

alter table Article add constraint article_last_update foreign key (create_by)
      references User (id);

alter table Article add constraint parent_ref foreign key (parent_id)
      references Article (id);

alter table Board add constraint board_create_by foreign key (create_by)
      references User (id);

alter table Board add constraint parent_ref foreign key (parent_id)
      references Board (id);

alter table Vote add constraint FK_Reference_7 foreign key (id)
      references Article (id) on delete restrict on update restrict;

alter table Vote_Option add constraint FK_Reference_8 foreign key (poll_id)
      references Vote (id) on delete restrict on update restrict;

