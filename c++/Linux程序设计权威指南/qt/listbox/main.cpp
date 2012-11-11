

#include "listbox.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	ListDemo listdemo;

	//设置尺寸
	listdemo.resize( 350, 250 );

	//设置窗口标题
	listdemo.setCaption( QObject::tr("列表演示"));

	//设置为主窗口
	a.setMainWidget( &listdemo );

	//显示
	listdemo.show();

	//进入循环
	return a.exec();
}
