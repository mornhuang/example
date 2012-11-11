#include "draw.h"
#include <qpainter.h>
#include <qbrush.h>
#include <qtextcodec.h>

DrawDemo::DrawDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{
	setBackgroundColor( white );
}
DrawDemo::~DrawDemo()
{
}

void DrawDemo::drawPrimitives(QPainter *p)
{
	QBrush b1( Qt::blue );
	QBrush b2( Qt::green, Qt::Dense6Pattern );
	QBrush b3( Qt::NoBrush );
	QBrush b4( Qt::CrossPattern );

	//红色
	p->setPen( Qt::red );
	p->setBrush( b1 );
	p->drawRect( 10, 10, 200, 100 );

	//红色, 图案
	p->setBrush( b2 );
	p->drawRoundRect( 10, 120, 200, 100, 20, 20 );

	//缺省
	p->setBrush( b3 );
	p->drawEllipse( 10, 240, 200, 100 );

	//图案
	p->setBrush( b4 );
	p->drawPie( 10, 360, 200, 100, 45*16, 90*16 );
}

void DrawDemo::drawStrings(QPainter *p)
{
	static const char *fonts[] = { "Helvetica", "Courier", "Times", 0 };
	static int   sizes[] = { 12, 14, 16, 24, 36, 0 };
	int f = 0;
	int y = 0;
	p->setPen( Qt::blue );
	while ( fonts[f] ) {
		int s = 0;
		while ( sizes[s] ) {
			//设置字体
			QFont font( fonts[f], sizes[s] );
			p->setFont( font );

			//取字体属性
			QFontMetrics fm = p->fontMetrics();

			y += fm.ascent();
			p->drawText( 250, y, tr("如何看待GPL软件?") );
			y += fm.descent();
			s++;
		}
		f++;
	}

}


//当需要绘制时激活
void DrawDemo::paintEvent( QPaintEvent * )
{
	QPainter paint( this );

	//画图元
	drawPrimitives( &paint );

	//画字符串
	drawStrings( &paint );
}

