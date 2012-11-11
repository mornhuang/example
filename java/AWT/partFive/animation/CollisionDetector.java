abstract public class CollisionDetector {
    protected CollisionArena arena;

    abstract public void detectCollisions();

    public CollisionDetector(CollisionArena arena) {
        this.arena = arena;
    }
}
