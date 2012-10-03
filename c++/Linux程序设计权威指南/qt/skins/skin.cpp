#include "skin.h"
#include "skinbutton.h"

#include <iostream.h>
#include <qfile.h>
#include <qapplication.h>
#include <qlabel.h>
#include <qtextcodec.h>
#include <qpixmap.h>
#include <qbitmap.h>
#include <qbutton.h>
#include <qtooltip.h>
#include <qlistbox.h>
#include <qmultilineedit.h>

SkinDemo::SkinDemo( QWidget *parent, const char *name )
    : QWidget( parent, name, WStyle_NoBorder)
{

	//打开配置文件
	QString strPath = QString("./") + qApp->argv()[1];
	QString skin_filename = strPath + "/skin.ini";
	QFile f(skin_filename);
	if(!f.open(IO_ReadOnly)) {
		cout << "Cannot open " << skin_filename << endl;
		exit(1);
	}

	//读取配置文件信息
	QString strLine;
	QPoint mainloc;				//主工作区位置
	int mainwidth = 0, mainheight = 0;	//主工作区尺寸

	while(!f.atEnd()) {
		if(f.readLine(strLine, 1024) == -1)
			cout << "error reading from skin file" << endl;
		strLine = strLine.simplifyWhiteSpace();
		if(strLine.isEmpty()) continue;
		if( *strLine.ascii() == ';') continue;
		if( (strLine.find("[skin]", 0, false) == 0) 
			&& (strLine.length() == 6) ) continue;
		if((strLine.find("name=", 0, false) == 0)) {
			strName = strLine.mid(5);
			strName = strName.stripWhiteSpace();
		} else if((strLine.find("main=", 0, false) == 0))
			mainloc = pair2point(strLine.mid(5));
		else if((strLine.find("mainwidth=", 0, false) == 0))
			mainwidth = strLine.mid(10).toInt();
		else if((strLine.find("mainheight=", 0, false) == 0))
			mainheight = strLine.mid(11).toInt();
		else if((strLine.find("logo=", 0, false) == 0))
			info_logo = pair2point(strLine.mid(5));
		else if((strLine.find("banner=", 0, false) == 0))
			info_banner = pair2point(strLine.mid(7));
		else if((strLine.find("help=", 0, false) == 0))
			info_help = pair2point(strLine.mid(5));
		else if((strLine.find("min=", 0, false) == 0))
			info_min = pair2point(strLine.mid(4));
		else if((strLine.find("exit=", 0, false) == 0))
			info_exit = pair2point(strLine.mid(5));
		else if((strLine.find("search=", 0, false) == 0))
			info_search = pair2point(strLine.mid(7));
		else if((strLine.find("update=", 0, false) == 0))
			info_update = pair2point(strLine.mid(7));
		else if((strLine.find("config=", 0, false) == 0))
			info_config = pair2point(strLine.mid(7));
	}
	//设置主工作区的位置和尺寸
	info_main.setRect(mainloc.x(), mainloc.y(), mainwidth, mainheight);

	//载入图像
	setupFiles();
	for(GRAPHIC g = BACKGROUND; g < MAXGRAPHIC; g = (GRAPHIC)(g + 1))
		Pixmaps[g] = new QPixmap(strPath + "/" + strFiles[g]);


	//背景图像和掩码
	QBitmap bm;
	bm = *(Pixmaps[MASK]);
	setMask(bm);
	setBackgroundPixmap(*Pixmaps[BACKGROUND]);
	resize(Pixmaps[BACKGROUND]->size().width(), 
		Pixmaps[BACKGROUND]->size().height());

	//各种按钮的初始化
	config_button = new SkinButton(this);
	update_button = new SkinButton(this);
	search_button = new SkinButton(this);
	min_button = new SkinButton(this);
	exit_button = new SkinButton(this);
	help_button = new SkinButton(this);

	config_button->setPixmaps(Pixmaps[CONFIG], Pixmaps[CONFIGCLICK]);
	config_button->setGeometry(info_config.x(), info_config.y(), 
	    Pixmaps[CONFIG]->size().width(), Pixmaps[CONFIG]->size().height());
	config_button->show();

	update_button->setPixmaps(Pixmaps[UPDATE], Pixmaps[UPDATECLICK]);
	update_button->setGeometry(info_update.x(), info_update.y(), 
	    Pixmaps[UPDATE]->size().width(), Pixmaps[UPDATE]->size().height());
	update_button->show();

	search_button->setPixmaps(Pixmaps[SEARCH], Pixmaps[SEARCHCLICK]);
	search_button->setGeometry(info_search.x(),info_search.y(), 
	    Pixmaps[SEARCH]->size().width(), Pixmaps[SEARCH]->size().height());
	search_button->show();

	min_button->setPixmaps(Pixmaps[MIN], Pixmaps[MINCLICK]);
	min_button->setGeometry(info_min.x(), info_min.y(), 
	    	Pixmaps[MIN]->size().width(), Pixmaps[MIN]->size().height());
	min_button->show();

	exit_button->setPixmaps(Pixmaps[EXIT], Pixmaps[EXITCLICK]);
	exit_button->setGeometry(info_exit.x(), info_exit.y(), 
		Pixmaps[EXIT]->size().width(), Pixmaps[EXIT]->size().height());
	exit_button->show();

	help_button->setPixmaps(Pixmaps[HELP], Pixmaps[HELPCLICK]);
	help_button->setGeometry(info_help.x(), info_help.y(), 
		Pixmaps[HELP]->size().width(), Pixmaps[HELP]->size().height());
	help_button->show();

	QToolTip::add(update_button, tr("从SecurityFocus更新文章"));
	QToolTip::add(config_button, tr("配置软件"));
	QToolTip::add(search_button, tr("从SecurityFocus的WEB站点查询"));
	QToolTip::add(min_button, tr("最小化"));
	QToolTip::add(exit_button, tr("退出程序"));
	QToolTip::add(help_button, tr("在线帮助"));

	connect(update_button, SIGNAL(clicked()), SLOT(slotUpdateContent()));
	connect(config_button, SIGNAL(clicked()), SLOT(slotDoConfig()));
	connect(search_button, SIGNAL(clicked()), SLOT(slotDoSearch()));
	connect(min_button, SIGNAL(clicked()), SLOT(showMinimized()));
	connect(exit_button, SIGNAL(clicked()), qApp, SLOT(quit()));
	connect(help_button, SIGNAL(clicked()), SLOT(doHelp()));


	//建立主工作区域
	QListBox *list = new QListBox( this );
	list->setFocusPolicy( QWidget::StrongFocus );
	list->insertItem(tr("显示器"));
	list->insertItem(tr("键盘"));
	list->insertItem(tr("鼠标"), 1);
	list->insertItem(QPixmap("qtlogo.gif"));
	for(int i=0; i<5; i++)
		list->insertItem(tr("这是第 ") + QString::number(i+4) 
			+ tr(" 行"));
	list->setCurrentItem(5);
	list->setGeometry(info_main);
	list->show();

}


