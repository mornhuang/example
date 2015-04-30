#!/usr/bin/perl # -*- cperl -*-
use strict;
use warnings;

use CGI qw/:standard/;
use HTML::Template;

our %planet_db;

do 'db';

my $template = HTML::Template->new(filename => "planet-info.template");

my $query = CGI->new();

my $planet_name = $query->param("planet_name");

if ($planet_name) {
  $template->param(planet_name => $planet_name,
                   description => $planet_db{$planet_name}->{description});
}

print CGI->header(-type => "text/xml");

print $template->output;
