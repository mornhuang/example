
	/* File: layout.cpp */

	#include <qapplication.h>
	#include <qlabel.h>
	#include <qpushbutton.h>
	#include <qlayout.h>
	#include <qmultilineedit.h>
	#include <qmenubar.h>
	#include <qpopupmenu.h>

	class ExampleWidget : public QWidget
	{
	public:
    		ExampleWidget( QWidget *parent = 0, const char *name = 0 );
    		~ExampleWidget();
	private:
	};

	ExampleWidget::ExampleWidget( QWidget *parent, const char *name )
    		: QWidget( parent, name )
	{
		//垂直布局
		QBoxLayout *topLayout = new QVBoxLayout( this, 5 );

		//建立菜单条
		QMenuBar *menubar = new QMenuBar( this );
		menubar->setSeparator( QMenuBar::InWindowsStyle );
		QPopupMenu* popup;
		popup = new QPopupMenu( this );
		popup->insertItem( "&Quit", qApp, SLOT(quit()) );
		menubar->insertItem( "&File", popup );

		//添加菜单条
		topLayout->setMenuBar( menubar );

		//建立水平的容器, 并添加到垂直容器中
		QBoxLayout *buttons = new QHBoxLayout( topLayout);

		int i;
		for ( i = 1; i <= 4; i++ ) {

			//建立按钮
			QPushButton* but = new QPushButton( this );
			QString s;
			s.sprintf( "Button %d", i );
			but->setText( s );

			//在水平容器上添加按钮
			buttons->addWidget( but, 10 );
		}

		//建立多行文本区
		QMultiLineEdit *bigWidget = new QMultiLineEdit( this );
		bigWidget->setText( "This widget will get all the remaining space" );
		bigWidget->setFrameStyle( QFrame::Panel | QFrame::Plain );

		//添加文本区到垂直容器中
		topLayout->addWidget( bigWidget);

		//建立标签
		QLabel* sb = new QLabel( this );
		sb->setText("Let's pretend this is a status bar");
		sb->setFrameStyle( QFrame::Panel | QFrame::Sunken );
		sb->setFixedHeight( sb->sizeHint().height() );
		sb->setAlignment( AlignVCenter | AlignLeft );

		//添加标签
		topLayout->addWidget( sb );

		//激活布局
		topLayout->activate();
	}


	ExampleWidget::~ExampleWidget()
	{
	}

	int main( int argc, char **argv )
	{
		QApplication a( argc, argv );

		ExampleWidget f;
		a.setMainWidget(&f);
		f.show();

		return a.exec();
	}
