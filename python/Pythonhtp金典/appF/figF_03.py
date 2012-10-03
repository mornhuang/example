# Fig. F.3: figF_03.py                         
# Unicode encoding for 10 different languages.  

welcomes = [ u"Unicode Encoding" ]

welcomes.append(  u"In English:\u0020\u0057\u0065\u006C\u0063" +
   u"\u006F\u006D\u0065\u0020\u0074\u006F\u0020Unicode\u0021" )

welcomes.append( u"In French:\u0020\u0042\u0069\u0065\u006E" +
   u"\u0076\u0065\u006E\u0075\u0065\u0020\u0061\u0075" +
   u"\u0020Unicode\u0021" )

welcomes.append( u"In German:\u0020\u0057\u0069\u006C\u006B" +
   u"\u006F\u006D\u006D\u0065\u006E\u0020\u007A\u0075\u0020" +
   u"Unicode\u0021" )

welcomes.append( u"In Japanese:\u0020Unicode\u3078\u3087" +
   u"\u3045\u3053\u305D\u0021" )

welcomes.append( u"In Kannada:\u0020\u0CB8\u0CC1\u0CB8\u0CCD" +
   u"\u0CB5\u0C97\u0CA4\u0020Unicode\u0021" )

welcomes.append( u"In Portuguese:\u0020\u0053\u00E9\u006A" +
   u"\u0061\u0020\u0042\u0065\u006D\u0076\u0069\u006E\u0064" +
   u"\u006F\u0020Unicode\u0021" )

welcomes.append( u"In Russian:\u0020\u0414\u043E\u0431\u0440" +
   u"\u043E\u0020\u043F\u043E\u0436\u0430\u043B\u043E\u0432" +
   u"\u0430\u0442\u044A\u0020\u0432\u0020Unicode\u0021" )

welcomes.append( u"In Simplified Chinese:\u0020\u6B22\u8FCE" +
   u"\u4F7F\u7528\u0020Unicode\u0021" )

welcomes.append( u"In Spanish:\u0020\u0042\u0069\u0065\u006E" +
   u"\u0076\u0065\u006E\u0069\u0064\u006F\u0020\u0061\u0020" +
   u"Unicode\u0021" )

welcomes.append( u"In Telugu:\u0020\u0C38\u0C41\u0C38\u0C3E" +
   u"\u0C35\u0C17\u0C24\u0C02\u0020Unicode\u0021" )

for welcome in welcomes:
    print welcome.encode( "utf-8" )
    print 

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
