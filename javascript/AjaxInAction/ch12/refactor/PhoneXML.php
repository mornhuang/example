<?php
header("Content-type: text/xml");
echo("<?xml version='1.0' ?>\n");

$db = mysql_connect("localhost","ajax","action"); 
mysql_select_db("ajax",$db); 
$result = mysql_query("SELECT * FROM Contacts WHERE ContactName like '%".$_GET['q']."%'",$db); 
?>
<phonebook>
<?
if ($myrow = mysql_fetch_array($result)) {   
  do { 
?>
<entry id='<?=$myrow['id']?>001'>
  <company><?=$myrow['companyName']?></company>
  <contact><?=$myrow['contactName']?></contact>
  <country><?=$myrow['country']?></country>
  <phone><?=$myrow['phone']?></phone>
</entry>
<?
  }while ($myrow = mysql_fetch_array($result)); 
}else{
?>
<entry id='001'>
  <company>No Results</company>
  <contact>N/A</contact>
  <country>N/A</country>
  <phone>N/A</phone>
</entry>
<?
}
?>
</phonebook>
<iframe  width="0" height="0"></iframe>
