set JDBC_DRIVER=jdbc/hsqldb.jar
java -cp "lib/ant-launcher-1.6.2.jar" org.apache.tools.ant.launch.Launcher -lib lib -Ddriver.jar=%JDBC_DRIVER% %1 %2 %3 %4 %5
