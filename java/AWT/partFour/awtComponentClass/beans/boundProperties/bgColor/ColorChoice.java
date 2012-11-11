import java.awt.*;

class ColorChoice extends Choice {
	private String colorNames[] = {
						"black", "blue", "cyan", "darkGray",
						"gray", "green", "lightgray", "magenta",
						"orange", "pink", "red", "white",
						"yellow" };

	private Color colors[] = {Color.black,     Color.blue,
							  Color.cyan,      Color.darkGray,
							  Color.gray,      Color.green,
							  Color.lightGray, Color.magenta,
							  Color.orange,    Color.pink,
							  Color.red,       Color.white,
							  Color.yellow };
	public ColorChoice() {
		for(int i=0; i < colors.length; ++i) {
			add(colorNames[i]);
		}
	}
	public Color getColor() {
		return colors[getSelectedIndex()];
	}
	public void setColor(Color color) {
		for(int i=0; i < colors.length; ++i) {
			if(colors[i].equals(color)) {
				select(i);
				break;
			}
		}
	}
}
