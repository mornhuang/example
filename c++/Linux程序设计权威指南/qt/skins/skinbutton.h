
#ifndef SKINBUTTON_H
#define SKINBUTTON_H


#include <qbutton.h>
class QPixmap;
class QPainter;

class SkinButton : public QButton
{
Q_OBJECT
public:
	SkinButton(QPixmap *pixmap, QPixmap *active, 
		QWidget *parent = 0, const char *name = 0, WFlags f = 0);
	SkinButton(QWidget *parent = 0, const char *name = 0, WFlags f = 0);
	void  setPixmaps(QPixmap *regular, QPixmap *active);
protected:
	QPixmap *pixRegular;
	QPixmap *pixActive;
	virtual void drawButton(QPainter *p);
protected slots:
	void slotPressed();
	void slotReleased(); 
};

#endif
