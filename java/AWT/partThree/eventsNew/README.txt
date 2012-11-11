This directory contains files that have different versions of applets (or
applications) that have identical class names.  As a result, you cannot do:

	javac *.java

Instead, you must individually compile each .java file.

Also, you'll need to remove all .class files before compiling.  For instance:

rm *.class
javac SerializeTest.java
java SerializeTest

...

rm *.class
javac SerializeTest2.java
java SerializeTest2


A number of the examples in this directory are applications.
