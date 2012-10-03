import java.awt.*;
import java.util.Vector;

public class Sprite extends Component { 
	private DoubleBufferedContainer container;
	private Sequence currentSequence, mainSequence;
	private Stopwatch moveTimer = new Stopwatch();

	private Point startPt      = new Point(0,0);
	private Point moveVector   = new Point(1,1);
	private long  moveInterval = 0;

	public Sprite(DoubleBufferedContainer container,
		Sequence                sequence, 
		Point                   ulhc) {
		this.container = container;
		setSequence          (sequence);
		setMainSequence      (sequence);

		moveTimer.start      ();
		currentSequence.start();

		setLocation(ulhc);
	}

	public void  reverseX () { moveVector.x = 0-moveVector.x;  }
	public void  reverseY () { moveVector.y = 0-moveVector.y;  }
	public void  reverse  () { reverseX(); reverseY();         }

	public void  setMoveVector (Point p) { moveVector = p;     }
	public Point getMoveVector()         { return moveVector;  }

	public void paint(Graphics g) {
		if(isVisible()) {
			Image image = currentSequence.getCurrentImage();
			g.drawImage(image, 0, 0, this);
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
	/**
	 * @deprecated as of JDK1.1
	 */
	public Dimension preferredSize() {
		Image image = currentSequence.getCurrentImage();
		return new Dimension(image.getWidth(this),
		                     image.getHeight(this));
	}
	public Dimension getPreferredSize() {
		return preferredSize();
	}
    public void play(Sequence sequence, long cycles) {
        setSequence(sequence);
        sequence.setCyclesPerAnimation(cycles);
        sequence.setCurrentCycle(0);
    }
    public void animate() {
        if(currentSequence.animationOver())
            currentSequence = mainSequence;

        if(timeToChangeImage()) currentSequence.advance();

        if(timeToMove()) {
			advance();
		}
		else {
			if(needsRepainting()) {
				container.paintComponent(this);
			}
		}
    }
    public void setMainSequence(Sequence sequence) {
        mainSequence = sequence;
    }
    public Sequence getMainSequence() { 
        return mainSequence; 
    }
    public void setSequence(Sequence sequence) {
        currentSequence = sequence;
    }
    public Sequence getSequence() { 
        return currentSequence; 
    }
    public boolean willIntersect(Sprite otherSprite) {
        return getNextBounds().intersects(
				otherSprite.getNextBounds());
    }
    public void setLocation(int x, int y) {
		super.setLocation(x, y);
        moveTimer.reset();
    }
    public void setMoveInterval(long interval) {
        moveInterval = interval;
    }
    public void setImageChangeInterval(long interval) {
        currentSequence.setAdvanceInterval(interval);
    }
    public Point getNextLocation() {
		Rectangle bounds = getBounds();
        return new Point(bounds.x + moveVector.x, 
                         bounds.y + moveVector.y);
    }
    public Rectangle getNextBounds() {
		Rectangle bounds = getBounds();
        Point nextLoc    = getNextLocation();
        return new Rectangle(nextLoc.x,    nextLoc.y, 
							 bounds.width, bounds.height);
    }
    protected boolean timeToChangeImage() {
        return currentSequence.timeToAdvanceCell();
    }
    protected boolean timeToMove() {
        return moveTimer.elapsedTime() > moveInterval;
    }
    protected boolean needsRepainting() {
		Rectangle bounds = getBounds();
        return currentSequence.needsRepainting(
					new Point(bounds.x, bounds.y));
    }
	protected void advance() {
		Rectangle bounds = getBounds();
		container.blitBackgroundToWorkplace(bounds);

		Image image = currentSequence.getCurrentImage();
		setBounds(bounds.x + moveVector.x,
		          bounds.y + moveVector.y,
				  image.getWidth(this), image.getHeight(this));

		container.paintComponents(bounds.union(getBounds()),
		                          true);
	}
}
