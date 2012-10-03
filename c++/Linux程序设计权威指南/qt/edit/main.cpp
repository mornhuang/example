

#include "edit.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	EditDemo editdemo;

	//设置尺寸
	editdemo.resize( 350, 250 );

	//设置窗口标题
	editdemo.setCaption( QObject::tr("输入条和编辑区演示"));

	//设置为主窗口
	a.setMainWidget( &editdemo );

	//显示
	editdemo.show();

	//进入循环
	return a.exec();
}
