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



//-------------------- mLiveSearch.js

function LiveSearch( pageURL, lookupField, xmlURL, xsltURL, options ) {
   this.pageURL      = pageURL;
   this.lookupField  = lookupField;
   this.xmlURL       = xmlURL;
   this.xsltURL      = xsltURL;
   this.setOptions(options);

   var oThis = this;
   lookupField.form.onsubmit = function() { oThis.doSearch(); return false; };
   this.initialize();
}

LiveSearch.prototype = {

   doSearch: function() {
      if ( XSLTHelper.isXSLTSupported() )
         this.doAjaxSearch();
      else
         this.submitTheForm();
   },

   setOptions: function(options) {
      this.options = options;

      if ( !this.options.loadingImage        ) this.options.loadingImage = 'images/loading.gif';
      if ( !this.options.bookmarkContainerId ) this.options.bookmarkContainerId = 'bookmark';
      if ( !this.options.resultsContainerId  ) this.options.resultsContainerId  = 'results';
      if ( !this.options.bookmarkText        ) this.options.bookmarkText = 'Bookmark Search';
   },

   initialize: function() {
      var currentLocation  = document.location.href;
      var qIndex = currentLocation.indexOf('q=');
      if ( qIndex != -1 ) {
         this.lookupField.value = currentLocation.substring( qIndex + 2 );
   this.doSearch();
      }
   },

   doAjaxSearch: function() {
      this.showLoadingImage();
      var searchUrl = this.appendToUrl( this.xmlURL, 'q', this.lookupField.value );
      new XSLTHelper( searchUrl, this.xsltURL ).loadView( this.options.resultsContainerId );
      this.updateBookmark();
   },

   submitTheForm: function() {
      var searchForm = this.lookupField.form;
      searchForm.onsubmit = function() { return true; };
      searchForm.submit();
   },

   showLoadingImage: function() {
      var newImg = document.createElement('img');
      newImg.setAttribute('src', this.options.loadingImage );
      document.getElementById(this.options.resultsContainerId).appendChild(newImg);
   },

   appendToUrl: function(url, name, value) {
      var separator = '?';
      if (url.indexOf(separator) > 0)
         separator = '&';

      return url + separator + name + '=' + value;
   },

   updateBookmark: function() {
      var container = document.getElementById(this.options.bookmarkContainerId);
      var bookmarkURL = this.appendToUrl( this.pageURL, 'q', this.lookupField.value );
      if ( container )
         container.innerHTML = '<a href="' + bookmarkURL + '" >' +
               this.options.bookmarkText + '</a>';
   }
}


//-------------------- mxsltHelper.js

function XSLTHelper( xmlURL, xslURL  ) {
   this.xmlURL = xmlURL;
   this.xslURL = xslURL;
}

XSLTHelper.isXSLTSupported = function() {
   return (window.XMLHttpRequest && window.XSLTProcessor ) ||
          XSLTHelper.isIEXmlSupported();
}

XSLTHelper.isIEXmlSupported = function() {
   if ( ! window.ActiveXObject )
      return false;
   try { new ActiveXObject("Microsoft.XMLDOM");  return true; }
   catch(err) { return false; }
}

XSLTHelper.prototype = {

   loadView: function(container) {
      if ( ! XSLTHelper.isXSLTSupported() )
         return;

      this.xmlDocument   = null;
      this.xslStyleSheet = null;
      this.container     = $(container);

      new Ajax.Request( this.xmlURL,
                        {onComplete: this.setXMLDocument.bind(this)} );
      new Ajax.Request( this.xslURL,
                        {method: "GET",
                        onComplete: this.setXSLDocument.bind(this)} );
   },

   setXMLDocument: function(request) {
      this.xmlDocument = request.responseXML;
      this.updateViewIfDocumentsLoaded();
   },

   setXSLDocument: function(request) {
      this.xslStyleSheet = request.responseXML;
      this.updateViewIfDocumentsLoaded();
   },

   updateViewIfDocumentsLoaded: function() {
      if ( this.xmlDocument == null || this.xslStyleSheet == null )
         return;
      this.updateView();
   },

   updateView: function () {
      if ( ! XSLTHelper.isXSLTSupported() )
         return;

     if ( window.XMLHttpRequest && window.XSLTProcessor )
         this.updateViewMozilla();
      else if ( window.ActiveXObject )
         this.updateViewIE();
   },

   updateViewMozilla: function() {
      var xsltProcessor = new XSLTProcessor();
      xsltProcessor.importStylesheet(this.xslStyleSheet);
      var fragment = xsltProcessor.transformToFragment(this.xmlDocument, document);

      this.container.innerHTML = "";
      this.container.appendChild(fragment);
   },

   updateViewIE: function(container) {
      this.container.innerHTML = this.xmlDocument.transformNode(this.xslStyleSheet);
   }

}
