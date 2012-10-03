import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class AdjustmentExample extends Applet {
    private ScrollPane scroller = new ScrollPane();
    private Scrollbar sbar = new Scrollbar(Scrollbar.HORIZONTAL);

    public void init() {
        setLayout(new BorderLayout());
        sbar.setValues(0,  // value
                       50, // visible
                       0,  // minimum
                       100 // maximum
                       );
        sbar.setUnitIncrement (10);
        sbar.setBlockIncrement(20);
        add(sbar, "North");

        scroller.add(new ScrollMe(), 0);
        add(scroller, "Center");

        sbar.addAdjustmentListener(
					new DebugAdjustmentListener());

        scroller.getHAdjustable().addAdjustmentListener(
                    new DebugAdjustmentListener());
        scroller.getVAdjustable().addAdjustmentListener(
                    new DebugAdjustmentListener());
    }
}
class ScrollMe extends Panel {
    public ScrollMe() {
        for(int i=0; i < 25; ++i)
            add(new Button("Button " + i));
    }
}
class DebugAdjustmentListener implements AdjustmentListener {
    public void adjustmentValueChanged(AdjustmentEvent event) {
        Object obj = event.getSource();
        System.out.println(obj.toString());
    }
}
