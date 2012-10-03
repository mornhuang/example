
#include <qwidget.h>
#include <qpixmap.h>
#include <qpen.h>

class QPoint;
class QPen;
class QPixmap;

class ScribbleDemo : public QWidget
{
Q_OBJECT
public:
	ScribbleDemo();

protected:
	virtual void mousePressEvent( QMouseEvent* );
	virtual void mouseMoveEvent( QMouseEvent* );
	virtual void paintEvent( QPaintEvent* );
	virtual void resizeEvent( QResizeEvent* );
private:
	QPoint last;
	QPixmap buffer;
	QPen pen;
};

