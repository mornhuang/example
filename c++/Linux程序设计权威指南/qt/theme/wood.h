/****************************************************************************
** $Id: wood.h,v 1.1 2008/12/04 15:58:54 huangmeng Exp $
**
** Definition of something or other
**
** Created : 979899
**
** Copyright (C) 1997 by Troll Tech AS.  All rights reserved.
**
****************************************************************************/

#ifndef WOOD_H
#define WOOD_H

#include <qwindowsstyle.h>
#include <qpalette.h>

class NorwegianWoodStyle : public QWindowsStyle
{
public:
    NorwegianWoodStyle( int sbext = -1 );
    void polish( QApplication*);
    void polish( QWidget* );
    void unPolish( QWidget* );
    void unPolish( QApplication*);

    void drawButton( QPainter *p, int x, int y, int w, int h,
			     const QColorGroup &g, bool sunken = FALSE,
			     const QBrush *fill = 0 );
    void drawBevelButton( QPainter *p, int x, int y, int w, int h,
			  const QColorGroup &g, bool sunken = FALSE,
			  const QBrush *fill = 0 );
    QRect buttonRect( int x, int y, int w, int h);
    void drawButtonMask( QPainter *p, int x, int y, int w, int h);
    void drawComboButton( QPainter *p, int x, int y, int w, int h,
				    const QColorGroup &g,
				    bool /* sunken */,
				    bool editable,
				    bool /*enabled */,
				    const QBrush *fb );


    void drawPushButton( QPushButton* btn, QPainter *p);
    void drawPushButtonLabel( QPushButton* btn, QPainter *p);
    void drawScrollBarControls( QPainter*,  const QScrollBar*, int sliderStart, uint controls, uint activeControl );

private:
    void drawSemicircleButton(QPainter *p, const QRect &r, int dir, 
			      bool sunken, const QColorGroup &g );
    QPalette oldPalette;
    QPixmap *sunkenDark;
    QPixmap *sunkenLight;

};

#endif
