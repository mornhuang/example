#!/bin/bash
echo 'word 1: '
read word1
echo 'word 2: '
read word2
echo 'word 3: '
read word3
if (test "$word1" = "$word2" -a "$word2" = "$word3")
  then
    echo 'Match: words 1, 2, & 3' 
elif (test "$word1" = "$word2")
  then
    echo 'Match: words 1 & 2'
elif (test "$word1" = "$word3")
  then
    echo 'Match: words 1 & 3'
elif (test "$word2" = "$word3")
  then
    echo 'Match: words 2 & 3'
else
    echo 'No match'
fi

