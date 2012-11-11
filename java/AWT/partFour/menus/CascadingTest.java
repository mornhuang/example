import java.awt.*;
import java.awt.event.*;

public class CascadingTest extends FrameWithMenuBar {
    private MenuItem quitItem;

    public static void main(String args[]) {
        CascadingTest test = 
            new CascadingTest("Cascading Menu Test");

        test.setBounds(300,300,300,100);
        test.show();
    }
    public CascadingTest(String s) {
        super(s);
    }
    public void createMenus(MenuBar mbar) {
        mbar.add(createFileMenu());
        mbar.add(createCascadingMenu());
    }
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File"); 
        fileMenu.add(quitItem = new MenuItem("Quit"));

		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				System.exit(0);
			}
		});
        return fileMenu;
    }
    private Menu createCascadingMenu() {
        Menu cascading  = new Menu("Cascading", true);
        Menu submenu    = new Menu("more", true);
        Menu subsubmenu = new Menu("yet more", true);

        submenu.add("submenu item 1");
        submenu.add("submenu item 2");
        submenu.add("submenu item 3");
        submenu.add("submenu item 4");
        submenu.add("submenu item 5");

        subsubmenu.add("subsubmenu item 1");
        subsubmenu.add("subsubmenu item 2");
        subsubmenu.add("subsubmenu item 3");
        subsubmenu.add("subsubmenu item 4");
        subsubmenu.add("subsubmenu item 5");

        submenu.add(subsubmenu);
        cascading.add(submenu);

        return cascading;
    }
}
