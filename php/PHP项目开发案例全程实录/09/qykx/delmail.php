<?php session_start();
$hostname=$_SESSION[host];
$username=$_SESSION[user];
$userpwd=$_SESSION[pwd];
if(!$mbox=@imap_open("$hostname","$username","$userpwd")){
   echo "<script>alert('µÇÂ¼³¬Ê±£¬ÇëÖØÐÂµÇÂ¼!');history.back();</script>";
   exit;
} 
$i=0;
while(list($name,$value)=each($_POST)){
  if(is_numeric($value)==true){
        $i+=$value;
		if(!@imap_delete($mbox,$value)){
		
		  echo "<script>alert('É¾³ýÊ§°Ü!');history.back();</script>";
		  exit;
		 
		}
  }		
}
if($i==0){
 echo "<script>alert('ÇëÑ¡ÔñÒªÉ¾³ýµÄÓÊ¼þ!');history.back();</script>";
 imap_close($mbox);
 exit;
}else{
  imap_expunge($mbox);
  imap_close($mbox);
  echo "<script>window.location.href='indexs.php?lmbs=É¾³ý'</script>";
}
?>