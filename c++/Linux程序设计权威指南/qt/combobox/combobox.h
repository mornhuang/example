#include <qwidget.h>

class QComboBox;
class QStatusBar;

class ComboboxDemo : public QWidget
{
Q_OBJECT

public:
	ComboboxDemo( QWidget *parent = 0, const char *name = 0 );

private:
	QComboBox *combo;
	QStatusBar *sbar;

};

