
#include <qmainwindow.h>

class ThemeDemo : public QMainWindow
{

Q_OBJECT

public:
	ThemeDemo();

protected:
	int sWood, sMetal, sPlatinum, sWindows, sCDE, sMotif, sMotifPlus;
	void closeEvent( QCloseEvent* );
private:
	QFont appFont;

public slots:
	void newDoc();
	void load();
	void save();
	void saveAs();
	void print();
	void styleMetal();
	void styleWood();
	void stylePlatinum();
	void styleWindows();
	void styleCDE();
	void styleMotif();
	void styleMotifPlus();
	void selectStyleMenu(int s);
	void about();
	void aboutQt();
};

