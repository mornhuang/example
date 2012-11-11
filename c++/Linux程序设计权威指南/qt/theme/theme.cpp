

#include "theme.h"
#include "icons.h"
#include "wood.h"
#include "metal.h"

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

#include <qwindowsstyle.h>
#include <qplatinumstyle.h>
#include <qmotifstyle.h>
#include <qmotifplusstyle.h>
#include <qcdestyle.h>


const char * fileOpenText = "<img source=\"fileopen\"> "
"按此按钮打开一个<em>新文件</em>. <br><br>"
"你也可以使用文件菜单中的<b>打开命令</b>";
const char * fileSaveText = "按此按钮保存被编辑的内容"
"你将被提示选择一个文件名\n\n"
"你也可以从菜单中选择保存或者保存为命令";
const char * filePrintText = "按此按钮打印正在编辑的文件\n\n"
"你和可以从菜单中选择打印命令";



ThemeDemo::ThemeDemo()
    : QMainWindow(0, "Toolbar Demo")
{

	appFont = QApplication::font();

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

	//建立Style菜单
	QPopupMenu *style = new QPopupMenu( this );
	style->setCheckable( TRUE );
	menuBar()->insertItem( tr("风格(&S)") , style );
	sMetal = style->insertItem( "&Metal", this, SLOT( styleMetal() ) );
	sWood = style->insertItem( "&Norwegian Wood", this, SLOT( styleWood() ) );
	sPlatinum = style->insertItem( "&Platinum" , this ,SLOT( stylePlatinum() ) );
	sWindows = style->insertItem( "&Windows", this, SLOT( styleWindows() ) );
	sCDE = style->insertItem( "&CDE", this, SLOT( styleCDE() ) );
	sMotif = style->insertItem( "M&otif", this, SLOT( styleMotif() ) );
	sMotifPlus = style->insertItem( "Motif P&lus", this, SLOT( styleMotifPlus() ) );

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


void ThemeDemo::newDoc()
{
    ThemeDemo *ed = new ThemeDemo;
    ed->show();
}

void ThemeDemo::load()
{
    QString fn = QFileDialog::getOpenFileName( QString::null, QString::null,
                                               this);
    statusBar()->message( "Loading " + fn );
}


void ThemeDemo::save()
{
    statusBar()->message( "Saved" );
}


void ThemeDemo::saveAs()
{
    statusBar()->message( "Save as..." );
}


void ThemeDemo::closeEvent( QCloseEvent* ce )
{
	ce->accept();
}


void ThemeDemo::print()
{
    statusBar()->message( "Send to printer..." );
}


void ThemeDemo::selectStyleMenu( int s )
{
    menuBar()->setItemChecked( sWood, FALSE );
    menuBar()->setItemChecked( sMetal, FALSE );
    menuBar()->setItemChecked( sPlatinum, FALSE );
    menuBar()->setItemChecked( sWindows, FALSE );
    menuBar()->setItemChecked( sCDE, FALSE );
    menuBar()->setItemChecked( sMotif, FALSE );
    menuBar()->setItemChecked( sMotifPlus, FALSE );
    menuBar()->setItemChecked( s, TRUE );
}


void ThemeDemo::styleWood()
{
    qApp->setStyle( new NorwegianWoodStyle );
    qApp->setFont( appFont, TRUE );
    selectStyleMenu( sWood );
}

void ThemeDemo::styleMetal()
{
    qApp->setStyle( new MetalStyle );
    qApp->setFont( appFont, TRUE );
    selectStyleMenu( sMetal );
}

void ThemeDemo::stylePlatinum()
{
    qApp->setStyle( new QPlatinumStyle );
    QPalette p( QColor( 239, 239, 239 ) );
    qApp->setPalette( p, TRUE );
    qApp->setFont( appFont, TRUE );
    selectStyleMenu( sPlatinum );
}

void ThemeDemo::styleWindows()
{
    qApp->setStyle( new QWindowsStyle );
    qApp->setFont( appFont, TRUE );
    selectStyleMenu( sWindows );
}

void ThemeDemo::styleCDE()
{
    qApp->setStyle( new QCDEStyle( TRUE ) );
    selectStyleMenu( sCDE );


    QPalette p( QColor( 75, 123, 130 ) );
    p.setColor( QPalette::Active, QColorGroup::Base, QColor( 55, 77, 78 ) );
    p.setColor( QPalette::Inactive, QColorGroup::Base, QColor( 55, 77, 78 ) );
    p.setColor( QPalette::Disabled, QColorGroup::Base, QColor( 55, 77, 78 ) );
    p.setColor( QPalette::Active, QColorGroup::Highlight, Qt::white );
    p.setColor( QPalette::Active, QColorGroup::HighlightedText, QColor( 55, 77, 78 ) );
    p.setColor( QPalette::Inactive, QColorGroup::Highlight, Qt::white );
    p.setColor( QPalette::Inactive, QColorGroup::HighlightedText, QColor( 55, 77, 78 ) );
    p.setColor( QPalette::Disabled, QColorGroup::Highlight, Qt::white );
    p.setColor( QPalette::Disabled, QColorGroup::HighlightedText, QColor( 55, 77, 78 ) );
    p.setColor( QPalette::Active, QColorGroup::Foreground, Qt::white );
    p.setColor( QPalette::Active, QColorGroup::Text, Qt::white );
    p.setColor( QPalette::Active, QColorGroup::ButtonText, Qt::white );
    p.setColor( QPalette::Inactive, QColorGroup::Foreground, Qt::white );
    p.setColor( QPalette::Inactive, QColorGroup::Text, Qt::white );
    p.setColor( QPalette::Inactive, QColorGroup::ButtonText, Qt::white );
    p.setColor( QPalette::Disabled, QColorGroup::Foreground, Qt::lightGray );
    p.setColor( QPalette::Disabled, QColorGroup::Text, Qt::lightGray );
    p.setColor( QPalette::Disabled, QColorGroup::ButtonText, Qt::lightGray );
    qApp->setPalette( p, TRUE );
    qApp->setFont( QFont( "times", appFont.pointSize() ), TRUE );
}

void ThemeDemo::styleMotif()
{
    qApp->setStyle( new QMotifStyle );
    QPalette p( QColor( 192, 192, 192 ) );
    qApp->setPalette( p, TRUE );
    qApp->setFont( appFont, TRUE );
    selectStyleMenu( sMotif );
}

void ThemeDemo::styleMotifPlus()
{
    qApp->setStyle( new QMotifPlusStyle );
    QPalette p( QColor( 192, 192, 192 ) );
    qApp->setPalette( p, TRUE );
    qApp->setFont( appFont, TRUE );
    selectStyleMenu( sMotifPlus );
}

 
void ThemeDemo::about()
{
    QMessageBox::about( this, "Qt Toolbar Example",
                        "This example demonstrates simple use of "
                        "QMainWindow,\nQMenuBar and QToolBar.");
}


void ThemeDemo::aboutQt()
{
    QMessageBox::aboutQt( this, "Qt Toolbar Example" );
}



