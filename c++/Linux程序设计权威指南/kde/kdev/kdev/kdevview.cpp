/***************************************************************************
                          kdevview.cpp  -  description
                             -------------------
    begin                : ËÄ 09 21 05:30:27 CST 2000
    copyright            : (C) 2000 by 
    email                : 
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

// include files for Qt
#include <qprinter.h>
#include <qpainter.h>

// application specific includes
#include "kdevview.h"
#include "kdevdoc.h"


KdevView::KdevView(KdevDoc* pDoc, QWidget *parent, const char* name, int wflags)
 : QWidget(parent, name, wflags)
{
    doc=pDoc;
}

KdevView::~KdevView()
{
}

KdevDoc *KdevView::getDocument() const
{
	return doc;
}

void KdevView::update(KdevView* pSender){
	if(pSender != this)
		repaint();
}

void KdevView::print(QPrinter *pPrinter)
{
  if (pPrinter->setup(this))
  {
		QPainter p;
		p.begin(pPrinter);
		
		///////////////////////////////
		// TODO: add your printing code here
		///////////////////////////////
		
		p.end();
  }
}

void KdevView::closeEvent(QCloseEvent*)
{
  // LEAVE THIS EMPTY: THE EVENT FILTER IN THE KdevApp CLASS TAKES CARE FOR CLOSING
  // QWidget closeEvent must be prevented.
}