@echo off

rem -------------------------------------------------------------------
rem Setup environment for hibernate tools
rem -------------------------------------------------------------------

set JDBC_DRIVER=C:\Progra~1\SQLLIB\java\db2java.zip;C:\mm.mysql-2.0.14\mm.mysql-2.0.14-bin.jar

set HIBERNATETOOLS_HOME=%~dp0..
echo HIBERNATETOOLS_HOME set to %HIBERNATETOOLS_HOME%

if "%HIBERNATE_HOME%" == "" goto noHIBERNATEHome

set CORELIB=%HIBERNATE_HOME%\lib
set LIB=%HIBERNATETOOLS_HOME%\lib
set CP=%CLASSPATH%;%JDBC_DRIVER%;%HIBERNATE_HOME%\hibernate2.jar;%CORELIB%\commons-logging-1.0.3.jar;%CORELIB%\commons-lang-1.0.1.jar;%CORELIB%\cglib-2.0-rc2.jar;%CORELIB%\dom4j-1.4.jar;%CORELIB%\odmg-3.0.jar;%CORELIB%\xml-apis.jar;%CORELIB%\xerces-2.4.0.jar;%CORELIB%\xalan-2.4.0.jar;%LIB%\jdom.jar;%CORELIB%\commons-collections-2.1.jar;%LIB%\..\hibernate-tools.jar

if not "%HIBERNATE_HOME%" == "" goto end

:noHIBERNATEHome
echo HIBERNATE_HOME is not set. Please set HIBERNATE_HOME.
goto end

:end