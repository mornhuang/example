#!/usr/bin/tclsh
set line "Subject: Re: happy hacking!!!"
if [regexp "^Subject: (.*)" $line all exp1] {
     puts "The subject is: $exp1"
 }
