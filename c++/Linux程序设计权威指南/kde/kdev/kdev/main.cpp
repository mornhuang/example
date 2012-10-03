/***************************************************************************
                          main.cpp  -  description
                             -------------------
    begin                : ËÄ 09 21 05:30:27 CST 2000
    copyright            : (C) 2000 by 
    email                : 
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

#include <qapplication.h>
#include <qfont.h>

#include "kdev.h"

int main(int argc, char *argv[])
{
  QApplication a(argc, argv);
  a.setFont(QFont("helvetica", 12));

  KdevApp *kdev=new KdevApp();
  a.setMainWidget(kdev);

  kdev->show();

  if(argc>1)
    kdev->openDocumentFile(argv[1]);
	else
	  kdev->openDocumentFile();
	
  return a.exec();
}
