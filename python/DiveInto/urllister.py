"""Extract list of URLs in a web page

This program is part of "Dive Into Python", a free Python book for
experienced programmers.  Visit http://diveintopython.org/ for the
latest version.
"""

__author__ = "Mark Pilgrim (mark@diveintopython.org)"
__version__ = "$Revision: 1.1 $"
__date__ = "$Date: 2008/10/15 05:54:46 $"
__copyright__ = "Copyright (c) 2001 Mark Pilgrim"
__license__ = "Python"

from sgmllib import SGMLParser

class URLLister(SGMLParser):
	def reset(self):
		SGMLParser.reset(self)
		self.urls = []

	def start_a(self, attrs):
		href = [v for k, v in attrs if k=='href']
		if href:
			self.urls.extend(href)

if __name__ == "__main__":
	import urllib
	usock = urllib.urlopen("http://diveintopython.org/")
	parser = URLLister()
	parser.feed(usock.read())
	parser.close()
	usock.close()
	for url in parser.urls: print url
