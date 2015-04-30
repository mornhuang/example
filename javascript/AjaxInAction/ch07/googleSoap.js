
function submitGuess(){

  if (!googleKey){
    alert("You will need to get a license key from google,\n and insert it into the script tag in the html file\n before this example will run.");
    return null;
  }
  var myGuess=document.Form1.yourGuess.value;

  var strSoap='<?xml version="1.0" encoding="UTF-8"?>\n\n<SOAP-ENV:Envelope'
    +' xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"'
    +' xmlns:xsi="http://www.w3.org/1999/XMLSchema-instance"'
    +' xmlns:xsd="http://www.w3.org/1999/XMLSchema">'
    +'<SOAP-ENV:Body><ns1:doGoogleSearch'
    +' xmlns:ns1="urn:GoogleSearch"'
    +' SOAP-ENV:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">'
    +'<key xsi:type="xsd:string">' + googleKey + '</key>'
    +'<q xsi:type="xsd:string">'+myGuess+'</q>'
    +'<start xsi:type="xsd:int">0</start>'
    +'<maxResults xsi:type="xsd:int">1</maxResults>'
    +'<filter xsi:type="xsd:boolean">true</filter>'
    +'<restrict xsi:type="xsd:string"></restrict>'
    +'<safeSearch xsi:type="xsd:boolean">false</safeSearch>'
    +'<lr xsi:type="xsd:string"></lr>'
    +'<ie xsi:type="xsd:string">latin1</ie>'
    +'<oe xsi:type="xsd:string">latin1</oe>'
    +'</ns1:doGoogleSearch>'
    +'</SOAP-ENV:Body>'
    +'</SOAP-ENV:Envelope>';

  var loader=new net.ContentLoader(
    "http://api.google.com/search/beta2",
    parseGoogleResponse,
    googleErrorHandler,
    "POST",
    strSoap,
    "text/xml",
    {
      Man:"POST http://api.google.com/search/beta2 HTTP/1.1",
      MessageType:"CALL"
    },
    true
  );

  var loader2=new net.ContentLoader(
    "http://www.google.com/",
    function(){
      this.debug("responded OK "+this.req.status);
    },
    function(){
      this.debug("responded ERR "+this.req.status);
    }
  );

}

function parseGoogleResponse(){
  var strDoc=this.req.responseText.toLowerCase();
  this.debug("parsed response text");
  document.getElementById('details').value=strDoc;
  var strStartTag = '<estimatedtotalresultscount xsi:type="xsd:int">';
  var strEndTag = '</estimatedtotalresultscount>';
  var spot1 = strDoc.indexOf(strStartTag);
  var spot2 = strDoc.indexOf(strEndTag);
  var strTotal1 = strDoc.substring(spot1 + strStartTag.length,spot2);
  var strOut = ""
  if(parseInt(strTotal1) >= intNum && parseInt(strTotal1)<= intNum + guessRange){
    strOut += "You guessed right!"
  }else{
    strOut += "WRONG! Try again!"
  }
  strOut += "<br/>Your search for <strong>" + document.Form1.yourGuess.value + "</strong> returned " + strTotal1 + " results!"
  document.getElementById("spanResults").innerHTML = strOut;
}

function googleErrorHandler(){
  alert("error contacting google");
}


