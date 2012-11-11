#include "listbox.h"
#include <qlabel.h>
#include <qtextcodec.h>
#include <qlistbox.h>
#include <qstatusbar.h>
#include <qlayout.h>

ListDemo::ListDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{
	//建立布局
	QVBoxLayout *vbox = new QVBoxLayout(this);

	//建立列表框
	list = new QListBox( this );
	list->setFocusPolicy( QWidget::StrongFocus );
	vbox->addWidget(list);

	//加入项目
	list->insertItem(tr("显示器"));
	list->insertItem(tr("键盘"));
	list->insertItem(tr("鼠标"), 1);
	list->insertItem(QPixmap("qtlogo.gif"));
	for(int i=0; i<5; i++)
		list->insertItem(tr("这是第 ") + QString::number(i+4) 
			+ tr(" 行"));
	
	list->setCurrentItem(5);

	//建立信号和插槽的联接
	sbar = new QStatusBar(this);
	vbox->addWidget(sbar);

	connect(list, SIGNAL(highlighted(int)), 
		this, SLOT(showMessage(int)));

	
}

void ListDemo::showMessage(int idx)
{
	sbar->message(tr("你选择了第") + QString::number(idx)
		+ tr("项:") + list->currentText());
}

