#include "edit.h"
#include <stdio.h>
#include <qlabel.h>
#include <qtextcodec.h>
#include <qlineedit.h>
#include <qmultilineedit.h>
#include <qvalidator.h>
#include <qlayout.h>


EditDemo::EditDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{
	//建立布局组件
	QGridLayout *grid = new QGridLayout(this, 5, 2, 2, 2);

	//普通输入条
	QLabel *label1 = new QLabel(tr("普通输入条:"), this);
	grid->addWidget( label1 , 0, 0);
	QLineEdit *edit1 = new QLineEdit(tr("LineEdit测试"), this);
	edit1->setFocus();
	edit1->setCursorPosition(4);
	grid->addWidget( edit1, 0, 1);

	//只读输入条
	QLabel *label2 = new QLabel(tr("<u><font color=#ff0000>只读输入条:</font></u>"), this);
	grid->addWidget( label2 , 1, 0);
	QLineEdit *edit2 = new QLineEdit(tr("只读:)"), this);
	edit2->setReadOnly(TRUE);
	grid->addWidget( edit2, 1, 1);

	//不显示输入条
	QLabel *label3 = new QLabel(tr("口令输入模式:"), this);
	grid->addWidget( label3 , 2, 0);
	QLineEdit *edit3 = new QLineEdit(this);
	edit3->setEchoMode(QLineEdit::Password);
	grid->addWidget( edit3, 2, 1);

	//整数输入校验
	QLabel *label4 = new QLabel(tr("整数输入校验:"), this);
	grid->addWidget( label4 , 3, 0);
	QLineEdit *edit4 = new QLineEdit(this);
	edit4->setValidator( new QIntValidator( edit4 ) );
	grid->addWidget( edit4, 3, 1);
	
	//多行编辑区
	QMultiLineEdit *multiedit = new QMultiLineEdit(this);
	grid->addMultiCellWidget(multiedit, 4, 4, 0, 1);

}