QPoint SkinDemo::pair2point(const QString &strPair) const
{
	int n;
	if( (n = strPair.find(",", 0)) == -1) return QPoint();
	return QPoint(strPair.left(n).toInt(), strPair.mid(n + 1).toInt());
}

void SkinDemo::setupFiles()
{
	strFiles[BACKGROUND]   = "pager.bmp";
	strFiles[MASK]         = "pager-mask.bmp";
	strFiles[HELP]         = "help.bmp";
	strFiles[HELPCLICK]    = "helpclick.bmp";
	strFiles[MIN]          = "min.bmp";
	strFiles[MINCLICK]     = "minclick.bmp";
	strFiles[EXIT]         = "exit.bmp";
	strFiles[EXITCLICK]    = "exitclick.bmp";
	strFiles[CONFIG]       = "config.bmp";
	strFiles[CONFIGCLICK]  = "configclick.bmp";
	strFiles[SEARCH]       = "search.bmp";
	strFiles[SEARCHCLICK]  = "searchclick.bmp";
	strFiles[UPDATE]       = "update.bmp";
	strFiles[UPDATECLICK]  = "updateclick.bmp";
}

void SkinDemo::slotUpdateContent()
{
	cout << "Update button clicked." << endl;
}
void SkinDemo::slotDoConfig()
{
	cout << "Config button clicked." << endl;
}
void SkinDemo::slotDoSearch()
{
	cout << "Search button clicked." << endl;
}
void SkinDemo::showMinimized()
{
	cout << "Minimized button clicked." << endl;
}
void SkinDemo::doHelp()
{
	cout << "Help button clicked." << endl;
}

void SkinDemo::mouseMoveEvent(QMouseEvent *e)
{
	QPoint newpos = e->globalPos();
	QPoint upleft = pos0 + newpos - last;
	move(upleft);
}

void SkinDemo::mousePressEvent(QMouseEvent *e)
{
	last = e->globalPos();
	pos0 = e->globalPos() - e->pos();
}

