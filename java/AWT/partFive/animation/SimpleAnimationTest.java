import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class SimpleAnimationTest extends Applet {
	public void init() {
		setLayout(new BorderLayout());
		add(
		new Label("Simple Animation - Mouse Down Starts/Stops"),
		"North"); 
		add(new SimpleAnimationTestPanel(this), "Center"); 
	}
}
class SimpleAnimationTestPanel extends Panel {
	public SimpleAnimationTestPanel(Applet applet) {
		setLayout(new BorderLayout());
		add(new SimplePlayfield(applet), "Center"); 
	}
	public void update(Graphics g) {
		paint(g);
	}
}
class SimplePlayfield extends Playfield {
	private Applet   applet;
	private URL      cb;
	private Sprite   javaDrinker;
	private Sequence spinSequence;

	public SimplePlayfield(Applet applet) {
		this.applet = applet;
		cb = applet.getCodeBase();

		makeSequencesAndSprites();

		setWallpaperImage(
				applet.getImage(cb, "gifs/background.gif"));

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if(running() == true) stop ();
				else                  start();
			}
		});
}
public void edgeCollision(Sprite sprite, 
							Orientation orientation) {
	if(orientation == Orientation.RIGHT || 
						orientation == Orientation.LEFT)
		sprite.reverseX();
	else
		sprite.reverseY();
}
private void makeSequencesAndSprites() {
	String  file;
	Point   startLoc   = new Point(10, 10);
	Image[] spinImages = new Image[19];

	for(int i=0; i < spinImages.length; ++i) {
		file = "gifs/spin";

		if(i < 10) file += "0" + i + ".gif";
		else       file += i + ".gif";

		spinImages[i] = applet.getImage(cb, file);
	}
	spinSequence = new Sequence(this, spinImages);
	javaDrinker  = new Sprite(this, spinSequence, startLoc);

	javaDrinker.setMoveVector(new Point(1,1));
	add(javaDrinker);
	}
}
