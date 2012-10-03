This is release 5 of Middlegen (2.1 beta) with Hibernate plugin (r5).

This is a sample on how to use Middlegen. The Ant build.xml script demonstrates how to 
invoke Middlegen. It is based on the sample that is provided with Middlegen as created by 
Aslak Hellesøy.

This sample is a simple airline reservation system using Hibernate. This package is using
a beta version of Middlegen 2 with the second release version of the Middlegen Plugin.
This is NOT an offical release of Middlegen. It has been released to provide a starting point
for Hibernate users to exercise (and test) the Hibernate Plugin. The only external plugin
included is the Hibernate plugin though I have included a few extra libraries to aid users
creating a complete development environment.

The build script will launch the Middlegen GUI which is used to build the Hibernate HBM 
O/R mapping documents only. The GUI option can be turned off in which case the generation
occurs with the option of tuning any aspect of the generation process.

Middlegen and the Plugin was built and Tested using Sun JDK_1.4.2 on Windows XP.

What you need to do:
* For a quick overview
-Install Ant 1.5.x on your computer (http://jakarta.apache.org/ant)
-Set classpath, path and system variables such as JAVA_HOME
-Run "ant"

if the database is not present then:
-Run "ant create-tables"

Use the GUI to change settings, add extra attributes and change the table relationships by
using the mouse while holding either cntl or shift key down.

That's it really. The rest of the work is to poke around and try to understand what's going on,
and how you can use this build.xml script as inspiration for your own project. Fore more info,
please refer to the FAQ, mailing lists and the general documentation.

Have fun
David Channon    (david@hibernate.org)

------------------------------------------------------------------------------------------
Change log:
29/05/03	000-01	DC	Created Plugin and packaged release 1 of the package. 
16/09/03	000-02	DC	Bug Fixes and Enhancements for Middlegen and Plugin (release 2). 
02/11/03	000-03	DC	Bug Fixes and Enhancements for Plugin (release 3). 
                        Note: This release has some Contributions from Cesbron Sebastien.
09/01/04	000-04	DC	Middlegen 2.0 vo (Final) includes Bug Fixes and Enhancements for 
                        Plugin (release 4). The CVS module have been merged into Head so 
                        all code is now in one place. 
                        Note: This release has some Contributions from Daniel Rosenbaum.
21/07/04	000-05	DC	Upgraded to support many new features and Hibernate 2.1.x and
				hbm2java. Improved compoundkey support and many other fixes
				and enhancements. 
------------------------------------------------------------------------------------------
