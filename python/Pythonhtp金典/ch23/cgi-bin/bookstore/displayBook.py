#!/usr/local/bin/python
# Fig. 23.12: displayBook.py
# Retrieve one book's XML representation, convert
# to required format using browser-specific XSLT
# stylesheet and display results.

import cgi
import sys
import Session
from xml.xslt import Processor
from xml.dom.DOMImplementation import implementation

form = cgi.FieldStorage()

# ISBN has not been specified
if not form.has_key( "isbn" ):
   Session.redirect( "error.py?message=No+ISBN+given" )
   sys.exit()

# load Session
try:
   session = Session.Session()     
except Session.SessionError, message:  # invalid/no session ID 
   Session.redirect( "error.py?message=%s" % message )
   sys.exit()
      
titles = session.data[ "titles" ]   # get titles
session.data[ "bookToAdd" ] = None  # book has not been found

# locate Book object for selected book   
for book in titles:
   
   if form[ "isbn" ].value == book.isbn:
      session.data[ "bookToAdd" ] = book
      break

# book has been found
if session.data[ "bookToAdd" ] is not None:

   # get XML from selected book
   document = implementation.createDocument( None, None, None )
   document.appendChild( session.data[ "bookToAdd" ].getXML(
      document ) )

   # process XML against XSLT style sheet
   processor = Processor.Processor()
   style = open( session.data[ "agent" ] + "/displayBook.xsl" )
   processor.appendStylesheetString( style.read() % \
      ( session.data[ "ID" ], session.data[ "ID" ] ) )
   results = processor.runNode( document )
   style.close()

   # save Session data and display processed XML 
   pageData = session.data[ "content type" ] + results
   session.saveSession()
   print pageData
else:

   # invalid ISBN has been specified         
   Session.redirect( "error.py?message=Nonexistent+ISBN" )

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
