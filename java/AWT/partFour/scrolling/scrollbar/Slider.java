import java.awt.*;
import java.awt.event.*;

public class Slider extends BorderedPanel implements Adjustable {
	Scrollbar scrollbar;
	Label     valueLabel;

	public Slider(int initialValue, int visible, 
	              int min, int max) {
		String initialValueStr = Integer.toString(initialValue);

		valueLabel = new Label(initialValueStr, Label.CENTER);
 		scrollbar  = new Scrollbar(Scrollbar.HORIZONTAL,
	                               initialValue,
								   visible, min, max);

		setLayout(new BorderLayout());
		add(valueLabel, "North");
		add(scrollbar, "Center");

		scrollbar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				valueLabel.setText(
					Integer.toString(scrollbar.getValue()));
			}
		});
	}
	public void addAdjustmentListener(AdjustmentListener l) {
		scrollbar.addAdjustmentListener(l);
	}
	public void removeAdjustmentListener(AdjustmentListener l) {
		scrollbar.removeAdjustmentListener(l);
	}
	public int getOrientation() {
		return scrollbar.getOrientation();	
	}
	public void setOrientation(int orient) {
		scrollbar.setOrientation(orient);	
	}
	public int getValue() {
		return scrollbar.getValue();
	}
	public int getVisibleAmount() {
		return scrollbar.getVisibleAmount();
	}
	public int getMinimum() {
		return scrollbar.getMinimum();
	}
	public int getMaximum() {
		return scrollbar.getMaximum();
	}
	public int getUnitIncrement() {
		return scrollbar.getUnitIncrement();
	}
	public int getBlockIncrement() {
		return scrollbar.getBlockIncrement();
	}
	public void setValue(int value) {
		scrollbar.setValue(value);
		valueLabel.setText(Integer.toString(value));
	}
	public void setVisibleAmount(int value) {
		scrollbar.setVisibleAmount(value);
	}
	public void setMinimum(int min) {
		scrollbar.setMinimum(min);
	}
	public void setMaximum(int max) {
		scrollbar.setMaximum(max);
	}
	public void setUnitIncrement(int inc) {
		scrollbar.setUnitIncrement(inc);
	}
	public void setBlockIncrement(int inc) {
		scrollbar.setBlockIncrement(inc);
	}
}
