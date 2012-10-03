import java.applet.Applet;
import java.awt.*;

public class CanvasEventExample extends Applet {
    public void init() {
        MouseCanvas canvas = new MouseCanvas();
        setLayout(new BorderLayout());
        add(canvas, "Center");
    }
}

class MouseCanvas extends Canvas {
    public boolean handleEvent(Event event) {
        if(event.id == Event.MOUSE_DOWN) {
            System.out.println("Mouse Down!");
            return true;  // event fully handled, don't propagate
        }
        return super.handleEvent(event);
    }
    public boolean mouseUp(Event event, int x, int y) {
        System.out.println("Mouse Up!");
        return true;
    }
}
