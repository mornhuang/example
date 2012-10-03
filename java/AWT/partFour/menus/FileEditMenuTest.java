import java.awt.*;
import java.awt.event.*;

public class FileEditMenuTest extends FrameWithMenuBar {
    private MenuItem quitItem;

    public static void main(String args[]) {
        FileEditMenuTest test = 
            new FileEditMenuTest("FileEdit Menu Test");

        test.setBounds(300,300,300,100);
        test.show();
    }
    public FileEditMenuTest(String s) {
        super(s);
    }
    public void createMenus(MenuBar mbar) {
        mbar.add(createFileMenu());
        mbar.add(createEditMenu());
    }
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File"); 

        fileMenu.add("New ...");
        fileMenu.add("Open ...");
        fileMenu.add("Save As ...");
        fileMenu.add("Save ...");
        fileMenu.addSeparator();
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
}
