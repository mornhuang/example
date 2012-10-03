# Fig. O.6: figO_06.py
# Fibonacci generator.

from __future__ import generators

def fibonacci():

   nextItem = 0  # next value in the sequence
   beyondItem = 1  # value *after* the next value in the sequence
   
   while 1:
      yield nextItem  # return fibonacci( n )

      # function resumes here when program calls next on
      # fibonacci's iterator

      # compute the next fibonacci( n ) and fibonacci( n + 1 ) 
      nextItem, beyondItem = beyondItem, nextItem + beyondItem

fibIterator = fibonacci()  # create iterator for Fibonacci sequence
result = 0

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

# find smallest Fibonacci number greater than 1000
while result < 1000:
   result = fibIterator.next()

print "The smallest fibonacci number greater than 1000 is:", result

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


