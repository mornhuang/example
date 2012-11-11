#!/usr/local/bin/python
# Fig. 23.6: bookstore.py
# Create a new Session for client.

import sys
import time
import Session

session = None  # Session not yet created

# attempt to create Session three times
for i in range( 3 ):

   # create new Session
   try:
      session = Session.Session( 1 )

   # ID already exists      
   except Session.IDError:                 
      time.sleep( 0.1 )  # wait 0.1 seconds -- try again

   # missing content type
   except Session.SessionError, message:   
      Session.redirect( "error.py?message=%s" % message )
      sys.exit()

   else:
      break  # Session created successfully

# if successful, save Session and re-direct to allBooks.py
if session: 
   nextPage = "allBooks.py?ID=%s" % session.data[ "ID" ]
   session.saveSession()
   Session.redirect( nextPage )
else:
   Session.redirect( "error.py?message=Unable+to+create+Session" )

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
