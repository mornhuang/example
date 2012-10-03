
# Fig. O.5: figO_05.py
# Inefficient, recursive fibonacci calls.

def fibonacci( n ):

   if n < 0:
      raise ValueError, \
         "Cannot computer fibonacci on negative number"

   elif n == 0 or n == 1:
      return n

   else:
      return fibonacci( n - 1 ) + fibonacci( n - 2 )
   
n = 0
value = 0

# compute smallest fibonacci( n ) > 1000
while value < 1000:
   value = fibonacci( n )
   n += 1

print "The smallest fibonacci number greater than 1000 is:", value

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

