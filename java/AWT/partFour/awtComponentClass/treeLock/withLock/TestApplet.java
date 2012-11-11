import java.applet.Applet;
import java.awt.*;

public class TestApplet extends Applet {
	Panel panel = new Panel();

	public void init() {
		panel.add(new Button("button 1"));
		panel.add(new Button("button 2"));
		panel.add(new Button("button 3"));
		panel.add(new Button("button 4"));
		panel.add(new Button("button 5"));

		setLayout(new BorderLayout());
		add(panel, "Center");

		AddRemoveThread addRemoveThread = new AddRemoveThread();
		ThrowThread throwThread = new ThrowThread();

		addRemoveThread.start();
		throwThread.start();
	}
	class ThrowThread extends Thread {
		public void run() {
			while(true) {
				synchronized(panel.getTreeLock()) {
					int cnt = panel.getComponentCount();

					System.out.println("throw Thread cnt:  " +
									panel.getComponentCount());
					try {
						Thread.currentThread().sleep(1500);	
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}

					for(int i=0; i < cnt; ++i) {
						try {
							Button b = (Button)
										(panel.getComponent(i));
						}	
						catch(NullPointerException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	class AddRemoveThread extends Thread {
		public void run() {
			while(true) {
				int cnt = panel.getComponentCount();

				if(cnt == 5) {
					System.out.println("removing");
					panel.remove(panel.getComponent(cnt-1));
				}
				else {
					System.out.println("adding");
					panel.add(new Button("button 5"), cnt);
				}
				System.out.println("add/remove Thread cnt:  " +
									panel.getComponentCount());
				panel.validate();

				try {
					Thread.currentThread().sleep(1000);	
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
