
#include "scribble.h"
#include <qapplication.h>
#include <qpen.h>
#include <qpainter.h>
#include <qpixmap.h>
#include <qwidget.h>

ScribbleDemo::ScribbleDemo()
{
	//设置透明色背景
	setBackgroundMode( NoBackground );
}


//当鼠标指针按下时, 记录鼠标位置
void ScribbleDemo::mousePressEvent( QMouseEvent* event )
{
	last = event->pos();
	pen = QPen(Qt::red, 3);
}


//当鼠标指针移动时, 同时在组件和缓冲区内画图
void ScribbleDemo::mouseMoveEvent( QMouseEvent* event )
{
	//建立两个画笔, 一个用于窗口, 一个用于缓冲区
	QPainter windowpainter;
	QPainter bufferpainter;

	//开始
	windowpainter.begin( this );
	bufferpainter.begin( &buffer );

	//设置红色的笔, 宽度为三个像素
	windowpainter.setPen( pen );
	bufferpainter.setPen( pen );
	windowpainter.drawLine( last, event->pos() );
	bufferpainter.drawLine( last, event->pos() );

	//结束
	windowpainter.end();
	bufferpainter.end();

	//记录位置
	last = event->pos();
}

//当需要重绘时, 把缓冲区内的内容拷贝到窗口中
void ScribbleDemo::paintEvent( QPaintEvent* event )
{
	bitBlt( this, 0, 0, &buffer );
}


//当窗口尺寸改变时, 使得缓冲区的内容总是和窗口尺寸一样大.
//为了不丢失信息, 把缓冲区保存起来, 然后改变缓冲区的大小, 
//再把保存的内容拷贝当缓冲区中.
void ScribbleDemo::resizeEvent( QResizeEvent* event )
{
	int w = width() > buffer.width() ?  width() : buffer.width();
	int h = height() > buffer.height() ?  height() : buffer.height();

	QPixmap tmp( buffer );
	buffer.resize( w, h );
	buffer.fill( colorGroup().base() );
	bitBlt( &buffer, 0, 0, &tmp, 0, 0, tmp.width(), tmp.height() );
}

