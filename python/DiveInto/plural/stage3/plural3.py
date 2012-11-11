"""Pluralize English nouns (stage 3)

This program is part of "Dive Into Python", a free Python book for
experienced programmers.  Visit http://diveintopython.org/ for the
latest version.

Command line usage:
$ python plural3.py noun
nouns
"""

__author__ = "Mark Pilgrim (mark@diveintopython.org)"
__version__ = "$Revision: 1.1 $"
__date__ = "$Date: 2008/10/15 05:54:46 $"
__copyright__ = "Copyright (c) 2004 Mark Pilgrim"
__license__ = "Python"

import re

rules = \
  (
    (
     lambda word: re.search('[sxz]$', word),
     lambda word: re.sub('$', 'es', word)
     ),
    (
     lambda word: re.search('[^aeioudgkprt]h$', word),
     lambda word: re.sub('$', 'es', word)
    ),
    (
     lambda word: re.search('[^aeiou]y$', word),
     lambda word: re.sub('y$', 'ies', word)
    ),
    (
     lambda word: re.search('$', word),
     lambda word: re.sub('$', 's', word)
    )
  )
    
def plural(noun):
    for matchesRule, applyRule in rules:
        if matchesRule(noun):
            return applyRule(noun)

if __name__ == '__main__':
    import sys
    if sys.argv[1:]:
        print plural(sys.argv[1])
    else:
        print __doc__
