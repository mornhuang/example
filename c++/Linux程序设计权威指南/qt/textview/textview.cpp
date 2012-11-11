	/* File: helloworld.c */

	#include <qapplication.h>
	#include <qtextview.h>
	#include <qtextstream.h>
	#include <qfile.h>
	#include <qtextcodec.h>

	int main( int argc, char **argv )
	{
		//定义Application
		QApplication a( argc, argv ); 
		a.setFont(QFont("Times", 16, QFont::Normal));
		a.setDefaultCodec( QTextCodec::codecForName("GBK") );

		//建立按钮
		QTextView tview;

		//设置按钮尺寸
		tview.resize( 640, 480 ); 


		//读文件
		QFile f("index.html");
		QString s;
		if ( f.open(IO_ReadOnly) ) {
			QTextStream t( &f );
			while ( !t.eof() ) {
				s += t.readLine();
			}
			f.close();
		}

		tview.setText(s);

		//在Application中加入按钮
		a.setMainWidget( &tview );

		//显示按钮
		tview.show();

		//进入循环
		return a.exec();
	}
