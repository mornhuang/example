@ECHO OFF
SET JAVA=java

REM 使用 Sun 的 XML parser ：
SET PARSER=com.sun.xml.parser.Parser
REM 如要使用 IBM xml4j 2.x ：
REM SET PARSER=com.ibm.xml.parsers.SAXParser
REM 您也可以在此设定 CLASSPATH ：
REM SET CLASSPATH=%CLASSPATH%;/path/to/xml.jar;/path/to/xt.jar

%JAVA% -Dcom.jclark.xsl.sax.parser=%PARSER% com.jclark.xsl.sax.Driver %1 %2 %3
