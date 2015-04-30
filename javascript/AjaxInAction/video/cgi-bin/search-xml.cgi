#!/usr/bin/perl # -*- cperl -*-
use strict;
use warnings;

use CGI qw/:standard/;

our %planet_db;

do 'db';

my $query = CGI->new();

my $description = $query->param("description");

my @planets;

if ($description) {
  foreach my $planet_name (keys %planet_db) {
    if ($planet_db{$planet_name}->{description} =~ /$description/) {
      push @planets, { (planet_name => $planet_name,
                        %{ $planet_db{$planet_name} }) };
    }
  }
}

print CGI->header(-type => "text/xml");

convert_to_xml(sort { $a->{planet_name} cmp $b->{planet_name} } @planets);

sub convert_to_xml {
  print "<planets>\n";
  foreach my $planet (@_) {
    print "  <planet>\n";
    print "    <name>$planet->{planet_name}</name>\n";
    print "    <size>$planet->{size}</size>\n";
    print "    <description>$planet->{description}</description>\n";
    print "  </planet>\n";
  }
  print "</planets>\n";
}
