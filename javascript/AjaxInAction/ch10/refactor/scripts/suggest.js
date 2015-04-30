

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


//-------------------- mTextSuggest.js

TextSuggest = Class.create();

TextSuggest.prototype = {

   initialize: function(anId, url, options) {
      this.id          = anId;
      var browser = navigator.userAgent.toLowerCase();
      this.isIE        = browser.indexOf("msie") != -1;
      this.isOpera     = browser.indexOf("opera")!= -1;
      this.textInput   = $(this.id);
      this.suggestions = [];
      this.setOptions(options);
      this.initAjax(url);

      this.injectSuggestBehavior();
   },

   initAjax: function(url) {
      ajaxEngine.registerRequest( this.id + '_request', url );
      ajaxEngine.registerAjaxObject( this.id + '_updater', this );
   },

   setOptions: function(options) {
      this.options = {
         suggestDivClassName: 'suggestDiv',
         suggestionClassName: 'suggestion',
         matchClassName     : 'match',
         matchTextWidth     : true,
         selectionColor     : '#b1c09c',
         matchAnywhere      : false,
         ignoreCase         : false,
         count              : 10
      }.extend(options || {});
   },

   injectSuggestBehavior: function() {

      if ( this.isIE )
         this.textInput.autocomplete = "off";

      var keyEventHandler = new TextSuggestKeyHandler(this);
      new Insertion.After( this.textInput,
                           '<input type="text" id="'+this.id+'_preventtsubmit'+'" style="display:none"/>' );
      new Insertion.After( this.textInput,
                           '<input type="hidden" name="'+this.id+'_hidden'+'" id="'+this.id+'_hidden'+'"/>' );

      this.createSuggestionsDiv();
   },

   handleTextInput: function() {
     var previousRequest    = this.lastRequestString;
     this.lastRequestString = this.textInput.value;
     if ( this.lastRequestString == "" )
        this.hideSuggestions();
     else if ( this.lastRequestString != previousRequest ) {
        this.sendRequestForSuggestions();
     }
   },

   moveSelectionUp: function() {
      if ( this.selectedIndex > 0 ) {
         this.updateSelection(this.selectedIndex - 1);
      }
   },

   moveSelectionDown: function() {
      if ( this.selectedIndex < (this.suggestions.length - 1)  ) {
         this.updateSelection(this.selectedIndex + 1);
      }
   },

   updateSelection: function(n) {
      var span = $( this.id + "_" + this.selectedIndex );
      if ( span ){
         span.style.backgroundColor = "";
      }
      this.selectedIndex = n;
      var span = $( this.id + "_" + this.selectedIndex );
      if ( span ){
         span.style.backgroundColor = this.options.selectionColor;
      }
   },

   sendRequestForSuggestions: function() {

     if ( this.handlingRequest ) {
        this.pendingRequest = true;
        return;
     }

     this.handlingRequest = true;
     this.callRicoAjaxEngine();
   },

   callRicoAjaxEngine: function() {
      var callParms = [];
      callParms.push( this.id + '_request');
      callParms.push( 'id='             + this.id);
      callParms.push( 'count='          + this.options.count);
      callParms.push( 'query='          + this.lastRequestString);
      callParms.push( 'match_anywhere=' + this.options.matchAnywhere);
      callParms.push( 'ignore_case='    + this.options.ignoreCase);

      var additionalParms = this.options.requestParameters || [];
      for( var i=0 ; i < additionalParms.length ; i++ )
         callParms.push(additionalParms[i]);

      ajaxEngine.sendRequest.apply( ajaxEngine, callParms );
   },

   ajaxUpdate: function( ajaxResponse ) {

      this.createSuggestions( ajaxResponse );

      if ( this.suggestions.length == 0 ) {
         this.hideSuggestions();
         $( this.id + "_hidden" ).value = "";
      }
      else {
         this.updateSuggestionsDiv();
         this.showSuggestions();
         this.updateSelection(0);
      }

      this.handlingRequest = false;

      if ( this.pendingRequest ) {
         this.pendingRequest    = false;
         this.lastRequestString = this.textInput.value;
         this.sendRequestForSuggestions();
      }
   },

   createSuggestions: function(ajaxResponse) {
      this.suggestions = [];
      var entries = ajaxResponse.getElementsByTagName('entry');
      for ( var i = 0 ; i < entries.length ; i++ ) {
         var strText  = this.getElementContent(entries[i].getElementsByTagName('text')[0]);
         var strValue = this.getElementContent(entries[i].getElementsByTagName('value')[0]);
         this.suggestions.push( { text: strText, value: strValue } );
      }
   },

   setInputFromSelection: function() {
     var hiddenInput = $( this.id + "_hidden" );
     var suggestion  = this.suggestions[ this.selectedIndex ];

     this.textInput.value = suggestion.text;
     hiddenInput.value    = suggestion.value;
     this.hideSuggestions();
   },

   showSuggestions: function() {
      var divStyle = this.suggestionsDiv.style;
      if ( divStyle.display == '' )
         return;
      this.positionSuggestionsDiv();
      divStyle.display = '';
   },

   positionSuggestionsDiv: function() {
      var textPos = RicoUtil.toDocumentPosition(this.textInput);
      var divStyle = this.suggestionsDiv.style;
      divStyle.top  = (textPos.y + this.textInput.offsetHeight) + "px";
      divStyle.left = textPos.x + "px";

      if ( this.options.matchTextWidth )
         divStyle.width = (this.textInput.offsetWidth- this.padding()) + "px";
   },

   padding: function() {
     try{
      var styleFunc = RicoUtil.getElementsComputedStyle;
      var lPad    = styleFunc( this.suggestionsDiv, "paddingLeft",      "padding-left" );
      var rPad    = styleFunc( this.suggestionsDiv, "paddingRight",     "padding-right" );
      var lBorder = styleFunc( this.suggestionsDiv, "borderLeftWidth",  "border-left-width" );
      var rBorder = styleFunc( this.suggestionsDiv, "borderRightWidth", "border-right-width" );

      lPad    = isNaN(lPad)    ? 0 : lPad;
      rPad    = isNaN(rPad)    ? 0 : rPad;
      lBorder = isNaN(lBorder) ? 0 : lBorder;
      rBorder = isNaN(rBorder) ? 0 : rBorder;

      return parseInt(lPad) + parseInt(rPad) + parseInt(lBorder) + parseInt(rBorder);
     }catch (e){
      return 0;
     }
   },

   hideSuggestions: function() {
      this.suggestionsDiv.style.display = 'none';
   },

   createSuggestionsDiv: function() {
      this.suggestionsDiv = document.createElement("div");
      this.suggestionsDiv.className = this.options.suggestDivClassName;

      var divStyle = this.suggestionsDiv.style;
      divStyle.position = 'absolute';
      divStyle.zIndex   = 101;
      divStyle.display  = "none";

      this.textInput.parentNode.appendChild(this.suggestionsDiv);
   },

   updateSuggestionsDiv: function() {
      this.suggestionsDiv.innerHTML = "";
      var suggestLines = this.createSuggestionSpans();
      for ( var i = 0 ; i < suggestLines.length ; i++ )
         this.suggestionsDiv.appendChild(suggestLines[i]);
   },

   createSuggestionSpans: function() {
      var regExpFlags = "";
      if ( this.options.ignoreCase )
         regExpFlags = 'i';
      var startRegExp = "^";
      if ( this.options.matchAnywhere )
         startRegExp = '';

      var regExp  = new RegExp( startRegExp + this.lastRequestString, regExpFlags );

      var suggestionSpans = [];
      for ( var i = 0 ; i < this.suggestions.length ; i++ )
         suggestionSpans.push( this.createSuggestionSpan( i, regExp ) )

      return suggestionSpans;
   },

   createSuggestionSpan: function( n, regExp ) {
      var suggestion = this.suggestions[n];

      var suggestionSpan = document.createElement("span");
      suggestionSpan.className = this.options.suggestionClassName;
      suggestionSpan.style.width   = '100%';
      suggestionSpan.style.display = 'block';
      suggestionSpan.id            = this.id + "_" + n;
      suggestionSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      suggestionSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      var textValues = this.splitTextValues( suggestion.text,
                                             this.lastRequestString.length,
                                             regExp );

      var textMatchSpan = document.createElement("span");
      textMatchSpan.id            = this.id + "_match_" + n;
      textMatchSpan.className     = this.options.matchClassName;
      textMatchSpan.onmouseover   = this.mouseoverHandler.bindAsEventListener(this);
      textMatchSpan.onclick       = this.itemClickHandler.bindAsEventListener(this);

      textMatchSpan.appendChild( document.createTextNode(textValues.mid) );

      suggestionSpan.appendChild( document.createTextNode( textValues.start ) );
      suggestionSpan.appendChild( textMatchSpan );
      suggestionSpan.appendChild( document.createTextNode( textValues.end ) );

      return suggestionSpan;
   },

   mouseoverHandler: function(e) {
      var src = e.srcElement ? e.srcElement : e.target;
      var index = parseInt(src.id.substring(src.id.lastIndexOf('_')+1));
      this.updateSelection(index);
   },

   itemClickHandler: function(e) {
      this.mouseoverHandler(e);
      this.hideSuggestions();
      this.textInput.focus();
   },

   splitTextValues: function( text, len, regExp ) {
      var startPos  = text.search(regExp);
      var matchText = text.substring( startPos, startPos + len );
      var startText = startPos == 0 ? "" : text.substring(0, startPos);
      var endText   = text.substring( startPos + len );
      return { start: startText, mid: matchText, end: endText };
   },

   getElementContent: function(element) {
      return element.firstChild.data;
   }
}

