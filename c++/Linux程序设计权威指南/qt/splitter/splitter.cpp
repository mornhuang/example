
#include <qapplication.h>
#include <qsplitter.h>

int main( int argc, char ** argv )
{

	QApplication a( argc, argv );

	//垂直分隔
	QSplitter *s1 = new QSplitter( QSplitter::Vertical, 0 , "main" );

	//顶部的水平分隔
	QSplitter *s2 = new QSplitter( QSplitter::Horizontal, s1, "top" );

	//左边
	QWidget *t1 = new QWidget( s2 );
	t1->setBackgroundColor( Qt::blue.light( 180 ) );
	t1->setMinimumSize( 50, 0 );

	//右边
	QWidget *t2 = new QWidget( s2 );
	t2->setBackgroundColor( Qt::green.light( 180 ) );
	s2->setResizeMode( t2, QSplitter::KeepSize );
	s2->moveToFirst( t2 );

	//底部水平分隔
	QSplitter *s3 = new QSplitter( QSplitter::Horizontal,  s1, "bottom" );

	QWidget *t3 = new QWidget( s3 );
	t3->setBackgroundColor( Qt::red );

	QWidget *t4 = new QWidget( s3 );
	t4->setBackgroundColor( Qt::white );

	QWidget *t5 = new QWidget( s3 );
	t5->setMaximumHeight( 250 );
	t5->setMinimumSize( 80, 50 );
	t5->setBackgroundColor( Qt::yellow );

	//设置缩放时的模式
	s2->setOpaqueResize( TRUE );
	s3->setOpaqueResize( TRUE );

	a.setMainWidget( s1 );
	s1->show();

	return a.exec();
}
