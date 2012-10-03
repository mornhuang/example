
#include <qapplication.h>
#include <qtextcodec.h>
#include "designerdemo.h"

int main(int argc, char *argv[])
{

	QApplication app(argc, argv);
	app.setDefaultCodec( QTextCodec::codecForName("GBK") );


	DesignerDemo * designerdemo = new DesignerDemo;

	designerdemo->show();

	app.setMainWidget(designerdemo);

	int ret = app.exec();

	delete designerdemo;

	return ret;
}

