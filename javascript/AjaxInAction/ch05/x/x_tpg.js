// x_tpg.js, part of X, a Cross-Browser.com Javascript Library
// Copyright (C) 2001,2002,2003,2004,2005 Michael Foster - Distributed under the terms of the GNU LGPL - OSI Certified
// File Rev: 5

/* xTabPanelGroup(id, w, h, th, clsTP, clsTG, clsTD, clsTS)
     id - id string of tabPanelGroup element.
     w - overall width.
     h - overall height.
     th - tab height.
     clsTP - tabPanel css class
     clsTG - tabGroup css class
     clsTD - tabDefault css class
     clsTS - tabSelected css class

   Assumes tabPanelGroup element (overall container) has a 1px border.
*/

function xTabPanelGroup(id, w, h, th, clsTP, clsTG, clsTD, clsTS) // object prototype
{
  var panelGrp = xGetElementById(id);
  if (!panelGrp) { return null; }
  var panels = xGetElementsByClassName(clsTP, panelGrp);
  var tabs = xGetElementsByClassName(clsTD, panelGrp);
  var tabGrp = xGetElementsByClassName(clsTG, panelGrp);
  if (!panels || !tabs || !tabGrp || panels.length != tabs.length || tabGrp.length != 1) { return null; }

  var selectedIndex = 0, highZ, x = 0, i;

  xResizeTo(panelGrp, w, h);

  xResizeTo(tabGrp[0], w, th);
  xMoveTo(tabGrp[0], 0, 0);
  
  w -= 2; // remove border widths
  var tw = w / tabs.length;

  for (i = 0; i < tabs.length; ++i) {
    xResizeTo(tabs[i], tw, th); 
    xMoveTo(tabs[i], x, 0);
    x += tw;
    tabs[i].xTabIndex = i;
    tabs[i].onclick = tabOnClick;
    xResizeTo(panels[i], w, h - th - 2); // -2 removes border widths
    xMoveTo(panels[i], 0, th);
  }
  highZ = i;
  tabs[0].onclick();
  
  function tabOnClick()
  {
    tabs[selectedIndex].className = clsTD;
    this.className = clsTS;
    xZIndex(this, highZ++);
    xZIndex(panels[this.xTabIndex], highZ++);
    selectedIndex = this.xTabIndex;
  }

  this.onUnload = function()
  {
    for (var i = 0; i < tabs.length; ++i) {tabs[i].onclick = null;}
  }
}
