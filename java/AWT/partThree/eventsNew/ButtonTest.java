import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ButtonTest extends Applet {
    public void init() {
        Button button = new Button("Press Me");
        button.addMouseListener(new ButtonMouseListener());
        add(button);
    }
}
class ButtonMouseListener implements MouseListener {
    public void mouseEntered(MouseEvent event) { 
        System.out.println("Mouse Entered Button");
    }
    public void mouseExited(MouseEvent event) { 
        System.out.println("Mouse Exited Button");
    }
    public void mousePressed (MouseEvent event) { }
    public void mouseClicked (MouseEvent event) { }
    public void mouseReleased(MouseEvent event) { }
}
