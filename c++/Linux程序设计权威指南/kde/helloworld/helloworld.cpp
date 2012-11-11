
	/* File: helloworld.cpp */
	#include <kapp.h>
	#include <klocale.h>
	#include <qpushbutton.h>

	int main( int argc, char **argv )
	{
		KApplication app(argc, argv , "HelloWorld"); 

		//建立按钮
		QPushButton *button = new QPushButton(i18n("Hello World!"), 0);
		button->setAutoResize( TRUE ); 

		//建立按钮回调
		QObject::connect(button, SIGNAL(clicked()), &app, SLOT(quit()));

		//把按钮组件设置为主窗口
		app.setMainWidget(button);
		button->show(); 

		//进入循环
		return app.exec();
	}
