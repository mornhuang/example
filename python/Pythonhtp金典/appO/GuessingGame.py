# Fig. O.4: GuessingGame.py
# Class and program to simulate computer guessing game.

import random

class ComputerGuessingGame:
   """Class to guess a number randomly"""

   def __init__( self, value, lowerBound = 0, upperBound = 10 ):
      """ComputerGuesser constructor; takes secret number, lower
      and upper bounds"""

      self.realValue = value
      
      # keep value within upper and lower bound
      if lowerBound > value:
         self.lower = value
      else:
         self.lower = lowerBound

      if upperBound < value:
         self.upper = value + 1
      else:
         self.upper = upperBound
         
   def __iter__( self ):
      """Return iterator for object of class ComputerGuesser"""

      return self

   def next( self ):
      """Guesses a new value. If correct, raises StopIteration;
      otherwise returns guess"""

      guess = random.randrange( self.lower, self.upper )

      if guess == self.realValue:
         raise StopIteration
      else:
         return guess

def main():

   # retrieve an integer from the user
   while 1:   

      try:
         secretNumber = int(
            raw_input( "Enter number for computer to guess: " ) )
      except ValueError:
         print "Please enter an integer."
      else:
         break

   print

   computerGuesser = ComputerGuessingGame( secretNumber )
   numberOfWrongGuesses = 0

   # print the incorrect guesses
   for wrongGuess in computerGuesser:
      numberOfWrongGuesses += 1
      print "Computer guessed: %d" % wrongGuess

   print "\nGot secret number after %d wrong guesses." % \
      numberOfWrongGuesses

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
