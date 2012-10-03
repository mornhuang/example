import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class SpriteCollisionDetector extends CollisionDetector {
    public SpriteCollisionDetector(CollisionArena arena) {
        super(arena);
    }
    public void detectCollisions() {
        Enumeration sprites = arena.getSprites().elements();
        Sprite      sprite;

        while(sprites.hasMoreElements()) {
            sprite = (Sprite)sprites.nextElement();

            Enumeration otherSprites = 
                            arena.getSprites().elements();
            Sprite otherSprite;

            while(otherSprites.hasMoreElements()) {
                otherSprite=(Sprite)otherSprites.nextElement();

                if(otherSprite != sprite) 
                    if(sprite.willIntersect(otherSprite))
                      arena.spriteCollision(sprite,otherSprite);
            }
        }
    }
}
