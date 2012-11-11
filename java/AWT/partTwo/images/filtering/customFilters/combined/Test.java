import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Hashtable;

public class Test extends Frame {
	Image orig, dissolved, dissolvedAndWaved;

	public Test() {
		super("Dissolve Filter");

		MediaTracker mt = new MediaTracker(this);
		URL url = getClass().getResource("tiger.gif");

		try {
			orig = createImage((ImageProducer)url.getContent());
			mt.addImage(orig, 0);
			mt.waitForID(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		FilteredImageSource fis = new FilteredImageSource(
									orig.getSource(),
									new DissolveFilter());
		fis = new FilteredImageSource(
				createImage(fis).getSource(),
				new WaveFilter());

		fis = new FilteredImageSource(
				createImage(fis).getSource(),
				new PropertiesReportingFilter());

		dissolvedAndWaved = createImage(fis);
	}
	public void update(Graphics g) {
		paint(g);
	}
	public static void main(String args[]) {
		final Frame f = new Test();
		f.setBounds(100,100,730, 380);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
	}
	public void paint(Graphics g) {
		Insets  i = getInsets();
		int 	ow = orig.getWidth(this);  // ow = Original Width

		g.drawImage(orig, i.left, i.top, this);
		g.drawImage(dissolvedAndWaved, 
					i.left + ow + 20, i.top, this);
	}
}
class PropertiesReportingFilter extends ImageFilter {
	public void setProperties(Hashtable ht) {
		super.setProperties(ht);
		consumer.setProperties(ht);
		System.out.println(ht);
	}
}
