#ifndef MYDESIGNERPLUGIN_H
#define MYDESIGNERPLUGIN_H

#include <QtGui/QWidget>

class MyDesignerPlugin : public QWidget
{
    Q_OBJECT

public:
    MyDesignerPlugin(QWidget *parent = 0);
};

#endif
