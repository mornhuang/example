

#include "button.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 14, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	LabelButton labelbutton;

	//设置尺寸
	labelbutton.resize( 500, 250 );

	//设置窗口标题
	labelbutton.setCaption( QObject::tr("标签和按钮的演示例子"));

	//设置为主窗口
	a.setMainWidget( &labelbutton );

	//显示
	labelbutton.show();

	//进入循环
	return a.exec();
}
