#!/usr/local/bin/python
# Fig. 23.22: error.py
# Generate XML error message and display to user
# using a browser-specific XSLT style sheet.

import cgi
import Session
from xml.xslt import Processor
from xml.dom.DOMImplementation import implementation

form = cgi.FieldStorage()

if form.has_key( "message" ):

   # create DOM for error message   
   document = implementation.createDocument( None, None, None )
   error = document.createElement( "error" )
   message = document.createElement( "message" )
   message.appendChild( document.createTextNode(
      form[ "message" ].value ) )
   error.appendChild( message )
   document.appendChild( error )
   
   # process against XSLT style sheet
   processor = Processor.Processor()
   style = open( Session.getClientType()[ 0 ] + "/error.xsl" )
   processor.appendStylesheetStream( style )
   results = processor.runNode( document )
   style.close()

   # display content type and processed XML
   print Session.getContentType() + results

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