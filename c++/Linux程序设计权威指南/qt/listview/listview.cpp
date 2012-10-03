#include "listview.h"
#include <qlabel.h>
#include <qtextcodec.h>
#include <qlistview.h>
#include <qlayout.h>

ListDemo::ListDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{
	//建立布局
	QVBoxLayout *vbox = new QVBoxLayout(this);

	//建立多列列表
	QListView *listview = new QListView(this);
	vbox->addWidget(listview);

	//加入三列
	listview->addColumn( tr("配置") );
	listview->addColumn( tr("项目类型") );
	listview->addColumn( tr("配置值") );
	listview->setRootIsDecorated( TRUE );
	listview->setAllColumnsShowFocus(TRUE);

	//加入项目
	QListViewItem *application = new QListViewItem( listview, tr("应用程序" ));
	QListViewItem *desktop = new QListViewItem( listview, tr("桌面" ));
	QListViewItem *network = new QListViewItem( listview, tr("网络" ));

	QListViewItem* item;
	item = new QListViewItem(application, 
		tr("登录管理器"), tr("外观"), tr("标准KDE"));
	item = new QListViewItem(application, 
		tr("文件管理器"), tr("字体"), tr("Times"));
	item = new QListViewItem(application, 
		tr("Web浏览器"), tr("Proxy"), tr("nothing.com"));

	item = new QListViewItem(desktop, 
		tr("背景"), tr("窗口背景"), tr("蓝色"));
	item = new QListViewItem(desktop, 
		tr("颜色"), tr("窗口颜色"), tr("天蓝色"));
	item = new QListViewItem(desktop, 
		tr("字体"), tr("正文字体"), tr("8x16"));
	item = new QListViewItem(desktop, 
		tr("风格"), tr("缺省风格"), tr("Motif"));


	item = new QListViewItem(network, 
		tr("地址"), tr("IP地址"), tr("192.168.1.1"));
	item = new QListViewItem(network, 
		tr("节点名"), tr("本地"), tr("localhost"));
	

}

