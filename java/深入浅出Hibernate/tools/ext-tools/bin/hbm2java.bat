@echo off

rem -------------------------------------------------------------------
rem Execute hbm2java tool
rem -------------------------------------------------------------------

call  %~dp0\setenv.bat

java -cp %CP% net.sf.hibernate.tool.hbm2java.CodeGenerator %*

