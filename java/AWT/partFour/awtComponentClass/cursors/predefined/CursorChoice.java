import java.awt.*;

public class CursorChoice extends Choice {
	private String cursorNames[] = {
						"default",          "cross hair", 
						"text",             "wait",
						"southwest resize", "southeast resize", 
						"northwest resize", "northeast resize",
						"north resize",     "south resize",
						"west resize",      "east resize",
						"hand",             "move" };

	private Cursor cursors[] = {
		Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR),
		Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR),
		Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR),      
		Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR),
		Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR),
		Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR),
		Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR), 
		Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR),
		Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR),    
		Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR),
		Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR),
		Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR),
		Cursor.getPredefinedCursor(Cursor.HAND_CURSOR),
		Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR) };
								
	public CursorChoice() {
		for(int i=0; i < cursors.length; ++i) {
			add(cursorNames[i]);
		}
	}
	public Cursor getSelectedCursor() {
		return Cursor.getPredefinedCursor(getSelectedIndex());
	}
	public void setSelectedCursor(Cursor cursor) {
		for(int i=0; i < cursors.length; ++i) {
			if(cursors[i].equals(cursor)) {
				select(i);
				break;
			}
		}
	}
}
