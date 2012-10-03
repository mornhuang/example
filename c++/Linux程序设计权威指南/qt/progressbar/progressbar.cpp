
#include "progressbar.h"

#include <qradiobutton.h>
#include <qpushbutton.h>
#include <qprogressbar.h>
#include <qlayout.h>
#include <qtimer.h>
#include <qtextcodec.h>

ProgressBarDemo::ProgressBarDemo( QWidget *parent, const char *name )
    : QWidget( parent, name )
{

	//建立布局
	QVBoxLayout *vbox = new QVBoxLayout(this);
	QHBoxLayout *hbox = new QHBoxLayout(vbox); 

	//建立两个按钮
	start = new QPushButton( tr("开始(&S)"), this );
	hbox->addWidget( start );
	reset = new QPushButton( tr("复位(&R)"), this );
	hbox->addWidget( reset );
    
	vbox->addStretch(0);

	//建立进度条
	progressbar = new QProgressBar( 100, this );
	progressbar->setCenterIndicator(TRUE);
	vbox->addWidget( progressbar );
	connect( start, SIGNAL( clicked() ), this, SLOT( slotStart() ) );
	connect( reset, SIGNAL( clicked() ), this, SLOT( slotReset() ) );

	//建立定时器
	timer = new QTimer(this);
	connect( timer, SIGNAL( timeout() ), this, SLOT( slotTimeout() ) );
	start->setFixedWidth( 80 );
	reset->setFixedWidth( 80 );

}

void ProgressBarDemo::slotStart()
{
	//进度条1000步长
	progressbar->setTotalSteps( 1000 );

	//如果进度条没有工作
	if ( !timer->isActive() ) {
		timer->start( 1 );	//每1毫秒超时
		start->setText( tr("暂停(&P)") );
	} else {	//否则
		timer->stop();
		start->setText( tr("继续(&C)") );
	}
}

void ProgressBarDemo::slotReset()
{
	//复位
	timer->stop();

	start->setText( tr("开始(&S)") );
	start->setEnabled( TRUE );

	progressbar->reset();
}

void ProgressBarDemo::slotTimeout()
{
	int p = progressbar->progress();

	//如果进度条到头
	if ( p == progressbar->totalSteps() )  {
		start->setText( tr("开始(&S)") );
		start->setEnabled( FALSE );
		return;
	}
    
	//否则
	progressbar->setProgress( ++p );
}
