#include <qwidget.h>
#include <qpainter.h>

class DrawDemo : public QWidget
{
Q_OBJECT

public:
	DrawDemo( QWidget *parent = 0, const char *name = 0 );
	~DrawDemo();

	void paintEvent( QPaintEvent * );
private:
	void drawPrimitives( QPainter *p );
	void drawStrings( QPainter *p );


};

