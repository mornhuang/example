import java.awt.*;
import java.awt.event.*;

public class ActionExample2 extends Frame {
    private Button    button    = new Button("Activate Me");
    private List      list      = new List();
    private TextField textfield = new TextField(25);
    private MenuItem  menuItem  = new MenuItem("Menu menuItem");

    static public void main(String args[]) {
        ActionExample2 f = new ActionExample2();
        f.setBounds(200,200,200,200);
        f.show();
    }
    public ActionExample2() {
        super("Action Example");
        MenuBar mbar = new MenuBar();
        Menu    menu = new Menu("Menu!");
        menu.add(menuItem);
        mbar.add(menu);
        setMenuBar(mbar);

        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        list.add("item 4");
        list.add("item 5");

        setLayout(new FlowLayout());
        add(button);
        add(list);
        add(textfield);
        
        button.addActionListener   (new DebugActionListener());
        list.addActionListener     (new DebugActionListener());
        textfield.addActionListener(new DebugActionListener());
        menuItem.addActionListener (new DebugActionListener());

        addWindowListener(new ActionWindowListener());
    }
}
class ActionWindowListener extends WindowAdapter {
    public void windowClosing(WindowEvent event) {
        Window window = (Window)event.getSource();
        window.dispose();
        System.exit(0);
    }
}
class DebugActionListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("action event in:  " + 
							event.getActionCommand());
    }
}
