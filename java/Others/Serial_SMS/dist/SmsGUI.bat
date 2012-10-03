set classpath = ./comm.jar; ./classes/
javac -d ./classes/ ./src/*.java
cd classes
java Sms_GUI
cd ..