TextSuggestKeyHandler = Class.create();

TextSuggestKeyHandler.prototype = {

   initialize: function( textSuggest ) {
      this.textSuggest = textSuggest;
      this.input       = this.textSuggest.textInput;
      this.addKeyHandling();
   },

   addKeyHandling: function() {
      this.input.onkeyup    = this.keyupHandler.bindAsEventListener(this);
      this.input.onkeydown  = this.keydownHandler.bindAsEventListener(this);
      this.input.onblur     = this.onblurHandler.bindAsEventListener(this);
      if ( this.isOpera )
         this.input.onkeypress = this.keyupHandler.bindAsEventListener(this);
   },

   keydownHandler: function(e) {
      var upArrow   = 38;
      var downArrow = 40;

      if ( e.keyCode == upArrow ) {
         this.textSuggest.moveSelectionUp();
         setTimeout( this.moveCaretToEnd.bind(this), 1 );
      }
      else if ( e.keyCode == downArrow ){
         this.textSuggest.moveSelectionDown();
      }
   },

   keyupHandler: function(e) {
      if ( this.input.length == 0 && !this.isOpera )
         this.textSuggest.hideSuggestions();

     if ( !this.handledSpecialKeys(e) )
        this.textSuggest.handleTextInput();
   },

   handledSpecialKeys: function(e) {
      var enterKey  = 13;
      var upArrow   = 38;
      var downArrow = 40;

      if ( e.keyCode == upArrow || e.keyCode == downArrow ) {
         return true;
      }
      else if ( e.keyCode == enterKey ) {
         this.textSuggest.setInputFromSelection();
         return true;
      }

      return false;
   },

   moveCaretToEnd: function() {
      var pos = this.input.value.length;
      if (this.input.setSelectionRange) {
         this.input.setSelectionRange(pos,pos);
      }
      else if(this.input.createTextRange){
         var m = this.input.createTextRange();
         m.moveStart('character',pos);
         m.collapse();
         m.select();
      }
   },

   onblurHandler: function(e) {
      if ( this.textSuggest.suggestionsDiv.style.display == '' )
         this.textSuggest.setInputFromSelection();
      this.textSuggest.hideSuggestions();
   }

};

