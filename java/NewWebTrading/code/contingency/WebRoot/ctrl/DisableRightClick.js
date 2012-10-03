/* Disable Right Click Mechanism */


var message="";
function clickIE() {if (document.all) {(message);return false;}}
function clickNS(e) {if
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {(message);return false;}}}
if (document.layers)
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}


/* Form Field Validations */

function IsEmpty(instring) {
    if (instring.length==0) {
        return true;
    }
    for (var i=0; i<instring.length; i++) {
        if (instring.substr(i,1)!=" ") {
            return false;
        }
    }
    return true;
}

function IsNumeric(passedVal) {
    for (i=0; i<passedVal.length; i++) {
        if (passedVal.charAt(i) < "0") {
            return false;
        }
        if (passedVal.charAt(i) > "9") {
            return false;
        }
    }
    return true;
}

function round(number,X) {
    // rounds number to X decimal places, defaults to 2
    X = (!X ? 2 : X);
    return Math.round(number*Math.pow(10,X))/Math.pow(10,X);
}

function isValidDate(format, strDate) {

    if (format=="yyyy/MM/dd"){
        // (\d{1,2}) means 4 or 12
         // (\/|-) means either (/ or -), 4-12 or 4/12
         // NOTE: we have to escape / (\/)
         // or else pattern matching will interpret it to mean the end instead of the literal "/"
         // \2 use the 2nd placeholder (\/|-) "here"
         // (\d{2}|\d{4}) means 02 or 2002

         var datePat = /^(\d{4})(\/)(\d{2})\2(\d{2})$/;
         var matchArray = strDate.match(datePat);

         if (matchArray == null) return false;
         // matchArray[0] will be the original entire string, for example, 4-12-02 or 4/12/2002
         var year = matchArray[1];
         var month = matchArray[3];
         var day = matchArray[4];

         if (month < 1 || month > 12) return false;
         if (day < 1 || day > 31) return false;
         if ((month == 4 || month == 6 || month==9 || month == 11) && day == 31) return false;
         if (month == 2) {
              var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

              if (day > 29 || (day == 29 && !isleap)) return false;
         }
		 if (year < 1900)
			 return false;
    }
    else {
        return false;
    }
     return true;
}

/*
// Email Address Validation - v0.1
function isValidEmail(EmailValue) {
    if ((EmailValue == "") || (EmailValue.indexOf ('@', 0) < 1) || (EmailValue.indexOf ("'", 0) > 1))
           error = 1;
    else {
        test = EmailValue.indexOf('.', EmailValue.indexOf ('@', 0))
        if (test != -1)
                {
                    error = 0;
                }
        else	{
                    error=1;
                }
    }

    if (error == 1)
        {
            return false
        }
    else
        {
            new_length = EmailValue.length - test
            if (new_length == 4 || new_length == 3 || (new_length >= 5 && EmailValue.indexOf ('.', (test+1)) != -1))
                    {
                        return true;
                    }
            else
                    return false;
        }
    return true
}
*/

// Email Address Validation - v0.2
/* 
Source URL: http://javascript.internet.com/forms/email-address-validation.html
Changes: 
1.1.4: Fixed a bug where upper ASCII characters (i.e. accented letters international characters) were allowed.
1.1.3: Added the restriction to only accept addresses ending in two letters (interpreted to be a country code) or one of the known TLDs (com, net, org, edu, int, mil, gov, arpa), including the
new ones (biz, aero, name, coop, info, pro, museum).  One can easily update the list (if ICANN adds even more TLDs in the future) by updating the knownDomsPat variable near the
top of the function.  Also, I added a variable at the top of the function that determines whether or not TLDs should be checked at all.  This is good if you are using this function
internally (i.e. intranet site) where hostnames don't have to  conform to W3C standards and thus internal organization e-mail addresses don't have to either. Changed some of the logic so that the function will work properly
with Netscape 6.
1.1.2: Fixed a bug where trailing . in e-mail address was passing (the bug is actually in the weak regexp engine of the browser; I simplified the regexps to make it work).
1.1.1: Removed restriction that countries must be preceded by a domain, so abc@host.uk is now legal.  However, there's still the restriction that an address must end in a two or three letter
word.
1.1: Rewrote most of the function to conform more closely to RFC 822.
1.0: Original
*/

