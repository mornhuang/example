import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EmptyCanvas extends Canvas 
						implements PropertyChangeListener {
	public EmptyCanvas() {
		setBackground(Color.gray);
	}
	public Dimension getMinimumSize() {
		return new Dimension(100,100);	
	}
	public void propertyChange(PropertyChangeEvent e) {
		Color newColor = (Color)e.getNewValue();

		if(newColor != null && ! newColor.equals(getBackground()))
			setBackground(newColor);
	}
}
