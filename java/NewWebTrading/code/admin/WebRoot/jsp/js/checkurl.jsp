<SCRIPT LANGUAGE="JavaScript">
   var parentId=['topFrame','leftFrame','right'];
   var i=0;
   
   for(i=0;i<parentId.length;i++)
    {    
     if(window.parent.document.getElementById(parentId[i])==null) 
      {     
		window.location.href="<%=request.getContextPath()%>/illegalOperat.do";
		break;		
      }
    } 
</SCRIPT> 
