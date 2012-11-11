# Fig. O.1: NewRange.py
# Class MyRange--defines __getitem__ used in a for loop.

class MyRange:
   """Simple class to simulate a range"""

   def __init__( self, start, stop, step ):
      """Class MyRange constructor; takes start, stop and step"""

      self.sequence = range( start, stop, step )

   def __getitem__( self, subscript ):
      """Overridden sequence element access"""

      return self.sequence[ subscript ]

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
