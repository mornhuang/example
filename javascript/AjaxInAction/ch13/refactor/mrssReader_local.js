RSSReader = Class.create();
RSSReader.prototype = {

   initialize: function( readerId, options ) {
      this.id              = readerId;
      this.transitionTimer = null;
      this.paused          = false;
      this.visibleLayer    = 0;
      this.setOptions(options);
      this.start();
   },

   setOptions: function(options) {
      this.options = {
          slideTransitionDelay: 7000,
          fadeDuration        : 300,
          errorHTML           : '<hr/>Error retrieving content.<br/>'
      }.extend(options);
   },

   start: function() {
      this.applyButtonBehaviors();
      new Effect.FadeTo( this.getLayer(1), 0.0, 1, 1, {} );
      this.loadRSSFeed(0,true);
      this.startSlideShow(false);
   },

   getLayer: function(n) {
      var contentArea = $(this.id+'_content');
      var children = contentArea.childNodes;
      var j = 0;
      for ( var i = 0 ; i < children.length ; i++ ) {
         if ( children[i].tagName && children[i].tagName.toLowerCase() == 'div' ) {
            if ( j == n ) return children[i];
            j++;
         }
      }
      return null;
   },

   applyButtonBehaviors: function() {
      $(this.id + '_prevBtn').onclick  = this.previous.bind(this);
      $(this.id + '_nextBtn').onclick  = this.next.bind(this);
      $(this.id + '_pauseBtn').onclick = this.pause.bind(this);
      $(this.id + '_addBtn').onclick   = this.addFeed.bind(this);
   },

   hasPrevious: function() {
      return !(this.feedIndex == 0 && this.itemIndex == 0);
   },

   hasNext: function() {
      return !(this.feedIndex == this.options.rssFeeds.length - 1 &&
               this.itemIndex == this.currentFeed.items.length - 1);
   },

   previous: function() {
      if ( !this.hasPrevious() ) return;

      var requiresLoad = this.itemIndex == 0;
      this.fadeOut( this.visibleLayer, Prototype.emptyFunction );
      this.visibleLayer = (this.visibleLayer + 1 ) % 2;
      if ( requiresLoad )
         this.loadRSSFeed( this.feedIndex - 1, false );
      else
         setTimeout( this.previousPartTwo.bind(this), parseInt(this.options.fadeDuration/4) );
   },
   previousPartTwo: function() { this.itemIndex--; this.updateView(); },

   next: function() {
      if ( !this.hasNext() ) return;
      var requiresLoad =  this.itemIndex == (this.currentFeed.items.length - 1);
      this.fadeOut( this.visibleLayer, Prototype.emptyFunction );
    this.visibleLayer = (this.visibleLayer + 1) % 2;
      if ( requiresLoad )
         this.loadRSSFeed( this.feedIndex + 1, true );
      else
         setTimeout( this.nextPartTwo.bind(this), parseInt(this.options.fadeDuration/4) );
   },
   nextPartTwo: function() { this.itemIndex++; this.updateView(); },

   pause: function() {
      if ( this.paused )
         this.startSlideShow(true);
      else
         clearTimeout( this.transitionTimer );
      this.paused = !this.paused;
   },

   addFeed: function() {
      var selectBox = $(this.id + '_newFeeds');
      var feedToAdd = selectBox.options[ selectBox.selectedIndex ].value;
      this.options.rssFeeds.push(feedToAdd);
   },

   fadeIn: function( layer, onComplete )  { this.fadeTo( 0.9999, layer, onComplete );  },
   fadeOut: function( layer, onComplete ) { this.fadeTo( 0.0001, layer, onComplete ); },
   fadeTo: function( n, layer, onComplete ) {
       new Effect.FadeTo( this.getLayer(layer), n, this.options.fadeDuration, 12, {complete: onComplete} );
   },

   startSlideShow: function(resume) {
      var delay = resume ? 1 : this.options.slideTransitionDelay;
      this.transitionTimer = setTimeout( this.nextSlide.bind(this), delay );
   },

   nextSlide: function() {
      if ( this.hasNext() )
         this.next();
      else
         this.loadRSSFeed(0, true);

      this.transitionTimer = setTimeout( this.nextSlide.bind(this),
                                         this.options.slideTransitionDelay );
   },

   loadRSSFeed: function(feedIndex, forward) {
      this.feedIndex = feedIndex;
      this.itemIndex = forward ? 0 : "last";
      new net.ContentLoader( this, this.options.rssFeeds[feedIndex], "GET", [] ).sendRequest();
   },

   ajaxUpdate: function(request) {
/*
      if ( window.netscape && window.netscape.security.PrivilegeManager.enablePrivilege)
         netscape.security.PrivilegeManager.enablePrivilege('UniversalBrowserRead');
*/
      this.currentFeed = RSSFeed.parseXML(request.responseXML.documentElement);
      if ( this.itemIndex == "last" )
         this.itemIndex = this.currentFeed.items.length - 1;
      this.updateView();
   },

   handleError: function(request) {
      this.getLayer(this.visibleLayer).innerHTML = this.options.errorHTML;
   },

   updateView: function() {
      var rssItemView =  new RSSItemView( this.currentFeed.items[this.itemIndex],
                                          this.feedIndex,
                                          this.itemIndex,
                                          this.options.rssFeeds.length );

      this.getLayer(this.visibleLayer).innerHTML = rssItemView;
      this.fadeIn( this.visibleLayer, this.bringVisibleLayerToTop.bind(this) );
   },

   bringVisibleLayerToTop: function() {
      this.getLayer(this.visibleLayer).style.zIndex = 10;
      this.getLayer((this.visibleLayer+1)%2).style.zIndex = 5;
   }

};

