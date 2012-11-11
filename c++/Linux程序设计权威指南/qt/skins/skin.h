#ifndef SKIN_H
#define SKIN_H


#include <qwidget.h>
#include "skinbutton.h"

class SkinDemo : public QWidget
{
	Q_OBJECT

public:
	SkinDemo( QWidget *parent = 0, const char *name = 0 );
	virtual void mouseMoveEvent(QMouseEvent *);
	virtual void mousePressEvent(QMouseEvent *);
	

protected:
	void setupFiles();

private:
	SkinButton *config_button, *search_button, *update_button, 
		   *min_button, *exit_button, *help_button; 

	enum GRAPHIC { BACKGROUND = 0, MASK, HELP, HELPCLICK, MIN, MINCLICK, 
		EXIT, EXITCLICK, CONFIG, CONFIGCLICK, SEARCH, SEARCHCLICK, 
		UPDATE, UPDATECLICK, MAXGRAPHIC };

	//Í¼ÏñÊý×é
	QString strFiles[MAXGRAPHIC];
	QString strName;
	QPixmap *Pixmaps[MAXGRAPHIC];
	QRect info_main;
	QPoint info_logo;
	QPoint info_banner;
	QPoint info_min;
	QPoint info_exit;
	QPoint info_help;
	QPoint info_search;
	QPoint info_update;
	QPoint info_config;
	QPoint pair2point(const QString &strPair) const;

	QPoint last, pos0;

public slots:
	void slotUpdateContent();
	void slotDoConfig();
	void slotDoSearch();
	void showMinimized();
	void doHelp();

};

#endif
