
#include "rangecontrol.h"
#include <qapplication.h>
#include <qtextcodec.h>
#include <qmenubar.h>
#include <qpopupmenu.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	RangeControlDemo rangecontroldemo;

	//设置窗口标题
	rangecontroldemo.setCaption(QObject::tr("范围控制演示"));

	//设置为主窗口
	a.setMainWidget( &rangecontroldemo );

	//显示
	rangecontroldemo.show();

	//进入循环
	return a.exec();
}
