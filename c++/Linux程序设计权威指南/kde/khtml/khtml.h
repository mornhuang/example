
#include <ktmainwindow.h>
#include <kurl.h>
#include <kparts/browserextension.h> 
#include <qvaluestack.h>

class QLineEdit;
class KHTMLPart;

class MainWindow : public KTMainWindow {

	Q_OBJECT

	public:
		MainWindow ( char * name );

	public slots:
		void changeLocation();
		void bookLocation();
		void gotoPreviousPage();
		void openURLRequest(const KURL &url, const KParts::URLArgs & );

	private:
		QLineEdit *location;
		KHTMLPart *browser;
		QValueStack <QString> history; 
}; 
