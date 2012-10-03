

#include "menu.h"
#include "icons.h"
#include <qlabel.h>
#include <qtextcodec.h>
#include <qmenubar.h>
#include <qpopupmenu.h>
#include <qstatusbar.h>
#include <qpixmap.h>
#include <qlayout.h>
#include <qapplication.h>
#include <qmessagebox.h>

MenuDemo::MenuDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{

	//建立布局
	QVBoxLayout *vbox = new QVBoxLayout(this);

	QPixmap p1( p1_xpm );
	QPixmap p2( p2_xpm );
	QPixmap p3( p3_xpm );

	//建立 print 子菜单
	QPopupMenu *print = new QPopupMenu( this );
	print->insertTearOffHandle();
	print->insertItem( tr("打印到打印机(&P)"), this, SLOT(printer()) );
	print->insertItem( tr("打印到文件(&f)"), this, SLOT(file()) );
	print->insertItem( tr("打印到传真机(&x)"), this, SLOT(fax()) );
	print->insertSeparator();
	print->insertItem( tr("打印机配置(&S)"), this, SLOT(printerSetup()) );


	//建立文件菜单
	QPopupMenu *file = new QPopupMenu( this );
	file->insertItem( p1, tr("打开(&O)"), this, SLOT(open()), CTRL+Key_O );
	file->insertItem( p2, tr("新建(&N)"), this, SLOT(news()), CTRL+Key_N );
	file->insertItem( p3, tr("保存(&S)"), this, SLOT(save()), CTRL+Key_S );
	file->insertItem( tr("关闭(&W)"), this, SLOT(closeDoc()), CTRL+Key_W );
	file->insertSeparator();
	file->insertItem( tr("打印(&P)"), print, CTRL+Key_P );
	file->insertSeparator();
	file->insertItem( tr("退出(&x)"),  qApp, SLOT(quit()), CTRL+Key_Q );

	//建立编辑菜单
	QPopupMenu *edit = new QPopupMenu( this );
	int undoID = edit->insertItem( tr("撤销(&U)"), this, SLOT(undo()) );
	int redoID = edit->insertItem( tr("重复(&R)"), this, SLOT(redo()) );
	edit->setItemEnabled( undoID, FALSE );
	edit->setItemEnabled( redoID, FALSE );

	//建立帮助菜单
	QPopupMenu *help = new QPopupMenu( this );
	help->insertItem( tr("关于(&H)..."), this, SLOT(about()), CTRL+Key_H );
	help->insertItem( tr("关于&Qt ..."), this, SLOT(aboutQt()) );

	menubar = new QMenuBar( this );
	menubar->insertItem( tr("文件(&F)"), file );
	menubar->insertItem( tr("编辑(&E)"), edit );
	menubar->insertSeparator();
	menubar->insertItem( tr("帮助(&H)"), help );
	menubar->setSeparator( QMenuBar::InWindowsStyle );
	vbox->addWidget(menubar);

	//stretch the vbox
	vbox->addStretch(0);

	//建立状态条
	QStatusBar *sbar = new QStatusBar(this);
	vbox->addWidget(sbar);
	sbar->message(tr("菜单演示, 请测试快捷键...")); 

	connect( this,  SIGNAL(explain(const QString&)),
		sbar, SLOT(message(const QString&)) );

}

void MenuDemo::printer()
{   
    emit explain( "File/Printer/Print selected" );
}   
    
void MenuDemo::file()
{   
    emit explain( "File/Printer/Print To File selected" );
}

void MenuDemo::fax()
{
    emit explain( "File/Printer/Print To Fax selected" );
}

void MenuDemo::printerSetup()
{
    emit explain( "File/Printer/Printer Setup selected" );
}

void MenuDemo::open()
{
    emit explain( "File/Open selected" );
}


void MenuDemo::news()
{
    emit explain( "File/New selected" );
}

void MenuDemo::save()
{
    emit explain( "File/Save selected" );
}

void MenuDemo::closeDoc()
{
    emit explain( "File/Close selected" );
}


void MenuDemo::undo()
{
    emit explain( "Edit/Undo selected" );
}


void MenuDemo::redo()
{
    emit explain( "Edit/Redo selected" );
}


void MenuDemo::about()
{
    QMessageBox::about( this, "Qt Menu Example",
                        "This example demonstrates simple use of Qt menus.\n"
                        "You can cut and paste lines from it to your own\n"
                        "programs." );
}


void MenuDemo::aboutQt()
{
    QMessageBox::aboutQt( this, "Qt Menu Example" );
}




