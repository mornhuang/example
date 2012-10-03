
	/* File: i18n.cpp */

	#include <iostream.h>
	#include <qapplication.h>
	#include <qtranslator.h>	//信息翻译载入
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
		popup->insertItem( tr("&Quit"), qApp, SLOT(quit()) );
		menubar->insertItem( tr("&File"), popup );

		//添加菜单条
		topLayout->setMenuBar( menubar );

		//建立水平的容器, 并添加到垂直容器中
		QBoxLayout *buttons = new QHBoxLayout( topLayout);

		int i;
		for ( i = 1; i <= 4; i++ ) {

			//建立按钮
			QPushButton* but = new QPushButton( this );
			QString s = tr("Button %1").arg(i);
			but->setText( s );

			//在水平容器上添加按钮
			buttons->addWidget( but, 10 );
		}

		//建立多行文本区
		QMultiLineEdit *bigWidget = new QMultiLineEdit( this );
		bigWidget->setText( tr("This widget will get all the remaining space") );
		bigWidget->setFrameStyle( QFrame::Panel | QFrame::Plain );

		//添加文本区到垂直容器中
		topLayout->addWidget( bigWidget);

		//建立标签
		QLabel* sb = new QLabel( this );
		sb->setText(tr("Let's pretend this is a status bar"));
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

		if(argc < 2) {
			cout << "Usage: ./i18n -[gb|big5]\n";
			exit(1);
		}

		//设置字体, 使中文程序尽量使用点阵字体.
		a.setFont(QFont("Times", 16, QFont::Normal));

		//建立 Translator, 用于载入信息文件
		QTranslator *translator = new QTranslator(0);
		if(!strcmp(argv[1], "-gb")){
			//载入当前目录下的文件
			translator->load("i18n_gb.qm", ".");
		} else if(!strcmp(argv[1], "-big5")){
			//载入当前目录下的文件
			translator->load("i18n_big5.qm", ".");
		}
		a.installTranslator(translator);

		ExampleWidget f;
		a.setMainWidget(&f);
		f.show();

		return a.exec();
	}
