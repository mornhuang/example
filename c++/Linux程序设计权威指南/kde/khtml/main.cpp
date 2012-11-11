


	#include <kapp.h>
	#include "khtml.h"

	int main( int argc, char **argv )
	{
		KApplication app(argc, argv, "Browser" );

		MainWindow *window=new MainWindow("Browser");
		window->resize( 500, 500 );

		app.setMainWidget( window );
		window->show(); 

		return app.exec();
	} 


