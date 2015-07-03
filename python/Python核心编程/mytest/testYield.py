#!/usr/bin/env python
# -*- coding: utf-8 -*-

__author__ = 'Morn'

"""
用于测试yield功能

"""


def counter(start=0):
    count = start
    while True:
        val = (yield count)
        if val is not None:
            count = val
        else:
            count += 1

if __name__ == '__main__':
    count = counter(5)

    print count.next()
    print count.next()
    print count.next()

    print count.send(9)
    print count.next()
    print count.next()
    print count.next()


