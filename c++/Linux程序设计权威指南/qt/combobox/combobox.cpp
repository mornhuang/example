#include "combobox.h"
#include <qtextcodec.h>
#include <qcombobox.h>
#include <qstatusbar.h>
#include <qpixmap.h>
#include <qlayout.h>

ComboboxDemo::ComboboxDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{
	//建立布局
	QVBoxLayout *vbox = new QVBoxLayout(this);

	//建立ComboBox
	combo = new QComboBox(TRUE, this);
	combo->setInsertionPolicy(QComboBox::AtTop);
	vbox->addWidget(combo);

	//加入项目
	int i;
	for ( i = 0; i < 5; i++ ) {
		combo->insertItem(QString( tr("Combobox 项目 %1") ).arg( i ));
	}
	combo->insertItem( QPixmap( "fileopen.xpm" ), tr("QT 产品") );

	//
	vbox->addStretch(0);

	//建立信号和插槽的联接
	sbar = new QStatusBar(this);
	sbar->message(tr("你可以在输入区域输入..."));
	vbox->addWidget(sbar);

	// 建立联接
	connect( combo, SIGNAL( activated( const QString & ) ), 
		sbar, SLOT( message( const QString & ) ) );
	connect( combo, SIGNAL( highlighted( const QString & ) ), 
		sbar, SLOT( message( const QString & ) ) );

}

