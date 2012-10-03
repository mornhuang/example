#!/usr/bin/python
import re

line = 'Subject: Re: happy hacking!!!'
p = re.compile('^Subject: (.*)')
m = p.match(line)
if m:
    print 'The subject is: ', m.group(1)
else:
    print 'No match'


