#include <qwidget.h>

class QListBox;
class QStatusBar;

class ListDemo : public QWidget
{
Q_OBJECT

public:
	ListDemo( QWidget *parent = 0, const char *name = 0 );

private:
	QListBox *list;
	QStatusBar *sbar;

protected slots:
	void showMessage(int);
	
};

