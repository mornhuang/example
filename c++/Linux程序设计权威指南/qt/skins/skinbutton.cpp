#include "skinbutton.h"
#include <qpainter.h>
#include <qpixmap.h>
#include <iostream>

SkinButton::SkinButton(QWidget *parent, const char *name, WFlags f)
	: QButton(parent, name, f), pixRegular(NULL), pixActive(NULL)
{
	QObject::connect(this, SIGNAL(pressed()), SLOT(slotPressed()));
	QObject::connect(this, SIGNAL(released()), SLOT(slotReleased()));
}

SkinButton::SkinButton(QPixmap *pixmap, QPixmap *active, 
	QWidget *parent, const char *name, WFlags f)
	: QButton(parent, name, f), pixRegular(pixmap), pixActive(active)
{
	setPixmap(*pixmap);
	QObject::connect(this, SIGNAL(pressed()), SLOT(slotPressed()));
	QObject::connect(this, SIGNAL(released()), SLOT(slotReleased()));
}

void SkinButton::drawButton(QPainter *p)
{
	const QPixmap *pm = pixmap();
	if(pm) p->drawPixmap(0, 0, *pm);
}

void SkinButton::slotPressed()
{
	setPixmap(*pixActive);
}

void SkinButton::slotReleased()
{
	setPixmap(*pixRegular);
}

void SkinButton::setPixmaps(QPixmap *regular, QPixmap *active)
{
	pixRegular = regular; 
	pixActive= active; 
	setPixmap(*pixRegular); 
}

