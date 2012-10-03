# Fig. 23.7: Book.py
# Represents one book.

class Book:
   """A Book instance contains the data for one book"""

   def __init__( self ):
      """Initialize Book data"""

      self.isbn = None
      self.title = None
      self.price = None
      self.imageFile = None
      self.copyright = None
      self.publisherID = None
      self.editionNumber = None

   def getXML( self, document ):
      """Return an XML representation of the product"""

      # create dictionary of Book information
      data = { "isbn" : self.isbn,
               "title" : self.title,
               "price" : self.price,
               "imageFile" : self.imageFile,
               "copyright" : self.copyright,
               "publisherID" : self.publisherID,
               "editionNumber" : self.editionNumber }

      # create product node
      product = document.createElement( "product" )

      # add element for each Book attribute
      for key in data.keys():
         
         # create element, append as child of product
         temp = document.createElement( key )
         temp.appendChild( document.createTextNode(
            str( data[ key ] ) ) )
         product.appendChild( temp )

      return product

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