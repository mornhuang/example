

#include "toolbar.h"
#include "icons.h"
#include <qtextcodec.h>
#include <qmenubar.h>
#include <qpopupmenu.h>
#include <qstatusbar.h>
#include <qpixmap.h>
#include <qapplication.h>
#include <qmultilineedit.h>
#include <qmessagebox.h>
#include <qmainwindow.h>
#include <qtoolbar.h>
#include <qaction.h>
#include <qwhatsthis.h>
#include <qfiledialog.h>

const char * fileOpenText = "<img source=\"fileopen\"> "
"按此按钮打开一个<em>新文件</em>. <br><br>"
"你也可以使用文件菜单中的<b>打开命令</b>";
const char * fileSaveText = "按此按钮保存被编辑的内容"
"你将被提示选择一个文件名\n\n"
"你也可以从菜单中选择保存或者保存为命令";
const char * filePrintText = "按此按钮打印正在编辑的文件\n\n"
"你和可以从菜单中选择打印命令";



ToolbarDemo::ToolbarDemo()
    : QMainWindow(0, "Toolbar Demo")
{

	//主窗口本身是一个高级容器

	//建立动作
	QAction *fileNewAction, *fileOpenAction, *fileSaveAction,
		*fileSaveAsAction, *filePrintAction, *fileCloseAction,
		*fileQuitAction;
	fileNewAction = new QAction( "New", tr("新建(&N)"), 
		CTRL+Key_N, this, "new" );
	connect( fileNewAction, SIGNAL( activated() ) ,this, SLOT( newDoc()));
	fileOpenAction = new QAction( "Open File", QPixmap( fileopen ), 
		tr("打开(&O)"), CTRL+Key_O, this, "open" );
	connect( fileOpenAction, SIGNAL( activated() ) , this, SLOT( load() ) );
	QMimeSourceFactory::defaultFactory()->setPixmap( "fileopen",
		QPixmap( fileopen ) );
	fileOpenAction->setWhatsThis( tr(fileOpenText) );

	fileSaveAction = new QAction( "Save File", QPixmap( filesave ), 
		tr("保存(&S)"), CTRL+Key_S, this, "save" );
	connect( fileSaveAction, SIGNAL( activated() ) , this, SLOT( save() ) );
	fileSaveAction->setWhatsThis( tr(fileSaveText) );

	fileSaveAsAction = new QAction( "Save File As", tr("保存为(&s)..."), 
		0,  this, "save as" );
	connect( fileSaveAsAction, SIGNAL(activated()),this,SLOT( saveAs() ));
	fileSaveAsAction->setWhatsThis( tr(fileSaveText) );
	filePrintAction = new QAction( "Print File", QPixmap( fileprint ), 
		tr("打印(&P)"), CTRL+Key_P, this, "print" );
	connect( filePrintAction, SIGNAL(activated()) , this, SLOT(print()));
	filePrintAction->setWhatsThis( tr(filePrintText) );

	fileCloseAction = new QAction( "Close", tr("关闭(&C)"), CTRL+Key_W, 
		this, "close" );
	connect( fileCloseAction, SIGNAL(activated()) , this, SLOT(close()) );

	fileQuitAction = new QAction( "Quit", tr("退出(&Q)"), CTRL+Key_Q, 
		this, "quit" );
	connect(fileQuitAction,SIGNAL(activated()),qApp,SLOT(closeAllWindows()));


	//建立按钮条
	QToolBar* fileTools = new QToolBar( this, "file operations" );
	fileTools->setLabel( "File Operations" );
	fileOpenAction->addTo( fileTools );
	fileSaveAction->addTo( fileTools );
	filePrintAction->addTo( fileTools );
	(void)QWhatsThis::whatsThisButton( fileTools );


	//建立文件菜单
	QPopupMenu * file = new QPopupMenu( this );
	menuBar()->insertItem( tr("文件(&F)"), file );
	fileNewAction->addTo( file );
	fileOpenAction->addTo( file );
	fileSaveAction->addTo( file );
	fileSaveAsAction->addTo( file );
	file->insertSeparator();
	filePrintAction->addTo( file );
	file->insertSeparator();
	fileCloseAction->addTo( file );
	fileQuitAction->addTo( file );

	//建立帮助菜单
	QPopupMenu * help = new QPopupMenu( this );
	menuBar()->insertSeparator();
	menuBar()->insertItem( tr("帮助(&H)"), help );
	help->insertItem( tr("关于(&A)"), this, SLOT(about()), Key_F1 );
	help->insertItem( tr("关于 &Qt"), this, SLOT(aboutQt()) );
	help->insertSeparator();
	help->insertItem(tr("这是什么?"),this,SLOT(whatsThis()),SHIFT+Key_F1);


	QMultiLineEdit *e = new QMultiLineEdit( this, "editor" );
	e->setFocus();
	setCentralWidget( e );
	statusBar()->message( tr("准备就绪"), 2000 );
	resize( 400, 400 );

}


void ToolbarDemo::newDoc()
{
    ToolbarDemo *ed = new ToolbarDemo;
    ed->show();
}

void ToolbarDemo::load()
{
    QString fn = QFileDialog::getOpenFileName( QString::null, QString::null,
                                               this);
    statusBar()->message( "Loading " + fn );
}


void ToolbarDemo::save()
{
    statusBar()->message( "Saved" );
}


void ToolbarDemo::saveAs()
{
    statusBar()->message( "Save as..." );
}


void ToolbarDemo::closeEvent( QCloseEvent* ce )
{
	ce->accept();
}


void ToolbarDemo::print()
{
    statusBar()->message( "Send to printer..." );
}

void ToolbarDemo::about()
{
    QMessageBox::about( this, "Qt Toolbar Example",
                        "This example demonstrates simple use of "
                        "QMainWindow,\nQMenuBar and QToolBar.");
}


void ToolbarDemo::aboutQt()
{
    QMessageBox::aboutQt( this, "Qt Toolbar Example" );
}



