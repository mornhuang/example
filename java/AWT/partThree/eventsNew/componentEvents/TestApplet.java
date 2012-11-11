import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	public void init() {
		final Button button = new Button("button");
		Choice visible = new Choice(), addRemove = new Choice();
		Panel  controls = new Panel();

		visible.add("show");
		visible.add("hide");

		addRemove.add("remove");
		addRemove.add("add");

		controls.add(visible);
		controls.add(addRemove);

		setLayout(new BorderLayout());
		add(button, "Center");
		add(controls, "North");

		button.addComponentListener(new ButtonListener());
		addContainerListener(new AppletListener());

		visible.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String s = 
					((Choice)e.getSource()).getSelectedItem();

				if(s.equals("hide")) 	button.setVisible(false);
				else 					button.setVisible(true);
			}
		});
		addRemove.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String s = 
					((Choice)e.getSource()).getSelectedItem();

				if(s.equals("add")) 	
					add(button, "Center");
				else 					
					remove(button);
			}
		});
	}
}
class ButtonListener implements ComponentListener {
	public void componentResized(ComponentEvent event) {
		Component c = (Component)event.getSource();
		System.out.println("button resized:  " +  c.getSize());
	}
	public void componentShown(ComponentEvent event) {
		System.out.println("button shown");
	}
	public void componentHidden(ComponentEvent event) {
		System.out.println("button hidden");
	}
	public void componentMoved(ComponentEvent event) {
		Component c = (Component)event.getSource();
		System.out.println("button moved:  " + c.getLocation());
	}
}
class AppletListener implements ContainerListener {
	public void componentAdded(ContainerEvent e) {
		Component c = e.getChild();
		System.out.println("container:  button added");
	}
	public void componentRemoved(ContainerEvent e) {
		Component c = e.getChild();
		System.out.println("container:  button removed");
	}
}
