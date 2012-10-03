rem  cloudTable.bat : Creates a table in the CloudscapeDB.
rem 
rem  Before running this script, change to the directory of the
rem  example.  The following commands create the tables for the
rem  account example:
rem 
rem     cd doc\guides\ejb\examples\account
rem     ..\util\cloudTable.sh

rem set J2EE_HOME=<installation-location>

java -Dij.connection.plDB=jdbc:rmi://localhost:1099/jdbc:cloudscape:plDB\;create=true -Dcloudscape.system.home=%J2EE_HOME%\cloudscape -classpath %J2EE_HOME%\lib\cloudscape\client.jar;%J2EE_HOME%\lib\cloudscape\tools.jar;%J2EE_HOME%\lib\cloudscape\cloudscape.jar;%J2EE_HOME%\lib\cloudscape\RmiJdbc.jar;%J2EE_HOME%\lib\cloudscape\license.jar;%CLASSPATH% -ms16m -mx32m COM.cloudscape.tools.ij CloudscapTest.sql

