#!/usr/local/bin/python
# Fig. 23.15: addToCart.py
# Create new/update CartItem for selected Book object

import sys
import Session
import CartItem

# load Session
try:
   session = Session.Session()
except Session.SessionError, message:   # invalid/no session ID 
   Session.redirect( "error.py?message=%s" % message )
   sys.exit()

book = session.data[ "bookToAdd" ]
cart = session.data[ "cart" ]

# if book is already in cart, update quantity 
if book.isbn in cart.keys():
   cartItem = cart[ book.isbn ]
   cartItem.quantity += 1

# otherwise, create and add a new CartItem to cart  
else:
   cart[ book.isbn ] = CartItem.CartItem( book, 1 )

# update cart attribute
session.data[ "cart" ] = cart

# save Session data and send user to viewCart.py
nextPage = "viewCart.py?ID=%s" % session.data[ "ID" ]
session.saveSession()
Session.redirect( nextPage )

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
