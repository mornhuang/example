#!/bin/bash
echo 'Enter A, B, or C: \c'
read letter
case $letter in
  A|a) echo 'You entered A.';;
  B|b) echo 'You entered B.';;
  C|c) echo 'You entered C.';;
  *) echo 'Not A, B, or C';;
esac

