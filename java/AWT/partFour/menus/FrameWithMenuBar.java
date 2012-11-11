import java.awt.*;
import java.awt.event.*;

/**
 * A simple Frame with a MenuBar.
 *
 */
public abstract class FrameWithMenuBar extends Frame {
    private MenuBar        mbar    = new MenuBar();
    private MenuBarPrinter printer;

    abstract protected void createMenus(MenuBar menuBar);

    public FrameWithMenuBar(String s) {
        super(s);

        createMenus(mbar);
        setMenuBar (mbar);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				quit();	
			}
		});
	}
	protected void quit() {
    	if(aboutToBeDestroyed())
			quitNoConfirm();
	}
	protected void quitNoConfirm() {
		System.exit(0);
	}
    public void printMenus() {
        if(printer == null)
            printer = new MenuBarPrinter();

        printer.print(mbar);
    }
    protected boolean aboutToBeDestroyed() {
		return true;
    }
}
