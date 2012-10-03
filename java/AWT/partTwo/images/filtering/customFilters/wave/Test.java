import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Test extends Frame {
	Image original, wavey;

	public Test() {
		super("Wave Filter");
		URL url = getClass().getResource("tiger.gif");
		try {
			original = 
				createImage((ImageProducer)url.getContent());

			MediaTracker mt = new MediaTracker(this);
			mt.addImage(original, 0);
			mt.waitForID(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		FilteredImageSource fis = new FilteredImageSource(
										original.getSource(),
										new WaveFilter(25,15));
		wavey = createImage(fis);
	}
	public static void main(String args[]) {
		final Frame f = new Test();
		f.setBounds(200,50,730,380);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
	}
	public void paint(Graphics g) {
		Insets i = getInsets();
		int iw = original.getWidth(this);

		g.drawImage(original, i.left, i.top, this);
		g.drawImage(wavey, i.left + iw + 20, i.top, this);
	}
}
