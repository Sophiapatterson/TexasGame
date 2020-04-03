package ooga.engine;

/**
 * Objects that implement this interface can collide with other objects that implement Collidable.
 */
public interface Collidable {
    /**
     * object will stop movement when colliding with another object that implements Collidable
     */
    public void collide();
}
