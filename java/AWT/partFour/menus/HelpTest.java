import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class HelpTest extends FrameWithMenuBar {
    private MenuItem quitItem;

    public static void main(String args[]) {
        HelpTest frame = new HelpTest("Help Menu Test");
        frame.setBounds(300,300,300,100);
        frame.show();
    }
    public HelpTest(String s) { 
        super(s); 
    }
    public void createMenus(MenuBar mbar) {
        Menu helpMenu = createHelpMenu();

        mbar.add(createFileMenu());
        mbar.add(createEditMenu());
        mbar.add(helpMenu);

        mbar.setHelpMenu(helpMenu);
    }
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File"); 
        fileMenu.add(quitItem = new MenuItem("Quit"));

		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MenuItem item = (MenuItem)event.getSource();
				if(item == quitItem) {
					dispose();
					System.exit(0);
				}
			}
		});
        return fileMenu;
    }
    private Menu createEditMenu() {
        Menu editMenu = new Menu("Edit"); 

        editMenu.add("Cut");
        editMenu.add("Copy");
        editMenu.add("Paste");

        return editMenu;
    }
    private Menu createHelpMenu() {
        Menu helpMenu = new Menu("Help");

        helpMenu.add("Overview ...");
        helpMenu.add("Topics ...");
        helpMenu.add("About ...");

        return helpMenu;
    }
}
