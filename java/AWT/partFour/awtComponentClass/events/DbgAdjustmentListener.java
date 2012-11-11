import java.awt.*;
import java.awt.event.*;

public class DbgAdjustmentListener implements AdjustmentListener {
	public void adjustmentValueChanged(AdjustmentEvent event) {
		System.out.println(event.toString());
	}
}