RSSFeed = Class.create();

RSSFeed.parseXML = function(xmlDoc) {

   var rssFeed = new RSSFeed( RSSFeed.getFirstValue(xmlDoc, 'title'),
                              RSSFeed.getFirstValue(xmlDoc, 'link' ),
                              RSSFeed.getFirstValue(xmlDoc, 'description' ) );

   var feedItems = xmlDoc.getElementsByTagName('item');
   for ( var i = 0 ; i < feedItems.length ; i++ ) {
      rssFeed.addItem(new RSSItem(rssFeed,
                                  RSSFeed.getFirstValue(feedItems[i], 'title'),
                                  RSSFeed.getFirstValue(feedItems[i], 'link' ),
                                  RSSFeed.getFirstValue(feedItems[i], 'description')));
   }
   return rssFeed;
}

RSSFeed.getFirstValue = function(element, tagName) {
   var children = element.getElementsByTagName(tagName);
   if ( children == null || children.length == 0 )
      return "";
   if ( children[0].firstChild && children[0].firstChild.nodeValue )
      return children[0].firstChild.nodeValue;
    return "";
}

RSSFeed.prototype = {

   initialize: function( title, link, description ) {
      this.title       = title;
      this.link        = link;
      this.description = description;
      this.items       = [];
   },

   addItem: function(anItem) {
      this.items.push(anItem);
   }
};


RSSItem = Class.create();
RSSItem.prototype = {
   initialize: function( rssFeed, title, link, description ) {
      this.rssFeed     = rssFeed;
      this.title       = title;
      this.link        = link;
      this.description = description;
   }
};

RSSItemView = Class.create();

RSSItemView.prototype = {

   initialize: function(rssItem, feedIndex, itemIndex, numFeeds) {
      this.rssItem = rssItem;
      this.feedIndex = feedIndex + 1;
      this.itemIndex = itemIndex + 1;
      this.numFeeds  = numFeeds;
   },

   toHTML: function() {
      var out = ""
      out += '<span class="rssFeedTitlePrompt">RSS Feed '
      out += '(' + this.feedIndex + ' of ' + this.numFeeds + ') : ';
      out += '</span>';
      out += '<span class="rssFeedTitle">';
      out += '<a href="' + this.rssItem.rssFeed.link + '">' + this.rssItem.rssFeed.title + '</a>';
      out += '</span>';
      out += '<br/>';
      out += '<span class="rssFeedItemTitlePrompt">Article ';
      out += '(' + this.itemIndex + ' of ' + this.rssItem.rssFeed.items.length + ') : ';
      out += '</span>';
      out += '<span class="rssFeedItemTitle">';
      out += '<a href="' + this.rssItem.link + '">' + this.rssItem.title  + '</a>';
      out += '</span>';

      out += '<div class="rssItemContent">';
      out += this.rssItem.description;
      out += '</div>';

      return out;
   },

   toString: function() {
      return this.toHTML();
   }
};