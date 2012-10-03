import java.awt.*;
import java.awt.event.*;

public class FileMenuTest extends Frame {
    private MenuBar mbar;
    private MenuItem newItem, openItem, 
					 saveAsItem, saveItem, quitItem;
	private MenuItemListener menuItemListener = 
								new MenuItemListener();

    public static void main(String args[]) {
        FileMenuTest test = new FileMenuTest("File Menu Test");
        test.setBounds(300,300,300,100);
        test.show();
    }
    public FileMenuTest(String s) {
        super(s);

        MenuBar mbar     = new MenuBar();
        Menu    fileMenu = new Menu("File", true);

        fileMenu.add(newItem = new MenuItem("New ..."));
        fileMenu.add(openItem = new MenuItem("Open ..."));
        fileMenu.add(saveAsItem = new MenuItem("Save As ..."));
        fileMenu.add(saveItem = new MenuItem("Save ..."));
        fileMenu.addSeparator();
        fileMenu.add(quitItem = new MenuItem("Quit"));

        mbar.add(fileMenu);
        setMenuBar (mbar);

		newItem.addActionListener(menuItemListener);
		openItem.addActionListener(menuItemListener);
		saveAsItem.addActionListener(menuItemListener);
		saveItem.addActionListener(menuItemListener);
		quitItem.addActionListener(menuItemListener);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
    }
	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			MenuItem item = (MenuItem)event.getSource();
			System.out.println(item.getLabel());

			if(item == quitItem) {
				dispose();
				System.exit(0);
			}
		}
	}
}
