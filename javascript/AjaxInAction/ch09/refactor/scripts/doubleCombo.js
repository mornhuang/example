
//-------------------- mContentLoader.js
var net = new Object();

net.READY_STATE_UNINITIALIZED= 0;
net.READY_STATE_LOADING      = 1;
net.READY_STATE_LOADED       = 2;
net.READY_STATE_INTERACTIVE  = 3;
net.READY_STATE_COMPLETE     = 4;

net.ContentLoader = function( component, url, method, requestParams ) {
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

      //if ( window.netscape && window.netscape.security.PrivilegeManager.enablePrivilege)
      //   netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');

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
     if ( request.readyState == net.READY_STATE_COMPLETE ) {
        if ( this.isSuccess(request) )
           this.component.ajaxUpdate(request);
        else
           this.component.handleError(request);
     }
  },

  isSuccess: function(request) {
    return  request.status == 0 
        || (request.status >= 200 && request.status < 300);
  }

};


//-------------------- mDoubleCombo.js

function DoubleCombo( masterId, slaveId, url, options ) {
   this.master     = document.getElementById(masterId);
   this.slave      = document.getElementById(slaveId);
   this.options    = options;
   this.ajaxHelper = new net.ContentLoader( this, url, "POST", options.requestParameters || [] );

   this.initializeBehavior();
}

DoubleCombo.prototype = {

   initializeBehavior: function() {
      var oThis = this;
      this.master.onchange = function() { oThis.masterComboChanged(); };
   },

   masterComboChanged: function() {
      var query = this.master.options[this.master.selectedIndex].value;
      this.ajaxHelper.sendRequest( 'query=' + query );
   },

   ajaxUpdate:  function(request) {
      var slaveOptions = this.createOptions(request.responseXML.documentElement);
      this.slave.length = 0;
      var optionsObj = this.slave.options;
      for ( var i = 0 ; i < slaveOptions.length ; i++ )
         optionsObj.add( slaveOptions[i] );
   },

   createOptions: function(ajaxResponse) {
      var newOptions = [];
      var entries = ajaxResponse.getElementsByTagName('entry');
      for ( var i = 0 ; i < entries.length ; i++ ) {
         var text  = this.getElementContent(entries[i],'optionText');
         var value = this.getElementContent(entries[i],'optionValue');
         newOptions.push( new Option(text, value) );
      }
      return newOptions;
   },

   handleError: function(request) {
      if ( this.options.errorHandler )
         this.options.errorHandler(request);
   },

   getElementContent: function(element,tagName) {
      var childElement = element.getElementsByTagName(tagName)[0];
      return childElement.text != undefined ? childElement.text : childElement.textContent;
   }

};


