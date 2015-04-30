
function Portal( baseUrl, options ) {
   this.baseUrl    = baseUrl;
   this.options    = options;
   this.initDocumentMouseHandler();
}

Portal.LOGIN_ACTION          = "login";
Portal.LOAD_SETTINGS_ACTION  = "PageLoad";
Portal.SAVE_SETTINGS_ACTION  = "UpdateDragWindow";
Portal.ADD_WINDOW_ACTION     = "AddWindow";
Portal.DELETE_WINDOW_ACTION  = "DeleteWindow";

Portal.prototype = {

   login: function(userName, password) {

     this.userName = userName;
     this.password = password;

     if ( this.options.messageSpanId )
        document.getElementById(this.options.messageSpanId).innerHTML = "Verifying Credentials";

     this.issuePortalCommand( Portal.LOGIN_ACTION,
                              "user=" + this.userName,
                              "pass=" + this.password );
   },

   loadPage: function() {
     this.issuePortalCommand( Portal.LOAD_SETTINGS_ACTION,
                              "user=" + this.userName,
                              "pass=" + this.password );
   },

   ajaxUpdate: function(request) {
      this.runScript(request.responseText);
   },

   handleError: function(request) {
     if ( this.options.mesageSpanId )
        document.getElementById(this.options.messsageSpanId).innerHTML =
    "Oops! Server error.  Please try again later.";
   },

   runScript: function(scriptText) {
      eval(scriptText);
   },

   initDocumentMouseHandler: function() {
      var oThis = this;
      document.onmouseup = function() { oThis.handleMouseUp(); };
   },

   handleMouseUp: function() {
      bDrag    = false;
      bResize  = false;
      intLastX = -1;
      document.body.style.cursor = "default";

      if ( elemWin && bHasMoved )
         this.saveWindowProperties(elemWin.id);

      bHasMoved = false;
   },

   deleteWindow: function(id) {
      var doDelete =  confirm("Are you sure you want to delete this window?");
      if(doDelete)
         this.issuePortalCommand( Portal.DELETE_WINDOW_ACTION, "ref=" + id );
   },

   addWindow: function(title, url, x, y, w, h) {
      this.issuePortalCommand( Portal.ADD_WINDOW_ACTION,
             "title=" + title,
                               "url="   + url,
                               "x="     + x,
                               "y="     + y,
                               "w="     + w,
                               "h="     + h );

   },

   saveWindowProperties: function(id) {
      this.issuePortalCommand( Portal.SAVE_SETTINGS_ACTION,
                               "ref=" + id,
                               "x="   + parseInt(elemWin.style.left),
                               "y="   + parseInt(elemWin.style.top),
                               "w="   + parseInt(elemWin.style.width),
                               "h="   + parseInt(elemWin.style.height) );
      elemWin = null;
   },

   issuePortalCommand: function( commandName ) {
      var actionParam = this.options['actionParam'];
      var urlSuffix = this.options['urlSuffix'];
      if (!urlSuffix) urlSuffix="";
      var url = this.baseUrl;
      var callParms = [];
      if (actionParam){
        callParms.push( actionParam + "=" + commandName );
      }else{
        url += "/" + commandName + urlSuffix;
      }
      for ( var i = 1 ; i < arguments.length ; i++ )
         callParms.push( arguments[i] );
      var ajaxHelper = new net.ContentLoader( this, url, "POST", [] );
      ajaxHelper.sendRequest.apply( ajaxHelper, callParms );
   },

   updateStatus: function() {
      window.status = "Last Updated: " + new Date();
   }
}
