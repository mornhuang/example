/***************************************************************************
                          kdev.cpp  -  description
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

#include <qvbox.h>

// application specific includes
#include "kdevview.h"
#include "kdevdoc.h"
#include "kdev.h"
#include "resource.h"

#include "filenew.xpm"
#include "fileopen.xpm"
#include "filesave.xpm"

KdevApp::KdevApp()
{
  setCaption(tr("Kdev" VERSION ) );

  printer = new QPrinter;
  untitledCount=0;
  pDocList = new QList<KdevDoc>();
  pDocList->setAutoDelete(true);

  ///////////////////////////////////////////////////////////////////
  // call inits to invoke all other construction parts
  initMenuBar();
  initToolBar();
  initStatusBar();
  initView();
  resize( 450, 400 );
}

KdevApp::~KdevApp()
{
  delete printer;
}

void KdevApp::initMenuBar()
{
  ///////////////////////////////////////////////////////////////////
  // MENUBAR

  ///////////////////////////////////////////////////////////////////
  // menuBar entry pFileMenu
		
  pFileMenu=new QPopupMenu();
  pFileMenu->insertItem(tr("&New"), this, SLOT(slotFileNew()), CTRL+Key_N, ID_FILE_NEW);
  pFileMenu->insertItem(tr("&Open..."), this, SLOT(slotFileOpen()), CTRL+Key_O, ID_FILE_OPEN);
  pFileMenu->insertItem(tr("&Close"), this, SLOT(slotFileClose()), CTRL+Key_W, ID_FILE_CLOSE);
  pFileMenu->insertSeparator();
  pFileMenu->insertItem(tr("&Save"), this, SLOT(slotFileSave()), CTRL+Key_S, ID_FILE_SAVE);
  pFileMenu->insertItem(tr("Save &as..."), this, SLOT(slotFileSaveAs()), 0, ID_FILE_SAVE_AS);
  pFileMenu->insertSeparator();
  pFileMenu->insertItem(tr("&Print"), this, SLOT(slotFilePrint()), CTRL+Key_P, ID_FILE_PRINT);
  pFileMenu->insertSeparator();
  pFileMenu->insertItem(tr("E&xit"), this, SLOT(slotFileQuit()), CTRL+Key_Q, ID_FILE_QUIT);

  ///////////////////////////////////////////////////////////////////
  // menuBar entry pEditMenu
  pEditMenu=new QPopupMenu();
  pEditMenu->insertItem(tr("&Undo"), this, SLOT(slotEditUndo()), CTRL+Key_Z, ID_EDIT_UNDO);
  pEditMenu->insertSeparator();
  pEditMenu->insertItem(tr("Cu&t"), this, SLOT(slotEditCut()), CTRL+Key_X, ID_EDIT_CUT);
  pEditMenu->insertItem(tr("&Copy"), this, SLOT(slotEditCopy()), CTRL+Key_C, ID_EDIT_COPY);
  pEditMenu->insertItem(tr("&Paste"), this, SLOT(slotEditPaste()), CTRL+Key_V, ID_EDIT_PASTE);
 
  
  ///////////////////////////////////////////////////////////////////
  // menuBar entry pViewMenu
  pViewMenu=new QPopupMenu();
  pViewMenu->setCheckable(true);
  pViewMenu->insertItem(tr("Tool&bar"), this, SLOT(slotViewToolBar()), 0, ID_VIEW_TOOLBAR);
  pViewMenu->insertItem(tr("&Status Bar"), this, SLOT(slotViewStatusBar()), 0, ID_VIEW_STATUSBAR);

  pViewMenu->setItemChecked(ID_VIEW_TOOLBAR, true);
  pViewMenu->setItemChecked(ID_VIEW_STATUSBAR, true);

  ///////////////////////////////////////////////////////////////////
  // EDIT YOUR APPLICATION SPECIFIC MENUENTRIES HERE

  ///////////////////////////////////////////////////////////////////
  // menuBar entry window-Menu
  pWindowMenu = new QPopupMenu(this);
  pWindowMenu->setCheckable(true);

  ///////////////////////////////////////////////////////////////////
  // menuBar entry pHelpMenu
  pHelpMenu=new QPopupMenu();
  pHelpMenu->insertItem(tr("About..."), this, SLOT(slotHelpAbout()), Key_F1, ID_HELP_ABOUT);
  pHelpMenu->insertSeparator();
  pHelpMenu->insertItem(tr("What's &This"), this, SLOT(whatsThis()), SHIFT+Key_F1);


  ///////////////////////////////////////////////////////////////////
  // MENUBAR CONFIGURATION
  // set menuBar() the current menuBar 

  menuBar()->insertItem(tr("&File"), pFileMenu);
  menuBar()->insertItem(tr("&Edit"), pEditMenu);
  menuBar()->insertItem(tr("&View"), pViewMenu);
  menuBar()->insertItem(tr("&Window"), pWindowMenu);
  menuBar()->insertItem(tr("&Help"), pHelpMenu);
  
  ///////////////////////////////////////////////////////////////////
  // CONNECT THE SUBMENU SLOTS WITH SIGNALS

  connect(pFileMenu, SIGNAL(highlighted(int)), SLOT(statusCallback(int)));
  connect(pEditMenu, SIGNAL(highlighted(int)), SLOT(statusCallback(int)));
  connect(pViewMenu, SIGNAL(highlighted(int)), SLOT(statusCallback(int)));
  connect(pWindowMenu, SIGNAL(aboutToShow() ), SLOT( windowMenuAboutToShow() ) );
  connect(pWindowMenu, SIGNAL(highlighted(int)), SLOT(statusCallback(int)));
  connect(pHelpMenu, SIGNAL(highlighted(int)), SLOT(statusCallback(int)));

}

void KdevApp::initToolBar()
{
  ///////////////////////////////////////////////////////////////////
  // TOOLBAR
  QPixmap openIcon, saveIcon, newIcon;

  fileToolbar = new QToolBar(this, "file operations");
 
  newIcon = QPixmap(filenew);
  QToolButton *fileNew = new QToolButton(newIcon, tr("New File"), 0, this,
                                         SLOT(slotFileNew()), fileToolbar);

  openIcon = QPixmap(fileopen);
  QToolButton *fileOpen = new QToolButton(openIcon, tr("Open File"), 0, this,
                                          SLOT(slotFileOpen()), fileToolbar);

  saveIcon = QPixmap(filesave);
  QToolButton *fileSave = new QToolButton(saveIcon, tr("Save File"), 0, this,
                                          SLOT(slotFileSave()), fileToolbar);
  
  
  fileToolbar->addSeparator();

  QWhatsThis::whatsThisButton(fileToolbar);
  QWhatsThis::add(fileNew,tr("Click this button to create a new file.\n\n"
                  "You can also select the New command from the File menu."));
  QWhatsThis::add(fileOpen,tr("Click this button to open a new file.\n\n"
                  "You can also select the Open command from the File menu."));
  QWhatsThis::add(fileSave,tr("Click this button to save the file you are "
                  "editing. You will be prompted for a file name.\n\n"
                  "You can also select the Save command from the File menu."));

}

void KdevApp::initStatusBar()
{
  ///////////////////////////////////////////////////////////////////
  //STATUSBAR
  statusBar()->message(tr("Ready."));
}

void KdevApp::initView()
{ 
  ////////////////////////////////////////////////////////////////////
  // set the main widget here
  QVBox* view_back = new QVBox( this );
  view_back->setFrameStyle( QFrame::StyledPanel | QFrame::Sunken );
  pWorkspace = new QWorkspace( view_back );
  connect(pWorkspace, SIGNAL(windowActivated(QWidget*)), this, SLOT(setWndTitle(QWidget*)));
  setCentralWidget(view_back);
}

void KdevApp::enableCommand(int id_)
{
  ///////////////////////////////////////////////////////////////////
  // enable menu and toolbar functions by their ID's
  menuBar()->setItemEnabled(id_, true);
}

void KdevApp::disableCommand(int id_)
{
  ///////////////////////////////////////////////////////////////////
  // disable menu and toolbar functions by their ID's
  menuBar()->setItemEnabled(id_, false);
}

void KdevApp::setWndTitle(QWidget*)
{
  setCaption(pWorkspace->activeWindow()->caption()+tr(" - Kdev"));
}

void KdevApp::createClient(KdevDoc* doc)
{
  KdevView* w = new KdevView(doc, pWorkspace,0,WDestructiveClose);
  w->installEventFilter(this);
  doc->addView(w);
  if ( pWorkspace->windowList().isEmpty() ) // show the very first window in maximized mode
    w->showMaximized();
  else
    w->show();
}

void KdevApp::openDocumentFile(const char* file)
{
  statusBar()->message(tr("Opening file..."));
  KdevDoc* doc;
	// check, if document already open. If yes, set the focus to the first view
  for(doc=pDocList->first(); doc > 0; doc=pDocList->next())
  {
    if(doc->pathName()==file)
    {
      KdevView* view=doc->firstView();	
      view->setFocus();
      return;
     }
  }
  doc = new KdevDoc();
  pDocList->append(doc);
  doc->newDocument();
  // Creates an untitled window if file is 0	
  if(!file)
  {
    untitledCount+=1;
    QString fileName=QString(tr("Untitled%1")).arg(untitledCount);
    doc->setPathName(fileName);
    doc->setTitle(fileName);
  }
  // Open the file
  else
  {
    if(!doc->openDocument(file))
    {
      QMessageBox::critical(this, tr("Error !"),tr("Could not open document !"));
      delete doc;
      return;
    }
  }
  // create the window
  createClient(doc);

  statusBar()->message(tr("Ready."));
}

bool KdevApp::queryExit()
{
  int exit=QMessageBox::information(this, tr("Quit..."),
                                    tr("Do your really want to quit?"),
                                    QMessageBox::Ok, QMessageBox::Cancel);

  if (exit==1)
  {

  }
  else
  {

  };

  return (exit==1);
}

bool KdevApp::eventFilter(QObject* object, QEvent* event)
{
  if(event->type() == QEvent::Close)
  {
    QCloseEvent* e=(QCloseEvent*)event;
    KdevView* pView=(KdevView*)object;
    KdevDoc* pDoc=pView->getDocument();
    if(pDoc->canCloseFrame(pView))
    {
      pDoc->removeView(pView);
      if(!pDoc->firstView())
        pDocList->remove(pDoc);
 			
      e->accept();
      //////////////	
      if(pWorkspace->windowList().count()==1)
        setCaption(tr("Kdev"));
      else
        setCaption(pWorkspace->activeWindow()->caption() +tr(" - Kdev"));			
      //////////////
    }
    else
      e->ignore();
  }
  return QWidget::eventFilter( object, event );    // standard event processing
}

/////////////////////////////////////////////////////////////////////
// SLOT IMPLEMENTATION
/////////////////////////////////////////////////////////////////////


void KdevApp::slotFileNew()
{
  statusBar()->message(tr("Creating new file..."));

  openDocumentFile();		

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotFileOpen()
{
  statusBar()->message(tr("Opening file..."));

  QString fileName = QFileDialog::getOpenFileName(0,0,this);
  if (!fileName.isEmpty())
  {
     openDocumentFile(fileName);		
  }
  statusBar()->message(tr("Ready."));
}


void KdevApp::slotFileSave()
{
  statusBar()->message(tr("Saving file..."));
	
  KdevView* m = (KdevView*)pWorkspace->activeWindow();
  if( m )
  {
    KdevDoc* doc = m->getDocument();
    if(doc->title().contains(tr("Untitled")))
      slotFileSaveAs();
    else
      if(!doc->saveDocument(doc->pathName()))
        QMessageBox::critical (this, tr("I/O Error !"), tr("Could not save the current document !"));
  }

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotFileSaveAs()
{
  statusBar()->message(tr("Saving file under new filename..."));
  QString fn = QFileDialog::getSaveFileName(0, 0, this);
  if (!fn.isEmpty())
  {
    KdevView* m = (KdevView*)pWorkspace->activeWindow();
    if( m )
    {
      KdevDoc* doc = m->getDocument();
      if(!doc->saveDocument(fn))
      {
         QMessageBox::critical (this, tr("I/O Error !"), tr("Could not save the current document !"));
         return;
      }
      doc->changedViewList();
      setWndTitle(m);
    }
  }
  statusBar()->message(tr("Ready."));
}

void KdevApp::slotFileClose()
{
  statusBar()->message(tr("Closing file..."));
	
  KdevView* m = (KdevView*)pWorkspace->activeWindow();
  if( m )
  {
    KdevDoc* doc=m->getDocument();
    doc->closeDocument();
  }

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotFilePrint()
{
  statusBar()->message(tr("Printing..."));
	
  KdevView* m = (KdevView*) pWorkspace->activeWindow();
  if ( m )
    m->print( printer );

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotFileQuit()
{ 
  statusBar()->message(tr("Exiting application..."));
  ///////////////////////////////////////////////////////////////////
  // exits the Application
//  if(doc->isModified())
//  {
//    if(queryExit())
//    {
//      qApp->quit();
//    }
//    else
//    {
//
//    };
//  }
//  else
//  {
    qApp->quit();
//  };

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotEditUndo()
{
  statusBar()->message(tr("Reverting last action..."));
	
  KdevView* m = (KdevView*) pWorkspace->activeWindow();
  if ( m )
//   m->undo();

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotEditCut()
{
  statusBar()->message(tr("Cutting selection..."));
	
  KdevView* m = (KdevView*) pWorkspace->activeWindow();
  if ( m )
//  m->cut();	

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotEditCopy()
{
  statusBar()->message(tr("Copying selection to clipboard..."));

  KdevView* m = (KdevView*) pWorkspace->activeWindow();
  if ( m )
//  m->copy();

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotEditPaste()
{
  statusBar()->message(tr("Inserting clipboard contents..."));
	
  KdevView* m = (KdevView*) pWorkspace->activeWindow();
  if ( m )
//   m->paste();

  statusBar()->message(tr("Ready."));
}


void KdevApp::slotViewToolBar()
{
  statusBar()->message(tr("Toggle toolbar..."));
  ///////////////////////////////////////////////////////////////////
  // turn Toolbar on or off
  
  if (fileToolbar->isVisible())
  {
    fileToolbar->hide();
    menuBar()->setItemChecked(ID_VIEW_TOOLBAR, false);
  } 
  else
  {
    fileToolbar->show();
    menuBar()->setItemChecked(ID_VIEW_TOOLBAR, true);
  };

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotViewStatusBar()
{
  statusBar()->message(tr("Toggle statusbar..."));
  ///////////////////////////////////////////////////////////////////
  //turn Statusbar on or off
  
  if (statusBar()->isVisible())
  {
    statusBar()->hide();
    menuBar()->setItemChecked(ID_VIEW_STATUSBAR, false);
  }
  else
  {
    statusBar()->show();
    menuBar()->setItemChecked(ID_VIEW_STATUSBAR, true);
  }
  
  statusBar()->message(tr("Ready."));
}

void KdevApp::slotWindowNewWindow()
{
  statusBar()->message(tr("Opening new document view..."));
	
  KdevView* m = (KdevView*) pWorkspace->activeWindow();
  if ( m ){
    KdevDoc* doc = m->getDocument();
    createClient(doc);
  }

  statusBar()->message(tr("Ready."));
}

void KdevApp::slotHelpAbout()
{
  QMessageBox::about(this,tr("About..."),
                     tr("Kdev\nVersion " VERSION
                     "\n(c) 2000 by "));
}

void KdevApp::slotStatusHelpMsg(const QString &text)
{
  ///////////////////////////////////////////////////////////////////
  // change status message of whole statusbar temporary (text, msec)
  statusBar()->message(text, 2000);
}

void KdevApp::windowMenuAboutToShow()
{
  pWindowMenu->clear();	
  pWindowMenu->insertItem(tr("&New Window"), this, SLOT(slotWindowNewWindow()),0,ID_WINDOW_NEW_WINDOW);
  pWindowMenu->insertItem(tr("&Cascade"), pWorkspace, SLOT(cascade() ),0 , ID_WINDOW_CASCADE );
  pWindowMenu->insertItem(tr("&Tile"), pWorkspace, SLOT(tile() ),0 , ID_WINDOW_TILE );
	
  if ( pWorkspace->windowList().isEmpty() )
  {
    disableCommand(ID_WINDOW_NEW_WINDOW);
    disableCommand(ID_WINDOW_CASCADE);
    disableCommand(ID_WINDOW_TILE);
  }

  pWindowMenu->insertSeparator();

  QWidgetList windows = pWorkspace->windowList();
  for ( int i = 0; i < int(windows.count()); ++i )
  {
    int id = pWindowMenu->insertItem(QString("&%1 ").arg(i+1)+windows.at(i)->caption(), this, SLOT( windowMenuActivated( int ) ) );
    pWindowMenu->setItemParameter( id, i );
    pWindowMenu->setItemChecked( id, pWorkspace->activeWindow() == windows.at(i) );
  }
}

void KdevApp::windowMenuActivated( int id )
{
  QWidget* w = pWorkspace->windowList().at( id );
  if ( w )
    w->setFocus();
}

void KdevApp::statusCallback(int id_)
{
  switch (id_)
  {
    case ID_FILE_NEW:
         slotStatusHelpMsg(tr("Creates a new document"));
         break;

    case ID_FILE_OPEN:
         slotStatusHelpMsg(tr("Opens an existing document"));
         break;

    case ID_FILE_SAVE:
         slotStatusHelpMsg(tr("Saves the actual document"));
         break;

    case ID_FILE_SAVE_AS:
         slotStatusHelpMsg(tr("Saves the actual document as..."));
         break;

    case ID_FILE_CLOSE:
         slotStatusHelpMsg(tr("Closes the actual document"));
         break;

    case ID_FILE_PRINT:
         slotStatusHelpMsg(tr("Prints out the actual document"));
         break;

    case ID_FILE_QUIT:
         slotStatusHelpMsg(tr("Quits the application"));
         break;

    case ID_EDIT_UNDO:
         slotStatusHelpMsg(tr("Reverts the last editing action"));
         break;

    case ID_EDIT_CUT:
         slotStatusHelpMsg(tr("Cuts the selected section and puts it to the clipboard"));
         break;

    case ID_EDIT_COPY:
         slotStatusHelpMsg(tr("Copies the selected section to the clipboard"));
         break;

    case ID_EDIT_PASTE:
         slotStatusHelpMsg(tr("Pastes the clipboard contents to actual position"));
         break;

    case ID_EDIT_SELECT_ALL:
         slotStatusHelpMsg(tr("Selects the whole document contents"));
         break;

    case ID_VIEW_TOOLBAR:
         slotStatusHelpMsg(tr("Enables/disables the toolbar"));
         break;

    case ID_VIEW_STATUSBAR:
         slotStatusHelpMsg(tr("Enables/disables the statusbar"));
         break;

    case ID_WINDOW_NEW_WINDOW:
         slotStatusHelpMsg(tr("Opens a new view for the current document"));
         break;

    case ID_WINDOW_CASCADE:
         slotStatusHelpMsg(tr("Cascades all windows"));
         break;

    case ID_WINDOW_TILE:
         slotStatusHelpMsg(tr("Tiles all windows"));
         break;

    case ID_HELP_ABOUT:
         slotStatusHelpMsg(tr("Shows an aboutbox"));
         break;
  }
}

