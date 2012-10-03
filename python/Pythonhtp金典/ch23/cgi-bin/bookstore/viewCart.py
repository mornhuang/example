#!/usr/local/bin/python
# Fig. 23.16: viewCart.py
# Generate XML representing cart, convert
# to required format using browser-specific XSLT
# style sheet and display results.

import sys
import Session
from xml.xslt import Processor
from xml.dom.DOMImplementation import implementation

# load Session
try:
   session = Session.Session()
except Session.SessionError, message:   # invalid/no session ID
   Session.redirect( "error.py?message=%s" % message )
   sys.exit()

cart = session.data[ "cart" ]
total = 0  # total for all ordered items

# generate XML representing cart object
document = implementation.createDocument( None, None, None )
cartNode = document.createElement( "cart" )
document.appendChild( cartNode )

# add XML representation for each cart item
for cartItem in cart.values():

   # get book data, calculate subtotal and total
   book = cartItem.item
   quantity = cartItem.quantity
   price = book.price
   subtotal = quantity * price
   total += subtotal

   # create an orderProduct element
   orderProduct = document.createElement( "orderProduct" )

   # create a product element and append to orderProduct
   productNode = book.getXML( document )
   orderProduct.appendChild( productNode )

   # create a quantity element and append to orderProduct
   quantityNode = document.createElement( "quantity" )
   quantityNode.appendChild( document.createTextNode( "%d" %
      quantity ) )
   orderProduct.appendChild( quantityNode )

   # create a subtotal element and append to orderProduct
   subtotalNode = document.createElement( "subtotal" )
   subtotalNode.appendChild( document.createTextNode( "%.2f" %
      subtotal ) )
   orderProduct.appendChild( subtotalNode )

   # append orderProduct to cartNode
   cartNode.appendChild( orderProduct )

# set the total attribute of cart element
cartNode.setAttribute( "total", "%.2f" % total )

# make current total a session attribute
session.data[ "total" ] = total

# process generated XML against XSLT style sheet
processor = Processor.Processor()
style = open( session.data[ "agent" ] + "/viewCart.xsl" )
processor.appendStylesheetString( style.read() % \
   ( session.data[ "ID" ], session.data[ "ID" ] ) )
results = processor.runNode( document )
style.close()

# save Session data and display processed XML
pageData = session.data[ "content type" ] + results
session.saveSession()
print pageData

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
