#!/usr/local/bin/python
# Fig. 23.9: allBooks.py
# Retrieve all books from database and store in session.
# Display book list to client by retrieving XML and converting
# to required format using browser-specific XSLT stylesheet.

import sys
import Book
import urllib
import Session
import MySQLdb
from xml.xslt import Processor
from xml.dom.DOMImplementation import implementation

# load Session
try:   
   session = Session.Session()
except Session.SessionError, message:  # invalid/no session ID
   Session.redirect( "error.py?message=%s" % message )
   sys.exit()

# setup mySQL statement
query = """SELECT ISBN, Title, EditionNumber, 
   Copyright, PublisherID, ImageFile, Price  
   FROM Titles ORDER BY Title"""

# attempt database connection and retrieve list of Books
try:

   # connect to the database, retrieve a cursor and execute query
   connection = MySQLdb.connect( db = "Books", user = "liperi",
      passwd = "liperi" )
   cursor = connection.cursor()
   cursor.execute( query )

   # acquire results and close database connection   
   results = cursor.fetchall()
   cursor.close()
   connection.close()

# error occurred, redirect to error page
except MySQLdb.OperationalError, message:

   # replace spaces with '+' for URL compatibility   
   message = urllib.quote_plus( str( message ) )
   Session.redirect( "error.py?message=%s" % message )
   sys.exit()

allBooks = []

# get row data
for row in results:

   # create new Book and set attributes   
   book = Book.Book()          
   book.isbn = row[ 0 ]           
   book.title = row[ 1 ]          
   book.editionNumber = row[ 2 ]  
   book.copyright = row[ 3 ]     
   book.publisherID = row[ 4 ]   
   book.imageFile = row[ 5 ]      
   book.price = row[ 6 ]          

   allBooks.append( book )  # one more Book created

session.data[ "titles" ] = allBooks

# generate XML
document = implementation.createDocument( None, None, None )
catalog = document.createElement( "catalog" )
document.appendChild( catalog )

# add all products to catalog
for book in allBooks:
   catalog.appendChild( book.getXML( document ) )

# process XML against XSLT stylesheet
processor = Processor.Processor()
style = open( session.data[ "agent" ] + "/allBooks.xsl" )
processor.appendStylesheetString( style.read() % \
   session.data[ "ID" ] )
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
