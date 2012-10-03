import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class BumpAnimationTest extends Applet {
    public void init() {
		setLayout(new BorderLayout());
		add(
		new Label("Bump Animation - Mouse Down Starts/Stops"), 
		"North"); 

        add(new BumpAnimationTestPanel(this), "Center"); 
	}
}

class BumpAnimationTestPanel extends Panel {
    public BumpAnimationTestPanel(Applet applet) {
        setLayout(new BorderLayout());
        add(new BumpPlayfield(applet), "Center"); 
    }
	public void update(Graphics g) {
		paint(g);
	}
}

class BumpPlayfield extends Playfield {
    private Applet   applet;
    private URL      cb;
    private Sprite   javaDrinker;
    private Sequence spinSequence, bumpSequence;

    public BumpPlayfield(Applet applet) {
        this.applet = applet;
        cb          = applet.getCodeBase();

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
    public void edgeCollision(Sprite      sprite, 
                              Orientation orientation) {
        if(orientation == Orientation.RIGHT || 
           orientation == Orientation.LEFT) {
            if(sprite.getSequence() != bumpSequence) {
                sprite.reverseX();

                if(orientation == Orientation.RIGHT)
                    sprite.play(bumpSequence, 1);
                else
                    sprite.play(bumpSequence, 2);
            }
        }   
        else 
            sprite.reverseY();
    }
    private void makeSequencesAndSprites() {
        String  file;
        Point   startLoc   = new Point(10, 10);
        Image[] spinImages = new Image[19];
        Image[] bumpImages = new Image[6];

        for(int i=0; i < spinImages.length; ++i) {
            file = "gifs/spin";

            if(i < 10) file += "0" + i + ".gif";
            else       file += i + ".gif";

            spinImages[i] = applet.getImage(cb, file);
        }
        for(int i=0; i < bumpImages.length; ++i) {
            file = "gifs/bump0" + i + ".gif";
            bumpImages[i] = applet.getImage(cb, file);
        }
        spinSequence = new Sequence(this, spinImages);
        bumpSequence = new Sequence(this, bumpImages);
        javaDrinker  = new Sprite(this, spinSequence, startLoc);

        spinSequence.setAdvanceInterval(100);
        bumpSequence.setAdvanceInterval(200);

        javaDrinker.setMoveVector(new Point(1,1));
		javaDrinker.setImageChangeInterval(50);
        add(javaDrinker);
    }
}
