

#include "menu.h"
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
	MenuDemo menudemo;

	//设置尺寸
	menudemo.resize( 350, 250 );

	//设置窗口标题
	menudemo.setCaption( QObject::tr("菜单演示"));

	//设置为主窗口
	a.setMainWidget( &menudemo );

	//显示
	menudemo.show();

	//进入循环
	return a.exec();
}
