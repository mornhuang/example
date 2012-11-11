#include <qbuttongroup.h>
#include <qtimer.h>

class QPushButton;
class QProgressBar;

class ProgressBarDemo : public QWidget
{
Q_OBJECT

public:
    ProgressBarDemo( QWidget *parent = 0, const char *name = 0 );

protected:
    QPushButton *start, *pause, *reset;
    QProgressBar *progressbar;
    QTimer *timer;

protected slots:
    void slotStart();
    void slotReset();
    void slotTimeout();

};

