# Fig. O.8: figO_08.py
# Nested functions.

def outer():

   def inner():
      print "\nFunction inner executing"
      print "The objects in inner's scope are:", dir()
      print "Function inner finishing"

   print "Function outer executing"
   print "The objects in outer's scope are:", dir()
   inner()
   print "\nFunction outer finishing"

print "The objects in the global scope are:"
print dir()

print "\nCalling function outer\n"
outer()
print "\nFunction outer finished"

########################################################################## 
# (C) Copyright 2002 by Deitel & Associates, Inc. and Prentice Hall.     #
# All Rights Reserved.                                                   #
#                                                                        #
# DISCLAIMER: The authors and publisher of this book have used their     #
# best efforts in preparing the book. These efforts include the          #
# development, research, and testing of the theories and programs        #
# to determine their effectiveness. The authors and publisher make       #
# no warranty of any kind, expressed or implied, with regard to these    #
# programs or to the documentation contained in these books. The authors #
# and publisher shall not be liable in any event for incidental or       #
# consequential damages in connection with, or arising out of, the       #
# furnishing, performance, or use of these programs.                     #
##########################################################################
