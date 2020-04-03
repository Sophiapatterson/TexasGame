package ooga.engine;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Objects that implement this interface can collide with other objects that implement Collidable.
 */
public interface Collidable {
    /**
     * object will stop movement when colliding with another object that implements Collidable.
     * Furthermore, any other interaction that might occur between the two objects will be performed.
     */
    public void collide(Shape s);
}
