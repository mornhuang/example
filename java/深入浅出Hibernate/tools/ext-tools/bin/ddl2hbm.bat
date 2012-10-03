@echo off

rem -------------------------------------------------------------------
rem Execute ddl2hbm tool
rem -------------------------------------------------------------------

call  %~dp0\setenv.bat

java -cp %CP% net.sf.hibernate.tool.ddl2hbm.Gui


