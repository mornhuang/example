

#include "combobox.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	ComboboxDemo comboboxdemo;

	//设置尺寸
	comboboxdemo.resize( 350, 250 );

	//设置窗口标题
	comboboxdemo.setCaption( QObject::tr("QComboBox 演示"));

	//设置为主窗口
	a.setMainWidget( &comboboxdemo );

	//显示
	comboboxdemo.show();

	//进入循环
	return a.exec();
}
