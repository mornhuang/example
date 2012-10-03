
#include "toolbar.h"
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
	ToolbarDemo toolbardemo;

	//设置窗口标题
	toolbardemo.setCaption(QObject::tr("按钮条演示"));

	//设置为主窗口
	a.setMainWidget( &toolbardemo );

	//显示
	toolbardemo.show();

	//进入循环
	return a.exec();
}
