import java.applet.Applet;
import java.awt.*;
import java.net.URL;

public class TestApplet extends Applet {
	private Lightweight filmstrip, frog, ladybug;
	private Button button;

	public void init() {
		URL cb = getCodeBase();

		setLayout(new BulletinLayout());

		add(filmstrip = new Lightweight(
						getImage(cb, "../gifs/filmstrip.gif")));

		add(frog = new Lightweight(
						getImage(cb, "../gifs/frog.gif")));

		add(ladybug = new Lightweight(
						getImage(cb, "../gifs/ladybug.gif")));

		add(button = new Button("button"));

		filmstrip.setLocation(15, 15);
		frog.setLocation(35, 35);
		ladybug.setLocation(55, 55);
		button.setLocation(30, 20);
	}
}
