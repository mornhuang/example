# Fig O.7: figO_07.py
# Fibonacci generator for nth Fibonacci sequence element.

from __future__ import generators

def fibonacci( n ):

   nextItem = 0  # next value in the sequence
   beyondItem = 1  # value *after* the next value in the sequence
   currentN = 0  # n for which the generator is producing a value
   
   while currentN <= n:
      yield nextItem  # return fibonacci( n )

      # compute the next fibonacci( n ) and fibonacci( n + 1 ) 
      nextItem, beyondItem = beyondItem, nextItem + beyondItem
      currentN += 1

while 1:

   # retrieve number from user
   try:
      fibNumber = int( raw_input( "Enter a number: " ) )
   except ValueError:
      print "Please enter an integer."
   else:
      break

print
counter = 0      

# print fibonacii( n ) for all n <= fibNumber
for result in fibonacci( fibNumber ):
   print "fibonacci( %d ) = %d" % ( counter, result )
   counter += 1

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
