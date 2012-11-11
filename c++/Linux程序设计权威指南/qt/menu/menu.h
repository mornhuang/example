#include <qwidget.h>

class QMenuBar;
class QStatusBar;

class MenuDemo : public QWidget
{

Q_OBJECT

public:
	MenuDemo( QWidget *parent = 0, const char *name = 0 );

private:
	QMenuBar *menubar;
	QStatusBar *sbar;

public slots:
	void open();
	void news();
	void save();
	void closeDoc();
	void undo();
	void redo();
	void about();
	void aboutQt();
	void printer();
	void file();
	void fax();
	void printerSetup();

signals:
	void explain( const QString& );
};

