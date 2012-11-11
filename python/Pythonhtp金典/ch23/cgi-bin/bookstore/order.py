#!/usr/local/bin/python
# Fig. 23.18: order.py
# Display order form to get information from customer

import sys
import Session

# load Session
try:
   session = Session.Session()   
except Session.SessionError, message:   # invalid/no session ID
   Session.redirect( "error.py?message=%s" % message )
   sys.exit()

# display content type and orderForm for specific client-type
content = open( "%s/orderForm.%s" % ( session.data[ "agent" ],
   session.data[ "extension" ] ) )
pageData = session.data[ "content type" ] + content.read() % \
   session.data[ "ID" ]
content.close()

# save Session data and display order form 
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
