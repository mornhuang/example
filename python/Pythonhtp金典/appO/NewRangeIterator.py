# Fig. O.3: NewRangeIterator.py
# __setitem__ used in a for loop.

class MyRange:
   """Simple class to simulate a range"""

   def __init__( self, start, stop, step ):
      """Class MyRange constructor; takes start, stop and step"""

      self.__sequence = range( start, stop, step )
      self.__nextValue = 0  # subscript of next value to produce

   def __iter__( self ):
      """Returns iterator for object of class MyRange"""
      
      return self

   def next( self ):
      """Iterator method to produce next value in sequence"""

      try:      
         value = self.__sequence[ self.__nextValue ]
      except IndexError:
         raise StopIteration
      else:
         self.__nextValue += 1
         return value

def main():

   # create an object of class MyRange and use for loop to iterate
   range1 = MyRange( 0, 10, 1 )

   print "Iterate over the values in range1 using a for loop:"

   for value in range1:
      print value,

   print      

   # create an object of class MyRange and call next to iterate
   range2 = MyRange( 0, 10, 1 )
   range2Iterator = iter( range2 )  # retrieve iterator for range2

   print "\nCall method next for range2Iterator:"
   
   while 1:

      try:
         value = range2.next()
      except StopIteration:
         break
      else:
         print value,

   print         

   # create one object of class MyRange and create two iterators
   # for same object
   range3 = MyRange( 0, 10, 1 )
   range3Iterator1 = iter( range3 )
   range3Iterator2 = iter( range3 )

   print "\nCall next for two iterators of the same object:"

   for i in range( 10 ):
      print "Loop iteration %d: range3Iterator1.next() = %d" % \
         ( i, range3Iterator1.next() )
      print "Loop iteration %d: range3Iterator2.next() = %d" % \
         ( i, range3Iterator2.next() )
      print

if __name__ == "__main__":
   main()

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
