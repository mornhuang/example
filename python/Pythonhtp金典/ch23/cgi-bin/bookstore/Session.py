# Fig. 23.1: Session.py
# Contains a Session class that keeps track of an http session
# by assigning a session ID and pickling session information.

import os
import sha
import cgi
import time
import urlparse
import urllib
import cPickle

def getClientType():
   """Return client type and corresponding file extension"""

   # search environment variables for identifying strings   
   if os.environ[ "HTTP_USER_AGENT" ].find( "MSIE" ) > -1 or \
      os.environ[ "HTTP_USER_AGENT" ].find( "Netscape" ) > -1:

      # MSIE and Netscape represent XHTML clients (.html)
      return ( "xhtml", "html" )

   elif os.environ[ "HTTP_ACCEPT" ].find(
      "text/vnd.wap.wml" ) > -1:

      # text/vnd.wap.wml represents WML clients (.wml)
      return ( "wml", "wml" )

   else:

      # otherwise, assume XHTML Basic client (.html)
      return ( "xhtml_basic", "html" )

def getContentType():
   """Return the contents of the client's contentType.txt file"""

   # obtain contentType.txt located in client's subfolder
   try:   
      file = open( getClientType()[ 0 ] + "/contentType.txt" )
   except:
      raise SessionError( "Missing file: contentType.txt" )

   contentType = file.read()
   file.close()
   return contentType

def redirect( URL ):
   """Redirect the client to a relative URL"""

   # use urljoin to append full path to relative URL   
   print "Location: %s\n" % \
      urlparse.urljoin( "http://" + os.environ[ "HTTP_HOST" ] +
         os.environ[ "REQUEST_URI" ], URL )

class SessionError( Exception ):
   """User-defined exception for Session class"""

   def __init__( self, error ):
      """Set error message"""

      # use quote_plus to replace spaces with '+'
      self.error = urllib.quote_plus( error )

   def __str__( self ):
      """Return error message"""
      
      return self.error

class IDError( Exception ):
   """User-defined exception for Session class"""

   pass

class Session:
   """Session class keeps track of an HTTP session"""

   def __init__( self, createNew = 0 ):
      """Create a new session or load an existing session"""

      # create new session
      if createNew:
         self.sessionID = self.generateID()
         self.fileName = os.getcwd() + "/sessions/." + \
            self.sessionID

         # newly generated ID already exists
         if self.sessionExists():
            raise IDError

         self.data = {}  # dictionary is empty

         # store ID, empty cart, and content type and agent type
         self.data[ "ID" ] = self.sessionID 
         self.data[ "cart" ] = {}
         self.data[ "content type" ] = getContentType()
         self.data[ "agent" ], self.data[ "extension" ] = \
            getClientType()

      # attempt to load previously created session      
      else:

         # session ID is passed in query string         
         queryString = cgi.parse_qs( os.environ[ "QUERY_STRING" ] )

         # no ID has been supplied in query string
         if not queryString.has_key( "ID" ):
            raise SessionError( "No ID given" )
          
         self.sessionID = queryString[ "ID" ][ 0 ]
         self.fileName = os.getcwd() + "/sessions/." + \
            self.sessionID

         # supplied ID is invalid         
         if not self.sessionExists():
            raise SessionError( "Nonexistent ID given" )

         # load pickled session dictionary
         self.loadSession()

   def sessionExists( self ):
      """Determine if the specified session file exists"""

      return os.path.exists( self.fileName )

   def loadSession( self ):
      """Unpickle dictionary of existing session"""
      
      if self.sessionExists():
         sessionFile = open( self.fileName )
         data = cPickle.load( sessionFile )
         sessionFile.close()
         self.data = data

   def saveSession( self ):
      """Pickle session dictionary to session file"""
      
      sessionFile = open( self.fileName, "w" )
      cPickle.dump( self.data, sessionFile )
      sessionFile.close()

   def deleteSession( self ):
      """Delete session file"""
      
      os.remove( self.fileName )

   def generateID( self ):
      """Use sha to generate a unique ID"""

      # generate ID using time, client address and port            
      randomString = str( time.time() ) + \
         os.environ[ "REMOTE_ADDR" ] + os.environ[ "REMOTE_PORT" ]
      ID = sha.new( randomString )
      return ID.hexdigest()

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
