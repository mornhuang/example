@echo off

rem -------------------------------------------------------------------
rem Execute class2hbm tool
rem -------------------------------------------------------------------

call  %~dp0\setenv.bat

java -cp %CP% net.sf.hibernate.tool.class2hbm.MapGenerator %*

