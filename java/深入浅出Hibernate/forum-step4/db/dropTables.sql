alter table Article DROP FOREIGN KEY belongs_to;
alter table Article DROP FOREIGN KEY article_create_by;
alter table Article DROP FOREIGN KEY article_last_update;
alter table Article DROP FOREIGN KEY parent_ref;
alter table Board DROP FOREIGN KEY board_create_by;
alter table Board DROP FOREIGN KEY parent_board_ref;
alter table Vote DROP FOREIGN KEY FK_Reference_7;
alter table Vote_Option DROP FOREIGN KEY FK_Reference_8;

alter table Article DROP INDEX belongs_to;
alter table Article DROP INDEX article_create_by;
alter table Article DROP INDEX article_last_update;
alter table Article DROP INDEX parent_ref;
alter table Board DROP INDEX board_create_by;
alter table Board DROP INDEX parent_board_ref;
alter table Vote_Option DROP INDEX FK_Reference_8;



drop table  Article;
drop table  Board;
drop table  User;
drop table  Vote;
drop table  Vote_Option;
