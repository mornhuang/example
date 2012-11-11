
#include <qmainwindow.h>

class ToolbarDemo : public QMainWindow
{

Q_OBJECT

public:
	ToolbarDemo();

protected:
	void closeEvent( QCloseEvent* );


public slots:
	void newDoc();
	void load();
	void save();
	void saveAs();
	void print();
	void about();
	void aboutQt();
};

