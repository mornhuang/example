
#include <stdio.h>
#include <kapp.h>
#include <kwinmodule.h>
#include <kwin.h>
#include <kconfig.h>

#include "docking.h"

KApplication *mykapp;
DockWidget *dock_widget;

int main( int argc, char *argv[] )
{
	mykapp = new KApplication(argc, argv,"zwinkde" );

	dock_widget = new DockWidget("ZWinKDE");
    	dock_widget->dock();

	return mykapp->exec();
}


DockWidget::DockWidget(const char *name) : QWidget(0, name, 0)
{
	if(!pixmap.load("./docking.xpm")){
		printf("Cannot load pixmap.\n");
		exit(1);
	}
}



DockWidget::~DockWidget() {
}

void DockWidget::dock() {

	if (!docked) {

		KWin::setSystemTrayWindowFor(this->winId(), 0);

		this->setFixedSize(24, 24);
		this->show();
		docked = true;
	}
}


void DockWidget::undock()
{
	if(docked){
		//´Ý»Ùdocking×é¼þ
        	this->destroy(true, true);
		this->create(0, false, false);
        	docked = false;
    	}
}


const bool DockWidget::isDocked()
{
	return docked;
}

void DockWidget::paintEvent (QPaintEvent *e)
{
	(void) e;
	paintIcon();
}

void DockWidget::paintIcon ()
{
	bitBlt(this, 0, 0, &pixmap);
}

void DockWidget::appexit()
{
	if(docked)undock();
	mykapp->quit();
	exit(0);
}
