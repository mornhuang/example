BEGIN{
print "This is the beginning of the AWK program"
count=1;
}
{
print "This is the "count" line";
count ++;
}
END{
print "This is the end!"
}

