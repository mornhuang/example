#include <qwidget.h>

class QCheckBox;
class QRadioButton;

class LabelButton : public QWidget
{
	Q_OBJECT

public:
	LabelButton( QWidget *parent = 0, const char *name = 0 );

protected:
	QCheckBox *state;
	QRadioButton *rb21, *rb22, *rb23;

protected slots:    
	void slotChangeGrp3State();

};

