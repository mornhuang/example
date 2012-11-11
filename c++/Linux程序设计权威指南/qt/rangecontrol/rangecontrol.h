
#include <qvbox.h>

class QCheckBox;

class RangeControlDemo : public QVBox
{
    Q_OBJECT

public:
    RangeControlDemo( QWidget *parent = 0, const char *name = 0 );

private:
    QCheckBox *notches, *wrapping;
};


