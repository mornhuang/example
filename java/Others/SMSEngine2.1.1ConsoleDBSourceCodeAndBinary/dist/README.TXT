SMSengine 2.1.1 + Console + DB Mode :)
------------------

What is that Application ?
--------------------------
	Imagine it is xmas time and u need to send a lots of sms to people and maybe
same sms to many people or different sms to each person.You can simply write all the sms 
in your pc and send them by this application.
This is will be the last version of application, i will do bug fixes :) , no worries ;) .

With this version of SMSEngine, you can get the load the message/receivers from a database by using a SQL query 
or by giving table and column name to load; you can send the messages by using Swing GUI or from the console mode 
by passing the parameters.


Pre-requirments
---------------
 * JRE 1.5 or higher (Napkin Look and Feel Requires)
 * Your phone needs to be connected to pc ( you check it by using hyper terminal )
	+ Your phone's modem driver needs to be loaded.You can check under control panel/fax-modem
	  Incase : If you dont have your phone's driver , you can try to load xp standart driver.


How to setup ?
--------------
1- Unzip the zip file to somewhere.
2- Copy the dll files into your %jre%/bin (If you download the application with source dlls will be in dist folder)


How to run in Graphical Mode ?
-------------------------------
	run SMSEngineGraphMode.cmd
	
	or

	Other ways to run this application from the command line without Ant, try(I assume u unzip to c:\SMSEngine):
	java -jar "C:\SMSEngine\SMSEngine1.0.0.jar"
 


	If you have many JREs :) 
	------------------------
	You can run as 
	c:\Program Files\Java\jre1.5.0_08\bin\java -jar "C:\SMSEngine\SMSEngine-1-1-1+Console.jar" 

	If your JRE location is "d:\jdk1.5.0_06\jre" use the following command (I assume you are in the same folder with jar folder)
	d:\jdk1.5.0_06\jre\bin\java -jar "SMSEngine-1-1-1+Console.jar"


How to run in Console Mode ?
-------------------------------

	run SMSEngineConsoleMode.cmd  (Please change the parameters as u wish otherwise u will send me a sms :))
	
	or

	If your JRE location is "d:\jdk1.5.0_06\jre" use the following command (I assume you are in the same folder with jar folder)
	d:\jdk1.5.0_06\jre\bin\java -jar "SMSEngine-1-1-1+Console.jar" -c -m hellowwww -r 00447772863346,+447772863346,07772863346

	Note : Difference between Console mode startup and Graph mode startuo is -c parameter.
	
	Console Mode Commands:
	----------------------
                	-m   : message   , usage: -m this is my message
                	-r   : recievers , usage: -r 004477728633XX or -r 004477728633XX,077728633XX,053634565XX
                	-mf  : message from file , usage: -mf c:\\SMSFolder\\message01.txt
                	-mdb : message from database , usage: -mdb , application will try to read the parameters from property file.
                	-rf  : receivers from file , usage: -rf c:\\SMSFolder\\recievers01.txt
                	-rdb : receivers from database , usage: -rdb , application will try to read the parameters from property file.              	
                	
                	-h or -help : shows the argument list
                	
                	Note : If you are a Windows user please use \\\\ instead \\
                	
                	Enjoy :)

		Example Usage : 
			java -jar "SMSEngine2-1-1+Console+DB.jar" -c -mdb -r 00447772863346,+447772863346

		Note : This command will try to load the message from database and will send this message to me, 2 times :).

How/from where does application gets the GSM Device, logger settings and default/console mode database conn parameters?
------------------------------------------------------------------------
If you open the jar file with winzip or winrar or whatever :), then you will see 3 property files.
You can easily change them with a text editor and add back to jar file.

Note : if you are changing log4j.properties for windows environment please use \\ instead of \.

