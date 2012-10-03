/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     2005-2-9 16:38:37                            */
/*==============================================================*/


drop table if exists Article;

drop table if exists Board;

drop table if exists User;

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
   primary key (id)
)
type = InnoDB;;

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
)
type = InnoDB;

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   id                             int                            not null,
   name                           varchar(50)                    not null,
   pwd                            varchar(50)                    not null,
   primary key (id)
)
type = InnoDB;

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

alter table Board add constraint parent_ref2 foreign key (parent_id)
      references Board (id);

