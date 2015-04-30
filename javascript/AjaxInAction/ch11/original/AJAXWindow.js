
          var bDrag = false;
          var bResize = false;
          var bMaxed = false;
          var bHasMoved = false;
          var elemDrag;
          
          var intLastX = -1;
          var intLastY = -1;
          var elemWin;
          var intTopZIndex = 1;

          var yPos = 0;
          var xPos = 0;

          function getMouseXY(e){
            xPos = parseInt((e)?e.pageX:event.clientX + document.body.scrollLeft);
            yPos = parseInt((e)?e.pageY:event.clientY + document.body.scrollTop);
            
            if(bDrag){bHasMoved = true;MoveWindow();}
            if(bResize){bHasMoved = true;SetWindowSize();}
            
          }
          
          document.onmousemove = getMouseXY;
          
          document.onmouseup = function(){
            bDrag = false;
            bResize = false;
            intLastX = -1;
            document.body.style.cursor = "default";
            if(elemWin && bHasMoved)SaveWindowProperties(elemWin);
            bHasMoved = false;
          }
                   
          function DragWindow(xElemID){
            if(!bMaxed){
              elemWin = document.getElementById(xElemID);
              elemWin.style.zIndex = intTopZIndex++;
              document.body.style.cursor = "move";
              bDrag = true;
            }
          }    
          
          function MoveWindow(){
            if(intLastX==-1){
              intLastX = xPos;
              intLastY = yPos;
            }
            else{
              intChangeX = xPos - intLastX;
              intChangeY = yPos - intLastY;
              intLastX = xPos;
              intLastY = yPos;

              elemWin.style.left = intChangeX + parseInt(elemWin.style.left) + "px";
              elemWin.style.top = intChangeY + parseInt(elemWin.style.top) + "px";         
            }
          }
          
          function ResizeWindow(xElemID){
            if(!bMaxed){
              elemWin = document.getElementById(xElemID);
              elemWin.style.zIndex = intTopZIndex++;
              document.body.style.cursor = "move";
              bResize = true;
            }    
          }
                    
          function SetWindowSize(){
            if(intLastX==-1){
              intLastX = xPos;
              intLastY = yPos;
            }
            else{
              intChangeX = xPos - intLastX;
              intChangeY = yPos - intLastY;
              intLastX = xPos;
              intLastY = yPos;
              
			  if(intChangeX + parseInt(elemWin.style.width) >= 250)
                elemWin.style.width = intChangeX + parseInt(elemWin.style.width) + "px";
			  
              if(intChangeY + parseInt(elemWin.style.height) >= 250)
			    elemWin.style.height = intChangeY + parseInt(elemWin.style.height) + "px";
			                             
              SizeIframe(elemWin.id);          

            }
          }
          
          function HideWindow(x){
            xWin = document.getElementById(x).style.display = "none";          
          }
          
          function CreateWindow(xWinClass){
            var elemBody = document.body;
            objWin = document.createElement("div");
            objWin.id  = xWinClass.id;
            objWin.className = "divWindow";
            objWin.style.top = xWinClass.top + "px";
            objWin.style.left = xWinClass.left + "px";
            objWin.style.width = xWinClass.width + "px";
            objWin.style.height = xWinClass.height + "px";
                  
            var objHead = document.createElement("div");
            objHead.className = "dToolbar";
            objHead.onmousedown = new Function("DragWindow('" + xWinClass.id + "');");
            objHead.ondblclick = new Function("MaximizeWindow('" + xWinClass.id + "',0);");
            
            var objTitleText = document.createTextNode(xWinClass.title);
            
            var objTools = document.createElement("span")
            objTools.className = "ToolButtons";

            var objBtn0 = document.createElement("button");
            objBtn0.className = "ToolButtons";
            objBtn0.title = "Open In Own Window";
            objBtn0Text = document.createTextNode("w");
            objBtn0.onclick = new Function("OpenWindow('"+ xWinClass.id + "');");
            objBtn0.appendChild(objBtn0Text);
            
            var objBtn1 = document.createElement("button");
            objBtn1.className = "ToolButtons";
            objBtn1Text = document.createTextNode("O");
            objBtn1.onclick = new Function("MaximizeWindow('" + xWinClass.id + "', this);");
            objBtn1.appendChild(objBtn1Text);
            
            var objBtn2 = document.createElement("button");
            objBtn2.className = "ToolButtons";
            objBtn2Text = document.createTextNode("X");
            objBtn2.onclick = new Function("HideWindow('" + xWinClass.id + "');");
            objBtn2.appendChild(objBtn2Text);
            
            var objIf = document.createElement("iframe");
            objIf.id = "if_" + xWinClass.id;
            objIf.src = xWinClass.src;
            objIf.className = "ifContent";
            objIf.style.width = xWinClass.width + "px"
            objIf.style.height = (xWinClass.height - 35) + "px"
            
            var objFoot = document.createElement("div");
            objFoot.className = "winFoot";
            
            var objFootSpan = document.createElement("span");
            objFootSpan.className = "winFootSpan";
            objFootSpan.innerHTML = "&nbsp;";
            objFootSpan.onmousedown = new Function("bDrag=false;bResize=false;ResizeWindow('" + xWinClass.id + "');");
            objFoot.appendChild(objFootSpan);      
                       
            objTools.appendChild(objBtn0);
            objTools.appendChild(objBtn1);
            objTools.appendChild(objBtn2);
            objHead.appendChild(objTools);
            objHead.appendChild(objTitleText);
            objWin.appendChild(objHead);
            objWin.appendChild(objIf);
            objWin.appendChild(objFoot);
            elemBody.appendChild(objWin);
          }
          
          function NewWin(xID,xXPos,xYPos,xWid,xHei,xUrl,xTitle){
            this.id = xID;
            this.top = xYPos;
            this.left = xXPos;
            this.width = xWid;
            this.height = xHei;
            this.src = xUrl;
            this.title = xTitle;
          }
          
          function MaximizeWindow(xWinID, xButton, resized){

            objWin = document.getElementById(xWinID);
            if(!objWin.oldParms || resized){

              bMaxed = true;
              
              if(!resized){
                var x = objWin.style.left;
                var y = objWin.style.top;
                var w = objWin.style.width;
                var h = objWin.style.height;
            
                objWin.oldParms = x + "," + y + "," + w + "," + h;
              }
                 
              var broWidth = 800;
              var broHeight = 600;
              var headerHeight = 100;
            
              if(typeof(window.innerWidth) == 'number'){
                broWidth = window.innerWidth;
                broHeight = window.innerHeight;
              }
              else if(document.documentElement){
                broWidth = document.documentElement.clientWidth;
                broHeight = document.documentElement.clientHeight;
              }
              else if(document.body){
                broWidth = document.body.clientWidth;
                broHeight = document.body.clientHeight;
              }
            
              broWidth = parseInt(broWidth);
              broHeight = parseInt(broHeight);
                  
              objWin.style.width = broWidth - 4 + "px";
              objWin.style.left = "0px";

              objWin.style.height = (broHeight-headerHeight - 4)+ "px";
              objWin.style.top = headerHeight + "px";
            
              window.onresize = new Function("MaximizeWindow('" + xWinID + "',0,1);");
              
              if(xButton) xButton.value = "o";
              else objWin.getElementsByTagName("button")[1].value = "o";
              
              
            }
            else{
            
              bMaxed = false;
            
              strParms = objWin.oldParms.split(",");
              
              objWin.style.width = strParms[2];
              objWin.style.left = strParms[0];

              objWin.style.height = strParms[3];
              objWin.style.top = strParms[1];
              
              objWin.oldParms = null;
              window.onresize = null;
              
              if(xButton) xButton.value = "O";
              else objWin.getElementsByTagName("button")[1].value = "O";
              
            }
            SizeIframe(xWinID)
          }
          
          function SizeIframe(xWinID){
            var objIF = document.getElementById("if_" + xWinID);
            var objWin = document.getElementById(xWinID);
            var intW = objWin.style.width;
            var newW = "100%";
            var newH = "100%";
            if(intW.indexOf("%") == -1){            
              var intH = parseInt(objWin.style.height);
              newW = intW;
              newH = (intH - 35) + "px";
            }
            objIF.style.width = newW;
            objIF.style.height = newH;
          }
          
          function OpenWindow(xWinID){
            strURL = document.getElementById("if_" + xWinID).src;
            var winPop = window.open(strURL);
          }
