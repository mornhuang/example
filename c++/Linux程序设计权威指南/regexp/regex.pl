#!/usr/bin/perl -w
$line="Subject: Re: happy hacking!!!";
if ( $line =~ m/^Subject: (.*)/ ) {
    print "The subject is: $1\n";
}

