"""Pluralize English nouns (stage 5)

This program is part of "Dive Into Python", a free Python book for
experienced programmers.  Visit http://diveintopython.org/ for the
latest version.

Command line usage:
$ python plural5.py noun
nouns
"""

__author__ = "Mark Pilgrim (mark@diveintopython.org)"
__version__ = "$Revision: 1.1 $"
__date__ = "$Date: 2008/10/15 05:54:46 $"
__copyright__ = "Copyright (c) 2004 Mark Pilgrim"
__license__ = "Python"

import re
import string

def buildRule((pattern, search, replace)):
    return lambda word: re.search(pattern, word) and re.sub(search, replace, word)

def plural(noun, language='en'): 
    lines = file('rules.%s' % language).readlines()
    patterns = map(string.split, lines)
    rules = map(buildRule, patterns)
    for rule in rules:
        result = rule(noun)
        if result: return result

if __name__ == '__main__':
    import sys
    if sys.argv[1:]:
        print plural(sys.argv[1])
    else:
        print __doc__
