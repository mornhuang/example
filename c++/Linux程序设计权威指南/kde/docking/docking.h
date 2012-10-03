
#ifndef _DOCKING_H_
#define _DOCKING_H_

#include <qapp.h>
#include <qpixmap.h>


class DockWidget : public QWidget {

Q_OBJECT

public:
	DockWidget(const char *name=0);
	~DockWidget();

protected:
	void paintEvent(QPaintEvent *e);

private slots:

	void appexit();

public slots:
	void dock();
	void undock();
	void paintIcon();
	const bool isDocked();
    

private:
	bool docked;
	QPixmap pixmap;

};

#endif



