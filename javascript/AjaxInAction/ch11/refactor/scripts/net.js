var net=new Object();

net.ContentLoader=function( component, url, method, requestParams ) {
   this.component     = component;
   this.url           = url;
   this.requestParams = requestParams;
   this.method        = method;
}

net.ContentLoader.prototype = {

   getTransport: function() {
      var transport;
      if ( window.XMLHttpRequest )
         transport = new XMLHttpRequest();
      else if ( window.ActiveXObject ) {
         try {
            transport = new ActiveXObject('Msxml2.XMLHTTP');
         }
         catch(err) {
            transport = new ActiveXObject('Microsoft.XMLHTTP');
         }
      }
      return transport;
   },

   sendRequest: function() {

      var requestParams = []
      for ( var i = 0 ; i < arguments.length ;  i++ )
         requestParams.push(arguments[i]);

      var oThis = this;
      var request = this.getTransport();
      request.open( this.method, this.url, true );
      request.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded');
      request.onreadystatechange = function() { oThis.handleAjaxResponse(request) };
      request.send( this.queryString(requestParams) );
  },

  queryString: function(args) {

     var requestParams = [];
     for ( var i = 0 ; i < this.requestParams.length ; i++ )
        requestParams.push(this.requestParams[i]);
     for ( var j = 0 ; j < args.length ; j++ )
        requestParams.push(args[j]);

     var queryString = "";
     if ( requestParams && requestParams.length > 0 ) {
        for ( var i = 0 ; i < requestParams.length ; i++ )
           queryString += requestParams[i] + '&';
        queryString = queryString.substring(0, queryString.length-1);
     }
     return queryString;
  },

  handleAjaxResponse: function(request) {
     if ( request.readyState == 4 ) {
        if ( request.status == 200 || request.status == 0 )
           this.component.ajaxUpdate(request);
        else
           this.component.handleError(request);
     }
  }

};