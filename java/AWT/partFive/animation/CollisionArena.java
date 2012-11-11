import java.awt.Dimension;
import java.awt.Insets;
import java.util.Vector;

public interface CollisionArena {
    Vector    getSprites();
    Dimension getSize   ();
    Insets    getInsets ();

    void      spriteCollision(Sprite sprite, Sprite other);
    void      edgeCollision(Sprite sprite, Orientation orient); 
}
