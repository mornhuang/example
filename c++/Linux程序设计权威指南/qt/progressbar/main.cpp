

#include "progressbar.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	ProgressBarDemo progressbardemo;

	//设置尺寸
	progressbardemo.resize( 350, 250 );

	//设置窗口标题
	progressbardemo.setCaption( QObject::tr("进度条演示") );

	//设置为主窗口
	a.setMainWidget( &progressbardemo );

	//显示
	progressbardemo.show();

	//进入循环
	return a.exec();
}
