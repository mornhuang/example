import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class EdgeCollisionDetector extends CollisionDetector {
	public EdgeCollisionDetector(CollisionArena arena) {
		super(arena);
	}
	public void detectCollisions() {
		Enumeration sprites     = arena.getSprites().elements();
		Dimension   arenaSize   = arena.getSize();
		Insets      arenaInsets = arena.getInsets();
		Sprite      sprite;

		while(sprites.hasMoreElements()) {
			sprite = (Sprite)sprites.nextElement();
            
			Point nl     = sprite.getNextLocation ();
			Point mv     = sprite.getMoveVector();
			int   width  = sprite.getBounds().width;
			int   height = sprite.getBounds().height;
			int   nextRightEdge   = nl.x + width;
			int   nextBottomEdge  = nl.y + height;
			int   arenaBottomEdge = arenaSize.height - 
									arenaInsets.bottom;
			int   arenaRightEdge  = arenaSize.width - 
									arenaInsets.right;

			if(nextRightEdge > arenaRightEdge) 
				arena.edgeCollision(sprite, Orientation.LEFT);
			else if(nl.x < arenaInsets.left)
			arena.edgeCollision(sprite, Orientation.RIGHT);

			if(nextBottomEdge > arenaBottomEdge) 
				arena.edgeCollision(sprite, Orientation.BOTTOM);
			else if(nl.y < arenaInsets.top)
				arena.edgeCollision(sprite, Orientation.TOP);
		}
	}
}
