	var isNav4, isIE4;

	if (parseInt(navigator.appVersion.charAt(0)) >= 4) {
	  isNav4 = (navigator.appName == "Netscape") ? true : false;
	  if (parseInt(navigator.appVersion.charAt(0)) == 5) {
	  	isIE5 = true
	  }   
	  else 
	  	isIE4 = (navigator.appName.indexOf("Microsoft") != -1) ? true : false;
	}

	if (isNav4) {
		layerRef="document.layers";
		styleSwitch="";
		visibleVar="show";
		nyear = 1900;
	} else if (isIE4) {
		layerRef="document.all";
		styleSwitch=".style";
		visibleVar="visible";
	} else if (isIE5) {
		layerRef="document.all";
		styleSwitch="";
	}

        var pre = 0
        layershow = false

        function layeron(num) {
                if (pre != num) {
                        if (!isNav4)
                                eval(layerRef + '.cell' + pre + '.style.background="FFC927"')
                        eval(layerRef + '["menu' + pre +'"]' + styleSwitch + '.visibility="hidden"')
                        if (!isNav4)
                                eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                        eval(layerRef + '["menu' + num +'"]' + styleSwitch + '.visibility="visible"')
                        layershow = true
                }
                else {
                        var temp = (!layershow) ? "visible" : "hidden"
                        eval(layerRef + '["menu' + num +'"]' + styleSwitch + '.visibility="' + temp + '"')

                        if (!isNav4) {
                                if (layershow)
                                        eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                                else
                                        eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                        }
                        layershow = !layershow
                }
                pre = num
        }

        function layerover(num) {
                if (pre != num || !layershow) {
                        eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                        eval(layerRef + '.cell' + num + '.style.cursor="hand"')
                }
        }
        function layerout(num) {
                if (pre != num || !layershow)
                        eval(layerRef + '.cell' + num + '.style.background="FFC927"')
        }


        function layeron2(num) {

                if (pre != num && num != 5 && num != 8 && num != 7 && num != 6) {
                        if (!isNav4)
                                eval(layerRef + '.cell' + pre + '.style.background="FFC927"')
                                if (!isNav4)
                                eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                                }
                else {
                        if (!isNav4 && num != 5 && num != 8 && num != 7 && num != 6) {
                                if (layershow)
                                        eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                                else
                                        eval(layerRef + '.cell' + num + '.style.background="FFC927"')
                        }
                }
                if(num==5){
                }

                if(num==4) {
                }
                if(num==8) {
                    var msg = 'Logout?';
                    if (layeron2.arguments.length > 1) {
                        msg = layeron2.arguments[1] != '' ? layeron2.arguments[1] : msg;
                    }
                    if (confirm(msg)) {
                        temp=1
                        top.location="logout.do"
                    }
                }
                if (num==6) {
                        temp=1
                }

                if (num==7){
                        temp=1
                        top.location="../gb/index.jsp"
                }
        }
        
