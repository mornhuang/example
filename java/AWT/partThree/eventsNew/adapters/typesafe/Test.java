import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Test extends Applet {
	YesNoDialog dialog = new YesNoDialog("Yes/No Dialog", 
	                                     "Do you use adapters?", 
										 true);  // true means modal

	Button launchButton = new Button("Show Dialog ...");

	public void init() {
		add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Point loc = launchButton.getLocationOnScreen();
				dialog.setLocation(loc.x + 15, loc.y + 15);
				dialog.show();

				if(dialog.getAnswer())
					showStatus("Yes");
				else
					showStatus("No");
			}
		});
	}
}
