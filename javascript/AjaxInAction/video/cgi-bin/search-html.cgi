#!/usr/bin/perl # -*- cperl -*-
use strict;
use warnings;

use CGI qw/:standard/;
use HTML::Template;

our %planet_db;

do 'db';

my $template = HTML::Template->new(filename => "search-html.template");

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

$template->param(planets => [ sort { $a->{planet_name} cmp $b->{planet_name} } @planets ] );

print CGI->header(-type => "text/xml");

print $template->output;
