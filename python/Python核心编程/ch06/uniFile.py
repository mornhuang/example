#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
An example of reading and writing Unicode strings: Writes
a Unicode string to a file in utf-8 and reads it back in.
"""

CODEC = 'gbk'
FILE = 'unicode.txt'

hello_out = u"你好\n"
bytes_out = hello_out.encode(CODEC)
f = open(FILE, "w")
f.write(bytes_out)
f.close()

f = open(FILE, "r")
bytes_in = f.read()
f.close()
hello_in = bytes_in.decode(CODEC)
print hello_in,