function isValidEmail(emailStr) {
	/* The following variable tells the rest of the function whether or not
	to verify that the address ends in a two-letter country or well-known
	TLD.  1 means check it, 0 means don't. */
	var checkTLD = 0;

	/* The following is the list of known TLDs that an e-mail address must end with. */
	var knownDomsPat=/^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;

	/* The following pattern is used to check if the entered e-mail address
	fits the user@domain format.  It also is used to separate the username
	from the domain. */
	var emailPat=/^(.+)@(.+)$/;

	/* The following string represents the pattern for matching all special
	characters.  We don't want to allow special characters in the address. 
	These characters include ( ) < > @ , ; : \ " . [ ] */
	var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";

	/* The following string represents the range of characters allowed in a 
	username or domainname.  It really states which chars aren't allowed.*/
	var validChars="\[^\\s" + specialChars + "\]";

	/* The following pattern applies if the "user" is a quoted string (in
	which case, there are no rules about which characters are allowed
	and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
	is a legal e-mail address. */
	var quotedUser="(\"[^\"]*\")";

	/* The following pattern applies for domains that are IP addresses,
	rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
	e-mail address. NOTE: The square brackets are required. */
	var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;

	/* The following string represents an atom (basically a series of non-special characters.) */
	var atom=validChars + '+';

	/* The following string represents one word in the typical username.
	For example, in john.doe@somewhere.com, john and doe are words.
	Basically, a word is either an atom or quoted string. */
	var word="(" + atom + "|" + quotedUser + ")";

	// The following pattern describes the structure of the user
	var userPat=new RegExp("^" + word + "(\\." + word + ")*$");

	/* The following pattern describes the structure of a normal symbolic
	domain, as opposed to ipDomainPat, shown above. */
	var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");

	/* Finally, let's start trying to figure out if the supplied address is valid. */

	/* Begin with the coarse pattern to simply break up user@domain into
	different pieces that are easy to analyze. */
	var matchArray=emailStr.match(emailPat);

	if (matchArray==null) {
		/* Too many/few @'s or something; basically, this address doesn't
		even fit the general mould of a valid e-mail address. */
		return false;
	}
	var user=matchArray[1];
	var domain=matchArray[2];

	// Start by checking that only basic ASCII characters are in the strings (0-127).
	for (i=0; i<user.length; i++) {
		if (user.charCodeAt(i)>127) {
			return false;
		}
	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127) {
			return false;
		}
	}

	// See if "user" is valid 
	if (user.match(userPat)==null) {
		// user is not valid
		return false;
	}

	/* if the e-mail address is at an IP address (as opposed to a symbolic
	host name) make sure the IP address is valid. */
	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		// this is an IP address
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				return false;
			}
		}
		return true;
	}

	// Domain is symbolic name.  Check if it's valid.
	var atomPat=new RegExp("^" + atom + "$");
	var domArr=domain.split(".");
	var len=domArr.length;
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			return false;
		}
	}

	/* domain name seems valid, but now make sure that it ends in a
	known top-level domain (like com, edu, gov) or a two-letter word,
	representing country (uk, nl), and that there's a hostname preceding 
	the domain or country. */
	if (checkTLD && domArr[domArr.length-1].length!=2 && domArr[domArr.length-1].search(knownDomsPat)==-1) {
		return false;
	}

	// Make sure there's a host name preceding the domain.
	if (len<2) {
		return false;
	}

	// If we've gotten this far, everything's valid!
	return true;
}

/* Popup window */

function popupWindow(url, winName, features) {
    var winName = window.open(url, winName, features);
}

/* Check start date is always earlier than end date */

function isStartDateEarlierThanEndDate(startDate, endDate) {
    var tmpStartDate = new Date(startDate);
    var tmpEndDate = new Date(endDate);
    if (tmpStartDate > tmpEndDate)
        return false;
    else
        return true;
}

/* Check  input string containing Chinese characters  */
/* Added on 2003/10/08 (SL) */

function hasChinese(inString) {
	for(i=0;i<inString.length;i++) {
		charat0 = inString.charCodeAt(i);
		if (!((charat0 >= 0) && (charat0 <= 127))) {	
			return true;
		}
	}
	return false;
}	

/* Check input string containing numbers and alphabets only */
/* Added on 2003/10/08 (SL) */

function isAlphaNumeric(inString) {
	for(i=0;i<inString.length;i++) {
		charat0 = inString.charCodeAt(i);
		// Check chararcter must be alphanumeric - number, uppercase letter, lowercase letter
		if (!( ((charat0 >= 48) && (charat0 <= 57)) || ( (charat0 >= 65) && (charat0 <= 90)) || ((charat0 >= 97) && (charat0 <= 122)))) 	
			return false;
	}
	return true;		
}	

function validatorUserId(uName){
	var u_name = /^[A-Za-z0-9]{5,20}$/;
	if(!u_name.test(uName)){
		return false;
	}
	return true;
}
	